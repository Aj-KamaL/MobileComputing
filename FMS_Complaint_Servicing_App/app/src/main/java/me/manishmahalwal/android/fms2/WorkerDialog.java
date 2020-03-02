package me.manishmahalwal.android.fms2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WorkerDialog extends DialogFragment {

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
    Button saveButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("Complaint Info");

        View v = inflater.inflate(R.layout.add_worker_dialog, container, false);
        saveButton = v.findViewById(R.id.button3);
        final EditText name, phone;
        name = v.findViewById(R.id.workerName);
        phone = v.findViewById(R.id.workerPhone);


        final Spinner type = v.findViewById(R.id.workerType);
        String[] arraySpinner2 = new String[] {
                "Room Cleaning", "Electric", "Carpenter", "AcComplaint"
        };
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arraySpinner2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter2);



        final Spinner s = v.findViewById(R.id.spinner5);
        String[] arraySpinner = new String[] {
                "Male", "Female"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isEmpty(name))  {
                    name.setError("Required");
                    name.requestFocus();
                    return;
                }
                if(isEmpty(phone))  {
                    phone.setError("Required");
                    phone.requestFocus();
                    return;
                }

                final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("WID");
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                myRef.addListenerForSingleValueEvent(new ValueEventListener(){
                    int val;
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        val = dataSnapshot.getValue(Integer.class);
                        int upd = val + 1;
                        myRef.setValue(upd);

                        String wName = name.getText().toString().trim();
                        String wphone = phone.getText().toString().trim();
                        String wgender= s.getSelectedItem().toString().trim();
                        String wType = type.getSelectedItem().toString().trim();

                        Worker workTemp = new Worker(Integer.toString(upd), wName, wphone, wgender, "false", "NULL", wType, "true");
                        final DatabaseReference myRef2 = database.getReference("ListOfWorker").child(upd+"");
                        myRef2.setValue(workTemp);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                getDialog().dismiss();

            }

        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
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