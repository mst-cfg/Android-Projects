package com.example.siawns.fitnessapp.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.siawns.fitnessapp.R;
import com.example.siawns.fitnessapp.data.Exercise;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivIcon;
        public TextView tvExercise;
        public TextView tvWeight;
        public TextView tvSets;
        public TextView tvReps;
        public Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvExercise = (TextView) itemView.findViewById(R.id.tvExercise);
            tvWeight = (TextView) itemView.findViewById(R.id.tvWeight);
            tvSets = (TextView) itemView.findViewById(R.id.tvSets);
            tvReps = (TextView) itemView.findViewById(R.id.tvReps);
            btnDelete = (Button) itemView.findViewById(R.id.delete_button);
        }
    }

    private List<Exercise> exercisesList;


    public ExercisesAdapter(List<Exercise> exercisesList) {
        this.exercisesList = exercisesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_exercise, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.tvExercise.setText(exercisesList.get(i).getExerciseName());
        viewHolder.tvWeight.setText(exercisesList.get(i).getWeight()+" pounds");
        viewHolder.tvSets.setText(exercisesList.get(i).getSets()+" sets");
        viewHolder.tvReps.setText(exercisesList.get(i).getReps()+" reps per set");
        viewHolder.ivIcon.setImageResource(exercisesList.get(i).getExerciseType().getIconId());
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

    public void addExercise(Exercise Exercise) {
        exercisesList.add(Exercise);
        notifyDataSetChanged();
    }

    public void updateExercise(int index, Exercise Exercise) {
        exercisesList.set(index, Exercise);
        notifyDataSetChanged();
    }

    public void removeItem(int index) {
        exercisesList.get(index).delete();
        exercisesList.remove(index);
        notifyDataSetChanged();
    }

    public Exercise getItem(int i) {
        return exercisesList.get(i);
    }
}
