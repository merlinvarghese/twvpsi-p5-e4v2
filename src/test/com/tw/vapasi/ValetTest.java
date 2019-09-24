package com.tw.vapasi;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class ValetTest {

    @Test
    void expectValetToParkACar() throws UnableToParkException {
        List<ParkingLot> availableParkingLots = new ArrayList<>();
        List<ParkingLot> fullParkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(2);
        availableParkingLots.add(parkingLot);

        Valet valet = new Valet(availableParkingLots, fullParkingLots);
        Vehicle vehicle = getVehicle();

        Assertions.assertDoesNotThrow(() -> valet.park(vehicle));
    }

    @Test
    void expectValetToUnParkACar() throws UnableToUnparkException, UnableToParkException {
        List<ParkingLot> availableParkingLots = new ArrayList<>();
        List<ParkingLot> fullParkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(2);
        availableParkingLots.add(parkingLot);

        Valet valet = new Valet(availableParkingLots, fullParkingLots);
        Vehicle vehicle = getVehicle();
        valet.park(vehicle);

        Assertions.assertDoesNotThrow(() -> valet.unPark(vehicle));
    }

    @Test
    void expectValetToGetParkingFullNotification() throws UnableToParkException {
        List<ParkingLot> availableParkingLots = new ArrayList<>();
        List<ParkingLot> fullParkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        availableParkingLots.add(parkingLot);

        Valet valet = new Valet(availableParkingLots, fullParkingLots);
        parkingLot.registerForParkingNotification(valet);

        Vehicle vehicle = getVehicle();
        valet.park(vehicle);

        Assertions.assertTrue(availableParkingLots.size() == 0);
        Assertions.assertTrue(fullParkingLots.size() == 1);
    }

    @Test
    void expectValetToGetParkingAvailableNotification() throws UnableToParkException, UnableToUnparkException {
        List<ParkingLot> availableParkingLots = new ArrayList<>();
        List<ParkingLot> fullParkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        availableParkingLots.add(parkingLot);

        Valet valet = new Valet(availableParkingLots, fullParkingLots);
        parkingLot.registerForParkingNotification(valet);
        Vehicle vehicle = getVehicle();
        valet.park(vehicle);
        valet.unPark(vehicle);

//        Assertions.assertTrue(availableParkingLots.size() == 1);
        Assertions.assertTrue(fullParkingLots.size() == 0);
    }

    private Vehicle getVehicle() {
        return new Vehicle() {
        };
    }

}