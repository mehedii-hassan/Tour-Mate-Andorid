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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import adapters.EventListAdapter;
import fragments.CreateEventDialog;
import interfaces.EventListAdapterInterface;

import com.example.tourmate.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import interfaces.EventInterface;
import databases.TourEventsDB;
import models.CreateEventModel;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawerLayoutId)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigationViewId)
    NavigationView navigationView;

    @BindView(R.id.toolBarId)
    Toolbar toolbar;

    @BindView(R.id.btnAddEvent)
    FloatingActionButton btnCreateEvent;

    @BindView(R.id.recyclerViewId)
    RecyclerView recyclerView;

    @BindView(R.id.txtNoItem)
    TextView txtNoItem;


    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TourEventsDB db;
    List<CreateEventModel> tourEvents;
    private androidx.appcompat.app.AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //show toolbar effect---------------------------
        setSupportActionBar(toolbar);

        //set toggle icon------------------------------
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);


        db = TourEventsDB.getINSTANCE(MainActivity.this);
        tourEvents = db.eventDao().getAllEvent();

        EventListAdapter adapter = new EventListAdapter(this, tourEvents, new EventListAdapterInterface() {
            @Override
            public void onItemClick(int position) {
                CreateEventModel model = tourEvents.get(position);
                Intent intent = new Intent(MainActivity.this, EventDetailsActivity.class);
                intent.putExtra("model", model);
                startActivity(intent);
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                CreateEventDialog fragmentDialog = new CreateEventDialog();

                fragmentDialog.setDbInterface(new EventInterface() {
                    @Override
                    public void onEventCreate(CreateEventModel createEventModel) {
                        //wait...
                        db.eventDao().insert(createEventModel);
                        Toast.makeText(MainActivity.this, "successfully inserted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        finish();

                    }
                });

                fragmentDialog.show(getSupportFragmentManager(), "CustomDialog");
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (tourEvents.size() > 0) {
            txtNoItem.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            txtNoItem.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miHome:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.miNearby:
                startActivity(new Intent(this, NearbyActivity.class));
                break;
            case R.id.miWeather:
                startActivity(new Intent(this, WeatherActivity.class));
                break;


        }
        return false;

    }

//    @Override
//    public void onBackPressed() {
//        showAlertDialog();
//
//    }

    private void showAlertDialog() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_alert_dialog, null);

        alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this).setView(view).create();
        alertDialog.show();

        Button buttonYes = view.findViewById(R.id.btnYes);
        Button buttonNo = view.findViewById(R.id.btnNo);

        buttonYes.setOnClickListener(view1 -> finish());
        buttonNo.setOnClickListener(view12 -> alertDialog.cancel());

    }
}