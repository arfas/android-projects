package DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, "Airlines", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"create table User(User_id integer primary key autoincrement,pname text,ppwd text,pmail text,pphno text);");
		db.execSQL("create table Flight(Flight_id integer primary key autoincrement,fname text,fcode text);");

		db.execSQL(
				"create table Book(Book_id integer primary key autoincrement,bphone text,bfrom text,bto text,bdate text);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
