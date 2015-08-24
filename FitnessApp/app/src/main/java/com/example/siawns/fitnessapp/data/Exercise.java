package com.example.siawns.fitnessapp.data;

import com.example.siawns.fitnessapp.R;
import com.orm.SugarRecord;
import java.io.Serializable;
import java.util.Date;

public class Exercise extends SugarRecord<Exercise> implements Serializable {
    public enum ExerciseType {
        CORE(0, R.drawable.woman_abs),
        BICEP(1, R.drawable.bicep),
        SHOULDERS(2, R.drawable.shoulders),
        CHEST(3, R.drawable.pushups),
        QUADS(4, R.drawable.quads),
        HAMSTRINGS(5, R.drawable.hamstrings),
        GLUTES(6, R.drawable.glutes),
        CALVES(7, R.drawable.calves),
        FULLBODY(8, R.drawable.full_body);


        private int value;
        private int iconId;

        private ExerciseType(int value, int iconId) {
            this.value = value;
            this.iconId = iconId;
        }

        public int getValue() {
            return value;
        }

        public int getIconId() {
            return iconId;
        }

        public static ExerciseType fromInt(int value) {
            for (ExerciseType e : ExerciseType.values()) {
                if (e.value == value) {
                    return e;
                }
            }
            return FULLBODY;
        }
    }

    public Exercise() {

    }

    public Exercise(String exerciseName, Integer sets, Integer reps, Integer weight, ExerciseType exerciseType) {
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.exerciseType = exerciseType;
    }

    private String exerciseName;
    private Integer sets;
    private Integer reps;
    private Integer weight;
    private ExerciseType exerciseType;

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String ExerciseName) {
        this.exerciseName = ExerciseName;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(ExerciseType ExerciseType) {
        this.exerciseType = ExerciseType;
    }
}