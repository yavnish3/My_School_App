package firebase.app.my_school_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Student_Result extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclehomework;
    DatabaseReference databaseReference;
    ArrayList<String> mSubject=new ArrayList<String>();
    ArrayList<String> mText=new ArrayList<String>();
    ArrayList<String> mTime=new ArrayList<String>();
    ArrayList<String> mKey=new ArrayList<String>();
    ArrayList<String> mMrkOb=new ArrayList<String>();
    ArrayList<String> mTlMrk=new ArrayList<String>();
    ArrayList<String> mTeacherKey=new ArrayList<String>();
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    Intent in;
    String key,cls,clas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__result);

        in=getIntent();
        key=in.getStringExtra("key");
        cls=in.getStringExtra("class");
        clas=cls;

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        recyclehomework=(RecyclerView)findViewById(R.id.recyclehomework);
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL);
        recyclehomework.setLayoutManager(staggeredGridLayoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("2019").child("student").child(cls).child(key).child("result").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot mykey : dataSnapshot.getChildren()) {
                    mSubject.add(mykey.child("testtype").getValue(String.class));
                    mText.add(mykey.child("testsubject").getValue(String.class));
                    mTime.add(mykey.child("time").getValue(String.class));
                    mMrkOb.add(mykey.child("markobtain").getValue(String.class));
                    mTlMrk.add(mykey.child("totalmark").getValue(String.class));
                    mKey.add(mykey.child("key").getValue(String.class));
                    mTeacherKey.add(mykey.child("studentkey").getValue(String.class));
                    Adapter_Student_Result adapter = new Adapter_Student_Result(Student_Result.this,mSubject,mText,mMrkOb,mTlMrk,mTime,mKey,mTeacherKey,clas);
                    recyclehomework.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
