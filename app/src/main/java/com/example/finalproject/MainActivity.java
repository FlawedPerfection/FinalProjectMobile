package com.example.finalproject;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ListView listViewNews;
    //Making our arraylist to store our News Items from the RSS feed
    ArrayList<String> titles;
    ArrayList<String> links;

    Toolbar toolbar;

    private URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.myToolbar);

        listViewNews = findViewById(R.id.listViewNews);
        titles = new ArrayList<>();
        links = new ArrayList<>();

        listViewNews.setOnItemClickListener((adapterView, view, position, l) -> {
            Uri uri = Uri.parse(links.get(position));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            Snackbar.make(listViewNews, "you clicked on list item "+ position, Snackbar.LENGTH_LONG).show();
        });



        new ProcessInBackground().execute();
    }


    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }


    }

    //
    public class ProcessInBackground extends AsyncTask<Integer, Void, Exception> {

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Loading rss feed");
            progressDialog.show();
        }

        @Override
        protected Exception doInBackground(Integer... params) {
            try {
                URL url = new URL("https://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                //not providing support for namespaces
                factory.setNamespaceAware(false);

                //creating a new instance of an xml pull parser to extract data from our XML document
                XmlPullParser xpp = factory.newPullParser();

                //setting the encoding type to be read through the input stream that creates a new connection
                xpp.setInput(getInputStream(url), "UTF_8");


                boolean insideItem = false;
                int eventType = xpp.getEventType(); //returns the tag of the current event

                //gonna run through all the elements in the document until its not the end of the document
                //when we reach the end, while loop stops

                while (eventType != XmlPullParser.END_DOCUMENT)
                {
                    //If start tag, whats the name of the tag?
                    if (eventType == XmlPullParser.START_TAG) {

                        //If the tag is an item, we are now inside where we want to be
                        if (xpp.getName().equalsIgnoreCase("item")){
                        insideItem = true;
                    }
                    else if (xpp.getName().equalsIgnoreCase("title")){

                        if (insideItem) {
                            titles.add(xpp.nextText());
                        }
                    } else if (xpp.getName().equalsIgnoreCase("link")){
                        if (insideItem){
                            links.add(xpp.nextText());
                        }
                    }
                }
                else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item"))
                {
                    insideItem=false;
                }
                    eventType=xpp.next();
                }

            } catch (MalformedURLException e) {

                exception = e;
            } catch (XmlPullParserException e) {
                exception = e;
            } catch (IOException e) {
                exception = e;
            }
            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, titles);
            listViewNews.setAdapter(adapter);

            progressDialog.dismiss();
        }
    }


}




