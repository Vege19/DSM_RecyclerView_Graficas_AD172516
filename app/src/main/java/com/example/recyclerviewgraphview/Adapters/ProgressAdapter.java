package com.example.recyclerviewgraphview.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerviewgraphview.DataBase.Progress;
import com.example.recyclerviewgraphview.R;

import java.util.List;

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ViewHolder> {

    private List<Progress> progresses;
    private Context context;

    public ProgressAdapter(List<Progress> progresses, Context context) {
        this.progresses = progresses;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_progress, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Progress progress = progresses.get(i);

        viewHolder.event.setText(progress.getProgress_event());
        viewHolder.percentage.setText(String.valueOf(progress.getProgress_percentage()) + "%");

    }

    @Override
    public int getItemCount() {
        return progresses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView event, percentage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            event = itemView.findViewById(R.id.progressEvent);
            percentage = itemView.findViewById(R.id.progressPercentage);

        }

    }

}
