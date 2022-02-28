package db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void insert(EventModalClass eventModalClass);

    @Update
    void update(EventModalClass eventModalClass);

    @Delete
    void delete(EventModalClass eventModalClass);


    @Query("SELECT * FROM event_information ORDER BY tripName ASC")
    List<EventModalClass> getAllEvent();

}
