package com.example.deremate.data.api.model;

import java.util.List;

public class OrderModelListResponse {
        private String status;
        private String address;
        private String orderId;
        private String riderId;
        private String coment;
        private String comentPunt;

        public String getStatus() {
            return status;
        }

        public String getAddress() {
            return address;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getRiderId() {
            return riderId;
        }

        public String getComent() {return coment;}
        public String getComentPunt() {return comentPunt;}
}
