package com.careerdevs.stockmarketapi.controllers;

import com.careerdevs.stockmarketapi.comparators.StockComparator;
import com.careerdevs.stockmarketapi.converters.DataConverter;
import com.careerdevs.stockmarketapi.models.CompAV;
import com.careerdevs.stockmarketapi.models.CompCSV;
import com.careerdevs.stockmarketapi.parsers.StockCSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api/stock/")
public class StockController {

    @Autowired
    Environment env;

    private String alphaURL(String symbol) {
        return "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&apikey=" + env.getProperty("alpha.key");
    }

    @GetMapping("/getalldata")
    public ArrayList<CompAV> getData(RestTemplate restTemplate) {

        ArrayList<CompCSV> csvData = StockCSVParser.readCSV();
        ArrayList<CompAV> allCompData = new ArrayList<>();

        assert csvData != null;
        for (CompCSV compData : csvData) {

            String URL = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + compData.getSymbol() + "&apikey=" + env.getProperty("alpha.key");

            CompAV compApiData = restTemplate.getForObject(URL, CompAV.class);
            allCompData.add(compApiData);
        }
        return allCompData;
    }


    @GetMapping("/feature1")
    public ArrayList<CompCSV> getNamesAlphaSymbol() {

        // get CSV data
        ArrayList<CompCSV> starterData = StockCSVParser.readCSV();
        // Method 1 to sort data
        assert starterData != null;
        starterData.sort(new StockComparator.SortCompCSVByName());
        // Method 2 to sort data
//        starterData.sort(Comparator.comparing(CompCSV::getName));

        //TODO: Remove/ only include name, symbol, and exchange

        ArrayList<CompCSV> sortedData = new ArrayList<>();
        for (CompCSV comp : starterData) {
            CompCSV tempComp = DataConverter.convertToFeature1(comp);
            sortedData.add(tempComp);
        }
        return sortedData;
    }


    @GetMapping("/feature2")
    public ArrayList<CompCSV> getNamesAlphaIPO() {

        // get CSV data
        ArrayList<CompCSV> starterData = StockCSVParser.readCSV();

        // sort data

        // Method 1 to sort data
        assert starterData != null;
        starterData.sort(new StockComparator.SortCompCSVByName());
        // Method 2 to sort data
        starterData.sort(Comparator.comparing(CompCSV::getIpoDate));

        //TODO: Remove/ only include name, symbol, and exchange

        ArrayList<CompCSV> sortedData = new ArrayList<>();
        for (CompCSV comp : starterData) {
            CompCSV tempComp = new CompCSV();
            tempComp.setName(comp.getName());
            tempComp.setIpoDate(comp.getIpoDate());
            sortedData.add(tempComp);
        }
        return sortedData;
    }


    @GetMapping("/feature3")
    public ArrayList<CompCSV> getNasdaQData() {

        // get CSV data
        ArrayList<CompCSV> starterData = StockCSVParser.readCSV();

        // sort data

        // Method 1 to sort data
        assert starterData != null;
        starterData.sort(new StockComparator.SortCompCSVByName());
        // Method 2 to sort data
        starterData.sort(Comparator.comparing(CompCSV::getName));

        ArrayList<CompCSV> nasdaqData = new ArrayList<>();
        for (CompCSV comp : starterData) {
            CompCSV tempComp = new CompCSV();
            tempComp.setName(comp.getName());
            tempComp.setSymbol(comp.getSymbol());
            tempComp.setExchange(comp.getExchange());

            if (tempComp.getExchange().equals("NASDAQ"))
                nasdaqData.add(tempComp);
        }
        return nasdaqData;
    }


