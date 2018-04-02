package collegeproject.com.nkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Prince on 27-03-2018.
 */

public class TeacherSignIn extends StudentLogin {

    Button FinalSignIn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_signin);
        FinalTeacherSignIn();
    }

    public void FinalTeacherSignIn(){

        final Context context = this;


        FinalSignIn = (Button) findViewById(R.id.appCompatButtonTeacherSignIn);


        FinalSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, AttendanceDetails.class);
                startActivity(intent);

            }

        });


    }


}
