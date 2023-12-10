package com.example.zakat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar myToolbar;
    EditText weightEditText;
    EditText typeEditText;
    EditText goldEditText;
    Button btnConvert;
    TextView tvOutput;

    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditText = findViewById(R.id.editTextWeight);
        typeEditText = findViewById(R.id.editTextType);
        goldEditText = findViewById(R.id.editTextGold);
        btnConvert = findViewById(R.id.btnConvert);
        tvOutput = findViewById(R.id.tvOuput);
        btnConvert.setOnClickListener(this);

        btnCalculate = findViewById(R.id.btnclear);
        btnCalculate.setOnClickListener(v -> clearFields());

        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.item_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Please use my application - https://t.co/app");
            startActivity(Intent.createChooser(shareIntent, null));
            return true;
        } else if (itemId == R.id.item_about) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (validateInput()) {
            calculateZakat();
        }
    }

    private void calculateZakat() {
        double weightValue = Double.parseDouble(weightEditText.getText().toString());
        String typeValue = typeEditText.getText().toString();
        double goldValue = Double.parseDouble(goldEditText.getText().toString());

        double totalValue = weightValue * goldValue;
        double zakatPayableValue = (weightValue - getThreshold(typeValue)) * goldValue;
        double zakatAmount = 0.025 * zakatPayableValue;

        String result = "Total Value: RM" + totalValue +
                "\nZakat Payable Value: RM" + zakatPayableValue +
                "\nTotal Zakat: RM" + zakatAmount;

        tvOutput.setText(result);
    }

    private double getThreshold(String type) {
        return type.equalsIgnoreCase("keep") ? 85 : 200;
    }

    private void clearFields() {
        // Clear the EditText fields
        weightEditText.setText("");
        typeEditText.setText("");
        goldEditText.setText("");

        // Clear the error messages
        weightEditText.setError(null);
        typeEditText.setError(null);
        goldEditText.setError(null);

        // Clear the TextView
        tvOutput.setText("");
    }


    private boolean validateInput() {
        boolean isValid = true;

        // Validate weight
        if (TextUtils.isEmpty(weightEditText.getText().toString())) {
            weightEditText.setError("Please enter weight");
            isValid = false;
        }

        // Validate type
        if (TextUtils.isEmpty(typeEditText.getText().toString())) {
            typeEditText.setError("Please enter type");
            isValid = false;
        }

        // Validate gold value
        if (TextUtils.isEmpty(goldEditText.getText().toString())) {
            goldEditText.setError("Please enter gold value");
            isValid = false;
        }

        return isValid;
    }
}
