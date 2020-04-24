package com.example.attendo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.attendo.Database.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.example.attendo.Database.DataSource;
import com.example.attendo.Model.SubAdapter;
import com.example.attendo.Model.SubEntity;
import com.example.attendo.Util.SampleData;

import java.util.List;

public class SubActivity extends AppCompatActivity implements SubAdapter.onitemclick {

    private RecyclerView mRView;
    private List<SubEntity> mSubList;
    private DataSource mDataSource;
    SQLiteDatabase mData;
    SubAdapter.onitemclick onItemclick;
    private SubAdapter subAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataSource = new DataSource(this);
        mDataSource.open();

        mRView=(RecyclerView)findViewById(R.id.recyclerView);
        SubAdapter subAdapter;
        subAdapter=new SubAdapter(this,getallitems(),onItemclick);

        mRView.setAdapter(subAdapter);
        mRView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.addSub);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDialog();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initRView();

        mSubList= SampleData.getSampleData();
        showData();
    }

    private Cursor getallitems()
    {
        return mData.query(
                Data.Table_Name,
                null,
                null,
                null,
                null,null,null,Data.Coln_4
                

        );
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mDataSource.open();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mDataSource.close();
    }

    private void showData()
    {
        SubAdapter subAdapter=new SubAdapter(this,getallitems(),onItemclick);
        mRView.setAdapter(subAdapter);
    }

    private void openDialog()
    {
        Dialog mDialog=new Dialog();
        mDialog.show(getSupportFragmentManager(),"TAG");
    }

    private void initRView()
    {
        mRView.hasFixedSize();
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        mRView.setLayoutManager(layoutManager);
    }

    @Override
    public void OnPresentClick(View view, int position, long id) {
        Cursor cursor = getallitems();
        cursor.moveToPosition(position);
        long pr = cursor.getLong(cursor.getColumnIndex(Data.Coln_1));
        int npresent = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Data.Coln_3))) + 1;
        final String sub = cursor.getString(cursor.getColumnIndex(Data.Coln_2));
        mDataSource.updatePresent(sub, npresent);
        subAdapter.SwapCursor(getallitems());
    }

    @Override
    public void OnAbsentClick(View view, int position) {
        Cursor cursor=getallitems();
        cursor.moveToPosition(position);
        long pr=cursor.getLong(cursor.getColumnIndex(Data.Coln_1));
        int nabsent=Integer.parseInt(cursor.getString(cursor.getColumnIndex(Data.Coln_4)))+1;
        final String sub=cursor.getString(cursor.getColumnIndex(Data.Coln_2));
        mDataSource.updatePresent(sub,nabsent);
        subAdapter.SwapCursor(getallitems());


    }
}
