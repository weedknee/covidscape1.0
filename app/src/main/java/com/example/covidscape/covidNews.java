package com.example.covidscape;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class covidNews extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String date_source;
    private String[] cases, dailyCases;
    private ArrayList<String> totalNum = new ArrayList<>(), dailNum = new ArrayList<>();
    private ArrayList<covidNewsItem> CovidNewsItems = new ArrayList<>();
    private covidNewsAdapter CovidNewsAdapter;
    private ProgressBar progressBar;
    private LinearLayoutManager linearLayoutManager;
    private Document doc;
    private Elements localCases, recovered, vaccinated, dCases, dDate;
    private TextView dateView;
    private CovidNewsItem covidNewsItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycler);
        dateView = findViewById(R.id.cases_date);

        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(covidNews.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        CovidNewsAdapter = new covidNewsAdapter(covidNews.this, CovidNewsItems);
        recyclerView.setAdapter(CovidNewsAdapter);

        covidNewsItem = new CovidNewsItem();
        covidNewsItem.execute();
    }

    private class CovidNewsItem extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(covidNews.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            dateView.setText(date_source);


            cases = getResources().getStringArray(R.array.cases);
            dailyCases = getResources().getStringArray(R.array.dailyCases);
            CovidNewsItems.add(new covidNewsItem(R.drawable.ic_disease, cases[0], totalNum.get(0), dailyCases[0], dailNum.get(0)));
            CovidNewsItems.add(new covidNewsItem(R.drawable.ic_deaths, cases[1], totalNum.get(1), dailyCases[1], dailNum.get(1)));
            CovidNewsItems.add(new covidNewsItem(R.drawable.ic_recovered, cases[2], totalNum.get(2), dailyCases[2], dailNum.get(2)));
            CovidNewsItems.add(new covidNewsItem(R.drawable.ic_vaccine, cases[3], totalNum.get(3), dailyCases[3], dailNum.get(3)));
            CovidNewsItems.add(new covidNewsItem(R.drawable.ic_disease, cases[4], totalNum.get(4), dailyCases[4], dailNum.get(4)));

            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(covidNews.this, android.R.anim.fade_out));

            CovidNewsAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                doc = Jsoup.connect("https://covidnow.moh.gov.my/").get();

                //retrieve element tag content based on entered class name
                localCases = doc.getElementsByClass("bg-blue-100 px-2 m-auto");
                recovered = doc.getElementsByClass("number flex justify-center gap-1.5");
                vaccinated = doc.getElementsByClass("relative self-end");
                dCases = doc.getElementsByClass("font-bold text-xl lg:text-2xl mr-auto");
                dDate = doc.getElementsByClass("col-span-1 text-xs text-gray-500 text-right tracking-tighter leading-3");

                date_source = dDate.get(0).text().trim();

                ArrayList<String> temp = new ArrayList<>();
                temp.add(dCases.get(5).text());
                temp.add(dCases.get(4).text());
                temp.addAll(Arrays.asList(recovered.get(3).text().replace(
                        "Deaths due to COVID - this differs from deaths with COVID (positive at time of death) but with non-COVID causes of death ", "")
                        .split(" ", 2)));
                temp.addAll(Arrays.asList(recovered.get(2).text().split(" ", 2)));
                temp.add(vaccinated.get(1).text());
                temp.add(vaccinated.get(0).text());
                temp.addAll(Arrays.asList(localCases.get(0).text().split(" ", 2)));
                for (int i = 0; i < 10; i += 2) {
                    totalNum.add(temp.get(i).toString());
                    dailNum.add(temp.get(i + 1).toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}