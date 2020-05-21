package firebase.app.my_school_app;

import android.arch.core.executor.DefaultTaskExecutor;
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

public class Teacher_ListActivity extends AppCompatActivity {

    ProgressBar mngNoticeProg;
    DatabaseReference databaseReference;
    RecyclerView recycletecherlist;
    ArrayList<String> mName=new ArrayList<String>();
    ArrayList<String> mFname=new ArrayList<String>();
    ArrayList<String> mDp=new ArrayList<String>();
    ArrayList<String> mKey=new ArrayList<String>();
    ArrayList<String> mmobile=new ArrayList<String>();
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__list);

        mngNoticeProg=(ProgressBar)findViewById(R.id.mngNoticeProg);
        recycletecherlist=(RecyclerView)findViewById(R.id.recycletecherlist);
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL);
        recycletecherlist.setLayoutManager(staggeredGridLayoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("2019").child("teacher").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot mykey:dataSnapshot.getChildren())
                {
                    mngNoticeProg.setVisibility(View.GONE);
                    mName.add(mykey.child("name").getValue(String.class));
                    mFname.add(mykey.child("fathername").getValue(String.class));
                    mKey.add(mykey.child("key").getValue(String.class));
                    mmobile.add(mykey.child("mobile").getValue(String.class));
                    mDp.add(mykey.child("image").getValue(String.class));

                    Adapter_Teacher_List adapter=new Adapter_Teacher_List(
                            Teacher_ListActivity.this,mName,mDp,mFname,mKey,mmobile);
                    recycletecherlist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
