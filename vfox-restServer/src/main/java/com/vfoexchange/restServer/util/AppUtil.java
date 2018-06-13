package com.vfoexchange.restServer.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class AppUtil {


    public static String getDate(String datePattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(DateTimeFormatter.ofPattern(datePattern));
        return date;
    }

    public static String getURL(String hostname,String port,String token){
        String url;
        url = "http://"+hostname+":"+port+"/verify/"+token+" ";
        return url;
    }

    public static String getEncodedString(String originalInput) {
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        return encodedString;
    }

    public static String getDecodedString(String encodedString){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }



    public static String getMailBody(String url){
        String body = "<table width=\"600\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "      <td>&nbsp;</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td valign=\"top\" style=\"font-family:Arial, Helvetica, sans-serif; font-size:20px; font-weight:bold; color:#292f33;\">Verify Email</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td valign=\"top\" style=\"height:20px;\"></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td valign=\"top\" style=\"font-family:Arial, Helvetica, sans-serif; font-size:15px; font-weight:bold; color:#292f33;\">Confirm your email address to complete your VFOX account. It's easy - just click the button below. </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td valign=\"top\" style=\"height:40px;\"></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td align=\"left\" valign=\"top\"><a href=\""+url+"\" style=\"font-family:Arial, Helvetica, sans-serif; background-color:#0f70b8; color:#ffffff; text-align:center; padding-right:20px; padding-left:20px; padding-top:12px; padding-bottom:12px; text-decoration:none; font-size:16px; font-weight:bold; border-radius:5px;\">Confirm now</a></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td valign=\"top\" style=\"height:40px;\"></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td valign=\"top\" style=\"font-family:Arial, Helvetica, sans-serif; font-size:15px; font-weight:bold; color:#292f33;\">Thanks,<br />\n" +
                "      VFOX</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td valign=\"top\" style=\"height:40px;\"></td>\n" +
                "  </tr>\n" +
                "</table>";
        return body;
    }
}
