package com.example.siawns.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.siawns.fitnessapp.data.Exercise;
import com.example.siawns.fitnessapp.R;

import java.util.Date;


public class CreateExerciseActivity extends AppCompatActivity {
    public static final String KEY_EXERCISE = "KEY_EXERCISE";

    private Spinner spinnerExerciseType;
    private CoordinatorLayout layoutContent;
    private EditText etExercise;
    private EditText etExerciseSets;
    private EditText etExerciseReps;
    private EditText etExerciseWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);
        layoutContent = (CoordinatorLayout) findViewById(R.id.layoutContent);
        spinnerExerciseType = (Spinner) findViewById(R.id.spinnerExerciseType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercisetypes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExerciseType.setAdapter(adapter);

        etExercise = (EditText) findViewById(R.id.etExerciseName);
        etExerciseSets = (EditText) findViewById(R.id.etExerciseSets);
        etExerciseReps = (EditText) findViewById(R.id.etExerciseReps);
        etExerciseWeight = (EditText) findViewById(R.id.etExerciseWeight);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveExercise();
            }
        });
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private void saveExercise() {
        if (!isEmpty(etExercise) && !isEmpty(etExerciseReps) &&
                !isEmpty(etExerciseSets) && !isEmpty(etExerciseWeight)) {
            Intent intentResult = new Intent();
            intentResult.putExtra(KEY_EXERCISE,
                    new Exercise(etExercise.getText().toString(),
                            Integer.parseInt(etExerciseSets.getText().toString()),
                            Integer.parseInt(etExerciseReps.getText().toString()),
                            Integer.parseInt(etExerciseWeight.getText().toString()),
                            Exercise.ExerciseType.fromInt(spinnerExerciseType.getSelectedItemPosition())));
            setResult(RESULT_OK, intentResult);
            finish();
        }
        else {
            Toast.makeText(this, R.string.error_create_exercise, Toast.LENGTH_LONG).show();
        }
    }
}