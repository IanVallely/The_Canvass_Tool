package uk.co.ianvallely.canvasstool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class NewCanvassPg3 extends AppCompatActivity {

    String uName;
    String respondantName;
    String address;
    String postCode;
    String answer1;
    String answer2;
    Button yes;
    Button no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_canvass_pg3);



        Intent in = getIntent();
        Bundle b = in.getExtras();
        respondantName = b.getString("responder");
        address = b.getString("address");
        postCode = b.getString("postecode");
        answer1 = b.getString("q1");

        Log.d("MyActivity", " user: " + uName + " Respondand: " + respondantName + " address: " + address
                + " postecode: " + postCode + " q1: " + answer1);

        yes = (Button) findViewById(R.id.q2_yes);
        no = (Button) findViewById(R.id.q2_no);

        yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                answer2 = "yes";
                NextActiviy(respondantName, address, postCode, answer1, answer2);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                answer2 = "no";
                NextActiviy(respondantName, address, postCode, answer1, answer2 );
            }
        });
    }
    public void NextActiviy (String respondantName, String address, String postCode, String answer1, String answer2){
        Intent intent = new Intent(this, NewCanvassPg4.class);
        Bundle extras = new Bundle();
        extras.putString("responder",respondantName);
        extras.putString("address", address);
        extras.putString("postecode", postCode);
        extras.putString("q1", answer1);
        extras.putString("q2", answer2);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
