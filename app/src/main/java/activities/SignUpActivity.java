package activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tourmate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.etSignUpUserName)
    AppCompatEditText etSignUpUserName;

    @BindView(R.id.etSignUpEmailAddress)
    AppCompatEditText etSignUpEmailAddress;

    @BindView(R.id.etUserPassSignUp)
    AppCompatEditText etUserPassSignUp;

    @BindView(R.id.etConfirmPassword)
    AppCompatEditText etConfirmPassword;

    @BindView(R.id.btnSignUp)
    AppCompatButton btnSignUp;

    @BindView(R.id.tvSignInSUActivity)
    AppCompatTextView tvSignInSUActivity;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(this);
        tvSignInSUActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSignUp:
                userSignUp();
            case R.id.tvSignInSUActivity:
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                break;
        }

    }

    private void userSignUp() {
        String userName = etSignUpUserName.getText().toString().trim().toLowerCase();
        String email = etSignUpEmailAddress.getText().toString().trim();
        String password = etUserPassSignUp.getText().toString().trim();


        //validity checking username------
        if(userName.isEmpty()){
            etSignUpUserName.setError("Please enter your username");
        }

        //checking the validity of the email
        if (email.isEmpty()) {
            etSignUpEmailAddress.setError("Enter an email address");
            etSignUpEmailAddress.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etSignUpEmailAddress.setError("Enter a valid email address");
            etSignUpEmailAddress.requestFocus();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            etUserPassSignUp.setError("Enter a password");
            etUserPassSignUp.requestFocus();
        }
        if (password.length()<6) {
            etUserPassSignUp.setError("minimum length at least 6");
            etUserPassSignUp.requestFocus();

        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Resister Successful",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Resister not Successful",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}