package com.example.samplequest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitorsActivity extends AppCompatActivity {

    private static final String TAG = "VisitorsActivity";



    Globalpreference globalPreference;
    private String ip;


    EditText nameET;
    EditText jobET;
    EditText postal_addressET;
    EditText companyET;
    EditText emailET;
    EditText phone_numberET;
    AutoCompleteTextView employee_to_meetET;
    EditText employee_to_meet_labelET;
    EditText identificationET;
    EditText identification_labelET;
    Spinner purpose_of_visitET;
    Button SubmitBT;

    String selectedPurpose;

    private ImageView backImageButton;
    private boolean val;
    private List<String> employeeSuggestions;
    private List<String> purposeofVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitors);

        nameET = findViewById(R.id.VisitornameET);
        jobET = findViewById(R.id.VisitorjobET);
        postal_addressET = findViewById(R.id.VisitoraddressET);
        companyET = findViewById(R.id.VisitorcompanyET);
        emailET = findViewById(R.id.VisitoremailET);
        phone_numberET = findViewById(R.id.VisitorphoneET);
        employee_to_meetET = findViewById(R.id.VisitoremploytomeetET);
        employee_to_meet_labelET = findViewById(R.id.VisitoremploytolabelET);
        identificationET = findViewById(R.id.VisitoridentificationET);
        identification_labelET = findViewById(R.id.VisitoridentificationlabelET);
        purpose_of_visitET = findViewById(R.id.Visitorpurpose_of_visitSpinner);
        SubmitBT = findViewById(R.id.VisitorsubmitBT);
        backImageButton = findViewById(R.id.VBackImageButton);

        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bIntent = new Intent(VisitorsActivity.this,MainActivity.class);
                startActivity(bIntent);
            }
        });

        globalPreference = new Globalpreference(this);
        ip = globalPreference.getIp();

        employeeSuggestions = new ArrayList<>();
        // Fetch employee suggestions from the database
        EmployeeSuggestionsFromDatabase();


        // Set up ArrayAdapter for AutoCompleteTextView
        ArrayAdapter<String> employeeAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, employeeSuggestions
        );

        purposeofVisit = new ArrayList<>();
        // Fetch purpose of visit suggestions from the database
        PurposeOfVisitfromDatabase();


        ArrayAdapter<String> purposeAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_hint_item,  // Custom hint layout
                purposeofVisit
        );

        purposeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employee_to_meetET.setAdapter(employeeAdapter);

        purpose_of_visitET.setAdapter(purposeAdapter);

        SubmitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                insert();
            }
        });

        purpose_of_visitET.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Exclude hint item (position 0)
                if (position != 0) {
                     selectedPurpose = purposeofVisit.get(position); // Adjust the index
                    // Do something with the selected purpose
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle nothing selected if needed
            }
        });

    }


    private void insert() {
      //  Toast.makeText(this, "asdfghj"+selectedPurpose, Toast.LENGTH_SHORT).show();


        if (nameET.getText().toString().equals("")) {
            nameET.setError("Please Enter name");
        }


        else if (selectedPurpose == null || selectedPurpose.equals(purposeofVisit.get(0))) {
            Toast.makeText(VisitorsActivity.this, "Please select a valid purpose of visit", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (phone_numberET.getText().equals("") || phone_numberET.getText().length() > 10 || phone_numberET.getText().length() < 10) {
            phone_numberET.setError("Invalid Phone number ");
        }
        else if (companyET.getText().toString().equals("")) {
            companyET.setError("Please Enter Company");
        }
        else if (emailET.getText().toString().equals("")) {
            emailET.setError("Please Enter Email");
        }
        else if (identification_labelET.getText().equals("") || identification_labelET.getText().length() < 3) {
            identification_labelET.setError("Field cannot be empty");
        }

        else if (emailET.getText().length()>0) {
            val =  validateEmail(emailET);
            if(val==true){
                VisitorsData();
            }
            else{
                Toast.makeText(VisitorsActivity.this,"Please Check Your Email id",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void VisitorsData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/Sample Quest/visitors.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Toast.makeText(VisitorsActivity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VisitorsActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VisitorsActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", nameET.getText().toString());
                params.put("job", jobET.getText().toString());
                params.put("address", postal_addressET.getText().toString());
                params.put("company", companyET.getText().toString());
                params.put("email", emailET.getText().toString());
                params.put("phone", phone_numberET.getText().toString());
                params.put("employee_meet", employee_to_meetET.getText().toString());
                params.put("employee_to_meet_label", employee_to_meet_labelET.getText().toString());
                params.put("identification", identificationET.getText().toString());
                params.put("identification_label", identification_labelET.getText().toString());
                params.put("selectedPurpose",selectedPurpose);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(VisitorsActivity.this);
        requestQueue.add(stringRequest);
    }
    private boolean validateEmail(EditText emailET) {
        String email = emailET.getText().toString();

        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            // Toast.makeText(RegisterActivity.this,"Email Validated",Toast.LENGTH_SHORT).show();
            return true;
        }else {
            // Toast.makeText(RegisterActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
            return false;

        }
    }
    private void EmployeeSuggestionsFromDatabase() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://"+ ip +"/Sample Quest/getemployees.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);

                if(response.equals("failed")){
                    Toast.makeText(VisitorsActivity.this, "No employees found", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for(int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String employees = object.getString("name");
                            employeeSuggestions.add(employees);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VisitorsActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    private void PurposeOfVisitfromDatabase() {

        purposeofVisit.add(0, "Select a purpose of visit...");


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://"+ ip +"/Sample Quest/getpurpose.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);



                if(response.equals("failed")){
                    Toast.makeText(VisitorsActivity.this, "Nothing found", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for(int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String purpose = object.getString("purpose");

                            purposeofVisit.add(purpose);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VisitorsActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}