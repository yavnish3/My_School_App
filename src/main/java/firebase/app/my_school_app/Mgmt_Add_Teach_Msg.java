package firebase.app.my_school_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Mgmt_Add_Teach_Msg extends AppCompatActivity {

    EditText et_noticetitle,et_noticedis;
    DatabaseReference databaseReference;
    Intent in;
    String key,cls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgmt__add__teach__msg);

        in=getIntent();
        key=in.getStringExtra("key");

        et_noticetitle=(EditText)findViewById(R.id.et_noticetitle);
        et_noticedis=(EditText)findViewById(R.id.et_noticedis);
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }

    public void uploadMessage(View view) {

        if (et_noticetitle.getText().toString().isEmpty() || et_noticedis.getText().toString().isEmpty()) {
            Toast.makeText(this, "Message Title OR Discription is Empty", Toast.LENGTH_SHORT).show();
        }
        else {
            String sub = et_noticetitle.getText().toString().trim();
            String dis = et_noticedis.getText().toString().trim();
            GetDateTime g = new GetDateTime();
            String time = g.getTimeDate();

            String ke=databaseReference.child("2019").child("teacher").child(key).child("message").push().getKey();
            databaseReference.child("2019").child("teacher").child(key).child("message").child(ke).child("title").setValue(sub);
            databaseReference.child("2019").child("teacher").child(key).child("message").child(ke).child("text").setValue(dis);
            databaseReference.child("2019").child("teacher").child(key).child("message").child(ke).child("time").setValue(time);
            databaseReference.child("2019").child("teacher").child(key).child("message").child(ke).child("key").setValue(ke);
            databaseReference.child("2019").child("teacher").child(key).child("message").child(ke).child("teacherkey").setValue(key);

            startActivity(new Intent(Mgmt_Add_Teach_Msg.this,Mgmt_Teach_Msge.class));
            finish();
            Toast.makeText(this, "Message Uploaded", Toast.LENGTH_SHORT).show();
        }
    }
}
