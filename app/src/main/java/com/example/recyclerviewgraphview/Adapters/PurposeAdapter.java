package com.example.recyclerviewgraphview.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerviewgraphview.DataBase.Purpose;
import com.example.recyclerviewgraphview.R;

import java.util.List;

public class PurposeAdapter extends RecyclerView.Adapter<PurposeAdapter.ViewHolder> {

    private List<Purpose> purposes;
    private Context context;

    public PurposeAdapter(List<Purpose> purposes, Context context) {
        this.purposes = purposes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_purpose, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Purpose purpose = purposes.get(i);

        viewHolder.mTitle.setText(purpose.getPurpose_title());

        viewHolder.mProgress.setText(String.valueOf(purpose.purpose_progress));

    }

    @Override
    public int getItemCount() {
        return purposes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle, mProgress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.purposeTitle);
            mProgress = itemView.findViewById(R.id.purposeProgress);

        }

    }

}
