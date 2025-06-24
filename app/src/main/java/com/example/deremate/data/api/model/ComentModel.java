package com.example.deremate.data.api.model;

public class ComentModel {
            private String orderId;
            private String coment;
            private String comentPunt;
            private String imgLink;

    public Float getComentPunt() {
        try {
            return Float.parseFloat(comentPunt);
        } catch (NumberFormatException e) {
            return 0f;
        }
    }

    public String getComent() {
        return coment;
    }

    public String getImgLink() {
        return imgLink;
    }
}


