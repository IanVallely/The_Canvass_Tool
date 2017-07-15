package uk.co.ianvallely.canvasstool;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class ConfirmDelete extends AppCompatActivity {

    Button del1;
    Button del2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_delete);

        del1= (Button) findViewById(R.id.btn_delMain);
        del2= (Button) findViewById(R.id.btn_delBackup);

        del1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename="CanvassData.csv";
                Environment.getExternalStorageDirectory().toString();
                File file = new File(Environment.getExternalStorageDirectory().toString()
                        + File.separator+ "CanvassTool" + File.separator, filename);
                file.delete();
                Toast.makeText(getBaseContext(), "You have sucessfully DELETED the main date file", Toast.LENGTH_SHORT).show();
            }
        });

        del2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String backup= "CanvassData.csv";
                Environment.getExternalStorageDirectory().toString();
                File file = new File(Environment.getExternalStorageDirectory().toString()
                        + File.separator+ "CanvassTool" + File.separator + "Backup" + File.separator, backup);
                file.delete();
                Toast.makeText(getBaseContext(), "You have sucessfully DELETED the backup date file", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
