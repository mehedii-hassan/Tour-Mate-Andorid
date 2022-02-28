package db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EventModalClass.class},version = 1)
public abstract class EventDatabase extends RoomDatabase {

    public abstract EventDao eventDao();
    private static  EventDatabase INSTANCE;

    public  static EventDatabase getINSTANCE(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),EventDatabase.class,"Tour events")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}
