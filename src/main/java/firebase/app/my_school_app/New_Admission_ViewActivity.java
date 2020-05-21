package firebase.app.my_school_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;

public class New_Admission_ViewActivity extends AppCompatActivity {

    TextView tvs[]=new TextView[7];
    int ids[]={
            R.id.name,
            R.id.father,
            R.id.Class,
            R.id.email,
            R.id.address,
            R.id.gender,
            R.id.mobile
    };
    DatabaseReference databaseReference;
    Intent in;
    String key,cls;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__admission__view);

        for (i=0;i<tvs.length;i++)
        {
            tvs[i]=(TextView)findViewById(ids[i]);
        }
        databaseReference= FirebaseDatabase.getInstance().getReference();
        in=getIntent();
        key=in.getStringExtra("key");
        databaseReference.child("2019").child("newadmission").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tvs[0].setText(dataSnapshot.child("name").getValue(String.class));
                tvs[1].setText(dataSnapshot.child("fathername").getValue(String.class));
                tvs[2].setText(dataSnapshot.child("class").getValue(String.class));
                tvs[3].setText(dataSnapshot.child("email").getValue(String.class));
                tvs[4].setText(dataSnapshot.child("address").getValue(String.class));
                tvs[5].setText(dataSnapshot.child("gender").getValue(String.class));
                tvs[6].setText(dataSnapshot.child("mobilenumber").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void delete(View view) {
        databaseReference.child("2019").child("newadmission").child(key).removeValue();
        startActivity(new Intent(New_Admission_ViewActivity.this,NewAdmission_ListActivity.class));
        finish();
        Toast.makeText(this, "admission Removed", Toast.LENGTH_LONG).show();
    }

    public void verify(View view) {
        String values[]=new String[tvs.length];
        for (i=0;i<tvs.length;i++)
        {
            values[i]=tvs[i].getText().toString().trim();
        }
        cls=values[2];

        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( 8 );
        for( int i = 0; i < 8; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
       String pass=sb.toString();


        databaseReference.child("2019").child("student").child(cls).child(key).child("name").setValue(values[0]);
        databaseReference.child("2019").child("student").child(cls).child(key).child("key").setValue(key);
        databaseReference.child("2019").child("student").child(cls).child(key).child("fathername").setValue(values[1]);
        databaseReference.child("2019").child("student").child(cls).child(key).child("class").setValue(values[2]);
        databaseReference.child("2019").child("student").child(cls).child(key).child("email").setValue(values[3]);
        databaseReference.child("2019").child("student").child(cls).child(key).child("address").setValue(values[4]);
        databaseReference.child("2019").child("student").child(cls).child(key).child("gender").setValue(values[5]);
        databaseReference.child("2019").child("student").child(cls).child(key).child("mobileno").setValue(values[6]);
        databaseReference.child("2019").child("student").child(cls).child(key).child("mothername").setValue("--------");
        databaseReference.child("2019").child("student").child(cls).child(key).child("category").setValue("--------");
        databaseReference.child("2019").child("student").child(cls).child(key).child("dateofbirth").setValue("------");
        databaseReference.child("2019").child("student").child(cls).child(key).child("adhar").setValue("-------");
        databaseReference.child("2019").child("student").child(cls).child(key).child("nationalty").setValue("--------");
        databaseReference.child("2019").child("student").child(cls).child(key).child("occupation").setValue("-------");
        databaseReference.child("2019").child("student").child(cls).child(key).child("password").setValue(pass);
        databaseReference.child("2019").child("student").child(cls).child(key).child("studentid").setValue("-----");
        startActivity(new Intent(New_Admission_ViewActivity.this,NewAdmission_ListActivity.class));
        finish();
        Toast.makeText(this, "admission Done", Toast.LENGTH_LONG).show();
        databaseReference.child("2019").child("newadmission").child(key).removeValue();
    }
}
