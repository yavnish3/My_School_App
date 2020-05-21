package firebase.app.my_school_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class St_LoginActivity extends AppCompatActivity {

    String []classes={
            "NUR","KG","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
    };
    String cls;
    Spinner spinclass;
    DatabaseReference databaseReference;
    ProgressBar progress;
    EditText et_userid,et_userpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st__login);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        progress=(ProgressBar)findViewById(R.id.progress);
        spinclass=(Spinner)findViewById(R.id.spinclass);
        ArrayAdapter<String> ad=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,classes);
        spinclass.setAdapter(ad);
        et_userid=(EditText)findViewById(R.id.et_userid);
        et_userpass=(EditText)findViewById(R.id.et_userpass);
    }
    public void login(View view)

    {
        progress.setVisibility(View.VISIBLE);
        cls=spinclass.getSelectedItem().toString().trim();
        final String email,pass;
        email=et_userid.getText().toString().trim();
        pass=et_userpass.getText().toString().trim();
        Query query=databaseReference.child("2019").child("student").child(cls).orderByChild("email").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildren().iterator().hasNext())
                {
                    for (DataSnapshot data:dataSnapshot.getChildren()) {

                        String password = data.child("password").getValue(String.class);
                        String key = data.child("key").getValue(String.class);
                        if (pass.equals(password))
                        {
                            progress.setVisibility(View.GONE);
                            Intent intent=new Intent(St_LoginActivity.this,Student_HomeActivity.class);
                            intent.putExtra("key",key);
                            intent.putExtra("class",cls);
                            startActivity(intent);
                            finish();

                        }
                        else
                        {
                            progress.setVisibility(View.GONE);
                            Toast.makeText(St_LoginActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    progress.setVisibility(View.GONE);
                    Toast.makeText(St_LoginActivity.this, "Email Id not found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
