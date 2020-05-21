package firebase.app.my_school_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Teacher_Result extends AppCompatActivity {

    EditText et_noticetitle,et_noticedis,et_obmrk,et_ttlmrk;
    DatabaseReference databaseReference;
    Intent in;
    String key,cls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__teacher__result);

        in=getIntent();
        key=in.getStringExtra("key");
        cls=in.getStringExtra("class");

        et_noticetitle=(EditText)findViewById(R.id.et_noticetitle);
        et_noticedis=(EditText)findViewById(R.id.et_noticedis);
        et_obmrk=(EditText)findViewById(R.id.et_obmrk);
        et_ttlmrk=(EditText)findViewById(R.id.et_ttlmrk);
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }

    public void addResult(View view) {

        if (et_noticetitle.getText().toString().isEmpty() || et_noticedis.getText().toString().isEmpty() || et_obmrk.getText().toString().isEmpty() || et_ttlmrk.getText().toString().isEmpty()) {
            Toast.makeText(this, "Attributes are Empaty", Toast.LENGTH_SHORT).show();
        }

        else
        {
            String tsty = et_noticetitle.getText().toString().trim();
            String sub = et_noticedis.getText().toString().trim();
            String obmrk = et_obmrk.getText().toString().trim();
            String tlmrk = et_ttlmrk.getText().toString().trim();
            GetDateTime g = new GetDateTime();
            String time = g.getTimeDate();

            String ke=databaseReference.child("2019").child("student").child(cls).child(key).child("result").push().getKey();
            databaseReference.child("2019").child("student").child(cls).child(key).child("result").child(ke).child("testtype").setValue(tsty);
            databaseReference.child("2019").child("student").child(cls).child(key).child("result").child(ke).child("testsubject").setValue(sub);
            databaseReference.child("2019").child("student").child(cls).child(key).child("result").child(ke).child("markobtain").setValue(obmrk);
            databaseReference.child("2019").child("student").child(cls).child(key).child("result").child(ke).child("totalmark").setValue(tlmrk);
            databaseReference.child("2019").child("student").child(cls).child(key).child("result").child(ke).child("time").setValue(time);
            databaseReference.child("2019").child("student").child(cls).child(key).child("result").child(ke).child("key").setValue(ke);
            databaseReference.child("2019").child("student").child(cls).child(key).child("result").child(ke).child("studentkey").setValue(key);

            startActivity(new Intent(Add_Teacher_Result.this,Teacher_result.class));
            Toast.makeText(this, "Result Uploaded", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
