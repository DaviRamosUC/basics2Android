package com.devdavi.aprendaingles.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.devdavi.aprendaingles.R;

public class NumerosFragment extends Fragment implements View.OnClickListener {

    private ImageButton buttonUm, buttonDois, buttonTres, buttonQuatro, buttonCinco,
            buttonSeis;

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_numeros, container, false);

        buttonUm = view.findViewById(R.id.imageButton1);
        buttonDois = view.findViewById(R.id.imageButton2);
        buttonTres = view.findViewById(R.id.imageButton3);
        buttonQuatro = view.findViewById(R.id.imageButton4);
        buttonCinco = view.findViewById(R.id.imageButton5);
        buttonSeis = view.findViewById(R.id.imageButton6);

        buttonUm.setOnClickListener(this);
        buttonDois.setOnClickListener(this);
        buttonTres.setOnClickListener(this);
        buttonQuatro.setOnClickListener(this);
        buttonCinco.setOnClickListener(this);
        buttonSeis.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton1:
                tocarSom(R.raw.one);
                break;
            case R.id.imageButton2:
                tocarSom(R.raw.two);
                break;
            case R.id.imageButton3:
                tocarSom(R.raw.three);
                break;
            case R.id.imageButton4:
                tocarSom(R.raw.four);
                break;
            case R.id.imageButton5:
                tocarSom(R.raw.five);
                break;
            case R.id.imageButton6:
                tocarSom(R.raw.six);
                break;
        }
    }

    public void tocarSom(int rawId) {
        mediaPlayer = MediaPlayer.create(getActivity(), rawId);
        if (mediaPlayer != null) {
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}