package com.example.adi.frappybird;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public final static String URL = "https://yocket.in/universities/masters-in-computer-science-engineering/study-in-usa?page=";
    public HashMap<String,String> universityDetailsMap = new HashMap<String,String>();
    private String chosenUnivsity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUniversityList();
    }

    public void searchUniversity(View view) {
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.editText);
        Intent intent = new Intent(this, CollegeDetailActivity.class);
        intent.putExtra("Chosen University Link", universityDetailsMap.get(textView.getText().toString()));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        return true;
    }

    private void getUniversityList() {
        final ArrayList<String> universityList = new ArrayList();
        AsyncTask task = new NetworkOperation(this, new NetworkOperation.AsyncResponse(){

            @Override
            public void processFinish(HashMap<String, String> output){
                //Here you will receive the result fired from async class
                //of onPostExecute(result) method.
                universityDetailsMap = output;
            }

        }).execute(URL);
    }
}




