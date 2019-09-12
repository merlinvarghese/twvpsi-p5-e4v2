package com.tw.vapasi;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


class ValetTest {

    @Test
    void expectValetToGetParkingAvailableNotification() throws UnableToParkException {
        ParkingLotListener owner = mock(ParkingLotListener.class);
        ParkingLot parkingLot = new ParkingLot(1, owner);
        List<ParkingLot> parkingLotsAvailable = new ArrayList<>();
        parkingLotsAvailable.add(parkingLot);
        Valet valet = mock(Valet.class);
        parkingLot.registerForParkingNotification(valet);

        Vehicle vehicle1 = getVehicle();
        parkingLot.park(vehicle1);

        verify(valet).notifyParkingIsFull(parkingLot);

    }
    private Vehicle getVehicle() {
        return new Vehicle() {
        };
    }

}