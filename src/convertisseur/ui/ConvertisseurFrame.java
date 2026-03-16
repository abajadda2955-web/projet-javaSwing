package convertisseur.ui;

import convertisseur.model.PoidsConverter;
import convertisseur.model.DistanceConverter;
import convertisseur.util.ValidationUtil;

import javax.swing.*;
import java.awt.*;

public class ConvertisseurFrame extends JFrame {

    private JTextField valeurField;
    private JComboBox<String> uniteDepart;
    private JComboBox<String> uniteArrivee;
    private JLabel resultat;

    private String categorie = "Poids";

    public ConvertisseurFrame() {

        setTitle("Convertisseur Poids & Distance");
        setSize(400,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        creerMenu();

        setLayout(new GridLayout(5,2,5,5));

        add(new JLabel("Valeur :"));

        valeurField = new JTextField();
        add(valeurField);

        add(new JLabel("De :"));

        uniteDepart = new JComboBox<>();
        add(uniteDepart);

        add(new JLabel("Vers :"));

        uniteArrivee = new JComboBox<>();
        add(uniteArrivee);

        JButton convertirBtn = new JButton("Convertir");
        JButton inverserBtn = new JButton("⇄");

        add(convertirBtn);
        add(inverserBtn);

        resultat = new JLabel("");
        add(resultat);

        mettreAJourUnites();

        convertirBtn.addActionListener(e -> convertir());

        inverserBtn.addActionListener(e -> inverser());

        setVisible(true);
    }

    private void creerMenu(){

        JMenuBar menuBar = new JMenuBar();

        JMenu menuCategorie = new JMenu("Catégories");

        JMenuItem poidsItem = new JMenuItem("Poids");
        JMenuItem distanceItem = new JMenuItem("Distance");

        poidsItem.addActionListener(e -> {
            categorie = "Poids";
            mettreAJourUnites();
        });

        distanceItem.addActionListener(e -> {
            categorie = "Distance";
            mettreAJourUnites();
        });

        menuCategorie.add(poidsItem);
        menuCategorie.add(distanceItem);

        menuBar.add(menuCategorie);

        setJMenuBar(menuBar);
    }

    private void mettreAJourUnites(){

        if(categorie.equals("Poids")){

            String[] poids = {"Gramme","Kilogramme","Livre"};

            uniteDepart.setModel(new DefaultComboBoxModel<>(poids));
            uniteArrivee.setModel(new DefaultComboBoxModel<>(poids));
        }

        else{

            String[] distance = {"Metre","Kilometre","Mile"};

            uniteDepart.setModel(new DefaultComboBoxModel<>(distance));
            uniteArrivee.setModel(new DefaultComboBoxModel<>(distance));
        }

    }

    private void convertir(){

        if(!ValidationUtil.estNombre(valeurField.getText())){

            JOptionPane.showMessageDialog(this,"Entrer un nombre valide");
            return;
        }

        double valeur = Double.parseDouble(valeurField.getText());

        String de = uniteDepart.getSelectedItem().toString();
        String vers = uniteArrivee.getSelectedItem().toString();

        double resultatConversion;

        if(categorie.equals("Poids")){

            resultatConversion = PoidsConverter.convertir(valeur,de,vers);
        }

        else{

            resultatConversion = DistanceConverter.convertir(valeur,de,vers);
        }

        resultat.setText(valeur + " " + de + " = " + resultatConversion + " " + vers);
    }

    private void inverser(){

        int i = uniteDepart.getSelectedIndex();
        int j = uniteArrivee.getSelectedIndex();

        uniteDepart.setSelectedIndex(j);
        uniteArrivee.setSelectedIndex(i);
    }
}