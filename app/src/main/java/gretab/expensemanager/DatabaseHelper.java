package gretab.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * The class handling the database.
 *
 * @author Greta Bereczki
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "expenses.db";
    public static final String TABLE_NAME = "expenses";
    public static final String ROW_ID = "_id";
    public static final String COLUMN1 = "itemname";
    public static final String COLUMN2 = "price";
    public static final String COLUMN3 = "category";
    public static final String COLUMN4 = "currency";
    public static final String COLUMN5 = "date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        /*db.execSQL("delete from " + TABLE_NAME);
        db.close();*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  " + TABLE_NAME + "( "
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN1 + " TEXT,"
                + COLUMN2 + " REAL," + COLUMN3 + " TEXT, " + COLUMN4 + " TEXT, " + COLUMN5 + " DATE " + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String name, Double price, String category, String currency, Date date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(COLUMN1, name);
        content.put(COLUMN2, price);
        content.put(COLUMN3, category);
        content.put(COLUMN4, currency);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        content.put(COLUMN5, dateFormat.format(date));

        long result = db.insert("expenses", null, content);
        return result != -1;
    }

    public Cursor getData() {
        Cursor cursor = getReadableDatabase().query(TABLE_NAME,
                new String[]{ROW_ID, COLUMN1, COLUMN2, COLUMN3, COLUMN4, COLUMN5},
                null, null, null, null, null);
        return cursor;

    }

    public double getSpendings()
    {
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT SUM(price) FROM expenses", null);
        if(cursor.moveToFirst()) {
            return cursor.getDouble(0);
        }
        else return 0;
    }

    public void updateCurrency(String newCurrency)
    {
        Cursor cursor = getReadableDatabase().rawQuery(
                "UPDATE expenses SET currency='"+newCurrency+"'", null);
    }
}

