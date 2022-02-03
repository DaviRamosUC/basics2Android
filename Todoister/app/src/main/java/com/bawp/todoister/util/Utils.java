package com.bawp.todoister.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String formatDate(Date dueDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd/MM/yy", Locale.forLanguageTag("pt-BR"));
        return simpleDateFormat.format(dueDate);
    }

    public static void hideSoftKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
