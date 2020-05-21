package firebase.app.my_school_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_NoticeActivity extends AppCompatActivity {

    EditText et_noticetitle,et_noticedis;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__notice);
        et_noticetitle=(EditText)findViewById(R.id.et_noticetitle);
        et_noticedis=(EditText)findViewById(R.id.et_noticedis);
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }
    public void addnotice(View view) {
        if (et_noticetitle.getText().toString().isEmpty()||et_noticedis.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Notic Title OR Discription is Empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String sub=et_noticetitle.getText().toString().trim();
            String dis=et_noticedis.getText().toString().trim();
            String key=databaseReference.child("2019").child("notice").push().getKey();
            databaseReference.child("2019").child("notice")
                    .child(key).child("title").setValue(sub);

            databaseReference.child("2019").child("notice")
                    .child(key).child("text").setValue(dis);

            databaseReference.child("2019").child("notice")
                    .child(key).child("key").setValue(key);


            Toast.makeText(this, "Notic Uploaded", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(Add_NoticeActivity.this,Management_NoticeActivity.class));
            finish();

        }
    }

}
