package db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import db.models.AddExpenseModel;

@Dao
public interface AddExpenseDao {
    @Insert
    void insert(AddExpenseModel addExpenseModel);


    @Update
    void update(AddExpenseModel addExpenseModel);

    @Delete
    void delete(AddExpenseModel addExpenseModel);


    @Query("SELECT * FROM `Add new expense`")
    List<AddExpenseModel> getAllExpense();
}
