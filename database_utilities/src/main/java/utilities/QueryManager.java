package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class QueryManager implements IQueryManager{
    private final IDataSourceManager dataSourceManager;

    public QueryManager(IDataSourceManager dataSourceManager) {
        this.dataSourceManager = dataSourceManager;
    }

    @Override
    public IResultSetManager executeQuery(final String sql) {
        IResultSetManager resultSetManager;
        try (Connection connection = dataSourceManager.getConnection()){
            try (Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery(sql);
                resultSetManager = new ResultSetManager(resultSet);
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSetManager;
    }

    @Override
    public IResultSetManager executeQuery(
            final String sql,
            final Object ...args
    ) {
        IResultSetManager resultSetManager;
        try (Connection connection = dataSourceManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (int i = 0; i < args.length; i++) {
                    statement.setObject(i + 1, args[i]);
                }
                ResultSet resultSet = statement.executeQuery();
                resultSetManager = new ResultSetManager(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSetManager;
    }
}
