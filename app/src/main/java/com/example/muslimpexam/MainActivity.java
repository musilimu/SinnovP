package com.example.muslimpexam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText names, id, department, college, projectName;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id=findViewById(R.id.ID);
        names=findViewById(R.id.Names);
        department=findViewById(R.id.Department);
        college=findViewById(R.id.college);
        projectName=findViewById(R.id.projectName);

        insert=findViewById(R.id.btnInsert);
        delete=findViewById(R.id.btnDelete);
        view=findViewById(R.id.btndisp);
        update=findViewById(R.id.btnUpdate);

        DB= new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idTXT= id.getText().toString();
                String namesTXT= names.getText().toString();
                String departmentTXT= department.getText().toString();
                String collegeTXT = college.getText().toString();
                String projectNameTXT= projectName.getText().toString();

                Boolean checkinsertdata= DB.insertuserdata(idTXT,namesTXT, departmentTXT,collegeTXT, projectNameTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Is Not Inserted", Toast.LENGTH_SHORT).show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idTXT= id.getText().toString();
                String namesTXT= names.getText().toString();
                String departmentTXT= department.getText().toString();
                String collegeTXT = college.getText().toString();
                String projectNameTXT= projectName.getText().toString();
                boolean checkupdatedata= DB.updateuserdata(idTXT,namesTXT, departmentTXT,collegeTXT, projectNameTXT);
                if(checkupdatedata)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Is Not Updated", Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idTXT= id.getText().toString();
                boolean checkdeletedata= DB.deletedata(idTXT);
                if(checkdeletedata)
                    Toast.makeText(MainActivity.this, "Entry deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Is Not deleted", Toast.LENGTH_SHORT).show();

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Names:"+ res.getString(1)+"\n");
                    buffer.append("College:"+ res.getString(2)+"\n\n");
                    buffer.append("Department:"+ res.getString(3)+"\n");
                    buffer.append("Project name:"+ res.getString(4)+"\n\n");
                }
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }

}