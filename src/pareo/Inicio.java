package pareo;

import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static pareo.Pareo.field1Guardar;
import static pareo.Pareo.field2Guardar;

public class Inicio {
    
    JPanel panelPrincipal = new JPanel();
    JPanel panelComponentes = new JPanel();
    JPanel panelHeader = new JPanel();
    JPanel panelBotonGuardar = new JPanel();
    JPanel panelBotonComenzar = new JPanel();
    JPanel panelBotonEditar = new JPanel();
    JPanel panelBotonInterfaz = new JPanel();
    JPanel panelLinea1 = new JPanel();
    JPanel panelLinea2 = new JPanel();
    JLabel textoBotonComenzar = new JLabel("Comenzar");
    JLabel textoBotonGuardar = new JLabel("Guardar");
    JLabel textoBotonEditar = new JLabel("Editar");
    JLabel textoBotonInterfaz = new JLabel("Interfaz");
    JLabel textoHeader = new JLabel("EasyStudy");
    JFrame ventanaInicio = new JFrame();
    JFrame ventanaComenzar = new JFrame();
    static JComboBox comboboxSeleccionarArchivo = new JComboBox();
    static String seleccion = "";
    boolean ventanaAbierta = false;
    
    Pareo pareo = new Pareo();
    BackEnd backend = new BackEnd();
    
    public Inicio(){
        initComponents();
    }
    
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
    
