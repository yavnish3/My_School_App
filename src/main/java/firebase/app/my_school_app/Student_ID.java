package firebase.app.my_school_app;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.atomic.AtomicInteger;

public class Student_ID {

    String st="2019ST";
    String s;
    int i;

    public String id(String stid)
    {

        st=st+stid;
        return st;
    }
}
