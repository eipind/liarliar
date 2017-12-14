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
    public SuffixEditText(Context context) {
        super(context);
    }

    public SuffixEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        extraSetUp(attrs);
    }

    public SuffixEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        extraSetUp(attrs);
    }

    @Override
    public boolean performClick() {
        if(getText() != null && getText().toString().length() != 0){
            setSelection(getText().toString().split(" ")[0].length());
        }
        return super.performClick();
    }

    private void extraSetUp(AttributeSet attrs) {

        TypedArray tArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.SuffixEditText, 0,0);
        final String singular, plural;

        try {
            singular = tArray.getString(R.styleable.SuffixEditText_singular);
            plural = tArray.getString(R.styleable.SuffixEditText_plural);
        } finally {
            tArray.recycle();
        }

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
                                s.append(" " + singular);
                                break;
                            default:
                                s.append(" " + plural);
                                break;
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

        // needed in order to next line work properly
        setTextIsSelectable(true);
        setTextIsSelectable(false);

        // restore soft keyboard functionality broken by previous line
        setFocusableInTouchMode(true);
        setFocusable(true);
        setClickable(true);
        setCursorVisible(true);
    }

}
