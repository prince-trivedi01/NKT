package collegeproject.com.nkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Prince on 27-03-2018.
 */

public class AttendanceDetails extends TeacherSignIn {

        Button FinalSubmit;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_details);
        Submit();
    }

    public void Submit(){

        final Context context = this;


       FinalSubmit = (Button) findViewById(R.id.appCompatButtonSubmit);


        FinalSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }

        });

    }
}
