package com.example.mario.fragmentdinamico;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    TextView NumQuestion,Question;
    Button Next;
    EditText Answers;
    private Question  question = new Question();
    private Answer answer= new Answer();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NumQuestion=findViewById(R.id.lblNumQuestion);
        Question=findViewById(R.id.lblQuestion);
        Next=findViewById(R.id.btnNext);
        Answers=findViewById(R.id.txtAnswer);
       // NumQuestion.setText(question.getIdQuestion()+"");
        //determinado por la pregunta
        //Question.setText(question.getData().getstatement());
        //Creates los fragmentos y Transacciones de Fragmentos
        QuestionFragment questionFragment  = new QuestionFragment.QuestionFragmentBuilder(question,answer).build();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        question.setType(QuestionData.QuestionType.OPEN);
        answer.setAnswer("hola");
//        //Remplazar el fragmento en la vista
        if (questionFragment!=null) {
            transaction.add(R.id.fragment_container, questionFragment);
//        transaction.addToBackStack(null);
            transaction.commit();
        }
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer.setAnswer(Answers.getText()+"");
            }
        });
    }

}
