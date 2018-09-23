package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Yangyulin on 2018/9/15.
 */
public class CrimeListFragment extends Fragment {

    String model = "EEEE,MMM dd,yyyy";
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(model, Locale.ENGLISH);
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    public enum ITEM_TYPE{
        ITEM_TYPE_STANDARD,
        ITEM_TYPE_POLICE
    }
    private int position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);

        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//支持以竖直列表的形式展示列表项

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    //该方法创建CrimeAdapter，然后设置给RecyclerView
    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if(mAdapter == null){
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }else{
            //mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemChanged(position);
        }

    }

    //CrimeHolder内部类，实例化并使用list_item_crime布局
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;
        private Crime mCrime;

        public CrimeHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_crime,parent,false));
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            mSolvedImageView = itemView.findViewById(R.id.crime_solved);
        }

        //每次有新的Crime要在CrimeHolder中显示时都要调用它一次
        public void bind(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            //mDateTextView.setText(mCrime.getDate().toString());
            mDateTextView.setText(mSimpleDateFormat.format(mCrime.getDate()));
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(),mCrime.getTitle() + " clicked!",Toast.LENGTH_SHORT).show();
            position = mCrimeRecyclerView.getChildAdapterPosition(v);
            Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
        }
    }

    private class CrimePoliceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Button mPoliceButton;
        private Crime mCrime;

        public CrimePoliceHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_crime,parent,false));
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            mPoliceButton = itemView.findViewById(R.id.crime_police);
            mPoliceButton.setVisibility(View.VISIBLE);
        }

        public void bind(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),mCrime.getTitle() + " clicked!",Toast.LENGTH_SHORT).show();
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @NonNull
        @Override
        //RecyclerView需要新的ViewHolder来显示列表项时，会调用此方法
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            if(viewType == ITEM_TYPE.ITEM_TYPE_STANDARD.ordinal()){
                return new CrimeHolder(layoutInflater,parent);
            }else if(viewType == ITEM_TYPE.ITEM_TYPE_POLICE.ordinal()){
                return new CrimePoliceHolder(layoutInflater,parent);
            }
            return null;
        }

        @Override
        //数据绑定
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            Crime crime = mCrimes.get(position);

            if(holder instanceof CrimeHolder){
                ((CrimeHolder)holder).bind(crime);
            }else if(holder instanceof CrimePoliceHolder){
                ((CrimePoliceHolder)holder).bind(crime);
            }

        }

        @Override
        public int getItemViewType(int position) {
            if(mCrimes.get(position).isRequiresPolice()){
                return ITEM_TYPE.ITEM_TYPE_POLICE.ordinal();
            }else{
                return ITEM_TYPE.ITEM_TYPE_STANDARD.ordinal();
            }
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
