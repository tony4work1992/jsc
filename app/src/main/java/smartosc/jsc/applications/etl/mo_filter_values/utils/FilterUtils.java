package smartosc.jsc.applications.etl.mo_filter_values.utils;

import smartosc.jsc.applications.etl.mo_filter_values.constants.FilterConstants;

public class FilterUtils {
    
    public static boolean applyComparison(String columnValue, String expression, String value) {
        switch (expression) {
            case FilterConstants.GREATER_THAN:
                // So sánh kiểu số nếu là ">"
                return Integer.parseInt(columnValue) > Integer.parseInt(value);
            case FilterConstants.LESS_THAN:
                // So sánh kiểu số nếu là "<"
                return Integer.parseInt(columnValue) < Integer.parseInt(value);
            case FilterConstants.EQUALS:
                // So sánh chuỗi nếu là "="
                return columnValue.equals(value);
            case FilterConstants.CONTAINS:
                // So sánh nếu chuỗi chứa giá trị so sánh
                return columnValue.contains(value);
            case FilterConstants.STARTS_WITH:
                // So sánh nếu chuỗi bắt đầu bằng giá trị so sánh
                return columnValue.startsWith(value);
            case FilterConstants.ENDS_WITH:
                // So sánh nếu chuỗi kết thúc bằng giá trị so sánh
                return columnValue.endsWith(value);
            default:
                throw new IllegalArgumentException(FilterConstants.UNSUPPORTED_EXPRESSION_ERROR + expression);
        }
    }
}
