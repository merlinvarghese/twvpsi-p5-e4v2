package com.tw.vapasi;

import java.util.ArrayList;
import java.util.List;

//Understands spaces available to halt a vehicle
class ParkingLot {
    private List<Vehicle> parkedVehicles;
    private Integer maxCapacity;
    private ParkingLotListener owner;

    ParkingLot(int capacity) {
        this.parkedVehicles = new ArrayList<>();
        this.maxCapacity = capacity;
    }

    ParkingLot(int capacity, ParkingLotListener parkingLotOwner) {
        this.parkedVehicles = new ArrayList<>();
        this.maxCapacity = capacity;
        this.owner = parkingLotOwner;
    }

    void park(Vehicle vehicle) throws UnableToParkException {
        if (parkedVehicles.size() == maxCapacity) {
            throw new UnableToParkException();
        }
        parkedVehicles.add(vehicle);
        if (parkedVehicles.size() == maxCapacity) {
            notifyOwnerParkingIsFull();
        }
    }

    boolean isSlotAvailable() {
        return parkedVehicles.size() < maxCapacity;
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
            notifyOwnerParkingIsAvailable();
        }
    }

    private void notifyOwnerParkingIsAvailable() {
        if (this.owner != null) {
            this.owner.notifyParkingIsAvailable(this);
        }

    }

    private void notifyOwnerParkingIsFull() {
        if (this.owner != null) {
            this.owner.notifyParkingIsFull(this);
        }

    }

    boolean isVehicleParked(Vehicle vehicle) {
        return parkedVehicles.contains(vehicle);
    }

    public void registerForParkingNotification(Valet valet) {

    }
}
