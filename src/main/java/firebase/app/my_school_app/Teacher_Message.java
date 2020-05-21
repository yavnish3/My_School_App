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

public class Teacher_Message extends AppCompatActivity {

    Intent in;
    ProgressBar progressBar;
    RecyclerView recyclehomework;
    DatabaseReference databaseReference;
    ArrayList<String> mSubject=new ArrayList<String>();
    ArrayList<String> mText=new ArrayList<String>();
    ArrayList<String> mTime=new ArrayList<String>();
    ArrayList<String> mKey=new ArrayList<String>();
    ArrayList<String> mTeacherKey=new ArrayList<String>();
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    String key,cls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__message);

        in=getIntent();
        key=in.getStringExtra("key");

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        recyclehomework=(RecyclerView)findViewById(R.id.recyclehomework);
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL);
        recyclehomework.setLayoutManager(staggeredGridLayoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("2019").child("teacher").child(key).child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot mykey : dataSnapshot.getChildren()) {
                    progressBar.setVisibility(View.GONE);
                    mSubject.add(mykey.child("title").getValue(String.class));
                    mText.add(mykey.child("text").getValue(String.class));
                    mTime.add(mykey.child("time").getValue(String.class));
                    mKey.add(mykey.child("key").getValue(String.class));
                    mTeacherKey.add(mykey.child("teacherkey").getValue(String.class));
                    Teacher_Message_Adapter adapter = new Teacher_Message_Adapter(Teacher_Message.this,mSubject,mText,mTime,mKey,mTeacherKey);
                    recyclehomework.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        progressBar.setVisibility(View.GONE);
    }
}
