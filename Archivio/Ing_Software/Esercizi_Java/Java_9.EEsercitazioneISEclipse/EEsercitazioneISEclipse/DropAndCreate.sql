
DROP TABLE Customers;
DROP TABLE Parcels;
DROP TABLE Shipments;

CREATE TABLE Customers (
        Id LONG NOT NULL AUTO_INCREMENT PRIMARY KEY,
        Address VARCHAR(255),
        EmailAddress VARCHAR(255),
        Company BOOLEAN,
        VatNumber VARCHAR(255),
        Name VARCHAR(255),
        Surname VARCHAR(255),
        DateOfBirth VARCHAR(64),
        TaxCode VARCHAR(255)
);

CREATE TABLE Parcels (
        Id LONG NOT NULL AUTO_INCREMENT PRIMARY KEY,
        Grams LONG,
        SizeClass VARCHAR(32),
        AwaitingDeliveryStorehouse LONG
);

CREATE TABLE Shipments (
        Id LONG NOT NULL AUTO_INCREMENT PRIMARY KEY,
        Identification VARCHAR(128),
        InvoiceId LONG,
        MethodOfPayment VARCHAR(32),
        MethodOfShipment VARCHAR(32),
        ParcelId LONG,
        Price REAL,
        ReceiverAddress VARCHAR(255),
        ReceiverId LONG,
        SenderAddress VARCHAR(255),
        SenderId LONG,
        Validated BOOLEAN,
        DateOfShipmentRequest VARCHAR(64)
);
