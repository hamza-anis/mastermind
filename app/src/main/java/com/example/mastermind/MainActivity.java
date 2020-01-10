package com.example.mastermind;

import android.content.Context;
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

import com.example.mastermind.Jeu.Fruit;
import com.example.mastermind.Jeu.FruitsAdapter;
import com.example.mastermind.Jeu.Mastermind;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Mastermind m = new Mastermind();
    private ArrayList<Fruit> selectedCombinaison;
    private int essais = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    private void initRecycle() {
        RecyclerView recycl = findViewById(R.id.recycler);
        FruitsAdapter adapter = new FruitsAdapter(this.selectedCombinaison);
        recycl.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycl.setAdapter(adapter);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        for (String f : m.getAllFruits().keySet()) {
            menu.add(0, v.getId(), 0, f);
        }
    }

    public boolean onContextItemSelected(MenuItem item) {
        ImageView selectImage = findViewById(item.getItemId());
        String name = item.getTitle().toString();
        selectImage.setTag(name);
        Context context = selectImage.getContext();
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        selectImage.setImageResource(id);

        return true;
    }

    private void showNbEssais() {
        TextView t = findViewById(R.id.nb_essais);
        t.setText("Essais : " + String.valueOf(this.essais));
    }

    public void valideSelectedCombinaison(View v) {

        this.selectedCombinaison = new ArrayList<>();
        ImageView i1 = findViewById(R.id.f1_img);
        ImageView i2 = findViewById(R.id.f2_img);
        ImageView i3 = findViewById(R.id.f3_img);
        ImageView i4 = findViewById(R.id.f4_img);
        try {
            this.selectedCombinaison.add(m.getAFruit(i1.getTag().toString()));
            this.selectedCombinaison.add(m.getAFruit(i2.getTag().toString()));
            this.selectedCombinaison.add(m.getAFruit(i3.getTag().toString()));
            this.selectedCombinaison.add(m.getAFruit(i4.getTag().toString()));
            showCombinaison();
            this.essais++;
            showNbEssais();
            initRecycle();
        } catch (NullPointerException e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Merci de selectionner une combinaison complète!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void showCombinaison() {
        for (Fruit f : this.selectedCombinaison) {
            System.out.println(f.getNom());
        }
    }



}
