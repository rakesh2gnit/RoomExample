package com.bizshare.roomexample;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * Created by Rakesh.Kumar on 08-03-2018.
 */

public class DatabaseAsync extends AsyncTask<Void, Void, Void> {

    private static final String TAG = DatabaseAsync.class.getSimpleName();
    SampleDatabase sampleDatabase;
    Context context;
    int action;
    University university;

    public DatabaseAsync(SampleDatabase sampleDatabase, Context context, int operation, University university) {
        super();
        this.sampleDatabase = sampleDatabase;
        this.context = context;
        this.action = operation;
        this.university = university;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //Perform pre-adding operation here.
    }

    @Override
    protected Void doInBackground(Void... voids) {

        if (action == Constant.insert) {
            //Now access all the methods defined in DaoAccess with sampleDatabase object
            sampleDatabase.daoAccess().insertOnlySingleRecord(university);
        } else if (action == Constant.select) {
            List<University> universityData = sampleDatabase.daoAccess().fetchAllData();
            Log.e(TAG, "getdata::" + universityData.size());
        } else if (action == Constant.update) {
            //To update only name of university, change it and pass the object along with the primary key value.
            university.setSlNo(1);
            university.setName("ABCUniversity");
            sampleDatabase.daoAccess().updateRecord(university);
        } else if (action == Constant.delete) {
            //To delete this record set the primary key and this will delete the record matching that primary key value.
            university.setSlNo(1);
            sampleDatabase.daoAccess().deleteRecord(university);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        //To after addition operation here.
    }
}