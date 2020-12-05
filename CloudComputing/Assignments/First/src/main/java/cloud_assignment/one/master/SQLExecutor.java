package cloud_assignment.one.master;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.*;
import org.apache.log4j.Logger;
import org.json.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import cloud_assignment.one.utils.tables.*;

public class SQLExecutor {
	public static String[] jsonArrayToStringArray(JSONArray jsonArray) {
	    int arraySize = jsonArray.length();
	    String[] stringArray = new String[arraySize];

	    for(int i=0; i<arraySize; i++) {
	        stringArray[i] = (String) jsonArray.get(i);
	    }

	    return stringArray;
	};
  static final Logger log = Logger.getLogger(SQLExecutor.class.getName());
  
  public static class QueryMapper
       extends Mapper<LongWritable, Text, Text, Text>{	
	private static JSONObject queryJSON;
	public void setup(Context context) {
		Configuration conf = context.getConfiguration();
        setQueryJSON(new JSONObject(conf.get("queryJSONString")));
    }
    private Text outputRow = new Text();

    public void map(LongWritable key, Text value, Context context
                    ) throws IOException, InterruptedException {
    	if(key.get() == 0) {
    		return;
    	}
    	String tableName = queryJSON.getString("table");
    	Table row = TableFactory.getTable(tableName, value.toString());
    	if(row == null)
			return;
//    	System.out.println("row is not null");
    	JSONObject whereJSON = queryJSON.getJSONObject("where");
//    	System.out.println(whereJSON);
    	if(row.checkColumnValue(whereJSON.getString("field"), whereJSON.getString("value")) == false) {
    		return;
    	}
//    	System.out.println("row matches column value");
    	outputRow.set(row.groupByString(jsonArrayToStringArray(queryJSON.getJSONArray("groupByColumns"))));
    	context.write(outputRow, value);
    }

	public static JSONObject getQueryJSON() {
		return queryJSON;
	}

	public static void setQueryJSON(JSONObject queryJSON) {
		QueryMapper.queryJSON = queryJSON;
	}
  }

  public static class QueryReducer
       extends Reducer<Text,Text,Text,Text> {
	  private static JSONObject queryJSON;
		public void setup(Context context) {
			Configuration conf = context.getConfiguration();
//			System.out.println("Inside Reducer Setup ========> ");
//			System.out.println(conf.get("queryJSONString"));
	        setQueryJSON(new JSONObject(conf.get("queryJSONString")));
//	        System.out.println("Inside reducer Setup =====> ".concat(queryJSON.toString()));
	    }
	    private Text outputRowText = new Text();

    public void reduce(Text key, Iterable<Text> values,
                       Context context
                       ) throws IOException, InterruptedException {
//    	System.out.println(key.toString());
    	String tableName = queryJSON.getString("table");
    	ArrayList<Table> arr = new ArrayList<Table>();
    	for(Text v : values) {
//    		System.out.println("value in reducer =====> ");
//    		System.out.println(v.toString());
    		Table row = TableFactory.getTable(tableName, v.toString());
//    		System.out.println("inside reducer === ");
//    		System.out.println(v.toString());
    		if(row == null)
    			continue;
    		arr.add(row);
    	}
    	System.out.println(arr.size());
    	if(arr.size() == 0)
    		return;
    	JSONObject havingJSON = queryJSON.getJSONObject("having");
    	JSONObject aggregateJSON = queryJSON.getJSONObject("aggregate");
    	JSONArray columnsArray = queryJSON.getJSONArray("columns");
    	String outputRow = new String("");
    	int i = 0;
//    	System.out.println("arr =====> ");
//    	System.out.println(arr.get(0));
//    	System.out.println(havingJSON);
    	String aggr = arr.get(0).getAggregate(aggregateJSON.getString("function"), aggregateJSON.getString("field"), arr).toString();
    	if(arr.get(0).compareAggregate(havingJSON.getString("column"), havingJSON.getString("function"), 
    			havingJSON.getString("operator"), havingJSON.getString("value"), arr) == false) {
    		System.out.println("aggregate not matched================");
    		return;
    	}
		for(i = 0; i < columnsArray.length(); i++) {
//			System.out.println("columns array ===>");
//			System.out.println(arr.get(0));
			outputRow = outputRow.concat(arr.get(0).getColumnValue(columnsArray.getString(i)).toString()).concat(",");
		}
		outputRow = outputRow.concat(aggr);
		System.out.println("printing oputput row === ");
		System.out.println(outputRow);
		outputRowText.set(outputRow);
		System.out.println("Writing out from reducer ==============");
		context.write(key, outputRowText); 	
    }
    public static JSONObject getQueryJSON() {
		return queryJSON;
	}

	public static void setQueryJSON(JSONObject queryJSON) {
		QueryReducer.queryJSON = queryJSON;
	}
  }
  
