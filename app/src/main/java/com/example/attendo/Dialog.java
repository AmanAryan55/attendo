package com.example.attendo;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attendo.Database.DataSource;

public class Dialog extends AppCompatDialogFragment {

    private EditText etSub;
    private Button bAddSubF;
    public DataSource dataSource;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.layout_dialog,null);
        builder.setView(view);
        builder.setTitle("Subject Details");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        etSub=view.findViewById(R.id.etSub);

        bAddSubF=view.findViewById(R.id.bAddSubF);
        bAddSubF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dataSource.insertSubname(etSub.getText().toString());
                Toast.makeText(getContext(),"Done",Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }

}
