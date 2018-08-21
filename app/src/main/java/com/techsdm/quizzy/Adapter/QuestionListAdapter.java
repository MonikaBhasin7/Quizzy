package com.techsdm.quizzy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.techsdm.quizzy.Model.CategoryItem;
import com.techsdm.quizzy.Model.QuestionListItem;
import com.techsdm.quizzy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monika Bhasin on 24-07-2018.
 */

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionListViewHolder> {

    List<QuestionListItem> questionListItemArray=new ArrayList<QuestionListItem>();

    public QuestionListAdapter(List<QuestionListItem> questionListItemArray) {
        this.questionListItemArray = questionListItemArray;
    }

    @Override
    public QuestionListAdapter.QuestionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.category_item_layout,parent,false);
        return new QuestionListAdapter.QuestionListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final QuestionListAdapter.QuestionListViewHolder holder, int position) {
        final QuestionListItem questionListItem=questionListItemArray.get(position);

        Picasso.get()
                .load(questionListItem.getImageLink())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .rotate(90f)
                .into(holder.image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(questionListItem.getImageLink())
                                .error(R.drawable.ic_wallpaper_black_24dp)
                                .into(holder.image, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        Log.e("error","Wallpaper not Fetched");
                                    }
                                });
                    }
                });
        holder.name.setText(questionListItem.getName());
    }

    @Override
    public int getItemCount() {
        return questionListItemArray.size();
    }

    public class QuestionListViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;
        public QuestionListViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            image=(ImageView)itemView.findViewById(R.id.image);
        }
    }


}
