package uk.co.ianvallely.canvasstool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static uk.co.ianvallely.canvasstool.MainActivity.MyPREFERENCES;

public class Options extends AppCompatActivity {

    String uName;
    TextView userName;
    Button newCanvass;
    Button sendMail;
    Button changeUser;
    String gName;
    String eMail;
    TextView groupName;
    String q1;
    String q2;
    String q3;
    String q4;
    String q5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        uName = sharedpreferences.getString("user", "No name defined");
        gName = sharedpreferences.getString("group", "Not Set");
        eMail = sharedpreferences.getString("email", "No email set");
        groupName = (TextView) findViewById(R.id.group_name);
        groupName.setText(gName);
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(uName);
        newCanvass = (Button)findViewById(R.id.new_Canvass);
        sendMail = (Button) findViewById(R.id.buttonMail);
        changeUser = (Button) findViewById(R.id.buttonchangeUser);

        changeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("user", "Not logged in");
                editor.commit();
                StartActiviy ();
            }
        });

        sendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename="CanvassData.csv";
                String file_backup= "/CanvassTool/Backup/CanvassData.csv";
                Environment.getExternalStorageDirectory().toString();
                File filelocation = new File(Environment.getExternalStorageDirectory().toString() + File.separator+ "CanvassTool" + File.separator, filename);
                File filedestination = new File(Environment.getExternalStorageDirectory().toString(), file_backup);
                Uri path = Uri.fromFile(filelocation);

                File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "CanvassTool"+ File.separator + "Backup");
                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdir();
                }
                try {
                    copy(filelocation, filedestination );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                q1 = getString(R.string.q1);
                q2 = getString(R.string.q2);
                q3 = getString(R.string.q3);
                q4 = getString(R.string.q4);


                Intent emailIntent = new Intent(Intent.ACTION_SEND);
// set the type to 'email'
                emailIntent .setType("vnd.android.cursor.dir/email");
                String to[] = {eMail};
                emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
// the attachment
                emailIntent .putExtra(Intent.EXTRA_STREAM, path);
// the mail subject
                emailIntent .putExtra(Intent.EXTRA_SUBJECT, gName+" App Data");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Questions 1to4 as follows  \n"+
                     "Q1.   " + q1 +"\n"+ "Q2.   "   + q2 +"\n"+ "Q3.   " + q3 +"\n"+ "Q4.   " + q4);
                startActivity(Intent.createChooser(emailIntent , "Send email..."));
                //File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator+ "CanvassTool" + File.separator, filename);
                //boolean deleted = file.delete();
            }
        });

        newCanvass.setOnClickListener (new View.OnClickListener() {
            public void onClick(View view) {
                    NextActiviy();
            }
        });

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
         //   @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

    }
    public void StartActiviy (){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void SetGroupActiviy (){
        Intent intent = new Intent(this, GroupSetup.class);
        startActivity(intent);
    }
    public void SetDelActiviy (){
        Intent intent = new Intent(this, ConfirmDelete.class);
        startActivity(intent);
    }



    public void NextActiviy (){
        Intent intent = new Intent(this, NewCanvassSession.class);
        startActivity(intent);
    }

    // copy files
    public void copy(File filelocation, File filedestination) throws IOException {
        InputStream in = new FileInputStream(filelocation);
        try {
            OutputStream out = new FileOutputStream(filedestination);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.menu_main_group:
                SetGroupActiviy ();
                break;
            case R.id.menu_main_delete:
                SetDelActiviy ();
                break;


        }
        return true;

    }
}
