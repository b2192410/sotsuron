package world_database_example;

import com.zaxxer.hikari.HikariConfig;
import utilities.AbstractDataSourceManager;
import utilities.IDataSourceManager;

public final class WorldManager extends AbstractDataSourceManager implements IDataSourceManager {
    private static final WorldManager WORLD_MANAGER = new WorldManager();

    private WorldManager() {
        super(new HikariConfig("example/src/main/resources/world.properties"));
    }

    public static IDataSourceManager getInstance() {
        return WORLD_MANAGER;
    }
}
