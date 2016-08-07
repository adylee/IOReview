package io.lf.ioreview;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.util.TypedValue;
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
       // file = new File(getFilesDir(),"index.txt");
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {

            file = new File(Environment.getExternalStorageDirectory(), "hello.txt");
            long free  =  file.getFreeSpace();
//
//            File sdk = Environment.getExternalStorageDirectory();
//            StatFs fs = new StatFs(sdk.getPath());
            try {
              FileOutputStream fos =  openFileOutput("index.txt",MODE_PRIVATE);
                fos.write("lalala".getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Log.v("MainActivity",file.toString()+" "+free+" "+file.length()+"@@@@" + Formatter.formatFileSize(this, file.getUsableSpace()));

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
    }
    public void write(){
        SharedPreferences sharedPreferences = getSharedPreferences("info",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",editText.getText().toString());
        editor.putString("pwd",editText2.getText().toString());
        editor.commit();

    }

    public void read(){
        SharedPreferences sharedPreferences = getSharedPreferences("info",MODE_PRIVATE);

        editText.setText(sharedPreferences.getString("name"," "));
        editText2.setText(sharedPreferences.getString("pwd",""));
    }

//    private void read(){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED)){
//            requestPerm();
//            return;
//        }
//
//        if(file.exists()){
//            checkBox.setChecked(true);
//            try {
//                FileInputStream fileInputStream = new FileInputStream(file);
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
//                String name = bufferedReader.readLine();
//                String pwd = bufferedReader.readLine();
//
//                //String s = bufferedReader.readLine();
//                //String[] strings = s.split("\n");
//                editText.setText(name);
//                editText2.setText(pwd);
//                fileInputStream.close();
//
//
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//    }
//
//    private void requestPerm() {
//        // Here, thisActivity is the current activity
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//
//        }
//    }
//
//    private void write() {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED)){
//            requestPerm();
//        }
//        if(checkBox.isChecked()){
//
//
//                try {
//                    FileOutputStream fileOutputStream = new FileOutputStream(file);
//                    text = editText.getText();
//                    //public Editable getText(){
//
//                    // }
//                    //public String toString();
//                   // this.toString()
//                    String name= text.toString();
//
//
//                    String pwd= editText2.getText().toString();
//                        if(TextUtils.isEmpty(name) && TextUtils.isEmpty(pwd)){
//                            fileOutputStream.close();
//                             Toast.makeText(this,"用户名密码不能为空",Toast.LENGTH_LONG).show();
//                            return ;
//                        }
//                        fileOutputStream.write((name+"\n"+pwd).getBytes());
//                        fileOutputStream.close();
//
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//        }
//    }
}
