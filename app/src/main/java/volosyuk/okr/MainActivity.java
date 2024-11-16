package volosyuk.okr;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextD1, editTextD2, editTextAlpha;
    Button buttonCalculate;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextD1 = findViewById(R.id.editText2);
        editTextD2 = findViewById(R.id.editText);
        editTextAlpha = findViewById(R.id.editText3);
        buttonCalculate = findViewById(R.id.button);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void calculateResult() {
        String d1Str = editTextD1.getText().toString();
        String d2Str = editTextD2.getText().toString();
        String alphaStr = editTextAlpha.getText().toString();

        if (TextUtils.isEmpty(d1Str) || TextUtils.isEmpty(d2Str) || TextUtils.isEmpty(alphaStr)) {
            showAlertDialog("Ошибка", "Пожалуйста, заполните все поля");
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double d1 = Double.parseDouble(d1Str);
            double d2 = Double.parseDouble(d2Str);
            double alpha = Double.parseDouble(alphaStr);

            if (d1 < 0 || d2 < 0 || alpha < 0 || alpha > 90) {
                showAlertDialog("Ошибка", "Значения не могут быть отрицательными и угол не должен превышать 90°");
                Toast.makeText(this, "Значения не могут быть отрицательными и угол не должен превышать 90°", Toast.LENGTH_SHORT).show();
                return;
            }

            // Пример формулы для расчета результата (замените на вашу формулу)
            double result = (d1 + d2) * Math.cos(Math.toRadians(alpha));
            textViewResult.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            showAlertDialog("Ошибка", "Недопустимые символы. Пожалуйста, вводите только числа.");
            Toast.makeText(this, "Недопустимые символы. Пожалуйста, вводите только числа.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}