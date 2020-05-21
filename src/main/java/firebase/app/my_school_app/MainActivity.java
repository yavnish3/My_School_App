package firebase.app.my_school_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void stlogin(View view)
    {
        Intent it=new Intent(this,St_LoginActivity.class);
        startActivity(it);
    }
    public void about(View view)
    {
        Intent it=new Intent(this,About_Us.class);
        startActivity(it);
        Toast.makeText(this,"Welcome to about us",Toast.LENGTH_SHORT).show();
    }
    public void management(View view)
    {
        Intent it=new Intent(this,Management_LoginActivity.class);
        startActivity(it);


    }
    public void teachlogin(View view)
    {
        Intent it=new Intent(this,Teacher_LoginActivity.class);
        startActivity(it);
    }
    public void admission(View view)
    {
        Intent it=new Intent(this,RegistrationActivity.class);
        startActivity(it);

    }
}
