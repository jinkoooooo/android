package com.example.googlemap2_source;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    public static int SelectedBtn = 0;


    public double DistanceMath(String latitude, String longitude){

        double x1 = MapsActivity.mMap.getCameraPosition().target.latitude;
        double y1 = MapsActivity.mMap.getCameraPosition().target.longitude;

        double x2 = Double.parseDouble(latitude);
        double y2 = Double.parseDouble(longitude);

        // 도 분 초
        int ld1 = (int) x1;
        int lb1 = (int) ((x1 - ld1) * 60);
        double lc1 = (((x1 - ld1)*60) - lb1) * 60;

        int lld1 = (int) y1;
        int llb1 = (int) ((y1 - lld1) * 60);
        double llc1 = (((y1 - lld1)*60) - llb1) * 60;

        int ld2 = (int) x2;
        int lb2 = (int) ((x2 - ld2) * 60);
        double lc2 = (((x2 - ld2)*60) - lb2) * 60;

        int lld2 = (int) y2;
        int llb2 = (int) ((y2 - lld2) * 60);
        double llc2 = (((y2 - lld2)*60) - llb2) * 60;

        //Log.d("!!!!!", "ld2, lb2, lc2 = "+ld2+" , "+lb2+" , "+lc2);

        int _ld = (int)Math.sqrt(Math.pow((ld1-ld2), 2));
        int _lb = (int)Math.sqrt(Math.pow((lb1-lb2), 2));
        int _lc = (int)Math.sqrt(Math.pow((lc1-lc2), 2));

        int _lld = (int)Math.sqrt(Math.pow((lld1-lld2), 2));
        int _llb = (int)Math.sqrt(Math.pow((llb1-llb2), 2));
        int _llc = (int)Math.sqrt(Math.pow((llc1-llc2), 2));

        double d1 = (_ld*88804 + _lb*1480 + _lc*25)/1000;
        double d2 = (_lld*88804 + _llb*1480 + _llc*25)/1000;

        return Math.sqrt(Math.pow(d1, 2) + Math.pow(d2, 2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);

        mainLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        for(int i=0 ;i<LoadingActivity.SearchList2.size();i++){

            DecimalFormat df = new DecimalFormat("#.###");

            String nowDist = df.format(DistanceMath(LoadingActivity.SearchList2.get(i).latitude,
                    LoadingActivity.SearchList2.get(i).longitude));

            LoadingActivity.SearchList2.get(i).setNowDistance(nowDist);

            /*Log.d("Distance"
                    , LoadingActivity.SearchList2.get(i).ccName +
                            " : " + LoadingActivity.SearchList2.get(i).nowDistance +" km");*/

             /*RadioButton radioButton = new RadioButton(this);

            radioButton.setText(LoadingActivity.SearchList.get(i).toString());

            radioButton.setId(i);


            LinearLayout.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);


            if( radioButton != null){
                mainLayout.addView(radioButton, layoutParams);
            }*/

            //라디오버튼식 구현 널익셉션 오류로 실패 포기.


            /*RadioGroup radioGroup = new RadioGroup(this);

            RadioButton radioButton = new RadioButton()*/

            Button button = new Button(this);

            //button.setText(LoadingActivity.SearchList.get(i).toString());
            button.setText(LoadingActivity.SearchList2.get(i).ccName
            +" - 약 "+LoadingActivity.SearchList2.get(i).nowDistance+" km");

            button.setLayoutParams(params);

            button.setTag(i);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent2 = new Intent(getApplicationContext(),ListClickActivity.class);
                    startActivity(intent2);

                    Object tag = v.getTag();
                    SelectedBtn = Integer.parseInt(tag.toString());
                    Log.d("test", "버튼" + tag + " : 클릭됨");
                }
            });

            mainLayout.addView(button);
        }

        setContentView(mainLayout);

    }

}
