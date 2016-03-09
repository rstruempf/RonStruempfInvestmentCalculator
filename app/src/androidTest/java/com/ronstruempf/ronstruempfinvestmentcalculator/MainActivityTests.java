package com.ronstruempf.ronstruempfinvestmentcalculator;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Test MainActivity
 *
 * Created by Ron on 3/8/2016.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTests() {
        super(MainActivity.class);
    }

    /*
     * Utility functions
     */

    /**
     * Set name edit text field to Jake
     *
     * @param id R.id of an EditText field
     * @param value String value to set
     */
    private void setEditTextTo(int id, String value) {
        MainActivity activity = getActivity();
        final EditText editText = (EditText)activity.findViewById(id);
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editText.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();
        hold(500);
        getInstrumentation().sendStringSync(value);
    }

    /**
     * Sleep for a given number of milliseconds
     *
     * @param tm Time to sleep
     */
    private void hold(int tm) {
        try {
            Thread.sleep(tm);
        } catch (InterruptedException e) {
            // don't do anything
        }
    }

    /**
     * Click a button on the form
     *
     * @param id R.id of Button to click
     */
    private void clickAButton(int id) {
        MainActivity activity = getActivity();
        Button greetButton = (Button)activity.findViewById(id);
        TouchUtils.clickView(this, greetButton);
    }


    /*
     * Tests
     */

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testWhenSetPaymentShouldMatchSetString()
    {
        setEditTextTo(R.id.payment_edit_text, "1000");
        final EditText editText = (EditText)getActivity().findViewById(R.id.payment_edit_text);
        String actualText = editText.getText().toString();
        assertEquals("1000", actualText);
    }

    public void testWhenSetInterestShouldMatchSetString()
    {
        setEditTextTo(R.id.interest_edit_text, "0.03");
        final EditText editText = (EditText)getActivity().findViewById(R.id.interest_edit_text);
        String actualText = editText.getText().toString();
        assertEquals("0.03", actualText);
    }

    public void testWhenSetPeriodsToDecimalShouldNotMatchInputString()
    {
        setEditTextTo(R.id.periods_edit_text, "0.03");
        final EditText editText = (EditText)getActivity().findViewById(R.id.periods_edit_text);
        String actualText = editText.getText().toString();
        assertEquals("003", actualText);
    }

    public void testWhenClickCalculateAnswerShouldBe11463_88()
    {
        setEditTextTo(R.id.payment_edit_text, "1000");
        setEditTextTo(R.id.interest_edit_text, "0.03");
        setEditTextTo(R.id.periods_edit_text, "10");
        clickAButton(R.id.calculate_button);
        final TextView fvText = (TextView)getActivity().findViewById(R.id.future_value_text);
        String answer = fvText.getText().toString();
        assertEquals("11463.88", answer);
    }

}
