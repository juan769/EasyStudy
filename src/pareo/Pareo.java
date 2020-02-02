
package pareo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javax.swing.JTextField;
/**
 *
 * @author Juan Ballesteros
 */
public class Pareo extends JFrame{
    
    JLabel fondo1 = new JLabel();
    JLabel fondo2 = new JLabel();
    JTextField[] field1 = new JTextField[10];
    JTextField[] field2 = new JTextField[10]; 
    JTextField[] field3 = new JTextField[10];
    JLabel checkVerde[] = new JLabel[10];
    JLabel checkRojo[] = new JLabel[10];
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
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
    JMenu cargar = new JMenu("Cargar");
    JMenu modificar = new JMenu("Modificar");
    JMenuItem guardar = new JMenuItem("Guardar");
    JTextField fieldArchivo = new JTextField();
    JTextField[] field1Guardar = new JTextField[10];
    JTextField[] field2Guardar = new JTextField[10];
    ArrayList<JMenuItem> ItemArchivoCargar = new ArrayList<JMenuItem>();
    ArrayList<JMenuItem> ItemArchivoModificar = new ArrayList<JMenuItem>();
    String archivo = "";
    String archivoSeleccionado = "";
    int count = 0;
    int registros = 0;
    static int numTablas = 0;
    boolean CampoVacio1 = false; 
    boolean CampoVacio2 = false; 
    boolean CampoVacio3 = false;

    RoundLineBorder r = new RoundLineBorder(Color.lightGray.darker(),true);

    public Pareo(){  
        this.setLayout(null);
        this.setBounds(0,0,500,500);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(67, 79, 118));
        
        this.setJMenuBar(menubar);
        menubar.add(menu);
       
        menu.add(guardar);
        menu.add(cargar);
        menu.add(modificar);
        
        try {
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from archivos");
            
            while (rs.next()) {
                registros++;
            }
            
        } catch(Exception e){} 
        
        try {
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from archivos");
            
            int i = 0;
            while(rs.next()){
                ItemArchivoCargar.add(new JMenuItem(rs.getString("archivo")));
                ItemArchivoModificar.add(new JMenuItem(rs.getString("archivo")));
                cargar.add(ItemArchivoCargar.get(i));
                modificar.add(ItemArchivoModificar.get(i));
                i++;
            }
            
        } catch(Exception e){}
        
