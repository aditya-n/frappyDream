package com.example.adi.frappybird;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adi on 05/03/17.
 */

public class NetworkOperation extends AsyncTask<String, Boolean, ArrayList<String>> {
    private Activity rootAct;
    public Map<String,String> universityDetailsMap = new HashMap<String,String>();
    ArrayList<String> universityList = new ArrayList<String>();

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public NetworkOperation(Activity activity, AsyncResponse delegate){
        this.rootAct = activity;
        this.delegate = delegate;
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

    private ArrayList getUniversityStringsFromWebDocument(Document document) {
        ArrayList universityList = new ArrayList<String>();
        for(Element university : document.select("div.col-sm-9")) {
            String universityName = university.getElementsByTag("a").first().text();
            String collegeDetailLink = university.select("a").first().attr("href");
            universityList.add(universityName);
            universityDetailsMap.put(universityName, collegeDetailLink);
        }
            return universityList;
    }

    @Override
    protected void onPostExecute(ArrayList<String> arrayList) {
        super.onPostExecute(arrayList);
        AutoCompleteTextView textView = (AutoCompleteTextView) rootAct.findViewById(R.id.editText);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootAct, android.R.layout.simple_dropdown_item_1line, arrayList);
        textView.setAdapter(adapter);
        delegate.processFinish(null);
    }

    public String getUniversityDetailHTML(String universityName){
        return null;
    }
}