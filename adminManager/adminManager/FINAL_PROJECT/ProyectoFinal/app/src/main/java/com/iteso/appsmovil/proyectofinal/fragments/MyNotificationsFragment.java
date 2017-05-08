package com.iteso.appsmovil.proyectofinal.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.iteso.appsmovil.proyectofinal.MyNotificationDetail;
import com.iteso.appsmovil.proyectofinal.R;
import com.iteso.appsmovil.proyectofinal.pojos.MonthlyIncomeReport;
import com.iteso.appsmovil.proyectofinal.rest.clients.AppsNubeRestClient;
import com.iteso.appsmovil.proyectofinal.utility.Utility;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by dideleo on 10/05/2015.
 */
public final class MyNotificationsFragment extends ListFragment {

    private static final String TAG = "MyNotificationsFragment";

    private MonthlyIncomeReport report;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        this.view = super.onCreateView(inflater,container,savedInstanceState);

        return this.view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.populateList();

    }

    private void populateList(){

        RequestParams params = new RequestParams();
        params.put("username",this.getUsername());

        AppsNubeRestClient.get("notification/query", params, new doPopulateMyNotificationsJsonAsyncResponseHandler());

    }

    private String getUsername(){

        SharedPreferences settings = this.view.getContext().getSharedPreferences(Utility.MY_SETTINGS, Context.MODE_PRIVATE);

        return settings.getString("username",null);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        String item = this.getListAdapter().getItem(position).toString();

        if ( item.contains("No-notifications-found") ){
            return;
        }

        Intent intent = new Intent(v.getContext(),MyNotificationDetail.class);

        intent.putExtra("ClickedItem",item);

        this.startActivityForResult(intent,666);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        this.populateList();
        ArrayAdapter<String> adapter = (ArrayAdapter) this.getListAdapter();
        adapter.notifyDataSetChanged();

    }


    private final class doPopulateMyNotificationsJsonAsyncResponseHandler extends JsonHttpResponseHandler {


       @Override
        public void onSuccess(int statusCode,org.apache.http.Header[] headers,org.json.JSONObject response){

            try {

                MyNotificationsFragment.this.report = Utility.getGensonInst().deserialize(response.toString(),MonthlyIncomeReport.class);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.fragment_my_notifications,MyNotificationsFragment.this.report.getReportLines());
                setListAdapter(adapter);

            } catch (Exception e) {

                Log.e(TAG, "FAILED TO PARSE JSON RESPONSE DURING MY NOTIFICATIONS: " + response.toString());
                e.printStackTrace();

            }

        }

        @Override
        public void onFailure(int statusCode,org.apache.http.Header[] headers,java.lang.Throwable throwable,org.json.JSONObject errorResponse){


            if(statusCode == 404){
                Log.e(TAG,"HTTP 404 during rest call to /notification/query");
                throwable.printStackTrace();
            }

            else if(statusCode == 500){
                Log.e(TAG,"HTTP 500 during rest call to /notification/query");
                throwable.printStackTrace();
            }

            else{

                Log.e(TAG,"Unknown error during rest call to /notification/query STATUS : " + statusCode);
                throwable.printStackTrace();
            }

        }


    }


}
