package firebase.app.my_school_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Student_Full_Message extends AppCompatActivity {

    TextView UpNoTl,UpNoDis;
    String key,t,d,Tkey,clss;
    Intent in;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__full__message);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        UpNoTl=(TextView) findViewById(R.id.UpNoTl);
        UpNoDis=(TextView) findViewById(R.id.UpNoDis);

        in=getIntent();
        key=in.getStringExtra("key");
        clss=in.getStringExtra("class");
        Tkey=in.getStringExtra("studentkey");

        databaseReference.child("2019").child("student").child(clss).child(Tkey).child("message").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                t=dataSnapshot.child("title").getValue(String.class);
                d=dataSnapshot.child("text").getValue(String.class);
                UpNoTl.setText(t);
                UpNoDis.setText(d);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
