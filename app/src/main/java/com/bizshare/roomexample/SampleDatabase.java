package com.bizshare.roomexample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Rakesh.Kumar on 08-03-2018.
 */

/**
 *  create our database with version number
 */
@Database(entities = {University.class}, version = 1)
public abstract class SampleDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
