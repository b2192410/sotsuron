package world_database_example;

import utilities.ITableEntity;

public final class CityEntity implements ITableEntity {
    private final int id;
    private final String name;
    private final String countryCode;
    private final String district;
    private final int population;

    public CityEntity(
            final int id,
            final String name,
            final String countryCode,
            final String district,
            final int population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode=" + countryCode +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }
}
