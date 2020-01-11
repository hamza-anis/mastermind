package com.example.mastermind;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mastermind.Jeu.Adapter;
import com.example.mastermind.Jeu.Fruit;
import com.example.mastermind.Jeu.Mastermind;
import com.example.mastermind.Jeu.Selection;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Mastermind mastermind = new Mastermind();
    private ArrayList<Selection> historique;
    private int essais = 0;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        historique = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView i1 = findViewById(R.id.f1_img);
        ImageView i2 = findViewById(R.id.f2_img);
        ImageView i3 = findViewById(R.id.f3_img);
        ImageView i4 = findViewById(R.id.f4_img);
        showNbEssais();
        registerForContextMenu(i1);
        registerForContextMenu(i2);
        registerForContextMenu(i3);
        registerForContextMenu(i4);

    }

    private void initRecycler(){
        RecyclerView recycl = findViewById(R.id.recycler);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Adapter(historique);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        for (String f : mastermind.getAllFruits().keySet()) {
            menu.add(0, v.getId(), 0, f);
        }
    }

    public boolean onContextItemSelected(MenuItem item) {
        ImageView selectImage = findViewById(item.getItemId());
        String name = item.getTitle().toString();
        int drawable = mastermind.getAllFruits().get(name).getDrawable();
        selectImage.setTag(name);
        selectImage.setImageResource(drawable);

        return true;
    }

    private void showNbEssais() {
        TextView t = findViewById(R.id.nb_essais);
        t.setText("Essais : " + this.essais);
    }

    public void valideSelectedCombinaison(View v) {

        ImageView i1 = findViewById(R.id.f1_img);
        ImageView i2 = findViewById(R.id.f2_img);
        ImageView i3 = findViewById(R.id.f3_img);
        ImageView i4 = findViewById(R.id.f4_img);
        try {
            Selection s = new Selection(
                    mastermind.getAFruit(i1.getTag().toString()),
                    mastermind.getAFruit(i2.getTag().toString()),
                    mastermind.getAFruit(i3.getTag().toString()),
                    mastermind.getAFruit(i4.getTag().toString())
                    );
            historique.add(s);
            if(essais == 0){
                initRecycler();
            }else{
                adapter.notifyDataSetChanged();
            }
            this.essais++;
            showNbEssais();
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(), "Merci de selectionner une combinaison complète!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void showCombinaison() {

    }

}
