package com.careerdevs.stockmarketapi.converters;

import com.careerdevs.stockmarketapi.models.CompAV;
import com.careerdevs.stockmarketapi.models.CompCSV;

public class DataConverter {


    public static CompCSV convertToFeature1(CompCSV comp) {
        CompCSV tempComp = new CompCSV();

        tempComp.setName(comp.getName());
        tempComp.setSymbol(comp.getSymbol());
        tempComp.setExchange(comp.getExchange());

        return tempComp;
    }


    public static CompAV feature5(String symbol, String assetType, String name, String description, String address) {

        CompAV newComp = new CompAV();

        newComp.setSymbol(symbol);
        newComp.setAssetType(assetType);
        newComp.setName(name);
        newComp.setDescription(description);
        newComp.setAddress(address);

        return newComp;
    }

    public static CompAV convertToFeature5(CompAV comp) {

        CompAV newComp = new CompAV();

        newComp.setSymbol(comp.getSymbol());
        newComp.setAssetType(comp.getAssetType());
        newComp.setName(comp.getName());
        newComp.setDescription(comp.getDescription());
        newComp.setAddress(comp.getAddress());

        return newComp;
    }

    public static CompAV convertToFeature6(CompAV comp) {

        CompAV newComp = new CompAV();

        newComp.setSymbol(comp.getSymbol());
        newComp.setMarketCapitalization(comp.getMarketCapitalization());
        newComp.setName(comp.getName());

        return newComp;
    }

    public static CompAV convertToFeature7(CompAV comp) {

        CompAV newComp = new CompAV();

        newComp.setSymbol(comp.getSymbol());
        newComp.setDividendDate(comp.getDividendDate());
        newComp.setName(comp.getName());

        return newComp;
    }

}
