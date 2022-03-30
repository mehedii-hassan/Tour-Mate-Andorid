package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourmate.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapters.ExpandableListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import classes.ExpandableListDataItems;
import databases.TourEventsDB;
import models.AddExpenseModel;
import models.CreateEventModel;
import fragments.AddNewExpenseDialogFragment;

public class EventDetailsActivity extends AppCompatActivity {

    private static final String TAG = "EventDetailsActivity";

    @BindView(R.id.expandableListViewId)
    ExpandableListView expandableListView;

    @BindView(R.id.tvEDTourName)
    TextView tourName;

    @BindView(R.id.tvDescription)
    TextView tvDescription;

    @BindView(R.id.tvStartLocation)
    TextView tvStartLocation;

    @BindView(R.id.tvDestination)
    TextView tvDestination;

    @BindView(R.id.tvStartDate)
    TextView tvStartDate;

    @BindView(R.id.tvEndDate)
    TextView tvEndDate;

    @BindView(R.id.tvBudget)
    TextView tvBudget;

    @BindView(R.id.tvPercent)
    TextView percentOfUseBudget;

    @BindView(R.id.progressBarId)
    ProgressBar progressBarId;

    private int progress;
    private List<AddExpenseModel> expenseList;
    int totalExpenseSum = 0;
    int totalBudget;


    ExpandableListAdapter expandableListAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private CreateEventModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        ButterKnife.bind(this);

        //progressbar height change------
        progressBarId.setScaleY(3f);

        model = getIntent().getParcelableExtra("model");

        tourName.setText(model.getTripName());
        tvDescription.setText(model.getTripDescription());
        tvStartLocation.setText(model.getTripStartLocation());
        tvDestination.setText(model.getTripDestination());
        tvStartDate.setText(model.getTripStartDate());
        tvEndDate.setText(model.getTripEndDate());
        tvBudget.setText(model.getTripBudget());

        //retrieve list from AddExpenseModel class ----------
        expenseList = TourEventsDB.getINSTANCE(this)
                .expenseDao()
                .getAllExpense();
        //calculate total amount of expense--------------
        /*for (int i = 0; i < expenseList.size(); i++) {
            totalExpenseSum += expenseList.get(i).getAmount();
        }
        totalBudget=Integer.parseInt(model.getTripBudget());
        int percent=((totalBudget*totalExpenseSum)/100);

        for (int i = 0; i < percent; i++) {
            //totalExpenseSum += expenseList.get(i).getAmount();
            progressBarId.setProgress(percent);
            percentOfUseBudget.setText(Integer.toString(percent));

        }*/


        listDataChild = ExpandableListDataItems.getData();
        listDataHeader = new ArrayList<>(listDataChild.keySet());


        expandableListAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                String selected = (String) expandableListAdapter.getChild(i, i1);

                if (selected.equalsIgnoreCase("Add new expense")) {

                    AddNewExpenseDialogFragment fragment = new AddNewExpenseDialogFragment();
                    fragment.setTourName(model.getTripName());

                    fragment.show(getSupportFragmentManager(), "ExpenseDialog");
                    Toast.makeText(EventDetailsActivity.this, "Add new expense clicked", Toast.LENGTH_SHORT).show();

                } else if (selected.equalsIgnoreCase("View all expenses")) {

                    startActivity(new Intent(EventDetailsActivity.this, ExpenseListActivity.class));
                    Toast.makeText(EventDetailsActivity.this, "View all expense clicked", Toast.LENGTH_SHORT).show();

                } else if (selected.equalsIgnoreCase("Add more budget")) {
                    Toast.makeText(EventDetailsActivity.this, "Add more budget clicked", Toast.LENGTH_SHORT).show();

                } else if (selected.equalsIgnoreCase("Take a photo")) {
                    startActivity(new Intent(EventDetailsActivity.this, CameraActivity.class));

                } else if (selected.equalsIgnoreCase("View gallery")) {
                    Toast.makeText(EventDetailsActivity.this, "View gallery clicked", Toast.LENGTH_SHORT).show();

                } else if (selected.equalsIgnoreCase("View all moments")) {
                    startActivity(new Intent(EventDetailsActivity.this, ViewAllMomentsActivity.class));

                }
                return true;
            }
        });


    }
}

/*public class SplashScreen extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private int progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        //progressBar.setScaleY(3f);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();

            }
        });

        thread.start();

    }

    private void doWork() {

        for (progress = 20; progress <= 100; progress += 20) {
            try {
                Thread.sleep(300);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {

            }

        }

    }


}*/