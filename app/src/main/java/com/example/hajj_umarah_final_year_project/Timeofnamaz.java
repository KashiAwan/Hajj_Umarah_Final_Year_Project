package com.example.hajj_umarah_final_year_project;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// yaha se aage

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class Timeofnamaz extends Fragment {


    private static final String TAG = "tag";
    //Url
    //this Prayer Timings is only for london now
    String Url;

    // Tag used to cancel the request
    String tag_json_obj = "json_obj_req";

    //progress Dialog
    ProgressDialog pDialog;

    TextView mFajrTv, mDhuhrTv, mAsrTv, mMaghribTv, mIshaTv, mLocationTv, mDateTv;
    EditText mSearchEt;
    Button mSearchBtn;



    public Timeofnamaz() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewroots = inflater.inflate(R.layout.fragment_timeofnamaz, container, false);

        mFajrTv      = viewroots.findViewById(R.id.fajrTv);
        mDhuhrTv     = viewroots.findViewById(R.id.dhuhrTv);
        mAsrTv       = viewroots.findViewById(R.id.asrTv);
        mMaghribTv   = viewroots.findViewById(R.id.maghribTv);
        mIshaTv      = viewroots.findViewById(R.id.ishaTv);
        mLocationTv      = viewroots.findViewById(R.id.locationTv);
        mDateTv      = viewroots.findViewById(R.id.dateTv);
        mSearchEt      = viewroots.findViewById(R.id.searchEt);
        mSearchBtn      = viewroots.findViewById(R.id.searchBtn);

        //handle Button Click

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get text from edit text
                String mLocation = mSearchEt.getText().toString().trim();

                // validate if it is null or not

                if (mLocation.isEmpty()){
                    Toast.makeText(getActivity(), "Please enter Location", Toast.LENGTH_SHORT).show();
                }
                else {
                    Url  = "https://muslimsalat.com/"+mLocation+".json?key=6e45d68e5d2d1563b584005149e65612";
                    // function to get location
                    searchlocation();
                }

            }
        });



        return viewroots;
    }

    private void searchlocation() {

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            //get Location
                            String country = response.get("country").toString();
                            String state = response.get("state").toString();
                            String city = response.get("city").toString();
                            String location = country +","+ state +","+city;

                            // get date

                            String date = response.getJSONArray("items").getJSONObject(0).get("date_for").toString();



                            // for getting namaz timings
                            String mFajar = response.getJSONArray("items").getJSONObject(0).get("fajr").toString();
                            String mDhuhr = response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString();
                            String mAsr = response.getJSONArray("items").getJSONObject(0).get("asr").toString();
                            String mMaghrib = response.getJSONArray("items").getJSONObject(0).get("maghrib").toString();
                            String mIsha = response.getJSONArray("items").getJSONObject(0).get("isha").toString();

                            mFajrTv.setText(mFajar);
                            mDhuhrTv.setText(mDhuhr);
                            mAsrTv.setText(mAsr);
                            mMaghribTv.setText(mMaghrib);
                            mIshaTv.setText(mIsha);
                            mLocationTv.setText(location);
                            mDateTv.setText(date);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
