package suchitapatel.com.smarttrolly;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RelativeLayout activityLogin;
    EditText loginEmail;
    EditText loginPassword;
    Button loginButton;
    String Email,Password;
    Context context;
    String responseEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

    }

    private void bindViews(){
        loginEmail = (EditText) findViewById(R.id.login_email);
        loginPassword = (EditText) findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.login_button);
    }


    public void onLoginClick(View view) {
        Email=loginEmail.getText().toString();
        Password=loginPassword.getText().toString();
        if(Email.length() == 0 || Password.length() == 0)
        {
            if(Email.length() == 0 && Password.length() == 0)
            {
                loginEmail.setError("Required..");
                loginPassword.setError("Required..");
            }
            else if(Email.length() == 0)
            {
                loginEmail.setError("Required..");
            }
            else
            {
                loginPassword.setError("Required..");
            }
        }
        else {

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://192.168.0.215/SmartTrolly/login.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("Wrong")) {
                                Log.e("msg1", "something went wrong");
                            } else {
                                Log.e("msg", response);
                                //Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
                                Intent registration = new Intent(MainActivity.this,BarcodeScanner.class);
                                startActivity(registration);
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                }
            })
            {
                @Override
                protected Map<String,String> getParams()
                {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Email",Email);
                    params.put("Password",Password);
                    return params;
                }

            };
            requestQueue.add(stringRequest);
            }
        }

    public void onSignUp(View view) {

        Intent signUp = new Intent(this,Registration.class);
        startActivity(signUp);
    }

    public void onForgotPassword(View view) {
    }

    public void onReaderClick(View view) {
        Intent tagReader = new Intent(this,nfcReader.class);
        startActivity(tagReader);
    }
}
