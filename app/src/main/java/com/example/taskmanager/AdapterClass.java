package com.example.taskmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdapterClass extends ArrayAdapter<Tasks> {


    LongClicked parentActivity;

    Context c;


    public AdapterClass (Context context, ArrayList<Tasks> data)
    {
        super(context, 0, (List<Tasks>) data);
        c = context;
    }

    public interface LongClicked{
        public void deleteTask(int index);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View v = convertView;
        TextView TaskTitle, TaskDesc;

        if(v == null)
        {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.design_file, parent, false);
        }

        TaskTitle = v.findViewById(R.id.TaskTitle);
        TaskDesc = v.findViewById(R.id.TaskDesc);

        Tasks t = getItem(position);

        TaskTitle.setText(t.getTitle());
        TaskDesc.setText(t.getDesc());

        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(c);
                deleteDialog.setTitle("Confirmation");
                deleteDialog.setMessage("Do you want to delete ?");
                deleteDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        parentActivity.deleteTask(position);
                    }
                });

                deleteDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //parentActivity.deletePassenger(position);
                    }
                });
                deleteDialog.show();
                return false;
            }
        });

        return v;
    }


}
