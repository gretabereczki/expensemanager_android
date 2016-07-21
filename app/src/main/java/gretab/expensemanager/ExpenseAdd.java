package gretab.expensemanager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * The activity managing the add of data.
 *
 * @author Greta Bereczki
 */
public class ExpenseAdd extends Activity {

    DatabaseHelper db;
    EditText nameText, priceText;
    Button buttonAdd;
    Spinner dropDown;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense);
        db = new DatabaseHelper(this);
        nameText = (EditText) findViewById(R.id.editText);
        priceText = (EditText) findViewById(R.id.editText2);
        buttonAdd = (Button) findViewById(R.id.button);

        dropDown = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[]{"Car", "Entertainment", "Food", "Healthcare", "Household", "Makeup", "Other", "Personal", "Travel", "Utilities", "Vacation"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDown.setAdapter(adapter);
    }

    public void addData(View view) {
        boolean isInserted = false;
        try {
            String category = dropDown.getItemAtPosition(dropDown.getSelectedItemPosition()).toString();
            SharedPreferences preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
            String currency = preferences.getString("currencyType", "HUF");
            isInserted = db.insertData(nameText.getText().toString(),
                    Double.parseDouble(priceText.getText().toString()), category, currency);
        } catch (NumberFormatException e) {
            Toast.makeText(ExpenseAdd.this, "Price must be a number!", Toast.LENGTH_LONG).show();
        }

        if (isInserted = true)
            Toast.makeText(ExpenseAdd.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(ExpenseAdd.this, "Data not Inserted", Toast.LENGTH_LONG).show();
    }
}
