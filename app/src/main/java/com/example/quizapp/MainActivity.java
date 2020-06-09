package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txtquestion;
    private ProgressBar pbar;
    private TextView txtstats;
    private Button btntrue;
    private Button btnwrong;
    private int mquestionIndex;
    private int mQuizques;
    private int User_score;
    private QuizModel[] questionCollections=new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,false),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,false),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,false),
    };
    final int USERPROGRESS=(int)Math.ceil(100/questionCollections.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtquestion=(TextView)findViewById(R.id.txtquestion);
        txtstats=(TextView)findViewById(R.id.txtstats);
        btntrue=(Button) findViewById(R.id.btntrue);
        btnwrong=(Button) findViewById(R.id.btnwrong);
        pbar=(ProgressBar)findViewById(R.id.pbar);

        QuizModel q1=questionCollections[mquestionIndex];
        mQuizques=q1.getQuestion();
        txtquestion.setText(questionCollections[mquestionIndex].getQuestion());
        btntrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateAnswers(true);
                changeQuestion();

            }
        });

        btnwrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                evaluateAnswers(false);
                changeQuestion();

            }
        });
    }

    private void changeQuestion(){

        mquestionIndex=(mquestionIndex+1)%10;
        if (mquestionIndex==0){
            AlertDialog.Builder quizAlert=new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("The quiz is Finished");
            quizAlert.setMessage("Your Score is " + User_score);
            quizAlert.setPositiveButton("Finish The Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            quizAlert.show();
        }
        mQuizques=questionCollections[mquestionIndex].getQuestion();
        txtquestion.setText(mQuizques);
        pbar.incrementProgressBy(USERPROGRESS);
        txtstats.setText(User_score+"");



    }

    private void evaluateAnswers(boolean UserGuess){
        boolean currentAns=questionCollections[mquestionIndex].isAnswer();
        if (UserGuess==currentAns){
            Toast.makeText(getApplicationContext(),R.string.correct_toast_message,Toast.LENGTH_SHORT).show();
            User_score=User_score+1;
        }else {
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast_message,Toast.LENGTH_SHORT).show();
        }

    }
}
