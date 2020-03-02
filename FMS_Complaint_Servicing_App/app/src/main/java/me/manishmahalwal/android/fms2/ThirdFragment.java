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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment implements WorkerDialog.DialogListener{

    List<ObjComplaintStatusStudent> objComplaintStatusList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_cc, container, false);
        ((AdminActivity) getActivity()).setActionBarTitle("Resolved Complaints");

        final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading data...");
        mProgressDialog.show();

        final RecyclerView recyclerView = v.findViewById(R.id.rv2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        objComplaintStatusList = new ArrayList<>();
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final String curUserEmail = mAuth.getCurrentUser().getEmail();
//        final String curUserEmail = "mad@t.com";
        FirebaseDatabase.getInstance().getReference().child("CleanComplaint")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            CleanComplaint temp=snapshot.getValue(CleanComplaint.class);
                            if(temp.completed.equals("true"))
                            {
                                objComplaintStatusList.add(
                                        new ObjComplaintStatusStudent(
                                                temp.ComplaintDescription,
                                                temp.complaintNum,
                                                temp.complaintRoom,
                                                temp.complaintTo,
                                                "Room Cleaning",
                                                Integer.toString(-1),
                                                temp.locationBuilding
                                        )
                                );
                                ObjComplaintStatusStudentAdapter adapter = new ObjComplaintStatusStudentAdapter(getActivity(), objComplaintStatusList);
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

        FirebaseDatabase.getInstance().getReference().child("AcComplaint")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            AcComplaint temp=snapshot.getValue(AcComplaint.class);
                            if( temp.completed.equals("true"))
                            {
                                objComplaintStatusList.add(
                                        new ObjComplaintStatusStudent(
                                                temp.ComplaintDescription,
                                                temp.complaintNum,
                                                temp.complaintRoom,
                                                temp.complaintTo,
                                                "AC Servicing",
                                                Integer.toString(-1),
                                                temp.locationBuilding
                                        )
                                );
                                ObjComplaintStatusStudentAdapter adapter = new ObjComplaintStatusStudentAdapter(getActivity(), objComplaintStatusList);
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

        FirebaseDatabase.getInstance().getReference().child("CarpentComplaint")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            CarpentComplaint temp=snapshot.getValue(CarpentComplaint.class);
                            if( temp.completed.equals("true"))
                            {
                                objComplaintStatusList.add(
                                        new ObjComplaintStatusStudent(
                                                temp.ComplaintDescription,
                                                temp.complaintNum,
                                                temp.complaintRoom,
                                                temp.complaintTo,
                                                "Carpenter",
                                                Integer.toString(-1),                                                temp.locationBuilding
                                        )
                                );
                                ObjComplaintStatusStudentAdapter adapter = new ObjComplaintStatusStudentAdapter(getActivity(), objComplaintStatusList);
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

        FirebaseDatabase.getInstance().getReference().child("ElectricComplaint")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            ElectricComplaint temp=snapshot.getValue(ElectricComplaint.class);
                            if( temp.completed.equals("true"))
                            {
                                objComplaintStatusList.add(
                                        new ObjComplaintStatusStudent(
                                                temp.ComplaintDescription,
                                                temp.complaintNum,
                                                temp.complaintRoom,
                                                temp.complaintTo,
                                                "Electric",
                                                Integer.toString(-1),                                                temp.locationBuilding
                                        )
                                );
                                ObjComplaintStatusStudentAdapter adapter = new ObjComplaintStatusStudentAdapter(getActivity(), objComplaintStatusList);
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

        return v;

    }

    public void addComplaintStatusStudent(ObjComplaintStatusStudent myObj)
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

