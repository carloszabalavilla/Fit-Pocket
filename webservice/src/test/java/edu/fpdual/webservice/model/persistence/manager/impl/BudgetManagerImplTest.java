package edu.fpdual.webservice.model.persistence.manager.impl;

import edu.fpdual.webservice.model.persistence.dao.Budget;
import org.junit.jupiter.api.Test;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BudgetManagerImplTest {
    private final BudgetManagerImpl manager = new BudgetManagerImpl();

    @Test void createReportsSuccessfulInsert() throws Exception {
        Connection con = mock(Connection.class);
        PreparedStatement stm = mock(PreparedStatement.class);
        when(con.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(stm);
        when(stm.executeUpdate()).thenReturn(1);
        Budget budget = new Budget(); budget.setBudgetName("Example"); budget.setCurrencyId(2);
        assertTrue(manager.create(con, budget));
        verify(stm).setInt(4, 2);
        verify(stm).close();
    }
    @Test void updateBindsTheBudgetId() throws Exception {
        Connection con = mock(Connection.class);
        PreparedStatement stm = mock(PreparedStatement.class);
        when(con.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(stm);
        when(stm.executeUpdate()).thenReturn(1);
        Budget budget = new Budget(); budget.setBudgetId(42);
        assertTrue(manager.update(con, budget));
        verify(stm).setInt(4, 42);
    }
    @Test void deleteReportsMissingRecord() throws Exception {
        Connection con = mock(Connection.class);
        PreparedStatement stm = mock(PreparedStatement.class);
        when(con.prepareStatement(anyString())).thenReturn(stm);
        assertFalse(manager.delete(con, new Budget()));
        verify(stm).close();
    }
    @Test void findAllReturnsEmptyListWhenNoRowsExist() throws Exception {
        Connection con = mock(Connection.class); Statement stm = mock(Statement.class);
        ResultSet rows = mock(ResultSet.class);
        when(con.createStatement()).thenReturn(stm);
        when(stm.executeQuery(anyString())).thenReturn(rows);
        assertTrue(manager.findAll(con).isEmpty());
    }
    @Test void filteredReadDoesNotRewindForwardOnlyResultSet() throws Exception {
        Connection con = mock(Connection.class); PreparedStatement stm = mock(PreparedStatement.class);
        ResultSet rows = mock(ResultSet.class);
        when(con.prepareStatement(anyString())).thenReturn(stm);
        when(stm.executeQuery()).thenReturn(rows);
        assertTrue(manager.findAllBy(con, "userId", 1).isEmpty());
        verify(rows, never()).beforeFirst();
    }
    @Test void databaseFailureReportsUnsuccessfulWrite() throws Exception {
        Connection con = mock(Connection.class);
        when(con.prepareStatement(anyString())).thenThrow(new SQLException("Unavailable"));
        assertFalse(manager.delete(con, new Budget()));
    }
}
