package utilities;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ResultSetManager implements IResultSetManager {
    /**
     * ResultSetから取り出したResultSetMetaDataクラス.
     */
    private final ResultSetMetaData rsMetaData;

    /**
     * データの列の数.
     */
    private final int columnCount;

    /**
     * 元のResultSetクラスのnextメソッドが一度に読み込む行の数.
     */
    private final int fetchSize;

    /**
     * 元のResultSetクラスのフェッチ方向.
     */
    private final int fetchDirection;

    /**
     * 元のResultSetクラスの並列処理のモード.
     */
    private final int concurrency;

    /**
     * 元のResultSetクラスから取り出したデータ.
     */
    private final Object[][] rsData;

    ResultSetManager(final ResultSet rs) throws SQLException {
        this.rsMetaData = rs.getMetaData();
        this.columnCount = this.rsMetaData.getColumnCount();
        this.fetchSize = rs.getFetchSize();
        this.fetchDirection = rs.getFetchDirection();
        this.concurrency = rs.getConcurrency();
        this.rsData = ResultSetManager.setRSData(rs, columnCount);
    }

    private static Object[][] setRSData(
            final ResultSet rs,
            final int columnCount
    ) throws SQLException {
        List<Object[]> objects = new ArrayList<>();
        if (rs.isFirst()) {
            throw new SQLException("ResultSetのカーソルが1行目にありません");
        }
        while (rs.next()) {
            Object[] rsDataArray = new Object[columnCount];
            for (int j = 0; j < columnCount; j++) {
                Object o = rs.getObject(j + 1);
                rsDataArray[j] = o;
            }
            objects.add(rsDataArray);
        }
        Object[][] objects1 = new Object[objects.size()][columnCount];
        for (int i = 0; i < objects.size(); i++) {
            System.arraycopy(objects.get(i), 0, objects1[i], 0, columnCount);
        }
        return objects1;
    }

    @Override
    public Object getObject(
            final int rowIndex,
            final int columnIndex) {
        return this.rsData[rowIndex - 1][columnIndex - 1];
    }

    @Override
    public <T> T getObject(
            final int rowIndex,
            final int columnIndex,
            final Class<T> tClass) {
        T t;
        try {
            t = (T) getObject(rowIndex, columnIndex);
        } catch (ClassCastException e) {
            Object o = getObject(rowIndex, columnIndex);
            System.out.println("キャストがうまくいきませんでした。");
            System.out.println("元のクラス" + o.getClass());
            System.out.println("元のデータ" + o);
            System.out.println("キャスト先のクラス" + tClass);
            throw new ClassCastException();
        }
        return t;
    }

    @Override
    public ResultSetMetaData getMetaData() {
        return this.rsMetaData;
    }

    @Override
    public int getRowLength() {
        return this.rsData.length;
    }

    @Override
    public int getColumnLength() {
        return this.columnCount;
    }

    @Override
    public int getFetchSize() {
        return this.fetchSize;
    }

    @Override
    public int getFetchDirection() {
        return this.fetchDirection;
    }

    @Override
    public int getConcurrency() {
        return this.concurrency;
    }
}
