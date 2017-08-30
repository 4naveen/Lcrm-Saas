package com.project.lorvent.way2freshers.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.activities.QuesPaperActivity;
import com.project.lorvent.way2freshers.models.PlacementPaper;

import java.util.ArrayList;


public class PlacementPaperAdapter extends RecyclerView.Adapter<PlacementPaperAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PlacementPaper> placementPaperArrayList;
    View v;
    private int no_of_papers;
    private ViewGroup viewGroup;

    public PlacementPaperAdapter(Context context, ArrayList<PlacementPaper> placementPaperArrayList) {
        this.context = context;
        this.placementPaperArrayList = placementPaperArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_placement_paper, parent, false);
        viewGroup=parent;
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PlacementPaperAdapter.ViewHolder viewHolder, int position) {
        PlacementPaper placementPaper= placementPaperArrayList.get(position);
        viewHolder.company_name.setText(placementPaper.getCompany_name());
        viewHolder.no_of_ques.setText(placementPaper.getNo_of_ques());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(viewHolder.itemView.getWindowToken(), 0);
                PlacementPaper paper = placementPaperArrayList.get(viewHolder.getAdapterPosition());
                no_of_papers = Integer.parseInt(paper.getNo_of_ques());
                if (no_of_papers != 0) {
                    Intent i = new Intent(context, QuesPaperActivity.class);
                    i.putExtra("url", paper.getPaper_name_url());
                    context.startActivity(i);
                } else {
                    final Snackbar snackbar = Snackbar.make(viewGroup, "Sorry ! No paper available", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    view.setMinimumWidth(1000);
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.YELLOW);
                    snackbar.show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return placementPaperArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView company_name,no_of_ques;

        ViewHolder(View itemView) {
            super(itemView);
            company_name = (TextView) itemView.findViewById(R.id.company_name);
            no_of_ques = (TextView) itemView.findViewById(R.id.no_of_ques);

        }
    }
}
