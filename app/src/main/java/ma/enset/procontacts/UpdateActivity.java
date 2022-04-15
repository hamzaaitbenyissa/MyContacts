package ma.enset.procontacts;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateActivity extends AppCompatActivity {

    EditText name_input, job_input, phone_input;
    Button update_button, delete_button;

    String id, name, job, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        RoomDb roomDb;
        roomDb = RoomDb.getInstance(this);


        name_input = findViewById(R.id.name_input2);
        job_input = findViewById(R.id.job_input2);
        phone_input = findViewById(R.id.phone_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar name after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                roomDb.contactDAO().update(new Contact(id, name_input.getText().toString(), job_input.getText().toString(), phone_input.getText().toString()));
                Toast.makeText(UpdateActivity.this,"Updated",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("job") && getIntent().hasExtra("phone")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            job = getIntent().getStringExtra("job");
            phone = getIntent().getStringExtra("phone");

            //Setting Intent Data
            name_input.setText(name);
            job_input.setText(job);
            phone_input.setText(phone);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RoomDb roomDb;
                roomDb = RoomDb.getInstance(UpdateActivity.this);
                roomDb.contactDAO().delete(id);

                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
