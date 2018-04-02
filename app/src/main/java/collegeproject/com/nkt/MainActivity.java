package collegeproject.com.nkt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {



        Button button;

        TextView RegisterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TeacherLogin();
        RegistrationPage();
    }

    private void TeacherLogin() {

        final Context context = this;


        button = (Button) findViewById(R.id.appCompatTeacherButtonLogin);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, TeacherLogin.class);
                startActivity(intent);

            }

        });

    }

    public void RegistrationPage(){

        final Context context = this;

        RegisterTextView = (TextView) findViewById(R.id.textViewLinkRegister);
        RegisterTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,RegistrationForm.class);
                startActivity(intent);

            }


        } );

    }
    }


