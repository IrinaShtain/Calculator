package shtainyky.com.calculator.Users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "users.db";
    private final static String TABLE_NAME = "users";
    private final static String COLUMN_ID = "_id";
    private final static String COLUMN_FIRST_NAME = "first_name";
    private final static String COLUMN_lAST_NAME = "last_name";
    private final static String COLUMN_EMAIL = "email";
    private final static String COLUMN_PHONE = "phone";
    private final static String COLUMN_CHILDREN = "children";
    private final static String COLUMN_LOGIN = "login";
    private final static String COLUMN_PASSWORD = "password";

    private final static String DATABASE_CREATE = "create table users (_id integer primary key autoincrement, " +
            "first_name text not null , last_name text not null ," +
            " email text not null, phone text , " +
            "children text , login text not null ,  password text not null);";

    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
        sqLiteDatabase = this.sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + DATABASE_NAME;
        sqLiteDatabase.execSQL(query);
        this.onCreate(sqLiteDatabase);
    }
    public void insertUser(User user)
    {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, user.getFirst_name());
        values.put(COLUMN_lAST_NAME, user.getLast_name());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PHONE, user.getPhone());
        values.put(COLUMN_CHILDREN, user.getChildren());
        values.put(COLUMN_LOGIN, user.getLogin());
        values.put(COLUMN_PASSWORD, user.getPassword());

        sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }
    public String searchPassword(String login)
    {
        sqLiteDatabase = this.getReadableDatabase();
        String query = "select login, password from "+ TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String uLogin, uPassword = "not found";
        if(cursor.moveToFirst())
        {
            do {
                uLogin = cursor.getString(0);
                if (uLogin.equals(login)) {
                    uPassword = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return uPassword;
    }
}
