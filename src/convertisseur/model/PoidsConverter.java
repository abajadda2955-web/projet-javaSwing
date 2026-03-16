package convertisseur.model;

public class PoidsConverter {

    public static double convertir(double valeur, String de, String vers){

        double gramme;

        if(de.equals("Kilogramme"))
            gramme = valeur * 1000;

        else if(de.equals("Livre"))
            gramme = valeur * 453.59;

        else
            gramme = valeur;

        if(vers.equals("Kilogramme"))
            return gramme / 1000;

        if(vers.equals("Livre"))
            return gramme / 453.59;

        return gramme;
    }
}