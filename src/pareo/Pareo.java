
package pareo;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;
import javax.swing.border.Border;
/**
 *
 * @author Juan Ballesteros
 */
public class Pareo {
    
    JFrame ventanaPareo = new JFrame();
    
    JLabel fondo1 = new JLabel();
    JLabel fondo2 = new JLabel();
    static JTextField[] field1 = new JTextField[10];
    static JTextField[] field2 = new JTextField[10]; 
    JTextField[] field3 = new JTextField[10];
    JLabel checkVerde[] = new JLabel[10];
    JLabel checkRojo[] = new JLabel[10];
    static JPanel panelFondo = new JPanel();
    static JPanel panelComponentes = new JPanel();
    JPanel botonPanel = new JPanel();
    JPanel botonPanelRevisar = new JPanel();
    JPanel botonPanelRevolver = new JPanel();
    JPanel botonPanelReiniciar = new JPanel();
    JLabel txtBotonReiniciar = new JLabel("Reiniciar");
    JLabel txtBotonRevisar = new JLabel("Revisar");
    JLabel txtBotonRevolver = new JLabel("Revolver");
    JLabel numeros[] = new JLabel[10];
    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("Opciones");
    static JMenu cargar = new JMenu("Cargar");
    static JMenu modificar = new JMenu("Modificar");
    JMenuItem guardar = new JMenuItem("Guardar");
    static JTextField fieldArchivo = new JTextField();
    static JTextArea[] field1Guardar = new JTextArea[10];
    static JTextArea[] field2Guardar = new JTextArea[10];
    JScrollPane[] scrollArea = new JScrollPane[10];
    JScrollPane[] scrollArea2 = new JScrollPane[10];
    static ArrayList<JMenuItem> ItemArchivoCargar = new ArrayList<JMenuItem>();
    static ArrayList<JMenuItem> ItemArchivoModificar = new ArrayList<JMenuItem>();
    BackEnd backend = new BackEnd();
    boolean CampoVacio1 = false; 
    boolean CampoVacio2 = false; 
    boolean CampoVacio3 = false;
    
    RoundLineBorder r = new RoundLineBorder(Color.lightGray.darker(),true);
    RoundLineBorder2 r2 = new RoundLineBorder2(Color.lightGray.darker(),true);
    RoundLineBorder3 r3 = new RoundLineBorder3(Color.lightGray.darker(),true);
    
    int ph(double d){
        double r = 0;
        Toolkit t = Toolkit.getDefaultToolkit();
        r = t.getScreenSize().height*(d/100);
        return (int)r;
    }
    
    int pw(double d){
        double r = 0;
        Toolkit t = Toolkit.getDefaultToolkit();
        r = t.getScreenSize().width*(d/100);
        return (int)r;
    }

