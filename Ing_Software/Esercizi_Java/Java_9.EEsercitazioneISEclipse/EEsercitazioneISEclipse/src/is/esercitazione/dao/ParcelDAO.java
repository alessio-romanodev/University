/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.dao;

import static is.esercitazione.dao.ConversionUtils.readLong;
import static is.esercitazione.dao.ConversionUtils.setLong;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import is.esercitazione.entity.Parcel;

/**
 *
 * @author nonplay
 */
public class ParcelDAO {
    /*
        CREATE TABLE Parcels (
                Id LONG NOT NULL AUTO_INCREMENT PRIMARY KEY,
                Grams LONG,
                SizeClass VARCHAR(32),
                AwaitingDeliveryStorehouse LONG);
    */
     private static Parcel deserializeCurrentRecord(ResultSet rs) throws SQLException {
        Parcel parcel = new Parcel();
        parcel.setAwaitingDeliveryStorehouse(readLong(rs, "AwaitingDeliveryStorehouse"));
        parcel.setGrams(readLong(rs, "Grams"));
        parcel.setId(rs.getLong("Id"));
//        parcel.setRouteIdList(routeIdList);
        parcel.setSizeClass(rs.getString("SizeClass"));
        return parcel;
    }
    
    public static Parcel readParcel(TransactionManager transactionManager, long id) throws DAOException {
        transactionManager.assertInTransaction();
        
        Parcel foundObject = transactionManager.getFromPersistanceContext(Parcel.class, id);
        
        if (foundObject == null) {
            try (PreparedStatement preparedStatement = transactionManager.getConnection().prepareStatement("SELECT * FROM Parcels WHERE ID = ?")) {
                preparedStatement.setLong(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next() == true) {
                        foundObject = deserializeCurrentRecord(resultSet);
                        transactionManager.putInPersistanceContext(foundObject, id);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to read the Parcel id=" + id, e);
            }
        }
        
        return foundObject;
    }
    
    public static void createParcel(TransactionManager transactionManager, Parcel parcel) throws DAOException {
        transactionManager.assertInTransaction();
        
        try (PreparedStatement ps = transactionManager.getConnection().prepareStatement(
                "INSERT INTO Parcels (AwaitingDeliveryStorehouse, Grams, SizeClass) VALUES (?, ?, ?)")) {
            setLong(ps, 1, parcel.getAwaitingDeliveryStorehouse());
            setLong(ps, 2, parcel.getGrams());
            ps.setString(3, parcel.getSizeClass());
            
            ps.executeUpdate();
            try (ResultSet resultSet = ps.getGeneratedKeys()) {
                if (resultSet.next() == true) {
                    Long parcelId = readLong(resultSet, "SCOPE_IDENTITY()");
                    if (parcelId != null) {
                        parcel.setId(parcelId);
                        transactionManager.putInPersistanceContext(parcel, parcelId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to persist a new Parcel!", e);
        }
    }
}
