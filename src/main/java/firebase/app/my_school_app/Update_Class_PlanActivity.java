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

public class Update_Class_PlanActivity extends AppCompatActivity {

    EditText UpNoTl,UpNoDis;
    Button UpNoBtn,DltNoBtn;
    String key,t,d,Tkey;
    Intent in;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__class__plan);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        UpNoBtn=(Button)findViewById(R.id.UpNoBtn);
        DltNoBtn=(Button)findViewById(R.id.DltNoBtn);
        UpNoTl=(EditText)findViewById(R.id.UpNoTl);
        UpNoDis=(EditText)findViewById(R.id.UpNoDis);

        in=getIntent();
        key=in.getStringExtra("key");
        Tkey=in.getStringExtra("teacherkey");

        databaseReference.child("2019").child("teacher").child(Tkey).child("classplan").child(key).addValueEventListener(new ValueEventListener() {
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

    public void updateplan(View view) {

        String upt,updis;
        upt=UpNoTl.getText().toString().trim();
        updis=UpNoDis.getText().toString().trim();
        databaseReference.child("2019").child("teacher").child(Tkey).child("classplan").child(key).child("title").setValue(upt);
        databaseReference.child("2019").child("teacher").child(Tkey).child("classplan").child(key).child("text").setValue(updis);
        startActivity(new Intent(Update_Class_PlanActivity.this,Class_PlanActivity.class));
        finish();
        Toast.makeText(this, "Notice Updated", Toast.LENGTH_SHORT).show();
    }

    public void deleteplan(View view) {
        databaseReference.child("2019").child("teacher").child(Tkey).child("classplan").child(key).removeValue();
        startActivity(new Intent(Update_Class_PlanActivity.this,Class_PlanActivity.class));
        finish();
        Toast.makeText(this, "Notice Deleted", Toast.LENGTH_SHORT).show();
    }
}
