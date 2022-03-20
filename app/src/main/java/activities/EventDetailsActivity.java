package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
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


    ExpandableListAdapter expandableListAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private CreateEventModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        ButterKnife.bind(this);

        model = getIntent().getParcelableExtra("model");
        //  Log.d(TAG, "Model: "+model);

        //tourName.setText(model.getTripName() + " Tour");
        tourName.setText(model.getTripName());
        tvDescription.setText(model.getTripDescription());
        tvStartLocation.setText(model.getTripStartLocation());
        tvDestination.setText(model.getTripDestination());
        tvStartDate.setText(model.getTripStartDate());
        tvEndDate.setText(model.getTripEndDate());
        tvBudget.setText(model.getTripBudget());
//
//        tvDescriptionName.setText(model.getTripDescription() + ", from " + model.getTripStartLocation() + " to " + model.getTripName() + ", " + model.getTripDestination() + "."
//                + " Started on " + model.getTripStartDate() + " to " + model.getTripEndDate() + " and " + "the Trip budget was " + model.getTripBudget() + " Taka.");

/*
 tourDetails.setText(model.getTripDescription() + "\n\n" + "From " + model.getTripStartLocation() + " to " + model.getTripName() + ", " + model.getTripDestination() + ".\n\n" + "Start date: " + model.getTripStartDate() + "\n\n" + "End date: " +
                model.getTripEndDate() + "\n\n" + "Budget: " + model.getTripBudget() + " Taka");

*/

        /* tourDetails.setText("Description: " + model.getTripDescription() + "\n\n" + "Starting location: " + model.getTripStartLocation()
                + "\n\n" + "Destination: " + model.getTripDestination() + "\n\n" + "Start date: " + model.getTripStartDate() + "\n\n" + "End date: " +
                model.getTripEndDate() + "\n\n" + "Budget: " + model.getTripBudget());
        */

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
                    fragment.show(getSupportFragmentManager(), "ExpenseDialog");
                    Toast.makeText(EventDetailsActivity.this, "Add new expense clicked", Toast.LENGTH_SHORT).show();

                } else if (selected.equalsIgnoreCase("View all expense")) {

                    startActivity(new Intent(EventDetailsActivity.this, ExpenseListActivity.class));
                    Toast.makeText(EventDetailsActivity.this, "View all expense clicked", Toast.LENGTH_SHORT).show();

                } else if (selected.equalsIgnoreCase("Take a photo")) {
                    startActivity(new Intent(EventDetailsActivity.this, CameraActivity.class));

                }
                return true;
            }
        });
    }
}