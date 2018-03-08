package com.bizshare.roomexample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Rakesh.Kumar on 08-03-2018.
 */

/**
 * create our Dao interface which contains the list of operation that we would like to perform on table.
 */
@Dao
public interface DaoAccess {
    @Insert
    void insertMultipleRecord(University... universities);

    @Insert
    void insertMultipleListRecord(List<University> universities);

    @Insert
    void insertOnlySingleRecord(University university);

    @Query("SELECT * FROM University")
    List<University> fetchAllData();

    @Query("SELECT * FROM University WHERE clgid =:college_id")
    University getSingleRecord(int college_id);

    @Update
    void updateRecord(University university);

    @Delete
    void deleteRecord(University university);

    @Query("SELECT * FROM University")
    LiveData<List<University>> fetchAllLiveData();
}
