package com.example.tejas.measureit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Project> projectList;
    private Context mCtx;

    public MyAdapter(Context mCtx, List<Project> projectList){
        this.mCtx = mCtx;
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //getting the product of the specified position
        Project product = projectList.get(position);

        //binding the data with the viewholder views
        holder.projectTitle.setText(product.getTitle());
        holder.projectDesc.setText(product.getDesc());
        holder.projectMeasurements.setText(String.valueOf(product.getMeasurements()));

        holder.projectImage.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView projectTitle, projectDesc, projectMeasurements;
        ImageView projectImage;
        public ViewHolder(View itemView) {
            super(itemView);
            projectTitle = itemView.findViewById(R.id.projectTitle);
            projectDesc = itemView.findViewById(R.id.projectDescription);
            projectMeasurements = itemView.findViewById(R.id.projectMeasurements);
            projectImage = itemView.findViewById(R.id.projectImage);
        }
    }
}
