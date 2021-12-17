package com.example.finalproject;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import androidx.appcompat.widget.Toolbar;
        import android.os.Bundle;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.androidlabs", Context.MODE_PRIVATE);
        String email = prefs.getString("Email", "");
        EditText text = findViewById(R.id.enterEmail);
        text.setText(email);

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(click ->
        {
            Intent goToProfile = new Intent(this, ToolbarActivity.class);
            goToProfile.putExtra("EMAIL", text.getText().toString());
            startActivityForResult(goToProfile, 456);
            Toast.makeText(getBaseContext(), "You've logged in" , Toast.LENGTH_SHORT ).show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = this.getSharedPreferences(
                "Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        EditText email = findViewById(R.id.enterEmail);
        edit.putString("Email", email.getText().toString());
        edit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }





}
