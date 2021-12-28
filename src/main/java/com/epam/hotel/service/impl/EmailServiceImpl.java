package com.epam.hotel.service.impl;

import com.epam.hotel.dao.exception.DaoException;
import com.epam.hotel.dao.factory.DAOServiceFactory;
import com.epam.hotel.dao.factory.DAOType;
import com.epam.hotel.dao.impl.UserDAOImpl;
import com.epam.hotel.entity.User;
import com.epam.hotel.service.BaseService;
import com.epam.hotel.service.factory.ServiceFactory;
import com.epam.hotel.service.factory.ServiceType;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailServiceImpl extends BaseService {
    public static final String ADMIN = "hotelgrodnoinn@yandex.by";
    private static final String EMAIL_PROPERTIES = "email.properties";
    private final PropertiesFileServiceImpl propertiesFile =
            (PropertiesFileServiceImpl) ServiceFactory.getInstance().getServiceObjectsMap().get(ServiceType.PROPERTIES_FILE_SERVICE);
    private final UserDAOImpl userDAO =
            (UserDAOImpl) DAOServiceFactory.getInstance().getDaoObjectMap().get(DAOType.USER_DAO);
    private final String REQUEST_TABLE = "" +
            "|Request ID     - %d \n" +
            "|Client Name    - %s %s\n" +
            "|Persons        - %d\n" +
            "|Room Class     - %s\n" +
            "|Check In Date  - %s\n" +
            "|Check Out Date - %s\n\n";
    private Properties properties;

    public EmailServiceImpl() {
        properties = propertiesFile.getProperties(EMAIL_PROPERTIES);
    }

    public void sendSimpleMail(String recipient, String emailSubject, String emailBody) {
        properties = propertiesFile.getProperties(EMAIL_PROPERTIES);
        Session session = Session.getInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                properties.getProperty("mail.smtp.user"),
                                properties.getProperty("mail.smtp.password"));
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("mail.smtp.user")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(emailSubject);
            message.setText(emailBody);
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            throw new SecurityException(e);
        }
    }

    public void sendEmailToAdmin(User user, int requestID, int persons, String roomClass, String dateFrom, String dateTo) {
        String emailBody = prepareEmailBodyForAdmin(user, requestID, persons, roomClass, dateFrom, dateTo);
        String emailSubject = prepareEmailSubjectForAdmin(requestID);
        sendSimpleMail(ADMIN, emailSubject, emailBody);
    }

    public void sendEmailToClient(long clientID, int requestID, int orderAssigned) throws DaoException {
        User user = executor.createConnection().executeAsSingleQuery(() -> userDAO.get(clientID));
        String emailBody = prepareEmailBodyForClient(user, requestID, orderAssigned);
        String emailSubject = prepareEmailSubjectForClient(requestID);
        sendSimpleMail(user.getEmail(), emailSubject, emailBody);
    }

    private String prepareEmailSubjectForClient(int requestID) {
        properties = propertiesFile.getProperties("local/local_en_US.properties");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(properties.getProperty("email.subject.send.invoice.row1.1"))
                .append(requestID)
                .append(properties.getProperty("email.subject.send.invoice.row1.2"));
        return stringBuilder.toString();
    }

    private String prepareEmailBodyForClient(User user, int requestID, int orderAssigned) {
        properties = propertiesFile.getProperties("local/local_en_US.properties");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(properties.getProperty("email.body.send.invoice.row1"))
                .append(user.getFirstName()).append(" ")
                .append(user.getLastName()).append(",\n\n");
        stringBuilder
                .append(properties.getProperty("email.body.send.invoice.row2.1"))
                .append(requestID)
                .append(properties.getProperty("email.body.send.invoice.row2.2"))
                .append(orderAssigned)
                .append(properties.getProperty("email.body.send.invoice.row2.3"))
                .append(properties.getProperty("email.body.send.invoice.row3"))
                .append(properties.getProperty("email.body.new.request.row2"));
        return stringBuilder.toString();
    }

    private String prepareEmailBodyForAdmin(User user, int requestID, int persons, String roomClass, String dateFrom, String dateTo) {
        properties = propertiesFile.getProperties("local/local_en_US.properties");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(properties.getProperty("email.body.new.request.row1"));
        stringBuilder.append(String.format(REQUEST_TABLE, requestID, user.getFirstName(), user.getLastName(), persons, roomClass, dateFrom, dateTo));
        stringBuilder.append(properties.getProperty("email.body.new.request.row2"));
        return stringBuilder.toString();
    }

    private String prepareEmailSubjectForAdmin(int requestID) {
        return propertiesFile.getProperties("local/local_en_US.properties").getProperty("email.subject.new.request") + requestID;
    }


}
