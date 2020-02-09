package com.svtechcorp.schoolapp;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerMyAdapter extends RecyclerView.Adapter<recyclerMyAdapter.ViewHolder> {
    private List<recyclerListItem> listItems;
    private Context context;

    public recyclerMyAdapter(List<recyclerListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        recyclerListItem listItem = listItems.get(position);

        holder.txtView1.setText(listItem.getHead());
        holder.txtView2.setText(listItem.getDesc());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtView1;
        public  TextView txtView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView1 = (TextView) itemView.findViewById(R.id.txtView1);
            txtView2 = (TextView) itemView.findViewById(R.id.txtView2);
        }
    }
}
