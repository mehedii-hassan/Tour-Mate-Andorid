package models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Add new expense")
public class AddExpenseModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int amount;
    private String comment;
    private long date;


    public AddExpenseModel(int amount, String comment, long date) {
        this.amount = amount;
        this.comment = comment;
        this.date = date;
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
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
