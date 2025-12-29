package com.apex.util;

import org.slf4j.Logger;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * PreparedStatement wrapper that logs SQL with parameters and timing
 */
public class LoggingPreparedStatement implements PreparedStatement {
    private final PreparedStatement delegate;
    private final String originalSql;
    private final Logger logger;
    private final List<Parameter> parameters = new ArrayList<>();

    public LoggingPreparedStatement(PreparedStatement delegate, String sql, Logger logger) {
        this.delegate = delegate;
        this.originalSql = sql;
        this.logger = logger;
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        long startTime = System.currentTimeMillis();

        try {
            ResultSet result = delegate.executeQuery();
            long duration = System.currentTimeMillis() - startTime;

            logger.debug("PreparedQuery executed in {} ms: {} with params: {}",
                    duration, sanitizeSql(originalSql), getParametersString());
            return result;
        } catch (SQLException e) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("PreparedQuery failed after {} ms: {} with params: {} - Error: {}",
                    duration, sanitizeSql(originalSql), getParametersString(), e.getMessage());
            throw e;
        }
    }

    @Override
    public int executeUpdate() throws SQLException {
        long startTime = System.currentTimeMillis();

        try {
            int rows = delegate.executeUpdate();
            long duration = System.currentTimeMillis() - startTime;

            logger.debug("PreparedUpdate executed in {} ms. Rows affected: {}, SQL: {} with params: {}",
                    duration, rows, sanitizeSql(originalSql), getParametersString());
            return rows;
        } catch (SQLException e) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("PreparedUpdate failed after {} ms: {} with params: {} - Error: {}",
                    duration, sanitizeSql(originalSql), getParametersString(), e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean execute() throws SQLException {
        long startTime = System.currentTimeMillis();

        try {
            boolean result = delegate.execute();
            long duration = System.currentTimeMillis() - startTime;

            logger.debug("PreparedStatement executed in {} ms. Result: {}, SQL: {} with params: {}",
                    duration, result, sanitizeSql(originalSql), getParametersString());
            return result;
        } catch (SQLException e) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("PreparedStatement failed after {} ms: {} with params: {} - Error: {}",
                    duration, sanitizeSql(originalSql), getParametersString(), e.getMessage());
            throw e;
        }
    }

    @Override
    public int[] executeBatch() throws SQLException {
        long startTime = System.currentTimeMillis();
        logger.debug("Executing prepared statement batch");

        try {
            int[] results = delegate.executeBatch();
            long duration = System.currentTimeMillis() - startTime;

            int totalRows = 0;
            for (int rows : results) {
                if (rows >= 0) totalRows += rows;
            }

            logger.debug("Prepared batch executed in {} ms. Total rows affected: {}, SQL: {}",
                    duration, totalRows, sanitizeSql(originalSql));
            return results;
        } catch (SQLException e) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("Prepared batch failed after {} ms: {} - Error: {}",
                    duration, sanitizeSql(originalSql), e.getMessage());
            throw e;
        }
    }

    @Override
    public void addBatch() throws SQLException {
        logger.trace("Adding prepared statement to batch: {} with params: {}",
                sanitizeSql(originalSql), getParametersString());
        delegate.addBatch();
        clearParameters(); // Clear for next batch
    }

    @Override
    public void clearParameters() throws SQLException {
        parameters.clear();
        delegate.clearParameters();
    }

    @Override
    public void close() throws SQLException {
        logger.trace("Closing prepared statement: {}", sanitizeSql(originalSql));
        delegate.close();
    }

    // Parameter setting methods with logging
    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        logParameter(parameterIndex, "NULL");
        delegate.setNull(parameterIndex, sqlType);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        logParameter(parameterIndex, x);
        delegate.setBoolean(parameterIndex, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        logParameter(parameterIndex, x);
        delegate.setByte(parameterIndex, x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        logParameter(parameterIndex, x);
        delegate.setShort(parameterIndex, x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        logParameter(parameterIndex, x);
        delegate.setInt(parameterIndex, x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        logParameter(parameterIndex, x);
        delegate.setLong(parameterIndex, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        logParameter(parameterIndex, x);
        delegate.setFloat(parameterIndex, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        logParameter(parameterIndex, x);
        delegate.setDouble(parameterIndex, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        logParameter(parameterIndex, x);
        delegate.setBigDecimal(parameterIndex, x);
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        logParameter(parameterIndex, maskSensitiveData(parameterIndex, x));
        delegate.setString(parameterIndex, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        logParameter(parameterIndex, x != null ? "BYTE_ARRAY[" + x.length + " bytes]" : "null");
        delegate.setBytes(parameterIndex, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        logParameter(parameterIndex, x);
        delegate.setDate(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        logParameter(parameterIndex, x);
        delegate.setTime(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        logParameter(parameterIndex, x);
        delegate.setTimestamp(parameterIndex, x);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        logParameter(parameterIndex, x);
        delegate.setObject(parameterIndex, x);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        logParameter(parameterIndex, "NULL");
        delegate.setNull(parameterIndex, sqlType, typeName);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        logParameter(parameterIndex, x);
        delegate.setObject(parameterIndex, x, targetSqlType);
    }

    // Helper method to log parameter values
    private void logParameter(int index, Object value) {
        // Ensure the list is big enough
        while (parameters.size() < index) {
            parameters.add(new Parameter(parameters.size() + 1, "NOT_SET"));
        }

        parameters.set(index - 1, new Parameter(index, value));

        logger.trace("Set parameter {} = {} for SQL: {}", index, value, sanitizeSql(originalSql));
    }

    // Get formatted parameter string for logging
    private String getParametersString() {
        if (parameters.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < parameters.size(); i++) {
            if (i > 0) sb.append(", ");
            Parameter param = parameters.get(i);
            sb.append(param.index).append("=").append(formatValue(param.value));
        }
        sb.append("]");
        return sb.toString();
    }

    // Format value for logging
    private String formatValue(Object value) {
        if (value == null) return "null";
        if (value instanceof String str) {
            if (str.length() > 50) {
                return "\"" + str.substring(0, 50) + "...\"";
            }
            return "\"" + str + "\"";
        }
        return String.valueOf(value);
    }

    // Mask sensitive data in parameters
    private String maskSensitiveData(int parameterIndex, String value) {
        if (value == null) return null;

        // Check if this might be sensitive data (e.g., password, token, etc.)
        String lowerValue = value.toLowerCase();
        String lowerSql = originalSql.toLowerCase();

        if (lowerSql.contains("password") || lowerSql.contains("token") || lowerSql.contains("secret")) {
            return "***MASKED***";
        }

        // Mask based on field names (parameter index mapping)
        if (parameterIndex == 3) { // Often password field in user insert/update
            if (!lowerValue.isEmpty()) {
                return "***MASKED***";
            }
        }

        return value;
    }

    // Sanitize SQL for logging
    private String sanitizeSql(String sql) {
        if (sql == null) return "null";
        if (sql.length() > 500) {
            return sql.substring(0, 500) + "... [TRUNCATED]";
        }
        return sql;
    }

    // Delegate all other methods
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return delegate.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return delegate.isWrapperFor(iface);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        delegate.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        delegate.setUnicodeStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        delegate.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void clearBatch() throws SQLException {
        delegate.clearBatch();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        delegate.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        delegate.setRef(parameterIndex, x);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        delegate.setBlob(parameterIndex, x);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        delegate.setClob(parameterIndex, x);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        delegate.setArray(parameterIndex, x);
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return delegate.getMetaData();
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        delegate.setDate(parameterIndex, x, cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        delegate.setTime(parameterIndex, x, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        delegate.setTimestamp(parameterIndex, x, cal);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        delegate.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        delegate.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        delegate.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        delegate.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        delegate.setAsciiStream(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        delegate.setBinaryStream(parameterIndex, x);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        delegate.setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        delegate.setNCharacterStream(parameterIndex, value);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        delegate.setNClob(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        delegate.setClob(parameterIndex, reader);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        delegate.setBlob(parameterIndex, inputStream, length);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        delegate.setNClob(parameterIndex, reader, length);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        delegate.setClob(parameterIndex, reader);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        delegate.setBlob(parameterIndex, inputStream);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        delegate.setNClob(parameterIndex, reader);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        delegate.setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        delegate.setRowId(parameterIndex, x);
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        delegate.setNString(parameterIndex, value);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        delegate.setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        delegate.setURL(parameterIndex, x);
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return delegate.getParameterMetaData();
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return delegate.executeQuery(sql);
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        return delegate.executeUpdate(sql);
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        return delegate.execute(sql);
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        delegate.addBatch(sql);
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return delegate.getResultSetHoldability();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return delegate.isClosed();
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return delegate.isPoolable();
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        delegate.setPoolable(poolable);
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        delegate.closeOnCompletion();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return delegate.isCloseOnCompletion();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return delegate.getConnection();
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return delegate.getGeneratedKeys();
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return delegate.executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return delegate.executeUpdate(sql, columnIndexes);
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return delegate.executeUpdate(sql, columnNames);
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return delegate.execute(sql, autoGeneratedKeys);
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return delegate.execute(sql, columnIndexes);
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return delegate.execute(sql, columnNames);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return delegate.getFetchDirection();
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        delegate.setFetchDirection(direction);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return delegate.getFetchSize();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        delegate.setFetchSize(rows);
    }

    @Override
    public int getResultSetType() throws SQLException {
        return delegate.getResultSetType();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return delegate.getResultSetConcurrency();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return delegate.getMoreResults();
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return delegate.getUpdateCount();
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return delegate.getMoreResults(current);
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return delegate.getResultSet();
    }

    @Override
    public void clearWarnings() throws SQLException {
        delegate.clearWarnings();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return delegate.getWarnings();
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        delegate.setCursorName(name);
    }

    @Override
    public void cancel() throws SQLException {
        delegate.cancel();
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return delegate.getQueryTimeout();
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        delegate.setQueryTimeout(seconds);
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        delegate.setEscapeProcessing(enable);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return delegate.getMaxRows();
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        delegate.setMaxRows(max);
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return delegate.getMaxFieldSize();
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        delegate.setMaxFieldSize(max);
    }

    // Parameter holder class
    private record Parameter(int index, Object value) {
    }
}
