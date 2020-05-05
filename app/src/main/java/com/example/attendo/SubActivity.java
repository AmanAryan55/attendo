package com.example.attendo;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.attendo.Database.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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

       // mSubList= SampleData.getSampleData();
     //   showData();
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

//    private void showData()
//    {
//        SubAdapter subAdapter=new SubAdapter(this,getallitems(),onItemclick);
//        mRView.setAdapter(subAdapter);
//    }

    private void openDialog()
    {
        //Dialog mDialog=new Dialog();
        //mDialog.show(getSupportFragmentManager(),"TAG");
        LayoutInflater inflater=LayoutInflater.from(this);
        View v=inflater.inflate(R.layout.layout_dialog,null);
        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        final EditText alertSub=v.findViewById(R.id.etSub);
      //  final EditText alertpresent=v.findViewById(R.id.alertpresent);
        //final EditText alerttotal=v.findViewById(R.id.alerttotal);
        // final EditText alertperc=v.findViewById(R.id.reqdper);
        alertDialog.setView(v);
        alertDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).
                setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String subjectName = alertSub.getText().toString();

                        if (subjectName.isEmpty()) {
                            new AlertDialog.Builder(SubActivity.this)
                                    .setTitle("Error!!")
                                    .setMessage("Subject name can't be empty")
                                    .show();
                            return;
                        }
                        mDataSource.insertSubname(subjectName);
                        subAdapter.SwapCursor(getallitems());
                        checkBack();


                    }


                });

    }

   public void checkBack()
     {
         Cursor C=mData.rawQuery("SELECT COUNT(*) FROM "+Data.Table_Name,null);
         C.moveToFirst();
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
