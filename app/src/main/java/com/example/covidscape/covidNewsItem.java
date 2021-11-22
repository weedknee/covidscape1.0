package com.example.covidscape;

//java class to store Covid news item
public class covidNewsItem {
    private int imageResource;
    private String cases, dailyCases, totalNum, dailyNum;

    public covidNewsItem(int imageResource, String cases, String totalNum, String dailyCases, String dailyNum) {
        this.imageResource = imageResource;
        this.cases = cases;
        this.dailyCases = dailyCases;
        this.totalNum = totalNum;
        this.dailyNum = dailyNum;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDailyCases() {
        return dailyCases;
    }

    public void setDailyCases(String dailyCases) {
        this.dailyCases = dailyCases;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getDailyNum() {
        return dailyNum;
    }

    public void setDailyNum(String dailyNum) {
        this.dailyNum = dailyNum;
    }

}
