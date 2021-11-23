package com.example.covidscape;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// adapter for recyclerView
public class CovidNewsAdapter extends RecyclerView.Adapter<CovidNewsAdapter.CovidNewsViewHolder> {

    private ArrayList<CovidNewsItem> covidNewsItems;
    private Context context;

    public CovidNewsAdapter(Context context, ArrayList<CovidNewsItem> covidNewsItems) {
        this.context = context;
        this.covidNewsItems = covidNewsItems;
    }

    //create viewHolder for recyclerView
    @NonNull
    @Override
    public CovidNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new CovidNewsViewHolder(view);
    }

    //set value for each component of row item
    @Override
    public void onBindViewHolder(@NonNull CovidNewsViewHolder holder, int position) {
        CovidNewsViewHolder viewHolder = (CovidNewsViewHolder) holder;
        CovidNewsItem currentData = covidNewsItems.get(position);
        viewHolder.imageView.setImageResource(currentData.getImageResource());
        viewHolder.view1.setText(currentData.getCases());
        viewHolder.view2.setText(currentData.getTotalNum());
        viewHolder.view3.setText(currentData.getDailyCases());
        viewHolder.view4.setText(currentData.getDailyNum());
    }

    //get item row to display
    @Override
    public int getItemCount() {
        return covidNewsItems.size();
    }

    //get each component of item row
    public class CovidNewsViewHolder extends RecyclerView.ViewHolder {

        private TextView view1, view2, view3, view4;
        private ImageView imageView;

        public CovidNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            view1 = (TextView) itemView.findViewById(R.id.total_cases);
            view2 = itemView.findViewById(R.id.total_num);
            view3 = itemView.findViewById(R.id.daily_num);
            view4 = itemView.findViewById(R.id.total_daily_num);
            imageView = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
