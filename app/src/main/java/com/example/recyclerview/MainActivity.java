package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<KisiModel> kisiModels;
    Adapter adp;
    FloatingActionButton fabBtn;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defination();
        if (kisiModels == null) {
            createList();
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            recyclerView.setAdapter(adp);
        }

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(getApplicationContext(), AddScreen.class);
                startActivityForResult(intentAdd, 2);
            }
        });




    }


    protected void onActivityResult(int i, int j, Intent data) {

        super.onActivityResult(i, j, data);
        if (Utility.requestCode_editScreen == i) {
            if (j == Activity.RESULT_OK) {

                kisiModels.remove(data.getIntExtra(Utility.position, 0));
                recyclerView.setAdapter(adp);


            }
        } else if (Utility.requestCode_addScreen == i) {
            if (j == Activity.RESULT_OK) {
                kisiModels.add(new KisiModel(data.getStringExtra(Utility.fullName), data.getIntExtra(Utility.gender, 0), data.getIntExtra(Utility.workingStatus, 0)));
                recyclerView.setAdapter(adp);
            }
        }
    }

    public void defination() {

        fabBtn = findViewById(R.id.activity_main_add_floatActionButton);
        recyclerView = findViewById(R.id.activity_main_person_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void createList() {

        kisiModels = new ArrayList<>();
        KisiModel k1 = new KisiModel("Alperen Sekban", R.drawable.man, R.drawable.ic_action_calismiyor);
        KisiModel k2 = new KisiModel("Derya Egeli", R.drawable.woman, R.drawable.ic_action_calisiyor);
        KisiModel k3 = new KisiModel("Recep Arslan", R.drawable.man, R.drawable.ic_action_calisiyor);
        KisiModel k4 = new KisiModel("Canan Kocadağ", R.drawable.woman, R.drawable.ic_action_calismiyor);
        KisiModel k5 = new KisiModel("Ahmet Çırak", R.drawable.man, R.drawable.ic_action_calismiyor);
        kisiModels.add(k1);
        kisiModels.add(k2);
        kisiModels.add(k3);
        kisiModels.add(k4);
        kisiModels.add(k5);
        adp = new Adapter(this, kisiModels);
        recyclerView.setAdapter(adp);

    }


}