package com.bizshare.roomexample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

/**
 * DO NOT PERFORM OPERATION ON MAIN THREAD.
 */

/**
 * app will crash as the operation is performed on a main thread.
 * Doing database operations — insert, update,delete, read data from the table has to be done in background thread,
 * will it was always intended to be perform on background thread but now it’s mandatory.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    SampleDatabase sampleDatabase;
    Button btnSubmit;
    Button btnGetdata;
    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sampleDatabase = Room.databaseBuilder(getApplicationContext(),
                SampleDatabase.class, "user.db").build();

        btnSubmit = findViewById(R.id.btn_submit);
        btnGetdata = findViewById(R.id.btn_getdata);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        btnSubmit.setOnClickListener(this);
        btnGetdata.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        getLivedata();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                University university = createData();
                DatabaseAsync databaseAsync = new DatabaseAsync(sampleDatabase, MainActivity.this, Constant.insert, university);
                databaseAsync.execute();
                break;
            case R.id.btn_getdata:
                University university1 = createData();
                DatabaseAsync databaseAsync1 = new DatabaseAsync(sampleDatabase, MainActivity.this, Constant.select, university1);
                databaseAsync1.execute();
                break;
            case R.id.btn_update:
                University university2 = createData();
                DatabaseAsync databaseAsync2 = new DatabaseAsync(sampleDatabase, MainActivity.this, Constant.update, university2);
                databaseAsync2.execute();
                break;
            case R.id.btn_delete:
                University university3 = createData();
                DatabaseAsync databaseAsync3 = new DatabaseAsync(sampleDatabase, MainActivity.this, Constant.delete, university3);
                databaseAsync3.execute();
                break;
            default:
                break;
        }
    }

    private void getLivedata() {
        LiveData<List<University>> universityLiveData = sampleDatabase.daoAccess().fetchAllLiveData();
        universityLiveData.observe(this, new Observer<List<University>>() {
            @Override
            public void onChanged(@Nullable List<University> universities) {
                //Update your UI here.
                Log.e(TAG, "onChanged::" + universities.size());
            }
        });
    }

    private University createData() {
        //Let's add some dummy data to the database.
        University university = new University();
        university.setName("MyUniversity");

        College college = new College();
        college.setId(1);
        college.setName("MyCollege");

        university.setCollege(college);
        return university;
    }
}