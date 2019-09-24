package com.tw.vapasi;

import java.util.ArrayList;
import java.util.List;

public class Valet implements ParkingLotListener {

    private List<ParkingLot> availableParkingLots;
    private List<ParkingLot> fullParkingLots;

    Valet() {
    }

    Valet(List<ParkingLot> availableParkingLots, List<ParkingLot> fullParkingLots) {
        this.availableParkingLots = new ArrayList<>(10);
        this.availableParkingLots.addAll(availableParkingLots);
        this.fullParkingLots = new ArrayList<>(10);
        this.fullParkingLots.addAll(fullParkingLots);
    }

    void park(Vehicle vehicle) throws UnableToParkException {
        availableParkingLots.get(0).park(vehicle);
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

    void unPark(Vehicle vehicle) throws UnableToUnparkException {
        List<ParkingLot> totalParkingLots = availableParkingLots;
        totalParkingLots.addAll(fullParkingLots);
        ParkingLot vehicleParkedLot = availableParkingLots.stream()
                .filter(parkingLot -> parkingLot.isVehicleParked(vehicle)).findFirst().get();
        if (vehicleParkedLot == null) {
            throw new UnableToUnparkException();
        }
        vehicleParkedLot.unPark(vehicle);
    }

}
