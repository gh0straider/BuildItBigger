package com.gh0straider.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.joke.endpoint.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created on 8/1/17 by gh0straider
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private com.gh0straider.builditbigger.OnTaskCompleted mListener;

    public EndpointsAsyncTask (com.gh0straider.builditbigger.OnTaskCompleted listener) {
        this.mListener = listener;
    }

    @Override
    protected String doInBackground(Void... params){
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbigger-140415.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        mListener.onTaskCompleted(result);
    }
}
