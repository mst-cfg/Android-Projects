package com.example.siawns.fitnessapp;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.siawns.fitnessapp.adapter.ExercisesAdapter;
import com.example.siawns.fitnessapp.data.Exercise;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    public static final int REQ_CREATE_EXERCISE = 101;
    private CoordinatorLayout layoutContent;
    private ExercisesAdapter exerciseAdapter;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Exercise> exercisesList = Exercise.listAll(Exercise.class);

        exerciseAdapter = new ExercisesAdapter(exercisesList);
        RecyclerView recyclerViewExercises = (RecyclerView) findViewById(R.id.recyclerViewExercises);
        recyclerViewExercises.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewExercises.setAdapter(exerciseAdapter);

        layoutContent = (CoordinatorLayout) findViewById(R.id.layoutContent);
        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateExerciseActivity();
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case R.id.action_add:
                                showCreateExerciseActivity();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.action_about:
                                showSnackBarMessage(getString(R.string.about_message));
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.action_help:
                                showSnackBarMessage(getString(R.string.help_message));
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                        }

                        return false;
                    }
                });

        setUpToolBar();
    }

    private void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menu_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void showCreateExerciseActivity() {
        Intent intentStart = new Intent(MainActivity.this, CreateExerciseActivity.class);
        startActivityForResult(intentStart, REQ_CREATE_EXERCISE);
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == REQ_CREATE_EXERCISE) {
                    Exercise exercise = (Exercise) data.getSerializableExtra(
                            CreateExerciseActivity.KEY_EXERCISE);

                    exercise.save();

                    exerciseAdapter.addExercise(exercise);
                    showSnackBarMessage(getString(R.string.exercise_added_success));
                }
                break;
            case RESULT_CANCELED:
                showSnackBarMessage(getString(R.string.exercise_added_cancel));
                break;
        }
    }

    private void showSnackBarMessage(String message) {
        Snackbar.make(layoutContent, message, Snackbar.LENGTH_LONG).setAction(getString(R.string.cancel_snack_bar), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                showCreateExerciseActivity();
                return true;
            default:
                showCreateExerciseActivity();
                return true;
        }
    }


}
