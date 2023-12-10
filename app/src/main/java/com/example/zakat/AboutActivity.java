package com.example.zakat;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    Toolbar aboutToolbar;
    TextView name;
    TextView group;
    TextView stuid;
    TextView code;
    TextView linkText; // Corrected variable name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutToolbar = findViewById(R.id.about_toolbar);
        setSupportActionBar(aboutToolbar);

        name = findViewById(R.id.name);
        group = findViewById(R.id.group);
        stuid = findViewById(R.id.stuid);
        code = findViewById(R.id.code);
        linkText = findViewById(R.id.linkText); // Corrected variable name

        // Applying LinkMovementMethod to make the link clickable
        linkText.setMovementMethod(LinkMovementMethod.getInstance());

        // Check if getSupportActionBar() returns a non-null value before calling setTitle
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("About");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Finish the current activity and go back
            return true; // Return true to indicate that the event has been handled
        }
        return super.onOptionsItemSelected(item);
    }
}
