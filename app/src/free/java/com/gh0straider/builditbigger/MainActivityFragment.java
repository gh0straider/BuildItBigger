package com.gh0straider.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.joke.JokeActivity;


/**
 * A placeholder fragment
 */
public class MainActivityFragment extends Fragment implements OnTaskCompleted{
    Button mJokeButton;
    ProgressBar mProgressBar;
    String mResult;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mJokeButton = (Button) root.findViewById(R.id.btn_joke);

        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
                launchActivity();
            }
        });

        return root;
    }

    public void loadData() {
        mResult = null;
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
        endpointsAsyncTask.execute();
    }

    @Override
    public void onTaskCompleted(String result) {
        mResult = result;
        launchActivity();
    }

    public void launchActivity() {
            // Data is ready
            if (mResult != null) {
                Intent intent = new Intent(getActivity(), JokeActivity.class);
                intent.putExtra(JokeActivity.JOKE_KEY, mResult);
                mProgressBar.setVisibility(View.GONE);
                startActivity(intent);
            // AsyncTask is not finish
            } else {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }
}
