package firebase.app.my_school_app;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;

public class Admission_FormActivity extends AppCompatActivity {


    RadioButton radio_male, radio_female;
    RadioGroup radio_group;
    String gender = "Male";
    DatabaseReference databaseReference;
    Spinner spinclass,spin_adcast;
    EditText []ets=new EditText[9];
    EditText email;
    int i;
    int ids[]={
            R.id.et_adname,
            R.id.adfname,
            R.id.admothername,
            R.id.et_adaddress,
            R.id.et_admobile,
            R.id.et_addob,
            R.id.et_adhar,
            R.id.et_nationalty,
            R.id.et_occupation
    };
    String values[]=new String[9];
    String []cast={
            "GEN","OBC","SC","ST"
    };
    String []classes={
            "NUR","KG","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
    };
    String cls,cat,mail,pass,sid,sid1,sid2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission__form);



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

        for(i=0;i<ets.length;i++)
        {
            ets[i]=(EditText)findViewById(ids[i]);
        }

        email=(EditText)findViewById(R.id.et_ademail);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        spinclass=(Spinner)findViewById(R.id.spinclass);
        spin_adcast=(Spinner)findViewById(R.id.spin_adcast);
        ArrayAdapter<String> ad=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,classes);
        ArrayAdapter<String> ad1=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,cast);
        spinclass.setAdapter(ad);
        spin_adcast.setAdapter(ad1);

    }

    public void addphoto(View view)
    {
        Toast.makeText(this, "Add Photo", Toast.LENGTH_SHORT).show();
    }
    public void submit(View view)
    {
        for (i=0;i<ets.length;i++)
        {
            if(ets[i].getText().toString().isEmpty())
            {
                ets[i].setError("empty");
                ets[i].requestFocus();
                break;
            }
        }



        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( 6 );
        for( int i = 0; i < 8; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        pass=sb.toString();




        if (i==ets.length)
        {
            for (i=0;i<ets.length;i++)
            {
                values[i]=ets[i].getText().toString().trim();
            }
            cls=spinclass.getSelectedItem().toString().trim();
            cat=spin_adcast.getSelectedItem().toString().trim();
           mail=email.getText().toString().trim();

            String key=databaseReference.child("2019").child("student").child(cls).push().getKey();
            databaseReference.child("2019").child("student").child(cls).child(key).child("studentid").setValue("");
            databaseReference.child("2019").child("student").child(cls).child(key).child("password").setValue(pass);
            databaseReference.child("2019").child("student").child(cls).child(key).child("gender").setValue(gender);
            databaseReference.child("2019").child("student").child(cls).child(key).child("name").setValue(values[0]);
            databaseReference.child("2019").child("student").child(cls).child(key).child("fathername").setValue(values[1]);
            databaseReference.child("2019").child("student").child(cls).child(key).child("mothername").setValue(values[2]);
            databaseReference.child("2019").child("student").child(cls).child(key).child("class").setValue(cls);
            databaseReference.child("2019").child("student").child(cls).child(key).child("email").setValue(mail);
            databaseReference.child("2019").child("student").child(cls).child(key).child("category").setValue(cat);
            databaseReference.child("2019").child("student").child(cls).child(key).child("address").setValue(values[3]);
            databaseReference.child("2019").child("student").child(cls).child(key).child("mobileno").setValue(values[4]);
            databaseReference.child("2019").child("student").child(cls).child(key).child("dateofbirth").setValue(values[5]);
            databaseReference.child("2019").child("student").child(cls).child(key).child("adhar").setValue(values[6]);
            databaseReference.child("2019").child("student").child(cls).child(key).child("nationalty").setValue(values[7]);
            databaseReference.child("2019").child("student").child(cls).child(key).child("occupation").setValue(values[8]);
            databaseReference.child("2019").child("student").child(cls).child(key).child("key").setValue(key);

            Toast.makeText(this, "admission done", Toast.LENGTH_SHORT).show();
            for (i=0;i<ets.length;i++)
            {
                ets[i].setText("");
            }
            email.setText("");
        }
        else {
            Toast.makeText(this, "admission not done" , Toast.LENGTH_SHORT).show();
        }
    }

}
