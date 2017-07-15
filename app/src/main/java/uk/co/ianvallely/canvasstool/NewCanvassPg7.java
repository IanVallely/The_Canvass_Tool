package uk.co.ianvallely.canvasstool;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import static uk.co.ianvallely.canvasstool.MainActivity.MyPREFERENCES;

public class NewCanvassPg7 extends AppCompatActivity {

    String uName;
    String gName;
    String respondantName;
    String address;
    String postCode;
    String answer1;
    String answer2;
    String answer3;
    String nextRef;
    String newsletter = "not set";
    String joinNow = "Not Set";
    String blockNow = "Not Set";
    String eMail = "Not Set";
    CheckBox news;
    CheckBox join;
    CheckBox block;
    EditText email;
    Button sub;



    private static String TAG = "PermissionDemo";
    private static final int REQUEST_WRITE_STORAGE = 112;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_canvass_pg7);

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        uName = sharedpreferences.getString("user", "Not logged in");
        gName = sharedpreferences.getString("group", "Not Set");

        Intent in = getIntent();
        Bundle b = in.getExtras();
        respondantName = b.getString("responder");
        address = b.getString("address");
        postCode = b.getString("postecode");
        answer1 = b.getString("q1");
        answer2 = b.getString("q2");
        answer3 = b.getString("q3");
        nextRef = b.getString("nextRef");

        news = (CheckBox) findViewById(R.id.checkBoxNewsletter);
        join = (CheckBox) findViewById(R.id.checkBoxJoin);
        block = (CheckBox) findViewById(R.id.checkBox3);
        email = (EditText) findViewById(R.id.editemail);
        sub = (Button) findViewById(R.id.button2);

        news.setText("Would you like to receive occasional newsletters from " + gName + "?");
        join.setText("Would you consider joining the " + gName + " group?");
        block.setText("By checking this box " + gName +" will not contact you again unless by your request. We will not call or leaflet your door.");

        //ask for the permission in android M
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied");

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Permission to access the SD-CARD is required for this app to Download PDF.")
                        .setTitle("Permission required");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        Log.i(TAG, "Clicked");
                        makeRequest();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                makeRequest();
            }
        }

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(news.isChecked()){
                    newsletter = "Yes";
                }
                if(join.isChecked()){
                    joinNow = "Yes";
                }
                if(block.isChecked()){
                    blockNow = "Yes";
                }
                if (email != null){
                    eMail = email.getText().toString();
                }

                File folder = new File(Environment.getExternalStorageDirectory() + "/CanvassTool");
                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdir();
                }

                String baseDir = Environment.getExternalStorageDirectory().toString();
                String fileName = "CanvassData.csv";
                String filePath = baseDir + "/CanvassTool/"  + fileName;
                File f = new File(filePath );
                CSVWriter writer;
// File exist
                try
                {
                if(f.exists() && !f.isDirectory()){
                FileWriter mFileWriter = new FileWriter(filePath , true);
                    writer = new CSVWriter(mFileWriter);
                }
                else {
                    writer = new CSVWriter(new FileWriter(filePath));
                    String header = "userName,Respondant,address,postcode,aQ1,aQ2,aQ3,aQ4,newsletter,join,block,email" ;
                    String[] splHeader = header.split(",");
                    writer.writeNext(splHeader);

                }
                String rData =uName +","+ respondantName+","+address +","+postCode +","+answer1+","+answer2+","+answer3+"," + nextRef+
                        ","+newsletter+","+joinNow+","+blockNow+ "," +eMail;
                String [] data = rData.split(",");
                //        String[] data = {uName +","+ respondantName+","+address +","+postCode +","+answer1+","+answer2+","+answer3+"," + answer4+","+nextRef+
                //       ","+newsletter+","+joinNow+","+blockNow+ "," +eMail};



                writer.writeNext(data);


                writer.close();

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    //error
                }
                Log.d("MyActivity", " user: " + uName + " Respondand: " + respondantName + " address: " + address
                        + " postecode: " + postCode + " q 1: " + answer1 + " answer 2: " + answer2 + " answer 3: "
                        + answer3 + " answer 4: " + " indyref: " +nextRef +" newsletter: "
                        + newsletter+" join: " +joinNow+" block: " +blockNow+" email: " +eMail);
                NextActiviy();

            }
        });

    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_WRITE_STORAGE);
    }

    public void NextActiviy () {
        Intent intent = new Intent(this, ThankYou.class);
        startActivity(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permission has been denied by user");

                } else {

                    Log.i(TAG, "Permission has been granted by user");

                }
            }
        }
    }
}
