package adapters;

import android.annotation.SuppressLint;
import android.app.FragmentManagerNonConfig;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmate.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import activities.EventDetailsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import databases.TourEventsDB;
import fragments.AddNewExpenseDialogFragment;
import models.AddExpenseModel;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ExpenseViewHolder> {


    Context context;
    List<AddExpenseModel> expenseList;


    public ExpenseListAdapter(Context context, List<AddExpenseModel> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }


    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_expense_list_item_design,parent,false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {

        AddExpenseModel model = expenseList.get(position);
        int uid = holder.getAdapterPosition();


        holder.tvExpenseAmount.setText(String.valueOf(model.getAmount()));
        holder.tvExpenseComment.setText(model.getComment());


        Date date=new Date(model.getDate());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy hh.mm aa");
        holder.tvCurrentDateAndTime.setText(df2.format(date));

        holder.imgBtnMenu.setOnClickListener(view -> {
            PopupMenu popupMenu=new PopupMenu(context,view);
            popupMenu.inflate(R.menu.rv_row_item_menu);
            popupMenu.setForceShowIcon(true);
            popupMenu.show();

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.delete:
                            // this is to delete the record from room database
                            TourEventsDB.getINSTANCE(context.getApplicationContext())
                                    .expenseDao()
                                    .deleteById(uid);
                            // this is to delete the record from Array List which is the source of recview data
                            expenseList.remove(expenseList.get(uid));

                            //update the fresh list of ArrayList data to recview
                            notifyDataSetChanged();
                            Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.update:
                            AddNewExpenseDialogFragment fragment = new AddNewExpenseDialogFragment();
                            fragment.show(((FragmentActivity)context).getSupportFragmentManager(), "ExpenseDialog");
                            Toast.makeText(context, "Add new expense clicked", Toast.LENGTH_SHORT).show();

                            break;
                    }
                    return false;
                }
            });
        });

    }


    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.tvExpenseAmount)
        TextView tvExpenseAmount;

        @BindView(R.id.tvExpenseComment)
        TextView tvExpenseComment;

         @BindView(R.id.imgBtnMenu)
         AppCompatImageButton imgBtnMenu;

        @BindView(R.id.tvCurrentDateAndTime)
        TextView tvCurrentDateAndTime;


        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
