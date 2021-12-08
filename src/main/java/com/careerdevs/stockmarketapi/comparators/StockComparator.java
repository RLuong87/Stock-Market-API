package com.careerdevs.stockmarketapi.comparators;

import com.careerdevs.stockmarketapi.models.CompAV;
import com.careerdevs.stockmarketapi.models.CompCSV;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class StockComparator {

    public static class SortCompCSVByName implements Comparator<CompCSV> {

        public int compare(CompCSV a, CompCSV b) {
            return a.getName().compareTo(b.getName());
        }
    }

    public static class SortCompAVMarketCap implements Comparator<CompAV> {

        public int compare(CompAV a, CompAV b) {
            long cap1 = Long.parseLong(a.getMarketCapitalization());
            long cap2 = Long.parseLong(b.getMarketCapitalization());
            return Long.compare(cap1, cap2);
        }
    }

    public static class SortByDivDate implements Comparator<CompAV>{
        public int compare (CompAV a, CompAV b){
            try {
                Date comp1date = new SimpleDateFormat("yyyy-MM-dd").parse(a.getDividendDate());
                Date comp2date = new SimpleDateFormat("yyyy-MM-dd").parse(b.getDividendDate());

                Date today = new Date();

                //Calculate days until comp1 divdate

                int daysbetweenComp1 =  Math.abs((int) ((comp1date.getTime() - today.getTime())/1000/60/60/ 24)); //converts to milliseconds, finds difference between dates, then converts back to days
                int daysbetweenComp2 = Math.abs((int) ((comp2date.getTime() - today.getTime())/1000/60/60/ 24)); //converts to milliseconds, finds difference between dates, then converts back to days

                if (comp1date.before(today)){ //if comp1 is before today, return true
                    daysbetweenComp1 = 365 - daysbetweenComp1;
                }
                if (comp2date.before(today)){
                    daysbetweenComp2 = 365 - daysbetweenComp2;
                }

                return Integer.compare(daysbetweenComp1, daysbetweenComp2);

            } catch (ParseException e) {
                return -1;
            } catch (Exception exception){
                exception.getMessage();
                System.out.println(exception.getMessage());
                return -1;
            }
        }


    }
}
