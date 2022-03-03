package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.tourmate.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapters.CustomizedExpandableListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import classes.ExpandableListDataItems;

public class EventDetailsActivity extends AppCompatActivity {

    @BindView(R.id.expandableListViewId)
    ExpandableListView expandableListView;

    CustomizedExpandableListAdapter customizedExpandableListAdapter;
    List<String> listDataHeader;
    HashMap<String ,List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        ButterKnife.bind(this);

        listDataChild = ExpandableListDataItems.getData();
        listDataHeader = new ArrayList<>(listDataChild.keySet());


        customizedExpandableListAdapter = new CustomizedExpandableListAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(customizedExpandableListAdapter);
    }
}

/*



		expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
		expandableListAdapter = new CustomizedExpandableListAdapter(this, expandableTitleList, expandableDetailList);
		expandableListViewExample.setAdapter(expandableListAdapter);

		// This method is called when the group is expanded
		expandableListViewExample.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(getApplicationContext(), expandableTitleList.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
			}
		});

		// This method is called when the group is collapsed
		expandableListViewExample.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getApplicationContext(), expandableTitleList.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
			}
		});

		// This method is called when the child in any group is clicked
		// via a toast method, it is shown to display the selected child item as a sample
		// we may need to add further steps according to the requirements
		expandableListViewExample.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
										int groupPosition, int childPosition, long id) {
				Toast.makeText(getApplicationContext(), expandableTitleList.get(groupPosition)
								+ " -> "
								+ expandableDetailList.get(
								expandableTitleList.get(groupPosition)).get(
								childPosition), Toast.LENGTH_SHORT
				).show();
				return false;
			}
		});
	}
}
*/