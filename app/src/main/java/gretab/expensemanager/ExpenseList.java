package gretab.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * The activity managing the display of data.
 *
 * @author Greta Bereczki
 */
public class ExpenseList extends Activity {

    DatabaseHelper db;
    SimpleCursorAdapter adapter;
    Cursor databaseData;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenselist);

        db = new DatabaseHelper(this);

        databaseData = db.getData();
        String[] from = {"itemname", "price", "category", "currency", "date"};
        int[] to = {R.id.textView, R.id.textView2, R.id.textView3, R.id.textView9, R.id.textViewDate};

        adapter = new SimpleCursorAdapter(this, R.layout.activity_listitem, databaseData, from, to, 0);

        listView = (ListView) findViewById(R.id.expense_list);
        listView.setAdapter(adapter);
    }

    public void openAddActivity(View view) {
        Intent intent = new Intent(ExpenseList.this, ExpenseAdd.class);
        startActivity(intent);
    }
}
