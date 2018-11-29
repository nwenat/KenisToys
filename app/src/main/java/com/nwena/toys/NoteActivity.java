package com.nwena.toys;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class NoteActivity extends AppCompatActivity implements CommonColors {

    EditText et;
    Bundle bundle = new Bundle();
    private String path = Environment.getExternalStorageDirectory().toString() + "/DigitalZombieLab/KenisToys";
    private final int MEMORY_ACCESS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("activity", "onCreate");
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNaviBarColor();
        et = (EditText) findViewById(R.id.editText);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            createDir();
            createFile();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        Log.d("activity", "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("activity", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("activity", "onPause");
        //text = et.getText().toString();
        bundle.putString("et", et.getText().toString());
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.d("activity", "onRestart");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d("activity", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("activity", "onDestroy");
        super.onDestroy();
    }


    @Override
    public void setNaviBarColor()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
            getWindow().setNavigationBarColor(getApplicationContext().getColor(R.color.colorPrimaryDark));
    }

    public void createDir() {
        File folder = new File(path);
        if(!folder.exists()) {
            try {
                folder.mkdirs();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void createFile() {
        File file = new File(path + "/" + System.currentTimeMillis() + ".txt");
        FileOutputStream fOut;
        OutputStreamWriter myOutWriter;
        try {
            fOut = new FileOutputStream(file);
            myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(et.getText());
            myOutWriter.close();
            fOut.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
