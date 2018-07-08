package com.example.tejas.measureit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static final String TAG = "MyAdapter";
    private MyAdapter myAdapter;
    private List<Project> projectList;
    private Context mCtx;

    DatabaseHelper myDB;


    public MyAdapter(Context mCtx, List<Project> projectList){
        this.mCtx = mCtx;
        this.projectList = projectList;
        this.myDB = new DatabaseHelper(mCtx);
        this.myAdapter = this;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //getting the project of the specified position
        final Project project = projectList.get(position);

        //binding the data with the viewholder views
        holder.projectTitle.setText(project.getTitle());
        holder.projectDesc.setText(project.getDesc());
        holder.projectMeasurements.setText(String.valueOf(project.getMeasurements()));

        holder.projectImage.setImageDrawable(mCtx.getResources().getDrawable(project.getImage()));

        holder.projectDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Close Clicked");

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(mCtx, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(mCtx);
                }
                builder.setTitle("Remove Project")
                        .setMessage("Are you sure you want to remove this project?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String title = project.getTitle();
                                myDB.deleteRow(title);
                                boolean isRemoved = myDB.deleteRow(title);
                                if(isRemoved){
                                    for (Iterator<Project> iter = projectList.listIterator(); iter.hasNext(); ) {
                                        Project temp = iter.next();
                                        if (temp.getTitle() == title) {
                                            iter.remove();
                                        }
                                    }
                                    myAdapter.notifyDataSetChanged();
                                    Toast.makeText(mCtx,
                                            "Project Removed",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(mCtx,
                                            "Failed",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Card Clicked");
                Intent intent = new Intent(v.getContext(),ViewMeasurementsActivity.class);
                intent.putExtra("Project", project);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView projectTitle, projectDesc, projectMeasurements, projectDelete;
        ImageView projectImage;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            projectTitle = itemView.findViewById(R.id.projectTitle);
            projectDesc = itemView.findViewById(R.id.projectDescription);
            projectMeasurements = itemView.findViewById(R.id.projectMeasurements);
            projectImage = itemView.findViewById(R.id.projectImage);
            projectDelete = itemView.findViewById(R.id.projectDelete);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
