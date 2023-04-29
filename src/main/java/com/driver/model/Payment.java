package com.driver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payment {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private int id;
       private boolean paymentCompleted;
       PaymentMode paymentMode;
       @OneToOne
       @JoinColumn
       Reservation reservation;
}
