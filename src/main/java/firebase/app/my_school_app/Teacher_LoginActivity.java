package firebase.app.my_school_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Teacher_LoginActivity extends AppCompatActivity {

    ProgressBar proglogin;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    EditText et_user,et_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__login);

        proglogin=(ProgressBar)findViewById(R.id.proglogin);

        et_user=(EditText) findViewById(R.id.et_user);
        et_pass=(EditText) findViewById(R.id.et_pass);

    }
    public void login(View view)
    {
        proglogin.setVisibility(View.VISIBLE);
        final String email,pass;
        email=et_user.getText().toString().trim();
        pass=et_pass.getText().toString().trim();
        Query query=databaseReference.child("2019").child("teacher").orderByChild("email").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildren().iterator().hasNext())
                {
                    for (DataSnapshot data:dataSnapshot.getChildren())
                    {
                        String password=data.child("password").getValue(String.class);
                        String key=data.child("key").getValue(String.class);
                        if (pass.equals(password))
                        {
                            Intent i=new Intent(Teacher_LoginActivity.this,Teacher_HomeActivity.class);
                            i.putExtra("key",key);
                            startActivity(i);
                            finish();
                        }
                        else {
                            proglogin.setVisibility(View.GONE);
                            Toast.makeText(Teacher_LoginActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                else
                {
                    proglogin.setVisibility(View.GONE);
                    Toast.makeText(Teacher_LoginActivity.this, "Email Id not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
