package com.example.fastreding;

import android.view.View;

public interface MainContract {

    interface ViewExercise {
        void init();
        void preparationToStart();
        void startExercise();
        void checkAnswer(View view);
        int changeLevel(int nowLevel, boolean lastAnswer);
        void exerciseEnd();
    }

    interface Presenter {
        void onDestroy();
    }

    interface Model {

    }
}
