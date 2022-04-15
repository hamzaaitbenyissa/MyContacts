package ma.enset.MyContacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddActivity extends AppCompatActivity {

    EditText name_input, job_input, phone_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        job_input = findViewById(R.id.job_input);
        phone_input = findViewById(R.id.phone_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addContact(name_input.getText().toString().trim(),
                        job_input.getText().toString().trim(),
                        phone_input.getText().toString().trim(), "");
            }
        });
    }
}
