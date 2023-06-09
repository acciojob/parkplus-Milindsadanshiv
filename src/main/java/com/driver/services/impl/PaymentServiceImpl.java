package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.model.Spot;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
         Payment payment=new Payment();
         Reservation reservation=reservationRepository2.findById(reservationId).get();
         Spot spot=reservation.getSpot();
         int bill=reservation.getNumberOfHours()*spot.getPricePerHour();


        payment.setReservation(reservation);
        String updatedMode = mode.toUpperCase();
        payment.setPaymentCompleted(false);
         if (amountSent<bill)
         {
             throw new Exception("Insufficient Amount");
         }
         if (mode.equals("CASH"))
         {
             payment.setPaymentMode(PaymentMode.CASH);
         }
         else if (mode.equals("CARD")) {
             payment.setPaymentMode(PaymentMode.CARD);
         }
         else if (mode.equals("UPI")) {
             payment.setPaymentMode(PaymentMode.UPI);
         }
         spot.setOccupied(false);
         payment.setPaymentCompleted(true);
         payment.setReservation(reservation);
         reservation.setPayment(payment);
         reservationRepository2.save(reservation);
         return payment;
    }
}
