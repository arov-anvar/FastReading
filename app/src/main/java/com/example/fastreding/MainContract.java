package com.example.fastreding;


import java.util.ArrayList;

public interface MainContract {

    interface ViewExercise {
        void init();
        void exerciseEnd();
    }

    interface Presenter {
        void setResult(int point);
    }

    interface Model {
        void setResult(String tableName, int point);
        ArrayList<Integer> getPastResult(String tableName);
        String getUserName();
        void setUserName(String name);
    }
}
