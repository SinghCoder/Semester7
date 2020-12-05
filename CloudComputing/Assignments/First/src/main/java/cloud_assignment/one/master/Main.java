package cloud_assignment.one.master;

import java.util.Iterator;

import javax.ws.rs.Consumes;

//import cloud_assignment.one.spark.SQLExecutor;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.*;

/**
 * Root resource (exposed at "/" path)
 */
@Path("/")
public class Main {
	
	@GET
    @Path("test")
    public String helloWorld() {
        return "Hello World!";
    }
	@POST
    @Path("query")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject runQuery(String sqlQuery) {
		String[] args = {"data/input", "data/output", sqlQuery};
		JSONObject hadoopSpec = new JSONObject();
		try {
			hadoopSpec = SQLExecutor.hadoopOutput(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject sparkSpec = cloud_assignment.one.spark.SQLExecutor.sparkOutput(sqlQuery);
		
		JSONObject merged = new JSONObject();
		JSONObject[] objs = new JSONObject[] { hadoopSpec, sparkSpec };
		
		for (JSONObject obj : objs) {
		    Iterator<String> it = obj.keys();
		    while (it.hasNext()) {
		        String key = (String)it.next();
		        merged.put(key, obj.get(key));
		    }
		}
		
        return merged;
        
    }	
}