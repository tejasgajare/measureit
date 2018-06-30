package com.example.tejas.measureit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ProjectListActivity extends AppCompatActivity {


    private static final String TAG = "ProjectListActivity";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    List<Project> projectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Projects");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        projectList = new ArrayList<>();


        for(int i=1; i<=8; i++){
            Project temp = new Project(
                    i,
                    "Project : "+i,
                    "Address : Hinjewadi",
                    10+" Measurements",
                    R.drawable.property);

            projectList.add(temp);
        }

        mAdapter = new MyAdapter(this, projectList);
        recyclerView.setAdapter(mAdapter);
    }
}
