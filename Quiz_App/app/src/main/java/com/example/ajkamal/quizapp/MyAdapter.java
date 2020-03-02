package com.example.ajkamal.quizapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public ques_item[] data;
    public Context c;
    public MyAdapter(ques_item[] data, Context c)
    {
        this.data = data;
        this.c=c;
    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ques_item,viewGroup,false);
        Log.e("IN Frag"," in adpater 4 ");
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final ques_item title =data[i];
        Log.e("IN Frag"," in adpater 2 "+i);
        Log.e("IN Frag"," in adpater 2.2 "+title.name);
        viewHolder.name.setText(title.name);

        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Fragment fragment = new Ques();
                Bundle arguments = new Bundle();
                arguments.putString( "Question" , title.question);
                arguments.putString( "ID" , title.name);
                fragment.setArguments(arguments);
                Log.e("IN Frag"," in adpater 8 "+title.question);
                FragmentTransaction ft = ((AppCompatActivity) c).getSupportFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.fragment_contair,fragment).commit();
                Log.e("IN Frag"," in adpater 9 ");

            }
        });










    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public Button name;

        public ViewHolder(View itemView) {
            super(itemView);
            name=(Button) itemView.findViewById(R.id.button);

            Log.e("IN Frag"," in adpater 1 ");



        }
    }

}