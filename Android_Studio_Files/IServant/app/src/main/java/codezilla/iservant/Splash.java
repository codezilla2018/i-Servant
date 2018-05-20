package codezilla.iservant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends Activity {
    private final int SPLASH_TIME = 1300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Intent in = new Intent(getApplicationContext(), MainPage.class);
                    startActivity(in);
                    finish();
                } else {
                    // No user is signed in
                    Intent in = new Intent(getApplicationContext(), Login.class);
                    startActivity(in);
                    finish();
                }
            }
        },SPLASH_TIME);
    }
}
