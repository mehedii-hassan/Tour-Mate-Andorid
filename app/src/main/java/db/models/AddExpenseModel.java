package db.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Add new expense")
public class AddExpenseModel {

    @PrimaryKey(autoGenerate = true)
    int id;
    int amount;
    String comment;




    public AddExpenseModel(int amount, String comment) {
        this.amount = amount;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @NonNull
    @Override
    public String toString() {
        return "AddExpenseModel{" +
                "id=" + id +
                ", amount=" + amount +
                ", comment='" + comment + '\'' +
                '}';
    }
}
