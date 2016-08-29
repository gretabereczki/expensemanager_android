package gretab.expensemanager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The activity managing the add of data.
 *
 * @author Greta Bereczki
 */
public class ExpenseAdd extends Activity {

    DatabaseHelper db;
    EditText nameText, priceText;
    TextView date;
    Button buttonAdd;
    Spinner dropDown;
    RadioButton incomeButton, expenseButton;
    int year, month, day;
    SharedPreferences preferences;
    String currency;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense);
        db = new DatabaseHelper(this);
        nameText = (EditText) findViewById(R.id.editText);
        priceText = (EditText) findViewById(R.id.editText2);
        //toggle=(Switch)findViewById(R.id.switch1);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        String formattedDate = df.format(c.getTime());
        date = (TextView) findViewById(R.id.textDate);
        date.setText(formattedDate);
        buttonAdd = (Button) findViewById(R.id.button);
        incomeButton = (RadioButton) findViewById(R.id.radioButton);
        expenseButton = (RadioButton) findViewById(R.id.radioButton2);

        dropDown = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[]{"Uncathegorised", "Car", "Entertainment", "Food", "Healthcare", "Household", "Makeup", "Other", "Personal", "Travel", "Utilities", "Vacation"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDown.setAdapter(adapter);

        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        currency = preferences.getString("currencyType", "HUF");

        priceText.setHint(currency);
    }

    public void addData(View view) {
        boolean isInserted = false;
        try {
            String category = dropDown.getItemAtPosition(dropDown.getSelectedItemPosition()).toString();

            Date date = new Date(year - 1900, month, day);

            if (incomeButton.isChecked())
                isInserted = db.insertData(nameText.getText().toString(),
                        Double.parseDouble(priceText.getText().toString()), category, currency, date);
            else if (expenseButton.isChecked())
                isInserted = db.insertData(nameText.getText().toString(),
                        -Double.parseDouble(priceText.getText().toString()), category, currency, date);
        } catch (NumberFormatException e) {
            Toast.makeText(ExpenseAdd.this, "Price must be a number!", Toast.LENGTH_LONG).show();
        }

        if (isInserted = true)
            Toast.makeText(ExpenseAdd.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(ExpenseAdd.this, "Data not Inserted", Toast.LENGTH_LONG).show();

        nameText.setText("");
        priceText.setText("");
        dropDown.setSelection(0);
        incomeButton.setChecked(false);
        expenseButton.setChecked(false);
    }

    public void openCalendar(View view) {
        DatePickerDialog dialog = new DatePickerDialog(this, 0, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                year = selectedYear;
                month = selectedMonth;
                day = selectedDay;
                String year1 = String.valueOf(selectedYear);
                String month1 = String.valueOf(selectedMonth + 1);
                String day1 = String.valueOf(selectedDay);
                date.setText(new StringBuilder()
                        .append(day1).append("/").append(month1).append("/").append(year1).append(" "));
            }
        }, year, month, day);


        dialog.show();
    }

}