package nl.tudelft.oopp.demo.entities;

import java.io.Serializable;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class RandomIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
        throws HibernateException {

        Connection connection = session.connection();

        try {
            Statement statement = connection.createStatement();

            SecureRandom secureRandom = new SecureRandom();
            long id = 0L;
            while (true) {
                id = Math.abs(secureRandom.nextLong());

                ResultSet rs = statement.executeQuery("SELECT * FROM rooms WHERE id = " + id);

                if (!rs.next()) {
                    return id;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
