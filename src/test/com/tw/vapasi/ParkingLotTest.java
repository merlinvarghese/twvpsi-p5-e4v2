package com.tw.vapasi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


class ParkingLotTest {

    @Test
    void expectToParkAVehicleSuccessfully() {
        ParkingLot parkingLot = new ParkingLot(10);
        Vehicle vehicle = getVehicle();
        try {
            parkingLot.park(vehicle);
        } catch (Exception e) {
            Assertions.fail("Fail path");
        }
    }

    @Test
    void expectExceptionWhenAnUnParkedCarIsTriedToUnPark() throws UnableToParkException {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle1 = getVehicle();
        Vehicle vehicle2 = getVehicle();
        parkingLot.park(vehicle1);
        Assertions.assertThrows(UnableToParkException.class, () -> parkingLot.park(vehicle2));
    }

    @Test
    void expectToUnParkAParkedVehicleSuccessfully() throws UnableToParkException {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle1 = getVehicle();
        parkingLot.park(vehicle1);
        try {
            parkingLot.unPark(vehicle1);
        } catch (UnableToUnparkException e) {
            Assertions.fail("Fail path");
        }
    }

    @Test
    void expectToUnParkAVehicleNotParked() {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle1 = getVehicle();

        Assertions.assertThrows(UnableToUnparkException.class, () -> parkingLot.unPark(vehicle1));
    }

    @Test
    void expectTrueIfTheVehicleIsParked() throws UnableToParkException {
        ParkingLot parkingLot = new ParkingLot(10);
        Vehicle vehicle = getVehicle();
        parkingLot.park(vehicle);

        Assertions.assertTrue(parkingLot.isVehicleParked(vehicle));
    }

    @Test
    void expectFalseIfTheVehicleIsNotParked() throws UnableToParkException {
        ParkingLot parkingLot = new ParkingLot(10);
        Vehicle vehicle = getVehicle();

        parkingLot.park(vehicle);

        Assertions.assertFalse(parkingLot.isVehicleParked(getVehicle()));
    }

    @Test
    void expectToNotifyOwnerWhenParkingLotIsFull() throws UnableToParkException {
        ParkingLotListener owner = mock(ParkingLotListener.class);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Vehicle vehicle1 = getVehicle();
        parkingLot.park(vehicle1);
        Vehicle vehicle2 = getVehicle();
        parkingLot.park(vehicle2);

        verify(owner).notifyParkingIsFull(parkingLot);
    }

    @Test
    void expectOwnerIsNotNotifiedWhenParkingIsAvailable() throws UnableToParkException {
        ParkingLotListener owner = mock(ParkingLotListener.class);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Vehicle vehicle1 = getVehicle();
        parkingLot.park(vehicle1);

        verify(owner, never()).notifyParkingIsFull(parkingLot);
    }

    @Test
    void expectOwnerIsNotifiedWhenParkingLotIsAvailableAgain() throws UnableToParkException, UnableToUnparkException {
        ParkingLotListener owner = mock(ParkingLotListener.class);
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Vehicle vehicle1 = getVehicle();
        parkingLot.park(vehicle1);
        parkingLot.unPark(vehicle1);

        verify(owner).notifyParkingIsAvailable(parkingLot);
    }

    private Vehicle getVehicle() {
        return new Vehicle() {
        };
    }
}
