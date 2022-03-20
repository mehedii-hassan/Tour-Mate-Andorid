package adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmate.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

        final AddExpenseModel model = expenseList.get(position);

        holder.tvExpenseAmount.setText(String.valueOf(model.getAmount()));
        holder.tvExpenseComment.setText(model.getComment());


        Date date=new Date(model.getDate());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy hh.mm aa");
        holder.tvCurrentDateAndTime.setText(df2.format(date));

    }


    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,PopupMenu.OnMenuItemClickListener {

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
           imgBtnMenu.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            showPopUpMenu(view);
        }

        private void showPopUpMenu(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.rv_row_item_menu);
            popupMenu.setForceShowIcon(true);
            //popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.miPopUpDelete:

                    return true;

                case R.id.miPopUpUpdate:
                    return true;

                default:
                    return false;
            }
        }
    }
}
