package com.example.adi.frappybird;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public final static String URL = "https://yocket.in/universities/masters-in-computer-science-engineering/study-in-usa?page=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUniversityList();
    }

    public void searchUniversity(View view) {
        //On clicking search button
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        return true;
    }

    private void getUniversityList() {
        final ArrayList<String> universityList = new ArrayList();
        AsyncTask task = new NetworkOperation(this).execute(URL);
    }
}




