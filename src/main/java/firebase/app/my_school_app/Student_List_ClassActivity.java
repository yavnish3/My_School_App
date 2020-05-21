package firebase.app.my_school_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Student_List_ClassActivity extends AppCompatActivity {

    String []classes={
            "NUR","KG","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
    };
    String cls;
    DatabaseReference databaseReference;
    ArrayList<String> mName=new ArrayList<String>();
    ArrayList<String> mFname=new ArrayList<String>();
    ArrayList<String> mDp=new ArrayList<String>();
    ArrayList<String> mclass=new ArrayList<String>();
    ArrayList<String> mKey=new ArrayList<String>();
    ArrayList<String> mMname=new ArrayList<String>();
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    Spinner spinclass;
    ProgressBar proglist;
    RecyclerView recyclestudentlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__list__class);

        proglist=(ProgressBar)findViewById(R.id.proglist);
        recyclestudentlist=(RecyclerView)findViewById(R.id.recyclestudentlist);
        spinclass=(Spinner)findViewById(R.id.spinclass);
        ArrayAdapter<String> ad=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,classes);
        spinclass.setAdapter(ad);



        staggeredGridLayoutManager=new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL);
        recyclestudentlist.setLayoutManager(staggeredGridLayoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference();


        cls=spinclass.getSelectedItem().toString();
    }

    public void showlist(View view) {

        proglist.setVisibility(View.VISIBLE);
        cls=spinclass.getSelectedItem().toString();


        databaseReference.child("2019").child("student").child(cls).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot mykey:dataSnapshot.getChildren())
                {
                    proglist.setVisibility(View.GONE);
                    mName.add(mykey.child("name").getValue(String.class));
                    mFname.add(mykey.child("fathername").getValue(String.class));
                    mKey.add(mykey.child("key").getValue(String.class));
                    mclass.add(mykey.child("class").getValue(String.class));
                    mMname.add(mykey.child("mothername").getValue(String.class));
                    mDp.add(mykey.child("image").getValue(String.class));
                    Adapter_Student_List adapter=new Adapter_Student_List(Student_List_ClassActivity.this,mName,mFname,mMname,mclass,mKey,mDp);
                    recyclestudentlist.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
