package database.CrimeDbSchema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import database.CrimeDbSchema.CrimeDbSchema.CrimeTable;

/**
 * Created by Yangyulin on 2018/10/25.
 */
public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    //负责创建初始数据库
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ CrimeTable.NAME + "("+"_id integer primary key autoincrement, "+CrimeTable.Cols.UUID+","+CrimeTable.Cols.TITLE+","+CrimeTable.Cols.DATE+","+CrimeTable.Cols.SOLVED+")");
    }

    @Override
    //负责与升级相关的工作
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
