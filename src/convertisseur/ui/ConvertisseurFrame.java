package convertisseur.ui;

import convertisseur.model.PoidsConverter;
import convertisseur.model.DistanceConverter;
import convertisseur.util.ValidationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConvertisseurFrame extends JFrame {

    private JTextField valeurField;
    private JComboBox<String> uniteDepart;
    private JComboBox<String> uniteArrivee;
    private JLabel resultat;

    private JRadioButton poidsRadio;
    private JRadioButton distanceRadio;

    public ConvertisseurFrame() {

        setTitle("Convertisseur Poids & Distance");
        setSize(400,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(6,2,5,5));

        poidsRadio = new JRadioButton("Poids", true);
        distanceRadio = new JRadioButton("Distance");

        ButtonGroup group = new ButtonGroup();
        group.add(poidsRadio);
        group.add(distanceRadio);

        add(poidsRadio);
        add(distanceRadio);

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

        poidsRadio.addActionListener(e -> mettreAJourUnites());
        distanceRadio.addActionListener(e -> mettreAJourUnites());

        convertirBtn.addActionListener(e -> convertir());

        inverserBtn.addActionListener(e -> inverser());

        setVisible(true);
    }

    private void mettreAJourUnites() {

        if (poidsRadio.isSelected()) {

            String[] poids = {"Gramme","Kilogramme","Livre"};

            uniteDepart.setModel(new DefaultComboBoxModel<>(poids));
            uniteArrivee.setModel(new DefaultComboBoxModel<>(poids));
        }

        else {

            String[] distance = {"Metre","Kilometre","Mile"};

            uniteDepart.setModel(new DefaultComboBoxModel<>(distance));
            uniteArrivee.setModel(new DefaultComboBoxModel<>(distance));
        }

    }

    private void convertir() {

        if(!ValidationUtil.estNombre(valeurField.getText())){

            JOptionPane.showMessageDialog(this,"Entrer un nombre valide");
            return;
        }

        double valeur = Double.parseDouble(valeurField.getText());

        String de = uniteDepart.getSelectedItem().toString();
        String vers = uniteArrivee.getSelectedItem().toString();

        double resultatConversion;

        if(poidsRadio.isSelected()){

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