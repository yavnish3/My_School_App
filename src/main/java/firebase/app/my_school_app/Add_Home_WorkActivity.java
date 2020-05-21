package firebase.app.my_school_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Home_WorkActivity extends AppCompatActivity {

    EditText et_subname,et_homeworkdis;
    //FirebaseUser firebaseUser;
    DatabaseReference databaseReference;



    String []classes={
            "NUR","KG","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
    };
    String cls;
    Spinner spinclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__home_work);
        et_subname=(EditText)findViewById(R.id.et_subname);
        et_homeworkdis=(EditText)findViewById(R.id.et_homeworkdis);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        //firebaseUser= FirebaseAuth.getInstance().getCurrentUser();


        spinclass=(Spinner)findViewById(R.id.spinclass);
        ArrayAdapter<String> ad=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,classes);
        spinclass.setAdapter(ad);

    }

    public void addhomework(View view) {
        if (et_subname.getText().toString().isEmpty()||et_homeworkdis.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Subject OR Discription is Empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            cls=spinclass.getSelectedItem().toString().trim();
            String sub=et_subname.getText().toString().trim();
            String dis=et_homeworkdis.getText().toString().trim();
            String key=databaseReference.child("2019").child("student").child(cls).child("homework").push().getKey();
            databaseReference.child("2019").child("student").child(cls).child("homework")
                    .child(key).child("subject").setValue(sub);

            databaseReference.child("2019").child("student").child(cls).child("homework")
                    .child(key).child("text").setValue(dis);

            databaseReference.child("2019").child("student").child(cls).child("homework")
                    .child(key).child("key").setValue(key);
            databaseReference.child("2019").child("student").child(cls).child("homework")
                    .child(key).child("class").setValue(cls);


            Toast.makeText(this, "Home Work Uploaded", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(Add_Home_WorkActivity.this,Home_WorkActivity.class));
            finish();

        }
    }
}
