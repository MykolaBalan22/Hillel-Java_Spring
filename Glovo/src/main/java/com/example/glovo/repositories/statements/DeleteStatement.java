package com.example.glovo.repositories.statements;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatement implements PreparedStatementCallback<Integer> {
    @Override
    public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
        return ps.executeUpdate();
    }
}
