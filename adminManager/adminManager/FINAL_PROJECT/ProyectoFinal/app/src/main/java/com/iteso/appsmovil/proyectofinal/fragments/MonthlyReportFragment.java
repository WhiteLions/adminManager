package com.iteso.appsmovil.proyectofinal.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.iteso.appsmovil.proyectofinal.R;
import com.iteso.appsmovil.proyectofinal.pojos.MonthlyIncomeReport;
import com.iteso.appsmovil.proyectofinal.rest.clients.AppsNubeRestClient;
import com.iteso.appsmovil.proyectofinal.utility.Utility;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by dideleo on 10/05/2015.
 */
public final class MonthlyReportFragment extends ListFragment {

    private static final String TAG = "MonthlyReportFragment";
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

        this.populateListFragment();

    }

    private void populateListFragment(){

        RequestParams params = new RequestParams();
        params.put("username",this.getUsername());

        AppsNubeRestClient.get("report/income/monthly",params, new doPopulateIncomeReportJsonAsyncResponseHandler());

    }

    private String getUsername(){

        SharedPreferences settings = this.view.getContext().getSharedPreferences(Utility.MY_SETTINGS, Context.MODE_PRIVATE);

        return settings.getString("username",null);

    }

    private final class doPopulateIncomeReportJsonAsyncResponseHandler extends JsonHttpResponseHandler {


        @Override
        public void onSuccess(int statusCode,org.apache.http.Header[] headers,org.json.JSONObject response){

            try {

                MonthlyReportFragment.this.report = Utility.getGensonInst().deserialize(response.toString(),MonthlyIncomeReport.class);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.fragment_monthly_report, MonthlyReportFragment.this.report.getReportLines());
                MonthlyReportFragment.this.setListAdapter(adapter);

            } catch (Exception e) {

                Log.e(MonthlyReportFragment.TAG, "FAILED TO PARSE JSON RESPONSE DURING MONTHLY REPORT : " + response.toString());
                e.printStackTrace();

            }

        }

        @Override
        public void onFailure(int statusCode,org.apache.http.Header[] headers,java.lang.Throwable throwable,org.json.JSONObject errorResponse){


            if(statusCode == 404){
                Log.e(MonthlyReportFragment.TAG,"HTTP 404 during rest call to /report/income/monthly");
                throwable.printStackTrace();
            }

            else if(statusCode == 500){
                Log.e(MonthlyReportFragment.TAG,"HTTP 500 during rest call to /report/income/monthly");
                throwable.printStackTrace();
            }

            else{

                Log.e(MonthlyReportFragment.TAG,"Unknown error during rest call to /report/income/monthly STATUS : " + statusCode);
                throwable.printStackTrace();
            }

        }


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // do something with the data
    }

}
