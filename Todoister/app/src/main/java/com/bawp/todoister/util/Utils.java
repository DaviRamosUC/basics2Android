package com.bawp.todoister.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String formatDate(Date dueDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd/MM/yy", Locale.forLanguageTag("pt-BR"));
        return simpleDateFormat.format(dueDate);
    }
}
