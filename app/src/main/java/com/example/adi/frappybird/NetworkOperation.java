package com.example.adi.frappybird;

import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by adi on 05/03/17.
 */

public class NetworkOperation extends AsyncTask {

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public NetworkOperation(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        Document doc = null;
        try {
            doc = Jsoup.connect(params.toString()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getUniversityStringsFromWebDocument(doc)

        ArrayList<String> universityList = new ArrayList();
        return universityList;
    }

    private ArrayList getUniversityStringsFromWebDocument(Document doc) {
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        delegate.processFinish(o.toString());
    }
}