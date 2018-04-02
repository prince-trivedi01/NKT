package collegeproject.com.nkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Prince on 27-03-2018.
 */

public class RegistrationForm extends MainActivity {
    Button stdbtn;
    Button teacherbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);
        StudentRegistration();
        TeacherRegistration();
    }

    public void StudentRegistration() {
        final Context context = this;


       stdbtn = (Button) findViewById(R.id.appCompatButtonStudentRegistration);
       stdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                        Intent intent = new Intent(context, StudentRegistration.class);
                        startActivity(intent);



            }
        });


    }

    public void TeacherRegistration() {

        final Context context = this;


       teacherbtn = (Button) findViewById(R.id.appCompatButtonTeacherRegistration);
       teacherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                        Intent intent = new Intent(context, TeacherRegistration.class);
                        startActivity(intent);



            }
        });
    }
}











