package com.example.adi.frappybird;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by adi on 05/03/17.
 */

public class NetworkOperation extends AsyncTask<String, Boolean, ArrayList<String>> {
    private Activity rootAct;
    ArrayList<String> universityList = new ArrayList<String>();

    public NetworkOperation(Activity activity){
        this.rootAct = activity;
    }

    @Override
    protected ArrayList doInBackground(String... params) {
        Document doc = null;
        for(int i=1; i<14; i++){
            try {
                doc = Jsoup.connect(params[0]+i).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            universityList.addAll(getUniversityStringsFromWebDocument(doc));
        }


        return universityList;
    }

    private ArrayList getUniversityStringsFromWebDocument(Document doc) {
        ArrayList universityList = new ArrayList<String>();
        for(Element university : doc.select("div.col-sm-9")) {
            String universityName = university.getElementsByTag("a").first().text();
            universityList.add(universityName);
        }
            return universityList;
    }

    @Override
    protected void onPostExecute(ArrayList<String> arrayList) {
        super.onPostExecute(arrayList);
        AutoCompleteTextView textView = (AutoCompleteTextView) rootAct.findViewById(R.id.editText);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootAct, android.R.layout.simple_dropdown_item_1line, arrayList);
        textView.setAdapter(adapter);
    }

}