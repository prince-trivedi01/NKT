package collegeproject.com.nkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Prince on 27-03-2018.
 */

public class StudentRegistration extends RegistrationForm {
    TextView teacherlp;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_registration);
        StudentLoginPage();
    }


    public void StudentLoginPage() {

        final Context context = this;

        teacherlp = (TextView) findViewById(R.id.appCompatTextViewLoginLink1);
        teacherlp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }


        });


    }
}
