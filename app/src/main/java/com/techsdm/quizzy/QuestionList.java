package com.techsdm.quizzy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techsdm.quizzy.Adapter.QuestionListAdapter;
import com.techsdm.quizzy.Model.CategoryItem;
import com.techsdm.quizzy.Model.QuestionListItem;

import java.util.ArrayList;
import java.util.List;

public class QuestionList extends AppCompatActivity {

    QuestionListAdapter mAdapter;
    List<QuestionListItem> questionListItemArray=new ArrayList<QuestionListItem>();
    RecyclerView recyclerView;
    DatabaseReference dbQuestionList;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        Bundle bundle=getIntent().getExtras();
        data=bundle.getString("name");
        data=data.trim();
    }

    @Override
    protected void onStart() {
        super.onStart();
        questionListItemArray.clear();
        dbQuestionList= FirebaseDatabase.getInstance().getReference("QuestionList").child(data);
        dbQuestionList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                QuestionListItem questionListItem=new QuestionListItem();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    questionListItem=postSnapshot.getValue(QuestionListItem.class);
                    questionListItemArray.add(questionListItem);
                }
                recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                mAdapter = new QuestionListAdapter(questionListItemArray);
                recyclerView.setHasFixedSize(true);
                GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(mAdapter);

                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        QuestionListItem questionListItem= questionListItemArray.get(position);
                        //Toast.makeText(getApplicationContext(), cricketer.getCname() + " is selected!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),ShowQuestion.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("name",questionListItem.getName().trim());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),CategoryList.class);
        startActivity(intent);
    }
}