    public void initComponents(){
        
        ventanaInicio.setLayout(null);
        ventanaInicio.setBounds(0, 0, 600, 600);
        ventanaInicio.setLocationRelativeTo(null);
        ventanaInicio.setExtendedState(MAXIMIZED_BOTH);
        ventanaInicio.setDefaultCloseOperation(EXIT_ON_CLOSE);    
        ventanaInicio.setVisible(true);
        ventanaInicio.getContentPane().setBackground(new Color(47, 53, 93));
        
        panelPrincipal.setLayout(null); 
        panelPrincipal.setBounds(pw(0), ph(0), pw(100), ph(100));
        panelPrincipal.setOpaque(false);
        
        panelComponentes.setLayout(null);
        panelComponentes.setBounds(pw(0), ph(0), pw(100), ph(100));
        panelComponentes.setOpaque(false);
        
        panelHeader.setLayout(null);
        panelHeader.setBounds(pw(0), ph(0), pw(100), ph(9));
        panelHeader.setBackground(new Color(209, 97, 134));
        
        textoHeader.setBounds(pw(10.55), ph(2), pw(15), ph(5.45));
        textoHeader.setFont(new Font("Myriad Pro", Font.PLAIN, 45));
        textoHeader.setForeground(Color.white);
        panelHeader.add(textoHeader);
        
        panelLinea1.setBounds(pw(25.9), ph(4.5), pw(72.3), ph(0.4));
        panelLinea1.setBackground(Color.white);
        panelHeader.add(panelLinea1);
        
        panelLinea2.setBounds(pw(1.7), ph(4.5), pw(7.5), ph(0.4));
        panelLinea2.setBackground(Color.white);
        panelHeader.add(panelLinea2);
        
        panelBotonComenzar.setLayout(new GridBagLayout());
        panelBotonComenzar.setBounds(pw(40), ph(25), pw(20), ph(10));
        panelBotonComenzar.setBackground(new Color(209, 97, 134));
        textoBotonComenzar.setFont(new Font("Arial", Font.BOLD, 22));
        textoBotonComenzar.setForeground(Color.white);
        panelBotonComenzar.add(textoBotonComenzar);
        panelBotonComenzar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(ventanaAbierta){
                    ventanaComenzar.setVisible(true);
                } else{
                    VentanaComenzar();
                    ventanaAbierta = true;
                }  
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                panelBotonComenzar.setBackground(new Color(219,107,144));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                panelBotonComenzar.setBackground(new Color(209,97,134));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBotonComenzar.setBackground(new Color(199,87,124));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelBotonComenzar.setBackground(new Color(219,107,144));
            }
        });
        
        
        
        panelBotonGuardar.setLayout(new GridBagLayout());
        panelBotonGuardar.setBounds(pw(40), ph(40), pw(20), ph(10));
        panelBotonGuardar.setBackground(new Color(209, 97, 134));
        textoBotonGuardar.setFont(new Font("Arial", Font.BOLD, 22));
        textoBotonGuardar.setForeground(Color.white);
        panelBotonGuardar.add(textoBotonGuardar);
        panelBotonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            pareo.VentanaGuardar();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                panelBotonGuardar.setBackground(new Color(219,107,144));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                panelBotonGuardar.setBackground(new Color(209,97,134));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBotonGuardar.setBackground(new Color(199,87,124));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelBotonGuardar.setBackground(new Color(219,107,144));
            }
        });
        
        panelBotonEditar.setLayout(new GridBagLayout());
        panelBotonEditar.setBounds(pw(40), ph(55), pw(20), ph(10));
        panelBotonEditar.setBackground(new Color(209, 97, 134));
        textoBotonEditar.setFont(new Font("Arial", Font.BOLD, 22));
        textoBotonEditar.setForeground(Color.white);
        panelBotonEditar.add(textoBotonEditar);
        panelBotonEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pareo.VentanaModificar();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                panelBotonEditar.setBackground(new Color(219,107,144));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                panelBotonEditar.setBackground(new Color(209,97,134));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBotonEditar.setBackground(new Color(199,87,124));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelBotonEditar.setBackground(new Color(219,107,144));
            }
        });
        
        panelBotonInterfaz.setLayout(new GridBagLayout());
        panelBotonInterfaz.setBounds(pw(40), ph(70), pw(20), ph(10));
        panelBotonInterfaz.setBackground(new Color(209, 97, 134));
        textoBotonInterfaz.setFont(new Font("Arial", Font.BOLD, 22));
        textoBotonInterfaz.setForeground(Color.white);
        panelBotonInterfaz.add(textoBotonInterfaz);
        panelBotonInterfaz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VentanaInterfaz();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                panelBotonInterfaz.setBackground(new Color(219,107,144));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                panelBotonInterfaz.setBackground(new Color(209,97,134));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBotonInterfaz.setBackground(new Color(199,87,124));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelBotonInterfaz.setBackground(new Color(219,107,144));
            }
        });
        
        panelComponentes.add(panelHeader);
        panelComponentes.add(panelBotonComenzar);
        panelComponentes.add(panelBotonGuardar);
        panelComponentes.add(panelBotonEditar);
        panelComponentes.add(panelBotonInterfaz);
        
        panelPrincipal.add(panelComponentes);
        
        ventanaInicio.getContentPane().add(panelPrincipal);

        ventanaInicio.revalidate();
        panelPrincipal.revalidate();
    }
    
    public void VentanaComenzar(){
        
        ventanaComenzar.setLayout(null);
        ventanaComenzar.setBounds(0,0,pw(65.9),ph(91));
        ventanaComenzar.setVisible(true);
        ventanaComenzar.setLocationRelativeTo(null);
        ventanaComenzar.setResizable(false);
        
        RoundLineBorder2 r2 = new RoundLineBorder2(Color.lightGray.darker(),true);
        
        comboboxSeleccionarArchivo.setBounds(pw(26.5),ph(5.2),pw(15),ph(3.9));
        comboboxSeleccionarArchivo.addItem("Seleccionar");
        backend.CargarItems();
        ItemListener listenerSeleccionarArchivo = new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getSource() == comboboxSeleccionarArchivo){
                    seleccion = comboboxSeleccionarArchivo.getSelectedItem().toString();
                    if(seleccion.equals("Seleccionar")){
                        for(int i = 0; i < 10; i++){
                            field1Guardar[i].setText("");
                            field2Guardar[i].setText("");
                            field1Guardar[i].setEditable(false);
                            field2Guardar[i].setEditable(false);
                            field1Guardar[i].setBackground(new Color(220, 220, 220));
                            field2Guardar[i].setBackground(new Color(220, 220, 220));
                        }
                    } else{
                        //backend.CargarDatosComenzar();
                        for(int i = 0; i < 10; i++){
                            field1Guardar[i].setEditable(true);
                            field2Guardar[i].setEditable(true);
                            field1Guardar[i].setBackground(Color.white);
                            field2Guardar[i].setBackground(Color.white);
                        }
                        
                    }
                }
            }
        };
        comboboxSeleccionarArchivo.addItemListener(listenerSeleccionarArchivo);
        
        JPanel fondo = new JPanel();
        fondo.setBounds(0, 0, ventanaComenzar.getWidth(), ventanaComenzar.getHeight());
        fondo.setBackground(new Color(70,70,70));  
        
        JLabel txtCantidad = new JLabel("Selecciona un archivo para comenzar:");
        txtCantidad.setBounds(pw(3.66), ph(5.2), pw(22), ph(3.9));
        txtCantidad.setFont(new Font("Arial", Font.BOLD, 17));
        txtCantidad.setForeground(Color.white);
        
        JPanel botonPanelAceptar = new JPanel();
        JLabel txtBotonPanelAceptar = new JLabel("Aceptar"); 
        
        double ik = 15.62;
        for(int i = 0; i < 10; i++, ik+=5.86){
            field1Guardar[i] = new JTextArea();
            field1Guardar[i].setBounds(pw(2.2),ph(ik),pw(21.96),ph(4.56));
            field1Guardar[i].setVisible(true);
            
            field1Guardar[i].setBorder(r2);
            field1Guardar[i].setBorder(BorderFactory.createCompoundBorder(field1Guardar[i].getBorder(), BorderFactory.createEmptyBorder(pw(0.7), ph(1.5), ph(2), pw(0.7))));
            
            ventanaComenzar.add(field1Guardar[i]);
        }
        
        ik = 15.62;
        for(int i = 0; i < 10; i++, ik+=5.86){
            field2Guardar[i] = new JTextArea();
            field2Guardar[i].setBounds(pw(26.35),ph(ik),pw(36.6),ph(4.56));
            field2Guardar[i].setVisible(true);
            
            field2Guardar[i].setBorder(r2);
            field2Guardar[i].setBorder(BorderFactory.createCompoundBorder(field2Guardar[i].getBorder(), BorderFactory.createEmptyBorder(pw(0.7), ph(1.5), ph(2), pw(0.7))));
            
            ventanaComenzar.add(field2Guardar[i]);
        }
        
        txtBotonPanelAceptar.setFont(new Font("Arial", Font.BOLD, 16));
        txtBotonPanelAceptar.setForeground(Color.white);
        
        botonPanelAceptar.setBounds(pw(27.09), ph(78.12), pw(8.78), ph(5.2));
        botonPanelAceptar.setBackground(new Color(209,97,134));
        botonPanelAceptar.setLayout(new GridBagLayout());
        botonPanelAceptar.setOpaque(true);
        botonPanelAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                /*for(){
                }*/
                pareo.Pareo();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                botonPanelAceptar.setBackground(new Color(219,107,144));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                botonPanelAceptar.setBackground(new Color(209,97,134));
            }
        });
        
        for(int i = 0; i < 10; i++){
            field1Guardar[i].setBackground(new Color(220, 220, 220));
            field2Guardar[i].setBackground(new Color(220, 220, 220));
            field1Guardar[i].setEditable(false);
            field2Guardar[i].setEditable(false);
        }
        
        botonPanelAceptar.add(txtBotonPanelAceptar);
        ventanaComenzar.add(comboboxSeleccionarArchivo);
        ventanaComenzar.add(txtCantidad);
        ventanaComenzar.add(botonPanelAceptar);
        ventanaComenzar.add(fondo);
        
    }
    
    public void CambiarInterfaz(){
        
    }
    
    public void VentanaInterfaz(){
        
        JFrame ventana;
        JPanel panelPrincipalInterfaz;
        JPanel contDis1, priDis1, secDis1;
        JPanel contDis2, priDis2, secDis2;
        JPanel contDis3, priDis3, secDis3;
        JPanel contDis4, priDis4, secDis4;
        JPanel contDis5, priDis5, secDis5;
        JPanel contDis6, priDis6, secDis6;
        JPanel contDis7, priDis7, secDis7;
        JPanel contDis8, priDis8, secDis8;
        JPanel contDis9, priDis9, secDis9;
        
        Color colPri1 = Color.white;
        Color colSec1 = new Color(42,150,255);
        Color colPri2 = new Color(102,102,102);
        Color colSec2 = new Color(42,150,255);
        Color colPri3 = new Color(47,53,93);
        Color colSec3 = new Color(42,150,255);
        
        Color colPri4 = Color.white;
        Color colSec4 = new Color(209,97,134);
        Color colPri5 = new Color(102,102,102);
        Color colSec5 = new Color(209,97,134);
        Color colPri6 = new Color(47,53,93);
        Color colSec6 = new Color(209,97,134);
        
        Color colPri7 = Color.white;
        Color colSec7 = new Color(0,167,180);
        Color colPri8 = new Color(102,102,102);
        Color colSec8 = new Color(0,167,180);
        Color colPri9 = new Color(47,53,93);
        Color colSec9 = new Color(0,167,180);
        
        int hover = 20;
        int hoverw = 10;
        
        ventana = new JFrame();
        ventana.setBounds(0,0,pw(65.9),ph(91));
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setVisible(true);
        
        panelPrincipalInterfaz = new JPanel();
        panelPrincipalInterfaz.setLayout(null);
        panelPrincipalInterfaz.setBackground(new Color(70,70,70));
        ventana.add(panelPrincipalInterfaz);
        
        contDis1 = new JPanel();
        contDis1.setLayout(null);
        contDis1.setBounds(pw(5.3), ph(10), pw(15), ph(15));
        panelPrincipalInterfaz.add(contDis1);
        priDis1 = new JPanel();
        priDis1.setBounds(pw(0), ph(0), pw(15), ph(9.5));
        priDis1.setBackground(Color.white);
        contDis1.add(priDis1);
        secDis1 = new JPanel();
        secDis1.setBounds(pw(0), ph(9.5), pw(15), ph(6));
        secDis1.setBackground(new Color(42,150,255));
        contDis1.add(secDis1);
        contDis1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CambiarInterfaz();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                priDis1.setBackground(new Color(255-hoverw,255-hoverw,255-hoverw));
                secDis1.setBackground(new Color(42+hover,150+hover,255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                priDis1.setBackground(Color.white);
                secDis1.setBackground(new Color(42,150,255));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                priDis1.setBackground(new Color(42,150,255));
                secDis1.setBackground(new Color(42,150,255));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                priDis1.setBackground(new Color(255-hoverw,255-hoverw,255-hoverw));
                secDis1.setBackground(new Color(42+hover,150+hover,255));
                if(!contDis1.isCursorSet()){
                    priDis1.setBackground(Color.white);
                    secDis1.setBackground(new Color(42,150,255));
                }
            }
        });
        
        contDis2 = new JPanel();
        contDis2.setLayout(null);
        contDis2.setBounds(pw(25.3), ph(10), pw(15), ph(15));
        panelPrincipalInterfaz.add(contDis2);
        priDis2 = new JPanel();
        priDis2.setBounds(pw(0), ph(0), pw(15), ph(9.5));
        priDis2.setBackground(new Color(102,102,102));
        contDis2.add(priDis2);
        secDis2 = new JPanel();
        secDis2.setBounds(pw(0), ph(9.5), pw(15), ph(6));
        secDis2.setBackground(new Color(42,150,255));
        contDis2.add(secDis2);
        contDis2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CambiarInterfaz();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                priDis2.setBackground(new Color(102+hover,102+hover,102+hover));
                secDis2.setBackground(new Color(42+hover,150+hover,255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                priDis2.setBackground(new Color(102,102,102));
                secDis2.setBackground(new Color(42,150,255));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                priDis2.setBackground(new Color(42,150,255));
                secDis2.setBackground(new Color(42,150,255));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                priDis2.setBackground(new Color(102+hover,102+hover,102+hover));
                secDis2.setBackground(new Color(42+hover,150+hover,255));
                if(!contDis1.isCursorSet()){
                    priDis2.setBackground(new Color(102,102,102));
                    secDis2.setBackground(new Color(42,150,255));
                }
            }
        });
        
        contDis3 = new JPanel();
        contDis3.setLayout(null);
        contDis3.setBounds(pw(45.3), ph(10), pw(15), ph(15));
        panelPrincipalInterfaz.add(contDis3);
        priDis3 = new JPanel();
        priDis3.setBounds(pw(0), ph(0), pw(15), ph(9.5));
        priDis3.setBackground(new Color(47,53,93));
        contDis3.add(priDis3);
        secDis3 = new JPanel();
        secDis3.setBounds(pw(0), ph(9.5), pw(15), ph(6));
        secDis3.setBackground(new Color(42,150,255));
        contDis3.add(secDis3);
        contDis3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CambiarInterfaz();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                priDis3.setBackground(new Color(47+hover,53+hover,93+hover));
                secDis3.setBackground(new Color(42+hover,150+hover,255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                priDis3.setBackground(new Color(47,53,93));
                secDis3.setBackground(new Color(42,150,255));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                priDis3.setBackground(new Color(42,150,255));
                secDis3.setBackground(new Color(42,150,255));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                priDis3.setBackground(new Color(47+hover,53+hover,93+hover));
                secDis3.setBackground(new Color(42+hover,150+hover,255));
                if(!contDis1.isCursorSet()){
                    priDis3.setBackground(new Color(47,53,93));
                    secDis3.setBackground(new Color(42,150,255));
                }
            }
        });
        
        contDis4 = new JPanel();
        contDis4.setLayout(null);
        contDis4.setBounds(pw(5.3), ph(35), pw(15), ph(15));
        panelPrincipalInterfaz.add(contDis4);
        priDis4 = new JPanel();
        priDis4.setBounds(pw(0), ph(0), pw(15), ph(9.5));
        priDis4.setBackground(Color.white);
        contDis4.add(priDis4);
        secDis4 = new JPanel();
        secDis4.setBounds(pw(0), ph(9.5), pw(15), ph(6));
        secDis4.setBackground(new Color(209,97,134));
        contDis4.add(secDis4);
        contDis4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CambiarInterfaz();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                priDis4.setBackground(new Color(255-hoverw,255-hoverw,255-hoverw));
                secDis4.setBackground(new Color(209+hover,97+hover,134+hover));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                priDis4.setBackground(Color.white);
                secDis4.setBackground(new Color(209,97,134));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                priDis4.setBackground(new Color(209,97,134));
                secDis4.setBackground(new Color(209,97,134));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                priDis4.setBackground(new Color(255-hoverw,255-hoverw,255-hoverw));
                secDis4.setBackground(new Color(209+hover,97+hover,134+hover));
                if(!contDis1.isCursorSet()){
                    priDis4.setBackground(Color.white);
                    secDis4.setBackground(new Color(209,97,134));
                }
            }
        });
        
        contDis5 = new JPanel();
        contDis5.setLayout(null);
        contDis5.setBounds(pw(25.3), ph(35), pw(15), ph(15));
        panelPrincipalInterfaz.add(contDis5);
        priDis5 = new JPanel();
        priDis5.setBounds(pw(0), ph(0), pw(15), ph(9.5));
        priDis5.setBackground(new Color(102,102,102));
        contDis5.add(priDis5);
        secDis5 = new JPanel();
        secDis5.setBounds(pw(0), ph(9.5), pw(15), ph(6));
        secDis5.setBackground(new Color(209,97,134));
        contDis5.add(secDis5);
        contDis5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CambiarInterfaz();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                priDis5.setBackground(new Color(102+hover,102+hover,102+hover));
                secDis5.setBackground(new Color(209+hover,97+hover,134+hover));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                priDis5.setBackground(new Color(102,102,102));
                secDis5.setBackground(new Color(209,97,134));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                priDis5.setBackground(new Color(209,97,134));
                secDis5.setBackground(new Color(209,97,134));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                priDis5.setBackground(new Color(102+hover,102+hover,102+hover));
                secDis5.setBackground(new Color(209+hover,97+hover,134+hover));
                if(!contDis1.isCursorSet()){
                    priDis5.setBackground(new Color(102,102,102));
                    secDis5.setBackground(new Color(209,97,134));
                }
            }
        });
        
        contDis6 = new JPanel();
        contDis6.setLayout(null);
        contDis6.setBounds(pw(45.3), ph(35), pw(15), ph(15));
        panelPrincipalInterfaz.add(contDis6);
        priDis6 = new JPanel();
        priDis6.setBounds(pw(0), ph(0), pw(15), ph(9.5));
        priDis6.setBackground(new Color(47,53,93));
        contDis6.add(priDis6);
        secDis6 = new JPanel();
        secDis6.setBounds(pw(0), ph(9.5), pw(15), ph(6));
        secDis6.setBackground(new Color(209,97,134));
        contDis6.add(secDis6);
        contDis6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CambiarInterfaz();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                priDis6.setBackground(new Color(47+hover,53+hover,93+hover));
                secDis6.setBackground(new Color(209+hover,97+hover,134+hover));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                priDis6.setBackground(new Color(47,53,93));
                secDis6.setBackground(new Color(209,97,134));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                priDis6.setBackground(new Color(209,97,134));
                secDis6.setBackground(new Color(209,97,134));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                priDis6.setBackground(new Color(47+hover,53+hover,93+hover));
                secDis6.setBackground(new Color(209+hover,97+hover,134+hover));
                if(!contDis1.isCursorSet()){
                    priDis6.setBackground(new Color(47,53,93));
                    secDis6.setBackground(new Color(209,97,134));
                }
            }
        });
        
        contDis7 = new JPanel();
        contDis7.setLayout(null);
        contDis7.setBounds(pw(5.3), ph(60), pw(15), ph(15));
        panelPrincipalInterfaz.add(contDis7);
        priDis7 = new JPanel();
        priDis7.setBounds(pw(0), ph(0), pw(15), ph(9.5));
        priDis7.setBackground(Color.white);
        contDis7.add(priDis7);
        secDis7 = new JPanel();
        secDis7.setBounds(pw(0), ph(9.5), pw(15), ph(6));
        secDis7.setBackground(new Color(0,167,180));
        contDis7.add(secDis7);
        contDis7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CambiarInterfaz();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                priDis7.setBackground(new Color(255-hoverw,255-hoverw,255-hoverw));
                secDis7.setBackground(new Color(0+hover,167+hover,180+hover));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                priDis7.setBackground(Color.white);
                secDis7.setBackground(new Color(0,167,180));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                priDis7.setBackground(new Color(0,167,180));
                secDis7.setBackground(new Color(0,167,180));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                priDis7.setBackground(new Color(255-hoverw,255-hoverw,255-hoverw));
                secDis7.setBackground(new Color(0+hover,167+hover,180+hover));
                if(!contDis1.isCursorSet()){
                    priDis7.setBackground(Color.white);
                    secDis7.setBackground(new Color(0,167,180));
                }
            }
        });
        
        contDis8 = new JPanel();
        contDis8.setLayout(null);
        contDis8.setBounds(pw(25.3), ph(60), pw(15), ph(15));
        panelPrincipalInterfaz.add(contDis8);
        priDis8 = new JPanel();
        priDis8.setBounds(pw(0), ph(0), pw(15), ph(9.5));
        priDis8.setBackground(new Color(102,102,102));
        contDis8.add(priDis8);
        secDis8 = new JPanel();
        secDis8.setBounds(pw(0), ph(9.5), pw(15), ph(6));
        secDis8.setBackground(new Color(0,167,180));
        contDis8.add(secDis8);
        contDis8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CambiarInterfaz();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                priDis8.setBackground(new Color(102+hover,102+hover,102+hover));
                secDis8.setBackground(new Color(0+hover,167+hover,180+hover));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                priDis8.setBackground(new Color(102,102,102));
                secDis8.setBackground(new Color(0,167,180));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                priDis8.setBackground(new Color(0,167,180));
                secDis8.setBackground(new Color(0,167,180));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                priDis8.setBackground(new Color(102+hover,102+hover,102+hover));
                secDis8.setBackground(new Color(0+hover,167+hover,180+hover));
                if(!contDis1.isCursorSet()){
                    priDis8.setBackground(new Color(102,102,102));
                    secDis8.setBackground(new Color(0,167,180));
                }
            }
        });
        
        contDis9 = new JPanel();
        contDis9.setLayout(null);
        contDis9.setBounds(pw(45.3), ph(60), pw(15), ph(15));
        panelPrincipalInterfaz.add(contDis9);
        priDis9 = new JPanel();
        priDis9.setBounds(pw(0), ph(0), pw(15), ph(9.5));
        priDis9.setBackground(new Color(47,53,93));
        contDis9.add(priDis9);
        secDis9 = new JPanel();
        secDis9.setBounds(pw(0), ph(9.5), pw(15), ph(6));
        secDis9.setBackground(new Color(0,167,180));
        contDis9.add(secDis9);
        contDis9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CambiarInterfaz();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                priDis9.setBackground(new Color(47+hover,53+hover,93+hover));
                secDis9.setBackground(new Color(0+hover,167+hover,180+hover));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                priDis9.setBackground(new Color(47,53,93));
                secDis9.setBackground(new Color(0,167,180));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                priDis9.setBackground(new Color(0,167,180));
                secDis9.setBackground(new Color(0,167,180));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                priDis9.setBackground(new Color(47+hover,53+hover,93+hover));
                secDis9.setBackground(new Color(0+hover,167+hover,180+hover));
                if(!contDis1.isCursorSet()){
                    priDis9.setBackground(new Color(47,53,93));
                    secDis9.setBackground(new Color(0,167,180));
                }
            }
        });
    }
    
    public static void main(String[] args) {
        
        Inicio inicio = new Inicio();

    }
    
}
