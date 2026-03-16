package convertisseur.model;

public class DistanceConverter {

    public static double convertir(double valeur, String de, String vers){

        double metre;

        if(de.equals("Kilometre"))
            metre = valeur * 1000;

        else if(de.equals("Mile"))
            metre = valeur * 1609.34;

        else
            metre = valeur;

        if(vers.equals("Kilometre"))
            return metre / 1000;

        if(vers.equals("Mile"))
            return metre / 1609.34;

        return metre;
    }
}