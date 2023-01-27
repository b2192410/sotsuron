package utilities;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * HikariDataSourceを管理するクラス.
 * シングルストンで実装すること.
 * また、クラス名は対象のデータベース名の跡にManagerとつなげる物とする.
 */
public interface IDataSourceManager {
    /**
     * HikariDataSourceからConnectionを取得するメソッド.
     *
     * @return HikariDataSource.getConnection()の戻り値.
     */
    Connection getConnection() throws SQLException;

    /**
     * HikariDataSourceをクローズするメソッド.
     */
    void close();

    /**
     * HikariDataSourceがクローズされているかを検証するメソッド.
     *
     * @return クローズされている場合にTrue.
     */
    boolean isClosed();
}
