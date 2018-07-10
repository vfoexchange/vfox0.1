package com.vfoexchange.restServer.util;

import com.vfoexchange.restServer.dto.BillingDTO;
import com.vfoexchange.restServer.dto.EnquiringUserDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

/*
This class consists exclusively of static methods that operate on or return
  results
 */

public class AppUtil {

    /*
    Method for date
     */
    public static String getDate(String datePattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(DateTimeFormatter.ofPattern(datePattern));
        return date;
    }

    /*
Method for URL
 */
    public static String getURL(String token) {
        String url = "www.vfoexchange.com" + "/verify/" + token;
        // url = "http://"+hostname+":"+port+"/verify/"+token+" ";
        return url;
    }

    /*
Method for Encoding
 */
    public static String getEncodedString(String originalInput) {
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        return encodedString;
    }

    /*
Method for Decoding
*/
    public static String getDecodedString(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

    /*
 Method for mail body
  */
    public static String getMailBody(String url) {
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
                "    <td align=\"left\" valign=\"top\"><a href=\"" + url + "\" style=\"font-family:Arial, Helvetica, sans-serif; background-color:#0f70b8; color:#ffffff; text-align:center; padding-right:20px; padding-left:20px; padding-top:12px; padding-bottom:12px; text-decoration:none; font-size:16px; font-weight:bold; border-radius:5px;\">Confirm now</a></td>\n" +
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


    /*
    Method for contact us mail body
    */
    public static String getContactUsMailBody(EnquiringUserDTO enquiringUserDTO) {
        String body = "<table width=\"600\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family:Arial, Helvetica, sans-serif;\">\n" +
                "  <tr>\n" +
                "    <td colspan=\"2\" valign=\"top\" style=\"height:20px;\"></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td colspan=\"2\" valign=\"top\" style=\"font-family:Arial, Helvetica, sans-serif; font-size:20px; font-weight:bold; color:#292f33;\">Greetings!</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td colspan=\"2\" valign=\"top\" style=\"height:20px;\"></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td colspan=\"2\" valign=\"top\" style=\"font-family:Arial, Helvetica, sans-serif; font-size:15px; font-weight:bold; color:#292f33;\">We have received a contact request on <a style=\"color:#0f70b8;\" href=\"https://vfoexchange.com/\" target=\"_blank\">www.vfoexchange.com</a>, with below details: </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td colspan=\"2\" valign=\"top\" style=\"height:40px;\"></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td width=\"280\" height=\"30\" valign=\"top\" style=\"font-size:14px; font-weight:normal;\">First name</td>\n" +
                "    <td width=\"320\" height=\"30\" valign=\"top\" style=\"font-size:14px; font-weight:bold;\">" + enquiringUserDTO.getFirstName() + "</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td height=\"30\" valign=\"top\" style=\"font-size:14px; font-weight:normal;\">Last name</td>\n" +
                "    <td height=\"30\" valign=\"top\" style=\"font-size:14px; font-weight:bold;\">" + enquiringUserDTO.getLastName() + "</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td height=\"30\" valign=\"top\" style=\"font-size:14px; font-weight:normal;\">Phone</td>\n" +
                "    <td height=\"30\" valign=\"top\" style=\"font-size:14px; font-weight:bold;\">" + enquiringUserDTO.getPhone() + "</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td height=\"30\" valign=\"top\" style=\"font-size:14px; font-weight:normal;\">Email</td>\n" +
                "    <td height=\"30\" valign=\"top\" style=\"font-size:14px; font-weight:bold;\"><a style=\"color:#0f70b8;\" href=\"mailto:" + enquiringUserDTO.getEmail() + "\">" + enquiringUserDTO.getEmail() + "</a></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td height=\"30\" valign=\"top\" style=\"font-size:14px; font-weight:normal;\">Comments/Questions/Best time to call </td>\n" +
                "    <td height=\"30\" valign=\"top\" style=\"font-size:14px; font-weight:bold;\">" + enquiringUserDTO.getComments() + "</td>\n" +
                "  </tr>\n" +
                "    <tr>\n" +
                "    <td colspan=\"2\" valign=\"top\" style=\"height:40px;\"></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td colspan=\"2\" valign=\"top\" style=\"font-family:Arial, Helvetica, sans-serif; font-size:15px; font-weight:bold; color:#292f33;\">Thanks,<br />\n" +
                "      VfoExchange Team</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td colspan=\"2\" valign=\"top\" style=\"height:40px;\"></td>\n" +
                "  </tr>\n" +
                "</table>\n";
        return body;
    }

    /*
    Method for learn more mail body
    */
    public static String getLearnMoreMailBody(BillingDTO billingDTO) {
        String body = "<table width=\"600\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family:Arial, Helvetica, sans-serif;\">\n" +
                "  <tr>\n" +
                "    <td width=\"600\" valign=\"top\" style=\"height:20px;\"></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td valign=\"top\" style=\"font-family:Arial, Helvetica, sans-serif; font-size:20px; font-weight:bold; color:#292f33;\">Greetings!</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td valign=\"top\" style=\"height:20px;\"></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td valign=\"top\" style=\"font-family:Arial, Helvetica, sans-serif; font-size:15px; font-weight:bold; color:#292f33;\">User <strong style=\"color:#0f70b8;\">" + billingDTO.getUserName() + "</strong> wants to learn more about Service Provider <strong style=\"color:#0f70b8;\">" + billingDTO.getProviderName() + "</strong>.</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td valign=\"top\" style=\"height:40px;\"></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td valign=\"top\" style=\"font-family:Arial, Helvetica, sans-serif; font-size:15px; font-weight:bold; color:#292f33;\">Thanks,<br />\n" +
                "      VfoExchange Team</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td valign=\"top\" style=\"height:40px;\"></td>\n" +
                "  </tr>\n" +
                "</table>";
        return body;
    }

    /*
    Add method for generate Captcha
     */
    public static String generateCaptchaTextMethod2(int captchaLength) {
        String saltChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer captchaStrBuffer = new StringBuffer();
        java.util.Random rnd = new java.util.Random();
        while (captchaStrBuffer.length() < captchaLength) {
            int index = (int) (rnd.nextFloat() * saltChars.length());
            captchaStrBuffer.append(saltChars.substring(index, index + 1));
        }

        return captchaStrBuffer.toString();
    }
}
