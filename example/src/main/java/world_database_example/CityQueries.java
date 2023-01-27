package world_database_example;

import utilities.AutoMapper;
import utilities.IQueryManager;
import utilities.IResultSetManager;
import utilities.QueryManager;
import java.util.List;

public final class CityQueries {
    private final IQueryManager queryManager = new QueryManager(WorldManager.getInstance());

    public List<CityEntity> selectCities() {
        String sql = "SELECT * FROM city";
        IResultSetManager resultSetManager = queryManager.executeQuery(sql);
        return new AutoMapper<>(CityEntity.class).mappingToList(resultSetManager);
    }

    public CityEntity selectCity(int id) {
        String sql = "SELECT * FROM city WHERE ID = ?";
        IResultSetManager resultSetManager = queryManager.executeQuery(sql, id);
        return new AutoMapper<>(CityEntity.class).mapping(resultSetManager, 1);
    }
}
