package cloud_assignment.one.utils.tables;

import java.util.ArrayList;
import java.util.Calendar;

public class ReviewTable implements Table{
	private String customer_id, product_id;
	private Integer rating, votes, helpful;
	private Calendar dt;
	public ReviewTable(String customer_id, String product_id, String rating, String votes, String helpful, String date) {
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.rating = new Integer(Integer.parseInt(rating));
		this.votes = new Integer(Integer.parseInt(votes));
		this.helpful = new Integer(Integer.parseInt(helpful));
		String[] dateParams = date.split("-");
		this.dt = Calendar.getInstance();
		this.dt.set(Integer.parseInt(dateParams[0])+1900, Integer.parseInt(dateParams[1]), Integer.parseInt(dateParams[2]));
	}
	public Object getColumnValue(String columnName) {
		if(columnName.equalsIgnoreCase("customer_id")) {
			return this.customer_id;
		}
		else if(columnName.equalsIgnoreCase("product_id")) {
			return this.product_id;
		}
		else if(columnName.equalsIgnoreCase("rating")) {
			return this.rating;
		}
		else if(columnName.equalsIgnoreCase("votes")) {
			return this.votes;
		}
		else if(columnName.equalsIgnoreCase("helpful")) {
			return this.helpful;
		}
		else if(columnName.equalsIgnoreCase("date")) {
			
		}
		return null;
	}
	public String groupByString(String[] columns) {
		String res = "";
		for(String columnName : columns) {
			if(columnName.equalsIgnoreCase("customer_id")) {
				res = res.concat(this.customer_id).concat("_");
			}
			else if(columnName.equalsIgnoreCase("product_id")) {
				res = res.concat(this.product_id).concat("_");
			}
			else if(columnName.equalsIgnoreCase("rating")) {
				res = res.concat(this.rating.toString()).concat("_");
			}
			else if(columnName.equalsIgnoreCase("votes")) {
				res = res.concat(this.votes.toString()).concat("_");
			}
			else if(columnName.equalsIgnoreCase("helpful")) {
				res = res.concat(this.helpful.toString()).concat("_");
			}
			else if(columnName.equalsIgnoreCase("date")) {
				res = res.concat(this.dt.toString()).concat("_");
			}
		}
		return res;
	}
	public Boolean checkColumnValue(String columnName, String value) {
		if(columnName.equalsIgnoreCase("customer_id")) {
			return this.customer_id.equalsIgnoreCase(value);
		}
		else if(columnName.equalsIgnoreCase("product_id")) {
			return this.product_id.equalsIgnoreCase(value);
		}
		else if(columnName.equalsIgnoreCase("rating")) {
			return this.rating == Integer.parseInt(value);
		}
		else if(columnName.equalsIgnoreCase("votes")) {
			return this.votes == Integer.parseInt(value);
		}
		else if(columnName.equalsIgnoreCase("helpful")) {
			return this.helpful == Integer.parseInt(value);
		}
		else if(columnName.equalsIgnoreCase("date")) {
			String[] dateParams = value.split("-");
			Calendar d = Calendar.getInstance();
			d.set(Integer.parseInt(dateParams[0])+1900, Integer.parseInt(dateParams[1]), Integer.parseInt(dateParams[2]));
			return this.dt.get(Calendar.YEAR) == d.get(Calendar.YEAR) && this.dt.get(Calendar.MONTH) == d.get(Calendar.MONTH) && this.dt.get(Calendar.DAY_OF_MONTH) == d.get(Calendar.DAY_OF_MONTH);
		}
		return null;
	}
	public Object getAggregate(String operation, String column, ArrayList<Table> arr) {
		if(operation.equalsIgnoreCase("count")) {
			return new Integer(arr.size());
		}
		else if(column.equalsIgnoreCase("customer_id") || column.equalsIgnoreCase("product_id") || column.equalsIgnoreCase("date")) {
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
		System.out.println("comparing aggregates ==============");
		System.out.println(actualValue);
		System.out.println(toCompareValue);
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
