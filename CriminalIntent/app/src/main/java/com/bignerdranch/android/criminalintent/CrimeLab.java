package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.CrimeDbSchema.CrimeBaseHelper;

import static database.CrimeDbSchema.CrimeDbSchema.*;

/**
 * Created by Yangyulin on 2018/9/14.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    //private Map<UUID,Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
//        for(int i = 0; i < 100; i++){
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0);
//            mCrimes.add(crime);
//        }
//        mCrimes = new LinkedHashMap<>();
//        for(int i = 0; i < 100; i++){
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0);
//            mCrimes.put(crime.getId(),crime);
//        }
    }

    public void addCrime(Crime c){
        ContentValues values = getContentValues(c);

        mDatabase.insert(CrimeTable.NAME,null,values);
    }

    public void deleteCrime(Crime c){
        String uuidString = c.getId().toString();

        mDatabase.delete(CrimeTable.NAME,CrimeTable.Cols.UUID+"=?",new String[]{uuidString});
    }

    public void updateCrime(Crime c){
        String uuidString = c.getId().toString();
        ContentValues values = getContentValues(c);

        mDatabase.update(CrimeTable.NAME,values,CrimeTable.Cols.UUID+"=?",new String[]{uuidString});
    }

    public CrimeCursorWrapper queryCrimes(String whereClause,String[] whereArgs){
        Cursor cursor = mDatabase.query(CrimeTable.NAME,null,whereClause,whereArgs,null,null,null);
        return new CrimeCursorWrapper(cursor);
    }

    //返回数组列表
    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursorWrapper = queryCrimes(null,null);

        try {
            cursorWrapper.moveToFirst();
            //是否有数据可取
            while(!cursorWrapper.isAfterLast()){
                crimes.add(cursorWrapper.getCrime());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }

        return crimes;
    }

    //返回带指定ID的Crime对象
    //和上面的getCrimes()方法很像，唯一区别就是，它只需要取出已存在的首条记录
    public Crime getCrime(UUID id){
        CrimeCursorWrapper cursorWrapper = queryCrimes(CrimeTable.Cols.UUID+"=?",new String[]{id.toString()});

        try {
            if(cursorWrapper.getCount()== 0){
                return null;
            }

            cursorWrapper.moveToFirst();
            return cursorWrapper.getCrime();
        } finally {
            cursorWrapper.close();
        }
    }

    private static ContentValues getContentValues(Crime crime){
      ContentValues values = new ContentValues();
      values.put(CrimeTable.Cols.UUID,crime.getId().toString());
      values.put(CrimeTable.Cols.TITLE,crime.getTitle());
      values.put(CrimeTable.Cols.DATE,crime.getDate().getTime());
      values.put(CrimeTable.Cols.SOLVED,crime.isSolved()?1:0);

      return values;
    }
}
