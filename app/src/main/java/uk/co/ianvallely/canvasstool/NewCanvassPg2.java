package uk.co.ianvallely.canvasstool;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewCanvassPg2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        final String uName;
        final String respondantName;
        final String address;
        final String postCode;
        TextView q1;
        Button yes;
        Button no;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_canvass_pg2);
        q1 = (TextView) findViewById(R.id.q1);
        ((AppCompatActivity)this).getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        Intent in = getIntent();
        Bundle b = in.getExtras();
        respondantName = b.getString("responder");
        address = b.getString("address");
        postCode = b.getString("postecode");

        yes = (Button) findViewById(R.id.q1_yes);
        no = (Button) findViewById(R.id.q1_no);

        yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String answer1 = "yes";
                NextActiviy(respondantName, address, postCode, answer1);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String answer1 = "no";
                NextActiviy(respondantName, address, postCode, answer1);
            }
        });

    }
    public void NextActiviy (String respondantName, String address, String postCode, String answer){
        Intent intent = new Intent(this, NewCanvassPg3.class);
        Bundle extras = new Bundle();
        extras.putString("responder",respondantName);
        extras.putString("address", address);
        extras.putString("postecode", postCode);
        extras.putString("q1", answer);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * Let's the user tap the activity icon to go 'home'.
     * Requires setHomeButtonEnabled() in onCreate().
     */
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Options.class);
        startActivityForResult(myIntent, 0);
        return true;

    }
}
