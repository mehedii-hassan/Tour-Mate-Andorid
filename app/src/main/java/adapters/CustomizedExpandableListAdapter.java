package adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.tourmate.R;

import java.util.HashMap;
import java.util.List;

public class CustomizedExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    public CustomizedExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listDataChild.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listDataChild.get(listDataHeader.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerText  = getGroup(i).toString().trim();

        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_group_layout,null);
        }

        TextView textView = view.findViewById(R.id.tvListHeaderTitle);
        textView.setText(headerText);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String hChildText  = getChild(i,i1).toString().trim();

        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_child_layout,null);
        }

        TextView textView = view.findViewById(R.id.tvExpandedListChildItem);
        textView.setText(hChildText);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

/*



	}

	@Override
	// Gets a View that displays the data for the given child within the given group.
	public View getChildView(int lstPosn, final int expanded_ListPosition,
							boolean isLastChild, View convertView, ViewGroup parent) {
		final String expandedListText = (String) getChild(lstPosn, expanded_ListPosition);
		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.list_item, null);
		}
		TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expandedListItem);
		expandedListTextView.setText(expandedListText);
		return convertView;
	}



 }

	@Override
	// Gets a View that displays the given group.
	// This View is only for the group--the Views for the group's children
	// will be fetched using getChildView()
	public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		String listTitle = (String) getGroup(listPosition);
		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) this.context.
					getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.list_group, null);
		}
		TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
		listTitleTextView.setTypeface(null, Typeface.BOLD);
		listTitleTextView.setText(listTitle);
		return convertView;
	}

	@Override
	// Indicates whether the child and group IDs are stable across changes to the underlying data.
	public boolean hasStableIds() {
		return false;
	}

	@Override
	// Whether the child at the specified position is selectable.
	public boolean isChildSelectable(int listPosition, int expandedListPosition) {
		return true;
	}
}
*/
}
