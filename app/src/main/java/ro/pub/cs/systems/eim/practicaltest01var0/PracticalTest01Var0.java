package ro.pub.cs.systems.eim.practicaltest01var0;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var0 extends AppCompatActivity {

    // definire campuri + butoane -> A2

    private IntentFilter intentFilter = new IntentFilter();
    Button navigate_to_secondary_activity_button;
    int serviceStatus;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // implementare -> B1
            if (view.getId() == R.id.navigate) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var0Secondary.class);
                //intent.putExtra(orice3, information);
                // orice3 - variabila de tip String care poate fi orice
                // information - variabila de orice tip
                startActivityForResult(intent, 1);
            }

//            if (conditie && serviceStatus == Constants.SERVICE_STOPPED) {
//                Intent intent = new Intent(getApplicationContext(), PracticalTest01Service.class);
//                intent.putExtra("firstNumber", nume_edit_text1.getText().toString());
//                intent.putExtra("secondNumber", nume_edit_text2.getText().toString());
//                getApplicationContext().startService(intent);
//                serviceStatus = Constants.SERVICE_STARTED;
//            }
        }
    }

    // pentru salvarea starii: se apeleaza inainte de stop - B2b
    // merge rulat fara super
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
//      savedInstanceState.putString(orice1, nume_edit_text1.getText().toString());
//      savedInstanceState.putString(orice2, nume_edit_text2.getText().toString());
    }

    // pentru restaurarea starii: se apeleaza dupa start - B2b
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        if (savedInstanceState.containsKey(orice1)) {
//            nume_edit_text1.setText(savedInstanceState.getString(orice1));
//        } else {
//            nume_edit_text1.setText(String.valueOf(0));
//        }
//        if (savedInstanceState.containsKey(orice2)) {
//            nume_edit_text2.setText(savedInstanceState.getString(orice2));
//        } else {
//            nume_edit_text2.setText(String.valueOf(0));
//        }
//    }


    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // cast campuri + butoane , adaugare listener pe butoane -> B1
        navigate_to_secondary_activity_button = (Button) findViewById(R.id.navigate);

        navigate_to_secondary_activity_button.setOnClickListener(buttonClickListener);

        // B2b
//        if (savedInstanceState != null) {
//            if (savedInstanceState.containsKey(orice1)) {
//                nume_edit_text1.setText(savedInstanceState.getString(orice1));
//            } else {
//                nume_edit_text1.setText(String.valueOf(0));
//            }
//            if (savedInstanceState.containsKey(orice2)) {
//                nume_edit_text2.setText(savedInstanceState.getString(orice2));
//            } else {
//                nume_edit_text2.setText(String.valueOf(0));
//            }
//        } else {
//            nume_edit_text1.setText(String.valueOf(0));
//            nume_edit_text2.setText(String.valueOf(0));
//        }


        intentFilter.addAction("ro.pub.cs.systems.eim.practicaltest01.arithmeticmean");
        intentFilter.addAction("ro.pub.cs.systems.eim.practicaltest01.geometricmean");


        serviceStatus = 0;
    }

    protected void onResume() {
        super.onResume();
        //registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Service.class);
        stopService(intent);
        super.onDestroy();
    }
}