package com.mohamedslama.internetchecker;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckInternet extends AsyncTask<Void, Integer, Boolean> {

    WeakReference<Context> context;
    AsyncResponse response;


    public CheckInternet(Context context, AsyncResponse response) {
        this.context = new WeakReference<>(context);
        this.response = response;
    }


    @Override
    protected Boolean doInBackground(Void... params) {

        ConnectivityManager cm =
                (ConnectivityManager) context.get().getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();


        if (isConnected) {
            try {
                HttpURLConnection urlc = (HttpURLConnection)
                        (new URL("https://google.com/")
                                .openConnection());
                urlc.setRequestProperty("User-Agent", "Android");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                if (urlc.getResponseCode() == 200) {
                    return true;
                }

            } catch (IOException e) {
                Log.e("INTERNET", "Error checking internet connection");
                return false;
            }
        } else {
            Log.d("INTERNET", "No network available!");
            return false;
        }


        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        response.processFinish(result);

    }


}