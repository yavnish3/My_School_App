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
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home_WorkActivity extends AppCompatActivity {



    String []classes={
            "NUR","KG","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
    };
    String cls;
    Spinner spinclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__work);


        spinclass=(Spinner)findViewById(R.id.spinclass);
        ArrayAdapter<String> ad=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,classes);
        spinclass.setAdapter(ad);


    }

    public void addnewhomework(View view) {
        startActivity(new Intent(Home_WorkActivity.this,Add_Home_WorkActivity.class));
        finish();
    }

    public void showwork(View view) {

        cls=spinclass.getSelectedItem().toString().trim();
         Intent i=new Intent(Home_WorkActivity.this,Show_Teacher_HomeWorkActivity.class);
         i.putExtra("class",cls);
        startActivity(i);
        finish();
    }
}
