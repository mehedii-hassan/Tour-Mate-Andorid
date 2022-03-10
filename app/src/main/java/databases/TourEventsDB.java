package databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import daos.AddExpenseDao;
import daos.EventDao;
import models.AddExpenseModel;
import models.CreateEventModel;

@Database(entities = {CreateEventModel.class, AddExpenseModel.class},version = 1)
public abstract class TourEventsDB extends RoomDatabase {

    public abstract EventDao eventDao();
    public abstract AddExpenseDao expenseDao();

    private static TourEventsDB INSTANCE;

    public  static TourEventsDB getINSTANCE(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TourEventsDB.class,"Tour events")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}
