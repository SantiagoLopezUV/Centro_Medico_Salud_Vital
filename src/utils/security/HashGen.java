package utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HashGen {

    public static boolean checkForInvalidChars(String str) {
        Pattern pattern = Pattern.compile("[^a-zA-z0-1 ]+");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

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
