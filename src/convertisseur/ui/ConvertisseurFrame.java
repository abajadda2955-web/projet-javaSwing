package convertisseur.ui;

import convertisseur.model.PoidsConverter;
import convertisseur.model.DistanceConverter;
import convertisseur.util.ValidationUtil;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class ConvertisseurFrame extends JFrame {

    private JTextField valeurField;
    private JComboBox<String> uniteDepart;
    private JComboBox<String> uniteArrivee;
    private JLabel resultat;

    private String categorie = "Poids";

    public ConvertisseurFrame() {

        setUIFont(new FontUIResource("Segoe UI", Font.PLAIN, 16));

        setTitle("Convertisseur Poids & Distance");
        setSize(450,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        creerMenu();

        setLayout(new BorderLayout());

        JLabel titre = new JLabel("Convertisseur Poids & Distance", JLabel.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 20));

        add(titre, BorderLayout.NORTH);

        JPanel panelCentre = new JPanel(new GridLayout(4,2,10,10));
        panelCentre.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panelCentre.add(new JLabel("Valeur :"));

        valeurField = new JTextField();
        panelCentre.add(valeurField);

        panelCentre.add(new JLabel("De :"));

        uniteDepart = new JComboBox<>();
        panelCentre.add(uniteDepart);

        panelCentre.add(new JLabel("Vers :"));

        uniteArrivee = new JComboBox<>();
        panelCentre.add(uniteArrivee);

        JButton convertirBtn = new JButton("Convertir");
        JButton inverserBtn = new JButton("⇄");

        convertirBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        convertirBtn.setBackground(new Color(70,130,180));
        convertirBtn.setForeground(Color.WHITE);

        inverserBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        panelCentre.add(convertirBtn);
        panelCentre.add(inverserBtn);

        add(panelCentre, BorderLayout.CENTER);

        resultat = new JLabel(" ", JLabel.CENTER);
        resultat.setFont(new Font("Segoe UI", Font.BOLD, 16));

        add(resultat, BorderLayout.SOUTH);

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

    private void setUIFont(FontUIResource f) {

        Enumeration<Object> keys = UIManager.getDefaults().keys();

        while (keys.hasMoreElements()) {

            Object key = keys.nextElement();
            Object value = UIManager.get(key);

            if (value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}