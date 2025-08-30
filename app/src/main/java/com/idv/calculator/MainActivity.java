package com.idv.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {

    // declaring required variables/buttons
    String display_string = " ";
    char last_character;
    Button button_c, button_left_bracket, button_right_bracket, button_division;
    Button button_seven, button_eight, button_nine, button_multiply;
    Button button_four, button_five, button_six, button_minus;
    Button button_one, button_two, button_three, button_plus;
    Button button_doublezero, button_zero, button_dot, button_equal;
    ImageButton button_backspace;
    TextView txtDisplay;
    int open_bracket_counter, close_bracket_counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing the buttons
        button_c = findViewById(R.id.button_c);
        button_left_bracket = findViewById(R.id.button_left_bracket);
        button_right_bracket = findViewById(R.id.button_right_bracket);
        button_division = findViewById(R.id.button_division);
        button_seven = findViewById(R.id.button_seven);
        button_eight = findViewById(R.id.button_eight);
        button_nine = findViewById(R.id.button_nine);
        button_multiply = findViewById(R.id.button_multiply);
        button_four = findViewById(R.id.button_four);
        button_five = findViewById(R.id.button_five);
        button_six = findViewById(R.id.button_six);
        button_minus = findViewById(R.id.button_minus);
        button_one = findViewById(R.id.button_one);
        button_two = findViewById(R.id.button_two);
        button_three = findViewById(R.id.button_three);
        button_plus = findViewById(R.id.button_plus);
        button_doublezero = findViewById(R.id.button_doublezero);
        button_zero = findViewById(R.id.button_zero);
        button_dot = findViewById(R.id.button_dot);
        button_equal = findViewById(R.id.button_equal);
        button_backspace = findViewById(R.id.button_backspace);

        txtDisplay = findViewById(R.id.txt_display);

        //bracket counters
        open_bracket_counter = 0;
        close_bracket_counter = 0;
    }
    // on click method for C
    public void button_c_clicked(View view)
    {
        display_string = " ";
        open_bracket_counter = close_bracket_counter = 0;
        txtDisplay.setText(display_string);
    }
    // on click method for (
    public void button_left_bracket_clicked(View view)
    {
        last_character = display_string.charAt(display_string.length() - 1);
        if (display_string.length() == 25)
            Toast.makeText(MainActivity.this, "Maximum length reached", Toast.LENGTH_SHORT).show();

        else
        {
            if (display_string.equals("")) {
                display_string = display_string + '(';
                open_bracket_counter += 1;
            } else if (last_character == ')' && open_bracket_counter == close_bracket_counter) {
                display_string = display_string + "x(";
                open_bracket_counter += 1;
            } else if (last_character == '(') {
                display_string = display_string + '(';
                open_bracket_counter += 1;
            } else if (Character.isDigit(last_character) && open_bracket_counter == close_bracket_counter) {
                display_string = display_string + "x(";
                open_bracket_counter += 1;
            } else {
                display_string = display_string + '(';
                open_bracket_counter += 1;
            }

            txtDisplay.setText(display_string);
        }
    }

    // on click method for )
    public void button_right_bracket_clicked(View view)
    {
        last_character =display_string.charAt(display_string.length()-1);
        if (display_string.length() == 25)
            Toast.makeText(MainActivity.this, "Maximum length reached", Toast.LENGTH_SHORT).show();

        else
        {
            if (last_character == ')' && open_bracket_counter != close_bracket_counter) {
                display_string = display_string + ')';
                close_bracket_counter += 1;
            }

            if (Character.isDigit(last_character) && open_bracket_counter != close_bracket_counter) {
                display_string = display_string + ')';
                close_bracket_counter += 1;
            }
            if (last_character == '+' || last_character == '-' || last_character == 'x' || last_character == '/')
                Toast.makeText(MainActivity.this, "Illegal use of parenthesis", Toast.LENGTH_SHORT).show();

            txtDisplay.setText(display_string);
        }
    }

    // on click method for /
    public void button_division_clicked(View view) {
        last_character = display_string.charAt(display_string.length() - 1);
        if (display_string.length() == 25)
            Toast.makeText(MainActivity.this, "Maximum length reached", Toast.LENGTH_SHORT).show();

        else {
            if (Character.isDigit(last_character) || last_character == ')')
                display_string += '/';
            else if (last_character == '+' || last_character == '-' || last_character == 'x')
                display_string = display_string.replace(last_character, '/');
            else ;
            txtDisplay.setText(display_string);
        }
    }

    // on click method for x
    public void button_multiply_clicked(View view) {
        last_character = display_string.charAt(display_string.length() - 1);
        if (display_string.length() == 25)
            Toast.makeText(MainActivity.this, "Maximum length reached", Toast.LENGTH_SHORT).show();

        else {
            if (Character.isDigit(last_character) || last_character == ')')
                display_string += 'x';
            else if (last_character == '+' || last_character == '-' || last_character == '/')
                display_string = display_string.replace(last_character, 'x');
            else ;
            txtDisplay.setText(display_string);
        }
    }

    // on click method for -
    public void button_minus_clicked(View view) {
        last_character = display_string.charAt(display_string.length() - 1);
        if (display_string.length() == 25)
            Toast.makeText(MainActivity.this, "Maximum length reached", Toast.LENGTH_SHORT).show();

        else {
            if (Character.isDigit(last_character) || last_character == ')' || last_character == '(')
                display_string += '-';
            else if (last_character == '+' || last_character == 'x' || last_character == '/')
                display_string = display_string.replace(last_character, '-');
            else ;
            txtDisplay.setText(display_string);
        }
    }

    // on click method for +
    public void button_plus_clicked(View view) {
        last_character = display_string.charAt(display_string.length() - 1);
        if (display_string.length() == 25)
            Toast.makeText(MainActivity.this, "Maximum length reached", Toast.LENGTH_SHORT).show();

        else {
            if (Character.isDigit(last_character) || last_character == ')')
                display_string += '+';
            else if (last_character == '-' || last_character == 'x' || last_character == '/')
                display_string = display_string.replace(last_character, '+');
            else ;
            txtDisplay.setText(display_string);
        }
    }

    // on click method for the numbers
    public void number_button_clicked(View view)
    {
        String last_character_string;
        Button b = (Button)view;
        last_character = display_string.charAt(display_string.length()-1);
        if (display_string.length() == 25)
            Toast.makeText(MainActivity.this, "Maximum length reached", Toast.LENGTH_SHORT).show();
        else
        {
            if (last_character == ')')
                display_string += "x" + b.getText().toString();
            else
                display_string += b.getText().toString();
        }

        txtDisplay.setText(display_string);
    }

    // on click method for the dot
    public void button_dot_clicked(View view) {
        last_character = display_string.charAt(display_string.length() - 1);
        if (display_string.length() == 25)
            Toast.makeText(MainActivity.this, "Maximum length reached", Toast.LENGTH_SHORT).show();

        else {
            if (Character.isDigit(last_character))
                display_string += '.';
            else if (last_character == ')')
                display_string += "x0.";
            else
                display_string += "0.";

            txtDisplay.setText(display_string);
        }
    }

    // on click method for the backspace button
    public void button_backspace_clicked(View view)
    {
        display_string = display_string.substring(0, display_string.length()-1);
        txtDisplay.setText(display_string);
    }

    // on click method for =
    public void button_equal_clicked(View view)
    {
        try
        {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();

            if (display_string.contains("x"))
                display_string = display_string.replace("x", "*");

            String result_string = context.evaluateString(scriptable, display_string, "Javascript", 1, null).toString();
            if (result_string.endsWith(".0"))
                result_string = result_string.replace(".0", "");
            txtDisplay.setText(result_string);
            display_string = result_string;
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this, "Error in computing result.", Toast.LENGTH_SHORT).show();
        }
    }
}