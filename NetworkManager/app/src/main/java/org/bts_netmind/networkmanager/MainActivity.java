package org.bts_netmind.networkmanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        final ImageButton downloadImage_Btn = (ImageButton) this.findViewById(R.id.imgBtnDownload);
            downloadImage_Btn.setOnClickListener(this);
        this.mImageView = (ImageView) this.findViewById(R.id.imageViewMain);
        final Button readFeed_Btn = (Button) this.findViewById(R.id.btnReadFeed);
            readFeed_Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View whichView)
    {
        // 'ConnectivityManager' answers queries about the state of network connectivity
        ConnectivityManager mConnectionManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 'NetworkInfo' describes the status of a network interface
        NetworkInfo mNetworkInfo = mConnectionManager.getActiveNetworkInfo();
        Log.i(MainActivity.TAG_MAIN_ACTIVITY, mNetworkInfo.getTypeName() + " " + mNetworkInfo.getState().toString());

        // 'isConnected()' handles cases like flaky mobile networks, airplane mode, and restricted background data
        if (mNetworkInfo != null && mNetworkInfo.isConnected())
        {
            if (whichView.getId() == R.id.imgBtnDownload)
            {
                // Do whatever you need
                final String urlString = "http://www.tutorialspoint.com/green/images/logo.png";
                // Querying the 'URL' from an alternative thread
                new MyAlternativeThread().execute(urlString);
            }
            else if (whichView.getId() == R.id.btnReadFeed)
            {
                // The feed to be accessed
                final String urlFeed = "http://www.theguardian.com/international/rss";
                //final String urlFeed = "http://www.revistalatahona.com/feed";
                // Querying the feed 'URL' from an alternative thread
                new MyParsingThread(this).execute(urlFeed);
            }
        }
        else
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Connection  not available");
            Toast.makeText(this, "Connection  not available", Toast.LENGTH_SHORT).show();
        }
    }

    // AsyncTask<TypeOfVarArgParams, ProgressValue, ResultValue>
    private class MyAlternativeThread extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String[] params)
        {
            try
            {
                // The input arguments are fetched in order
                URL myUrl = new URL(params[0]);   // Throws 'MalformedURLException'
                HttpURLConnection myConnection = (HttpURLConnection) myUrl.openConnection();   // Throws 'IOException'
                    myConnection.setRequestMethod("GET");
                    myConnection.setDoInput(true);
                // Starting the query
                myConnection.connect();   // Throws 'IOException'
                    int respCode = myConnection.getResponseCode();   // Throws 'IOException'
                    Log.i(MainActivity.TAG_MAIN_ACTIVITY, "The response is: " + respCode);
                if (respCode == HttpURLConnection.HTTP_OK)
                {
                    InputStream myInStream = myConnection.getInputStream();   // Throws 'IOException'

                    Bitmap myBitmap = BitmapFactory.decodeStream(myInStream);

                    myInStream.close();   // Always close the 'InputStream'

                    return myBitmap;
                }
            }
            catch (MalformedURLException e1) { e1.printStackTrace(); }
            catch (IOException e2) { e2.printStackTrace(); }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            super.onPostExecute(bitmap);

            if (bitmap != null)
                mImageView.setImageBitmap(bitmap);
            else
                Log.i(MainActivity.TAG_MAIN_ACTIVITY, "No image to be loaded");
        }
    }

    private class MyParsingThread extends AsyncTask<String, Void, String>
    {
        private Context threadContext;

        public MyParsingThread(Context mContext) { this.threadContext = mContext; }

        @Override
        protected String doInBackground(String[] params)
        {
            try
            {
                // The input arguments are fetched in order
                URL myUrl = new URL(params[0]);   // Throws 'MalformedURLException'
                HttpURLConnection myConnection = (HttpURLConnection) myUrl.openConnection();   // Throws 'IOException'
                    myConnection.setRequestMethod("GET");
                    myConnection.setDoInput(true);
                // Starting the query
                myConnection.connect();   // Throws 'IOException'
                int respCode = myConnection.getResponseCode();   // Throws 'IOException'
                Log.i(MainActivity.TAG_MAIN_ACTIVITY, "The response is: " + respCode);
                if (respCode == HttpURLConnection.HTTP_OK)
                {
                    InputStream myInStream = myConnection.getInputStream();   // Throws 'IOException'

                    XmlPullParser myXmlParser = Xml.newPullParser();
                        //myXmlParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);   // Throws 'XmlPullParserException'
                        myXmlParser.setInput(myInStream, null);   // Throws 'XmlPullParserException'

                    String fullTitleString = "";
                    int event = myXmlParser.nextTag();
                    // This parsing process is ONLY ensured to be valid for the specific feed accessed
                    while (myXmlParser.getEventType() != XmlPullParser.END_DOCUMENT)
                    {
                        switch (event)
                        {
                            case XmlPullParser.START_TAG:
                                if (myXmlParser.getName().equals("item"))
                                {
                                    myXmlParser.nextTag();
                                    myXmlParser.next();
                                    fullTitleString += "- " + myXmlParser.getText() + "\n";
                                }
                                break;
                        }

                        event = myXmlParser.next();
                    }
                    Log.i(MainActivity.TAG_MAIN_ACTIVITY, fullTitleString);

                    myInStream.close();   // Always close the 'InputStream'

                    return fullTitleString;
                }
            }
            catch (MalformedURLException e1) { e1.printStackTrace(); }
            catch (IOException e2) { e2.printStackTrace(); }
            catch (XmlPullParserException e3) { e3.printStackTrace(); }

            return null;
        }

        @Override
        protected void onPostExecute(String inString)
        {
            super.onPostExecute(inString);

            if (inString != null)
                Toast.makeText(this.threadContext, inString, Toast.LENGTH_LONG).show();
            else
                Log.i(MainActivity.TAG_MAIN_ACTIVITY, "No feed to be loaded");
        }
    }
}
