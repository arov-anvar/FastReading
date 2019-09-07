package com.example.fastreding;

import android.view.View;

public interface MainContract {

    interface ViewExercise {
        void init();
//        void preparationToStart();
//        void startExercise();
//        void checkAnswer(View view);
        void exerciseEnd();
    }

    interface Presenter {
        void setResult(int point);
    }

    interface Model {

    }
}
