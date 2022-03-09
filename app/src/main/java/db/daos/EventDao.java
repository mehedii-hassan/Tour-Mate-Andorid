package db.daos;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import db.models.CreateEventModel;

@Dao
public interface EventDao {
    @Insert
    void insert(CreateEventModel createEventModel);


    @Update
    void update(CreateEventModel createEventModel);

    @Delete
    void delete(CreateEventModel createEventModel);


    @Query("SELECT * FROM event_information ORDER BY tripName ASC")
    List<CreateEventModel> getAllEvent();


}
