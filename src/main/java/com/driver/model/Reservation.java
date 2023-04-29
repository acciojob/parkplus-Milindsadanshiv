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
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    private int numberOfHours;

     @ManyToOne
     @JoinColumn
     private Spot spot;

     @ManyToOne
     @JoinColumn
      private User user;

     @OneToOne(mappedBy = "reservation",cascade = CascadeType.ALL)
     Payment payment;
}
