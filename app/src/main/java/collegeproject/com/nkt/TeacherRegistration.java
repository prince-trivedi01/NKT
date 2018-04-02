package collegeproject.com.nkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Prince on 27-03-2018.
 */

public class TeacherRegistration extends RegistrationForm {
    TextView teacherl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_registration);
        MainLoginPage();

    }
    public void MainLoginPage(){

        final Context context = this;

        teacherl = (TextView) findViewById(R.id.appCompatTextViewLoginLink);
        teacherl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);

            }


        } );



    }

}
