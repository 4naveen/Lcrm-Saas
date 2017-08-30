package com.project.lorvent.way2freshers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.models.Category;

import java.util.ArrayList;

/**
 * Created by User on 2/15/2017.
 */

public class ListOfCategoryAdapter extends RecyclerView.Adapter<ListOfCategoryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Category> categoryArrayList;

    public ListOfCategoryAdapter(Context context, ArrayList<Category> categoryArrayList) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
    }

    @Override
    public ListOfCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_list_of_skills, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListOfCategoryAdapter.ViewHolder holder, int position) {
        holder.textView.setText(categoryArrayList.get(position).getCategory_name());
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.skills);
        }
    }
}
