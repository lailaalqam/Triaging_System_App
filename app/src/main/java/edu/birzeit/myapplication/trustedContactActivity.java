package edu.birzeit.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.text.TextUtils;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;


          import edu.birzeit.myapplication.models.TrustedContact;
          import edu.birzeit.myapplication.ApiInterface;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class trustedContactActivity extends AppCompatActivity {


    EditText edtUId;
    EditText edtcontactname,editPhoneNumber,editcontactorder;
    Button btnSave;
    Button btnDel;

    Boolean CheckEditText;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_contact2);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        edtUId = (EditText) findViewById(R.id.edtUId);
        edtcontactname = (EditText) findViewById(R.id.edtcontactname);
        editPhoneNumber = (EditText) findViewById(R.id.editPhoneNumber);
        editcontactorder = (EditText) findViewById(R.id.editcontactorder);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);


        Bundle extras = getIntent().getExtras();
        final String userId = extras.getString("trustContact_id");
        String userName = extras.getString("trustContact_name");
        String userPhone = extras.getString("trustContact_phone");
        final String userContactOrder = extras.getString("trustContact_ContactOrder");


        edtUId.setText(userId);
        edtcontactname.setText(userName);
        editPhoneNumber.setText(userPhone);
        editcontactorder.setText(userContactOrder);

        if ( Integer.parseInt(userId) != 0 ) {
            edtUId.setFocusable(false);
        } else {

            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrustedContact u = new TrustedContact();

                u.setName(edtcontactname.getText().toString());
                u.setPhone(editPhoneNumber.getText().toString());
                u.setContactOrder(Integer.parseInt(editcontactorder.getText().toString()));
                if (userId != null && userId.trim().length() > 0) {
                    //update user
                    updateUser(Integer.parseInt(userId), u);
                } else {
                    //add user
                    addUser(u);
                }
            }
        });





        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(Integer.parseInt(userId));

                Intent intent = new Intent(trustedContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addUser(TrustedContact u) {
        String res = Api.getClient().addUserTrustedContact(u);
        switch (res) {
            case "ok":

                // display the message getting from web api
                Toast.makeText(trustedContactActivity.this, " Add done.", Toast.LENGTH_SHORT).show();
                break;

            case "no":
                // in this method we will get the response from API
                // display an error message
                Toast.makeText(trustedContactActivity.this, "Error , please try again.", Toast.LENGTH_LONG).show();
                break;
        }

    }


    public void updateUser(long id, TrustedContact u) {

        String res = Api.getClient().UpdateUserTrustedContact(id, u);
        switch (res) {
            case "ok":

                // display the message getting from web api
                Toast.makeText(trustedContactActivity.this, " Update done.", Toast.LENGTH_SHORT).show();
                break;

            case "no":
                // in this method we will get the response from API
                // display an error message
                Toast.makeText(trustedContactActivity.this, "Error , please try again.", Toast.LENGTH_LONG).show();
                break;
        }

    }


    public void deleteUser(int id) {
        //Call<String> call = Api.getClient().deleteUserTrustedContact("5cf69a0b320000e18c8cd3f1");

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // registration is a POST request type method in which we are sending our field's data
        String response = Api.getClient().deleteUserTrustedContact(id);
        //String response = "no";
        // check response
        switch (response) {
            case "ok":
                // in this method we will get the response from API
                Toast.makeText(trustedContactActivity.this, "delete done.", Toast.LENGTH_SHORT).show();
                break;

            case "no":

                Toast.makeText(trustedContactActivity.this, "Error , please try again.", Toast.LENGTH_LONG).show();
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

   /* public void CheckEditTextIsEmptyOrNot() {

        Name = edtcontactname.getText().toString();
        Phone = editPhoneNumber.getText().toString();

        Order = Integer.parseInt(editcontactorder.getText().toString());

        if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Phone) || Order < 0) {

            CheckEditText = false;

        } else {

            CheckEditText = true;
        }
    }
    */

}








