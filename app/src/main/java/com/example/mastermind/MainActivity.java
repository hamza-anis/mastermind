package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button f1 = findViewById(R.id.f1);
        Button f2 = findViewById(R.id.f2);
        Button f3 = findViewById(R.id.f3);
        Button f4 = findViewById(R.id.f4);
        registerForContextMenu(f1);
        registerForContextMenu(f2);
        registerForContextMenu(f3);
        registerForContextMenu(f4);
    }
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Fraise");
        menu.add(0, v.getId(), 0, "Banane");
        menu.add(0, v.getId(), 0, "Kiwi");
        menu.add(0, v.getId(), 0, "Orange");
    }
}
