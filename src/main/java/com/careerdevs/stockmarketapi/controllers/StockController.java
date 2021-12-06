package com.careerdevs.stockmarketapi.controllers;

import com.careerdevs.stockmarketapi.models.CompAV;
import com.careerdevs.stockmarketapi.models.CompCSV;
import com.careerdevs.stockmarketapi.parsers.StockCSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api/stock/")
public class StockController {

    private final String AA_URL = "https://www.alphavantage.co/query";

    @Autowired
    Environment env;

    @GetMapping("/getalldata")
    public ArrayList<CompAV> getData(RestTemplate restTemplate) {

        ArrayList<CompCSV> csvData = StockCSVParser.readCSV();
        ArrayList<CompAV> allCompData = new ArrayList<>();

        assert csvData != null;

        for (CompCSV compData : csvData) {

            String URL = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + compData.getSymbol() + "&apikey=" + env.getProperty("alpha.key");
            CompAV compApiData = restTemplate.getForObject(URL, CompAV.class);
//            allCompData.sort(Comparator.comparing(CompAV::getSymbol));
            allCompData.add(compApiData);
        }
        return allCompData;
    }


    @GetMapping("/feature1")
    public ArrayList<CompCSV> getNamesAlphaSymbol() {

        // get CSV data
        ArrayList<CompCSV> starterData = StockCSVParser.readCSV();

        // sort data

        // Method 1 to sort data
        assert starterData != null;
        starterData.sort(new SortCompCSVByName());
        // Method 2 to sort data
        starterData.sort(Comparator.comparing(CompCSV::getName));

        //TODO: Remove/ only include name, symbol, and exchange
//        for (CompCSV comp : starterData) {
//
//            comp.setIpoDate(null);
//            comp.setAssetType(null);
//            comp.setDelistingDate(null);
//            comp.setStatus(null);
//        }

        ArrayList<CompCSV> sortedData = new ArrayList<>();
        for (CompCSV comp : starterData) {
            CompCSV tempComp = new CompCSV();
            tempComp.setName(comp.getName());
            tempComp.setSymbol(comp.getSymbol());
            tempComp.setExchange(comp.getExchange());
            sortedData.add(tempComp);
        }

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
    public List<CompAV> getNyse(RestTemplate restTemplate) {

        List<CompCSV> csvData = StockCSVParser.readCSV();
        List<CompAV> nyseData = new ArrayList<>();

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


    // This method works
    @GetMapping("/overview")
    public CompAV getOverview(RestTemplate restTemplate, @RequestParam(name = "symbol") String symbol) {

        String URL = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&apikey=" + env.getProperty("alpha.key");

        return restTemplate.getForObject(URL, CompAV.class);
    }


    // This method is not working
//    @GetMapping("/getexchange")
//    public CompAV getOverview2(RestTemplate restTemplate, @RequestParam(name = "exchange") String exchange) {
//
//        String URL = "https://www.alphavantage.co/query?function=OVERVIEW" + "&exchange=" + exchange + env.getProperty("alpha.key");
//
//        return restTemplate.getForObject(URL, CompAV.class);
//    }

    public static class SortCompCSVByName implements Comparator<CompCSV> {

        public int compare(CompCSV a, CompCSV b) {
            return a.getName().compareTo(b.getName());
        }
    }

}
