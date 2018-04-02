package collegeproject.com.nkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Prince on 27-03-2018.
 */

public class StudentLogin extends LectureDetails {

        Button SubmitAttendance;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);
        SubmitStudentAttendance();
    }

    public void SubmitStudentAttendance(){

        final Context context = this;


        SubmitAttendance = (Button) findViewById(R.id.appCompatButtonStudentSignIn);


        SubmitAttendance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, TeacherSignIn.class);
                startActivity(intent);

            }

        });



    }

}
