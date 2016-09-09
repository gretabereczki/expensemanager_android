package gretab.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Greta on 22/07/2016.
 */
public class Statistics extends Activity {

    DatabaseHelper db;
    TextView sum, balance;
    SharedPreferences preferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        db = new DatabaseHelper(this);
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        double initialBalance=Double.parseDouble(preferences.getString("balance", "0").toString());

        sum=(TextView)findViewById(R.id.textView11);
        balance=(TextView)findViewById(R.id.balanceValue);

        sum.setText(Double.toString(db.getSpendings()));
        balance.setText(Double.toString(initialBalance+db.getSpendings()));
    }

    public void openMainActivity(View view) {
        Intent intent = new Intent(Statistics.this, MainActivity.class);
        startActivity(intent);
    }
}