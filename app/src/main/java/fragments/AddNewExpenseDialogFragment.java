package fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.DialogFragment;

import com.example.tourmate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import databases.TourEventsDB;
import models.AddExpenseModel;

public class AddNewExpenseDialogFragment extends DialogFragment implements View.OnClickListener {

    @BindView(R.id.etEnterAmount)
    AppCompatEditText amount;

    @BindView(R.id.etComment)
    AppCompatEditText comment;

    @BindView(R.id.btnSave)
    Button btnSave;

    @BindView(R.id.btnCancel)
    Button btnCancel;

   // private EventInterface eventInterface;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_new_expense, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                int amountNew = Integer.parseInt(amount.getText().toString().trim());
                String commentString = comment.getText().toString().trim();
                AddExpenseModel expenseModel = new AddExpenseModel(amountNew, commentString, System.currentTimeMillis());

                TourEventsDB.getINSTANCE(getContext())
                        .expenseDao()
                        .insert(expenseModel);
                Toast.makeText(getContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }

    //custom dialogFragment width set maximize----------------------
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}
