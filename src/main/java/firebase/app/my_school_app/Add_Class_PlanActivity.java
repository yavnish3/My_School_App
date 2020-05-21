package firebase.app.my_school_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Class_PlanActivity extends AppCompatActivity {

    EditText et_noticetitle,et_noticedis;
    DatabaseReference databaseReference;
    Intent in;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__class__plan);

        in=getIntent();
        key=in.getStringExtra("key");

        et_noticetitle=(EditText)findViewById(R.id.et_noticetitle);
        et_noticedis=(EditText)findViewById(R.id.et_noticedis);
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }

    public void addclassplane(View view) {

        if (et_noticetitle.getText().toString().isEmpty() || et_noticedis.getText().toString().isEmpty()) {
            Toast.makeText(this, "Class Plan Title OR Discription is Empty", Toast.LENGTH_SHORT).show();
        } else {
            String sub = et_noticetitle.getText().toString().trim();
            String dis = et_noticedis.getText().toString().trim();
            GetDateTime g=new GetDateTime();
            String time=g.getTimeDate();


            String ke=databaseReference.child("2019").child("teacher").child(key).child("classplan").push().getKey();
            databaseReference.child("2019").child("teacher").child(key).child("classplan").child(ke).child("title").setValue(sub);
            databaseReference.child("2019").child("teacher").child(key).child("classplan").child(ke).child("text").setValue(dis);
            databaseReference.child("2019").child("teacher").child(key).child("classplan").child(ke).child("time").setValue(time);
            databaseReference.child("2019").child("teacher").child(key).child("classplan").child(ke).child("key").setValue(ke);
            databaseReference.child("2019").child("teacher").child(key).child("classplan").child(ke).child("teacherkey").setValue(key);


            startActivity(new Intent(Add_Class_PlanActivity.this,Class_PlanActivity.class));
            finish();
            Toast.makeText(this, "Class Plan Uploaded", Toast.LENGTH_SHORT).show();
        }
    }
}
