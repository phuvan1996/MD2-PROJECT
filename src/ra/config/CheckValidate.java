package ra.config;

import ra.busines.entyti.Catalog;
import ra.busines.entyti.Product;
import ra.busines.imp.CatalogImp;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckValidate {
    public static boolean checkLenghString(String str, int min, int max) {
        if (str.length() > min && str.length() < max) {
            return true;
        }
        return false;
    }

    public static boolean checkInterger(String str) {
        try {
            int number = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean checkFloat(String str){
        try {
            Float number = Float.parseFloat(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    public static boolean checkTableNameLength(String str){
        if (str.trim().length()>=5){
            return true;
        }
        return false;
    }
    public static boolean checkCharAtTable(String str){
     String regex = "^[A][Z]\\d{3}$";
     Pattern pattern = Pattern.compile(regex);
     Matcher matcher = pattern.matcher(str);
     return matcher.matches();
    }
    public static boolean checkUserNameLength(String str){
        if (str.length()>=6){
            return true;
        }
        return false;
    }
    public static boolean checkUserName(String str){
        Pattern pattern = Pattern.compile(ShopConstant.USERNAME_REGEX);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    public  static boolean checkEmail(String str){
        Pattern pattern = Pattern.compile(ShopConstant.EMAIL_REGEX);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    public static boolean checkPhoneNumber(String str){
        Pattern pattern = Pattern.compile(ShopConstant.PHONE_REGEX);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    public static boolean checkProductLength(String str){
        if (str.length()==5){
            return true;
        }
        return false;
    }
}