package me.manishmahalwal.android.fms2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

public class SecondFragment extends Fragment implements EditComplaintStatusAdminDialog.DialogListener{


    List<ObjComplaintStatusStudent> objComplaintStatusList;
    static ArrayList<String> idList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        idList.clear();
        View v = inflater.inflate(R.layout.second_layout, container, false);
        ((AdminActivity) getActivity()).setActionBarTitle("Pending Complaints");

        final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading data...");
        mProgressDialog.show();

        final RecyclerView recyclerView = v.findViewById(R.id.rv3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        objComplaintStatusList = new ArrayList<>();

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final String curUserEmail = mAuth.getCurrentUser().getEmail();
        Log.d("CheckC", curUserEmail);

        FirebaseDatabase.getInstance().getReference("CleanComplaint")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            CleanComplaint temp=snapshot.getValue(CleanComplaint.class);

                            Log.d("Check2.1",temp.toString());

                            if(temp.completed.equals("false") ){
                                objComplaintStatusList.add(
                                        new ObjComplaintStatusStudent(
                                                temp.ComplaintDescription,
                                                temp.complaintNum,
                                                temp.complaintRoom,
                                                temp.complaintTo,
                                                "Room Cleaning",
                                                temp.priority,
                                                temp.locationBuilding
                                        )
                                );
                                idList.add(temp.complaintNum);
                                ObjComplaintStatusStudentAdapter adapter = new ObjComplaintStatusStudentAdapter(getActivity(), objComplaintStatusList);
                                recyclerView.setAdapter(adapter);
                            }
                        }
                        mProgressDialog.dismiss();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("AboutStudent", "NOT POSSIBLE");

                    }
                });

        FirebaseDatabase.getInstance().getReference().child("AcComplaint")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Log.d("Check2.2","iterate");
                            AcComplaint temp=snapshot.getValue(AcComplaint.class);
                            if(temp.completed.equals("false")){
                                objComplaintStatusList.add(
                                        new ObjComplaintStatusStudent(
                                                temp.ComplaintDescription,
                                                temp.complaintNum,
                                                temp.complaintRoom,
                                                temp.complaintTo,
                                                "AC Servicing",
                                                temp.priority,
                                                temp.locationBuilding
                                        )
                                );
                                idList.add(temp.complaintNum);
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
                    public void onDataChange(DataSnapshot dataSnapshot){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Log.d("Check2.2","iterate");
                            CarpentComplaint temp=snapshot.getValue(CarpentComplaint.class);
                            if( temp.completed.equals("false")){
                                objComplaintStatusList.add(
                                        new ObjComplaintStatusStudent(
                                                temp.ComplaintDescription,
                                                temp.complaintNum,
                                                temp.complaintRoom,
                                                temp.complaintTo,
                                                "Carpenter",
                                                temp.priority,
                                                temp.locationBuilding
                                        )
                                );
                                idList.add(temp.complaintNum);
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
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Log.d("Check2.2","iterate");
                            ElectricComplaint temp=snapshot.getValue(ElectricComplaint.class);
                            if(temp.completed.equals("false")){
                                objComplaintStatusList.add(
                                        new ObjComplaintStatusStudent(
                                                temp.ComplaintDescription,
                                                temp.complaintNum,
                                                temp.complaintRoom,
                                                temp.complaintTo,
                                                "Electric",
                                                temp.priority,
                                                temp.locationBuilding
                                        )
                                );
                                idList.add(temp.complaintNum);
                                ObjComplaintStatusStudentAdapter adapter = new ObjComplaintStatusStudentAdapter(getActivity(), objComplaintStatusList);
                                recyclerView.setAdapter(adapter);
                            }

                        }
                        mProgressDialog.dismiss();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("AboutStudent", "NOT POSSIBLE");

                    }
                });
           /*
         * add onclicklistener for each recyclerview item
         *
         * */

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
                    EditComplaintStatusAdminDialog dialogFragment = new EditComplaintStatusAdminDialog();

                    View selectedComplaint = recyclerView.getLayoutManager().findViewByPosition(position);

                    Bundle bundle = new Bundle();
                    bundle.putBoolean("notAlertDialog", true);
                    bundle.putString("complaintId", idList.get(position));
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
