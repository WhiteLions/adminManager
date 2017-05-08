package com.iteso.appsmovil.proyectofinal.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.iteso.appsmovil.proyectofinal.LoginActivity;
import com.iteso.appsmovil.proyectofinal.R;
import com.iteso.appsmovil.proyectofinal.pojos.LoginBean;
import com.iteso.appsmovil.proyectofinal.pojos.NewNotificationBean;
import com.iteso.appsmovil.proyectofinal.pojos.NewNotificationResponse;
import com.iteso.appsmovil.proyectofinal.rest.clients.AppsNubeRestClient;
import com.iteso.appsmovil.proyectofinal.utility.Utility;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.owlike.genson.Genson;

/**
 * Created by dideleo on 10/05/2015.
 */
public final class NewNotificationFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "NewNotificationFragment";

    private Spinner companySpinner;
    private String[] companySPinnerItems;

    private Spinner hospitalSpinner;
    private String[] hospitalSPinnerItems;

    private EditText textPatientName;
    private EditText textTreatment;

    private Button btnClear;
    private Button btnSend;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        this.view = inflater.inflate(R.layout.fragment_new_notification, container, false);

        this.textPatientName = (EditText) this.view.findViewById(R.id.new_notification_text_patient_name);
        this.textTreatment = (EditText) this.view.findViewById(R.id.new_notification_text_treatment);
        this.btnClear = (Button) this.view.findViewById(R.id.new_notification_btn_clear);
        this.btnSend = (Button) this.view.findViewById(R.id.new_notification_btn_send);

        this.btnClear.setOnClickListener(this);
        this.btnSend.setOnClickListener(this);


        this.setCompanySpinner(this.view);
        this.setHospitalSpinner(this.view);

        return this.view;
    }

    private String getUsername(){

        SharedPreferences settings = this.view.getContext().getSharedPreferences(Utility.MY_SETTINGS, Context.MODE_PRIVATE);

        return settings.getString("username",null);

    }

    private void setCompanySpinner(View view){

        this.companySpinner = (Spinner) view.findViewById(R.id.new_notification_company_spinner);
        this.companySPinnerItems = view.getResources().getStringArray(R.array.company_spinner_items);
        ArrayAdapter<String> compSpinArrayAdapter = new ArrayAdapter<String>(view.getContext(),R.layout.spinner_list_text,this.companySPinnerItems);
        compSpinArrayAdapter.setDropDownViewResource(R.layout.spinner_list_text);
        this.companySpinner.setAdapter(compSpinArrayAdapter);

    }

    private void setHospitalSpinner(View view){

        this.hospitalSpinner = (Spinner) view.findViewById(R.id.new_notification_hospital_spinner);
        this.hospitalSPinnerItems = view.getResources().getStringArray(R.array.hospital_spinner_items);
        ArrayAdapter<String> hosSpinArrayAdapter = new ArrayAdapter<String>(view.getContext(),R.layout.spinner_list_text,this.hospitalSPinnerItems);
        hosSpinArrayAdapter.setDropDownViewResource(R.layout.spinner_list_text);
        this.hospitalSpinner.setAdapter(hosSpinArrayAdapter);

    }

    private void clearFields(){

        this.companySpinner.setSelection(0);
        this.hospitalSpinner.setSelection(0);
        this.textPatientName.setText("");
        this.textTreatment.setText("");

    }

    private void sendBtn(){

        if ( this.validateFieldsNotNull() ){

            Genson genson = Utility.getGensonInst();
            NewNotificationBean notification = new NewNotificationBean(this.companySpinner.getSelectedItem().toString(),
                    this.hospitalSpinner.getSelectedItem().toString(),
                    this.textPatientName.getText().toString(),
                    this.textTreatment.getText().toString(),
                    this.getUsername(),
                    -1);


            RequestParams params = new RequestParams();
            params.put("NewNotificationJson",Utility.getGensonInst().serialize(notification));

            AppsNubeRestClient.post("notification/create",params,new doCreateNotificationJsonAsyncResponseHandler());

        }else{

            Toast.makeText(this.view.getContext(),R.string.empty_patient_treatment,Toast.LENGTH_LONG).show();

        }

    }

    private Boolean validateFieldsNotNull(){

        return ( Utility.isNotNull(this.companySpinner.getSelectedItem().toString()) &&
                Utility.isNotNull(this.hospitalSpinner.getSelectedItem().toString()) &&
                Utility.isNotNull(this.textPatientName.getText().toString())&&
                Utility.isNotNull(this.textTreatment.getText().toString()) );

    }

    private final class doCreateNotificationJsonAsyncResponseHandler extends JsonHttpResponseHandler {


        @Override
        public void onSuccess(int statusCode,org.apache.http.Header[] headers,org.json.JSONObject response){

            try {

                NewNotificationResponse notificationResponse = Utility.getGensonInst().deserialize(response.toString(),NewNotificationResponse.class);

                if ( notificationResponse.getStatus().equals(NewNotificationResponse.CREATED) ){

                    Toast.makeText(NewNotificationFragment.this.view.getContext(),notificationResponse.getDesc(), Toast.LENGTH_LONG).show();

                    NewNotificationFragment.this.clearFields();

                }else{

                    Toast.makeText(NewNotificationFragment.this.view.getContext(),notificationResponse.getDesc(), Toast.LENGTH_LONG).show();

                }


            } catch (Exception e) {

                Toast.makeText(NewNotificationFragment.this.view.getContext(),"Something went wrong parsing the JSON Response.", Toast.LENGTH_LONG).show();
                Log.e(NewNotificationFragment.TAG,"FAILED TO PARSE JSON RESPONSE DURING NEW NOIFICATION CREATE : " + response.toString());
                e.printStackTrace();

            }

        }

        @Override
        public void onFailure(int statusCode,org.apache.http.Header[] headers,java.lang.Throwable throwable,org.json.JSONObject errorResponse){


            if(statusCode == 404){
                Toast.makeText(NewNotificationFragment.this.view.getContext(), "Requested resource not found : HTTP CODE 404", Toast.LENGTH_LONG).show();
                Log.e(NewNotificationFragment.TAG,"HTTP 404 during rest call to /notification/create");
                throwable.printStackTrace();
            }

            else if(statusCode == 500){
                Toast.makeText(NewNotificationFragment.this.view.getContext(), "Something went wrong at server end : HTTP CODE 500", Toast.LENGTH_LONG).show();
                Log.e(NewNotificationFragment.TAG,"HTTP 500 during rest call to /notification/create");
                throwable.printStackTrace();
            }

            else{
                Toast.makeText(NewNotificationFragment.this.view.getContext(), "Unknown Error occurred!", Toast.LENGTH_LONG).show();
                Log.e(NewNotificationFragment.TAG,"Unknown error during rest call to /notification/create STATUS : " + statusCode);
                throwable.printStackTrace();
            }

        }


    }


    @Override
    public void onClick(View v) {

        switch ( v.getId() ){

            case R.id.new_notification_btn_clear :
                this.clearFields();
                break;

            case R.id.new_notification_btn_send :
                this.sendBtn();
                break;

        }


    }
}
