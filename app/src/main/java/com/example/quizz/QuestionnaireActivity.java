package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionnaireActivity extends AppCompatActivity {
    int score=0;
    int nbaleatoire =0;
    int indiceBonneReponse =1;
    int indiceMauvaiseReponse1 =0;
    int indiceMauvaiseReponse2 =1;
    int indiceMauvaiseReponse3 =2;
    int nombreTotaleQuestion= 0;
    Button reponse1,reponse2,reponse3,reponse4,retourAccueil;
    TextView numeroQuestion,contenuQuestion,affichageScore;
    String j ="0";
    int time = 2500;

    BonneReponse br1=new BonneReponse("2");
    BonneReponse br2=new BonneReponse("Paris");
    BonneReponse br3=new BonneReponse("La Lune");
    BonneReponse br4=new BonneReponse("Un footballer");

    MauvaiseReponse mr1= new MauvaiseReponse("1");
    MauvaiseReponse mr2= new MauvaiseReponse("3");
    MauvaiseReponse mr3= new MauvaiseReponse("4");
    MauvaiseReponse mr4= new MauvaiseReponse("Madrid");
    MauvaiseReponse mr5= new MauvaiseReponse("Tunis");
    MauvaiseReponse mr6= new MauvaiseReponse("Rome");
    MauvaiseReponse mr7= new MauvaiseReponse("Jupiter");
    MauvaiseReponse mr8= new MauvaiseReponse("Saturne");
    MauvaiseReponse mr9= new MauvaiseReponse("Uranus");
    MauvaiseReponse mr10= new MauvaiseReponse("Un tennisman");
    MauvaiseReponse mr11= new MauvaiseReponse("Un pilote");
    MauvaiseReponse mr12= new MauvaiseReponse("Un chanteur");

    Question q1=new Question("Combien font 1+1 ?",  br1 =new BonneReponse("2"));
    Question q2=new Question("Quelle est la capitale de la France?",br2=new BonneReponse("Paris"));
    Question q3=new Question("Comment se nomme le satellite naturel de la Terre",br3=new BonneReponse("La Lune"));
    Question q4=new Question("Qui est Zinedine Zidane?",br4=new BonneReponse("Un footballer"));

    Question q = new Question();


    List<Question> questions =new ArrayList<>();
    List<BonneReponse> listeBonneReponse = new ArrayList<>();
    List<MauvaiseReponse> listeMauvaiseReponse = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);



        reponse1=findViewById(R.id.buttonReponse1);
        reponse2=findViewById(R.id.buttonReponse2);
        reponse3=findViewById(R.id.buttonReponse3);
        reponse4=findViewById(R.id.buttonReponse4);
        retourAccueil=findViewById(R.id.buttonRetourAccueil);
        numeroQuestion=findViewById(R.id.textViewNumeroQuestion);
        contenuQuestion=findViewById(R.id.textViewContenuQuestion);
        affichageScore=findViewById(R.id.textViewScore);


        ajouterQuestionListe(questions,q1);ajouterQuestionListe(questions,q2);ajouterQuestionListe(questions,q3);ajouterQuestionListe(questions,q4);
        nombreTotaleQuestion= questions.size();
        ajouterBonneReponseListe(listeBonneReponse,q1.getBonneReponse());ajouterBonneReponseListe(listeBonneReponse,q2.getBonneReponse());ajouterBonneReponseListe(listeBonneReponse,q3.getBonneReponse());ajouterBonneReponseListe(listeBonneReponse, q4.getBonneReponse());
        ajouterMauvaiseReponseListe(listeMauvaiseReponse,mr1);ajouterMauvaiseReponseListe(listeMauvaiseReponse,mr2);ajouterMauvaiseReponseListe(listeMauvaiseReponse,mr3);ajouterMauvaiseReponseListe(listeMauvaiseReponse,mr4);ajouterMauvaiseReponseListe(listeMauvaiseReponse,mr5);ajouterMauvaiseReponseListe(listeMauvaiseReponse,mr6);ajouterMauvaiseReponseListe(listeMauvaiseReponse,mr7);ajouterMauvaiseReponseListe(listeMauvaiseReponse,mr8);ajouterMauvaiseReponseListe(listeMauvaiseReponse,mr9);ajouterMauvaiseReponseListe(listeMauvaiseReponse,mr10);ajouterMauvaiseReponseListe(listeMauvaiseReponse,mr11);ajouterMauvaiseReponseListe(listeMauvaiseReponse,mr12);

        afficherBoutonAcceuil(false);
        afficherscore(score,false);
        majNumeroQuestionQuestionnaire(questions, indiceBonneReponse);
        majContenuReponse(indiceBonneReponse,indiceMauvaiseReponse1,indiceMauvaiseReponse2,indiceMauvaiseReponse3);
        majContenuQuestion(indiceBonneReponse);


        reponse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j="1";
                AllModifOnCliqueBouton();
            }
        });

        reponse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j="2";
                AllModifOnCliqueBouton();
            }
        });

        reponse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j="3";
                AllModifOnCliqueBouton();
            }
        });

        reponse4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j="4";
                AllModifOnCliqueBouton();
            }
        });

        retourAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionnaireActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


        private int genererInt(int borneInf, int borneSup){
            Random random = new Random();
            int nb;
            nb = borneInf+random.nextInt(borneSup-borneInf);
            return nb;
        }
        private void afficherBoutonAcceuil(boolean b){
            if (b){
                retourAccueil.setVisibility(View.VISIBLE);
                retourAccueil.setEnabled(b);
            }else {
                retourAccueil.setVisibility(View.INVISIBLE);
                retourAccueil.setEnabled(b);
            }
        }


    private void afficherscore(int score,boolean b){
        if(b && score == 0){
            affichageScore.setVisibility(View.VISIBLE);
            affichageScore.setText("Lamentable, votre score est de "+score);
        }
        else if(b && score == 1){
            affichageScore.setVisibility(View.VISIBLE);
            affichageScore.setText("Nul, votre score est de "+score);
        }
        else if(b && score == 2){
            affichageScore.setVisibility(View.VISIBLE);
            affichageScore.setText("Bof, votre score est de "+score);
        }
        else if(b && score == 3){
            affichageScore.setVisibility(View.VISIBLE);
            affichageScore.setText("Bien, votre score est de "+score);
        }
        else if(b && score == 4){
            affichageScore.setVisibility(View.VISIBLE);
            affichageScore.setText("Très bien, votre score est de "+score);
        }
        else {
            affichageScore.setVisibility(View.INVISIBLE);
        }
    }





        private void AllModifOnCliqueBouton(){
            indiceBonneReponse++;
            indiceMauvaiseReponse1 +=3;
            indiceMauvaiseReponse2 +=3;
            indiceMauvaiseReponse3 +=3;
            q= testContenuQuestion();
            testBonneReponse(q);
            majNumeroQuestionQuestionnaire(questions, indiceBonneReponse);
            majContenuReponse(indiceBonneReponse,indiceMauvaiseReponse1,indiceMauvaiseReponse2,indiceMauvaiseReponse3);
            majContenuQuestion(indiceBonneReponse);
        }

        private Question testContenuQuestion(){
            int k=0;
            try {
                for( k=0;k<=3;k++){
                    if(contenuQuestion.getText().toString() == questions.get(k).getQuestions()){
                        return questions.get(k);
                    }
                }
                return questions.get(k);
            }catch (Exception e){
                Toast.makeText(this, "pas de contenu", Toast.LENGTH_SHORT).show();
            }
            return questions.get(k);
        }

        private void testBoutonReponse(Button b){
            if(b.getText().toString() == q.getBonneReponse().getReponse()){
                score+=1;
                Toast.makeText(this,"bonne réponse", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(Long.valueOf(time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (indiceBonneReponse > 4){
                    try {
                        Thread.sleep(Long.valueOf(0));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    majBouton();
                    afficherscore(score,true);
                    afficherBoutonAcceuil(true);
                }
            }else {
                Toast.makeText(this, "Mauvaise réponse", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(Long.valueOf(time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (indiceBonneReponse > 4){
                    majBouton();
                    afficherscore(score,true);
                    afficherBoutonAcceuil(true);
                }

            }
        }

        private void testBonneReponse(Question q)  {
            if (j == "1"){
               testBoutonReponse(reponse1);
            }
            if (j == "2"){
              testBoutonReponse(reponse2);
            }
            if (j == "3"){
                testBoutonReponse(reponse3);
            }
            if (j == "4"){
              testBoutonReponse(reponse4);
            }
        }

        private void ajouterQuestionListe(List l,Question q){
            l.add(q);
        }

        private void ajouterBonneReponseListe(List l,BonneReponse b){
            l.add(b);
        }

        private void ajouterMauvaiseReponseListe(List l,MauvaiseReponse b){
            l.add(b);
        }

        private void majNumeroQuestionQuestionnaire(List list, int indice){
            if (indice<= nombreTotaleQuestion){
                numeroQuestion.setText("Question " +indice+"/"+nombreTotaleQuestion);
            }else{
                return;
            }
        }

        private void majContenuQuestion(int indice){
            try {
                contenuQuestion.setText(questions.get(indice-1).getQuestions());
            }catch (Exception e){
                return;
            }
        }

        private void majContenuReponse(int indice,int indiceMauvaiseReponse1,int indiceMauvaiseReponse2,int indiceMauvaiseReponse3){
            try {
                nbaleatoire = genererInt(0,4);
                if(nbaleatoire==0){
                    reponse1.setText(listeMauvaiseReponse.get(indiceMauvaiseReponse1).getReponse());
                    reponse2.setText(listeMauvaiseReponse.get(indiceMauvaiseReponse2).getReponse());
                    reponse3.setText(listeBonneReponse.get(indice-1).getReponse());
                    reponse4.setText(listeMauvaiseReponse.get(indiceMauvaiseReponse3).getReponse());
                }
                if(nbaleatoire==1){
                    reponse1.setText(listeBonneReponse.get(indice-1).getReponse());
                    reponse2.setText(listeMauvaiseReponse.get(indiceMauvaiseReponse2).getReponse());
                    reponse3.setText(listeMauvaiseReponse.get(indiceMauvaiseReponse1).getReponse());
                    reponse4.setText(listeMauvaiseReponse.get(indiceMauvaiseReponse3).getReponse());
                }
                if(nbaleatoire==2){
                    reponse1.setText(listeMauvaiseReponse.get(indiceMauvaiseReponse2).getReponse());
                    reponse2.setText(listeBonneReponse.get(indice-1).getReponse());
                    reponse3.setText(listeMauvaiseReponse.get(indiceMauvaiseReponse1).getReponse());
                    reponse4.setText(listeMauvaiseReponse.get(indiceMauvaiseReponse3).getReponse());
                }
                if(nbaleatoire==3){
                    reponse1.setText(listeMauvaiseReponse.get(indiceMauvaiseReponse2).getReponse());
                    reponse2.setText(listeMauvaiseReponse.get(indiceMauvaiseReponse3).getReponse());
                    reponse3.setText(listeMauvaiseReponse.get(indiceMauvaiseReponse1).getReponse());
                    reponse4.setText(listeBonneReponse.get(indice-1).getReponse());
                }


            }catch (Exception e){
                return;
            }
        }

        private void majBouton(){
            reponse1.setEnabled(false);
            reponse2.setEnabled(false);
            reponse3.setEnabled(false);
            reponse4.setEnabled(false);
            reponse1.setBackgroundColor(Color.rgb(206,206,206));
            reponse2.setBackgroundColor(Color.rgb(206,206,206));
            reponse3.setBackgroundColor(Color.rgb(206,206,206));
            reponse4.setBackgroundColor(Color.rgb(206,206,206));
        }


    }


