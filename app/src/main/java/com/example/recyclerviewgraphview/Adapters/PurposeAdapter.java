package com.example.recyclerviewgraphview.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerviewgraphview.DataBase.Purpose;
import com.example.recyclerviewgraphview.MainActivity;
import com.example.recyclerviewgraphview.PurposeDetailsActivity;
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Purpose purpose = purposes.get(i);

        viewHolder.mTitle.setText(purpose.getPurpose_title());
        viewHolder.mDate.setText(purpose.getPurpose_date());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PurposeDetailsActivity.class);
                intent.putExtra("details", purpose);
                context.startActivity(intent);

            }
        });

        viewHolder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.dataBase.purposeDao().deletePurpose(purpose);
                MainActivity.recyclerViewSetup();
            }
        });

    }

    @Override
    public int getItemCount() {
        return purposes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle, mDate;
        private ImageView mDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.purposeTitle);
            mDate = itemView.findViewById(R.id.purposeDate);
            mDelete = itemView.findViewById(R.id.deleteItem);

        }

    }

}
