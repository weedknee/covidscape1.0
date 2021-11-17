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

public class covidNewsAdapter extends RecyclerView.Adapter<covidNewsAdapter.covidNewsViewHolder> {

    private ArrayList<covidNewsItem> covidNewsItems;
    private Context context;

    public covidNewsAdapter(Context context, ArrayList<covidNewsItem> covidNewsItems) {
        this.context = context;
        this.covidNewsItems = covidNewsItems;
    }

    @NonNull
    @Override
    public covidNewsAdapter.covidNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new covidNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull covidNewsAdapter.covidNewsViewHolder holder, int position) {
        covidNewsAdapter.covidNewsViewHolder viewHolder = (covidNewsAdapter.covidNewsViewHolder) holder;
        covidNewsItem currentData = covidNewsItems.get(position);
        viewHolder.imageView.setImageResource(currentData.getImageResource());
        viewHolder.view1.setText(currentData.getCases());
        viewHolder.view2.setText(currentData.getTotalNum());
        viewHolder.view3.setText(currentData.getDailyCases());
        viewHolder.view4.setText(currentData.getDailyNum());
    }

    @Override
    public int getItemCount() {
        return covidNewsItems.size();

    }

    public class covidNewsViewHolder extends RecyclerView.ViewHolder {

        private TextView view1, view2, view3, view4;
        private ImageView imageView;

        public covidNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            view1 = (TextView) itemView.findViewById(R.id.total_cases);
            view2 = itemView.findViewById(R.id.total_num);
            view3 = itemView.findViewById(R.id.daily_num);
            view4 = itemView.findViewById(R.id.total_daily_num);
            imageView = (ImageView) itemView.findViewById(R.id.icon);
        }

    }

}
