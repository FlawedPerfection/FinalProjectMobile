package com.example.finalproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class CBCParser extends AppCompatActivity {
    public TextView title;
    public TextView description;
    public TextView link;
    public TextView pubDate;

    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.VISIBLE);

        title = findViewById(R.id.titleText);
        description = findViewById(R.id.descriptionText);
        link = findViewById(R.id.linkText);
        pubDate = findViewById(R.id.pubDateText);


        ListParser castQuery = new ListParser();
        castQuery.execute("http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml");
    }

    class ListParser extends AsyncTask<String, Integer, String> {
        private static final String ACTIVITY_NAME = "CbcParser";
        private String currentTitle;
        private String currentDescription;
        private String currentpubDate;
        private String currentLink;


        @Override
        protected String doInBackground(String... args) {
            try {
                URL url = new URL(args[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream response = urlConnection.getInputStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(response, "UTF-8");

                String iconName = null;
                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        //If you get here, then you are pointing at a start tag
                        if (xpp.getName().equals("title")) {
                            //grabs the text after getting to 'title' tag
                            currentTitle = xpp.getText();
                            publishProgress(25);
                            currentLink = xpp.getText();
                            publishProgress(50);
                            currentDescription = xpp.getText();
                            publishProgress(75);
                            currentpubDate = xpp.getText();
                            publishProgress(75);
                        }
                    }
                    eventType = xpp.next(); //move to the next xml event and store it in a variable
                }

                url = new URL("http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml");


                //opening connection
                urlConnection = (HttpURLConnection) url.openConnection();

                response = urlConnection.getInputStream();


                BufferedReader reader = new BufferedReader(new InputStreamReader(response, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();


                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                String result = sb.toString();


                JSONObject jObject = new JSONObject(result);


                currentTitle = jObject.getString("item");
                publishProgress(100);

                return null;
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "Done";
        }

        public void onProgressUpdate(Integer... args) {
            progressBar.setProgress(args[0]);
        }

        public void onPostExecute(String fromDoInBackground) {
            Log.e(ACTIVITY_NAME, "In onPostExecute");

            title.setText("The current temperature is: " + currentTitle );
            description.setText("The minimum temperature today is: " + currentDescription);
            link.setText("The maximum temperature today is: " + currentLink);
            pubDate.setText("The current UV today is: " + currentpubDate);

            progressBar.setVisibility(View.INVISIBLE);

        }

    }
}


