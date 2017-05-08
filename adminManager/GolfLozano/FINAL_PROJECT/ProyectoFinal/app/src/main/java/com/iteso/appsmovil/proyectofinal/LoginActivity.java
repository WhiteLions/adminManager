package com.iteso.appsmovil.proyectofinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iteso.appsmovil.proyectofinal.pojos.LoginBean;
import com.iteso.appsmovil.proyectofinal.rest.clients.AppsNubeRestClient;
import com.iteso.appsmovil.proyectofinal.utility.Utility;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public final class LoginActivity extends Activity {

    private EditText editUsername;
    private EditText editPassword;
    private Button btnLogin;

    private static final String TAG = "LoginActivity";

    public LoginActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.editUsername = (EditText) this.findViewById(R.id.et_username);
        this.editPassword = (EditText) this.findViewById(R.id.et_password);
        this.btnLogin = (Button) this.findViewById(R.id.btn_login);

        if (this.isUserLogged()){
            this.navigateToMainScreen();
        }

    }

    public void doLogin(View view){

        String user = this.editUsername.getText().toString();
        String pass = this.editPassword.getText().toString();


        if ( Utility.isNotNull(user) && Utility.isNotNull(pass) ){

            RequestParams requestParams = new RequestParams();
            requestParams.put("username",user);
            requestParams.put("password",pass);

            AppsNubeRestClient.post("login", requestParams, new LoginActivity.doLoginJsonAsyncResponseHandler());

        }else{

            Toast.makeText(this.getApplicationContext(),this.getString(R.string.empty_user_password),Toast.LENGTH_LONG).show();

        }

    }

    private void navigateToMainScreen(){

        Intent intent = new Intent(this,MainActivity.class);
        this.startActivity(intent);
        this.finish();

    }

    private void saveLoginState(LoginBean loginBean){

        SharedPreferences settings = this.getSharedPreferences(Utility.MY_SETTINGS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username",loginBean.getUsername());
        editor.putBoolean("authorized",loginBean.isAuthorized());
        editor.commit();

    }

    private Boolean isUserLogged(){

        SharedPreferences settings = this.getSharedPreferences(Utility.MY_SETTINGS,Context.MODE_PRIVATE);

        return settings.getBoolean("authorized",Boolean.FALSE);

    }

    private final class doLoginJsonAsyncResponseHandler extends JsonHttpResponseHandler {


        @Override
        public void onSuccess(int statusCode,org.apache.http.Header[] headers,org.json.JSONObject response){

            try {

                LoginBean loginBean = Utility.getGensonInst().deserialize(response.toString(),LoginBean.class);
                if ( loginBean.isAuthorized() ){

                    LoginActivity.this.saveLoginState(loginBean);
                    LoginActivity.this.navigateToMainScreen();

                }else{

                    Toast.makeText(LoginActivity.this.getApplicationContext(),LoginActivity.this.getString(R.string.invalid_login), Toast.LENGTH_LONG).show();

                }


            } catch (Exception e) {

                Toast.makeText(LoginActivity.this.getApplicationContext(),"Something went wrong parsing the JSON Response.", Toast.LENGTH_LONG).show();
                Log.e(LoginActivity.TAG,"FAILED TO PARSE JSON RESPONSE DURING LOGIN : " + response.toString());
                e.printStackTrace();
            }

        }

        @Override
        public void onFailure(int statusCode,org.apache.http.Header[] headers,java.lang.Throwable throwable,org.json.JSONObject errorResponse){


            if(statusCode == 404){
                Toast.makeText(getApplicationContext(), "Requested resource not found : HTTP CODE 404", Toast.LENGTH_LONG).show();
                Log.e(LoginActivity.TAG,"HTTP 404 during rest call to /login");
                throwable.printStackTrace();
            }

            else if(statusCode == 500){
                Toast.makeText(getApplicationContext(), "Something went wrong at server end : HTTP CODE 500", Toast.LENGTH_LONG).show();
                Log.e(LoginActivity.TAG,"HTTP 500 during rest call to /login");
                throwable.printStackTrace();
            }

            else{
                Toast.makeText(getApplicationContext(), "Unknown Error occurred!", Toast.LENGTH_LONG).show();
                Log.e(LoginActivity.TAG,"Unknown error during rest call to /login STATUS : " + statusCode);
                throwable.printStackTrace();
            }

        }


    }


}


