package world_database_example;

import utilities.IResultSetManager;
import utilities.IResultSetMapper;
import utilities.ITableEntity;

public class CityMapper implements IResultSetMapper {
    @Override
    public ITableEntity mapping(IResultSetManager resultSet, int rowIndex) {
        return new CityEntity(
                resultSet.getObject(rowIndex, 1, int.class),
                resultSet.getObject(rowIndex, 2, String.class),
                resultSet.getObject(rowIndex, 3, String.class),
                resultSet.getObject(rowIndex, 4, String.class),
                resultSet.getObject(rowIndex, 5, int.class)
        );
    }
}
