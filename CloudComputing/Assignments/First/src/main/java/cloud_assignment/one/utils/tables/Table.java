package cloud_assignment.one.utils.tables;

import java.util.ArrayList;

public interface Table {
	Object getColumnValue(String columnName);
	String groupByString(String[] columns);
	Boolean checkColumnValue(String columnName, String value);
	Object getAggregate(String operation, String column, ArrayList<Table> arr);
	Boolean compareAggregate(String column, String operation, String comparisonOperator, String value, ArrayList<Table> arr);
}
