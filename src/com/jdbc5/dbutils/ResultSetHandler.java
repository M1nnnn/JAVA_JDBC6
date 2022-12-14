package com.jdbc5.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface ResultSetHandler<T> {
        public T handle(ResultSet resultSet) throws SQLException;
}
