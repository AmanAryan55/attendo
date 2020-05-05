package com.example.attendo.Model;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendo.Database.Data;
import com.example.attendo.Database.DataSource;
import com.example.attendo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.MyViewHolder> {

    private Context mContext;
    //private List<SubEntity> mDataList;
    private Cursor mCursor;
    public onitemclick mOnclick;

    DataSource db;


    public  SubAdapter(Context mContext, Cursor mCursor,onitemclick mOnclick)
    {
        this.mContext=mContext;
        this.mCursor=mCursor;
        //this.mDatalist=mDatalist;
        this.mOnclick=mOnclick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.sub_info,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int position) {
        if(!mCursor.moveToPosition(position))
        {
            return;
        }
        final String sub_name=mCursor.getString(mCursor.getColumnIndex(Data.Coln_2));
        //final String sub_present=mCursor.getString(mCursor.getColumnIndex(Data.Coln_3));
        //final String sub_absent=mCursor.getString(mCursor.getColumnIndex(Data.Coln_4));
        long id=mCursor.getLong(mCursor.getColumnIndex(Data.Coln_1));
        myViewHolder.subName.setText(sub_name);

        myViewHolder.bPresent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                final long id=mCursor.getLong(mCursor.getColumnIndex(Data.Coln_1));
                mOnclick.OnPresentClick(v,myViewHolder.getLayoutPosition(),id);

            }
        });

        myViewHolder.bAbsent.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v)
            {
                mOnclick.OnAbsentClick(v,myViewHolder.getLayoutPosition());
            }
        });
//        SubEntity subEntity=new SubEntity();
//
//        int p=subEntity.getPresent();
//        int a= subEntity.getAbsent();
//        int tc=0;
//        int Percent =0;
//        tc=p + a;
//
//        if(tc!=0)
//            Percent = p*100 / tc;
//            else
//                Percent = 100;
//
//            myViewHolder.percent.setText(String.valueOf(Percent) + "%");
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void SwapCursor(Cursor newcursor)
    {
        if(mCursor!=null)
            mCursor.close();

        mCursor=newcursor;
        if(newcursor!=null)
            notifyDataSetChanged();
    }
    public interface onitemclick{
        void OnPresentClick(View view,int position,long id);
        void OnAbsentClick(View view,int position);
    }





    class MyViewHolder extends  RecyclerView.ViewHolder
    {
        @BindView(R.id.subName)
        TextView subName;

        @BindView(R.id.percent)
        TextView percent;

        @BindView(R.id.bPresent)
        Button bPresent;

        @BindView(R.id.bAbsent)
        Button bAbsent;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }




    }
}
