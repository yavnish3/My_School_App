package firebase.app.my_school_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Teacher_Profile_DetailsActivity extends AppCompatActivity {

   public Intent in;
    public String key;
    public EditText ets[]=new EditText[12];
    public int ids[]={
            R.id.name,
            R.id.fathername,
            R.id.tv_email,
            R.id.tv_qulification,
            R.id.tv_address,
            R.id.tv_category,
            R.id.tv_subject,
            R.id.tv_mobile,
            R.id.tv_dob,
            R.id.tv_adhar,
            R.id.tv_gender,
            R.id.tv_password
    };
    public int i;
    public DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__profile__details);


        for (i=0;i<ets.length;i++)
        {
          ets[i]=(EditText)findViewById(ids[i]);
        }

        in=getIntent();
        key=in.getStringExtra("key");
        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("2019").child("teacher").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ets[0].setText(dataSnapshot.child("name").getValue(String.class));
                ets[1].setText(dataSnapshot.child("fathername").getValue(String.class));
                ets[2].setText(dataSnapshot.child("email").getValue(String.class));
                ets[3].setText(dataSnapshot.child("qualification").getValue(String.class));
                ets[4].setText(dataSnapshot.child("address").getValue(String.class));
                ets[5].setText(dataSnapshot.child("category").getValue(String.class));
                ets[6].setText(dataSnapshot.child("subject").getValue(String.class));
                ets[7].setText(dataSnapshot.child("mobile").getValue(String.class));
                ets[8].setText(dataSnapshot.child("dateofbirth").getValue(String.class));
                ets[9].setText(dataSnapshot.child("adhar").getValue(String.class));
                ets[10].setText(dataSnapshot.child("gender").getValue(String.class));
                ets[11].setText(dataSnapshot.child("password").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void update(View view) {
        String values[]=new String[ets.length];
        for (i=0;i<ets.length;i++)
        {
            values[i]=ets[i].getText().toString().trim();
        }
        databaseReference.child("2019").child("teacher").child(key).child("name").setValue(values[0]);
        databaseReference.child("2019").child("teacher").child(key).child("fathername").setValue(values[1]);
        databaseReference.child("2019").child("teacher").child(key).child("email").setValue(values[2]);
        databaseReference.child("2019").child("teacher").child(key).child("qualification").setValue(values[3]);
        databaseReference.child("2019").child("teacher").child(key).child("address").setValue(values[4]);
        databaseReference.child("2019").child("teacher").child(key).child("category").setValue(values[5]);
        databaseReference.child("2019").child("teacher").child(key).child("subject").setValue(values[6]);
        databaseReference.child("2019").child("teacher").child(key).child("mobile").setValue(values[7]);
        databaseReference.child("2019").child("teacher").child(key).child("dateofbirth").setValue(values[8]);
        databaseReference.child("2019").child("teacher").child(key).child("adhar").setValue(values[9]);
        databaseReference.child("2019").child("teacher").child(key).child("gender").setValue(values[10]);
        databaseReference.child("2019").child("teacher").child(key).child("password").setValue(values[11]);

        startActivity(new Intent(Teacher_Profile_DetailsActivity.this,Teacher_ListActivity.class));
        finish();
        Toast.makeText(this, "ProfileUpdated", Toast.LENGTH_LONG).show();
    }

    public void delete(View view) {
        databaseReference.child("2019").child("teacher").child(key).removeValue();
        startActivity(new Intent(Teacher_Profile_DetailsActivity.this,Teacher_ListActivity.class));
        finish();
        Toast.makeText(this, "Teacher Reamoved", Toast.LENGTH_SHORT).show();
    }
}
