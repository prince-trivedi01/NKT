package collegeproject.com.nkt;

import
        android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;

import Helper.InputValidation;
import SQL.StudentDataBase;
import modal.Student;

import static collegeproject.com.nkt.R.id.textInputEditTextEmail;

/**
 * Created by Prince on 27-03-2018.
 */

public class StudentRegistration extends RegistrationForm implements View.OnClickListener {

    private final AppCompatActivity activity = StudentRegistration.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutStudentName;
    private TextInputLayout textInputLayoutStudentUserId;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutBranch;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextStudentName;
    private TextInputEditText textInputEditTextUserId;
    private TextInputEditText textInputEditTextBranch;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonStudentRegister;
    private AppCompatTextView appCompatTextViewLoginLink;


    private InputValidation inputValidation;
    private StudentDataBase StudentDatabase;
    private Student student;
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_registration);
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

        textInputLayoutStudentName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutStudentUserId = (TextInputLayout) findViewById(R.id.textInputLayoutUserId);
        textInputLayoutBranch = (TextInputLayout) findViewById(R.id.textInputEditBranch);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextStudentName = (TextInputEditText) findViewById(R.id.textInputEditTextStudentName);
        textInputEditTextUserId = (TextInputEditText) findViewById(R.id.textInputEditTextStudentUserId);
        textInputEditTextBranch = (TextInputEditText) findViewById(R.id.textInputEditTextBranch);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonStudentRegister = (AppCompatButton) findViewById(R.id.appCompatButtonStudentRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink1);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonStudentRegister.setOnClickListener((View.OnClickListener) this);
        appCompatTextViewLoginLink.setOnClickListener((View.OnClickListener) this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        StudentDatabase = new StudentDataBase(activity);
        student = new Student();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonStudentRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink1:
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                break;
        }
    }



    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextStudentName, textInputLayoutStudentName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextUserId, textInputLayoutStudentUserId, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextUserId(textInputEditTextUserId, textInputLayoutStudentUserId, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextBranch, textInputLayoutBranch, getString(R.string.error_message_branch))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!StudentDatabase.checkStudent(textInputEditTextUserId.getText().toString().trim())) {

            student.setStd_name(textInputEditTextStudentName.getText().toString().trim());
            student.setStd_userID(textInputEditTextUserId.getText().toString().trim());
            student.setStd_branch(textInputEditTextBranch.getText().toString().trim());
            student.setStd_password(textInputEditTextPassword.getText().toString().trim());
            StudentDatabase.addStudent(student);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextStudentName.setText(null);
        textInputEditTextUserId.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}
