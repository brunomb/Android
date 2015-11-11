package example.com.zk.blueprint.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import example.com.zk.blureprint.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(this, BluePrintOne.class);

        Button btBluePrintExampleOne = (Button) findViewById(R.id.bt_example_one);

        btBluePrintExampleOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        final Intent intent2 = new Intent(this, BluePrintTwo.class);

        Button btBluePrintExampleTwo = (Button) findViewById(R.id.bt_example_two);

        btBluePrintExampleTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });

    }
}
