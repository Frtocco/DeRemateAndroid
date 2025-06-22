package com.example.deremate.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
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
    private static final String imagnLink = "imgLink";

    public static OpinionFragment newInstance(float puntaje, String comentario, String imgLink) {
        OpinionFragment fragment = new OpinionFragment();
        Bundle args = new Bundle();
        args.putFloat(comentPunt, puntaje);
        args.putString(coment, comentario);
        args.putString(imagnLink, imgLink);
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
        TextView txtImageLink = view.findViewById(R.id.imgLink);


        txtComentario.setText(comentario);
        ratingBar.setRating(score);
        String imgUrl = getArguments().getString(imagnLink);

        if (imgUrl != null && !imgUrl.trim().isEmpty()) {
            txtImageLink.setText("Ver imagen relacionada");
            txtImageLink.setTextColor(Color.CYAN);
            txtImageLink.setPaintFlags(txtImageLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            txtImageLink.setOnClickListener(v -> {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imgUrl));
                        startActivity(intent);
                    }
            );
        }else{
            txtImageLink.setText("No hay imagen adjunta");
            txtImageLink.setTextColor(Color.LTGRAY);
        }

        return new AlertDialog.Builder(requireContext())
                .setTitle("Opinión del envío")
                .setView(view)
                .setPositiveButton("Cerrar", null)
                .create();
    }
}
