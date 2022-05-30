package com.example.razorpay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    Button button ;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Checkout.preload(getApplicationContext());

        button = findViewById(R.id.BTNPay);
        textView = findViewById(R.id.TXT_PAY);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentNow("1000");
            }
        });


    }

    private void PaymentNow(String amount) {
        final Activity activity = this;

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_mmpEuGoaauRGNy");
        checkout.setImage(R.drawable.ic_launcher_background);

        double final_amount = Float.parseFloat(amount) * 100;


        try {
            JSONObject options = new JSONObject();
            options.put("name" , "Kunal Bhatt");
            options.put("Description", "Refrence No. #123456");
            options.put("theme.color","#3399cc");
            options.put("currency","INR");
            options.put("amount", final_amount+"");
            options.put("profile.email","Kunal0Bhatt@gmail.com");
            options.put("profile.contact","7290917547");

            checkout.open(activity,options);

        }
         catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
        textView.setText(s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failure", Toast.LENGTH_SHORT).show();
        textView.setText(s);
    }
}