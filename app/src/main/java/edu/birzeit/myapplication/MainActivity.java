package edu.birzeit.myapplication;



        import edu.birzeit.myapplication.models.*;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;
        import java.util.List;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button button, testSignup,Trusted,Signin,Firststep,Home,instcare;
    TextView show;
    // http://www.mocky.io/v2/5cd087d0320000b52100fd50 https://192.168.1.111:44374/api/user
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        show=(TextView)findViewById(R.id.showUsers);
        button = (Button) findViewById(R.id.button1);

       testSignup = (Button) findViewById(R.id.testSignup);
        testSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, SignUp.class);
                MainActivity.this.startActivity(intent);
            }
        });

        instcare = (Button) findViewById(R.id.button5);
        instcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, Main2Activity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Trusted = (Button) findViewById(R.id.button);
        Trusted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, trustedContact.class);
                MainActivity.this.startActivity(intent);
            }
        });


        Signin = (Button) findViewById(R.id.button2);
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, logIn.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Firststep = (Button) findViewById(R.id.button3);
        Firststep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, FirstStep.class);
                MainActivity.this.startActivity(intent);
            }
        });


        Home = (Button) findViewById(R.id.button4);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, home.class);
                MainActivity.this.startActivity(intent);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.setText("users List\n");
                try {

                    Call<List<UserInfo>> repos =
                            Api.getClient().getUsers("5cd087d0320000b52100fd50");
                    repos.enqueue(new Callback<List<UserInfo>>() {
                        @Override
                        public void onResponse(Call<List<UserInfo>> call,
                                               Response<List<UserInfo>> response) {
                            int statusCode = response.code();
                            List<UserInfo> usersObjects= response.body();
                            for (int i = 0; i < usersObjects.size(); i++) {

                                show.setText(show.getText()+usersObjects.get(i).getName()+"\n");
                            }
                        }
                        @Override
                        public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                            // Log error here since request failed
                            Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
                        }
                    });
                }catch(Exception e)
                {
                }
            }
        });
    }
}
