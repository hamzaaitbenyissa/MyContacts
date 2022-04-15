package ma.enset.procontacts;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 2,exportSchema = false)
public abstract class RoomDb extends RoomDatabase {

    // create database instance
    private static RoomDb database;

    // Define database name
    private static String DATABASE_NAME="contacts2_db";

    public synchronized static RoomDb getInstance(Context context)
    {
        // check condition
        if(database==null)
        {
            // when database is null
            // Initialize database
            database= Room.databaseBuilder(context.getApplicationContext(), RoomDb.class,DATABASE_NAME)
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        // Return database
        return database;
    }

    //Create DAO
    public abstract ContactDao contactDAO();


}
