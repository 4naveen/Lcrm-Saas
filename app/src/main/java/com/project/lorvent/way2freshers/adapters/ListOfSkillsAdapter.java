package com.project.lorvent.way2freshers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.activities.ListofJobsActivity;
import com.project.lorvent.way2freshers.models.Skills;

import java.util.ArrayList;

/**
 * Created by User on 2/15/2017.
 */

public class ListOfSkillsAdapter extends RecyclerView.Adapter<ListOfSkillsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Skills> skillArrayList;
      RecyclerView recyclerView;

    public ListOfSkillsAdapter(Context context, ArrayList<Skills> skillArrayList,RecyclerView recyclerView) {
        this.context = context;
        this.skillArrayList = skillArrayList;
        this.recyclerView = recyclerView;
    }

    @Override
    public ListOfSkillsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_list_of_skills, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListOfSkillsAdapter.ViewHolder holder, final int position) {
       holder.textView.setText(skillArrayList.get(position).getSkills());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ListofJobsActivity.class);
               i.putExtra("id",skillArrayList.get(holder.getAdapterPosition()).getId());
                i.putExtra("title",skillArrayList.get(holder.getAdapterPosition()).getSkills());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return skillArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.skills);
        }
    }
}
