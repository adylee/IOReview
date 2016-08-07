package io.lf.ioreview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
   EditText editText ;
    EditText editText2;
    Button button;
    CheckBox checkBox;
    private File file;
    private Editable text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file = new File(getFilesDir(),"index.txt");

        editText = (EditText) findViewById(R.id.edit_text);
        editText2 = (EditText) findViewById(R.id.edit_text2);
        button = (Button) findViewById(R.id.btn);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        read();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write();
            }
        });

    }
    private void read(){
        if(file.exists()){
            checkBox.setChecked(true);
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String s = bufferedReader.readLine();
                String[] strings = s.split("-");
                editText.setText(strings[0]);
                editText2.setText(strings[1]);
                fileInputStream.close();



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void write() {
        if(checkBox.isChecked()){


                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    text = editText.getText();
                    //public Editable getText(){

                    // }
                    //public String toString();
                   // this.toString()
                    String name= text.toString();
                    
                    String pwd= editText2.getText().toString();
                        if(TextUtils.isEmpty(name) && TextUtils.isEmpty(pwd)){
                            fileOutputStream.close();
                             Toast.makeText(this,"用户名密码不能为空",Toast.LENGTH_LONG).show();
                            return ;
                        }
                        fileOutputStream.write((name+"-"+pwd).getBytes());
                        fileOutputStream.close();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


        }
    }
}
