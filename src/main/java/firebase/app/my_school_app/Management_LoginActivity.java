package firebase.app.my_school_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Management_LoginActivity extends AppCompatActivity {

    ProgressBar progmng;
    FirebaseAuth auth;
    EditText et_userid,et_userpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management__login);

        auth=FirebaseAuth.getInstance();

        progmng=(ProgressBar)findViewById(R.id.progmng);
        et_userid=(EditText)findViewById(R.id.et_userid);
        et_userpass=(EditText)findViewById(R.id.et_userpass);
    }


    public void login(View view)

    {
        progmng.setVisibility(View.VISIBLE);
        if(et_userid.getText().toString().isEmpty())
        {
            et_userid.setError("Empty");
            et_userid.requestFocus();
        }
        else  if (et_userpass.getText().toString().isEmpty())
        {
            et_userpass.setError("Empty");
            et_userpass.requestFocus();
        }
        else
        {
           String email,password;
           email=et_userid.getText().toString().trim();
           password=et_userpass.getText().toString().trim();

           auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {

                   if (task.isSuccessful()) {
                       progmng.setVisibility(View.GONE);
                       Intent i=new Intent(Management_LoginActivity.this,Management_HomeActivity.class);
                        startActivity(i);
                        finish();
                       Toast.makeText(Management_LoginActivity.this, "Welcome to Management Home Activity", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       progmng.setVisibility(View.GONE);
                       Toast.makeText(Management_LoginActivity.this, "Wrong Email And Password", Toast.LENGTH_SHORT).show();
                   }
               }
           });
        }

    }
}
