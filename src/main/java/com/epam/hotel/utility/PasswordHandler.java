package com.epam.hotel.utility;

import com.epam.hotel.service.exception.ServiceException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Provides the functionality of encrypting/decrypting of the user password.
 */
public class PasswordHandler {
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static final String UNICODE_FORMAT = "UTF8";
    private Cipher cipher;
    private SecretKey key;

    public PasswordHandler() {

    }

    public PasswordHandler setEncryptionKey(int hash) throws ServiceException {
        try {
            String myEncryptionKey = generateKey(hash);
            String myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
            byte[] arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(myEncryptionScheme);
            cipher = Cipher.getInstance(myEncryptionScheme);
            key = skf.generateSecret(ks);
        } catch (InvalidKeySpecException | InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new ServiceException(e);
        }
        return this;
    }

    private String generateKey(int hash) {
        StringBuilder key = new StringBuilder();
        return key.append(hash).append(hash).append(hash).toString();
    }

    /**
     * Encrypts a password.
     *
     * @param unencryptedString an unencrypted password.
     * @return an encrypted password.
     */
    public String encryptPassword(String unencryptedString) throws ServiceException {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return encryptedString;
    }

    /**
     * Decrypts a password.
     *
     * @param encryptedString an encrypted password.
     * @return a decrypted password.
     */
    public String decryptPassword(String encryptedString) throws ServiceException {
        String decryptedText = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return decryptedText;
    }
}
