package gretab.expensemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense);
        db = new DatabaseHelper(this);
        nameText = (EditText) findViewById(R.id.editText);
        priceText = (EditText) findViewById(R.id.editText2);
        buttonAdd = (Button) findViewById(R.id.button);
    }

    public void addData(View view) {
        boolean isInserted = false;
        try {
            isInserted = db.insertData(nameText.getText().toString(),
                    Double.parseDouble(priceText.getText().toString()));
        } catch (NumberFormatException e) {
            Toast.makeText(ExpenseAdd.this, "Price must be a number!", Toast.LENGTH_LONG).show();
        }

        if (isInserted = true)
            Toast.makeText(ExpenseAdd.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(ExpenseAdd.this, "Data not Inserted", Toast.LENGTH_LONG).show();
    }
}
