package com.example.mastermind;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
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
    private int indices;
    private boolean win;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Fruit> secretCombinaison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.essais = 10;
        this.indices = 0;
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
        secretCombinaison = mastermind.getSecretcombinaison();
        printSecretCombinaison();

        this.win = false;
        historique = new ArrayList<>();

        Button b = findViewById(R.id.valide_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valideSelectedCombinaison(v);
            }
        });

        Button indicesButton = findViewById(R.id.indices_button);
        indicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerIndice();
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
        t.setText("Essais restants : " + (this.essais - 1));
    }

    public void valideSelectedCombinaison(View v) {
        try {
            if (this.essais > 0) {
                Selection s = new Selection(
                        mastermind.getAFruit(findViewById(R.id.f1_img).getTag().toString()),
                        mastermind.getAFruit(findViewById(R.id.f2_img).getTag().toString()),
                        mastermind.getAFruit(findViewById(R.id.f3_img).getTag().toString()),
                        mastermind.getAFruit(findViewById(R.id.f4_img).getTag().toString())
                );

                if (s.isSelectionValid()) {
                    historique.add(s);

                    handlerCombinaison(s);
                    showNbEssais();
                    if (this.essais == 10 | this.indices > 0) {
                        initRecycler();
                    } else {
                        adapter.notifyItemInserted(10 - this.essais);
                    }
                    this.essais--;
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Merci de selectionner des élements différents.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } else {
                handlerEndgame();
                System.out.println("Fin du jeu ");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void handlerCombinaison(Selection selection) {
        int right_placement_n_fruit = 0;
        ArrayList<Fruit> s = selection.getSelection();
        for (Fruit f : s) {
            if (secretCombinaison.contains(f)) {
                if (secretCombinaison.indexOf(f) == s.indexOf(f)) {
                    f.setState(R.drawable.true_pos);
                    right_placement_n_fruit++;
                } else {
                    f.setState(R.drawable.again);
                }
            } else {
                f.setState(R.drawable.false_pos);
            }
        }
        if (right_placement_n_fruit == 4) {
            this.win = true;
            handlerEndgame();
        }
    }

    private void handlerIndice() {
        System.out.println(this.indices);
        if ((this.indices == 0) && (this.essais > 2)) {
            this.essais -= 2;
            this.indices++;
            showNbEssais();
            TextView h1, h2, h3, h4;
            h1 = findViewById(R.id.h1);
            h2 = findViewById(R.id.h2);
            h3 = findViewById(R.id.h3);
            h4 = findViewById(R.id.h4);
            h1.setText(String.valueOf(this.secretCombinaison.get(0).isGotSeeds()));
            h2.setText(String.valueOf(this.secretCombinaison.get(1).isGotSeeds()));
            h3.setText(String.valueOf(this.secretCombinaison.get(2).isGotSeeds()));
            h4.setText(String.valueOf(this.secretCombinaison.get(3).isGotSeeds()));
        } else if ((this.indices == 1) && (this.essais > 3)) {
            this.indices++;
            this.essais -= 3;
            showNbEssais();
            TextView h1, h2, h3, h4;
            h1 = findViewById(R.id.h21);
            h2 = findViewById(R.id.h22);
            h3 = findViewById(R.id.h23);
            h4 = findViewById(R.id.h24);
            h1.setText(String.valueOf(this.secretCombinaison.get(0).isPeelable()));
            h2.setText(String.valueOf(this.secretCombinaison.get(1).isPeelable()));
            h3.setText(String.valueOf(this.secretCombinaison.get(2).isPeelable()));
            h4.setText(String.valueOf(this.secretCombinaison.get(3).isPeelable()));
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Plus d'indices disponibles.", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private void handlerEndgame() {
        AlertDialog.Builder e = new AlertDialog.Builder(this);
        e.setTitle("Fin du jeu ");
        if(this.win){
            e.setTitle("Bravo vous avez gagné. ");
            e.setMessage("Score : " + this.essais);
        }else{
            e.setMessage("Dommage, perdu .. ");
        }
        e.setPositiveButton("Rejouer",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recreate();
            }
        });
        e.show();
    }

    private void printSecretCombinaison() {
        /* pour les tests. */
        for (Fruit f : secretCombinaison) {
            System.out.println("Secret combinaison: " + f.getNom());
        }
    }

}
