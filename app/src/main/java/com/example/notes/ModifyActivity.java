package com.example.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class ModifyActivity extends AppCompatActivity {

    Button update;
    Content content;
    Bundle intentData;
    TextInputLayout modTitle, modDesk;
    private String title;
    private String desc;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_modify);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        modTitle = findViewById(R.id.txtModinput);
        modDesk = findViewById(R.id.txtModinput2);

        intentData = getIntent().getExtras();
        modTitle.getEditText().setText(intentData.getString("judul"));
        modDesk.getEditText().setText(intentData.getString("deskripsi"));

        update = findViewById(R.id.update);
        update.setOnClickListener(v -> {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            content = new Content();
            title = modTitle.getEditText().getText().toString();
            desc = modDesk.getEditText().getText().toString();

            if(title.isEmpty() && desc.isEmpty()){
                Toast.makeText(this, "Data tidak boleh kosong!", Toast.LENGTH_LONG).show();
            }else {
                content.setId(intentData.getInt("id"));
                content.setTitle(title);
                content.setDesc(desc);

                dbHelper.update(content);

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                HomeActivity home = new HomeActivity();
                home.setupRecyclerView();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        int ID = item.getItemId();

        if(ID == R.id.delete_action){
            dbHelper.delete(intentData.getInt("id"));

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);

            Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_LONG).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
