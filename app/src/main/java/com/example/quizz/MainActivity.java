package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView afficherMessage;
    EditText saisirNom;
    Button bouttonCommencer;
    ImageView delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        afficherMessage = findViewById(R.id.textViewMessage);
        saisirNom=findViewById(R.id.editTextSaisirNom);
        bouttonCommencer=findViewById(R.id.buttonCommencer);
        delete=findViewById(R.id.imageViewDelete);

        bouttonCommencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, QuestionnaireActivity.class);
                startActivity(intent);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saisirNom.setText("");
                changerCouleurBoutonDelete(255,255,255);
                changerCouleurBoutonCommencer(206,206,206);
                activerBoutonCommencer(false);
            }
        });

        saisirNom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                activerBoutonDelete(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!(saisirNom.getText()==null) && !(saisirNom.getText().toString().isEmpty()) ){
                    changerCouleurBoutonDelete(238,0,58);
                    activerBoutonDelete(true);
                    if ( saisirNom.getText().length()>=3){
                        changerCouleurBoutonCommencer(50,218,3);
                        activerBoutonCommencer(true);
                    }if ( saisirNom.getText().length()<3){
                        changerCouleurBoutonCommencer(206,206,206);
                        activerBoutonCommencer(false);
                    }
                }else {
                    changerCouleurBoutonDelete(255,255,255);
                    activerBoutonDelete(false);
                }

                if(saisirNom.getText().toString().matches("[^A-Za-z0-9]+")){
                    String text = saisirNom.getText().toString().substring(0,saisirNom.length()-1);
                    saisirNom.setText(text);
                    Toast.makeText(MainActivity.this, "Vous ne pouvez saisir que des chiffres ou des lettres pour le prénom", Toast.LENGTH_LONG).show();
                }
                if(saisirNom.getText().length() == 10){
                    Toast.makeText(MainActivity.this, "la taille du prénom doit être entre 3 et 10 inclus", Toast.LENGTH_SHORT).show();
                }if(saisirNom.getText().length()>10){
                    String text = saisirNom.getText().toString().substring(0,10);
                    saisirNom.setText(text);
                    saisirNom.setSelection(saisirNom.getText().length());
                }
            }






            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public  void activerBoutonDelete(boolean b){
        delete.setEnabled(b);
    }

    public void changerCouleurBoutonDelete(int a ,int b ,int c){
        delete.setBackgroundColor(Color.rgb(a,b,c));
    }

    public void changerCouleurBoutonCommencer(int a ,int b ,int c){
        bouttonCommencer.setBackgroundColor(Color.rgb(a,b,c));
    }

    public  void activerBoutonCommencer(boolean b){
        bouttonCommencer.setEnabled(b);
    }

}