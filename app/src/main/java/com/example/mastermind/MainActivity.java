package com.example.mastermind;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    private int essais;
    private boolean endgame;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Fruit> secretCombinaison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.essais = 1;
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
        secretCombinaison  = mastermind.getSecretcombinaison();
        printSecretCombinaison();

        endgame = false;
        historique = new ArrayList<>();

        Button b = findViewById(R.id.valide_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valideSelectedCombinaison(v);
            }
        });

    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.adapter = new Adapter(historique);
        recyclerView.setAdapter(this.adapter);
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


        try {
            Selection s = new Selection(
                    mastermind.getAFruit(findViewById(R.id.f1_img).getTag().toString()),
                    mastermind.getAFruit(findViewById(R.id.f2_img).getTag().toString()),
                    mastermind.getAFruit(findViewById(R.id.f3_img).getTag().toString()),
                    mastermind.getAFruit(findViewById(R.id.f4_img).getTag().toString())
            );
            historique.add(s);
            if (endgame){
                System.out.println("Gagné.");
            }

            handlerCombinaison(s);
            showNbEssais();
            if (this.essais == 0) {
                initRecycler();
            } else {
                adapter.notifyItemInserted(this.essais);
            }
            this.essais++;
            System.out.println( "Essais : " + this.essais);
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(), "Merci de selectionner une combinaison complète!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void handlerCombinaison(Selection selection) {
        int good = 0;
        ArrayList<Fruit> s = selection.getSelection();
        for (Fruit f : s) {
            if (secretCombinaison.contains(f)) {
                if (secretCombinaison.indexOf(f) == s.indexOf(f)){
                    System.out.println(secretCombinaison.indexOf(f) +"   "+ selection.getSelection().indexOf(f));
                    f.setState(R.drawable.true_pos);
                    good++;
                } else {
                    f.setState(R.drawable.again);
                }
            } else {
                f.setState(R.drawable.false_pos);
            }
        }
        if (good == 4) {
            endgame = true;
        }
    }

    public void printSecretCombinaison() {
        for (Fruit f : secretCombinaison) {
            System.out.println("Secret combinaison: " + f.getNom());
        }
    }
}
