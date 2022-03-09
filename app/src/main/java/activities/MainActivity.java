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
import android.view.View;
import android.widget.Toast;

import adapters.EventListAdapter;
import adapters.EventListAdapterInterface;
import fragments.CustomDialogFragment;

import com.example.tourmate.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import db.interfaces.EventInterface;
import db.databases.TourEventsDB;
import db.models.CreateEventModel;

public class MainActivity extends AppCompatActivity {

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


    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TourEventsDB db;
    List<CreateEventModel> tourEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //set toolbar effect---------------------------
        setSupportActionBar(toolbar);

        //set toggle icon------------------------------
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        db = TourEventsDB.getINSTANCE(MainActivity.this);
        tourEvents = db.eventDao().getAllEvent();

        EventListAdapter adapter = new EventListAdapter(this, tourEvents, new EventListAdapterInterface() {
            @Override
            public void onItemClick() {

                startActivity(new Intent(MainActivity.this,EventDetailsActivity.class));
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                CustomDialogFragment fragmentDialog = new CustomDialogFragment();

                fragmentDialog.setDbInterface(new EventInterface() {
                    @Override
                    public void onEventCreate(CreateEventModel createEventModel) {
                        //wait...
                        db.eventDao().insert(createEventModel);
                        Toast.makeText(MainActivity.this, "successfully inserted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        finish();

                    }
                });

                fragmentDialog.show(getSupportFragmentManager(), "CustomDialog");
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
}