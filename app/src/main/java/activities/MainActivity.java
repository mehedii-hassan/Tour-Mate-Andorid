package activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fragments.CustomDialogFragment;
import adapters.MyAdapter;

import com.example.tourmate.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import db.DbInterface;
import db.EventDatabase;
import db.EventModalClass;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayoutId)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigationViewId)
    NavigationView navigationView;

    @BindView(R.id.toolBarId)
    Toolbar toolbar;

    @BindView(R.id.btnAddEvent)
    FloatingActionButton button;

    @BindView(R.id.recyclerViewId)
    RecyclerView recyclerView;

    @BindView(R.id.btnIntent)
    Button bIntent;


    private ActionBarDrawerToggle actionBarDrawerToggle;
    //    private androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder;
//    private Dialog alertDialog;
    private static final String TAG = "CreateEvent";
    private EventDatabase db;

    List<EventModalClass> tourEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        db = EventDatabase.getINSTANCE(MainActivity.this);

        tourEvents = db.eventDao().getAllEvent();

        MyAdapter adapter = new MyAdapter(tourEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                CustomDialogFragment fragmentDialog = new CustomDialogFragment();
                fragmentDialog.setDbInterface(new DbInterface() {
                    @Override
                    public void onEventCreate(EventModalClass eventModalClass) {
                        //wait...
                        db.eventDao().insert(eventModalClass);
                        Toast.makeText(MainActivity.this, "successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                fragmentDialog.show(getSupportFragmentManager(), "CustomDialog");

                //showCreateEventDialog();
            }
        });

        bIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EventDetailsActivity.class));
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showCreateEventDialog() {
//        LayoutInflater layoutInflater = getLayoutInflater();
//        View view = layoutInflater.inflate(R.layout.add_an_event2, null);
//
//
//        alertDialog.show();


        // Window window = fragmentDialog.
        //window.setLayout(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);

//        EditText tripName = findViewById(R.id.etTripName);
//        EditText tripDescription = findViewById(R.id.etTripDesc);
//        EditText tripStartLocation = findViewById(R.id.etTripStartLocation);
//        EditText tripDestination = findViewById(R.id.etTripDestination);
//        EditText tripStartDate = findViewById(R.id.etTripStartDate);
//        EditText tripEndDate = findViewById(R.id.etTripEndDate);
//        EditText tripBudget = findViewById(R.id.etTripBudget);
//        Button btnCreateEvent = findViewById(R.id.btnCreateEvent);


//        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EventModalClass eventModalClass = new EventModalClass(tripName.getText().toString(), tripDescription.getText().toString(), tripStartLocation.getText().toString(), tripDestination.getText().toString(), tripStartDate.getText().toString(), tripEndDate.getText().toString(), tripBudget.getText().toString());
//                db.eventDao().insert(eventModalClass);
//                Toast.makeText(MainActivity.this, "successful", Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onclick: tripName: " + tripName.getText().toString());
//                finish();
//
//            }
//        });
    }
}