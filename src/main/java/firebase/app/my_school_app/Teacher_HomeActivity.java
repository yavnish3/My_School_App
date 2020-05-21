package firebase.app.my_school_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Teacher_HomeActivity extends AppCompatActivity {

    Intent intent;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__home);

        intent=getIntent();
        key=intent.getStringExtra("key");
    }
    public void classplan(View view)
    {
        Intent i=new Intent(Teacher_HomeActivity.this,Class_PlanActivity.class);
        i.putExtra("key",key);
        startActivity(i);
    }
    public void temessage(View view)
    {
        Intent i=new Intent(Teacher_HomeActivity.this,Teacher_Message.class);
        i.putExtra("key",key);
        startActivity(i);
    }
    public void tenotice(View view)
    {
        startActivity(new Intent(Teacher_HomeActivity.this,Notice_BordActivity.class));
    }

    public void teacheraddwork(View view) {
        startActivity(new Intent(Teacher_HomeActivity.this,Home_WorkActivity.class));
    }

    public void profile(View view) {

        Intent i=new Intent(Teacher_HomeActivity.this,Profile_TeacherActivity.class);
        i.putExtra("key",key);
        startActivity(i);

    }
    public void teresult(View view) {
        Intent i=new Intent(Teacher_HomeActivity.this,Teacher_result.class);
        i.putExtra("key",key);
        startActivity(i);

    }

    public void teachersettings(View view) {

        Intent i=new Intent(Teacher_HomeActivity.this,Teacher_Settings.class);
        i.putExtra("key",key);
        startActivity(i);
    }
}
