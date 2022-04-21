package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireActivity extends AppCompatActivity {
    int indice=1;
    int nombreTotaleQuestion= 0;
    Button reponse1,reponse2,reponse3,reponse4;
    TextView numeroQuestion,contenuQuestion;
    String j ="0";
    int time = 2500;

    BonneReponse br1=new BonneReponse("2");
    BonneReponse br2=new BonneReponse("Paris");
    BonneReponse br3=new BonneReponse("La Lune");
    BonneReponse br4=new BonneReponse("Un footballer");

    Question q1=new Question("Combien font 1+1 ?",  br1 =new BonneReponse("2"));
    Question q2=new Question("Quelle est la capitale de la France?",br2=new BonneReponse("Paris"));
    Question q3=new Question("Comment se nomme le satellite naturel de la Terre",br3=new BonneReponse("La Lune"));
    Question q4=new Question("Qui est Zinedine Zidane?",br4=new BonneReponse("Un footballer"));

    Question q = new Question();


    List<Question> questions =new ArrayList<>();
    List<BonneReponse> bonneReponses = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);



        reponse1=findViewById(R.id.buttonReponse1);
        reponse2=findViewById(R.id.buttonReponse2);
        reponse3=findViewById(R.id.buttonReponse3);
        reponse4=findViewById(R.id.buttonReponse4);
        numeroQuestion=findViewById(R.id.textViewNumeroQuestion);
        contenuQuestion=findViewById(R.id.textViewContenuQuestion);


        ajouterQuestionListe(questions,q1);
        ajouterQuestionListe(questions,q2);
        ajouterQuestionListe(questions,q3);
        ajouterQuestionListe(questions,q4);

        nombreTotaleQuestion= questions.size();

        ajouterBonneReponseListe(bonneReponses,q1.getBonneReponse());
        ajouterBonneReponseListe(bonneReponses,q2.getBonneReponse());
        ajouterBonneReponseListe(bonneReponses,q3.getBonneReponse());
        ajouterBonneReponseListe(bonneReponses, q4.getBonneReponse());

        majNumeroQuestionQuestionnaire(questions,indice);
        majContenuReponse(indice);
        majContenuQuestion(indice);


        reponse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indice++;
                majNumeroQuestionQuestionnaire(questions,indice);
                majContenuReponse(indice);
                majContenuQuestion(indice);
                j="1";
                q= testContenuQuestion();
                testBonneReponse(q);

            }
        });

        reponse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indice++;
                majNumeroQuestionQuestionnaire(questions,indice);
                majContenuReponse(indice);
                majContenuQuestion(indice);
                j="2";
                q= testContenuQuestion();
                testBonneReponse(q);

            }
        });

        reponse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indice++;
                majNumeroQuestionQuestionnaire(questions,indice);
                majContenuReponse(indice);
                majContenuQuestion(indice);
                j="3";
                q= testContenuQuestion();
                testBonneReponse(q);
            }
        });

        reponse4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indice++;
                majNumeroQuestionQuestionnaire(questions,indice);
                majContenuReponse(indice);
                majContenuQuestion(indice);
                j="4";
                q= testContenuQuestion();
                testBonneReponse(q);
            }
        });

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
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }
            return questions.get(k);
        }
        private void testBoutonReponse(Button b){
            if(b.getText() == q.getBonneReponse().getReponse()){
                Toast.makeText(this,"Bonne réponse", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(Long.valueOf(time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (indice > 4){
                    Toast.makeText(this, "Quizz terminé", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "mauvaise réponse", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(Long.valueOf(time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (indice > 4){
                    Toast.makeText(this, "Quizz terminé", Toast.LENGTH_SHORT).show();
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

        private void majNumeroQuestionQuestionnaire(List list, int indice){
            if (indice<= nombreTotaleQuestion){
                numeroQuestion.setText("Question " +indice+"/"+nombreTotaleQuestion);
            }else{
                majBouton();
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

        private void majContenuReponse(int indice){
            try {
                reponse1.setText(bonneReponses.get(indice-1).getReponse());
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


