package adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmate.R;

import java.util.List;

import activities.EventDetailsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import db.EventModalClass;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    List<EventModalClass> tourEvents;

    public MyAdapter(List<EventModalClass> tourEvents) {
        this.tourEvents = tourEvents;
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
        holder.tourDestination.setText(tourEvents.get(position).getTripDestination());
        holder.tourBudget.setText(tourEvents.get(position).getTripBudget());
        holder.tourStartDate.setText(tourEvents.get(position).getTripStartDate());
        holder.tourEndDate.setText(tourEvents.get(position).getTripEndDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

        @BindView(R.id.tvTDestination)
        TextView tourDestination;

        @BindView(R.id.tvTBudget)
        TextView tourBudget;

        @BindView(R.id.imgBtn3dot)
        ImageButton imageButton;

        @BindView(R.id.tvTStartDate)
        TextView tourStartDate;

        @BindView(R.id.tvTEndDate)
        TextView tourEndDate;

        String TAG = "MyViewHolder";

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imageButton.setOnClickListener(this);

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
                case R.id.miPopUpDelete:

                    return true;

                case R.id.miPopUpUpdate:
                    Log.d(TAG, "onMenuItemClick: pop up delete");
                    return true;

                default:
                    return false;
            }
        }
    }


}
