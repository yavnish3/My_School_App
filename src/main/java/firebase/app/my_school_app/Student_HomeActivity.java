package firebase.app.my_school_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Student_HomeActivity extends AppCompatActivity {

    Intent in;
    String key,cls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__home);

        in=getIntent();
        key=in.getStringExtra("key");
        cls=in.getStringExtra("class");
    }
    public void stprofile(View view)
    {
       Intent intent=new Intent(Student_HomeActivity.this,Profile_ViewActivity.class);
       intent.putExtra("key",key);
       intent.putExtra("class",cls);
       startActivity(intent);

    }
    public void stnotice(View view)
    {
        startActivity(new Intent(Student_HomeActivity.this,Notice_BordActivity.class));
    }
    public void sthomework(View view)
    {
        Intent intent=new Intent(Student_HomeActivity.this,Show_Homw_workActivity.class);
        intent.putExtra("key",key);
        intent.putExtra("class",cls);
        startActivity(intent);
    }
    public void stmessage(View view)
    {
        Intent intent=new Intent(Student_HomeActivity.this,Sudent_Message.class);
        intent.putExtra("key",key);
        intent.putExtra("class",cls);
        startActivity(intent);
        Toast.makeText(this,"Welcome to Student Message",Toast.LENGTH_SHORT).show();
    }


    public void stsetting(View view) {

        Intent intent=new Intent(Student_HomeActivity.this,Student_Setting.class);
        intent.putExtra("key",key);
        intent.putExtra("class",cls);
        startActivity(intent);
        Toast.makeText(this,"Welcome to Student Settings",Toast.LENGTH_SHORT).show();
    }

    public void stresult(View view) {
        Intent intent=new Intent(Student_HomeActivity.this,Student_Result.class);
        intent.putExtra("key",key);
        intent.putExtra("class",cls);
        startActivity(intent);
        Toast.makeText(this,"Welcome to Student Results",Toast.LENGTH_SHORT).show();

    }
}
