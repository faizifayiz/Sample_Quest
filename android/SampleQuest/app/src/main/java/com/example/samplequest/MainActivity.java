package com.example.samplequest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button visitorBT,constructorBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visitorBT=findViewById(R.id.btnVisitor);
        constructorBT=findViewById(R.id.btnContractor);


        visitorBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,VisitorsActivity.class);
                startActivity(intent);
            }
        });

        constructorBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContractorActivity.class);
                startActivity(intent);
            }
        });


    }
}
