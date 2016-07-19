package gretab.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                + COLUMN2 + " INTEGER" + " )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String name, Double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(COLUMN1, name);
        content.put(COLUMN2, price);
        long result = db.insert("expenses", null, content);
        return result != -1;
    }

    public Cursor getData() {
        Cursor cursor = getReadableDatabase().query("expenses",
                new String[]{ROW_ID, COLUMN1, COLUMN2},
                null, null, null, null, null);
        return cursor;

    }

}

