package com.example.vegan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class TypeActivity extends Fragment {
    TextView textView, textView3, textView4, frag1_tv;
    RadioButton radio_0,radio_1,radio_2,radio_3,radio_4,radio_5,radio_6;
    Button result_bt;
    String result, type;

    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.activity_type, container, false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        sharedPreferences = this.getActivity().getSharedPreferences("test", Context.MODE_PRIVATE);



        RadioGroup rg = (RadioGroup)v.findViewById(R.id.typegroup);
        final TextView textView4 = (TextView)v.findViewById(R.id.textView4);
        TextView textView = (TextView)v.findViewById(R.id.textView);
        radio_0 = (RadioButton)v.findViewById(R.id.radio1);
        radio_1 = (RadioButton)v.findViewById(R.id.radio2);
        radio_2 = (RadioButton)v.findViewById(R.id.radio3);
        radio_3 = (RadioButton)v.findViewById(R.id.radio4);
        radio_4 = (RadioButton)v.findViewById(R.id.radio5);
        radio_5 = (RadioButton)v.findViewById(R.id.radio6);
        radio_6 = (RadioButton)v.findViewById(R.id.radio7);



        result_bt = (Button)v.findViewById(R.id.result);


        ImageView type1 = (ImageView) v.findViewById(R.id.type1);
        ImageView type2 = (ImageView) v.findViewById(R.id.type2);
        ImageView type3 = (ImageView) v.findViewById(R.id.type3);
        ImageView type4 = (ImageView) v.findViewById(R.id.type4);
        ImageView type5 = (ImageView) v.findViewById(R.id.type5);
        ImageView type6 = (ImageView) v.findViewById(R.id.type6);
        ImageView type7 = (ImageView) v.findViewById(R.id.type7);


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio1){
                    result = "??????";
                }
                else if (checkedId == R.id.radio2){
                    result ="?????? ???????????????";
                }
                else if (checkedId == R.id.radio3){
                    result ="????????? ???????????????";
                }
                else if (checkedId == R.id.radio4){
                    result ="?????? ?????? ???????????????";
                }
                else if (checkedId == R.id.radio5){
                    result ="?????? ???????????????";
                }
                else if (checkedId == R.id.radio6) {
                    result ="?????? ???????????????";
                }
                else
                {
                    result ="??????";
                }
            }
        });

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("test", Context.MODE_PRIVATE);

        result_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID;
                String userType;


                userID = sharedPreferences.getString("email","aaa");

                if (result_bt != null){
                    textView4.setText("????????? ?????? ????????? \n\n"  + result + "?????????!!");
                    result_bt.setVisibility(View.GONE);
                    radio_0.setVisibility(View.GONE);
                    radio_1.setVisibility(View.GONE);
                    radio_2.setVisibility(View.GONE);
                    radio_3.setVisibility(View.GONE);
                    radio_4.setVisibility(View.GONE);
                    radio_5.setVisibility(View.GONE);
                    radio_6.setVisibility(View.GONE);

                    type1.setVisibility(View.GONE);
                    type2.setVisibility(View.GONE);
                    type3.setVisibility(View.GONE);
                    type4.setVisibility(View.GONE);
                    type5.setVisibility(View.GONE);
                    type6.setVisibility(View.GONE);
                    type7.setVisibility(View.GONE);

                    textView.setText("?????? ?????? ??????");

                } else {
                    textView4.setText("????????? ???????????????");

                }


                userType = result;

                SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences??? ????????? editor??? ??????
                editor.putString("type",userType);



                Response.Listener<String> responseListner= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) { //????????? ???????????? ??????.
                                //?????? ?????? ??????
                                Toast.makeText(getActivity().getApplicationContext(), "????????? ??????????????????.", Toast.LENGTH_SHORT).show();

                            } else {// ?????? ????????????
                                Toast.makeText(getActivity().getApplicationContext(), "????????? ??? ????????????.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                TypeRequest typeRequest = new TypeRequest(userID, userType, responseListner );
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                queue.add(typeRequest);
            }
        });




        type=result;


        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("type",type);
        editor.commit();



        return v;
    }



    private void setContentView(int activity_type) {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}