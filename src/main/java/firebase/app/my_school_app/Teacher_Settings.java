package firebase.app.my_school_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Teacher_Settings extends AppCompatActivity {

    EditText npass,cnpass;
    Intent in;
    DatabaseReference databaseReference;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__settings);
        npass=(EditText)findViewById(R.id.npass);
        cnpass=(EditText)findViewById(R.id.cnpass);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        in=getIntent();
        key=in.getStringExtra("key");
    }

    public void updatepass(View view) {
        if (npass.getText().toString().isEmpty()&&cnpass.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Password is emputy", Toast.LENGTH_SHORT).show();
        }
        else

        {
            String upt,up;
            upt=npass.getText().toString().trim();
            up=cnpass.getText().toString().trim();
            if(upt.equals(up)) {
                databaseReference.child("2019").child("teacher").child(key).child("password").setValue(upt);
                startActivity(new Intent(Teacher_Settings.this, Teacher_LoginActivity.class));
                Toast.makeText(this, "Password Updated", Toast.LENGTH_SHORT).show();
                finish();

            }
            else
            {
                Toast.makeText(this, "Confirm Password is different", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
