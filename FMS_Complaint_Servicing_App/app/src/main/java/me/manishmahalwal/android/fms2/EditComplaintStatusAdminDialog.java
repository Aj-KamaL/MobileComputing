package me.manishmahalwal.android.fms2;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditComplaintStatusAdminDialog extends DialogFragment {

    Button assignWorker;
    Button resolved;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        if (getArguments() != null) {
            if (getArguments().getBoolean("notAlertDialog")) {
                return super.onCreateDialog(savedInstanceState);
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Alert Dialog");
        builder.setMessage("Alert Dialog inside DialogFragment");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("Complaint Info");
        final String complaintId = getArguments().getString("complaintId");
        final int typeComplaint = Integer.parseInt(complaintId.charAt(8)+"");
        View v = inflater.inflate(R.layout.edit_complaint_dialog_admin, container, false);
        final HashMap<String, String> mp = new HashMap<>();
        final HashMap<Integer, String> typemap = new HashMap<>();
        typemap.put(0, "Room Cleaning");
        typemap.put(1, "AC Servicing");
        typemap.put(2, "Carpenter");
        typemap.put(3, "Electric");
        Log.e("order","o1");
        final ArrayList<String> arraySpinner = new ArrayList<>();


        FirebaseDatabase.getInstance().getReference().child("ListOfWorker")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Worker temp=snapshot.getValue(Worker.class);
                            mp.put(temp.name, temp.id);
                            Log.e("order",""+temp.valid.equals("true"));
                            Log.e("order",""+temp.assignedStatus.equals("false"));;
                            Log.e("order",typemap.get(typeComplaint)+" : "+temp.type+" : "+typemap.get(typeComplaint).equals(temp.type) );
                            if(temp.valid.equals("true") && temp.assignedStatus.equals("false") && typemap.get(typeComplaint).equals(temp.type)){
                                arraySpinner.add(temp.name);
                            }
                        }
//                        Log.e("order123",arraySpinner.toString()+"    &&&&   "+getActivity().toString());
//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arraySpinner);
//                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        Log.e("order","o3");
//                        spinner.setAdapter(adapter);

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("AboutStudent", "NOT POSSIBLE");
                    }
                });
//        final Spinner s = v.findViewById(R.id.spinner3);
//        String[] arraySpinner1 = new String[] {
//                "Male", "Female"
//        };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arraySpinner1);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        s.setAdapter(adapter);
//        Log.e("order","o2 " +arraySpinner.size());
//        ArrayList<String> years = new ArrayList<String>();
//        for (int i = 0; i <arraySpinner.size(); i++) {
//            Log.e("Howtodit",arraySpinner.get(i));
//            years.add(arraySpinner.get(i));
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, years);
//        final Spinner spinner = (Spinner)v.findViewById(R.id.spinner3);
//        Log.e("order","o3");
//
//        spinner.setAdapter(adapter);

