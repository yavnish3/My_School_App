package firebase.app.my_school_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Management_HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management__home);
    }
    public void studentdetails(View view)
    {
        startActivity(new Intent(Management_HomeActivity.this,Student_List_ClassActivity.class));

        Toast.makeText(this,"Welcome to Student Details",Toast.LENGTH_SHORT).show();
    }

    public void teacherdetails(View view)
    {
        startActivity(new Intent(Management_HomeActivity.this,Teacher_ListActivity.class));
        Toast.makeText(this,"Welcome to Teacher Details",Toast.LENGTH_SHORT).show();
    }

    public void messages(View view)
    {
        startActivity(new Intent(Management_HomeActivity.this,Management_MessageActivity.class));

        Toast.makeText(this,"Welcome to mnagement Messages",Toast.LENGTH_SHORT).show();
    }

    public void uploadnotice(View view)
    {
        startActivity(new Intent(Management_HomeActivity.this,Management_NoticeActivity.class));
    }
    public void newadmission(View view)
    {
        startActivity(new Intent(Management_HomeActivity.this,NewAdmission_ListActivity.class));
    }

    public void addstudent(View view)
    {
        Intent i=new Intent(Management_HomeActivity.this,Admission_FormActivity.class);
        startActivity(i);
    }

    public void addteacher(View view)
    {
        Intent i=new Intent(Management_HomeActivity.this,Add_TeacherActivity.class);
        startActivity(i);
        Toast.makeText(this,"Welcome to Add Teacher",Toast.LENGTH_SHORT).show();
    }

}
