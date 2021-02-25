package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    static RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Content> listContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.myListView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        setupRecyclerView();

        if(listContent.isEmpty()){
            Snackbar.make(recyclerView, "Tap + untuk insert", Snackbar.LENGTH_SHORT).show();
        }
    }

    public void setupRecyclerView(){
        DatabaseHelper db = new DatabaseHelper(this);
        listContent = db.selectContentList();
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(listContent);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public static void setupRecyclerView(Context context, List<Content> contentList, RecyclerView recyclerView){
        DatabaseHelper db = new DatabaseHelper(context);
        contentList = db.selectContentList();

        RecyclerviewAdapter adapter = new RecyclerviewAdapter(contentList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onClickAddItem(View view){
        Intent intent = new Intent(getApplicationContext(), AddItem.class);
        startActivity(intent);
    }
}