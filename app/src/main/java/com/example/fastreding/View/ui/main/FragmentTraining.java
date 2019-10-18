package com.example.fastreding.View.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fastreding.R;
import com.example.fastreding.View.Training;
import com.example.fastreding.View.exercise.DifferentWords;
import com.example.fastreding.View.exercise.EvenNumbers;
import com.example.fastreding.View.exercise.Numerical;
import com.example.fastreding.View.exercise.ReadingSpeed;
import com.example.fastreding.View.exercise.SearchLetter;
import com.example.fastreding.View.exercise.Suggestions;
import com.example.fastreding.View.exercise.TableSchulte;
import com.example.fastreding.View.exercise.WordPair;

import java.util.Arrays;
import java.util.Random;

public class FragmentTraining extends Fragment {

    private Button nextExercise;
    private String[] nameExercise;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        nextExercise = (Button) view.findViewById(R.id.nextExercise);
        nextExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startExercise(nextExercise.getText().toString());
                changeExercise();
            }
        });
        nameExercise = getResources().getStringArray(R.array.name_exercise);
        changeExercise();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private int getRandomNumber() {
        Random r = new Random();
        return r.nextInt(8);
    }

    private void changeExercise() {
        nextExercise.setText(nameExercise[getRandomNumber()]);
    }

    private void startExercise(String name) {
        Training activity = (Training) getActivity();
        for (int i = 0; i < nameExercise.length; i++) {
            if (nameExercise[i].equals(name)) {
                activity.startExercise(i);
            }
        }

    }


}
