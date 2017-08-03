package com.rajesh.custom.edittext.view;


/**
 * Created by Mizpah_DEV on 12-Jul-17.
 */


import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ViewGroup;


public class CustomEditText extends android.support.v7.widget.AppCompatEditText {

    public final static int IS_PHONE = 1;
    public final static int IS_EMAIL = 2;
    public final static int HAS_TEXT = 3;
    public final static int IS_PHONE_WITH_COUNTRY_CODE = 4;
    public final static int HAS_TEXT_WITH_MIN_LIMIT = 5;
    CustomEditText editText;
    TextInputLayout textInputLayout;
    int VALIDATION_TYPE;
    int MIN_LENGTH,MAX_LENGTH;

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setErrorTextListener(Context context, CustomEditText editText, int VALIDATION_TYPE,ViewGroup parentView) {


        this.editText=editText;
        this.VALIDATION_TYPE=VALIDATION_TYPE;
        textInputLayout = new TextInputLayout(context);
        parentView.addView(textInputLayout);

        textInputLayout.addView(editText);
       // editText.setTag(textInputLayout);
        setCustomTextListner(editText, textInputLayout, VALIDATION_TYPE);

    }
    /*
    * if any edittext has a validation with min character
    * need to call this listener
    * */
    public void setErrorMinTextListener(Context context, CustomEditText editText, int MIN_LENGTH,ViewGroup parentView) {


        this.editText=editText;
        this.MIN_LENGTH=MIN_LENGTH;
        this.MAX_LENGTH=MAX_LENGTH;
        this.VALIDATION_TYPE=HAS_TEXT_WITH_MIN_LIMIT;
        textInputLayout = new TextInputLayout(context);
        parentView.addView(textInputLayout);
        textInputLayout.addView(editText);
       // editText.setTag(textInputLayout);
        setCustomTextListner(editText, textInputLayout, VALIDATION_TYPE);

    }

    public void setCustomTextListner(final CustomEditText editText, final TextInputLayout textInputLayout, final int VALIDATION_TYPE) {
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if(VALIDATION_TYPE==IS_PHONE){
                    Validation.isPhoneNumber(editText, textInputLayout, true);
                }
                else if(VALIDATION_TYPE==IS_EMAIL){
                    Validation.isEmailAddress(editText, textInputLayout, true);
                }else if(VALIDATION_TYPE==HAS_TEXT){
                    Validation.hasText(editText, textInputLayout, true);
                }else if(VALIDATION_TYPE==IS_PHONE_WITH_COUNTRY_CODE){
                    Validation.isPhoneWithCountryCode(editText, textInputLayout, true);
                }else if(VALIDATION_TYPE==HAS_TEXT_WITH_MIN_LIMIT){
                    Validation.isCharMin(editText, textInputLayout, true,MIN_LENGTH);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    public boolean isValid() {
        return Validation.hasText(editText, textInputLayout, true);
    }


}