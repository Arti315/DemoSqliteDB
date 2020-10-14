package com.arti.demosqlitedb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {
EditText e1,e2,e3,e4;
Button save,read,update,delete;
Mydatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydatabase= new Mydatabase(this);
        e1=findViewById(R.id.et_name);
        e2=findViewById(R.id.et_Surname);
        e3=findViewById(R.id.et_Roll);
        save=findViewById(R.id.save);
        read=findViewById(R.id.read);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        e4=findViewById(R.id.et_id);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isupdate=mydatabase.isUpdate(e4.getText().toString(),
                        e1.getText().toString(),
                        e2.getText().toString(),
                        e3.getText().toString());
                if (isupdate==true){
                    Toast.makeText(MainActivity.this, "sucessfully is update", Toast.LENGTH_SHORT).show();

                }
           else{
                    Toast.makeText(MainActivity.this, "Problem is updating", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int DeletedRows=mydatabase.deleteData(e4.getText().toString());
                if (DeletedRows>0){
                    Toast.makeText(MainActivity.this, "sucessfully is DeletedData", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Problem is DeletedData", Toast.LENGTH_SHORT).show();
                }
            }
        });

          save.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  boolean data=mydatabase.isInsert(e1.getText().toString(),
                          e2.getText().toString(),
                          e3.getText().toString());
                  if (data==true)
                  {
                      Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                  }
                  else
                  {
                      Toast.makeText(MainActivity.this, "Problem in data inserting", Toast.LENGTH_SHORT).show();
                  }
              }
          });
        read.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                Cursor mydata=mydatabase.readData();
                if(mydata.getCount()==0)
                {
                    showMessage("Error","No Data");
                }
                StringBuffer buffer=new StringBuffer();
                while (mydata.moveToNext())
                {
                    buffer.append("ID is " + mydata.getString(0)+"\n");
                    buffer.append("Name  is " + mydata.getString(1)+"\n");
                    buffer.append("Sur Name  is " + mydata.getString(2)+"\n");
                    buffer.append("Roll  is " + mydata.getString(3)+"\n\n");
                }
                showMessage("Data",buffer.toString());

            }
        });
    }

    public void showMessage(String title,String massage)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(massage);
        //builder.setCancelable(false);
        builder.show();
    }
}