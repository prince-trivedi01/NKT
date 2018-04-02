package collegeproject.com.nkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by Prince on 27-03-2018.
 */

public class TeacherLogin extends MainActivity {
        Button SubmitLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_login);
       SubmitTeacherLogin();
    }

    public void SubmitTeacherLogin(){

        final Context context = this;


        SubmitLogin = (Button) findViewById(R.id.appCompatButtonTLogin);


        SubmitLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, LectureDetails.class);
                startActivity(intent);

            }

        });

    }
}
