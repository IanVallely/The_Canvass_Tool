package uk.co.ianvallely.canvasstool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static uk.co.ianvallely.canvasstool.MainActivity.MyPREFERENCES;

public class ThankYou extends AppCompatActivity {

    String uName;
    Button home;
    Button nextCanvass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        home = (Button) findViewById(R.id.buttonHome);
        nextCanvass = (Button) findViewById(R.id.new_Canvass);
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        uName = sharedpreferences.getString("user", "Not logged in");


        nextCanvass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NextActiviy();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActiviy();
            }
        });

    }
    public void NextActiviy (){
        Intent intent = new Intent(this, NewCanvassSession.class);
        intent.putExtra("user", uName);
        startActivity(intent);
    }


    public void HomeActiviy (){
        Intent intent = new Intent(this, Options.class);
        intent.putExtra("user", uName);
        startActivity(intent);
    }
}
