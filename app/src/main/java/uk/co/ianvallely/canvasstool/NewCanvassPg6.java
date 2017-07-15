package uk.co.ianvallely.canvasstool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class NewCanvassPg6 extends AppCompatActivity {

    String uName;
    String respondantName;
    String address;
    String postCode;
    String answer1;
    String answer2;
    String answer3;
    RadioButton q6_now;
    RadioButton q6_brexit;
    RadioButton q6_fiveYr;
    RadioButton q6_later;
    RadioButton q6_never;

    String nextRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_canvass_pg6);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        respondantName = b.getString("responder");
        address = b.getString("address");
        postCode = b.getString("postecode");
        answer1 = b.getString("q1");
        answer2 = b.getString("q2");
        answer3 = b.getString("q3");


        q6_now = (RadioButton) findViewById(R.id.rButtonNow);
        q6_brexit = (RadioButton) findViewById(R.id.rButtonAfterBrexit);
        q6_fiveYr = (RadioButton) findViewById(R.id.radioButtonFiveYrs);
        q6_later = (RadioButton) findViewById(R.id.radioButtonLater);
        q6_never = (RadioButton) findViewById(R.id.radioButtonNever);

        q6_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextRef = "now";
                NextActiviy(respondantName, address, postCode, answer1, answer2, answer3, nextRef);
            }

        });

        q6_brexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextRef = "AfterBrexit";
                NextActiviy(respondantName, address, postCode, answer1, answer2, answer3, nextRef);
            }
        });

        q6_fiveYr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextRef = "In next five years";
                NextActiviy(respondantName, address, postCode, answer1, answer2, answer3, nextRef);
            }
        });
        q6_later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextRef = "Later";
                NextActiviy(respondantName, address, postCode, answer1, answer2, answer3, nextRef);
            }
        });
        q6_never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextRef = "Never";
                NextActiviy(respondantName, address, postCode, answer1, answer2, answer3, nextRef);
            }
        });



        Log.d("MyActivity", " Respondand: " + respondantName + " address: " + address
                + " postecode: " + postCode + " q 1: " + answer1 + " answer 2: " + answer2 + " answer 3: "
        + answer3);

    }

    public void NextActiviy (String respondantName, String address, String postCode,
                             String answer1, String answer2, String answer3,  String nextRef){
        Intent intent = new Intent(this, NewCanvassPg7.class);
        Bundle extras = new Bundle();
        extras.putString("responder",respondantName);
        extras.putString("address", address);
        extras.putString("postecode", postCode);
        extras.putString("q1", answer1);
        extras.putString("q2", answer2);
        extras.putString("q3", answer3);
        extras.putString("nextRef", nextRef);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
