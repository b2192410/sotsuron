package utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 自動でIResultSetManagerの結果についてマッピングを試みるクラス.
 *
 * @param <T> マッピング対象のクラス.
 */
public final class AutoMapper<T extends ITableEntity>
        implements IResultSetMapper {
    /**
     * マッピング対象のクラス.
     */
    private final Class<T> tClass;

    /**
     * マッピング対象のクラスを指定してインスタンス化する.
     * @param tClass マッピング対象のクラス.
     */
    AutoMapper(final Class<T> tClass) {
        this.tClass = tClass;
    }

    /**
     * このクラスをインスタンス化した際に取得したtClassからITableEntityへのマッピングを自動で行うメソッド.
     *
     * @param resultSet SQLの実行結果.
     * @return ITableEntityクラス.
     */
    @Override
    public ITableEntity mapping(
            final IResultSetManager resultSet,
            final int rowIndex
    ) {
        if (resultSet.getRowLength() == 0 || resultSet.getColumnLength() == 0) {
            throw new NullPointerException("ResultSetにデータがありません");
        }

        final Field[] fields = tClass.getDeclaredFields();
        final int fieldLength = fields.length;
        final Class<?>[] argsClasses = new Class[fieldLength];
        final Object[] rsDataArray = new Object[fieldLength];

        for (int i = 0; i < fieldLength; i++) {
            argsClasses[i] = fields[i].getType();
            rsDataArray[i] = resultSet.getObject(rowIndex, i, tClass);
        }

        Constructor<T> entityConstructor;
        try {
            entityConstructor = tClass.getConstructor(argsClasses);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        T iEntity;
        try {
            iEntity = entityConstructor.newInstance(rsDataArray);
        } catch (InvocationTargetException
                 | InstantiationException
                 | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return iEntity;
    }
}
