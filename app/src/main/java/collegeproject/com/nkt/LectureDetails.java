package collegeproject.com.nkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Prince on 27-03-2018.
 */

public class LectureDetails extends TeacherLogin {

        Button Submit;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecture_details);
        SubmitLectureDetails();
    }

    public void SubmitLectureDetails(){

        final Context context = this;


        Submit = (Button) findViewById(R.id.appCompatButtonTeacherLectureD);


        Submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, StudentLogin.class);
                startActivity(intent);

            }

        });

    }



}
