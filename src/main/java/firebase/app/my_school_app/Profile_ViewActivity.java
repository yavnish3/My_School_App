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

public class Profile_ViewActivity extends AppCompatActivity {

    public Intent in;
    public String key,cls;
    public TextView ets[]=new TextView[15];
    public int i;
    public DatabaseReference databaseReference;
    public int ids[]={
            R.id.name,
            R.id.fathername,
            R.id.mothername,
            R.id.tv_class,
            R.id.tv_address,
            R.id.tv_category,
            R.id.tv_email,
            R.id.tv_mobile,
            R.id.tv_dob,
            R.id.tv_adhar,
            R.id.tv_nationalty,
            R.id.tv_occupation,
            R.id.tv_gender,
            R.id.tv_password,
            R.id.tv_studentid
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__view);

        for (i=0;i<ets.length;i++)
        {
            ets[i]=(TextView) findViewById(ids[i]);
        }

        in=getIntent();
        key=in.getStringExtra("key");
        cls=in.getStringExtra("class");
        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("2019").child("student").child(cls).child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ets[0].setText(dataSnapshot.child("name").getValue(String.class));
                ets[1].setText(dataSnapshot.child("fathername").getValue(String.class));
                ets[2].setText(dataSnapshot.child("mothername").getValue(String.class));
                ets[3].setText(dataSnapshot.child("class").getValue(String.class));
                ets[4].setText(dataSnapshot.child("address").getValue(String.class));
                ets[5].setText(dataSnapshot.child("category").getValue(String.class));
                ets[6].setText(dataSnapshot.child("email").getValue(String.class));
                ets[7].setText(dataSnapshot.child("mobileno").getValue(String.class));
                ets[8].setText(dataSnapshot.child("dateofbirth").getValue(String.class));
                ets[9].setText(dataSnapshot.child("adhar").getValue(String.class));
                ets[10].setText(dataSnapshot.child("nationalty").getValue(String.class));
                ets[11].setText(dataSnapshot.child("occupation").getValue(String.class));
                ets[12].setText(dataSnapshot.child("gender").getValue(String.class));
                ets[13].setText(dataSnapshot.child("password").getValue(String.class));
                ets[14].setText(dataSnapshot.child("studentid").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
