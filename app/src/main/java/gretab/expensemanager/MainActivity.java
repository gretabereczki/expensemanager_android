package gretab.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * The main activity.
 *
 * @author Greta Bereczki
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openAddActivity(View view) {
        Intent intent = new Intent(MainActivity.this, ExpenseAdd.class);
        startActivity(intent);
    }

    public void openListActivity(View view) {
        Intent intent = new Intent(MainActivity.this, ExpenseList.class);
        startActivity(intent);
    }

    public void openSettingsActivity(View view) {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);
    }
}