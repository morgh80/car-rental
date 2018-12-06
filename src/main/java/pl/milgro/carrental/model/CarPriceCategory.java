package pl.milgro.carrental.model;

import lombok.Getter;

@Getter
public enum CarPriceCategory {
    MINI("MINI_CAR_RATE"),
    ECONOMY("ECONOMY_CAR_RATE"),
    COMPACT("COMPACT_CAR_RATE"),
    STANDARD("STANDARD_CAR_RATE"),
    PREMIUM("PREMIUM_CAR_RATE"),
    ELECTRIC("ELECTRIC_CAR_RATE");

    private String dbName;

    CarPriceCategory(String dbName) {
        this.dbName = dbName;
    }
}
