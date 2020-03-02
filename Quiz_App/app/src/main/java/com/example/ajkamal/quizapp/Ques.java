package com.example.ajkamal.quizapp;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class Ques extends Fragment  {
    public TextView l;
    public CheckBox c,d;
    Button e;
    String temp="NA";
    SQLiteOpenHelper mdb;
    String ans="";
    String name="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        View v = inflater.inflate(R.layout.fragment_ques, container, false);
        Log.e("IN Frag"," in adpater 9.2 ");
        mdb=new Data(getContext());

        Bundle arguments = getArguments();
        String desired_string = arguments.getString("Question");
        String id_ques=arguments.getString("ID");
        String[] tem=id_ques.split(" ");
        String id=tem[1];
        int id_f=Integer.parseInt(id);

        Cursor res = ((Data) (mdb)).getAllData();

        if(res.getCount() == 0) {
            Log.e("tree"," NOTHING FOUND ");
        }

        else {
            int idx = 0;
            while (res.moveToNext()&& idx < id_f ) {
                if (idx==id_f-1){
                    name=res.getString(1);
                    ans=res.getString(4);
                }
                idx++;
            }
        }



        Log.e("In Frag"," in adapter 10 "+ans);

        l=(TextView)v.findViewById(R.id.quesl);
        l.setText(desired_string);
       // Toast.makeText(getContext(), "Save Your Choice before moving to next Question", Toast.LENGTH_LONG).show();

        c=(CheckBox)v.findViewById(R.id.tru);
        d=(CheckBox)v.findViewById(R.id.fals);
        if (ans.equals("false")){
            d.toggle();


        }
        else if (ans.equals("true")){
            c.toggle();
        }

        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    d.setChecked(false);
                    temp="true";
                    Toast.makeText(getContext(), temp + " selected", Toast.LENGTH_SHORT).show();


                }
            }
        });

        d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    c.setChecked(false);
                    temp="false";
                    Toast.makeText(getContext(), temp + " selected", Toast.LENGTH_SHORT).show();
                }
            }
        });



        //Submit
        e=(Button)v.findViewById(R.id.button2);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Submit
                if (temp.equals("NA")&&!c.isChecked()&&!d.isChecked()){
                    boolean c = ((Data) mdb).updateData(name, temp);
                }
                else if((!temp.equals("NA"))&& (!temp.equals(ans))){
                    boolean c=((Data)mdb).updateData(name,temp);
                }

                Log.e("in Ques","in adapter 8.2 "+c);
                Fragment fragment = new queslist();
                FragmentTransaction ft = ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_contair,fragment).commit();
                Log.e("IN Frag"," in adpater 9 ");

            }
        });

        mdb.close();
        return v;
    }



}
