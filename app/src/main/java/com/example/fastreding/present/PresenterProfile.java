package com.example.fastreding.present;

import android.content.Context;

import com.example.fastreding.R;
import com.example.fastreding.db.Model;

import java.util.ArrayList;
import java.util.Collections;

public class PresenterProfile {

    private Model model;
    private String[] nameExercisesTable;
    private String[] nameExercises;

    public PresenterProfile(Context context) {
        model = new Model(context);
        nameExercisesTable = context.getResources().getStringArray(R.array.name_exercise_table);
        nameExercises = context.getResources().getStringArray(R.array.name_exercise);
    }

    private int getMaxResult(String tableName) {
        ArrayList<Integer> array = model.getPastResult(tableName);//
        if (array == null) return 0;
        return Collections.max(array);
    }

    public String getBestExerciseName() {
        String name = nameExercises[0];
        int max = getMaxResult(nameExercisesTable[0]);
        for (int i = 1; i < nameExercisesTable.length; ++i) {
            if (max < getMaxResult(nameExercisesTable[i])) {
                name = nameExercises[i];
                max = getMaxResult(nameExercisesTable[i]);
            }
        }
        return name;
    }

    private int getMinResult(String tableName) {
        ArrayList<Integer> array = model.getPastResult(tableName);//
        if (array == null) return 0;
        return Collections.min(array);
    }

    public String getWorseExerciseName() {
        String name = nameExercises[0];
        int min = getMinResult(nameExercisesTable[0]);
        for (int i = 1; i < nameExercisesTable.length; ++i) {
            if (min > getMinResult(nameExercisesTable[i])) {
                name = nameExercises[i];
                min = getMaxResult(nameExercisesTable[i]);
            }
        }
        return name;
    }

    public String getMiddleResult() {
        Integer result = 0;
        for (int i = 0; i < nameExercisesTable.length; ++i) {
            result += getMinResult(nameExercisesTable[i]) + getMaxResult(nameExercisesTable[i]);
        }
        result /= 14;
        return result.toString();
    }

    public String getUserName() {
        return model.getUserName();
    }
}
