package au.com.patricklabes.chaliceofawesome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by patrick on 4/03/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database";
    private static final String TABLE_NAME = "table_accomplishments";
    private static final String COL_DATE = "date";
    private static final String COL_VISITS = "visits";
    private static final String COL_ACCOM = "accomplishments";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =
                "CREATE TABLE "+ TABLE_NAME + "("
                        + COL_DATE + " TEXT,"
                        + COL_VISITS + " INTEGER,"
                        + COL_ACCOM + " TEXT"
                        +")";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.close();
    }

    public void dropTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        db.close();
    }

    public void addEntry(PersonalWins personalWins){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_ACCOM, personalWins.getAccomplishments());
        values.put(COL_DATE, personalWins.getDate());
        values.put(COL_VISITS, personalWins.getVisits());

        db.insert(TABLE_NAME,null,values);
        db.close();

    }

    public long getDatabaseProfilesCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        long cnt  = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return cnt;

    }


    public PersonalWins getSpecificRowById(int pos){
        SQLiteDatabase db = this.getReadableDatabase();
        PersonalWins personalWin = new PersonalWins();

        String selectQuary  = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuary,null);
        cursor.moveToPosition(pos);
        //cursor.moveToFirst();

        //cursor.moveToPosition(pos);
        String dateCol = cursor.getString(0);
        int visits = cursor.getInt(1);
        String accomplishment = cursor.getString(2);
        db.close();

        personalWin.PersonlWinsContinue(accomplishment,dateCol,visits);

        return personalWin;
    }



}
