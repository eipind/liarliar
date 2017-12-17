package com.edmond.liarliar;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;

import org.w3c.dom.Attr;

/**
 * Created by Ed on 14/12/2017.
 */

public class SuffixEditText extends android.support.v7.widget.AppCompatEditText {
    private String plural, single;
    public SuffixEditText(Context context) {
        super(context);
        extraSetUp();
    }

    public SuffixEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        extraSetUp();
    }

    public SuffixEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        extraSetUp();
    }

    @Override
    public boolean performClick() {
        if(getText() != null && getText().toString().length() != 0){
            setSelection(getText().toString().split(" ")[0].length());
        }
        return super.performClick();
    }

    private void extraSetUp() {
        single = getResources().getQuantityString(R.plurals.suffix_edit_text, 1);
        plural = getResources().getQuantityString(R.plurals.suffix_edit_text, 2);

        this.addTextChangedListener(new TextWatcher() {
            private Integer currentNumber = -1;
            private int lengthOfNumber;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{

                    if(currentNumber != Integer.parseInt(s.toString().split(" ")[0])){
                        currentNumber = Integer.parseInt(s.toString().split(" ")[0]);
                        lengthOfNumber = String.valueOf(currentNumber).length();

                        InputFilter[] filters = s.getFilters();
                        s.setFilters(new InputFilter[] {});

                        s.delete(lengthOfNumber, s.length());
                        switch(currentNumber){
                            case 1:
                                s.append(" " + single);
                                break;
                            default:
                                s.append(" " + plural);
                        }
                        setSelection(lengthOfNumber);
                        s.setFilters(filters);
                    }
                }catch(NumberFormatException e){
                    currentNumber = -1;
                    s.clear();
                }
            }
        });
        setTextIsSelectable(true);
        setTextIsSelectable(false);

        setFocusableInTouchMode(true);
        setFocusable(true);
        setClickable(true);
        setCursorVisible(true);
    }

}
