package uk.co.ianvallely.canvasstool;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static uk.co.ianvallely.canvasstool.MainActivity.MyPREFERENCES;

public class GroupSetup extends AppCompatActivity {

    String uName;
    TextView user;
    EditText groupName;
    EditText g_email;
    String gName;
    String gEmail;
    Button sub;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_setup);

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        uName = sharedpreferences.getString("user", "No name defined");
        user = (TextView) findViewById(R.id.user);
        user.setText(uName);

        groupName = (EditText) findViewById(R.id.group_name);
        g_email = (EditText) findViewById(R.id.g_email);
        sub = (Button) findViewById(R.id.gSub);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gName = groupName.getText().toString();
                gEmail = g_email.getText().toString();
                if (gName != null && !TextUtils.isEmpty(gName)&& gEmail != null && !TextUtils.isEmpty(gEmail)){
                    SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("user", uName);
                    editor.putString("group", gName);
                    editor.putString("email", gEmail);
                    editor.commit();
                    StartActiviy ();
                }else {
                    Toast.makeText(getApplicationContext(), "Please enter both a group name and email address!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void StartActiviy (){
        Intent intent = new Intent(this, Options.class);
        startActivity(intent);
    }
}
