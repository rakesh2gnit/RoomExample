package com.bizshare.roomexample;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Rakesh.Kumar on 08-03-2018.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    SampleDatabase sampleDatabase;
    EditText editTextName, editTextEmail, editTextPassword, editTextPhone, editTextAddress;
    Button buttonSubmit;
    Spinner spinnerGender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        sampleDatabase = Room.databaseBuilder(getApplicationContext(),
                SampleDatabase.class, "user.db").build();

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        spinnerGender = findViewById(R.id.spinnerGender);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSubmit:
                onSubmit();
                break;
            default:
                break;
        }
    }

    private void onSubmit() {
        String strName = editTextName.getText().toString().trim();
        String strEmail = editTextEmail.getText().toString().trim();
        String strPassword = editTextPassword.getText().toString().trim();
        String strPhone = editTextPhone.getText().toString().trim();
        String strAddress = editTextAddress.getText().toString().trim();
        String strGender = spinnerGender.getSelectedItem().toString().trim();
    }
}
