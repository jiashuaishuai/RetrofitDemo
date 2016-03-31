package com.example.jiashuaishuai.myapplicationretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et;
    TextView tv;
    Button btn;
    private static final String BASE_URL = "http://apis.baidu.com";
    private static final String API_KEY = "8e13586b86e4b7f3758ba3bd6c9c9135";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et);
        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        ButterKnife.bind(this);
    }


    private void query() {
        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .baseUrl(BASE_URL)//主机地址
                .build();

        //2.创建访问API的请求
        PhoneService service = retrofit.create(PhoneService.class);
        Call<PhoneResult> call = service.getPhoneResultCall(API_KEY, et.getText().toString());

        //3.发送请求
        call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                //4.处理结果
                if (response.isSuccess()) {
                    PhoneResult result = response.body();
                    if (result != null) {
                        PhoneResult.RetDataEntity entity = result.getRetData();
                        tv.setText(entity.getCity());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {

            }
        });
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn:
                query();
                break;
        }
    }
}
