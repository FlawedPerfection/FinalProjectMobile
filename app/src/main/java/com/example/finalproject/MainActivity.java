package com.example.finalproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
    ListView listViewNews;
    //Making our arraylist to store our News Items from the RSS feed
    ArrayList<NewsItem> newsItemsCBC;


    Toolbar toolbar;
    private URL CbcNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewNews = findViewById(R.id.listViewNews);


        listViewNews.setOnItemClickListener((parent, view, position, id) -> {

            NewsItem newsItem = newsItemsCBC.get(position);
            openShowNewsItemActivity(newsItem);

        });
    }


    private void setSupportActionBar(Toolbar toolbar) {
    }


    //Opens our ShowNewsItemActivity and passes the newsItem instance values the user selected so it can populate the activity
    public void openShowNewsItemActivity(NewsItem newsItem) {
        Intent intent = new Intent(MainActivity.this, ShowNewsItem.class);
        //send our newsItem selected and it's values to the second activity
        intent.putExtra("title", newsItem.getTitle());
        intent.putExtra("description", newsItem.getDescription());
        intent.putExtra("link", newsItem.getLink());
        intent.putExtra("pubDate", newsItem.getPubDate());
        startActivity(intent);
    }

    //to inflate the xml menu file
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar, menu);
        return true;
    }


}

