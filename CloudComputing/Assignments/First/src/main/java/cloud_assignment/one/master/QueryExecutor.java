package cloud_assignment.one.master;
import java.util.Iterator;
import org.json.*;

public class QueryExecutor {	
    public static void main(String[] args) {
		String sqlQuery = args[0];
		String[] arguements = {"data/input", "data/output", sqlQuery};
		JSONObject hadoopSpec;
		JSONObject sparkSpec;
		try {
			hadoopSpec = SQLExecutor.hadoopOutput(arguements);
//			sparkSpec = cloud_assignment.one.spark.SQLExecutor.sparkOutput(sqlQuery);
			JSONObject merged = new JSONObject();
			JSONObject[] objs = new JSONObject[] { hadoopSpec};
			
			for (JSONObject obj : objs) {
				Iterator<String> it = obj.keys();
				while (it.hasNext()) {
					String key = (String)it.next();
					merged.put(key, obj.get(key));
				}
			}
			System.out.println(merged.toString(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }	
}