package cloud_assignment.one.utils.tables;

import java.util.ArrayList;

public class SimilarTable implements Table{
	private String product_id, similar_product_id;
	
	
	
	public SimilarTable(String product_id, String similar_product_id) {
		this.product_id = product_id;
		this.similar_product_id = similar_product_id;
	}

	public Object getColumnValue(String columnName) {
		if(columnName.equalsIgnoreCase("similar_product_id")) {
			return this.similar_product_id;
		}
		else if(columnName.equalsIgnoreCase("product_id")) {
			return this.product_id;
		}
		return null;
	}

	public String groupByString(String[] columns) {
		String res = "";
		for(String columnName : columns) {
			if(columnName.equalsIgnoreCase("similar_product_id")) {
				res = res.concat(this.similar_product_id).concat("_");
			}
			else if(columnName.equalsIgnoreCase("product_id")) {
				res = res.concat(this.product_id).concat("_");
			}
		}
		return res;
	}

	public Boolean checkColumnValue(String columnName, String value) {
//		System.out.println(columnName.concat("@@@@@@@").concat(value));
		if(columnName.equalsIgnoreCase("similar_product_id")) {
			return this.similar_product_id.equalsIgnoreCase(value);
		}
		else if(columnName.equalsIgnoreCase("product_id")) {
			return this.product_id.equalsIgnoreCase(value);
		}
		return false;
	}

	public Object getAggregate(String operation, String column, ArrayList<Table> arr) {
//		System.out.println("inside getaggregate => ");
//		System.out.println(operation);
//		System.out.println(column);
//		System.out.println(arr);
		if(operation.equalsIgnoreCase("count")) {
			return new Integer(arr.size());
		}
		
		return null;
	}

	public Boolean compareAggregate(String column, String operation, String comparisonOperator,
			String value, ArrayList<Table> arr) {
//		System.out.println("Compare aggregate ====> ");
//		System.out.println(column);
//		System.out.println(operation);
//		System.out.println(comparisonOperator);
//		System.out.println(value);
//		System.out.println(arr);
		Double actualValue = Double.parseDouble(getAggregate(operation, column, arr).toString());
		Double toCompareValue = Double.parseDouble(value);
		Double epsilon = 0.0001;
		if(comparisonOperator.equalsIgnoreCase("==")) {
			return Math.abs(actualValue - toCompareValue) < epsilon;
		}
		else if(comparisonOperator.equalsIgnoreCase("!=")) {
			return actualValue != toCompareValue;
		}
		else if(comparisonOperator.equalsIgnoreCase(">=")) {
			return (Math.abs(actualValue - toCompareValue) < epsilon) || actualValue > toCompareValue;
		}
		else if(comparisonOperator.equalsIgnoreCase("<=")) {
			return (Math.abs(actualValue - toCompareValue) < epsilon) || actualValue < toCompareValue;
		}
		else if(comparisonOperator.equalsIgnoreCase(">")) {
			return actualValue > toCompareValue;
		}
		else if(comparisonOperator.equalsIgnoreCase("<")) {
			return actualValue < toCompareValue;
		} 
		return null;
	}
	
}
