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
import com.project.lorvent.way2freshers.activities.InterviewQuesActivity;
import com.project.lorvent.way2freshers.models.ItInterview;

import java.util.ArrayList;

/**
 * Created by User on 2/15/2017.
 */

public class ItInterviewAdapter extends RecyclerView.Adapter<ItInterviewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ItInterview> interviewArrayList;
    private String category_id;
    private ViewGroup viewGroup;

    public ItInterviewAdapter(Context context, ArrayList<ItInterview> interviewArrayList) {
        this.context = context;
        this.interviewArrayList = interviewArrayList;
    }

    @Override
    public ItInterviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_list_of_skills, parent, false);
        viewGroup=parent;
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItInterviewAdapter.ViewHolder holder, int position) {
        ItInterview interview=interviewArrayList.get(position);
        if (interview.getCategory_id().equalsIgnoreCase("0"))
        {
            holder.textView.setTextColor(Color.rgb(158,158,158));
        }
       holder.textView.setText(interview.getSub_name());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(holder.itemView.getWindowToken(), 0);
                ItInterview interview= interviewArrayList.get(holder.getAdapterPosition());
                category_id =interview.getCategory_id();
                if (category_id.equalsIgnoreCase("0") ) {
                    final Snackbar snackbar = Snackbar.make(viewGroup, "Sorry ! No  paper available", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    view.setMinimumWidth(1000);
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.YELLOW);
                    snackbar.show();
                } else {
                    Intent i = new Intent(context, InterviewQuesActivity.class);
                    i.putExtra("id", category_id);
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return interviewArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.skills);
        }
    }

}
