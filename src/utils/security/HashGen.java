package utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGen {
    public static String gen(String password) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }catch (NullPointerException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
