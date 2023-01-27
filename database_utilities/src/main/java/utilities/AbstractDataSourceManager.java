package utilities;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 開発者がIDataSourceManagerの実装を行う際の補助をするクラス.
 */
public abstract class AbstractDataSourceManager implements IDataSourceManager {
    /**
     * 接続対象のデータソース.
     */
    private final HikariDataSource dataSource;

    /**
     * HikariConfigを受け取って生成する.
     * @param config 接続したいデータベースのためのHikariConfig.
     */
    protected AbstractDataSourceManager(final HikariConfig config) {
        dataSource = new HikariDataSource(config);
    }

    /**
     * * シングルストンで実装するため、このクラスの利用時にインスタンスを取得するメソッド.
     * @return IDataSourceManagerを実装したクラス.
     */
    public abstract IDataSourceManager getInstance();

    /**
     * HikariDataSourceを取得する.
     * @return HikariDataSourceクラス.
     */
    public final HikariDataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public final Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public final void close() {
        if (isClosed()) {
            return;
        }
        dataSource.close();
    }

    @Override
    public final boolean isClosed() {
        return dataSource.isClosed();
    }
}
