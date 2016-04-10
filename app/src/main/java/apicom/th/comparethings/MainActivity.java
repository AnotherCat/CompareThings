package apicom.th.comparethings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    EditText cost1ET, cost2ET, amount1ET, amount2ET;
    TextView result1TV, result2TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAllView();
    }

    void initAllView() {
        cost1ET = (EditText) findViewById(R.id.cost1ET);
        cost2ET = (EditText) findViewById(R.id.cost2ET);
        amount1ET = (EditText) findViewById(R.id.amount1ET);
        amount2ET = (EditText) findViewById(R.id.amount2ET);
        result1TV = (TextView) findViewById(R.id.result1TV);
        result2TV = (TextView) findViewById(R.id.result2TV);

        cost1ET.addTextChangedListener(this);
        cost2ET.addTextChangedListener(this);
        amount1ET.addTextChangedListener(this);
        amount2ET.addTextChangedListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        float cost1, cost2;
        float amount1, amount2;
        try {
            cost1 = Float.parseFloat(cost1ET.getText().toString());
            if (cost1 <= 0) {
                result1TV.setText(getResources().getString(R.string.incorrectValue));
                result2TV.setText("");
                return;
            }
        } catch (NumberFormatException e) {
            return;
        }
        try {
            cost2 = Float.parseFloat(cost2ET.getText().toString());
            if (cost2 <= 0) {
                result1TV.setText(getResources().getString(R.string.incorrectValue));
                result2TV.setText("");
                return;
            }
        } catch (NumberFormatException e) {
            return;
        }
        try {
            amount1 = Float.parseFloat(amount1ET.getText().toString());
            if (amount1 <= 0) {
                result1TV.setText(getResources().getString(R.string.incorrectValue));
                result2TV.setText("");
                return;
            }
        } catch (NumberFormatException e) {
            return;
        }
        try {
            amount2 = Float.parseFloat(amount2ET.getText().toString());
            if (amount2 <= 0) {
                result1TV.setText(getResources().getString(R.string.incorrectValue));
                result2TV.setText("");
                return;
            }
        } catch (NumberFormatException e) {
            return;
        }

        float value1 = amount1 / cost1;
        float value2 = amount2 / cost2;

        DecimalFormat df = new DecimalFormat("#.##");

        String str;
        if (value1 > value2) {
            str = getResources().getString(R.string.item1Recommended);
        } else if (value1 < value2) {
            str = getResources().getString(R.string.item2Recommended);
        } else {
            if (cost1 > cost2) {
                str = getResources().getString(R.string.item2Recommended);
            } else if (cost1 < cost2) {
                str = getResources().getString(R.string.item1Recommended);
            } else {
                str = getResources().getString(R.string.bothRecommended);
            }
        }

        result1TV.setText(str);
        str = getResources().getString(R.string.ACost1for) + " [" + df.format(value1) + " " + getResources().getString(R.string.gram) + "]" +
                "\n" + getResources().getString(R.string.BCost1for) + " [" + df.format(value2) + " " + getResources().getString(R.string.gram) + "]";
        result2TV.setText(str);
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (cost1ET.getText().toString().isEmpty() || cost2ET.getText().toString().isEmpty() || amount1ET.getText().toString().isEmpty() || amount2ET.getText().toString().isEmpty()) {
            result1TV.setText(getResources().getString(R.string.incorrectValue));
            result2TV.setText("");
        }
    }
}
