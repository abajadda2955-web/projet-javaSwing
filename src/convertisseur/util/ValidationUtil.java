package convertisseur.util;

public class ValidationUtil {

    public static boolean estNombre(String text){

        try{
            Double.parseDouble(text);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}