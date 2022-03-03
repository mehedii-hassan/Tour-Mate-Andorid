package fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;

import com.example.tourmate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import db.DbInterface;
import db.EventDatabase;
import db.EventDatabase_Impl;
import db.EventModalClass;

public class CustomDialogFragment extends DialogFragment {

    @BindView(R.id.etTripName)
    EditText tripName;

    @BindView(R.id.etTripDesc)
    EditText tripDescription;

    @BindView(R.id.etTripStartLocation)
    EditText tripStartLocation;

    @BindView(R.id.etTripDestination)
    EditText tripDestination;

    @BindView(R.id.etTripStartDate)
    EditText tripStartDate;

    @BindView(R.id.etTripEndDate)
    EditText tripEndDate;

    @BindView(R.id.etTripBudget)
    EditText tripBudget;

    @BindView(R.id.btnCreateEvent)
    Button btnCreateEvent;

    private DbInterface dbInterface;

    public void setDbInterface(DbInterface dbInterface) {
        this.dbInterface = dbInterface;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_an_event2, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventModalClass eventModalClass = new EventModalClass(tripName.getText().toString(), tripDescription.getText().toString(), tripStartLocation.getText().toString(), tripDestination.getText().toString(), tripStartDate.getText().toString(), tripEndDate.getText().toString(), tripBudget.getText().toString());

                dbInterface.onEventCreate(eventModalClass);
            }
        });

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
