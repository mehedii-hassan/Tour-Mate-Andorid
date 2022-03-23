package fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tourmate.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import interfaces.EventInterface;
import models.CreateEventModel;

public class CustomDialogFragment extends DialogFragment {

    @BindView(R.id.etTripName)
    EditText tripName;

    @BindView(R.id.etTripDesc)
    EditText tripDescription;

    @BindView(R.id.etTripStartLocation)
    EditText tripStartLocation;

    @BindView(R.id.etTripDestination)
    EditText tripDestination;


    @BindView(R.id.tvTripStartDate)
    TextView tripStartDate;

    @BindView(R.id.tvTripEndDate)
    TextView tripEndDate;

    @BindView(R.id.etTripBudget)
    EditText tripBudget;

    @BindView(R.id.btnCreateEvent)
    Button btnCreateEvent;

    DatePickerDialog datePickerDialog;
    private EventInterface eventInterface;

    public void setDbInterface(EventInterface eventInterface) {
        this.eventInterface = eventInterface;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_an_event, null);
        ButterKnife.bind(this, view);

        MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        tripStartDate.setOnClickListener(view1 -> {
            DatePicker datePicker = new DatePicker(getContext());
            int currentDay = datePicker.getDayOfMonth();
            int currentMonth = (datePicker.getMonth() + 1);
            int currentYear = datePicker.getYear();


            datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    tripStartDate.setText(i2 + "-" + (i1 + 1) + "-" + i);
                }
            }, currentDay, currentMonth, currentYear);
            datePickerDialog.show();

            Toast.makeText(getContext(), "date clicked", Toast.LENGTH_SHORT).show();
        });


        tripEndDate.setOnClickListener(view12 -> {

            materialDatePicker.show(getActivity().getSupportFragmentManager(), "Select date");
            materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                @Override
                public void onPositiveButtonClick(Long selection) {
                    tripEndDate.setText(materialDatePicker.getHeaderText());
                }
            });


        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnCreateEvent.setOnClickListener(view1 -> {
            CreateEventModel createEventModel = new CreateEventModel(tripName.getText().toString(), tripDescription.getText().toString(), tripStartLocation.getText().toString(), tripDestination.getText().toString(), tripStartDate.getText().toString(), tripEndDate.getText().toString(), tripBudget.getText().toString(), "12-3-2022", "20-3-2021", "4 days left");

            eventInterface.onEventCreate(createEventModel);
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
