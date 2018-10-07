package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Yangyulin on 2018/9/14.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;
    //private Map<UUID,Crime> mCrimes;

    public static CrimeLab get(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();//创造一个空List用来保存Crime对象   List中的元素类型可以基于变量声明传入的抽象参数来确定
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
        mCrimes.add(c) ;
    }

    public void deleteCrime(Crime c){
        mCrimes.remove(c);
    }

    //返回数组列表
    public List<Crime> getCrimes() {
        //return mCrimes;
        return new ArrayList<>(mCrimes);
    }

    //返回带指定ID的Crime对象
    public Crime getCrime(UUID id){
        for(Crime crime : mCrimes){
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
//        return mCrimes.get(id);
    }
}
