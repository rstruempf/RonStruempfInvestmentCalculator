package com.ronstruempf.ronstruempfinvestmentcalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.ronstruempf.ronstruempfinvestmentcalculator.model.InvestmentCalculator;

import java.text.NumberFormat;
import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity {
    private InvestmentCalculator calculator = new InvestmentCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calcButtonClicked(View view) {
        // clear error message
        errorMessage("", null);
        EditText paymentEditText = (EditText) findViewById(R.id.payment_edit_text);
        String paymentText = paymentEditText.getText().toString();
        if (paymentText.length() == 0) {
            errorMessage("Payment required", paymentEditText);
            return;
        }
        EditText interestEditText = (EditText) findViewById(R.id.interest_edit_text);
        String interestText = interestEditText.getText().toString();
        if (interestText.length() == 0) {
            errorMessage("Interest Rate required", interestEditText);
            return;
        }
        EditText periodsEditText = (EditText) findViewById(R.id.periods_edit_text);
        String periodsText = periodsEditText.getText().toString();
        if (periodsText.length() == 0) {
            errorMessage("Number of Periods required", periodsEditText);
            return;
        }
        double payment;
        double interest;
        int periods;
        try {
            payment = Double.parseDouble(paymentText);
        }
        catch (NumberFormatException ex) {
            errorMessage("Payment invalid", paymentEditText);
            return;
        }
        try {
            interest = Double.parseDouble(interestText);
        }
        catch (NumberFormatException ex) {
            errorMessage("Interest invalid", interestEditText);
            return;
        }
        try {
            periods = Integer.parseInt(periodsText);
        }
        catch (NumberFormatException ex) {
            errorMessage("Periods invalid", periodsEditText);
            return;
        }
        if (periods <= 0) {
            errorMessage("Must make at least one payment", periodsEditText);
            return;
        }
        double answer = calculator.futureValue(payment, interest, periods);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String answerText = formatter.format(answer);
        TextView answerTextView = (TextView)findViewById(R.id.future_value_text);
        answerTextView.setText(answerText);
    }

    private void errorMessage(String text, View view) {
        TextView answerTextView = (TextView)findViewById(R.id.error_message);
        answerTextView.setText(text);
        if (view != null) {
            view.requestFocus();
        }
    }
}
