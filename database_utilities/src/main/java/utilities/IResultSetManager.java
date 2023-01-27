package utilities;

import java.sql.ResultSetMetaData;

/**
 * SQLの実行結果であるResultSetクラスのデータを保持しておくためのクラス.
 */
public interface IResultSetManager {
    /**
     * SQLの実行結果について行と列を指定して取得.
     *
     * @param rowIndex    行番号(1~).
     * @param columnIndex 列番号(1~).
     * @return 取り出したデータ
     */
    Object getObject(
            int rowIndex,
            int columnIndex
    );

    /**
     * SQLの実行結果についてあるクラスにキャストして取得.
     *
     * @param rowIndex    行番号(1~).
     * @param columnIndex 列番号(1~).
     * @param tClass      キャスト先のクラス.
     * @param <T>         　キャスト先のクラス.
     * @return 取り出したデータ.
     */
    <T> T getObject(
            int rowIndex,
            int columnIndex,
            Class<T> tClass
    );

    /**
     * ResultSetクラスのメタデータを取得する.
     *
     * @return ResultSetMetaData.
     */
    ResultSetMetaData getMetaData();

    /**
     * 取り出されたデータの行数を取得する.
     *
     * @return 行の数.
     */
    int getRowLength();

    /**
     * 取り出されたデータの列数を取得する.
     *
     * @return 列の数.
     */
    int getColumnLength();

    /**
     * 元のResultSetクラスのnextメソッドを利用したときに一度に読み込まれる行数を取得する.
     *
     * @return 元のResultSetクラスのnextメソッドを利用したときに一度に読み込まれる行数.
     */
    int getFetchSize();

    /**
     * 元のResultSetクラスのフェッチ方向を取得する.
     *
     * @return 元のResultSetクラスのフェッチ方向.
     */
    int getFetchDirection();

    /**
     * 元のResultSetクラスの並列処理のモードを取得する.
     *
     * @return 元のResultSetクラスの並列処理のモード.
     */
    int getConcurrency();
}
