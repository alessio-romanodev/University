/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.dao;

import static is.esercitazione.dao.ConversionUtils.deserializeDate;
import static is.esercitazione.dao.ConversionUtils.readLong;
import static is.esercitazione.dao.ConversionUtils.serializeDate;
import static is.esercitazione.dao.ConversionUtils.setLong;
import is.esercitazione.entity.MethodOfPayment;
import is.esercitazione.entity.MethodOfShipment;
import is.esercitazione.entity.Shipment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author nonplay
 */
public class ShipmentDAO {
    /*
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
    */
    
    private static Shipment deserializeCurrentRecord(ResultSet rs) throws SQLException {
        
        Shipment shipment = new Shipment();
//        shipment.setAppliedOffer(appliedOffer);
        shipment.setDateOfShipmentRequest(deserializeDate(rs.getString("DateOfShipmentRequest")));
        
        shipment.setId(readLong(rs, "Id"));
        shipment.setIdentification(rs.getString("Identification"));
        shipment.setInvoiceId(readLong(rs, "InvoiceId"));
        shipment.setMethodOfPayment(MethodOfPayment.valueOf(rs.getString("MethodOfPayment")));
        shipment.setMethodOfShipment(MethodOfShipment.valueOf(rs.getString("MethodOfShipment")));
        shipment.setParcelId(readLong(rs, "ParcelId"));
        shipment.setPrice(rs.getDouble("Price"));
        shipment.setReceiverAddress(rs.getString("ReceiverAddress"));
        shipment.setReceiverId(readLong(rs, "ReceiverId"));
        shipment.setSenderId(readLong(rs, "SenderId"));
        shipment.setSenderAddress(rs.getString("SenderAddress"));
        shipment.setValidated(rs.getBoolean("Validated"));
        
        return shipment;
    }
    
    public static Shipment readShipment(TransactionManager transactionManager, String shipment_UUID)  throws DAOException {
        transactionManager.assertInTransaction();
        
        try (PreparedStatement preparedStatement = transactionManager.getConnection().prepareStatement("SELECT * FROM Shipments WHERE Identification = ?")) {
            preparedStatement.setString(1, shipment_UUID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next() == true) {
                    Long id = resultSet.getLong("Id");
                    Shipment foundObject = transactionManager.getFromPersistanceContext(Shipment.class, id);
                    if (foundObject == null) {
                        foundObject = deserializeCurrentRecord(resultSet);
                        transactionManager.putInPersistanceContext(foundObject, id);
                    }
                    return foundObject;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to read the Shipment uuid=" + shipment_UUID, e);
        }
        return null;
    }
    
    public static Shipment readShipment(TransactionManager transactionManager, long id)  throws DAOException {
        transactionManager.assertInTransaction();
        
        Shipment foundObject = transactionManager.getFromPersistanceContext(Shipment.class, id);
        
        if (foundObject == null) {
            try (PreparedStatement preparedStatement = transactionManager.getConnection().prepareStatement("SELECT * FROM Shipments WHERE ID = ?")) {
                preparedStatement.setLong(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next() == true) {
                        foundObject = deserializeCurrentRecord(resultSet);
                        transactionManager.putInPersistanceContext(foundObject, id);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to read the Shipment id=" + id, e);
            }
        }
        
        return foundObject;
    }
    
    public static List<Shipment> readShipments(TransactionManager transactionManager)  throws DAOException {
        transactionManager.assertInTransaction();
        
        java.util.List<Shipment> customerList = new java.util.ArrayList<>();
        try (PreparedStatement preparedStatement = transactionManager.getConnection().prepareStatement("SELECT * FROM Shipments")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next() == true) {
                    
                    Shipment shipment = transactionManager.getFromPersistanceContext(Shipment.class, resultSet.getLong("Id"));
                    if (shipment != null) {
                        customerList.add(shipment);
                    } else {
                        shipment = deserializeCurrentRecord(resultSet);
                        customerList.add(shipment);
                        transactionManager.putInPersistanceContext(shipment, shipment.getId());
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to read all the Shipments", e);
        }
        
        return customerList;
    }
    
    public static void createShipment(TransactionManager transactionManager, Shipment shipment)  throws DAOException {
        transactionManager.assertInTransaction();
        
        try (PreparedStatement ps = transactionManager.getConnection().prepareStatement(
                "INSERT INTO Shipments (DateOfShipmentRequest, Identification, InvoiceId, MethodOfPayment, MethodOfShipment, ParcelId, Price, ReceiverAddress, ReceiverId, SenderAddress, SenderId, Validated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            
            ps.setString(1, serializeDate(shipment.getDateOfShipmentRequest()));
            ps.setString(2, shipment.getIdentification());
            setLong(ps, 3, shipment.getInvoiceId());
            ps.setString(4, shipment.getMethodOfPayment().toString());
            ps.setString(5, shipment.getMethodOfShipment().toString());
            setLong(ps, 6, shipment.getParcelId());
            ps.setDouble(7, shipment.getPrice());
            
            ps.setString(8, shipment.getReceiverAddress());
            setLong(ps, 9, shipment.getReceiverId());
            
            ps.setString(10, shipment.getSenderAddress());
            setLong(ps, 11, shipment.getSenderId());
            
            ps.setBoolean(12, shipment.isValidated());
            
            ps.executeUpdate();
            try (ResultSet resultSet = ps.getGeneratedKeys()) {
                if (resultSet.next() == true) {
                    Long shipmentId = readLong(resultSet, "SCOPE_IDENTITY()");
                    if (shipmentId != null) {
                        shipment.setId(shipmentId);
                        transactionManager.putInPersistanceContext(shipment, shipmentId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to create a new shipment!", e);
        }
    }
    
}
