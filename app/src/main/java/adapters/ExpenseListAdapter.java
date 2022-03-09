package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmate.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import db.models.AddExpenseModel;

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
        View view = LayoutInflater.from(context).inflate(R.layout.expense_list_item,parent,false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        int amount = expenseList.get(position).getAmount();
        String comment = expenseList.get(position).getComment();

        holder.tvExpenseAmount.setText(String.valueOf(amount));
        holder.tvExpenseComment.setText(comment);

    }


    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvExpenseAmount)
        TextView tvExpenseAmount;

        @BindView(R.id.tvExpenseComment)
        TextView tvExpenseComment;

         @BindView(R.id.imgBtnMenu)
         AppCompatImageButton imgBtnMenu;


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
    }
}
