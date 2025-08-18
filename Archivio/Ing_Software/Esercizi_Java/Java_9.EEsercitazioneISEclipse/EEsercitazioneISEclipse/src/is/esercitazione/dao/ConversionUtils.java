/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author nonplay
 */
public class ConversionUtils {
	
	private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	
	private static DateFormat createDateFormatter() {
		return new SimpleDateFormat(DATE_FORMAT_PATTERN);
	}
	
    public static java.util.Date deserializeDate(String date) {
        if (date == null) {
            return null;
        }
        
        try {
        	return createDateFormatter().parse(date);
        } catch (ParseException e) {
        	throw new IllegalArgumentException("Invalid serialized date: " + date, e);
        }
    }
    
    public static String serializeDate(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return createDateFormatter().format(date);
    }
    
    public static Long readLong(ResultSet resultSet, String columnLabel) throws SQLException {
        long value = resultSet.getLong(columnLabel);
        if (resultSet.wasNull()) {
            return null;
        }
        return value;
    }
    
    public static void setLong(PreparedStatement statement, int columnIndex, Long l) throws SQLException {
        if (l != null) {
            statement.setLong(columnIndex, l);
        } else {
            statement.setNull(columnIndex, java.sql.Types.BIGINT);
        }
    }
}
