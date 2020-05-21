package firebase.app.my_school_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update_Home_WorkActivity extends AppCompatActivity {

    EditText UpNoTl,UpNoDis;
    Button UpNoBtn,DltNoBtn;
    String key,t,d,c,cls;
    Intent in;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__home__work);



        databaseReference= FirebaseDatabase.getInstance().getReference();
        UpNoBtn=(Button)findViewById(R.id.UpNoBtn);
        DltNoBtn=(Button)findViewById(R.id.DltNoBtn);
        UpNoTl=(EditText)findViewById(R.id.UpNoTl);
        UpNoDis=(EditText)findViewById(R.id.UpNoDis);

        in=getIntent();
        key=in.getStringExtra("key");
        cls=in.getStringExtra("class");
        databaseReference.child("2019").child("student").child(cls).child("homework")
                .child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                t=dataSnapshot.child("subject").getValue(String.class);
                c=dataSnapshot.child("class").getValue(String.class);
                d=dataSnapshot.child("text").getValue(String.class);
                UpNoTl.setText(t);
                UpNoDis.setText(d);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deleteNotice(View view) {

        databaseReference.child("2019").child("student").child(cls).child("homework").child(key).removeValue();
        startActivity(new Intent(Update_Home_WorkActivity.this,Home_WorkActivity.class));
        finish();
        Toast.makeText(this, "Home Work Deleted", Toast.LENGTH_LONG).show();

    }
    public void updatenotice(View view) {
        String upt,updis;
        upt=UpNoTl.getText().toString().trim();
        updis=UpNoDis.getText().toString().trim();
        databaseReference.child("2019").child("student").child(cls).child("homework").child(key).child("subject").setValue(upt);
        databaseReference.child("2019").child("student").child(cls).child("homework").child(key).child("text").setValue(updis);

        startActivity(new Intent(Update_Home_WorkActivity.this,Home_WorkActivity.class));
        finish();
        Toast.makeText(this, "Home Work Updated", Toast.LENGTH_LONG).show();


    }

}