        ActionListener eventoCargar = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < registros; i++){
                    
                    archivoSeleccionado = "";
            
                    try {
                        Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
                        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("SELECT * FROM " + ItemArchivoCargar.get(i).getText().toLowerCase());
                        ResultSet rs = pst.executeQuery();
            
                        int count = 0;
                        while (rs.next()) {
                            count++;
                        } 
            
                        Integer countArr[] = new Integer[count];
            
                        Connection cn2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
                        PreparedStatement pst2 = (PreparedStatement) cn2.prepareStatement("SELECT * FROM " + ItemArchivoCargar.get(i).getText().toLowerCase());
                        ResultSet rs2 = pst2.executeQuery();
            
                        Integer id[] = new Integer[count];
            
                        int count2 = 0;
                        int j = 0;
                        while (rs2.next()) {
                            id[count2] = Integer.parseInt(rs2.getString("ID"));
                            countArr[j] = count2;
                            j++;
                            count2++;
                        } 
            
                        int arr[] = new int[10];
                        arr[0] = (int)(Math.random()*count);
        
                        for(int k = 1; k < 10; k++){
                            arr[k] = (int)(Math.random()*count);
                            for(int r = 0; r < k; r++){
                                if(arr[k] == arr[r]){
                                    k--;
                                }
                            }
                        }
            
                        Connection cn3 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
                        PreparedStatement pst3 = (PreparedStatement) cn3.prepareStatement("select * from " + ItemArchivoCargar.get(i).getText().toLowerCase() + " where ID = ?");
            
                        for(int m = 0; m < count; m++){
                            for(int r = 0; r < count; r++){
                                if(arr[m] == countArr[r]){
                                    try{
                                        String dei = "";
                    
                                        dei = Integer.toString(id[arr[m]]);
                                        pst3.setString(1, dei);
                    
                                        ResultSet rs3 = pst3.executeQuery();

                                        if(rs3.next()){
                                            field1[m].setText(rs3.getString("concepto"));
                                            field2[m].setText(rs3.getString("respuesta"));
                                        } else{
                                            JOptionPane.showMessageDialog(null, "Conexión no exitosa");
                                        }
                                    }catch(Exception q){}
                                }
                            }
                        }
            
                        pst.close();
                        cn.close();
                        pst2.close();
                        cn2.close();
                        pst3.close();
                        cn3.close();
            
                    } catch (Exception n) {}
                }
            }  
        };
        for(int i = 0; i < registros; i++){
            ItemArchivoCargar.get(i).addActionListener(eventoCargar);
        }
        
        ActionListener eventoModificar = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < registros; i++){
                    if(e.getSource() == ItemArchivoModificar.get(i)){
                        archivo = ItemArchivoModificar.get(i).getText();
                    }
                }
                VentanaModificar();
                CargarDatosGuardados();
            }  
        };
        for(int i = 0; i < registros; i++){
            ItemArchivoModificar.get(i).addActionListener(eventoModificar);
        }
        
        panel.setBounds(0,30,500,700);
        panel.setBackground(new Color(67, 79, 118));
        
        panel2.setLayout(null);
        panel2.setBounds(0,0,1300,800);
        panel2.setOpaque(false);
        
        panel3.setBounds(515,30,900,700);
        panel3.setOpaque(false);
        
        
        fondo1.setBounds(20,30,350,620);
        ImageIcon imagen = new ImageIcon(getClass().getResource("fondo1.png"));
        ImageIcon imagen2 = new ImageIcon(imagen.getImage().getScaledInstance(fondo1.getWidth(),fondo1.getHeight(), Image.SCALE_DEFAULT));
        fondo1.setIcon(imagen2);
        panel.add(fondo1);
        
        fondo2.setBounds(100,30,650,620);
        ImageIcon imagen3 = new ImageIcon(imagen.getImage().getScaledInstance(fondo2.getWidth(),fondo2.getHeight(), Image.SCALE_DEFAULT));
        fondo2.setIcon(imagen3);
        panel3.add(fondo2);
        
            
        for(int i = 0, k = 60; i < 10; i++, k+=60){
            field1[i] = new JTextField();
            field1[i].setBounds(100,k,300,35);
            field1[i].setVisible(true);
            
            field1[i].setBorder(r);
            field1[i].setBorder(BorderFactory.createCompoundBorder(field1[i].getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 5)));
            
            panel2.add(field1[i]);
        }
        
        for(int i = 0, k = 60; i < 10; i++, k+=60){
            field2[i] = new JTextField();
            field2[i].setBounds(750,k,500,35);
            field2[i].setVisible(true);
            
            field2[i].setBorder(r);
            field2[i].setBorder(BorderFactory.createCompoundBorder(field2[i].getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 5)));
            
            panel2.add(field2[i]);
        }
        
        for(int i = 0, k = 60; i < 10; i++, k+=60){
            field3[i] = new JTextField();
            field3[i].setBounds(680,k,50,35);
            field3[i].setVisible(true);
            
            field3[i].setBorder(r);
            field3[i].setBorder(BorderFactory.createCompoundBorder(field1[i].getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 5)));
            
            panel2.add(field3[i]);
        }
        
        for(int i = 0, k = 62; i < 10; i++, k+=60){
            int num = i+1;
            String numString = Integer.toString(num);
            if(i == 9){
                numeros[i] = new JLabel(numString + " -");
                numeros[i].setBounds(43,k,30,30);
                numeros[i].setForeground(Color.WHITE);
                numeros[i].setFont(new Font("Arial", Font.BOLD, 14));
                panel2.add(numeros[i]);
            } else{
                numeros[i] = new JLabel(numString + " -");
                numeros[i].setBounds(50,k,30,30);
                numeros[i].setForeground(Color.WHITE);
                numeros[i].setFont(new Font("Arial", Font.BOLD, 14));
                panel2.add(numeros[i]);
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
        
        botonPanelRevisar.setBounds(458, 550, 150, 60);
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
        
        botonPanelReiniciar.setBounds(458, 550, 150, 60);
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
        
        botonPanelRevolver.setBounds(458, 550, 150, 60);
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
        botonPanel.setBounds(458, 550, 150, 60);
        botonPanel.add(txtBotonReiniciar);
        botonPanel.add(txtBotonRevolver);
        botonPanel.add(txtBotonRevisar);
        botonPanel.setLayout(new GridBagLayout());
        
        this.add(botonPanelRevisar);
        this.add(botonPanelRevolver);
        this.add(botonPanelReiniciar);
        this.add(botonPanel);
        this.add(panel2);
        this.add(panel3);
        this.add(panel);
        
        this.setVisible(true);
        
        CrearChecks();
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
            checkVerde[i].setSize(15,15);
            ImageIcon imagenVerde2 = new ImageIcon(imagenVerde.getImage().getScaledInstance(checkVerde[i].getWidth(),checkVerde[i].getHeight(), Image.SCALE_DEFAULT));
            checkVerde[i].setIcon(imagenVerde2);
            checkVerde[i].setVisible(false);
            panel2.add(checkVerde[i]);
            checkVerde[i].repaint();
        }
        
        for(int i = 0, j = 0; i < 10; i++, j++){
            checkRojo[i].setSize(15,15);
            ImageIcon imagenRojo2 = new ImageIcon(imagenRojo.getImage().getScaledInstance(checkRojo[i].getWidth(),checkRojo[i].getHeight(), Image.SCALE_DEFAULT));
            checkRojo[i].setIcon(imagenRojo2);
            checkRojo[i].setVisible(false);
            panel2.add(checkRojo[i]);
            checkRojo[i].repaint();
        }

    }
    
    public void revisar(){
         
        for(int i = 0; i < 10; i++){
            if(field1[i].getText().equals("")){
                CampoVacio1 = true;
            } else if(field2[i].getText().equals("")){
                CampoVacio2 = true;
            } else if(field3[i].getText().equals("")){
                CampoVacio3 = true;
            }
        }
        
        if(CampoVacio1 == false && CampoVacio2 == false && CampoVacio3 == false){
            for(int i = 0; i < 10; i++){
                if(Integer.parseInt(field3[i].getText()) == 1){
                    if(field1[i].getBounds().contains(100,60,300,35)){
                        checkVerde[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkVerde[i].setVisible(true);
                    } else{
                        checkRojo[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkRojo[i].setVisible(true);
                    }
                } else if(Integer.parseInt(field3[i].getText()) == 2){
                    if(field1[i].getBounds().contains(100,60*2,300,35)){
                        checkVerde[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkVerde[i].setVisible(true);
                    } else{
                        checkRojo[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkRojo[i].setVisible(true);
                    }
                } else if(Integer.parseInt(field3[i].getText()) == 3){
                    if(field1[i].getBounds().contains(100,60*3,300,35)){
                        checkVerde[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkVerde[i].setVisible(true);
                    } else{
                        checkRojo[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkRojo[i].setVisible(true);
                    }
                } else if(Integer.parseInt(field3[i].getText()) == 4){
                    if(field1[i].getBounds().contains(100,60*4,300,35)){
                        checkVerde[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkVerde[i].setVisible(true);
                    } else{
                        checkRojo[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkRojo[i].setVisible(true);
                    }
                } else if(Integer.parseInt(field3[i].getText()) == 5){
                    if(field1[i].getBounds().contains(100,60*5,300,35)){
                        checkVerde[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkVerde[i].setVisible(true);
                    } else{
                        checkRojo[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkRojo[i].setVisible(true);
                    }
                } else if(Integer.parseInt(field3[i].getText()) == 6){
                    if(field1[i].getBounds().contains(100,60*6,300,35)){
                        checkVerde[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkVerde[i].setVisible(true);
                    } else{
                        checkRojo[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkRojo[i].setVisible(true);
                    }
                } else if(Integer.parseInt(field3[i].getText()) == 7){
                    if(field1[i].getBounds().contains(100,60*7,300,35)){
                        checkVerde[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkVerde[i].setVisible(true);
                    } else{
                        checkRojo[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkRojo[i].setVisible(true);
                    }
                } else if(Integer.parseInt(field3[i].getText()) == 8){
                    if(field1[i].getBounds().contains(100,60*8,300,35)){
                        checkVerde[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkVerde[i].setVisible(true);
                    } else{
                        checkRojo[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkRojo[i].setVisible(true);
                    }
                } else if(Integer.parseInt(field3[i].getText()) == 9){
                    if(field1[i].getBounds().contains(100,60*9,300,35)){
                        checkVerde[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkVerde[i].setVisible(true);
                    } else{
                        checkRojo[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkRojo[i].setVisible(true);
                    }
                } else if(Integer.parseInt(field3[i].getText()) == 10){
                    if(field1[i].getBounds().contains(100,60*10,300,35)){
                        checkVerde[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkVerde[i].setVisible(true);
                    } else{
                        checkRojo[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                        checkRojo[i].setVisible(true);
                    }
                } else{
                    checkRojo[i].setLocation(field3[i].getX()-25, field3[i].getY()+9);
                    checkRojo[i].setVisible(true);
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
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(arr[i] == j){
                    field1[i].setLocation(100, 60*(j+1));
                }
            }
        }
        
        //Revolver los dos últimos componentes
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(arr2[i] == j){
                    field2[i].setLocation(750, 60*(j+1));
                    field3[i].setLocation(680, 60*(j+1));
                }
            }     
        }     
    }
    
    public void modificar(){
        boolean campoVacio = false;
        boolean nombreVacio = false;
        
        if(fieldArchivo.getText().trim().equals("")){
            nombreVacio = true;
        }
        
        for(int i = 0; i < 10; i++){
            if(field1Guardar[i].getText().trim().equals("") || field2Guardar[i].getText().trim().equals("")){
                campoVacio = true;
            }
        }
        
        if(nombreVacio == false && campoVacio == false){
            try{
                Connection cn2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
                PreparedStatement pst2 = (PreparedStatement) cn2.prepareStatement("select * from " + archivo.toLowerCase());
                
                ResultSet rs = pst2.executeQuery();
                
                ArrayList<Integer> id = new ArrayList<Integer>();
                while(rs.next()){
                    id.add(Integer.parseInt(rs.getString("ID")));
                }
                
                for(int i = 0; i < 10; i++){
                    Connection cn3 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
                    PreparedStatement pst3 = (PreparedStatement) cn3.prepareStatement("update " + archivo.toLowerCase() + " set concepto=?, respuesta=? where ID = " + Integer.toString(id.get(i)));
                
                    pst3.setString(1, field1Guardar[i].getText().trim());
                    pst3.setString(2, field2Guardar[i].getText().trim());
                    pst3.executeUpdate();
                }
                
                Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
                PreparedStatement pst = (PreparedStatement) cn.prepareStatement("rename table " + archivo.toLowerCase() + " to " + fieldArchivo.getText());
                pst.executeUpdate();

                Connection cn4 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
                PreparedStatement pst4 = (PreparedStatement) cn4.prepareStatement("update archivos set archivo=? where archivo = '" + archivo + "'");
                
                pst4.setString(1, fieldArchivo.getText().trim());
                pst4.executeUpdate();
            }catch(Exception e){}  
            
            try {
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from archivos");
            
            //Actualizar el nombre de archivo en la pestaña "Cargar" y "Modificar"
            int i = 0;
            while(rs.next()){
                ItemArchivoCargar.get(i).setText(rs.getString("archivo"));
                ItemArchivoModificar.get(i).setText(rs.getString("archivo"));
                i++;
            }
            }catch(Exception e){}

            //////////////////////////////////////<<<<<<<<----------------------
            
            try {
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from archivos");
            
            registros = 0;
            while (rs.next()) {
                registros++;
            }
            
        } catch(Exception e){} 
            
            ActionListener evento = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < registros; i++){
            
                    try {
                        Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
                        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("SELECT * FROM " + ItemArchivoCargar.get(i).getText().toLowerCase());
                        ResultSet rs = pst.executeQuery();
            
                        int count = 0;
                        while (rs.next()) {
                            count++;
                        } 
            
                        Integer countArr[] = new Integer[count];
            
                        Connection cn2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
                        PreparedStatement pst2 = (PreparedStatement) cn2.prepareStatement("SELECT * FROM " + ItemArchivoCargar.get(i).getText().toLowerCase());
                        ResultSet rs2 = pst2.executeQuery();
            
                        Integer id[] = new Integer[count];
            
                        int count2 = 0;
                        int j = 0;
                        while (rs2.next()) {
                            id[count2] = Integer.parseInt(rs2.getString("ID"));
                            countArr[j] = count2;
                            j++;
                            count2++;
                        } 
            
                        int arr[] = new int[10];
                        arr[0] = (int)(Math.random()*count);
        
                        for(int k = 1; k < 10; k++){
                            arr[k] = (int)(Math.random()*count);
                            for(int r = 0; r < k; r++){
                                if(arr[k] == arr[r]){
                                    k--;
                                }
                            }
                        }
            
                        Connection cn3 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
                        PreparedStatement pst3 = (PreparedStatement) cn3.prepareStatement("select * from " + ItemArchivoCargar.get(i).getText().toLowerCase() + " where ID = ?");
            
                        for(int m = 0; m < count; m++){
                            for(int r = 0; r < count; r++){
                                if(arr[m] == countArr[r]){
                                    try{
                                        String dei = "";
                    
                                        dei = Integer.toString(id[arr[m]]);
                                        pst3.setString(1, dei);
                    
                                        ResultSet rs3 = pst3.executeQuery();

                                        if(rs3.next()){
                                            field1[m].setText(rs3.getString("concepto"));
                                            field2[m].setText(rs3.getString("respuesta"));
                                        } else{
                                            JOptionPane.showMessageDialog(null, "Conexión no exitosa");
                                        }
                                    }catch(Exception q){}
                                }
                            }
                        }
            
                        pst.close();
                        cn.close();
                        pst2.close();
                        cn2.close();
                        pst3.close();
                        cn3.close();
            
                    } catch (Exception n) {}
                }
            }   
        };
            
            for(int i = 0; i < registros; i++){
            ItemArchivoCargar.get(i).addActionListener(evento);
            }
            
            JOptionPane.showMessageDialog(null, "Archivo modificado correctamente");
        } else{
            if(campoVacio == true){
                JOptionPane.showMessageDialog(null, "Por favor llena todos los campos");
            }
            if(nombreVacio == true){
                JOptionPane.showMessageDialog(null, "Por favor nombra el archivo");
            }
        }
    }
    
    public void CargarDatosGuardados(){
        
        fieldArchivo.setText(archivo);
        
        try{
                
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("select * from " + archivo.toLowerCase());
                
            ResultSet rs = pst.executeQuery();
            
            ArrayList<Integer> id = new ArrayList<Integer>();
  
            int count = 0;
            while(rs.next()){
                id.add(Integer.parseInt(rs.getString("ID")));
                count++;
            }
            
            PreparedStatement pst2 = (PreparedStatement) cn.prepareStatement("select * from " + archivo.toLowerCase() + " where ID = ?");
            
            for(int i = 0; i < count; i++){
                String dei = "";
                    
                dei = Integer.toString(id.get(i));
                pst2.setString(1, dei);
                    
                ResultSet rs2 = pst2.executeQuery();

                if(rs2.next()){
                    field1Guardar[i].setText(rs2.getString("concepto"));
                    field2Guardar[i].setText(rs2.getString("respuesta"));
                } else{
                    JOptionPane.showMessageDialog(null, "Conexión no exitosa");
                }
            }
        }catch(Exception e){}
    }
    
    public void reiniciar(){
        for(int i = 0; i < 10; i++){
            field1[i].setLocation(100, 60 + (60 * i));
            field2[i].setLocation(750, 60 + (60 * i));
            field3[i].setLocation(680, 60 + (60 * i));
        }

        for(int i = 0; i < 10; i++){
            checkVerde[i].setVisible(false);
            checkRojo[i].setVisible(false);
            field3[i].setText("");
        }
    }
    
    public void guardar(){
        
        boolean campoVacio = false;
        boolean nombreVacio = false;
        
        if(fieldArchivo.getText().trim().equals("")){
            nombreVacio = true;
        }
        
        for(int i = 0; i < 10; i++){
            if(field1Guardar[i].getText().trim().equals("") || field2Guardar[i].getText().trim().equals("")){
                campoVacio = true;
            }
        }
        
        if(nombreVacio == false && campoVacio == false){
            try{
                Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
                PreparedStatement pst = (PreparedStatement) cn.prepareStatement("CREATE TABLE " + fieldArchivo.getText().trim() + " (ID INT AUTO_INCREMENT PRIMARY KEY, concepto TEXT, respuesta TEXT)");
                pst.execute();
                pst.close();
                cn.close();
                
                Connection cn2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
                PreparedStatement pst2 = (PreparedStatement) cn2.prepareStatement("insert into " + fieldArchivo.getText() + " values(?,?,?)");
                
                for(int i = 0; i < 10; i++){
                    pst2.setString(1, "0");
                    pst2.setString(2, field1Guardar[i].getText().trim());
                    pst2.setString(3, field2Guardar[i].getText().trim());
                    pst2.executeUpdate();
                }
                
                Connection cn3 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
                PreparedStatement pst3 = (PreparedStatement) cn3.prepareStatement("insert into archivos values(?,?)");
                
                pst3.setString(1, "0");
                pst3.setString(2, fieldArchivo.getText().trim());
                pst3.executeUpdate();

                pst3.close();
                cn3.close();
                cn2.close();
                pst2.close();
            }catch(Exception e){}    
        } else{
            if(campoVacio == true){
                JOptionPane.showMessageDialog(null, "Por favor llena todos los campos");
            }
            if(nombreVacio == true){
                JOptionPane.showMessageDialog(null, "Por favor nombra el archivo");
            }
        }
        /////////////////////////////////////////////////
        try {
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from archivos");
            
            registros = 0;
            while (rs.next()) {
                registros++;
            }
            
        } catch(Exception e){} 
        

        try {
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from archivos");
            
            int i = 0;
            while(rs.next()){
                ItemArchivoCargar.add(new JMenuItem());
                cargar.add(ItemArchivoCargar.get(i));
                ItemArchivoCargar.get(i).setText(rs.getString("archivo"));
                i++;
            }
            
        } catch(Exception e){}
        
        ActionListener evento = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < registros; i++){
            
                    try {
                        Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
                        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("SELECT * FROM " + ItemArchivoCargar.get(i).getText().toLowerCase());
                        ResultSet rs = pst.executeQuery();
            
                        int count = 0;
                        while (rs.next()) {
                            count++;
                        } 
            
                        Integer countArr[] = new Integer[count];
            
                        Connection cn2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
                        PreparedStatement pst2 = (PreparedStatement) cn2.prepareStatement("SELECT * FROM " + ItemArchivoCargar.get(i).getText().toLowerCase());
                        ResultSet rs2 = pst2.executeQuery();
            
                        Integer id[] = new Integer[count];
            
                        int count2 = 0;
                        int j = 0;
                        while (rs2.next()) {
                            id[count2] = Integer.parseInt(rs2.getString("ID"));
                            countArr[j] = count2;
                            j++;
                            count2++;
                        } 
            
                        int arr[] = new int[10];
                        arr[0] = (int)(Math.random()*count);
        
                        for(int k = 1; k < 10; k++){
                            arr[k] = (int)(Math.random()*count);
                            for(int r = 0; r < k; r++){
                                if(arr[k] == arr[r]){
                                    k--;
                                }
                            }
                        }
            
                        Connection cn3 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
                        PreparedStatement pst3 = (PreparedStatement) cn3.prepareStatement("select * from " + ItemArchivoCargar.get(i).getText().toLowerCase() + " where ID = ?");
            
                        for(int m = 0; m < count; m++){
                            for(int r = 0; r < count; r++){
                                if(arr[m] == countArr[r]){
                                    try{
                                        String dei = "";
                    
                                        dei = Integer.toString(id[arr[m]]);
                                        pst3.setString(1, dei);
                    
                                        ResultSet rs3 = pst3.executeQuery();

                                        if(rs3.next()){
                                            field1[m].setText(rs3.getString("concepto"));
                                            field2[m].setText(rs3.getString("respuesta"));
                                        } else{
                                            JOptionPane.showMessageDialog(null, "Conexión no exitosa");
                                        }
                                    }catch(Exception q){}
                                }
                            }
                        }
            
                        pst.close();
                        cn.close();
                        pst2.close();
                        cn2.close();
                        pst3.close();
                        cn3.close();
            
                    } catch (Exception n) {}
                }
            }   
        };
        
        for(int i = 0; i < registros; i++){
            ItemArchivoCargar.get(i).addActionListener(evento);
        }
    }
    
    public void alternarDatos(){
        
        try {
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("SELECT * FROM conceptos");
            ResultSet rs = pst.executeQuery();
            
            int count = 0;
            while (rs.next()) {
                count++;
            } 
            
            Integer countArr[] = new Integer[count];
            
            Connection cn2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
            PreparedStatement pst2 = (PreparedStatement) cn2.prepareStatement("SELECT * FROM conceptos");
            ResultSet rs2 = pst2.executeQuery();
            
            Integer id[] = new Integer[count];
            
            int count2 = 0;
            int i = 0;
            while (rs2.next()) {
                id[count2] = Integer.parseInt(rs2.getString("ID"));
                countArr[i] = count2;
                i++;
                count2++;
            } 
            
            int arr[] = new int[10];
            arr[0] = (int)(Math.random()*count);
        
            for(int k = 1; k < 10; k++){
                arr[k] = (int)(Math.random()*count);
                for(int j = 0; j < k; j++){
                    if(arr[k] == arr[j]){
                        k--;
                    }
                }
            }
            
            Connection cn3 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            PreparedStatement pst3 = (PreparedStatement) cn3.prepareStatement("select * from conceptos where ID = ?");
            
            for(int m = 0; m < count; m++){
                for(int j = 0; j < count; j++){
                    if(arr[m] == countArr[j]){
                        try{
                            String dei = "";
                    
                            dei = Integer.toString(id[arr[m]]);
                            pst3.setString(1, dei);
                    
                            ResultSet rs3 = pst3.executeQuery();

                            if(rs3.next()){
                                field1[m].setText(rs3.getString("concepto"));
                                field2[m].setText(rs3.getString("respuesta"));
                            } else{
                                JOptionPane.showMessageDialog(null, "Conexión no exitosa");
                            }
                        }catch(Exception r){}
                    }
                }
            }
            
            pst.close();
            cn.close();
            pst2.close();
            cn2.close();
            pst3.close();
            cn3.close();
            
        } catch (Exception e) {}
    }
    
    public void VentanaGuardar(){
        JFrame ventana = new JFrame();
        ventana.setLayout(null);
        ventana.setBounds(0,0,900,700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        
        RoundLineBorder2 r2 = new RoundLineBorder2(Color.lightGray.darker(),true);
        
        JComboBox combobox = new JComboBox();
        combobox.setBounds(225,40,50,30);
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
        txtCantidad.setBounds(50, 40, 200, 30);
        txtCantidad.setFont(new Font("Arial", Font.BOLD, 14));
        txtCantidad.setForeground(Color.white);
        
        JLabel txtArchivo = new JLabel("Nombre de archivo:");
        txtArchivo.setBounds(400, 40, 200, 30);
        txtArchivo.setFont(new Font("Arial", Font.BOLD, 14));
        txtArchivo.setForeground(Color.white);
        
        fieldArchivo.setBounds(545,38,200,35);
        fieldArchivo.setVisible(true);    
        fieldArchivo.setBorder(r2);
        fieldArchivo.setBorder(BorderFactory.createCompoundBorder(fieldArchivo.getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 5))); 
        
        JPanel botonPanelGuardar = new JPanel();
        JLabel txtBotonPanelGuardar = new JLabel("Guardar"); 
        
        for(int i = 0, k = 120; i < 10; i++, k+=45){
            field1Guardar[i] = new JTextField();
            field1Guardar[i].setBounds(30,k,300,35);
            field1Guardar[i].setVisible(true);
            
            field1Guardar[i].setBorder(r2);
            field1Guardar[i].setBorder(BorderFactory.createCompoundBorder(field1Guardar[i].getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 5)));
            
            ventana.add(field1Guardar[i]);
        }
        
        for(int i = 0, k = 120; i < 10; i++, k+=45){
            field2Guardar[i] = new JTextField();
            field2Guardar[i].setBounds(360,k,500,35);
            field2Guardar[i].setVisible(true);
            
            field2Guardar[i].setBorder(r2);
            field2Guardar[i].setBorder(BorderFactory.createCompoundBorder(field2Guardar[i].getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 5)));
            
            ventana.add(field2Guardar[i]);
        }
        
        txtBotonPanelGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        txtBotonPanelGuardar.setForeground(Color.white);
        
        botonPanelGuardar.setBounds(370, 600, 120, 40);
        botonPanelGuardar.setBackground(new Color(209,97,134));
        botonPanelGuardar.setLayout(new GridBagLayout());
        botonPanelGuardar.setOpaque(true);
        botonPanelGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardar();
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
    
    public void VentanaModificar(){
        JFrame ventana = new JFrame();
        ventana.setLayout(null);
        ventana.setBounds(0,0,900,700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        
        RoundLineBorder2 r2 = new RoundLineBorder2(Color.lightGray.darker(),true);
        
        JComboBox combobox = new JComboBox();
        combobox.setBounds(225,40,50,30);
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
        txtCantidad.setBounds(50, 40, 200, 30);
        txtCantidad.setFont(new Font("Arial", Font.BOLD, 14));
        txtCantidad.setForeground(Color.white);
        
        JLabel txtArchivo = new JLabel("Nombre de archivo:");
        txtArchivo.setBounds(400, 40, 200, 30);
        txtArchivo.setFont(new Font("Arial", Font.BOLD, 14));
        txtArchivo.setForeground(Color.white);
        
        fieldArchivo.setBounds(545,38,200,35);
        fieldArchivo.setVisible(true);    
        fieldArchivo.setBorder(r2);
        fieldArchivo.setBorder(BorderFactory.createCompoundBorder(fieldArchivo.getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 5))); 
        
        JPanel botonPanelGuardar = new JPanel();
        JLabel txtBotonPanelGuardar = new JLabel("Modificar"); 
        
        for(int i = 0, k = 120; i < 10; i++, k+=45){
            field1Guardar[i] = new JTextField();
            field1Guardar[i].setBounds(30,k,300,35);
            field1Guardar[i].setVisible(true);
            
            field1Guardar[i].setBorder(r2);
            field1Guardar[i].setBorder(BorderFactory.createCompoundBorder(field1Guardar[i].getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 5)));
            
            ventana.add(field1Guardar[i]);
        }
        
        for(int i = 0, k = 120; i < 10; i++, k+=45){
            field2Guardar[i] = new JTextField();
            field2Guardar[i].setBounds(360,k,500,35);
            field2Guardar[i].setVisible(true);
            
            field2Guardar[i].setBorder(r2);
            field2Guardar[i].setBorder(BorderFactory.createCompoundBorder(field2Guardar[i].getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 5)));
            
            ventana.add(field2Guardar[i]);
        }
        
        txtBotonPanelGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        txtBotonPanelGuardar.setForeground(Color.white);
        
        botonPanelGuardar.setBounds(370, 600, 120, 40);
        botonPanelGuardar.setBackground(new Color(209,97,134));
        botonPanelGuardar.setLayout(new GridBagLayout());
        botonPanelGuardar.setOpaque(true);
        botonPanelGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificar();
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
    
    public static void main(String[] args) {
        
        Pareo pareo = new Pareo();
   
    }
    
}
