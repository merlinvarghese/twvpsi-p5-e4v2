package com.tw.vapasi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Understands spaces available to halt a vehicle
class ParkingLot {
    private List<Vehicle> parkedVehicles;
    private Integer maxCapacity;
    private List<ParkingLotListener> parkingLotListeners;

    ParkingLot(int capacity) {
        this.parkedVehicles = new ArrayList<>();
        this.parkingLotListeners = new ArrayList<>();
        this.maxCapacity = capacity;
    }

    ParkingLot(int capacity, ParkingLotListener parkingLotListener) {
        this.parkedVehicles = new ArrayList<>();
        this.maxCapacity = capacity;
        this.parkingLotListeners = new ArrayList<>();
        parkingLotListeners.add(parkingLotListener);
    }

    void park(Vehicle vehicle) throws UnableToParkException {
        if (parkedVehicles.size() == maxCapacity) {
            throw new UnableToParkException();
        }
        parkedVehicles.add(vehicle);
        if (parkedVehicles.size() == maxCapacity) {
            notifyParkingIsFull();
        }
    }

    void unPark(Vehicle vehicle) throws UnableToUnparkException {
        if (!parkedVehicles.contains(vehicle)) {
            throw new UnableToUnparkException();
        }
        boolean isParkingFull = false;
        if (parkedVehicles.size() == maxCapacity) {
            isParkingFull = true;
        }
        parkedVehicles.remove(vehicle);
        if (parkedVehicles.size() == maxCapacity - 1 && isParkingFull) {
            notifyParkingIsAvailable();
        }
    }


    private void notifyParkingIsAvailable() {
        parkingLotListeners.stream()
                .forEach((parkingLotListener) -> parkingLotListener.notifyParkingIsAvailable(this));

    }

    private void notifyParkingIsFull() {
        parkingLotListeners.stream()
                .forEach((parkingLotListener) -> parkingLotListener.notifyParkingIsFull(this));
    }

    boolean isVehicleParked(Vehicle vehicle) {
        return parkedVehicles.contains(vehicle);
    }

    void registerForParkingNotification(Valet valet) {
        this.parkingLotListeners.add(valet);
    }
}
