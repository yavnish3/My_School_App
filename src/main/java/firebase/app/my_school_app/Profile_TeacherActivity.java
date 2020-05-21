package firebase.app.my_school_app;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_TeacherActivity extends AppCompatActivity {

    public Intent in;
    public String key,downloadUrl;
    public EditText ets[]=new EditText[12];
    public int ids[]={
            R.id.name,
            R.id.fathername,
            R.id.tv_email,
            R.id.tv_qulification,
            R.id.tv_address,
            R.id.tv_category,
            R.id.tv_subject,
            R.id.tv_mobile,
            R.id.tv_dob,
            R.id.tv_adhar,
            R.id.tv_gender,
            R.id.tv_password
    };
    public int i;
    public DatabaseReference databaseReference;
    StorageReference storageReference;
    CircleImageView teacherprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__teacher);


        ActivityCompat.requestPermissions(Profile_TeacherActivity.this,new String []{Manifest.permission.READ_EXTERNAL_STORAGE},1122);
        ActivityCompat.requestPermissions(Profile_TeacherActivity.this,new String []{Manifest.permission.WRITE_EXTERNAL_STORAGE},1122);
        ActivityCompat.requestPermissions(Profile_TeacherActivity.this,new String []{Manifest.permission.CAMERA},5);
        teacherprofile=(CircleImageView)findViewById(R.id.teacherprofile);
        storageReference= FirebaseStorage.getInstance().getReference();

        for (i=0;i<ets.length;i++)
        {
            ets[i]=(EditText)findViewById(ids[i]);
        }
        in=getIntent();
        key=in.getStringExtra("key");

        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("2019").child("teacher").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Glide.with(Profile_TeacherActivity.this).load(dataSnapshot.child("image").getValue(String.class))
                        .asBitmap().into(teacherprofile);
                ets[0].setText(dataSnapshot.child("name").getValue(String.class));
                ets[1].setText(dataSnapshot.child("fathername").getValue(String.class));
                ets[2].setText(dataSnapshot.child("email").getValue(String.class));
                ets[3].setText(dataSnapshot.child("qualification").getValue(String.class));
                ets[4].setText(dataSnapshot.child("address").getValue(String.class));
                ets[5].setText(dataSnapshot.child("category").getValue(String.class));
                ets[6].setText(dataSnapshot.child("subject").getValue(String.class));
                ets[7].setText(dataSnapshot.child("mobile").getValue(String.class));
                ets[8].setText(dataSnapshot.child("dateofbirth").getValue(String.class));
                ets[9].setText(dataSnapshot.child("adhar").getValue(String.class));
                ets[10].setText(dataSnapshot.child("gender").getValue(String.class));
                ets[11].setText(dataSnapshot.child("password").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void update(View view) {
        String values[]=new String[ets.length];
        for (i=0;i<ets.length;i++)
        {
            values[i]=ets[i].getText().toString().trim();
        }
        databaseReference.child("2019").child("teacher").child(key).child("name").setValue(values[0]);
        databaseReference.child("2019").child("teacher").child(key).child("image").setValue(downloadUrl);
        databaseReference.child("2019").child("teacher").child(key).child("fathername").setValue(values[1]);
        databaseReference.child("2019").child("teacher").child(key).child("email").setValue(values[2]);
        databaseReference.child("2019").child("teacher").child(key).child("qualification").setValue(values[3]);
        databaseReference.child("2019").child("teacher").child(key).child("address").setValue(values[4]);
        databaseReference.child("2019").child("teacher").child(key).child("category").setValue(values[5]);
        databaseReference.child("2019").child("teacher").child(key).child("subject").setValue(values[6]);
        databaseReference.child("2019").child("teacher").child(key).child("mobile").setValue(values[7]);
        databaseReference.child("2019").child("teacher").child(key).child("dateofbirth").setValue(values[8]);
        databaseReference.child("2019").child("teacher").child(key).child("adhar").setValue(values[9]);
        databaseReference.child("2019").child("teacher").child(key).child("gender").setValue(values[10]);
        databaseReference.child("2019").child("teacher").child(key).child("password").setValue(values[11]);

        startActivity(new Intent(Profile_TeacherActivity.this,Teacher_HomeActivity.class));
        finish();
        Toast.makeText(this, "ProfileUpdated", Toast.LENGTH_LONG).show();
    }

    public void profileTeacherupload(View view) {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select image"),1122);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==1122 && resultCode==RESULT_OK && data != null){
            final Uri uri=data.getData();
            teacherprofile.setImageURI(uri);
            final ProgressDialog progressDialog=new ProgressDialog(Profile_TeacherActivity.this);
            progressDialog.setMessage("Uploading.....");
            progressDialog.setCancelable(false);
            progressDialog.show();
            final StorageReference filepath=storageReference.child("profile/teacher/")
                    .child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()){
                        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        progressDialog.dismiss();
                                        downloadUrl= String.valueOf(uri);
                                    }
                                });
                            }
                        });
                    }
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double prog=(100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                    progressDialog.setMessage((int)prog+"% Uploaded");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(Profile_TeacherActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
