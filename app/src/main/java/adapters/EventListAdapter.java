package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmate.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import databases.TourEventsDB;
import interfaces.EventListAdapterInterface;
import models.CreateEventModel;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.MyViewHolder> {

    private Context context;
    List<CreateEventModel> tourEvents;
    private EventListAdapterInterface eventListAdapterInterface;

    public EventListAdapter(Context context, List<CreateEventModel> tourEvents, EventListAdapterInterface eventListAdapterInterface) {
        this.context = context;
        this.tourEvents = tourEvents;
        this.eventListAdapterInterface = eventListAdapterInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row_item_design, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tourName.setText(tourEvents.get(position).getTripName());
        holder.tvEventCreateDate.setText(tourEvents.get(position).getCreateDate());
        holder.tvEventStartDate.setText(tourEvents.get(position).getStartDate());
        holder.tvDaysLeft.setText(tourEvents.get(position).getHowDaysLeft());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventListAdapterInterface.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tourEvents.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        @BindView(R.id.tvTName)
        TextView tourName;

        @BindView(R.id.tvEventCreateDate)
        TextView tvEventCreateDate;

        @BindView(R.id.tvEventStartDate)
        TextView tvEventStartDate;

        @BindView(R.id.imgBtn3dot)
        ImageButton imageButton;

        @BindView(R.id.tvDaysLeft)
        TextView tvDaysLeft;


        String TAG = "MyViewHolder";

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imageButton.setOnClickListener(this);
            //itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            Log.d(TAG, "onclick" + getAdapterPosition());
            showPopUpMenu(view);
        }

        private void showPopUpMenu(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.rv_row_item_menu);
            popupMenu.setForceShowIcon(true);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete:

                    return true;

                case R.id.update:
                    Log.d(TAG, "onMenuItemClick: pop up delete");
                    return true;

                default:
                    return false;
            }
        }
    }




}
