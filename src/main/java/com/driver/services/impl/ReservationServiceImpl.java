package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
         Reservation reservation=new Reservation();
        User user;
        ParkingLot parkingLot;
        Spot spot1=new Spot();
        int type;
        try{
         user=userRepository3.findById(userId).get();
         parkingLot=parkingLotRepository3.findById(parkingLotId).get();
            List<Spot> spotList=parkingLot.getSpotList();
            for(Spot spot:spotList)
            {
               if (spot.getSpotType().equals(SpotType.TWO_WHEELER))
               {
                   type=2;
               }
               else  if(spot.getSpotType().equals(SpotType.FOUR_WHEELER))
               {
                   type=4;
               }
               else {
                   type=5;
               }

               if (numberOfWheels==type && spot.isOccupied()==false)
               {
                   reservation.setSpot(spot);
                   reservation.setNumberOfHours(timeInHours);
                   reservation.setUser(user);
                   spot1.setId(spot.getId());
               }
            }
       }
       catch (Exception e)
       {
           throw new Exception("Cannot make reservation");
       }

        Spot spotFinal=spotRepository3.findById(spot1.getId()).get();
        List<Reservation>reservationList=spotFinal.getReservationList();
        reservationList.add(reservation);
        spotRepository3.save(spotFinal);
        reservationRepository3.save(reservation);

        List<Reservation> userReservationList=user.getReservationList();
        userReservationList.add(reservation);
        userRepository3.save(user);
        return reservation;
    }
}
