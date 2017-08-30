package com.project.lorvent.way2freshers.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lorvent.way2freshers.R;
import com.project.lorvent.way2freshers.activities.ListOfLocationJobActivity;
import com.project.lorvent.way2freshers.models.Location;

import java.util.ArrayList;

/**
 * Created by User on 2/15/2017.
 */

public class ListOfLocationAdapter extends RecyclerView.Adapter<ListOfLocationAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Location> locationArrayList;
     ViewGroup viewGroup;
    int count=0;
    public ListOfLocationAdapter(Context context, ArrayList<Location> locationArrayList) {
        this.context = context;
        this.locationArrayList = locationArrayList;
    }
    @Override
    public ListOfLocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_list_of_skills, parent, false);
        viewGroup=parent;

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListOfLocationAdapter.ViewHolder holder, final int position) {
       /* Location location=locationArrayList.get(position);
        if (location.getId().equals("0"))
        {
            Log.i("id-",location.getId());
            holder.textView.setTextColor(Color.rgb(158,158,158));
        }*/
        holder.textView.setText(locationArrayList.get(position).getLocation());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationArrayList.get(holder.getAdapterPosition()).getId().equalsIgnoreCase("0")) {
                    final Snackbar snackbar = Snackbar.make(viewGroup, "Sorry ! No jobs available", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    view.setMinimumWidth(1000);
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.YELLOW);
                    snackbar.show();
                } else {
                    Intent i = new Intent(context, ListOfLocationJobActivity.class);
                    i.putExtra("id", locationArrayList.get(holder.getAdapterPosition()).getId());
                    i.putExtra("location", locationArrayList.get(holder.getAdapterPosition()).getLocation());
                    context.startActivity(i);
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return locationArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.skills);
        }
        @Override
        public void onClick(View v) {

        }
    }
}
