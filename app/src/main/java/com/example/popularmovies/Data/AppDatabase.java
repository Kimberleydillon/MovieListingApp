package com.example.popularmovies.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Film.class}, version = 1 )
public abstract class AppDatabase extends RoomDatabase {
    // ---DAO---
    public abstract FilmDao filmDao();

   //---Singleton---
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase (final Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            // Queries should be done in a separate thread to avoid locking the UI
                            // We will allow this ONLY TEMPORALLY to see that our DB is working
                            //.allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
