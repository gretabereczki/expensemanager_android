package gretab.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * The activity managing the settings.
 *
 * @author Greta Bereczki
 */
public class Settings extends Activity {

    DatabaseHelper db;
    Spinner dropDown;
    EditText balance;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        db = new DatabaseHelper(this);

        dropDown = (Spinner) findViewById(R.id.spinner2);
        balance = (EditText) findViewById(R.id.editText3);
        String[] currency = new String[]{"GBP (£)", "USD ($)", "EUR (€)", "HUF"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDown.setAdapter(adapter);

        SharedPreferences preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        ;
        double initialBalance = Double.parseDouble(preferences.getString("balance", "0").toString());
        balance.setHint(preferences.getString("balance", "0").toString());
    }

    public void saveSettings(View view) {
        SharedPreferences preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        String category = dropDown.getItemAtPosition(dropDown.getSelectedItemPosition()).toString();
        edit.putString("currencyType", category);

        try {
            edit.putString("balance", balance.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(Settings.this, "Balance must be a number!", Toast.LENGTH_LONG).show();
        }
        edit.commit();
        db.updateCurrency(category);

        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent);
    }
}