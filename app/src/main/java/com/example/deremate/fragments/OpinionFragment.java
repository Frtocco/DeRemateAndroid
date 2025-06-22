package com.example.deremate.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.deremate.R;

public class OpinionFragment extends DialogFragment {

    private static final String comentPunt = "puntaje";
    private static final String coment = "comentario";

    public static OpinionFragment newInstance(float puntaje, String comentario) {
        OpinionFragment fragment = new OpinionFragment();
        Bundle args = new Bundle();
        args.putFloat(comentPunt, puntaje);
        args.putString(coment, comentario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        float score = getArguments().getFloat(comentPunt);
        String comentario = getArguments().getString(coment);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_opinion, null);
        TextView txtComentario = view.findViewById(R.id.coment);
        RatingBar ratingBar = view.findViewById(R.id.comentPunt);

        txtComentario.setText(comentario);
        ratingBar.setRating(score);

        return new AlertDialog.Builder(requireContext())
                .setTitle("Opinión del envío")
                .setView(view)
                .setPositiveButton("Cerrar", null)
                .create();
    }
}
