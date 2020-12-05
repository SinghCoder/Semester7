package cloud_assignment.one.utils.tables;

import java.util.ArrayList;

public class ProductTable implements Table{

	private String asin, title, group;
	private Double average_rating;
	private Integer id;
	private Integer downloaded, salesrank;
	
	public ProductTable(String id, String asin, String title, String group, String salesrank, String average_rating,
			String downloaded) {		
		this.id = new Integer(Integer.parseInt(id));
		this.asin = asin;
		this.title = title;
		this.group = group;
		this.average_rating = new Double(Double.parseDouble(average_rating));
		this.downloaded = new Integer(Integer.parseInt(downloaded));
		this.salesrank = new Integer(Integer.parseInt(salesrank));
	}
	
	public Object getColumnValue(String columnName) {
		if(columnName.equalsIgnoreCase("asin")) {
			return this.asin;
		}
		else if(columnName.equalsIgnoreCase("title")) {
			return this.title;
		}
		else if(columnName.equalsIgnoreCase("group")) {
			return this.group;
		}
		else if(columnName.equalsIgnoreCase("average_rating")) {
			return this.average_rating;
		}
		else if(columnName.equalsIgnoreCase("downloaded")) {
			return this.downloaded;
		}
		else if(columnName.equalsIgnoreCase("salesrank")) {
			return this.salesrank;
		}
		else if(columnName.equalsIgnoreCase("id")) {
			return this.id;
		}
		return null;
	}

	public String groupByString(String[] columns) {
		String res = "";
		for(String columnName : columns) {
			if(columnName.equalsIgnoreCase("asin")) {
				res = res.concat(this.asin);
			}
			else if(columnName.equalsIgnoreCase("title")) {
				res = res.concat(this.title);
			}
			else if(columnName.equalsIgnoreCase("group")) {
				res = res.concat(this.group);
			}
			else if(columnName.equalsIgnoreCase("average_rating")) {
				res = res.concat(this.average_rating.toString()).concat("_");
			}
			else if(columnName.equalsIgnoreCase("downloaded")) {
				res = res.concat(this.downloaded.toString()).concat("_");
			}
			else if(columnName.equalsIgnoreCase("salesrank")) {
				res = res.concat(this.salesrank.toString()).concat("_");
			}
			else if(columnName.equalsIgnoreCase("id")) {
				res = res.concat(this.id.toString()).concat("_");
			}
		}
		return res;
	}

	public Boolean checkColumnValue(String columnName, String value) {
		if(columnName.equalsIgnoreCase("asin")) {
			return this.asin.equalsIgnoreCase(value);
		}
		else if(columnName.equalsIgnoreCase("title")) {
			return this.title.equalsIgnoreCase(value);
		}
		else if(columnName.equalsIgnoreCase("group")) {
			return this.group.equalsIgnoreCase(value);
		}
		else if(columnName.equalsIgnoreCase("average_rating")) {
			return this.average_rating == Double.parseDouble(value);
		}
		else if(columnName.equalsIgnoreCase("downloaded")) {
			return this.downloaded == Integer.parseInt(value);
		}
		else if(columnName.equalsIgnoreCase("salesrank")) {
			return this.salesrank == Integer.parseInt(value);
		}
		else if(columnName.equalsIgnoreCase("id")) {
			return this.id == Integer.parseInt(value);
		}
		return null;
	}

	public Object getAggregate(String operation, String column, ArrayList<Table> arr) {
		if(operation.equalsIgnoreCase("count")) {
			return new Integer(arr.size());
		}
		else if(column.equalsIgnoreCase("asin") || column.equalsIgnoreCase("title") || column.equalsIgnoreCase("group")) {
			return null;
		}
		else if(operation.equalsIgnoreCase("sum")) {
			double sum = 0;
			System.out.println("printing values ====================");
			for(Table r : arr) {
				System.out.println(Integer.parseInt(r.getColumnValue(column).toString()));
				sum += Integer.parseInt(r.getColumnValue(column).toString());
			}
			System.out.println(sum);
			return new Double(sum);
		}
		else if(operation.equalsIgnoreCase("max")) {
			double maxVal = Double.NEGATIVE_INFINITY;
			for(Table r : arr) {
				maxVal = Double.max(maxVal, (Double)r.getColumnValue(column));
			}
			return new Double(maxVal);
		}
		else if(operation.equalsIgnoreCase("min")) {
			double minVal = Double.POSITIVE_INFINITY;
			for(Table r : arr) {
				minVal = Double.min(minVal, (Double)r.getColumnValue(column));
			}
			return new Double(minVal);
		}
		
		return null;
	}

	public Boolean compareAggregate(String column, String operation, String comparisonOperator, String value, ArrayList<Table> arr) {
		Double actualValue = Double.parseDouble(getAggregate(operation, column, arr).toString());;
		Double toCompareValue = Double.parseDouble(value);
		System.out.println("comparing values  ");
		System.out.println(actualValue);
		System.out.println(toCompareValue);
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
