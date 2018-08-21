package com.techsdm.quizzy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techsdm.quizzy.Model.Question;
import com.techsdm.quizzy.Model.QuestionListItem;

import java.util.ArrayList;
import java.util.List;

public class ShowQuestion extends AppCompatActivity {

    Animation animate_button_background;
    Animation animate_button_background_stop;
    int clue1_select_score=0;
    int clue2_select_score=0;
    int clue3_select_score=0;
    int clue4_select_score=0;
    int test_score=0;
    int size=1;
    Question question=new Question();
    int score=0;
    int clue1_select=0;
    int clue2_select=0;
    int clue3_select=0;
    int clue4_select=0;
    int counter=0;
    List<Question> questionList=new ArrayList<Question>();
    RecyclerView recyclerView;
    String data;
    DatabaseReference dbQuestions;
    TextView question_name;
    Button clue_button1,clue_button2,clue_button3,clue_button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_question);

        question_name=(TextView)findViewById(R.id.question);
        clue_button1=(Button)findViewById(R.id.clue1);
        clue_button2=(Button)findViewById(R.id.clue2);
        clue_button3=(Button)findViewById(R.id.clue3);
        clue_button4=(Button)findViewById(R.id.clue4);

        animate_button_background= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.background_animation);
        animate_button_background_stop=AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.background_animation_stop);

        Bundle bundle=getIntent().getExtras();
        data=bundle.getString("name");
        data=data.trim();
        Toast.makeText(getApplicationContext(),"Data->"+data,Toast.LENGTH_SHORT).show();

        clue_button1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v)
            {
                clue_button1.startAnimation(animate_button_background);
                clue_button2.startAnimation(animate_button_background_stop);
                clue_button3.startAnimation(animate_button_background_stop);
                clue_button4.startAnimation(animate_button_background_stop);
                clue1_select=1;
                clue2_select=0;
                clue3_select=0;
                clue4_select=0;
                clue3_select_score=0;
                clue2_select_score=0;
                clue4_select_score=0;
                //test_score=test_score+1;
                perform();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        dbQuestions= FirebaseDatabase.getInstance().getReference("Question").child(data);
        dbQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                question=new Question();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    question=postSnapshot.getValue(Question.class);
                    questionList.add(question);
                }
                size=questionList.size();
                //Toast.makeText(getApplicationContext(),"Size of List->"+size,Toast.LENGTH_SHORT).show();
                question= questionList.get(counter);
                clue_button1.setText(question.getClue1());
                clue_button2.setText(question.getClue2());
                clue_button3.setText(question.getClue3());
                clue_button4.setText(question.getClue4());
                question_name.setText(question.getQuestion_name());




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @SuppressLint("ResourceAsColor")
    public void clue2_click(View view)
    {
        clue_button2.startAnimation(animate_button_background);
        clue_button1.startAnimation(animate_button_background_stop);
        clue_button4.startAnimation(animate_button_background_stop);
        clue_button3.startAnimation(animate_button_background_stop);
        clue2_select=1;
        clue1_select=0;
        clue3_select=0;
        clue4_select=0;
        clue1_select_score=0;
        clue3_select_score=0;
        clue4_select_score=0;

        //test_score=test_score+1;
        perform();
    }
    @SuppressLint("ResourceAsColor")
    public void clue3_click(View view)
    {
        clue_button3.startAnimation(animate_button_background);
        clue_button1.startAnimation(animate_button_background_stop);
        clue_button2.startAnimation(animate_button_background_stop);
        clue_button4.startAnimation(animate_button_background_stop);
        clue3_select=1;
        clue2_select=0;
        clue1_select=0;
        clue4_select=0;
        clue1_select_score=0;
        clue2_select_score=0;
        clue4_select_score=0;
        //test_score=test_score+1;
        perform();
    }
    @SuppressLint("ResourceAsColor")
    public void clue4_click(View view)
    {
        clue_button4.startAnimation(animate_button_background);
        clue_button1.startAnimation(animate_button_background_stop);
        clue_button2.startAnimation(animate_button_background_stop);
        clue_button3.startAnimation(animate_button_background_stop);
        clue4_select=1;
        clue3_select=0;
        clue2_select=0;
        clue1_select=0;
        clue1_select_score=0;
        clue2_select_score=0;
        clue3_select_score=0;
        //test_score=test_score+1;
        perform();
    }
    public void forwardbefore()
    {
        if(clue1_select_score!=0)
        {
            score=score+clue1_select_score;
        }
        else if(clue2_select_score!=0)
        {
            score=score+clue2_select_score;
        }
        else if(clue3_select_score!=0)
        {
            score=score+clue3_select_score;
        }
        else if(clue4_select_score!=0)
        {
            score=score+clue4_select_score;
        }
        else
        {
            score=score+0;
        }
    }

    @SuppressLint("ResourceAsColor")
    public void forward_button_click(View view)
    {
        forwardbefore();
        clue1_select_score=0;
        clue2_select_score=0;
        clue3_select_score=0;
        clue4_select_score=0;
        clue_button1.startAnimation(animate_button_background_stop);
        clue_button2.startAnimation(animate_button_background_stop);
        clue_button3.startAnimation(animate_button_background_stop);
        clue_button4.startAnimation(animate_button_background_stop);
        if(counter>size-2)
        {
            Intent intent=new Intent(getApplicationContext(),Result.class);
            Bundle bundle=new Bundle();
            bundle.putString("name", String.valueOf(score));
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else
        {
            counter=counter+1;
            question= questionList.get(counter);
            clue_button1.setText(question.getClue1());
            clue_button2.setText(question.getClue2());
            clue_button3.setText(question.getClue3());
            clue_button4.setText(question.getClue4());
            question_name.setText(question.getQuestion_name());
        }



    }
    public void perform()
    {

        if((clue1_select==1) && (question.getQuestion_answer().equals("clue1")))
        {
            clue1_select_score=1;
            clue2_select_score=0;
            clue3_select_score=0;
            clue4_select_score=0;
        }
        else if((clue2_select==1) && (question.getQuestion_answer().equals("clue2")))
        {
            clue1_select_score=0;
            clue2_select_score=1;
            clue3_select_score=0;
            clue4_select_score=0;
        }
        else if((clue3_select==1) && (question.getQuestion_answer().equals("clue3")))
        {
            clue1_select_score=0;
            clue2_select_score=0;
            clue3_select_score=1;
            clue4_select_score=0;
        }
        else if((clue4_select==1) && (question.getQuestion_answer().equals("clue4")))
        {
            clue1_select_score=0;
            clue2_select_score=0;
            clue3_select_score=0;
            clue4_select_score=1;
        }
        else
        {
            score=score;
            Toast.makeText(getApplicationContext(),"Size of List->"+size,Toast.LENGTH_SHORT).show();
        }
    }

}
