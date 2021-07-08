package sg.edu.rp.c346.id20047518.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    Button btnRetrieve;
    Button btnStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        btnRetrieve = findViewById(R.id.buttonRetrieve);
        btnStore = findViewById(R.id.buttonStore);

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                String name = prefs.getString("name", "John Doe");
                String gpa = prefs.getString("gpa", String.valueOf(4.0f));
                etName.setText(name);
                etGPA.setText(gpa + "");
            }
        });

        btnStore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("name", name);
                prefEdit.putString("gpa", String.valueOf(gpa));
                prefEdit.commit();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        String name = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("name", name);
        prefEdit.putString("gpa", String.valueOf(gpa));
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String name = prefs.getString("name", "John Doe");
        String gpa = prefs.getString("gpa", String.valueOf(4.0f));
        etName.setText(name);
        etGPA.setText(gpa + "");
        //Toast toast = Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG);
        //toast.show();
    }
}