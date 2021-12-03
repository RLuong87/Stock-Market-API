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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stock/")
public class StockController {

    private final String AA_URL = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=IBM&apikey=demo";

    @Autowired
    private Environment env;

    @GetMapping("/getalldata")
    public List<CompAV> getData(RestTemplate restTemplate) {

        List<CompCSV> csvData = StockCSVParser.readCSV();
        List<CompAV> allCompData = new ArrayList<>();

        for (CompCSV compData : csvData) {

            String URL = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + compData.getSymbol() + "&apikey=" + env.getProperty("alpha.key");
            CompAV compApiData = restTemplate.getForObject(URL, CompAV.class);
            allCompData.add(compApiData);
        }
        return allCompData;
    }


    @GetMapping("/overview")
    public CompAV getOverview(RestTemplate restTemplate,
                              @RequestParam(name = "function") String function,
                              @RequestParam(name = "symbol") String symbol) {

        String URL = "https://www.alphavantage.co/query?function=" + function + "&symbol=" + symbol + "&apikey=" + env.getProperty("alpha.key");

        return restTemplate.getForObject(URL, CompAV.class);
    }

    @GetMapping("/overviewv2")
    public CompAV getOverview2(RestTemplate restTemplate,
                               @RequestParam(name = "function") String function,
                               @RequestParam(name = "symbol") String symbol) {

        String URL = "https://www.alphavantage.co/query" + env.getProperty("alpha.key");

        return restTemplate.getForObject(URL, CompAV.class);
    }
}
