package uk.co.ianvallely.canvasstool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewCanvassPg4 extends AppCompatActivity {

    String uName;
    String respondantName;
    String address;
    String postCode;
    String answer1;
    String answer2;
    String answer3;
    Button yes;
    Button no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_canvass_pg4);



        Intent in = getIntent();
        Bundle b = in.getExtras();
        respondantName = b.getString("responder");
        address = b.getString("address");
        postCode = b.getString("postecode");
        answer1 = b.getString("q1");
        answer2 = b.getString("q2");

        yes = (Button) findViewById(R.id.q3_yes);
        no = (Button) findViewById(R.id.q3_no);

        yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                answer3 = "yes";
                NextActiviy(respondantName, address, postCode, answer1, answer2, answer3);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                answer3 = "no";
                NextActiviy(respondantName, address, postCode, answer1, answer2, answer3);
            }
        });



    }

    public void NextActiviy (String respondantName, String address, String postCode,
                             String answer1, String answer2, String answer3){
        Intent intent = new Intent(this, NewCanvassPg6.class);
        Bundle extras = new Bundle();
        extras.putString("responder",respondantName);
        extras.putString("address", address);
        extras.putString("postecode", postCode);
        extras.putString("q1", answer1);
        extras.putString("q2", answer2);
        extras.putString("q3", answer3);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
