package activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tourmate.R;

import java.util.List;

import adapters.ExpenseListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import db.databases.TourEventsDB;
import db.models.AddExpenseModel;

public class ExpenseListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<AddExpenseModel> expenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);
        ButterKnife.bind(this);

        //retrieve list from AddExpenseModel class ----------
        expenseList= TourEventsDB.getINSTANCE(this)
                .expenseDao()
                .getAllExpense();

        ExpenseListAdapter listAdapter = new ExpenseListAdapter(this,expenseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

    }
}