    public void Pareo(){

        ventanaPareo.setLayout(null);
        ventanaPareo.setBounds(0,0,500,500);
        ventanaPareo.setLocationRelativeTo(null);
        ventanaPareo.setResizable(true);
        ventanaPareo.setExtendedState(MAXIMIZED_BOTH);
        ventanaPareo.getContentPane().setBackground(new Color(67, 79, 118));
        
        ventanaPareo.setJMenuBar(menubar);
        menubar.add(menu);
       
        menu.add(guardar);
        menu.add(cargar);
        menu.add(modificar);
        
        backend.CargarItems();
        backend.EventoCargarDatos();
        backend.EventoModificarDatos();
        
        panelFondo.setBounds(pw(0),ph(0),pw(100),ph(100));
        panelFondo.setOpaque(false);
        panelFondo.setLayout(null);
        
        panelComponentes.setLayout(null);
        panelComponentes.setBounds(pw(0),ph(0),pw(100),ph(100));
        panelComponentes.setOpaque(false);

        fondo1.setBounds(pw(6),ph(3.8),pw(24.76),ph(83));
        
        ImageIcon imagen = new ImageIcon(getClass().getResource("fondo1.png"));
        ImageIcon imagen2 = new ImageIcon(imagen.getImage().getScaledInstance(fondo1.getWidth(),fondo1.getHeight(), Image.SCALE_DEFAULT));
        fondo1.setIcon(imagen2);
        panelFondo.add(fondo1);
        
        fondo2.setBounds(pw(46.5),ph(3.8),pw(47),ph(83));
        
        ImageIcon imagen3 = new ImageIcon(imagen.getImage().getScaledInstance(fondo2.getWidth(),fondo2.getHeight(), Image.SCALE_DEFAULT));
        fondo2.setIcon(imagen3);
        panelFondo.add(fondo2);
        
        double ik = 8;
        for(int i = 0; i < 10; i++, ik += 7.8){
            field1[i] = new JTextField();
            field1[i].setBounds(pw(7.8),ph(ik),pw(21),ph(4.8));
            field1[i].setVisible(true);            
            field1[i].setBorder(r);
            field1[i].setBorder(BorderFactory.createCompoundBorder(field1[i].getBorder(), BorderFactory.createEmptyBorder(pw(0.7), ph(1.5), ph(2), pw(0.7) )));
            
            panelComponentes.add(field1[i]);
        }
        
        ik = 8;
        for(int i = 0; i < 10; i++, ik += 7.8){
            field2[i] = new JTextField();
            field2[i].setBounds(pw(53.5),ph(ik),pw(37.5),ph(4.8));
            field2[i].setVisible(true);
            
            field2[i].setBorder(r);
            field2[i].setBorder(BorderFactory.createCompoundBorder(field2[i].getBorder(), BorderFactory.createEmptyBorder(pw(0.7), ph(1.5), ph(2), pw(0.7))));
            
            panelComponentes.add(field2[i]);
        }
        
        ik = 8;
        for(int i = 0; i < 10; i++, ik += 7.8){
            field3[i] = new JTextField();
            field3[i].setBounds(pw(48.5),ph(ik),pw(4),ph(4.8));
            field3[i].setVisible(true);
            
            field3[i].setBorder(r);
            field3[i].setBorder(BorderFactory.createCompoundBorder(field1[i].getBorder(), BorderFactory.createEmptyBorder(pw(0.7), ph(1.5), ph(2), pw(0.7))));
            
            panelComponentes.add(field3[i]);
            
        }
        
        ik = 9.2;
        for(int i = 0; i < 10; i++, ik += 7.8){
            int num = i+1;
            String numString = Integer.toString(num);
            if(i == 9){
                numeros[i] = new JLabel(numString + " -");
                numeros[i].setBounds(pw(3.4),ph(ik),pw(2),ph(2));
                numeros[i].setForeground(Color.WHITE);
                numeros[i].setFont(new Font("Arial", Font.BOLD, 14));
                panelComponentes.add(numeros[i]);
            } else{
                numeros[i] = new JLabel(numString + " -");
                numeros[i].setBounds(pw(4),ph(ik),pw(2),ph(2));
                numeros[i].setForeground(Color.WHITE);
                numeros[i].setFont(new Font("Arial", Font.BOLD, 14));
                panelComponentes.add(numeros[i]);
            }

        }
        
        txtBotonRevolver.setFont(new Font("Arial", Font.BOLD, 16));
        txtBotonRevisar.setFont(new Font("Arial", Font.BOLD, 16));
        txtBotonReiniciar.setFont(new Font("Arial", Font.BOLD, 16));
        txtBotonReiniciar.setForeground(Color.white);
        txtBotonRevisar.setForeground(Color.white);
        txtBotonRevolver.setForeground(Color.white);
        txtBotonRevisar.setVisible(false);
        botonPanelRevisar.setVisible(false);
        botonPanelReiniciar.setVisible(false);
        txtBotonReiniciar.setVisible(false);
        
        botonPanelRevisar.setBounds(pw(33.35), ph(72), pw(10.5), ph(7.7));
        botonPanelRevisar.setOpaque(false);
        botonPanelRevisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                revisar();
                if(CampoVacio1 == true || CampoVacio2 == true || CampoVacio3 == true){
                } else{
                    botonPanelRevisar.setVisible(false);
                    txtBotonRevisar.setVisible(false);
                    botonPanelReiniciar.setVisible(true);
                    txtBotonReiniciar.setVisible(true);
                }
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                botonPanel.setBackground(new Color(219,107,144));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                botonPanel.setBackground(new Color(209,97,134));
            }
        });
        
        botonPanelReiniciar.setBounds(pw(33.35), ph(72), pw(10.5), ph(7.7));
        botonPanelReiniciar.setOpaque(false);
        botonPanelReiniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reiniciar();
                botonPanelRevolver.setVisible(true);
                txtBotonRevolver.setVisible(true);
                botonPanelReiniciar.setVisible(false);
                txtBotonReiniciar.setVisible(false);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                botonPanel.setBackground(new Color(219,107,144));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                botonPanel.setBackground(new Color(209,97,134));
            }
        });
        
        botonPanelRevolver.setBounds(pw(33.35), ph(72), pw(10.5), ph(7.7));
        botonPanelRevolver.setLayout(new GridBagLayout());
        botonPanelRevolver.setOpaque(false);
        botonPanelRevolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                revolver();
                botonPanelRevolver.setVisible(false);
                txtBotonRevolver.setVisible(false);
                botonPanelRevisar.setVisible(true);
                txtBotonRevisar.setVisible(true);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                botonPanel.setBackground(new Color(219,107,144));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                botonPanel.setBackground(new Color(209,97,134));
            }
        });

        ActionListener listener3 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {    
                VentanaGuardar();
            }
        };
        guardar.addActionListener(listener3);
        
        botonPanel.setBackground(new Color(209,97,134));
        botonPanel.setBounds(pw(33.35), ph(72), pw(10.5), ph(7.7));
        botonPanel.add(txtBotonReiniciar);
        botonPanel.add(txtBotonRevolver);
        botonPanel.add(txtBotonRevisar);
        botonPanel.setLayout(new GridBagLayout());
        
        ventanaPareo.add(botonPanelRevisar);
        ventanaPareo.add(botonPanelRevolver);
        ventanaPareo.add(botonPanelReiniciar);
        ventanaPareo.add(botonPanel);
        ventanaPareo.add(panelComponentes);
        ventanaPareo.add(panelFondo);
        
        ventanaPareo.setVisible(true);
        
        CrearChecks();
        
        panelComponentes.revalidate();
    }
    
    public void CrearChecks(){
        
        for(int i = 0; i < 10; i++){
            checkVerde[i] = new JLabel();
        }
        
        for(int i = 0; i < 10; i++){
            checkRojo[i] = new JLabel();
        }
        
        ImageIcon imagenVerde = new ImageIcon(getClass().getResource("PuntoVerde.png"));
        ImageIcon imagenRojo = new ImageIcon(getClass().getResource("PuntoRojo.png"));
         
        for(int i = 0; i < 10; i++){
            checkVerde[i].setSize(pw(0.8),ph(1.4));
            ImageIcon imagenVerde2 = new ImageIcon(imagenVerde.getImage().getScaledInstance(checkVerde[i].getWidth(),checkVerde[i].getHeight(), Image.SCALE_DEFAULT));
            checkVerde[i].setIcon(imagenVerde2);
            checkVerde[i].setVisible(false);
            panelComponentes.add(checkVerde[i]);
            checkVerde[i].repaint();
        }
        
        for(int i = 0, j = 0; i < 10; i++, j++){
            checkRojo[i].setSize(pw(0.8),ph(1.4));
            ImageIcon imagenRojo2 = new ImageIcon(imagenRojo.getImage().getScaledInstance(checkRojo[i].getWidth(),checkRojo[i].getHeight(), Image.SCALE_DEFAULT));
            checkRojo[i].setIcon(imagenRojo2);
            checkRojo[i].setVisible(false);
            panelComponentes.add(checkRojo[i]);
            checkRojo[i].repaint();
        }

    }
    
    public void revisar(){
         
        for(int i = 0; i < 10; i++){
            if(field1[i].getText().equals("")){
                CampoVacio1 = true;
            } else{
                CampoVacio1 = false;
            } 
            if(field2[i].getText().equals("")){
                CampoVacio2 = true;
            } else{
                CampoVacio2 = false;
            }
            if(field3[i].getText().equals("")){
                CampoVacio3 = true;
            } else{
                CampoVacio3 = false;
            }
        }
        
        if(CampoVacio1 == false && CampoVacio2 == false && CampoVacio3 == false){
            double ik = 8;
            for(int i = 0; i < 10; i++){
                ik = 8;
                for(int j = 1; j <= 10; j++, ik += 7.8){
                    if(Integer.parseInt(field3[i].getText()) == j){  
                        if(field1[i].getBounds().contains(pw(7.8),ph(ik),pw(21),ph(4.8))){
                            checkVerde[i].setLocation(field3[i].getX()-pw(1.45), field3[i].getY()+ph(1.6));
                            checkVerde[i].setVisible(true);
                        } else{
                            checkRojo[i].setLocation(field3[i].getX()-pw(1.45), field3[i].getY()+ph(1.6));
                            checkRojo[i].setVisible(true);
                        }
                    } else{  
                        checkRojo[i].setLocation(field3[i].getX()-pw(1.45), field3[i].getY()+ph(1.6));
                        checkRojo[i].setVisible(true);
                    }   
                }
            }
        }
        
        if(CampoVacio1 == true || CampoVacio2 == true || CampoVacio3 == true){
            JOptionPane.showMessageDialog(null, "Por favor llena todos los campos");
        }

    }
    
    public void revolver(){
        
        //Creación del primer arreglo llenado con números al azar
        int arr[] = new int[10];
        arr[0] = (int)(Math.random()*10);
        
        for(int k = 1; k < 10; k++){
            arr[k] = (int)(Math.random()*10);
            for(int j = 0; j < k; j++){
                if(arr[k] == arr[j]){
                    k--;
                }
            }
        }
        
        //Creación del segundo arreglo llenado con números al azar
        int arr2[] = new int[10];
        arr2[0] = (int)(Math.random()*10);
        
        for(int k = 1; k < 10; k++){
            arr2[k] = (int)(Math.random()*10);
            for(int j = 0; j < k; j++){
                if(arr2[k] == arr2[j]){
                    k--;
                }
            }
        }
        
        //Revolver el primer componente
        double ik = 8;
        for(int i = 0; i < 10; i++, ik += 7.8){
            for(int j = 0; j < 10; j++){
                if(arr[i] == j){
                    field1[j].setLocation(pw(7.8), ph(ik));
                }
            }
        }
        
        //Revolver los dos últimos componentes
        ik = 8;
        for(int i = 0; i < 10; i++, ik += 7.8){
            for(int j = 0; j < 10; j++){
                if(arr2[i] == j){
                    field2[j].setLocation(pw(53.5), ph(ik));
                    field3[j].setLocation(pw(48.5), ph(ik));
                }
            }     
        }     
    }
    
    public void reiniciar(){
        double ik = 8;
        for(int i = 0; i < 10; i++, ik += 7.8){
            field1[i].setLocation(pw(7.8), ph(ik));
            field2[i].setLocation(pw(53.5), ph(ik));
            field3[i].setLocation(pw(48.5), ph(ik));
        }

        for(int i = 0; i < 10; i++){
            checkVerde[i].setVisible(false);
            checkRojo[i].setVisible(false);
            field3[i].setText("");
        }
    }
    
    public void VentanaGuardar(){
        JFrame ventana = new JFrame();
        ventana.setBounds(0,0,pw(65.9),ph(91));
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        
        JPanel componentes = new JPanel();
        componentes.setLayout(null);
        componentes.setOpaque(true);
        componentes.setPreferredSize(new Dimension(pw(64),ph(86)));
        componentes.setBackground(new Color(70,70,70));
        
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(0,0,pw(65.9),ph(91));
        Border borderScroll = BorderFactory.createLineBorder(new Color(70,70,70));
        scroll.setBorder(BorderFactory.createCompoundBorder(borderScroll, BorderFactory.createEmptyBorder(0,0,0,0)));
        
        JComboBox combobox = new JComboBox();
        combobox.setBounds(pw(16.5),ph(5.2),pw(3.66),ph(3.9));
        combobox.addItem("20");
        combobox.addItem("19");
        combobox.addItem("18");
        combobox.addItem("17");
        combobox.addItem("16");
        combobox.addItem("15");
        combobox.addItem("14");
        combobox.addItem("13");
        combobox.addItem("12");
        combobox.addItem("11");
        combobox.addItem("10");
        combobox.addItem("9");
        combobox.addItem("8");
        combobox.addItem("7");
        combobox.addItem("6");
        combobox.addItem("5");
        combobox.setSelectedIndex(13);
        
        JPanel botonPanelGuardar = new JPanel();
        JLabel txtBotonPanelGuardar = new JLabel("Guardar"); 
        
        txtBotonPanelGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        txtBotonPanelGuardar.setForeground(Color.white);
        
        botonPanelGuardar.setBounds(pw(27.09), ph(78.12), pw(8.78), ph(5.2));
        botonPanelGuardar.setBackground(new Color(209,97,134));
        botonPanelGuardar.setLayout(new GridBagLayout());
        botonPanelGuardar.setOpaque(true);
        botonPanelGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backend.guardar();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                botonPanelGuardar.setBackground(new Color(219,107,144));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                botonPanelGuardar.setBackground(new Color(209,97,134));
            }
        });
        
        for(int i = 0; i < 10; i++){
            field1Guardar[i] = new JTextArea();
            field2Guardar[i] = new JTextArea();
        }
        
        for(int i = 0; i < 10; i++){
            scrollArea[i] = new JScrollPane(field1Guardar[i]);
            scrollArea2[i] = new JScrollPane(field2Guardar[i]);
            scrollArea[i].setBackground(Color.white);
            scrollArea[i].setBorder(r2);
            scrollArea[i].setBorder(BorderFactory.createCompoundBorder(scrollArea[i].getBorder(), BorderFactory.createEmptyBorder(pw(0.7), ph(1.5), ph(1.2), pw(1))));
            field1Guardar[i].setBorder(BorderFactory.createCompoundBorder(field1Guardar[i].getBorder(), BorderFactory.createEmptyBorder(pw(0.6), ph(0.5), ph(1), pw(1))));
            field1Guardar[i].setLineWrap(true);
            scrollArea2[i].setBackground(Color.white);
            scrollArea2[i].setBorder(r2);
            scrollArea2[i].setBorder(BorderFactory.createCompoundBorder(scrollArea2[i].getBorder(), BorderFactory.createEmptyBorder(pw(0.7), ph(1.5), ph(1.2), pw(1))));
            field2Guardar[i].setBorder(BorderFactory.createCompoundBorder(field2Guardar[i].getBorder(), BorderFactory.createEmptyBorder(pw(0.6), ph(0.5), ph(1), pw(1))));
            field2Guardar[i].setLineWrap(true);
        }
        
        ActionListener cantConceptos = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == combobox){
                    for(int i = 0; i < 10; i++){
                        field1Guardar[i].setVisible(false);
                        field2Guardar[i].setVisible(false);
                        scrollArea[i].setVisible(false);
                        scrollArea2[i].setVisible(false);
                    }
                    String selec = combobox.getSelectedItem().toString();
                    if(selec.equals("5")){
                        componentes.setPreferredSize(new Dimension(pw(64),ph(86)));
                        botonPanelGuardar.setLocation(pw(27.09), ph(78.12));
                        scroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
                        double ik = 15.62;
                        for(int i = 0; i < 5; i++, ik+=12.8){
                            scrollArea[i].setBounds(pw(2.2),ph(ik),pw(21.96),ph(6.56));
                            scrollArea[i].setVisible(true);
                            field1Guardar[i].setVisible(true);
                        }
                        ik = 15.62;
                        for(int i = 0; i < 5; i++, ik+=12.8){
                            scrollArea2[i].setBounds(pw(26.35),ph(ik),pw(36.6),ph(6.56));
                            scrollArea2[i].setVisible(true);
                            field2Guardar[i].setVisible(true);
                        }
                    } else if(selec.equals("6")){
                        componentes.setPreferredSize(new Dimension(pw(64),ph(86)));
                        botonPanelGuardar.setLocation(pw(27.09), ph(78.12));
                        scroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
                        double ik = 15.62;
                        for(int i = 0; i < 6; i++, ik+=10.24){
                            scrollArea[i].setBounds(pw(2.2),ph(ik),pw(21.96),ph(6.56));
                            scrollArea[i].setVisible(true);
                            field1Guardar[i].setVisible(true);
                        }
                        ik = 15.62;
                        for(int i = 0; i < 6; i++, ik+=10.24){
                            scrollArea2[i].setBounds(pw(26.35),ph(ik),pw(36.6),ph(6.56));
                            scrollArea2[i].setVisible(true);
                            field2Guardar[i].setVisible(true);
                        }
                    } else if(selec.equals("7")){
                        componentes.setPreferredSize(new Dimension(pw(64),ph(86)));
                        botonPanelGuardar.setLocation(pw(27.09), ph(78.12));
                        scroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
                        double ik = 15.62;
                        for(int i = 0; i < 7; i++, ik+=8.54){
                            scrollArea[i].setBounds(pw(2.2),ph(ik),pw(21.96),ph(6.56));
                            scrollArea[i].setVisible(true);
                            field1Guardar[i].setVisible(true);
                        }
                        ik = 15.62;
                        for(int i = 0; i < 7; i++, ik+=8.54){
                            scrollArea2[i].setBounds(pw(26.35),ph(ik),pw(36.6),ph(6.56));
                            scrollArea2[i].setVisible(true);
                            field2Guardar[i].setVisible(true);
                        }
                    } else if(selec.equals("8")){
                        componentes.setPreferredSize(new Dimension(pw(64),ph(97)));
                        botonPanelGuardar.setLocation(pw(27.09), ph(88.3));
                        scroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
                        double ik = 15.62;
                        for(int i = 0; i < 8; i++, ik+=8.8){
                            scrollArea[i].setBounds(pw(2.2),ph(ik),pw(21.96),ph(6.56));
                            scrollArea[i].setVisible(true);
                            field1Guardar[i].setVisible(true);
                        }
                        ik = 15.62;
                        for(int i = 0; i < 8; i++, ik+=8.8){
                            scrollArea2[i].setBounds(pw(26.35),ph(ik),pw(36.6),ph(6.56));
                            scrollArea2[i].setVisible(true);
                            field2Guardar[i].setVisible(true);
                        }
                    } else if(selec.equals("9")){
                        componentes.setPreferredSize(new Dimension(pw(64),ph(106)));
                        botonPanelGuardar.setLocation(pw(27.09), ph(97.2));
                        scroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
                        double ik = 15.62;
                        for(int i = 0; i < 9; i++, ik+=8.8){
                            scrollArea[i].setBounds(pw(2.2),ph(ik),pw(21.96),ph(6.56));
                            scrollArea[i].setVisible(true);
                            field1Guardar[i].setVisible(true);
                        }
        
                        ik = 15.62;
                        for(int i = 0; i < 9; i++, ik+=8.8){
                            scrollArea2[i].setBounds(pw(26.35),ph(ik),pw(36.6),ph(6.56));
                            scrollArea2[i].setVisible(true);
                            field2Guardar[i].setVisible(true);
                        }
                    } else if(selec.equals("10")){
                        componentes.setPreferredSize(new Dimension(pw(64),ph(115)));
                        botonPanelGuardar.setLocation(pw(27.09), ph(106));
                        scroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
                        double ik = 15.62;
                        for(int i = 0; i < 10; i++, ik+=8.8){
                            scrollArea[i].setBounds(pw(2.2),ph(ik),pw(21.96),ph(6.56));
                            scrollArea[i].setVisible(true);
                            field1Guardar[i].setVisible(true);
                        }
        
                        ik = 15.62;
                        for(int i = 0; i < 10; i++, ik+=8.8){
                            scrollArea2[i].setBounds(pw(26.35),ph(ik),pw(36.6),ph(6.56));
                            scrollArea2[i].setVisible(true);
                            field2Guardar[i].setVisible(true);
                        }
                    }
                }
            }
        };
        combobox.addActionListener(cantConceptos);
        
        for(int i = 0; i < 10; i++){
            componentes.add(scrollArea[i]);
            componentes.add(scrollArea2[i]);
        }
        
        double ik = 15.62;
        for(int i = 0; i < 7; i++, ik+=8.54){
            scrollArea[i].setBounds(pw(2.2),ph(ik),pw(21.96),ph(6.56));
            scrollArea[i].setVisible(true);
            field1Guardar[i].setVisible(true);
            
        }
        
        ik = 15.62;
        for(int i = 0; i < 7; i++, ik+=8.54){
            scrollArea2[i].setBounds(pw(26.35),ph(ik),pw(36.6),ph(6.56));
            scrollArea2[i].setVisible(true);
            field2Guardar[i].setVisible(true);
        }
        
        JPanel fondo = new JPanel();
        fondo.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        fondo.setBackground(new Color(70,70,70));  
        
        JLabel txtCantidad = new JLabel("Cantidad de conceptos:");
        txtCantidad.setBounds(pw(3.66), ph(5.2), pw(14.64), ph(3.9));
        txtCantidad.setFont(new Font("Arial", Font.BOLD, 14));
        txtCantidad.setForeground(Color.white);
        
        JLabel txtArchivo = new JLabel("Nombre de archivo:");
        txtArchivo.setBounds(pw(29.28), ph(5.2), pw(14.64), ph(3.9));
        txtArchivo.setFont(new Font("Arial", Font.BOLD, 14));
        txtArchivo.setForeground(Color.white);
        
        fieldArchivo.setBounds(pw(39.9),ph(4.95),pw(14.64),ph(4.56));
        fieldArchivo.setVisible(true);    
        fieldArchivo.setBorder(r2);
        fieldArchivo.setBorder(BorderFactory.createCompoundBorder(fieldArchivo.getBorder(), BorderFactory.createEmptyBorder(pw(0), ph(1.5), ph(0), pw(0.7)))); 
        
        
        scroll.setViewportView(componentes);
        
        botonPanelGuardar.add(txtBotonPanelGuardar);
        componentes.add(combobox);
        componentes.add(fieldArchivo);
        componentes.add(txtCantidad);
        componentes.add(txtArchivo);
        componentes.add(botonPanelGuardar);
        ventana.add(scroll);
        
    }
    
    public void VentanaModificar(){
        JFrame ventana = new JFrame();
        ventana.setLayout(null);
        ventana.setBounds(0,0,pw(65.88),ph(91.14));
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        
        RoundLineBorder2 r2 = new RoundLineBorder2(Color.lightGray.darker(),true);
        
        JComboBox combobox = new JComboBox();
        combobox.setBounds(pw(16.47),ph(5.2),pw(3.66),ph(3.9));
        combobox.addItem("10");
        combobox.addItem("9");
        combobox.addItem("8");
        combobox.addItem("7");
        combobox.addItem("6");
        combobox.addItem("5");
        
        
        JPanel fondo = new JPanel();
        fondo.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        fondo.setBackground(new Color(70,70,70));  
        
        JLabel txtCantidad = new JLabel("Cantidad de conceptos:");
        txtCantidad.setBounds(pw(3.66), ph(5.2), pw(14.64), ph(3.9));
        txtCantidad.setFont(new Font("Arial", Font.BOLD, 14));
        txtCantidad.setForeground(Color.white);
        
        JLabel txtArchivo = new JLabel("Nombre de archivo:");
        txtArchivo.setBounds(pw(29.28), ph(5.2), pw(14.64), ph(3.9));
        txtArchivo.setFont(new Font("Arial", Font.BOLD, 14));
        txtArchivo.setForeground(Color.white);
        
        fieldArchivo.setBounds(pw(39.9),ph(4.95),pw(14.6),ph(4.57));
        fieldArchivo.setVisible(true);    
        fieldArchivo.setBorder(r2);
        fieldArchivo.setBorder(BorderFactory.createCompoundBorder(fieldArchivo.getBorder(), BorderFactory.createEmptyBorder(pw(0.7), ph(1.5), ph(2), pw(0.7)))); 
        
        JPanel botonPanelGuardar = new JPanel();
        JLabel txtBotonPanelGuardar = new JLabel("Modificar"); 
        
        double ik = 15.62;
        for(int i = 0; i < 10; i++, ik+=5.86){
            field1Guardar[i] = new JTextArea();
            field1Guardar[i].setBounds(pw(2.2),ph(ik),pw(21.96),ph(4.56));
            field1Guardar[i].setVisible(true);
            
            field1Guardar[i].setBorder(r2);
            field1Guardar[i].setBorder(BorderFactory.createCompoundBorder(field1Guardar[i].getBorder(), BorderFactory.createEmptyBorder(pw(0.7), ph(1.5), ph(2), pw(0.7))));
            
            ventana.add(field1Guardar[i]);
        }
        
        ik = 15.62;
        for(int i = 0; i < 10; i++, ik+=5.86){
            field2Guardar[i] = new JTextArea();
            field2Guardar[i].setBounds(pw(26.35),ph(ik),pw(36.6),ph(4.56));
            field2Guardar[i].setVisible(true);
            
            field2Guardar[i].setBorder(r2);
            field2Guardar[i].setBorder(BorderFactory.createCompoundBorder(field2Guardar[i].getBorder(), BorderFactory.createEmptyBorder(pw(0.7), ph(1.5), ph(2), pw(0.7))));
            
            ventana.add(field2Guardar[i]);
        }
        
        txtBotonPanelGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        txtBotonPanelGuardar.setForeground(Color.white);
        
        botonPanelGuardar.setBounds(pw(27.1), ph(78.1), pw(8.78), ph(5.2));
        botonPanelGuardar.setBackground(new Color(209,97,134));
        botonPanelGuardar.setLayout(new GridBagLayout());
        botonPanelGuardar.setOpaque(true);
        botonPanelGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backend.modificar();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                botonPanelGuardar.setBackground(new Color(219,107,144));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                botonPanelGuardar.setBackground(new Color(209,97,134));
            }
        });
        
        botonPanelGuardar.add(txtBotonPanelGuardar);
        ventana.add(combobox);
        ventana.add(fieldArchivo);
        ventana.add(txtCantidad);
        ventana.add(txtArchivo);
        ventana.add(botonPanelGuardar);
        ventana.add(fondo);
    }
}
