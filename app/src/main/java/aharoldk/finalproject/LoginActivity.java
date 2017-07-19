package aharoldk.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by aharoldk on 26/06/17.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText emailField;
    private EditText passwordField;
    private Button btnLogin;
    private TextView registerText;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        registerText = (TextView) findViewById(R.id.registerText);

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                registerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(registerIntent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }


    private void checkLogin() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

            progressDialog.setMessage("Wait . . .");
            progressDialog.show();
            if(email.equals("admin@gmail.com") && password.equals("password")){
                progressDialog.dismiss();

                Intent adminIntent = new Intent(LoginActivity.this, AdminActivity.class);
                adminIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(adminIntent);
                finish();

            } else {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        progressDialog.dismiss();

                        Intent userIntent = new Intent(LoginActivity.this, UserActivity.class);
                        userIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(userIntent);
                        finish();

                    } else {

                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Please Sign Up", Toast.LENGTH_SHORT).show();
                    }

                    }
                });
            }

        } else {
            Toast.makeText(LoginActivity.this, "Please Fill All Form", Toast.LENGTH_SHORT).show();

        }

    }

    public void onBackPressed() {
        super.onBackPressed();
        LoginActivity.this.finish();
    }
}
