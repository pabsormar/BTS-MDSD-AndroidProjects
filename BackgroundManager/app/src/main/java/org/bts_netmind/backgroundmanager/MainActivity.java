package org.bts_netmind.backgroundmanager;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    private ProgressBar mProgressBar;
    private android.os.Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startThread_Btn = (Button) this.findViewById(R.id.btnStartThread);
        startThread_Btn.setOnClickListener(this);
        final Button startAsyncTask_Btn = (Button) this.findViewById(R.id.btnStartAsyncTask);
        startAsyncTask_Btn.setOnClickListener(this);
        this.mProgressBar = (ProgressBar) this.findViewById(R.id.progressBar);
            this.mProgressBar.setProgress(0);
        // If using a 'Handler' to update a thread instead of the 'Activity.runOnUiThread()' method
        this.mHandler = new Handler();

        // The next line reports a runtime error (ANR) unless the operation is performed in a worker thread
        //this.sleepForAWhile(20);
    }

    // Method simulating a long-time processing task
    private void sleepForAWhile(int numSeconds) {
        long endTime = System.currentTimeMillis() + (numSeconds * 1000);

        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Sleeping...");
                    this.wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View whichView) {
        mProgressBar.setProgress(0);

        if (whichView.getId() == R.id.btnStartThread) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Thread started");
                    runOnUiThread(new Runnable() {
                    //mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Sleeping...", Toast.LENGTH_SHORT).show();
                        }
                    });
                    sleepForAWhile(2);
                }
            }).start();
        } else if (whichView.getId() == R.id.btnStartAsyncTask) {
            new MyAsyncTask(this).execute(1);
        }
    }

    private class MyAsyncTask extends AsyncTask<Integer, Integer, String> {
        private Context mContext;

        public MyAsyncTask(Context anyContext) {
            this.mContext = anyContext;
        }

        @Override
        protected String doInBackground(Integer... params) {
            for (int idx = 1; idx <= 5; idx++) {
                sleepForAWhile(params[0]);
                publishProgress(idx * 20);
            }
            return "AsyncTask finished";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String retString) {
            super.onPostExecute(retString);
            Toast.makeText(this.mContext, retString, Toast.LENGTH_SHORT).show();
        }
    }
}
