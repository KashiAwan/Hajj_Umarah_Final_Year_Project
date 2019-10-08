package com.example.hajj_umarah_final_year_project;


import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class Setingsactiviy extends Fragment {


    public ViewFlipper viewFlipper;
    public Button qrcod, scancode, emrgencynum,addnumm;
    public  ImageView qrcoview;
    public  EditText editText;
    public  ListView listView;
    public  ArrayList<String> list;
    public  ArrayAdapter<String> arrayAdapter;





    public Setingsactiviy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootview = inflater.inflate(R.layout.fragment_setingsactiviy, container, false);

        viewFlipper = rootview.findViewById(R.id.viewwwflipper);
        qrcod = rootview.findViewById(R.id.adnewmember);
        scancode =rootview.findViewById(R.id.joingroup);
        emrgencynum =rootview.findViewById(R.id.adnumforemergency);
        qrcoview =  rootview.findViewById(R.id.qrcoview);
        addnumm =  rootview.findViewById(R.id.baddtext);
        editText =  rootview.findViewById(R.id.addtext);
        listView =  rootview.findViewById(R.id.listviewhhh);

        list = new ArrayList<String>();
        arrayAdapter= new ArrayAdapter<String>(getActivity(),
        android.R.layout.simple_list_item_1,list);

        //  buttons coding
        addnumm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namess = editText.getText().toString();
                list.add(namess);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        qrcod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = "Halllianahdgdyusdgghd";

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                BitMatrix bitMatrix = null;
                try {
                    bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 290,290);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                qrcoview.setImageBitmap(bitmap);


                viewFlipper.setDisplayedChild(0);
            }
        });

        scancode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setCameraId(0);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt("scanning");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.initiateScan();
            }
        });

        emrgencynum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                viewFlipper.setDisplayedChild(1);
            }
        });

        return rootview;
    }

}
