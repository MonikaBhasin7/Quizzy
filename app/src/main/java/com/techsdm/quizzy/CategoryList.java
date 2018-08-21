package com.techsdm.quizzy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techsdm.quizzy.Adapter.CategoryAdapter;
import com.techsdm.quizzy.Model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryList extends AppCompatActivity {


    CategoryAdapter mAdapter;
    private RecyclerView recyclerView;
    List<CategoryItem> categoryList=new ArrayList<CategoryItem>();
    DatabaseReference dbCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        dbCategories= FirebaseDatabase.getInstance().getReference("Category");

    }

    @Override
    public void onStart() {

        super.onStart();
        dbCategories= FirebaseDatabase.getInstance().getReference("Category");
        dbCategories.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categoryList.clear();
                CategoryItem categoryItem=new CategoryItem();

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    categoryItem = postSnapshot.getValue(CategoryItem.class);

                    categoryList.add(categoryItem);
                }
                recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                mAdapter = new CategoryAdapter(categoryList);
                RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);

                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        CategoryItem categoryItem = categoryList.get(position);
                        //Toast.makeText(getApplicationContext(), cricketer.getCname() + " is selected!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),QuestionList.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("name",categoryItem.getName().trim());
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
        Intent start = new Intent(Intent.ACTION_MAIN);
        start.addCategory(Intent.CATEGORY_HOME);
        start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(start);
    }
}