  public static JSONObject parseSQL(String query) {
	  Pattern pattern = Pattern.compile("select(.)(sum|count|max|min)\\((.?)\\) +from(.)where(.?)=(.)group +by(.)having +(sum|count|max|min)\\((.?)\\) *(>=|<=|==|!=|>|<)(.)");
	  
	  Matcher matcher = pattern.matcher(query);
	  JSONObject queryJSON = new JSONObject();
	  
	  if(matcher.matches()) {
		  String[] columns = matcher.group(1).trim().split(",");
		  JSONArray columnsList = new JSONArray();
		  for(String column : columns) {
			  columnsList.put(column.trim());
		  }
		  queryJSON.put("columns", columnsList);
		  
		  JSONObject aggregateJSON = new JSONObject();
		  aggregateJSON.put("function", matcher.group(2).trim());
		  aggregateJSON.put("field", matcher.group(3).trim());
		  queryJSON.put("aggregate", aggregateJSON);
		  
		  queryJSON.put("table", matcher.group(4).trim());
		  
		  JSONObject whereJSON = new JSONObject();
		  whereJSON.put("field", matcher.group(5).trim());
		  whereJSON.put("value", matcher.group(6).trim());
		  queryJSON.put("where", whereJSON);
		  
		  String[] groupByColumns = matcher.group(7).trim().split(",");
		  JSONArray groupByColumnsList = new JSONArray();
		  for(String groupByColumn : groupByColumns) {
			  groupByColumnsList.put(groupByColumn.trim());
		  }
		  queryJSON.put("groupByColumns", groupByColumnsList);
		  
		  JSONObject havingJSON = new JSONObject();
		  havingJSON.put("function", matcher.group(8).trim());
		  havingJSON.put("column", matcher.group(9).trim());
		  havingJSON.put("operator", matcher.group(10).trim());
		  havingJSON.put("value", matcher.group(11).trim());
		  
		  queryJSON.put("having", havingJSON);
		  log.info(queryJSON);
	  }
	  else {
		  log.info("SQL Query parse error");
	  }
	  
	  return queryJSON;
  }
  
  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();     
    String sqlQuery = args[2];
    JSONObject queryJSON = parseSQL(sqlQuery.toLowerCase());
    System.out.println(queryJSON);
    conf.set("queryJSONString", queryJSON.toString());
    
    Job job = Job.getInstance(conf, "word count");   
    job.setJarByClass(SQLExecutor.class);
    job.setMapperClass(QueryMapper.class);
    job.setReducerClass(QueryReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(args[0].concat("/").concat(queryJSON.getString("table")).concat(".csv")));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    long start = new Date().getTime();
    boolean status = job.waitForCompletion(true);            
    long end = new Date().getTime();
    System.out.println("Hadoop Execution Time "+(end-start) + "milliseconds");
    System.out.println("Mapper input -> <LongWritable, Text> (offset, line of file)");
    System.out.println("Mapper output -> <Text, Text> (columns in group by separated by _, row satisfying where condition)");
    System.out.println("Reducer input -> <Text, Text> (columns in group by separated by _, row)");
    System.out.println("Reducer output -> <Text, Text> (columns in group by separated by _, row containing required columns and satisfying having condition)");
    
    
//    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
  
  public static JSONObject hadoopOutput(String[] args) throws Exception {
	String sqlQuery = args[2];
    Configuration conf = new Configuration();     
    JSONObject queryJSON = parseSQL(sqlQuery.toLowerCase());
    System.out.println(queryJSON);
    conf.set("queryJSONString", queryJSON.toString());
    
    Job job = Job.getInstance(conf, "word count");   
    job.setJarByClass(SQLExecutor.class);
    job.setMapperClass(QueryMapper.class);
    job.setReducerClass(QueryReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(args[0].concat("/").concat(queryJSON.getString("table")).concat(".csv")));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    long start = new Date().getTime();
    boolean status = job.waitForCompletion(true);            
    long end = new Date().getTime();
//    System.out.println("Hadoop Execution Time "+(end-start) + "milliseconds");
//    System.out.println("Mapper input -> <LongWritable, Text> (offset, line of file)");
//    System.out.println("Mapper output -> <Text, Text> (columns in group by separated by _, row satisfying where condition)");
//    System.out.println("Reducer input -> <Text, Text> (columns in group by separated by _, row)");
//    System.out.println("Reducer output -> <Text, Text> (columns in group by separated by _, row containing required columns and satisfying having condition)");
    
    JSONObject hadoopSpecifications = new JSONObject();
    hadoopSpecifications.put("Hadoop Execution Time in ms", new Long(end-start));
    hadoopSpecifications.put("Mapper input", "<LongWritable, Text> (offset, line of file)");
    hadoopSpecifications.put("Mapper output", "<Text, Text> (columns in group by separated by _, row satisfying where condition)");
    hadoopSpecifications.put("Reducer input", "<Text, Text> (columns in group by separated by _, row)");
    hadoopSpecifications.put("Reducer output", "<Text, Text> (columns in group by separated by _, row containing required columns and satisfying having condition)");
    
    return hadoopSpecifications;
//	    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}