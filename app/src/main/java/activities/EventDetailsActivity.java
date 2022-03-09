package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.tourmate.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapters.ExpandableListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import classes.ExpandableListDataItems;
import db.databases.TourEventsDB;
import db.models.AddExpenseModel;
import fragments.AddNewExpenseDialogFragment;

public class EventDetailsActivity extends AppCompatActivity {

    @BindView(R.id.expandableListViewId)
    ExpandableListView expandableListView;

    ExpandableListAdapter expandableListAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        ButterKnife.bind(this);

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

                     startActivity(new Intent(EventDetailsActivity.this,ExpenseListActivity.class));
                    Toast.makeText(EventDetailsActivity.this, "View all expense clicked", Toast.LENGTH_SHORT).show();

                } else if (selected.equalsIgnoreCase("Add more budget")) {

                    Toast.makeText(EventDetailsActivity.this, "Add more budget clicked", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
}