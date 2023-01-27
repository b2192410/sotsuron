package utilities;

/**
 * IResultSetManagerを、
 * ITableEntityを実装したクラスにマッピングするためのクラスのインタフェース.
 */
public interface IResultSetMapper {
    /**
     * SQLの実行結果を結果を保存するためのクラスにマッピングする.
     * @param resultSet SQLの実行結果.
     * @param rowIndex マッピングを行う行番号.
     * @return ITableEntityを実装したクラス.
     */
    ITableEntity mapping(
            IResultSetManager resultSet,
            int rowIndex
    );
}
