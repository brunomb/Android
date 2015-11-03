package example.com.zk.customlistfragment.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import example.com.zk.customlistfragment.R;
import example.com.zk.customlistfragment.adapter.PersonItemAdapter;
import example.com.zk.customlistfragment.dialog.PersonSelectDialogFragment;

public class MainActivity extends AppCompatActivity {

    private PersonSelectDialogFragment mPersonSelectDialog;
    private PersonItemAdapter personAdapter;
    private static String mName;
    private static String mAge;
    private static String mCity;
    private Receiver mReceiver;

    private TextView personName;
    private TextView personAge;
    private TextView personCity;

    String[] personA = new String[3];
    String[] personB = new String[3];
    String[] personC = new String[3];
    String[][] persons = new String[3][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personName = (TextView) findViewById(R.id.name_value);
        personAge = (TextView) findViewById(R.id.age_value);
        personCity = (TextView) findViewById(R.id.city_value);

        Button btSelectDialog = (Button) findViewById(R.id.bt_select_person);

        mReceiver = new Receiver();
        IntentFilter filter = new IntentFilter("example.com.zk.SELECT_PERSON");
        this.registerReceiver(mReceiver, filter);

        populatePerson();
        personAdapter = new PersonItemAdapter(this,R.layout.item_person_radio,persons);

        btSelectDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectPersonDialog();
            }
        });
    }

    private void populatePerson() {
        personA[0] = "Ted";
        personA[1] = "35";
        personA[2] = "New York";

        personB[0] = "Marshal";
        personB[1] = "25";
        personB[2] = "New York";

        personC[0] = "Lily";
        personC[1] = "20";
        personC[2] = "New York";

        persons[0] = personA;
        persons[1] = personB;
        persons[2] = personC;
    }

    private void showSelectPersonDialog() {
        mPersonSelectDialog = PersonSelectDialogFragment.newInstance(personAdapter);
        mPersonSelectDialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat_Light_Dialog_Alert);
        mPersonSelectDialog.show(getFragmentManager(), "select_person");
    }

    public void updateScreen() {
        personName.setText(mName);
        personAge.setText(mAge);
        personCity.setText(mCity);
    }

    public void clearDialog(){
        mPersonSelectDialog.dismiss();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("PersonName", mName);
        savedInstanceState.putString("PersonAge", mAge);
        savedInstanceState.putString("PersonCity", mCity);
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mName = savedInstanceState.getString("PersonName");
        mAge = savedInstanceState.getString("PersonAge");
        mCity = savedInstanceState.getString("PersonCity");
        updateScreen();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            mName = arg1.getStringExtra("SELECT_PERSON_NAME");
            mAge = arg1.getStringExtra("SELECT_PERSON_AGE");
            mCity = arg1.getStringExtra("SELECT_PERSON_CITY");
            updateScreen();
        }
    }
}
