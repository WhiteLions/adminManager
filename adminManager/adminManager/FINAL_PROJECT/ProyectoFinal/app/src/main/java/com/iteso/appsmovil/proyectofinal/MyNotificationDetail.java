package com.iteso.appsmovil.proyectofinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.iteso.appsmovil.proyectofinal.fragments.MyNotificationsFragment;
import com.iteso.appsmovil.proyectofinal.pojos.NewNotificationBean;
import com.iteso.appsmovil.proyectofinal.pojos.NewNotificationResponse;
import com.iteso.appsmovil.proyectofinal.rest.clients.AppsNubeRestClient;
import com.iteso.appsmovil.proyectofinal.utility.Utility;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.owlike.genson.Genson;


public class MyNotificationDetail extends ActionBarActivity implements View.OnClickListener{

    private static final String TAG = "MyNotificationDetail";


    private NewNotificationBean notification ;
    private Spinner companySpinner;
    private String[] companySPinnerItems;

    private Spinner hospitalSpinner;
    private String[] hospitalSPinnerItems;

    private EditText textPatientName;
    private EditText textTreatment;

    private Button btnClear;
    private Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notification_detail);

        this.textPatientName = (EditText) this.findViewById(R.id.new_notification_text_patient_name);
        this.textTreatment = (EditText) this.findViewById(R.id.new_notification_text_treatment);
        this.btnClear = (Button) this.findViewById(R.id.new_notification_btn_clear);
        this.btnSend = (Button) this.findViewById(R.id.new_notification_btn_send);
        this.btnSend.setOnClickListener(this);

        this.btnClear.setEnabled(false);

        this.setCompanySpinner();
        this.setHospitalSpinner();

        String item = this.getIntent().getStringExtra("ClickedItem");

        this.fillNotificationBean(item);
        this.setFields();

    }

    private void setFields(){

        this.textPatientName.setText(this.notification.getPatientName());
        this.textTreatment.setText(this.notification.getTreatmentName());
        this.setSelectedCompanySpinner();
        this.setSelectedHospitalSpinner();

    }

    private int findSpinnerPosition(String item,String[] spinnerItems){

        int index = 0;
        item = item.trim();
       for ( int position = 0; position < spinnerItems.length ; position++ ){

           if ( spinnerItems[position].equals(item) ){

               index = position;
               break;

           }

       }

       return index;

    }

    private void setSelectedCompanySpinner(){

        this.companySpinner.setSelection(this.findSpinnerPosition(this.notification.getCompanyName(),this.companySPinnerItems));

    }

    private void setSelectedHospitalSpinner(){

        this.hospitalSpinner.setSelection(this.findSpinnerPosition(this.notification.getHospitalName(),this.hospitalSPinnerItems));

    }

    private void setCompanySpinner(){

        this.companySpinner = (Spinner) findViewById(R.id.new_notification_company_spinner);
        this.companySPinnerItems = getResources().getStringArray(R.array.company_spinner_items);
        ArrayAdapter<String> compSpinArrayAdapter = new ArrayAdapter<String>(this.getApplicationContext(),R.layout.spinner_list_text,this.companySPinnerItems);
        compSpinArrayAdapter.setDropDownViewResource(R.layout.spinner_list_text);
        this.companySpinner.setAdapter(compSpinArrayAdapter);


    }

    private void setHospitalSpinner(){

        this.hospitalSpinner = (Spinner) findViewById(R.id.new_notification_hospital_spinner);
        this.hospitalSPinnerItems = getResources().getStringArray(R.array.hospital_spinner_items);
        ArrayAdapter<String> hosSpinArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_list_text,this.hospitalSPinnerItems);
        hosSpinArrayAdapter.setDropDownViewResource(R.layout.spinner_list_text);
        this.hospitalSpinner.setAdapter(hosSpinArrayAdapter);

    }

    private void fillNotificationBean(String item){

        String[] array = item.split("\n");

        this.notification = new NewNotificationBean(array[1].split(":")[1],array[2].split(":")[1],array[3].split(":")[1],array[4].split(":")[1],this.getUsername(),Integer.parseInt(array[0].split(":")[1]));

    }

    private String getUsername(){

        SharedPreferences settings = this.getSharedPreferences(Utility.MY_SETTINGS, Context.MODE_PRIVATE);

        return settings.getString("username",null);

    }

    private Boolean validateFieldsNotNull(){

        return ( Utility.isNotNull(this.companySpinner.getSelectedItem().toString()) &&
                Utility.isNotNull(this.hospitalSpinner.getSelectedItem().toString()) &&
                Utility.isNotNull(this.textPatientName.getText().toString())&&
                Utility.isNotNull(this.textTreatment.getText().toString()) );

    }


    private void updateNotification(){


        if ( this.validateFieldsNotNull() ){

            NewNotificationBean updatedNotification = new NewNotificationBean(this.companySpinner.getSelectedItem().toString(),
                    this.hospitalSpinner.getSelectedItem().toString(),
                    this.textPatientName.getText().toString(),
                    this.textTreatment.getText().toString(),
                    this.getUsername(),
                    this.notification.getId());

            RequestParams params = new RequestParams();
            params.put("updatedNotification", Utility.getGensonInst().serialize(updatedNotification));

            AppsNubeRestClient.post("notification/update", params, new doUpdateNotificationJsonAsyncResponseHandler());


        }else{

            Toast.makeText(this.getApplicationContext(),R.string.empty_patient_treatment,Toast.LENGTH_LONG).show();

        }

    }

    private final class doUpdateNotificationJsonAsyncResponseHandler extends JsonHttpResponseHandler {


        @Override
        public void onSuccess(int statusCode,org.apache.http.Header[] headers,org.json.JSONObject response){

            try {

                NewNotificationResponse notificationResponse = Utility.getGensonInst().deserialize(response.toString(),NewNotificationResponse.class);

                if ( notificationResponse.getStatus().equals(NewNotificationResponse.CREATED) ){

                    Toast.makeText(MyNotificationDetail.this.getApplicationContext(),notificationResponse.getDesc(), Toast.LENGTH_LONG).show();

                    MyNotificationDetail.this.finish();

                }else{

                    Toast.makeText(MyNotificationDetail.this.getApplicationContext(),notificationResponse.getDesc(), Toast.LENGTH_LONG).show();

                }


            } catch (Exception e) {

                Toast.makeText(MyNotificationDetail.this.getApplicationContext(),"Something went wrong parsing the JSON Response.", Toast.LENGTH_LONG).show();
                Log.e(MyNotificationDetail.TAG,"FAILED TO PARSE JSON RESPONSE DURING NEW NOIFICATION CREATE : " + response.toString());
                e.printStackTrace();

            }

        }

        @Override
        public void onFailure(int statusCode,org.apache.http.Header[] headers,java.lang.Throwable throwable,org.json.JSONObject errorResponse){


            if(statusCode == 404){
                Toast.makeText(MyNotificationDetail.this.getApplicationContext(), "Requested resource not found : HTTP CODE 404", Toast.LENGTH_LONG).show();
                Log.e(MyNotificationDetail.TAG,"HTTP 404 during rest call to /notification/create");
                throwable.printStackTrace();
            }

            else if(statusCode == 500){
                Toast.makeText(MyNotificationDetail.this.getApplicationContext(), "Something went wrong at server end : HTTP CODE 500", Toast.LENGTH_LONG).show();
                Log.e(MyNotificationDetail.TAG,"HTTP 500 during rest call to /notification/create");
                throwable.printStackTrace();
            }

            else{
                Toast.makeText(MyNotificationDetail.this.getApplicationContext(), "Unknown Error occurred!", Toast.LENGTH_LONG).show();
                Log.e(MyNotificationDetail.TAG,"Unknown error during rest call to /notification/create STATUS : " + statusCode);
                throwable.printStackTrace();
            }

        }


    }



    @Override
    public void onClick(View v) {

        switch ( v.getId() ){

            case R.id.new_notification_btn_send:
                updateNotification();

        }

    }
}
