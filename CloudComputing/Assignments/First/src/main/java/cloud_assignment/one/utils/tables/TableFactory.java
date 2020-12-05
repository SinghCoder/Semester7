package cloud_assignment.one.utils.tables;

public class TableFactory {
	public static Table getTable(String tableName, String row) {
//		System.out.println(row);
		String[] values = row.split(",");
//		System.out.println("Inside Factory:=======>");
		
		if(tableName.equalsIgnoreCase("category")) {
			if(values.length < 2)
				return null;
			return new CategoryTable(values[0], values[1]);
		}
		else if(tableName.equalsIgnoreCase("product")) {
			if(values.length < 7)
				return null;
			return new ProductTable(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
		}
		else if(tableName.equalsIgnoreCase("review")) {
			if(values.length < 6)
				return null;
			return new ReviewTable(values[0], values[1], values[2], values[3], values[4], values[5]);
		}
		else if(tableName.equalsIgnoreCase("similar")) {
			if(values.length < 2)
				return null;
			return new SimilarTable(values[0], values[1]);
		}
		return null;
	}
}