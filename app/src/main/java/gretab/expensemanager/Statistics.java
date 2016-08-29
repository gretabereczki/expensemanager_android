package gretab.expensemanager;

import android.app.Activity;
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
    TextView sum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        db = new DatabaseHelper(this);
        sum=(TextView)findViewById(R.id.textView11);
        sum.setText(Double.toString(db.getSpendings()));
    }
}