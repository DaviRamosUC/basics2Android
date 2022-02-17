package com.devdavi.aprendaingles.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.devdavi.aprendaingles.R;

public class BichosFragment extends Fragment implements View.OnClickListener {

    private ImageButton buttonCao, buttonGato, buttonLeao, buttonMacaco, buttonOvelha,
            buttonVaca;

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bichos, container, false);

        buttonCao = view.findViewById(R.id.imageViewCao);
        buttonGato = view.findViewById(R.id.imageViewGato);
        buttonLeao = view.findViewById(R.id.imageViewLeao);
        buttonMacaco = view.findViewById(R.id.imageViewMacaco);
        buttonOvelha = view.findViewById(R.id.imageViewOvelha);
        buttonVaca = view.findViewById(R.id.imageViewVaca);

        buttonCao.setOnClickListener(this);
        buttonGato.setOnClickListener(this);
        buttonLeao.setOnClickListener(this);
        buttonMacaco.setOnClickListener(this);
        buttonOvelha.setOnClickListener(this);
        buttonVaca.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewCao:
                tocarSom(R.raw.dog);
                break;
            case R.id.imageViewGato:
                tocarSom(R.raw.cat);
                break;
            case R.id.imageViewLeao:
                tocarSom(R.raw.lion);
                break;
            case R.id.imageViewMacaco:
                tocarSom(R.raw.monkey);
                break;
            case R.id.imageViewOvelha:
                tocarSom(R.raw.sheep);
                break;
            case R.id.imageViewVaca:
                tocarSom(R.raw.cow);
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