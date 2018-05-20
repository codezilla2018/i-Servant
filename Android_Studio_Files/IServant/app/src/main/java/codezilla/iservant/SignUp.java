package codezilla.iservant;


import android.app.Activity;
import android.app.AlertDialog;
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

public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Activity thisContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

        thisContext=this;
        Button signup=(Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView emailtxt=findViewById(R.id.emailtxt);
                TextView pword1txt=findViewById(R.id.pword1txt);
                TextView pword2txt=findViewById(R.id.pword2txt);
                String email =emailtxt.getText().toString().trim();
                String pword1 =pword1txt.getText().toString().trim();
                String pword2 =pword2txt.getText().toString().trim();
                if(email.equals("")){
                    AlertDialog alertDialog = new AlertDialog.Builder(thisContext).create();
                    alertDialog.setTitle("Email");
                    alertDialog.setMessage("Must add a valid Email");
                    alertDialog.show();
                }else if(pword1.equals("")){
                    AlertDialog alertDialog = new AlertDialog.Builder(thisContext).create();
                    alertDialog.setTitle("Password");
                    alertDialog.setMessage("Password cannot be empty");
                    alertDialog.show();
                }else if(!pword1.equals(pword2)){
                    AlertDialog alertDialog = new AlertDialog.Builder(thisContext).create();
                    alertDialog.setTitle("Password");
                    alertDialog.setMessage("Passwords doesn't match");
                    alertDialog.show();
                }else{
                    mAuth.createUserWithEmailAndPassword(email, pword1)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //Toast.makeText(SignUp.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    //progressBar.setVisibility(View.GONE);
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        AlertDialog alertDialog = new AlertDialog.Builder(thisContext).create();
                                        alertDialog.setTitle("Sign Up");
                                        alertDialog.setMessage("Authentication failed.\n" + task.getException().getMessage());
                                        alertDialog.show();
                                        startActivity(new Intent(SignUp.this, SignUp.class));
                                        finish();
                                    } else {
                                        startActivity(new Intent(SignUp.this, VerifySignUp.class));
                                        finish();
                                    }
                                }
                            });
                }


            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }


}
