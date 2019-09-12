package com.tw.vapasi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Valet implements ParkingLotListener {

    private List<ParkingLot> availableParkingLots;
    private List<ParkingLot> fullParkingLots;
    Map<ParkingLot,List<Vehicle>> parkeVehiclesMap = new HashMap<>();

    Valet(List<ParkingLot> parkingLots){
        this.availableParkingLots = parkingLots;
    }

    boolean park(Vehicle vehicle) throws UnableToParkException {
        for (ParkingLot parkingLot : availableParkingLots) {
            if (parkingLot.isSlotAvailable()) {
                parkingLot.park(vehicle);
                return true;
            }
        }
        return false;
    }


    @Override
    public void notifyParkingIsFull(ParkingLot parkingLot) {
        availableParkingLots.remove(parkingLot);
        fullParkingLots.add(parkingLot);
    }

    @Override
    public void notifyParkingIsAvailable(ParkingLot parkingLot) {
        fullParkingLots.remove(parkingLot);
        availableParkingLots.add(parkingLot);
    }
}
