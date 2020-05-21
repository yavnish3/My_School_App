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

public class Show_Homw_workActivity extends AppCompatActivity {

    public Intent in;
   String cls,key;
    ProgressBar mngNoticeProg;
    RecyclerView recyclehomework;
    Student_Home_Work_Adapter adapter;
    DatabaseReference databaseReference;
    ArrayList<String> mSubject=new ArrayList<String>();
    ArrayList<String> mText=new ArrayList<String>();
    ArrayList<String> mKey=new ArrayList<String>();
    ArrayList<String> mclass=new ArrayList<String>();
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__homw_work);

        in=getIntent();
        key=in.getStringExtra("key");
        cls=in.getStringExtra("class");
        mngNoticeProg=(ProgressBar)findViewById(R.id.mngNoticeProg);
        recyclehomework=(RecyclerView)findViewById(R.id.recyclehomework);
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL);
        recyclehomework.setLayoutManager(staggeredGridLayoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("2019").child("student").child(cls).child("homework").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot mykey:dataSnapshot.getChildren())
                {
                    mngNoticeProg.setVisibility(View.GONE);
                    mSubject.add(mykey.child("subject").getValue(String.class));
                    mText.add(mykey.child("text").getValue(String.class));
                    mKey.add(mykey.child("key").getValue(String.class));
                    mclass.add(mykey.child("class").getValue(String.class));

                    adapter= new Student_Home_Work_Adapter(Show_Homw_workActivity.this,mSubject,mText,mKey,mclass);
                    recyclehomework.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}