package com.ceng319.final_app_2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    private Menu globalmenu;
    private FirebaseAuth mAuth;
    private TextView message;
    private Button login;
    private Button register;
    private Button signout;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findAllViewsfromLayout();
        handleLogin();

      /*  mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mButtonRegister = (Button) findViewById(R.id.button_register);
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_register);
                Intent registerIntent = new Intent(MainActivity.this, Register.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_register);
                Intent Main = new Intent(MainActivity.this, MainActivity.class);
                startActivity(Main);
            }
        });*/

    }
    private void handleLogin(){
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser(String.valueOf(email.getText()), String.valueOf(password.getText()));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(String.valueOf(email.getText()), String.valueOf(password.getText()));
            }
        });

       /* signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignoutfromDatabase();
            }
        });*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void findAllViewsfromLayout() {
        login = findViewById(R.id.button_login_2);
        register = findViewById(R.id.button_register);
        email = findViewById(R.id.edittext_username);
        password = findViewById(R.id.edittext_password);
      //  signout = findViewById(R.id.signout);
    }
    private void loginUser(String email, String password){
        // TODO: Login with Email and Password on Firebase.
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d("MapleLeaf", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                           // message.setText("User "+ user.getEmail() + " is now Logged In");

                           // setButtonStatus(true);
                            gotoRead();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MapleLeaf", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }

    private void gotoRead() {
        // TODO : Start the read option After login
        Intent intent1 = new Intent(MainActivity.this, kk.class);
        startActivity(intent1);
    }


    private void gotoWrite() {
        // TODO : Start the write db operation
    //    Intent intent2 = new Intent(this, WriteDB.class);
      //  startActivity(intent2);
    }

    private void createNewUser(String email, String password){
        Intent register = new Intent(this, Register.class);
        startActivity(register);

    }

    private void SignoutfromDatabase(){
        // TODO: Logout from Firebase.
        mAuth.signOut();
        setButtonStatus(false);
    }

    private void setButtonStatus(boolean status) {
        // Disable the reading and writing function.
       // setMenuItem(R.id.action_write, status);  // enable the write function.
        //setMenuItem(R.id.action_read, status);  // enable the write function.
        //signout.setEnabled(status);
    }

    private boolean testUser(){
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null)
        {
            message.setText("Already Logged in");
            return true;
        }
        return false;
    }
}
