package cloud_assignment.one.utils.tables;

import java.util.ArrayList;

public class CategoryTable implements Table{
	private String category_name;
	private Integer category_code;
	public CategoryTable(String categoryName, String categoryCode) {
		this.category_name = categoryName;
		this.category_code = Integer.parseInt(categoryCode);
	}
	public Object getColumnValue(String columnName) {
		if(columnName.equalsIgnoreCase("category_name")) {
			return this.category_name;
		}
		else if(columnName.equalsIgnoreCase("category_code")) {
			return this.category_code;
		}
		return null;
	}
	public String groupByString(String[] columns) {
		String res = "";
		for(String columnName : columns) {
			if(columnName.equalsIgnoreCase("category_name")) {
				res = res.concat(this.category_name).concat("_");
			}
			else if(columnName.equalsIgnoreCase("category_code")) {
				res = res.concat(this.category_code.toString()).concat("_");
			}
		}
		return res;
	}
	public Boolean checkColumnValue(String columnName, String value) {
		if(columnName.equalsIgnoreCase("category_name")) {
			return this.category_name.equalsIgnoreCase(value);
		}
		else if(columnName.equalsIgnoreCase("category_code")) {
			return this.category_code == (int)Integer.parseInt(value);
		}
		return null;
	}
	public Object getAggregate(String operation, String column, ArrayList<Table> arr) {
		if(operation.equalsIgnoreCase("count")) {
			return new Integer(arr.size());
		}
		else if(column.equalsIgnoreCase("category_name")) {
			return null;
		}
		else if(operation.equalsIgnoreCase("sum")) {
			int sum = 0;
			for(Table r : arr) {
				sum += (Integer)r.getColumnValue(column);
			}
			return new Integer(sum);
		}
		else if(operation.equalsIgnoreCase("max")) {
			int maxVal = Integer.MIN_VALUE;
			for(Table r : arr) {
				maxVal = Integer.max(maxVal, (Integer)r.getColumnValue(column));
			}
			return new Integer(maxVal);
		}
		else if(operation.equalsIgnoreCase("min")) {
			int minVal = Integer.MAX_VALUE;
			for(Table r : arr) {
				minVal = Integer.min(minVal, (Integer)r.getColumnValue(column));
			}
			return new Integer(minVal);
		}
		
		return null;
	}
	public Boolean compareAggregate(String column, String operation, String comparisonOperator, String value, ArrayList<Table> arr) {
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
