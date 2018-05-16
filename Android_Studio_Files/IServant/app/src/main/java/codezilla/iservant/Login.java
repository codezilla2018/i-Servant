package codezilla.iservant;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        final Activity thisContext = this;
        Button loginbtn=(Button)findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView unametxt=(TextView)findViewById(R.id.usernametxt);
                TextView pwordtxt=(TextView)findViewById(R.id.pwordtxt);
                String email =unametxt.getText().toString().trim();
                final String pword =pwordtxt.getText().toString().trim();
                //check login

                mAuth.signInWithEmailAndPassword(email, pword)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                //progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (pword.length() < 6) {
                                        //inputPassword.setError(getString(R.string.minimum_password));
                                        Toast.makeText(Login.this, "password short", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Login.this, "failed", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(Login.this, "Success", Toast.LENGTH_LONG).show();
//                                    Intent intent = new Intent(Login.this, Login.class);
//                                    startActivity(intent);
//                                    finish();
                                }
                            }
                        });
            }
        });

        TextView signup =(TextView)findViewById(R.id.signupbtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), SignUp.class);
                startActivity(in);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


}
