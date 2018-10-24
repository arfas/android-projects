package DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, "Railway", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table UserReg(donation_id integer primary key autoincrement,buser text,bmail text,"
				+ "bphno text,bpwd text);");
		db.execSQL(
				"create table Admin(Admin_id integer primary key autoincrement,pname text,ppwd text,pphno text,pmail text);");
		
		db.execSQL(
				"create table bfeedback(feedback_id integer primary key autoincrement,feedback text,fdate text,fgid integer);");
		
		
		
		db.execSQL("create table NewTrain(Train_id integer primary key autoincrement,TrainName text,tcode text);");
		
		
		db.execSQL("create table Request(request_id integer primary key autoincrement,bname text,bphno text" +
				",bbg text,bgender text," +
				"bage integer,bdistrict text,blod text,bstatus text,FOREIGN key(request_id) REFERENCES UserReg(donation_id));");
		db.execSQL("create table Donar(donar_id integer primary key autoincrement,bname text,bphno text" +
				",bbg text,bgender text," +
				"bage integer,bdistrict text,blod text,bstatus text, FOREIGN key(donar_id) REFERENCES UserReg(donation_id));");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
