package com.tw.vapasi;


//Understands Owner of Parking Lot
interface ParkingLotListener {
    void notifyParkingIsFull(ParkingLot parkingLot);

    void notifyParkingIsAvailable(ParkingLot parkingLot);
}
