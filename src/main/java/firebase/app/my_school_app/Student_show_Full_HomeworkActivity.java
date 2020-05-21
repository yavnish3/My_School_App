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

public class Student_show_Full_HomeworkActivity extends AppCompatActivity {

    TextView UpNoTl,UpNoDis;
    String key,t,d,cls;
    Intent in;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_show__full__homework);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        UpNoTl=(TextView) findViewById(R.id.UpNoTl);
        UpNoDis=(TextView) findViewById(R.id.UpNoDis);
        in=getIntent();
        key=in.getStringExtra("key");
        cls=in.getStringExtra("class");


        databaseReference.child("2019").child("student").child(cls).child("homework")
                .child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                t = dataSnapshot.child("subject").getValue(String.class);
                d = dataSnapshot.child("text").getValue(String.class);
                UpNoTl.setText(t);
                UpNoDis.setText(d);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
