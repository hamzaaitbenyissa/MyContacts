package ma.enset.procontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;


public class AddActivity extends AppCompatActivity {

    EditText name_input, job_input, phone_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        RoomDb roomDb;
        roomDb = RoomDb.getInstance(this);

        name_input = findViewById(R.id.name_input);
        job_input = findViewById(R.id.job_input);
        phone_input = findViewById(R.id.phone_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                roomDb.contactDAO().insert(new Contact(UUID.randomUUID().toString(),name_input.getText().toString().trim(),
                        job_input.getText().toString().trim(),
                        phone_input.getText().toString().trim()));

                Toast.makeText(AddActivity.this,"Added",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
