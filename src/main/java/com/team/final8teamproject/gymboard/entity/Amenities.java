package com.team.final8teamproject.gymboard.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
public class Amenities implements Serializable{

    @Id
    @Column(name = "Amenities_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean locker = false;
    private boolean parkingLot = false;
    private boolean towel = false;
    private boolean showerRoom = false;
    private boolean wifi = false;
    private boolean event = false;
    @Builder
    private Amenities(boolean locker, boolean parkingLot,
                      boolean towel, boolean showerRoom,
                      boolean wifi, boolean event){
        updateLocker(locker);
        updateParkingLot(parkingLot);
        updateTowel(towel);
        updateShowerRoom(showerRoom);
        updateWifi(wifi);
        updateEvent(event);
    }
    private void updateLocker(boolean locker){this.locker = locker;}
    private void updateParkingLot(boolean parkingLot){this.parkingLot = parkingLot;}
    private void updateTowel(boolean towel){this.towel = towel;}
    private void updateShowerRoom(boolean showerRoom){this.showerRoom=showerRoom;}
    private void updateWifi(boolean wifi){this.wifi = wifi;}
    private void updateEvent(boolean event){this.event=event;}
}
