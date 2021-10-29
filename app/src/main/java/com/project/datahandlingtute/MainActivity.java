package com.project.datahandlingtute;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.datahandlingtute.database.DBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtName, edtPassword;
    Button btnadd,btnselect,btnupdate,btndel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName= findViewById(R.id.edtName);
        edtPassword= findViewById(R.id.edt_Password);
        btnadd=findViewById(R.id.btnadd);
        btnselect=findViewById(R.id.btnSelect);
        btnupdate=findViewById(R.id.btnupdate);
        btndel=findViewById(R.id.btndel);



        btnselect.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                viewAll(view);
            }

        });
        btnupdate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                updateUser(view);
            }

        });
        btndel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                deleteUser(view);
            }

        });


        btnadd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                saveUser(view);
            }
        });
    }
    public void saveUser(View view){
        String name=edtName.getText().toString();
        String password=edtPassword.getText().toString();
        DBHelper dBhelper = new DBHelper(this);

        if(name.isEmpty()||password.isEmpty()){
            Toast.makeText(this,"Enter values ",Toast.LENGTH_SHORT).show();
        }else{
            long inserted = dBhelper.addInfo(name,password);

            if(inserted > 0){
                Toast.makeText(this,"Data inserted successfully",Toast.LENGTH_SHORT).show();
               edtName.setText("");
               edtPassword.setText("");
            }else{
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void viewAll(View view){
        DBHelper dbHelper = new DBHelper(this);
        List info = dbHelper.readAll();
        String [] infoArray = (String[]) info.toArray(new String[0]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Users details");

        builder.setItems(infoArray,new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialogInterface, int i){
                String username = infoArray[i].split(":")[0];
                edtName.setText(username);
                edtPassword.setText("*******");


            }
        });
        builder.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialogInterface, int i){

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void deleteUser(View view){
        //create an instance of the DBHelper class for all the CRUD methods
        DBHelper dbHelper = new DBHelper(this);
        String username = edtName.getText().toString();

        if(username.isEmpty()){
            Toast.makeText(this,"Select a user",Toast.LENGTH_SHORT).show();
        }else{
            dbHelper.deleteInfo(username);
            Toast.makeText(this,"user is deleted",Toast.LENGTH_SHORT).show();
           edtName.setText("");
           edtPassword.setText("");
        }
    }
    public void updateUser(View view){
        DBHelper dbHelper = new DBHelper(this);
        String username = edtName.getText().toString();
        String password = edtPassword.getText().toString();


        if(username.isEmpty()||password.isEmpty()){
            Toast.makeText(this,"Select or add user",Toast.LENGTH_SHORT).show();
        }else{
            dbHelper.updateInfo(view,username,password);
            edtName.setText("");
            edtPassword.setText("");
        }

    }
}