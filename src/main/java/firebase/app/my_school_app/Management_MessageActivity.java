package firebase.app.my_school_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Management_MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management__message);
    }

    public void stmsg(View view) {
        Intent intent=new Intent(Management_MessageActivity.this,Mgmt_St_Msge.class);
        startActivity(intent);
    }

    public void teachermsg(View view) {
        Intent intent=new Intent(Management_MessageActivity.this, Mgmt_Teach_Msge.class);
        startActivity(intent);
    }
}
