package example.com.zk.customlistfragment.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CustomRadioButton extends RadioButton {

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    // Implement necessary constructors

    @Override
    public void toggle() {
        if(isChecked()) {
            if(getParent() instanceof RadioGroup) {
                ((RadioGroup)getParent()).clearCheck();
            }
        } else {
            setChecked(true);
        }
    }
}
