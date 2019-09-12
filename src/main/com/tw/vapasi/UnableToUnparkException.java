package com.tw.vapasi;

class UnableToUnparkException extends Exception {
    UnableToUnparkException() {
        super("Unable to unpark a Car");
    }
}
