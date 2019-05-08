package suchitapatel.com.smarttrolly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    EditText name,email,password,mbileno;
    String Name,Email,Password,Mbileno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        bindviewa();
    }

    private void bindviewa() {
        name = (EditText) findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        mbileno = (EditText)findViewById(R.id.mobile_no);

    }

    public void onRegisterClick(View view) {

        Name = name.getText().toString();
        Email = email.getText().toString();
        Password = password.getText().toString();
        Mbileno = mbileno.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.215/SmartTrolly/userregistration.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Wrong")) {
                            Log.e("msg1", "something went wrong");
                        } else {
                            Log.e("msg", "User Registration Successfully");
                            //Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
                            Intent registration = new Intent(Registration.this,BarcodeScanner.class);
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
                params.put("Name",Name);
                params.put("Email",Email);
                params.put("Password",Password);
                params.put("Mbileno",Mbileno);
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }
}
