package aharoldk.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.Touch;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FormActivity extends AppCompatActivity {

    private static final int MAX_LENGTH = 7;
    private EditText formTitle;
    private EditText formDesc;
    private ImageButton formImage;

    private static final int GALLERY_REQUEST = 1;

    private Uri uriImage = null;

    private FirebaseAuth firebaseAuth;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceUser;
    private FirebaseUser firebaseUser;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseUser = firebaseAuth.getCurrentUser();

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("news");
        databaseReferenceUser = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid());


        formTitle = (EditText) findViewById(R.id.formTitle);
        formDesc = (EditText) findViewById(R.id.formDesc);
        formImage = (ImageButton) findViewById(R.id.formImage);

        progressDialog = new ProgressDialog(this);

        formImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.nav_submit){
            postToDo();
        }
        return super.onOptionsItemSelected(item);
    }

    private void postToDo() {

        progressDialog.setMessage("Wait");

        final String title = formTitle.getText().toString().trim();
        final String desc = formDesc.getText().toString().trim();

        final String user_id = firebaseAuth.getCurrentUser().getUid();

        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(desc) && uriImage != null){

            progressDialog.show();

            databaseReferenceUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(final DataSnapshot dataSnapshot) {
                    StorageReference path = storageReference.child("image").child(random());

                    path.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUri = taskSnapshot.getDownloadUrl();
                            DatabaseReference newPost = databaseReference.push();

                            newPost.child("title").setValue(title);
                            newPost.child("desc").setValue(desc);
                            newPost.child("image").setValue(downloadUri.toString());
                            newPost.child("currentDate").setValue(dateTime());
                            newPost.child("status").setValue("enable");
                            newPost.child("user_id").setValue(user_id);
                            newPost.child("name").setValue(dataSnapshot.child("name").getValue());


                            progressDialog.dismiss();

                            startActivity(new Intent(FormActivity.this, UserActivity.class));
                            finish();
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        } else {
            progressDialog.dismiss();
            Toast.makeText(this, "Please Fill All Form", Toast.LENGTH_SHORT).show();
        }
    }


    public static String random(){
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(MAX_LENGTH);
        char tempChar;

        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();

    }

    public static String dateTime(){
        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String date = dateFormat.format(curDate);

        return date;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){

            uriImage = data.getData();

            formImage.setImageURI(uriImage);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        FormActivity.this.finish();
    }
}
