package collegeproject.com.nkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;


import java.io.ObjectInputValidation;

import Helper.InputValidation;
import SQL.StudentDataBase;

import static collegeproject.com.nkt.R.id.textInputEditTextEmail;
import static collegeproject.com.nkt.R.id.textInputLayoutEmail;
import static collegeproject.com.nkt.R.id.textInputLayoutSUserId;

/**
 * Created by Prince on 27-03-2018.
 */

public class StudentLogin extends LectureDetails implements View.OnClickListener {

    private final AppCompatActivity activity = StudentLogin.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutSUserId;
    private TextInputLayout textInputLayoutSPassword;

    private TextInputEditText textInputEditTextStudentUserId;
    private TextInputEditText textInputEditTextStudentPassword;

    private AppCompatButton appCompatStudentLogin;

    private AppCompatButton appCompatStudentAttendSubmit;

    private InputValidation inputValidation;
    private StudentDataBase StudentDatabase;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutSUserId = (TextInputLayout) findViewById(R.id.textInputLayoutSUserId);
        textInputLayoutSPassword = (TextInputLayout) findViewById(R.id.textInputLayoutSPassword);

        textInputEditTextStudentUserId = (TextInputEditText) findViewById(R.id.textInputEditTextStudentUserId);
        textInputEditTextStudentPassword = (TextInputEditText) findViewById(R.id.textInputEditTextStudentPassword);

        appCompatStudentLogin = (AppCompatButton) findViewById(R.id.appCompatButtonStudentLogin);

        appCompatStudentAttendSubmit = (AppCompatButton) findViewById(R.id.appCompatStudentAttendSubmit);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatStudentLogin.setOnClickListener(this);
        appCompatStudentAttendSubmit.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        StudentDatabase = new StudentDataBase(activity);
        inputValidation = new Helper.InputValidation(activity);

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.appCompatButtonStudentLogin:
                verifyFromSQLite();
                break;
            case R.id.appCompatStudentAttendSubmit:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), TeacherSignIn.class);
                startActivity(intentRegister);
                break;
        }
    }


    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextStudentUserId, textInputLayoutSUserId, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextUserId(textInputEditTextStudentUserId, textInputLayoutSUserId, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextStudentPassword, textInputLayoutSPassword, getString(R.string.error_message_userid))) {
            return;
        }

        if (StudentDatabase.checkStudent(textInputEditTextStudentUserId.getText().toString().trim(),textInputEditTextStudentPassword
        .getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, TeacherSignIn.class);
            accountsIntent.putExtra("UserId", textInputEditTextStudentUserId.getText().toString().trim());
             emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }

    }


    private void emptyInputEditText() {
        textInputEditTextStudentUserId.setText(null);
        textInputEditTextStudentPassword.setText(null);
    }





}

