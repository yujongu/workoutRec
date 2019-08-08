package com.yujongu.workoutrec;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yujongu.workoutrec.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);

        initInstance();
        eventListener();


    }

    private void initInstance(){

    }

    private void eventListener(){
        binding.tvStartTime.setOnClickListener(listener);
        binding.tvEndTime.setOnClickListener(listener);
        binding.btnConfirmTime.setOnClickListener(listener);
    }

    private int focus = -1;
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.tvStartTime:
                    binding.timePickerFrame.setVisibility(View.VISIBLE);
                    focus = 0;
                    break;
                case R.id.tvEndTime:
                    focus = 1;
                    binding.timePickerFrame.setVisibility(View.VISIBLE);
                    break;
                case R.id.btnConfirmTime:
                    String hour = Integer.toString(binding.timePicker.getHour());
                    String minute = "";
                    if (binding.timePicker.getMinute() < 10){
                        System.out.println("Check");
                        minute = "0" + binding.timePicker.getMinute();
                        System.out.println(minute);
                    } else {
                        minute = Integer.toString(binding.timePicker.getMinute());
                    }

                    if (focus == 0){
                        binding.tvStartTime.setText(hour + ":" + minute);
                        //todo add exception for starttime > endtime
                    } else if (focus == 1){
                        binding.tvEndTime.setText(hour + ":" + minute);
                    }
                    focus = -1;
                    binding.timePickerFrame.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    };
}
