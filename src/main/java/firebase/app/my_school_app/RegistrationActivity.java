package firebase.app.my_school_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    Spinner spin_class;
    String []classes={
            "NUR","KG","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
    };
    ArrayAdapter ad;
    EditText[] ets = new EditText[4];
    RadioButton radio_male, radio_female;
    RadioGroup radio_group;
    String gender = "Male",email;
    int[] ids = {
            R.id.et_name,
            R.id.et_fname,
            R.id.et_address,
            R.id.et_mbnm
    };
    EditText et_email;
    String[] values = new String[4];
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        spin_class=(Spinner) findViewById(R.id.spin_class);
        et_email=(EditText)findViewById(R.id.et_email);
        for (i = 0; i < ets.length; i++) {
            ets[i] = (EditText) findViewById(ids[i]);
        }
        radio_male = (RadioButton) findViewById(R.id.radio_male);
        radio_female = (RadioButton) findViewById(R.id.radio_female);
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int radio) {
                switch (radio) {
                    case R.id.radio_male:
                        gender = "male";
                        break;
                    case R.id.radio_female:
                        gender = "Female";
                        break;
                }
            }
        });
        ad=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,classes);
        spin_class.setAdapter(ad);
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }
    public void register(View view) {

        for (i = 0; i < ets.length; i++) {
            if (ets[i].getText().toString().isEmpty()) {
                ets[i].setError("Empty");
                ets[i].requestFocus();
                break;
            }
        }
        if (i == ets.length) {
            email=et_email.getText().toString().trim();

            for (i = 0; i < ets.length; i++) {
                values[i] = ets[i].getText().toString().trim();

                }
            String key=databaseReference.child("2019").child("newadmission").push().getKey();
            databaseReference.child("2019").child("newadmission").child(key).child("name").setValue(values[0]);
            databaseReference.child("2019").child("newadmission").child(key).child("fathername").setValue(values[1]);
            databaseReference.child("2019").child("newadmission").child(key).child("address").setValue(values[2]);
            databaseReference.child("2019").child("newadmission").child(key).child("mobilenumber").setValue(values[3]);
            databaseReference.child("2019").child("newadmission").child(key).child("gender").setValue(gender);
            databaseReference.child("2019").child("newadmission").child(key).child("email").setValue(email);
            databaseReference.child("2019").child("newadmission").child(key).child("class").setValue(spin_class.getSelectedItem().toString());
            databaseReference.child("2019").child("newadmission").child(key).child("key").setValue(key);

                Toast.makeText(this, "Registration done", Toast.LENGTH_SHORT).show();
            for (i=0;i<ets.length;i++)
            {
                ets[i].setText("");
            }
            et_email.setText("");

        }
        else
            {
                Toast.makeText(this, "Registration not done", Toast.LENGTH_SHORT).show();
            }
    }
}
