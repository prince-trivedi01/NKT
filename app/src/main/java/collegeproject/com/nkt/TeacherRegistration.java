package collegeproject.com.nkt;

import android.content.Context;
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
import SQL.TeacherDataBase;
import modal.Teacher;

/**
 * Created by Prince on 27-03-2018.
 */

public class TeacherRegistration extends RegistrationForm implements View.OnClickListener {
    private final AppCompatActivity activity = TeacherRegistration.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutTeacherName;
    private TextInputLayout textInputLayoutTeacherUserId;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutBranch;
    private TextInputLayout textInputLayoutSemester;
    private TextInputLayout textInputLayoutSubject;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextTeacherName;
    private TextInputEditText textInputEditTextUserId;
    private TextInputEditText textInputEditTextSemester;
    private TextInputEditText textInputEditTextSubject;
    private TextInputEditText textInputEditTextBranch;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonTeacherRegister;
    private AppCompatTextView appCompatTextViewLoginLink;


    private InputValidation inputValidation;
    private TeacherDataBase TeacherDatabase;
    private Teacher Teacher;
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_registration);
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

        textInputLayoutTeacherName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutTeacherUserId = (TextInputLayout) findViewById(R.id.textInputLayoutUserId);
        textInputLayoutBranch = (TextInputLayout) findViewById(R.id.textInputEditBranch);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutSemester = (TextInputLayout) findViewById(R.id.textInputEditSemester);
        textInputLayoutSubject = (TextInputLayout) findViewById(R.id.textInputEditSubject);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextTeacherName = (TextInputEditText) findViewById(R.id.textInputEditTextTeacherName);
        textInputEditTextUserId = (TextInputEditText) findViewById(R.id.textInputEditTextTeacherUserId);
        textInputEditTextBranch = (TextInputEditText) findViewById(R.id.textInputEditTextTeacherBranch);
        textInputEditTextSemester = (TextInputEditText) findViewById(R.id.textInputEditSemesterText);
        textInputEditTextSubject = (TextInputEditText) findViewById(R.id.textInputEditTextTeacherSubject);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonTeacherRegister = (AppCompatButton) findViewById(R.id.appCompatButtonTeacherRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonTeacherRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        TeacherDatabase = new TeacherDataBase(activity);
        Teacher = new Teacher();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonTeacherRegister:
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
        if (!inputValidation.isInputEditTextFilled(textInputEditTextTeacherName, textInputLayoutTeacherName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextUserId, textInputLayoutTeacherUserId, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextUserId(textInputEditTextUserId, textInputLayoutTeacherUserId, getString(R.string.error_message_userid))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextBranch, textInputLayoutBranch, getString(R.string.error_message_branch))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextSemester, textInputLayoutSemester, getString(R.string.error_message_semester))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextSubject, textInputLayoutSubject, getString(R.string.error_message_subject))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!TeacherDatabase.checkTeacher(textInputEditTextUserId.getText().toString().trim())) {

            Teacher.setT_name(textInputEditTextTeacherName.getText().toString().trim());
            Teacher.setT_userID(textInputEditTextUserId.getText().toString().trim());
            Teacher.setT_branch(textInputEditTextBranch.getText().toString().trim());
            Teacher.setT_semester(textInputEditTextSemester.getText().toString().trim());
            Teacher.setT_subject(textInputEditTextSubject.getText().toString().trim());
            Teacher.setT_password(textInputEditTextPassword.getText().toString().trim());
            TeacherDatabase.addTeacher(Teacher);

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
        textInputEditTextTeacherName.setText(null);
        textInputEditTextUserId.setText(null);
        textInputEditTextBranch.setText(null);
        textInputEditTextSubject.setText(null);
        textInputEditTextSemester.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }

}
