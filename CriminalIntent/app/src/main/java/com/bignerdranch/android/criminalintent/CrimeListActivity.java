package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Yangyulin on 2018/9/15.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment creatFragment() {
        return new CrimeListFragment();
    }
}
