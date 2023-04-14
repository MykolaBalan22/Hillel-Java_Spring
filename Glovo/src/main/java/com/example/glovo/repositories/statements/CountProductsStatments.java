package com.example.glovo.repositories.statements;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountProductsStatments implements PreparedStatementCallback<Integer> {

    @Override
    public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }
}
