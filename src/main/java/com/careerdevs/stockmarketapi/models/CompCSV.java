package com.careerdevs.stockmarketapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompCSV {

    private String symbol;
    private String name;
    private String exchange;
    private String assetType;
    private String ipoDate;
    private String delistingDate;
    private String status;
    private String dividendDate;
    private String marketCapitalization;

    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("Symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getExchange() {
        return exchange;
    }

    @JsonProperty("Exchange")
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getAssetType() {
        return assetType;
    }

    @JsonProperty("AssetType")
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getIpoDate() {
        return ipoDate;
    }

    @JsonProperty("IpoDate")
    public void setIpoDate(String ipoDate) {
        this.ipoDate = ipoDate;
    }

    public String getDelistingDate() {
        return delistingDate;
    }

    @JsonProperty("DelistingDate")
    public void setDelistingDate(String delistingDate) {
        this.delistingDate = delistingDate;
    }

    public String getStatus() {
        return status;
    }

    @JsonProperty("Status")
    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "{" + name + "::" + symbol + "::" + ipoDate + "::" + status + "}";
    }
}
