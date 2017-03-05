package com.example.adi.frappybird;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.adi.frappybird.MESSAGE";
    public final static String URL = "https://yocket.in/universities/masters-in-computer-science-engineering/study-in-usa";
    private static final String[] UNIVERSITIES = new String[] {
            "UCSD", "UCI", "Stony", "NCSU", "Columbia", "USC"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.editText);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, UNIVERSITIES);
        textView.setAdapter(adapter);
    }

    public void searchUniversity(View view) {
        //Intent intent = new Intent(this, ProfileActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivity(intent);
        getUniversityList(URL);
        Doucment doc;
        AsyncTask task = new NetworkOperation(new NetworkOperation.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                doc = output;
            }
        }).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        return true;
    }


/*    private void getUniversityList(String url){
        final TextView mTextView = (TextView) findViewById(R.id.textView2);
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mTextView.setText("Response is: "+ response.substring(0,500));
                        try {
                            getListFromHTML(response);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }

    public void getListFromHTML(String httpResponse) throws IOException {
        final TextView mTextView = (TextView) findViewById(R.id.textView2);
        Document doc = Jsoup.connect(URL).get();
        StringBuffer buffer  = new StringBuffer();
        buffer.append("Title: " + doc.title() + "rn");


        mTextView.setText(buffer);
    }
*/


}
