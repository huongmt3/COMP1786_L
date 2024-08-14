package com.example.helloworld;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.time.LocalDate;

public class L3_4_GUI extends AppCompatActivity {
    //Ref
    Spinner sp;
    TextView dobControl;
    EditText nameInput;
    EditText phoneInput;
    EditText emailInput;
    CheckBox checkBox;
    Button submitBtn;
    // Create an array with options
    private String[] workStatus = {"Employed", "Unemployed"};

    //Get inputs
    private void getInput() {
        nameInput = findViewById(R.id.name_input);
        phoneInput = findViewById(R.id.phone_input);
        emailInput = findViewById(R.id.email_input);
        checkBox = findViewById(R.id.checkBox);
        submitBtn = findViewById(R.id.submit_btn);

        String name = nameInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String email = emailInput.getText().toString();
        String workStatus = sp.getSelectedItem().toString();
        boolean isChecked = checkBox.isChecked();

        displayNextAlert(name, phone, email, workStatus, isChecked);
    }

    private void displayNextAlert(String name, String phone, String email, String workStatus, boolean isChecked) {
        new AlertDialog.Builder(this).
                setTitle("Entred Information").
                setMessage("Name: " + name + "\n"
                        + "Phone Number: " + phone + " \n"
                        + "Email: " + email + "\n"
                        + "Work Status: " + workStatus).
                setNeutralButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l34_gui2);

        // Get reference
        sp = findViewById(R.id.spinner);
        submitBtn = findViewById(R.id.submit_btn);

        // Create an adapter
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, workStatus);
        // Connect adapter to spinner
        sp.setAdapter(dataAdapter);

        //Add behavioural to the button
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = findViewById(R.id.checkBox);

                if (!cb.isChecked()) {
                    Toast.makeText(
                                    L3_4_GUI.this,
                                    "Please check the checkbox",
                                    Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                getInput();
            }
        });
    }

    private void updateDOB(LocalDate dob) {
        TextView dobControl = findViewById(R.id.dob_control);
        dobControl.setText(dob.toString());
    }

    // DatePicker Fragment inside MainActivity
    public class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            LocalDate d = LocalDate.now();
            int year = d.getYear();
            int month = d.getMonthValue();
            int day = d.getDayOfMonth();
            return new DatePickerDialog(getActivity(), this, year, --month, day);
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            LocalDate dob = LocalDate.of(year, ++month, day);
            ((L3_4_GUI) getActivity()).updateDOB(dob);
        }

        //OnClickListener
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_l34_gui2);
            dobControl = findViewById(R.id.dob_control);

            dobControl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "datePicker");
                }
            });
        }
    }
}