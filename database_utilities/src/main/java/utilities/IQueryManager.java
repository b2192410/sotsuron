package utilities;

/**
 * 元のStatementクラスに存在するexecute関係のメソッドについて、IResultSetManagerを返すようにしたものを提供するクラス.
 */
public interface IQueryManager {
    /**
     * SQLを実行して結果をIResultSetManagerで返すメソッド.
     * @param sql 実行したいSQL文.
     * @return 結果を保存したIResultSetManager.
     */
    IResultSetManager executeQuery(String sql);

    /**
     * SQLを実行して結果をIResultSetManagerで返すメソッド.
     * @param sql 実行したいSQL文.
     * @param args プレースホルダのための変数.
     * @return 結果を保存したIResultSetManager.
     */
    IResultSetManager executeQuery(String sql, Object ...args);
}
