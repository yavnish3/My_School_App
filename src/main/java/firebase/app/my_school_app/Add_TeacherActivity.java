package firebase.app.my_school_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.security.SecureRandom;

import de.hdodenhof.circleimageview.CircleImageView;

public class Add_TeacherActivity extends AppCompatActivity {

    String downloadUrl="Image not found";

    CircleImageView iv_adphoto;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    EditText[]ets=new EditText[9];
    Spinner spin_adcast,spin_gender;

    int i;
    int ids[]={
            R.id.et_adname,
            R.id.adfname,
            R.id.tesub,
            R.id.tequalification,
            R.id.et_adaddress,
            R.id.et_ademail,
            R.id.et_admobile,
            R.id.et_addob,
            R.id.et_adhar,
    };
    String values[]=new String[9];
    String []gen={
            "Male",
            "Female"
    };
    String []cast={
            "GEN","OBC","SC","ST"
    };
    String cat,pass,gend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__teacher);

        iv_adphoto=(CircleImageView)findViewById(R.id.iv_adphoto);

        for(i=0;i<ets.length;i++)
        {
            ets[i]=(EditText)findViewById(ids[i]);
        }
        databaseReference= FirebaseDatabase.getInstance().getReference();
        storageReference= FirebaseStorage.getInstance().getReference();

        spin_adcast=(Spinner)findViewById(R.id.spin_adcast);
        spin_gender=(Spinner)findViewById(R.id.spin_gender);
        ArrayAdapter<String> ad1=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,cast);
        ArrayAdapter<String> ad2=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,gen);
        spin_adcast.setAdapter(ad1);
        spin_gender.setAdapter(ad2);
    }

    public void addteacher(View view) {

        for (i=0;i<ets.length;i++)
            if (ets[i].getText().toString().isEmpty()) {
                ets[i].setError("empty");
                ets[i].requestFocus();
                break;
            }

        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( 8 );
        for( int i = 0; i < 8; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        pass=sb.toString();



        if (i==ets.length) {
            for (i = 0; i < ets.length; i++) {
                values[i] = ets[i].getText().toString().trim();
            }
            cat=spin_adcast.getSelectedItem().toString().trim();
            gend=spin_gender.getSelectedItem().toString().trim();

            String key=databaseReference.child("2019").child("teacher").push().getKey();
            databaseReference.child("2019").child("teacher").child(key).child("image").setValue(downloadUrl);
            databaseReference.child("2019").child("teacher").child(key).child("name").setValue(values[0]);
            databaseReference.child("2019").child("teacher").child(key).child("password").setValue(pass);
            databaseReference.child("2019").child("teacher").child(key).child("gender").setValue(gend);
            databaseReference.child("2019").child("teacher").child(key).child("fathername").setValue(values[1]);
            databaseReference.child("2019").child("teacher").child(key).child("subject").setValue(values[2]);
            databaseReference.child("2019").child("teacher").child(key).child("qualification").setValue(values[3]);
            databaseReference.child("2019").child("teacher").child(key).child("address").setValue(values[4]);
            databaseReference.child("2019").child("teacher").child(key).child("email").setValue(values[5]);
            databaseReference.child("2019").child("teacher").child(key).child("mobile").setValue(values[6]);
            databaseReference.child("2019").child("teacher").child(key).child("dateofbirth").setValue(values[7]);
            databaseReference.child("2019").child("teacher").child(key).child("adhar").setValue(values[8]);
            databaseReference.child("2019").child("teacher").child(key).child("category").setValue(cat);
            databaseReference.child("2019").child("teacher").child(key).child("key").setValue(key);

            Toast.makeText(this, "Teacher Added", Toast.LENGTH_SHORT).show();
            for (i=0;i<ets.length;i++)
            {
                ets[i].setText("");
            }
        }
        else
            Toast.makeText(this, "Teacher Not Added", Toast.LENGTH_SHORT).show();

    }

    public void addphoto(View view) {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent,"Awnish get image"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data != null){
            Uri uri = data.getData();
            iv_adphoto.setImageURI(uri);
            if (uri != null){
                final StorageReference filePath=storageReference.child("teacher/image").child(uri.getLastPathSegment());
                filePath.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl= String.valueOf(uri);
                                    Toast.makeText(Add_TeacherActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        }
    }
}
