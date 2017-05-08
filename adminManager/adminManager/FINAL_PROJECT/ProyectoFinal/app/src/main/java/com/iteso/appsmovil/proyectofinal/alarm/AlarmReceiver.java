package com.iteso.appsmovil.proyectofinal.alarm;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.iteso.appsmovil.proyectofinal.R;
import com.iteso.appsmovil.proyectofinal.pojos.MonthlyIncomeReport;
import com.iteso.appsmovil.proyectofinal.pojos.PaymentBean;
import com.iteso.appsmovil.proyectofinal.rest.clients.AppsNubeRestClient;
import com.iteso.appsmovil.proyectofinal.utility.Utility;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by dideleo on 13/05/2015.
 */
public final class AlarmReceiver extends BroadcastReceiver{

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"Payment Received",Toast.LENGTH_LONG).show();
        this.context = context;

        RequestParams params = new RequestParams();
        params.put("username",this.getUsername());

        AppsNubeRestClient.get("push/check/payments",params,new doPopulateAlarmReportJsonAsyncResponseHandler());

    }

    private String getUsername(){

        SharedPreferences settings = this.context.getSharedPreferences(Utility.MY_SETTINGS, Context.MODE_PRIVATE);

        return settings.getString("username",null);

    }

    private final class doPopulateAlarmReportJsonAsyncResponseHandler extends JsonHttpResponseHandler {


        @Override
        public void onSuccess(int statusCode,org.apache.http.Header[] headers,org.json.JSONObject response){

            try {

                PaymentBean payment = Utility.getGensonInst().deserialize(response.toString(),PaymentBean.class);

                String ns = Context.NOTIFICATION_SERVICE;

                NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(ns);

                Notification notification = new Notification.Builder(context).setContentTitle("Payment received").setContentText(payment.getAmount()).setSmallIcon(R.drawable.ic_launcher).build();

                mNotificationManager.notify(666, notification);


            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        @Override
        public void onFailure(int statusCode,org.apache.http.Header[] headers,java.lang.Throwable throwable,org.json.JSONObject errorResponse){


            if(statusCode == 404){

                throwable.printStackTrace();
            }

            else if(statusCode == 500){

                throwable.printStackTrace();
            }

            else{


                throwable.printStackTrace();
            }

        }


    }



}
