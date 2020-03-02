package me.manishmahalwal.android.fms2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CompletedComplaintsStudent extends Fragment {

    List<ObjComplaintStatusStudent> objComplaintStatusList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v= inflater.inflate(R.layout.fragment_cc, container, false);

        ((Student) getActivity()).setActionBarTitle("Resolved Complaints");
        final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading data...");
        mProgressDialog.show();

        final RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.rv2);

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
                            if(temp.complaintFrom.equals(curUserEmail) && temp.completed.equals("true"))
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
                            if(temp.complaintFrom.equals(curUserEmail) && temp.completed.equals("true"))
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
                            if(temp.complaintFrom.equals(curUserEmail) && temp.completed.equals("true"))
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
                            if(temp.complaintFrom.equals(curUserEmail) && temp.completed.equals("true"))
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
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {


                    int position = rv.getChildAdapterPosition(child);
                    Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_SHORT).show();

                    EditCompletedComplaintStatusStudentDialog dialogFragment = new EditCompletedComplaintStatusStudentDialog();

                    Bundle bundle = new Bundle();
                    bundle.putBoolean("notAlertDialog", true);

                    // bundle.putString("unid",atemp.getText().toString());



                    dialogFragment.setArguments(bundle);

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);

                    dialogFragment.show(ft, "dialog");

                }

                return false;
            }



            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        return v;

    }

    public void addComplaintStatusStudent(ObjComplaintStatusStudent myObj)
    {
        objComplaintStatusList.add(myObj);
    }
}