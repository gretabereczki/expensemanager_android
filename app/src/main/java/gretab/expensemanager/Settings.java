package gretab.expensemanager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * The activity managing the settings.
 *
 * @author Greta Bereczki
 */
public class Settings extends Activity {

    DatabaseHelper db;
    Spinner dropDown;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        db = new DatabaseHelper(this);

        dropDown = (Spinner) findViewById(R.id.spinner2);
        String[] currency = new String[]{"GBP (£)", "USD ($)", "EUR (€)", "HUF"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDown.setAdapter(adapter);
    }

    public void saveSettings(View view) {
        SharedPreferences preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        String category = dropDown.getItemAtPosition(dropDown.getSelectedItemPosition()).toString();
        edit.putString("currencyType", category);
        edit.commit();
    }
}