//        final Spinner spinner = (Spinner)v.findViewById(R.id.spinner3);
//        List<String> list = Arrays.asList(getResources().getStringArray(R.array.state));
//
//        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,list);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(spinnerAdapter);
//        for (int i=0;i<arraySpinner.size();i++){
//            spinnerAdapter.add(arraySpinner.get(i));
//            spinnerAdapter.notifyDataSetChanged();
//        }



        final TextView idtext = v.findViewById(R.id.complaintView);
        final TextView destext = v.findViewById(R.id.descriptionView);
        assignWorker = (Button) v.findViewById(R.id.assignButton);
        resolved = (Button) v.findViewById(R.id.resolveButton);
        Log.e("ComplaintStat", complaintId);
        idtext.setText(complaintId);
        FirebaseDatabase.getInstance().getReference("CleanComplaint")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        ///orderin is making problems


                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            CleanComplaint temp=snapshot.getValue(CleanComplaint.class);
                            if(complaintId.equals(temp.complaintNum)){
                                destext.setText(temp.ComplaintDescription);
                            }
                        }
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
                            Log.d("Check2.1","iterate");
                            AcComplaint temp=snapshot.getValue(AcComplaint.class);
                            if(complaintId.equals(temp.complaintNum)){
                                destext.setText(temp.ComplaintDescription);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("AboutStudent", "NOT POSSIBLE");
                    }
                });

        FirebaseDatabase.getInstance().getReference().child("CarpentComplaint")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            Log.d("Check2.1","iterate");
                            CarpentComplaint temp=snapshot.getValue(CarpentComplaint.class);
                            if(complaintId.equals(temp.complaintNum)){
                                destext.setText(temp.ComplaintDescription);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("AboutStudent", "NOT POSSIBLE");

                    }
                });

        FirebaseDatabase.getInstance().getReference().child("ElectricComplaint")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            Log.d("Check2.1","iterate");
                            ElectricComplaint temp=snapshot.getValue(ElectricComplaint.class);
                            if(complaintId.equals(temp.complaintNum)){
                                destext.setText(temp.ComplaintDescription);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("AboutStudent", "NOT POSSIBLE");

                    }
                });

        assignWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("CleanComplaint")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot){
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    Log.e("snapshit", "assign");
                                    CleanComplaint temp=snapshot.getValue(CleanComplaint.class);
                                    String idNum = complaintId.substring(9);
                                    if (typeComplaint==0) {
                                        Log.e("IDNUM", idNum + "");
                                        Map<String, Object> taskMap = new HashMap<String, Object>();
                                        Log.e("order","o4");
                                        final String workerAssigned = arraySpinner.get(0);
                                        taskMap.put("complaintTo", workerAssigned);
                                        FirebaseDatabase.getInstance().getReference("CleanComplaint").child(idNum).updateChildren(taskMap);
                                        Map<String, Object> taskMap2 = new HashMap<String, Object>();
                                        taskMap2.put("assignedStatus", "true");
                                        FirebaseDatabase.getInstance().getReference("ListOfWorker").child(mp.get(workerAssigned)).updateChildren(taskMap2);

                                        try {
                                            SmsManager.getDefault().sendTextMessage("+918585966708", null,
                                                    "New Complaint assigned to you: \nRoom no.: " + temp.complaintRoom + "\nBuilding: " +temp.locationBuilding + "\nDescription: " + temp.ComplaintDescription, null, null);
                                        } catch (Exception e) {
                                            AlertDialog.Builder alertDialogBuilder = new
                                                    AlertDialog.Builder(getActivity());
                                            AlertDialog dialog = alertDialogBuilder.create();
                                            dialog.setMessage(e.getMessage());
                                            dialog.show();
                                        }
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e("AboutStudent", "NOT POSSIBLE");

                            }
                        });

        FirebaseDatabase.getInstance().getReference().child("AcComplaint")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            AcComplaint temp=snapshot.getValue(AcComplaint.class);
                            String idNum = complaintId.substring(9);
                            if (typeComplaint==1) {
                                Log.e("IDNUM", idNum + "");
                                Map<String, Object> taskMap = new HashMap<String, Object>();
                                Log.e("order","o5");
                                final String workerAssigned =arraySpinner.get(0);
                                taskMap.put("complaintTo", workerAssigned);
                                FirebaseDatabase.getInstance().getReference("AcComplaint").child(idNum).updateChildren(taskMap);
                                Map<String, Object> taskMap2 = new HashMap<String, Object>();
                                taskMap2.put("assignedStatus", "true");
                                FirebaseDatabase.getInstance().getReference("ListOfWorker").child(mp.get(workerAssigned)).updateChildren(taskMap2);
                            }
                         }
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
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            CarpentComplaint temp=snapshot.getValue(CarpentComplaint.class);
                            String idNum = complaintId.substring(9);
                            if (typeComplaint==2) {
                                Log.e("IDNUM", idNum + "");
                                Map<String, Object> taskMap = new HashMap<String, Object>();
                                Log.e("order","o6");
                                final String workerAssigned =arraySpinner.get(0);
                                taskMap.put("complaintTo", workerAssigned);
                                FirebaseDatabase.getInstance().getReference("CarpentComplaint").child(idNum).updateChildren(taskMap);
                                Map<String, Object> taskMap2 = new HashMap<String, Object>();
                                taskMap2.put("assignedStatus", "true");
                                FirebaseDatabase.getInstance().getReference("ListOfWorker").child(mp.get(workerAssigned)).updateChildren(taskMap2);
                            }
                        }
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
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            ElectricComplaint temp=snapshot.getValue(ElectricComplaint.class);
                            String idNum = complaintId.substring(9);
                            if (typeComplaint==3) {
                                Log.e("IDNUM", idNum + "");
                                Map<String, Object> taskMap = new HashMap<String, Object>();
                                Log.e("order","o7");

                                int random = (int )(Math.random() * arraySpinner.size());
                                final String workerAssigned =arraySpinner.get(0);
                                taskMap.put("complaintTo", workerAssigned);
                                FirebaseDatabase.getInstance().getReference("ElectricComplaint").child(idNum).updateChildren(taskMap);
                                Map<String, Object> taskMap2 = new HashMap<String, Object>();
                                taskMap2.put("assignedStatus", "true");
                                FirebaseDatabase.getInstance().getReference("ListOfWorker").child(mp.get(workerAssigned)).updateChildren(taskMap2);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("AboutStudent", "NOT POSSIBLE");

                    }
                });
                final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Notification");
                myRef.addListenerForSingleValueEvent(new ValueEventListener(){
                    int val;
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        val = dataSnapshot.getValue(Integer.class);
                        myRef.setValue(1);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                dismiss();
            }
        });

        resolved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Notification");
                myRef.addListenerForSingleValueEvent(new ValueEventListener(){
                    int val;
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        val = dataSnapshot.getValue(Integer.class);
                        myRef.setValue(2);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                FirebaseDatabase.getInstance().getReference("CleanComplaint")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot){
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    Log.e("snapshit", "resolve");
                                    ElectricComplaint temp=snapshot.getValue(ElectricComplaint.class);
                                    String idNum = complaintId.substring(9);
                                    if (typeComplaint==0) {
                                        String workerAssigned = temp.complaintTo;
                                        Map<String, Object> taskMap = new HashMap<String, Object>();
                                        String tempw=temp.complaintTo;
                                        taskMap.put("completed", "true");
                                        taskMap.put("complaintTo", "NULL");
                                        taskMap.put("priority", "-1");
                                        FirebaseDatabase.getInstance().getReference("CleanComplaint").child(idNum).updateChildren(taskMap);
                                        Map<String, Object> taskMap2 = new HashMap<String, Object>();
                                        taskMap2.put("assignedStatus", "false");
                                        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("CleanID");
                                        ref.addListenerForSingleValueEvent(new ValueEventListener(){
                                            int val;
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                val = dataSnapshot.getValue(Integer.class);
                                                int upd = val - 1;
                                                ref.setValue(upd);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                        Log.e("snapshit",tempw);
                                        if(!tempw.equals("NULL")) {
                                            Log.e("snapshit", tempw);
                                            FirebaseDatabase.getInstance().getReference("ListOfWorker").child(mp.get(tempw)).updateChildren(taskMap2);
                                        }
                                        FirebaseDatabase.getInstance().getReference("CleanID").getKey();

                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e("AboutStudent", "NOT POSSIBLE");

                            }
                        });

                FirebaseDatabase.getInstance().getReference().child("AcComplaint")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot)
                            {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    ElectricComplaint temp=snapshot.getValue(ElectricComplaint.class);
                                    String idNum = complaintId.substring(9);
                                    if (typeComplaint==1) {
                                        String workerAssigned = temp.complaintTo;
                                        Map<String, Object> taskMap = new HashMap<String, Object>();
                                        String tempw=temp.complaintTo;
                                        taskMap.put("completed", "true");
                                        taskMap.put("complaintTo", "NULL");
                                        taskMap.put("priority", "-1");
                                        FirebaseDatabase.getInstance().getReference("AcComplaint").child(idNum).updateChildren(taskMap);
                                        Map<String, Object> taskMap2 = new HashMap<String, Object>();
                                        taskMap2.put("assignedStatus", "false");
                                        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ACID");
                                        ref.addListenerForSingleValueEvent(new ValueEventListener(){
                                            int val;
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                val = dataSnapshot.getValue(Integer.class);
                                                int upd = val - 1;
                                                ref.setValue(upd);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                        if(!tempw.equals("NULL")) {
                                            FirebaseDatabase.getInstance().getReference("ListOfWorker").child(mp.get(tempw)).updateChildren(taskMap2);
                                        }
                                        FirebaseDatabase.getInstance().getReference("ACID").getKey();

                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e("AboutStudent", "NOT POSSIBLE");

                            }
                        });

                FirebaseDatabase.getInstance().getReference().child("CarpentComplaint")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot)
                            {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    ElectricComplaint temp=snapshot.getValue(ElectricComplaint.class);
                                    String idNum = complaintId.substring(9);
                                    if (typeComplaint==2) {
                                        String workerAssigned = temp.complaintTo;
                                        Map<String, Object> taskMap = new HashMap<String, Object>();
                                        String tempw=temp.complaintTo;
                                        taskMap.put("completed", "true");
                                        taskMap.put("complaintTo", "NULL");
                                        taskMap.put("priority", "-1");
                                        FirebaseDatabase.getInstance().getReference("CarpentComplaint").child(idNum).updateChildren(taskMap);
                                        Map<String, Object> taskMap2 = new HashMap<String, Object>();
                                        taskMap2.put("assignedStatus", "false");
                                        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("CarpenterID");
                                        ref.addListenerForSingleValueEvent(new ValueEventListener(){
                                            int val;
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                val = dataSnapshot.getValue(Integer.class);
                                                int upd = val - 1;
                                                ref.setValue(upd);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                        if(!tempw.equals("NULL")) {
                                            FirebaseDatabase.getInstance().getReference("ListOfWorker").child(mp.get(tempw)).updateChildren(taskMap2);
                                        }
                                        FirebaseDatabase.getInstance().getReference("CarpentID").getKey();

                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e("AboutStudent", "NOT POSSIBLE");

                            }
                        });

                FirebaseDatabase.getInstance().getReference().child("ElectricComplaint")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot)
                            {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    ElectricComplaint temp=snapshot.getValue(ElectricComplaint.class);
                                    String idNum = complaintId.substring(9);
                                    if (typeComplaint==3) {
                                        String workerAssigned = temp.complaintTo;
                                        Map<String, Object> taskMap = new HashMap<String, Object>();
                                        String tempw=temp.complaintTo;
                                        taskMap.put("completed", "true");
                                        taskMap.put("complaintTo", "NULL");
                                        taskMap.put("priority", "-1");
                                        FirebaseDatabase.getInstance().getReference("ElectricComplaint").child(idNum).updateChildren(taskMap);
                                        Map<String, Object> taskMap2 = new HashMap<String, Object>();
                                        taskMap2.put("assignedStatus", "false");
                                        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ElectricID");
                                        ref.addListenerForSingleValueEvent(new ValueEventListener(){
                                            int val;
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                val = dataSnapshot.getValue(Integer.class);
                                                int upd = val - 1;
                                                ref.setValue(upd);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                        if(!tempw.equals("NULL")) {
                                            FirebaseDatabase.getInstance().getReference("ListOfWorker").child(mp.get(tempw)).updateChildren(taskMap2);
                                        }
                                        FirebaseDatabase.getInstance().getReference("ElectricID").getKey();

                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e("AboutStudent", "NOT POSSIBLE");

                            }
                        });
                dismiss();
            }
        });
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("API123", "onCreate");

        boolean setFullScreen = false;
        if (getArguments() != null) {
            setFullScreen = getArguments().getBoolean("fullScreen");
        }

        if (setFullScreen)
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public interface DialogListener {
        void onFinishEditDialog(String inputText);
    }
}
