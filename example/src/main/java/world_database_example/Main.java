package world_database_example;

import java.util.List;

public class Main {
    /**
     * SQLからデータを取り出すデモプログラム.
     * 対象のデータベースはMySQLのWorld.
     * このデータはMySQLのWebサイトで配布されている.
     * @param args 利用目的なし
     */
    public static void main(String[] args) {
        CityQueries cityQueries = new CityQueries();

        // 実行例
        System.out.println("---------プレースホルダなし---------");
        List<CityEntity> cityEntities = cityQueries.selectCities();
        for (CityEntity cityEntity : cityEntities) {
            System.out.println(cityEntity.toString());
        }

        // プレースホルダを用いた実行例
        System.out.println("\n\n ---------プレースホルダあり---------");
        int id = 1;
        CityEntity cityEntity = cityQueries.selectCity(id);
        System.out.println(cityEntity.toString());
    }
}