    @GetMapping("/feature4")
    public ArrayList<CompCSV> getNyseData() {

        // get CSV data
        ArrayList<CompCSV> starterData = StockCSVParser.readCSV();

        // sort data

        // Method 1 to sort data
        assert starterData != null;
        starterData.sort(new StockComparator.SortCompCSVByName());
        // Method 2 to sort data
        starterData.sort(Comparator.comparing(CompCSV::getName));

        ArrayList<CompCSV> nyseData = new ArrayList<>();
        for (CompCSV comp : starterData) {
            CompCSV tempComp = new CompCSV();
            tempComp.setName(comp.getName());
            tempComp.setSymbol(comp.getSymbol());
            tempComp.setExchange(comp.getExchange());

            if (tempComp.getExchange().equals("NYSE"))
                nyseData.add(tempComp);
        }
        return nyseData;
    }


    @GetMapping("/feature5")
    public ArrayList<CompAV> getAllDataAPI(RestTemplate restTemplate) {

        ArrayList<CompCSV> csvData = StockCSVParser.readCSV();
        ArrayList<CompAV> allCompData = new ArrayList<>();

        assert csvData != null;
        for (CompCSV compData : csvData) {

            CompAV compApiData = restTemplate.getForObject(alphaURL(compData.getSymbol()), CompAV.class);

            assert compApiData != null;
            CompAV trimmedData = DataConverter.convertToFeature5(compApiData);
            allCompData.add(trimmedData);

        }
        return allCompData;
    }


    @GetMapping("/feature5v2")
    public ArrayList<CompAV> getAllDataAPIv2(RestTemplate restTemplate) {

        ArrayList<CompCSV> csvData = StockCSVParser.readCSV();
        ArrayList<CompAV> allCompData = new ArrayList<>();

        assert csvData != null;
        for (CompCSV compData : csvData) {

            CompAV compApiData = restTemplate.getForObject(alphaURL(compData.getSymbol()), CompAV.class);

            assert compApiData != null;
            CompAV trimmedData = compApiData.convertToFeature5v2();
            allCompData.add(trimmedData);

        }
        return allCompData;
    }


    @GetMapping("/feature6")
    public ArrayList<CompAV> getNamesAlphaMarketCap(RestTemplate restTemplate) {

        // get CSV data
        ArrayList<CompCSV> starterData = StockCSVParser.readCSV();

        // sort data

        // Method 1 to sort data
        assert starterData != null;

        ArrayList<CompAV> sortedData = new ArrayList<>();
        for (CompCSV comp : starterData) {

            CompAV tempComp = restTemplate.getForObject(alphaURL(comp.getSymbol()), CompAV.class);
            assert tempComp != null;
            CompAV trimmedData = DataConverter.convertToFeature5(tempComp);
            sortedData.add(trimmedData);
        }
        sortedData.sort(new StockComparator.SortCompAVMarketCap());
        Collections.reverse(sortedData); // sorted Av is sorting from lowest to highest, so the reverse method is reversing the order from highest to lowest
        return sortedData;
    }


    @GetMapping("/getnasdaq")
    public ArrayList<CompCSV> getNasdaq(RestTemplate restTemplate) {

        ArrayList<CompCSV> nasdaqData = StockCSVParser.readCSV();

        assert nasdaqData != null;
        for (CompCSV compData : nasdaqData) {

//            String URL = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + compData.getSymbol() + "&apikey=" + env.getProperty("alpha.key");
//            CompAV compApiData = restTemplate.getForObject(URL, CompAV.class);

            if (compData.getExchange().equals("NASDAQ")) {
                nasdaqData.add(compData);
            }
        }
        return nasdaqData;
    }


    @GetMapping("/getnyse")
    public ArrayList<CompAV> getNyse(RestTemplate restTemplate) {

        ArrayList<CompCSV> csvData = StockCSVParser.readCSV();
        ArrayList<CompAV> nyseData = new ArrayList<>();

        assert csvData != null;
        for (CompCSV compData : csvData) {

            String URL = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + compData.getSymbol() + "&apikey=" + env.getProperty("alpha.key");
            CompAV compApiData = restTemplate.getForObject(URL, CompAV.class);

            if (compData.getExchange().equals("NYSE")) {
                nyseData.add(compApiData);
            }
        }
        return nyseData;
    }

}
