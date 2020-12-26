package cloud_assignment.one.spark;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.apache.spark.sql.functions.count;
import static org.apache.spark.sql.functions.sum;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.*;

public class SQLExecutor {
  private static Logger log =
      LoggerFactory.getLogger(SQLExecutor.class);
  private static JSONObject queryJSON;
  private static String sqlQuery;
  private static long time_ms;
  /**
   * main() is your entry point to the application.
   * 
   * @param args
   */
  public static void main(String[] args) {
    SQLExecutor app = new SQLExecutor();
    SQLExecutor.sqlQuery = args[0];
    System.out.println("Parsing Query: " + sqlQuery);    
    SQLExecutor.queryJSON = parseSQL(SQLExecutor.sqlQuery.toLowerCase());    
    System.out.println("Parsed output:\n" + queryJSON.toString(2));
    app.start();
    System.out.println("called");
  }
  
  
  public static JSONObject parseSQL(String query) {
	  Pattern pattern = Pattern.compile("select(.*)(sum|count|max|min)\\((.*?)\\) +from(.*)where(.*?)=(.*)group +by(.*)having +(sum|count|max|min)\\((.*?)\\) *(>=|<=|==|!=|>|<)(.*)");
	  
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
	  }
	  else {
		  log.info("SQL Query parse error");
	  }
	  
	  return queryJSON;
  }
  /**
   * The processing code.
   */
  private void start() {
	  System.out.println("starting");
    // Creates a session on a local master	
    SparkSession spark = SparkSession.builder()
        .appName("Query Executor")
        .master("local[*]")
        .getOrCreate();

    Dataset<Row> df = spark.read().format("csv")
        .option("header", true)
        .option("inferSchema", true)
        .load("data/input/".concat(SQLExecutor.queryJSON.getString("table")).concat(".csv"));
    long start = new Date().getTime();
    df.createOrReplaceTempView(SQLExecutor.queryJSON.getString("table"));
    String sqlStatement = SQLExecutor.sqlQuery;
    Dataset<Row> sqlDf = spark.sql(sqlStatement);
    long end = new Date().getTime();
    sqlDf.show();
    String result = "";
    List<Row> arrayList= new ArrayList<Row>();
    arrayList=sqlDf.collectAsList();
    System.out.println("Results are: " + arrayList);
    JSONObject queryResult = new JSONObject();
    queryResult.append("SparkQueryResult", arrayList.toString());
    
    queryResult.append("Spark Execution Time ", (end-start) + " milliseconds");
    FileWriter fw;
	try {
		fw = new FileWriter("SparkResults.json");
		fw.write(queryResult.toString(2));
		fw.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}    
    time_ms = end - start;
  }

}