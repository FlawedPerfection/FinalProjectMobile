package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ToolbarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_activity);
        //getting toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        //this gives me the drawer
        DrawerLayout drawer = findViewById(R.id.myDrawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navView = findViewById(R.id.sideNavBar);
        navView.setNavigationItemSelectedListener(this);

        /*ImageView wavingguyIcon = findViewById(R.id.guywaving);

        wavingguyIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "sup", Toast.LENGTH_SHORT ).show();
            }});

        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_bar_menu, menu);

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.search:
                Intent intent = new Intent(this, RssActivity.class);
                startActivityForResult(intent, 456);
                break;

                /*case R.id.home:
                Intent intent = new Intent(this, RssActivity.class);
                startActivityForResult(intent, 456);
                break;

                case R.id.note_taker:
                Intent intent = new Intent(this, RssActivity.class);
                startActivityForResult(intent, 456);
                break;

                case R.id.favorites:
                Intent intent = new Intent(this, RssActivity.class);
                startActivityForResult(intent, 456);
                break;*/
        }











        return false;
    }
}
