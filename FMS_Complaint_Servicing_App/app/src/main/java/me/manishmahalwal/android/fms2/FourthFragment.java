package me.manishmahalwal.android.fms2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FourthFragment extends Fragment implements WorkerDialog.DialogListener{

    List<WorkComplaintStatusStudent> objComplaintStatusList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fourth_layout, container, false);
        ((AdminActivity) getActivity()).setActionBarTitle("Add Worker");
        final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading data...");
        mProgressDialog.show();

        final RecyclerView recyclerView = v.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        final FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_blac);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkerDialog dialogFragment = new WorkerDialog();

                Bundle bundle = new Bundle();
                bundle.putBoolean("notAlertDialog", true);

                dialogFragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                dialogFragment.show(ft, "dialog");
            }
        });

        objComplaintStatusList = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("ListOfWorker")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            Worker temp=snapshot.getValue(Worker.class);
                            if(temp.valid.equals("true"))
                            {
                                objComplaintStatusList.add(
                                        new WorkComplaintStatusStudent(
                                                temp.assignedRoom,
                                                temp.assignedStatus,
                                                temp.gender,
                                                temp.id,
                                                temp.name,
                                                temp.phoneNumber,
                                                temp.type,
                                                temp.valid
                                        )
                                );
                                WorkComplaintStatusStudentAdapter adapter = new WorkComplaintStatusStudentAdapter(getActivity(), objComplaintStatusList);
                                recyclerView.setAdapter(adapter);
                            }

                        }
                        mProgressDialog.dismiss();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("AboutStudent", "NOT POSSIBLE");
//                        Toast.makeText(getApplicationContext(), "Maa Chud Gayi", Toast.LENGTH_LONG).show();

                    }
                });



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy > 0 || dy<0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        /*
         * add onclicklistener for each recyclerview item
         *
         * */



        return v;

    }

    public void addComplaintStatusStudent(WorkComplaintStatusStudent myObj)
    {
        objComplaintStatusList.add(myObj);
    }

    /*
     * When dialog box is closed this is called so you can use it to update the information entered by the user
     *
     * */
    @Override
    public void onFinishEditDialog(String inputText) {

        if (TextUtils.isEmpty(inputText)) {
            Toast.makeText(getActivity(), "Email was not entered", Toast.LENGTH_SHORT);
        }
        else
            Toast.makeText(getActivity(), "Email: " + inputText, Toast.LENGTH_SHORT);
    }
}
