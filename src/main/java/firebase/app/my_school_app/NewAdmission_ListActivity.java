package firebase.app.my_school_app;

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

public class NewAdmission_ListActivity extends AppCompatActivity {

    ProgressBar mngNoticeProg;
    RecyclerView recyclehomework;
    DatabaseReference databaseReference;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    ArrayList<String> mName=new ArrayList<>();
    ArrayList<String> mClass=new ArrayList<>();
    ArrayList<String> mMobile=new ArrayList<>();
    ArrayList<String> mKey=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_admission__list);

        mngNoticeProg=(ProgressBar)findViewById(R.id.mngNoticeProg);
        recyclehomework=(RecyclerView)findViewById(R.id.recyclehomework);
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL);
        recyclehomework.setLayoutManager(staggeredGridLayoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("2019").child("newadmission").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot mykey : dataSnapshot.getChildren()) {
                    mngNoticeProg.setVisibility(View.GONE);
                    mName.add(mykey.child("name").getValue(String.class));
                    mClass.add(mykey.child("class").getValue(String.class));
                    mKey.add(mykey.child("key").getValue(String.class));
                    mMobile.add(mykey.child("mobilenumber").getValue(String.class));
                    registration_Adapter adapter=new registration_Adapter(NewAdmission_ListActivity.this,mName,mClass,mMobile,mKey);
                    recyclehomework.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
