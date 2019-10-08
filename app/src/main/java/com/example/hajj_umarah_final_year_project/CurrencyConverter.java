package com.example.hajj_umarah_final_year_project;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.StringTokenizer;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrencyConverter extends Fragment implements AdapterView.OnItemSelectedListener{


    private static final String TAG = "tag";
    //Url
    //this Prayer Timings is only for london now

    // Tag used to cancel the request
    String tag_json_obj = "json_obj_req";

    //progress Dialog
    ProgressDialog pDialog;

    TextView usdtxt, eurtxt, inrtxt, pkrtxt, afntxt, bdttxt, idrtxt, myrtxt, trytxt, aedtxt,  sartxt;
    EditText  addnum;
    Button cnvrtbtn , refresh;

    Double USD;
    Double EUR;
    Double INR;
    Double PKR;
    Double AFN;
    Double BDT;
    Double IDR;
    Double MYR;
    Double TRY;
    Double AED;
    Double SAR;

    SharedPreferences SharedPreferences ;

    Double AEDTOUSD;
    Double AEDTOEUR;
    Double AEDTOINR;
    Double AEDTOPKR;
    Double AEDTOAFN;
    Double AEDTOBDT;
    Double AEDTOIDR;
    Double AEDTOMYR;
    Double AEDTOTRY;
    Double AEDTOAED;
    Double AEDTOSAR;


    Double USDSS =  1.108158;
    Double EURSS = 1.0;
    Double INRSS = 79.536908;
    Double PKRSS = 177.671269;
    Double AFNSS = 87.034971;
    Double BDTSS = 93.832178;
    Double IDRSS = 15807.043645;
    Double MYRSS = 4.636521;
    Double TRYSS = 6.339794;
    Double AEDSS = 4.070541;
    Double SARSS = 4.156047;

    double etvalue;


    public CurrencyConverter() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View roootcvie= inflater.inflate(R.layout.fragment_currency_converter, container, false);



        usdtxt      =  roootcvie.findViewById(R.id.usd);
        eurtxt      =  roootcvie.findViewById(R.id.euro);
        inrtxt     =   roootcvie.findViewById(R.id.inr);
        pkrtxt     = roootcvie.findViewById(R.id.pkr);
        afntxt   =     roootcvie.findViewById(R.id.afn);
        bdttxt      =  roootcvie.findViewById(R.id.bdt);
        idrtxt      =  roootcvie.findViewById(R.id.idr);
        myrtxt      =  roootcvie.findViewById(R.id.myr);
        trytxt      =  roootcvie.findViewById(R.id.tury);
        aedtxt      =  roootcvie.findViewById(R.id.aed);
        sartxt      =  roootcvie.findViewById(R.id.sar);
        addnum = roootcvie.findViewById(R.id.et);
        cnvrtbtn = roootcvie.findViewById(R.id.cnvrtbtn);
        refresh = roootcvie.findViewById(R.id.refreshh);

        addnum.setInputType(InputType.TYPE_CLASS_NUMBER);

        SharedPreferences = getActivity().getSharedPreferences("Values" ,Context.MODE_PRIVATE);
        Currencconvertttt();

        Spinner spinner = (Spinner) roootcvie.findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.currency, android.R.layout.simple_gallery_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        // link wagera he ye



        // function to get location

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Currencconvertttt();
            }
        });

        cnvrtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return roootcvie;
    }

    public void Currencconvertttt() {

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();


        String Url  = "http://data.fixer.io/api/latest?access_key=9b3ce64e71cb1234f73ef956c99f5d83";



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONObject>() {



                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            //   Toast.makeText(currencconvert.this, "your code run", Toast.LENGTH_SHORT).show();

                            // get date

                            //String date = response.getJSONArray("JSON").getJSONObject(0).get("date").toString();


                            // for getting namaz timings

                            JSONObject newarray =new JSONObject(response.getJSONObject("rates").toString());

                            USD= newarray.getDouble("USD");
                            EUR= newarray.getDouble("EUR");
                            INR= newarray.getDouble("INR");
                            PKR= newarray.getDouble("PKR");
                            AFN= newarray.getDouble("AFN");
                            BDT= newarray.getDouble("BDT");
                            IDR= newarray.getDouble("IDR");
                            MYR= newarray.getDouble("MYR");
                            TRY= newarray.getDouble("TRY");
                            AED= newarray.getDouble("AED");
                            SAR= newarray.getDouble("SAR");


                            usdtxt.setText(USD.toString());
                            eurtxt.setText(EUR.toString());
                            inrtxt.setText(INR.toString());
                            pkrtxt.setText(PKR.toString());
                            afntxt.setText(AFN.toString());
                            bdttxt.setText(BDT.toString());
                            idrtxt.setText(IDR.toString());
                            myrtxt.setText(MYR.toString());
                            trytxt.setText(TRY.toString());
                            aedtxt.setText(AED.toString());
                            sartxt.setText(SAR.toString());

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

                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(request, tag_json_obj);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        etvalue = Double.parseDouble(addnum.getText().toString());

        switch (position) {

            case 0:

                AEDTOUSD = (EURSS / EURSS * USDSS * etvalue);
                AEDTOEUR = (EURSS / EURSS * EURSS * etvalue);
                AEDTOINR = (EURSS / EURSS * INRSS * etvalue);
                AEDTOPKR = (EURSS / EURSS * PKRSS * etvalue);
                AEDTOAFN = (EURSS / EURSS * AFNSS * etvalue);
                AEDTOBDT = (EURSS / EURSS * BDTSS * etvalue);
                AEDTOIDR = (EURSS / EURSS * IDRSS * etvalue);
                AEDTOMYR = (EURSS / EURSS * MYRSS * etvalue);
                AEDTOTRY = (EURSS / EURSS * TRYSS * etvalue);
                AEDTOAED = (EURSS / EURSS * AEDSS * etvalue);
                AEDTOSAR = (EURSS / EURSS * SARSS * etvalue);

                break;
            case 1:


                AEDTOUSD = (EURSS / AEDSS * USDSS * etvalue);
                AEDTOEUR = (EURSS / AEDSS * EURSS * etvalue);
                AEDTOINR = (EURSS / AEDSS * INRSS * etvalue);
                AEDTOPKR = (EURSS / AEDSS * PKRSS * etvalue);
                AEDTOAFN = (EURSS / AEDSS * AFNSS * etvalue);
                AEDTOBDT = (EURSS / AEDSS * BDTSS * etvalue);
                AEDTOIDR = (EURSS / AEDSS * IDRSS * etvalue);
                AEDTOMYR = (EURSS / AEDSS * MYRSS * etvalue);
                AEDTOTRY = (EURSS / AEDSS * TRYSS * etvalue);
                AEDTOAED = (EURSS / AEDSS * AEDSS * etvalue);
                AEDTOSAR = (EURSS / AEDSS * SARSS * etvalue);

                break;
            case 2:


                AEDTOUSD = (EURSS / SARSS * USDSS * etvalue);
                AEDTOEUR = (EURSS / SARSS * EURSS * etvalue);
                AEDTOINR = (EURSS / SARSS * INRSS * etvalue);
                AEDTOPKR = (EURSS / SARSS * PKRSS * etvalue);
                AEDTOAFN = (EURSS / SARSS * AFNSS * etvalue);
                AEDTOBDT = (EURSS / SARSS * BDTSS * etvalue);
                AEDTOIDR = (EURSS / SARSS * IDRSS * etvalue);
                AEDTOMYR = (EURSS / SARSS * MYRSS * etvalue);
                AEDTOTRY = (EURSS / SARSS * TRYSS * etvalue);
                AEDTOAED = (EURSS / SARSS * AEDSS * etvalue);
                AEDTOSAR = (EURSS / SARSS * SARSS * etvalue);


                break;
            case 3:

                AEDTOUSD = (EURSS / USDSS * USDSS * etvalue);
                AEDTOEUR = (EURSS / USDSS * EURSS * etvalue);
                AEDTOINR = (EURSS / USDSS * INRSS * etvalue);
                AEDTOPKR = (EURSS / USDSS * PKRSS * etvalue);
                AEDTOAFN = (EURSS / USDSS * AFNSS * etvalue);
                AEDTOBDT = (EURSS / USDSS * BDTSS * etvalue);
                AEDTOIDR = (EURSS / USDSS * IDRSS * etvalue);
                AEDTOMYR = (EURSS / USDSS * MYRSS * etvalue);
                AEDTOTRY = (EURSS / USDSS * TRYSS * etvalue);
                AEDTOAED = (EURSS / USDSS * AEDSS * etvalue);
                AEDTOSAR = (EURSS / USDSS * SARSS * etvalue);

                break;
            case 4:


                AEDTOUSD = (EURSS / INRSS * USDSS * etvalue);
                AEDTOEUR = (EURSS / INRSS * EURSS * etvalue);
                AEDTOINR = (EURSS / INRSS * INRSS * etvalue);
                AEDTOPKR = (EURSS / INRSS * PKRSS * etvalue);
                AEDTOAFN = (EURSS / INRSS * AFNSS * etvalue);
                AEDTOBDT = (EURSS / INRSS * BDTSS * etvalue);
                AEDTOIDR = (EURSS / INRSS * IDRSS * etvalue);
                AEDTOMYR = (EURSS / INRSS * MYRSS * etvalue);
                AEDTOTRY = (EURSS / INRSS * TRYSS * etvalue);
                AEDTOAED = (EURSS / INRSS * AEDSS * etvalue);
                AEDTOSAR = (EURSS / INRSS * SARSS * etvalue);

                break;
            case 5:

                AEDTOUSD = (EURSS / PKRSS * USDSS * etvalue);
                AEDTOEUR = (EURSS / PKRSS * EURSS * etvalue);
                AEDTOINR = (EURSS / PKRSS * INRSS * etvalue);
                AEDTOPKR = (EURSS / PKRSS * PKRSS * etvalue);
                AEDTOAFN = (EURSS / PKRSS * AFNSS * etvalue);
                AEDTOBDT = (EURSS / PKRSS * BDTSS * etvalue);
                AEDTOIDR = (EURSS / PKRSS * IDRSS * etvalue);
                AEDTOMYR = (EURSS / PKRSS * MYRSS * etvalue);
                AEDTOTRY = (EURSS / PKRSS * TRYSS * etvalue);
                AEDTOAED = (EURSS / PKRSS * AEDSS * etvalue);
                AEDTOSAR = (EURSS / PKRSS * SARSS * etvalue);


                break;
            case 6:


                AEDTOUSD = (EURSS / AFNSS * USDSS * etvalue);
                AEDTOEUR = (EURSS / AFNSS * EURSS * etvalue);
                AEDTOINR = (EURSS / AFNSS * INRSS * etvalue);
                AEDTOPKR = (EURSS / AFNSS * PKRSS * etvalue);
                AEDTOAFN = (EURSS / AFNSS * AFNSS * etvalue);
                AEDTOBDT = (EURSS / AFNSS * BDTSS * etvalue);
                AEDTOIDR = (EURSS / AFNSS * IDRSS * etvalue);
                AEDTOMYR = (EURSS / AFNSS * MYRSS * etvalue);
                AEDTOTRY = (EURSS / AFNSS * TRYSS * etvalue);
                AEDTOAED = (EURSS / AFNSS * AEDSS * etvalue);
                AEDTOSAR = (EURSS / AFNSS * SARSS * etvalue);


                break;
            case 7:

                AEDTOUSD = (EURSS / BDTSS * USDSS * etvalue);
                AEDTOEUR = (EURSS / BDTSS * EURSS * etvalue);
                AEDTOINR = (EURSS / BDTSS * INRSS * etvalue);
                AEDTOPKR = (EURSS / BDTSS * PKRSS * etvalue);
                AEDTOAFN = (EURSS / BDTSS * AFNSS * etvalue);
                AEDTOBDT = (EURSS / BDTSS * BDTSS * etvalue);
                AEDTOIDR = (EURSS / BDTSS * IDRSS * etvalue);
                AEDTOMYR = (EURSS / BDTSS * MYRSS * etvalue);
                AEDTOTRY = (EURSS / BDTSS * TRYSS * etvalue);
                AEDTOAED = (EURSS / BDTSS * AEDSS * etvalue);
                AEDTOSAR = (EURSS / BDTSS * SARSS * etvalue);

                break;
            case 8:

                AEDTOUSD = (EURSS / IDRSS * USDSS * etvalue);
                AEDTOEUR = (EURSS / IDRSS * EURSS * etvalue);
                AEDTOINR = (EURSS / IDRSS * INRSS * etvalue);
                AEDTOPKR = (EURSS / IDRSS * PKRSS * etvalue);
                AEDTOAFN = (EURSS / IDRSS * AFNSS * etvalue);
                AEDTOBDT = (EURSS / IDRSS * BDTSS * etvalue);
                AEDTOIDR = (EURSS / IDRSS * IDRSS * etvalue);
                AEDTOMYR = (EURSS / IDRSS * MYRSS * etvalue);
                AEDTOTRY = (EURSS / IDRSS * TRYSS * etvalue);
                AEDTOAED = (EURSS / IDRSS * AEDSS * etvalue);
                AEDTOSAR = (EURSS / IDRSS * SARSS * etvalue);

                break;
            case 9:

                AEDTOUSD = (EURSS / MYRSS * USDSS * etvalue);
                AEDTOEUR = (EURSS / MYRSS * EURSS * etvalue);
                AEDTOINR = (EURSS / MYRSS * INRSS * etvalue);
                AEDTOPKR = (EURSS / MYRSS * PKRSS * etvalue);
                AEDTOAFN = (EURSS / MYRSS * AFNSS * etvalue);
                AEDTOBDT = (EURSS / MYRSS * BDTSS * etvalue);
                AEDTOIDR = (EURSS / MYRSS * IDRSS * etvalue);
                AEDTOMYR = (EURSS / MYRSS * MYRSS * etvalue);
                AEDTOTRY = (EURSS / MYRSS * TRYSS * etvalue);
                AEDTOAED = (EURSS / MYRSS * AEDSS * etvalue);
                AEDTOSAR = (EURSS / MYRSS * SARSS * etvalue);


                break;
            case 10:

                AEDTOUSD = (EURSS / TRYSS * USDSS * etvalue);
                AEDTOEUR = (EURSS / TRYSS * EURSS * etvalue);
                AEDTOINR = (EURSS / TRYSS * INRSS * etvalue);
                AEDTOPKR = (EURSS / TRYSS * PKRSS * etvalue);
                AEDTOAFN = (EURSS / TRYSS * AFNSS * etvalue);
                AEDTOBDT = (EURSS / TRYSS * BDTSS * etvalue);
                AEDTOIDR = (EURSS / TRYSS * IDRSS * etvalue);
                AEDTOMYR = (EURSS / TRYSS * MYRSS * etvalue);
                AEDTOTRY = (EURSS / TRYSS * TRYSS * etvalue);
                AEDTOAED = (EURSS / TRYSS * AEDSS * etvalue);
                AEDTOSAR = (EURSS / TRYSS * SARSS * etvalue);


        }

        usdtxt.setText(String.format("%f",AEDTOUSD));
        eurtxt.setText(String.format("%f",AEDTOEUR));
        inrtxt.setText(String.format("%f",AEDTOINR));
        pkrtxt.setText(String.format("%f",AEDTOPKR));
        afntxt.setText(String.format("%f",AEDTOAFN));
        bdttxt.setText(String.format("%f",AEDTOBDT));
        idrtxt.setText(String.format("%f",AEDTOIDR));
        myrtxt.setText(String.format("%f",AEDTOMYR));
        trytxt.setText(String.format("%f",AEDTOTRY));
        aedtxt.setText(String.format("%f",AEDTOAED));
        sartxt.setText(String.format("%f",AEDTOSAR));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
