package com.devdavi.minhasanotacoes.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AnotacaoPreferencias {

    public static final String ANOTACAO_PREFERENCIAS = "anotacao.preferencias";
    public static final String ANOTACAO = "anotacao";
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public AnotacaoPreferencias(Context c) {
        context = c;
        preferences = context.getSharedPreferences(ANOTACAO_PREFERENCIAS, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void salvarAnotacao(String anotacao){
        editor.putString(ANOTACAO, anotacao);
        editor.apply();
    }

    public String recuperarAnotacao(){
        return preferences.getString(ANOTACAO, "");
    }
}
