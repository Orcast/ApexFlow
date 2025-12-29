package com.apex.util;

import org.slf4j.Logger;
import java.sql.*;

/**
 * Statement wrapper that logs all SQL operations with timing
 */
record LoggingStatement(Statement delegate, Logger logger) implements Statement {

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        long startTime = System.currentTimeMillis();
        logger.debug("Executing query: {}", sanitizeSql(sql));

        try {
            ResultSet result = delegate.executeQuery(sql);
            long duration = System.currentTimeMillis() - startTime;
            logger.debug("Query executed successfully in {} ms: {}", duration, sanitizeSql(sql));
            return result;
        } catch (SQLException e) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("Query failed after {} ms: {} - Error: {}",
                    duration, sanitizeSql(sql), e.getMessage());
            throw e;
        }
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        long startTime = System.currentTimeMillis();
        logger.debug("Executing update: {}", sanitizeSql(sql));

        try {
            int rows = delegate.executeUpdate(sql);
            long duration = System.currentTimeMillis() - startTime;
            logger.debug("Update executed successfully in {} ms. Rows affected: {}, SQL: {}",
                    duration, rows, sanitizeSql(sql));
            return rows;
        } catch (SQLException e) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("Update failed after {} ms: {} - Error: {}",
                    duration, sanitizeSql(sql), e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        long startTime = System.currentTimeMillis();
        logger.debug("Executing statement: {}", sanitizeSql(sql));

        try {
            boolean result = delegate.execute(sql);
            long duration = System.currentTimeMillis() - startTime;
            logger.debug("Statement executed in {} ms. Result: {}, SQL: {}",
                    duration, result, sanitizeSql(sql));
            return result;
        } catch (SQLException e) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("Statement execution failed after {} ms: {} - Error: {}",
                    duration, sanitizeSql(sql), e.getMessage());
            throw e;
        }
    }

    @Override
    public int[] executeBatch() throws SQLException {
        long startTime = System.currentTimeMillis();
        logger.debug("Executing batch update");

        try {
            int[] results = delegate.executeBatch();
            long duration = System.currentTimeMillis() - startTime;
            int totalRows = 0;
            for (int rows : results) {
                if (rows >= 0) totalRows += rows;
            }
            logger.debug("Batch update executed in {} ms. Total rows affected: {}",
                    duration, totalRows);
            return results;
        } catch (SQLException e) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("Batch update failed after {} ms - Error: {}",
                    duration, e.getMessage());
            throw e;
        }
    }

    @Override
    public void close() throws SQLException {
        logger.trace("Closing statement");
        delegate.close();
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        logger.trace("Adding to batch: {}", sanitizeSql(sql));
        delegate.addBatch(sql);
    }

    @Override
    public void clearBatch() throws SQLException {
        logger.trace("Clearing batch");
        delegate.clearBatch();
    }

    // Sanitize SQL for logging (truncate long SQL)
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
    public int getMaxFieldSize() throws SQLException {
        return delegate.getMaxFieldSize();
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        delegate.setMaxFieldSize(max);
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
    public void setEscapeProcessing(boolean enable) throws SQLException {
        delegate.setEscapeProcessing(enable);
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
    public void cancel() throws SQLException {
        delegate.cancel();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return delegate.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        delegate.clearWarnings();
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        delegate.setCursorName(name);
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return delegate.getResultSet();
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return delegate.getUpdateCount();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return delegate.getMoreResults();
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        delegate.setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return delegate.getFetchDirection();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        delegate.setFetchSize(rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return delegate.getFetchSize();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return delegate.getResultSetConcurrency();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return delegate.getResultSetType();
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
    public boolean getMoreResults(int current) throws SQLException {
        return delegate.getMoreResults(current);
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
    public int getResultSetHoldability() throws SQLException {
        return delegate.getResultSetHoldability();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return delegate.isClosed();
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        delegate.setPoolable(poolable);
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return delegate.isPoolable();
    }
}
