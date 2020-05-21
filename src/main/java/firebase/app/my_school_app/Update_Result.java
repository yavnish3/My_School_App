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

public class Update_Result extends AppCompatActivity {

    EditText tsty,sub,mrkob,tlmrk;
    Button UpNoBtn,DltNoBtn;
    String key,t,d,Tkey,clss,ob,tl;
    Intent in;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__result);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        UpNoBtn=(Button)findViewById(R.id.UpNoBtn);
        DltNoBtn=(Button)findViewById(R.id.DltNoBtn);
        tsty=(EditText)findViewById(R.id.tsty);
        sub=(EditText)findViewById(R.id.sub);
        mrkob=(EditText)findViewById(R.id.mrkob);
        tlmrk=(EditText)findViewById(R.id.tlmrk);

        in=getIntent();
        key=in.getStringExtra("key");
        clss=in.getStringExtra("class");
        Tkey=in.getStringExtra("studentkey");

        databaseReference.child("2019").child("student").child(clss).child(Tkey).child("result").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                t=dataSnapshot.child("testtype").getValue(String.class);
                d=dataSnapshot.child("testsubject").getValue(String.class);
                ob=dataSnapshot.child("markobtain").getValue(String.class);
                tl=dataSnapshot.child("totalmark").getValue(String.class);
                tsty.setText(t);
                sub.setText(d);
                mrkob.setText(ob);
                tlmrk.setText(tl);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updatrslt(View view) {

        String ty,s,om,tm;
        ty=tsty.getText().toString().trim();
        s=sub.getText().toString().trim();
        om=mrkob.getText().toString().trim();
        tm=tlmrk.getText().toString().trim();
        databaseReference.child("2019").child("student").child(clss).child(Tkey).child("result").child(key).child("testtype").setValue(ty);
        databaseReference.child("2019").child("student").child(clss).child(Tkey).child("result").child(key).child("testsubject").setValue(s);
        databaseReference.child("2019").child("student").child(clss).child(Tkey).child("result").child(key).child("markobtain").setValue(om);
        databaseReference.child("2019").child("student").child(clss).child(Tkey).child("result").child(key).child("totalmark").setValue(tm);
        startActivity(new Intent(Update_Result.this,Teacher_result.class));
        Toast.makeText(this, "Result Updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void deletereslt(View view) {

        databaseReference.child("2019").child("student").child(clss).child(Tkey).child("result").child(key).removeValue();
        startActivity(new Intent(Update_Result.this,Teacher_result.class));
        Toast.makeText(this, "result Deleted", Toast.LENGTH_SHORT).show();
        finish();
    }
}
