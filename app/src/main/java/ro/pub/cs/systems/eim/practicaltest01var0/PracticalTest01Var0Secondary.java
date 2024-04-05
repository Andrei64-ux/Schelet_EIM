package ro.pub.cs.systems.eim.practicaltest01var0;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var0Secondary extends AppCompatActivity {

    // definire butoane + editTexturi
    Button ok_button;
    Button cancel_button;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // implementare -> B1
            if (view.getId() == R.id.ok) {
                setResult(RESULT_OK, null);
                finish();
            } else if (view.getId() == R.id.cancel) {
                setResult(RESULT_CANCELED, null);
                finish();
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_secondary);

        ok_button = (Button)findViewById(R.id.ok);
        cancel_button = (Button)findViewById(R.id.cancel);

        ok_button.setOnClickListener(buttonClickListener);
        cancel_button.setOnClickListener(buttonClickListener);
    }
}
