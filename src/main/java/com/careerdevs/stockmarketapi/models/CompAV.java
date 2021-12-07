package com.careerdevs.stockmarketapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompAV {

    private String symbol;
    private String assetType;
    private String name;
    private String description;
    private String address;
    private String exchange;
    private String dividendDate;
    private String marketCapitalization;

    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("Symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAssetType() {
        return assetType;
    }

    @JsonProperty("AssetType")
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    @JsonProperty("Address")
    public void setAddress(String address) {
        this.address = address;
    }

    public String getExchange() {
        return exchange;
    }

    @JsonProperty("Exchange")
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getDividendDate() {
        return dividendDate;
    }

    @JsonProperty("DividendDate")
    public void setDividendDate(String dividendDate) {
        this.dividendDate = dividendDate;
    }

    public String getMarketCapitalization() {
        return marketCapitalization;
    }

    @JsonProperty("MarketCapitalization")
    public void setMarketCapitalization(String marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    @Autowired
    public CompAV convertToFeature5v2() {

        setMarketCapitalization(null);
        setDividendDate(null);
        setExchange(null);

        return this;
    }

}
