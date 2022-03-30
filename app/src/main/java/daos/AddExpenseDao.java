package daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import models.AddExpenseModel;

@Dao
public interface AddExpenseDao {
    @Insert
    void insert(AddExpenseModel addExpenseModel);


    @Update
    void update(AddExpenseModel addExpenseModel);

    /*@Delete
    void delete(AddExpenseModel addExpenseModel);

*/
//    @Query("UPDATE `add new expense` set comment=:newComment,amount=:newAmount where id =:id")
//    void update(String newComment, int newAmount, int id);

    @Query("SELECT * FROM `Add new expense`")
    List<AddExpenseModel> getAllExpense();

    @Query("SELECT * FROM `Add new expense` where tourName =:tourName")
    List<AddExpenseModel> getTourExpenses(String tourName);


    @Query("DELETE FROM `add new expense` WHERE id = :id")
    void deleteById(int id);
}
