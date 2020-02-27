
package pareo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static pareo.Pareo.ItemArchivoCargar;
import static pareo.Pareo.ItemArchivoModificar;
import static pareo.Pareo.cargar;
import static pareo.Pareo.modificar;
import static pareo.Pareo.field1;
import static pareo.Pareo.field1Guardar;
import static pareo.Pareo.field2;
import static pareo.Pareo.field2Guardar;
import static pareo.Pareo.fieldArchivo;

public class BackEnd {
    
    int registros = 0;
    String archivoSeleccionado = "";
    String archivo = "";
    
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
    
    public void CargarItems(){
        
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
                cargar.add(Pareo.ItemArchivoCargar.get(i));
                modificar.add(Pareo.ItemArchivoModificar.get(i));
                i++;
            }
            
        } catch(Exception e){}
    }
    
    
    public void EventoCargarDatos(){
        
        ActionListener eventoCargar = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < registros; i++){
                    if(e.getSource() == ItemArchivoCargar.get(i)){
                        try {
                        Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
                        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("SELECT * FROM " + Pareo.ItemArchivoCargar.get(i).getText().toLowerCase());
                        ResultSet rs = pst.executeQuery();
            
                        int count = 0;
                        while (rs.next()) {
                            count++;
                        } 
            
                        Integer countArr[] = new Integer[count];
            
                        Connection cn2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
            
                        PreparedStatement pst2 = (PreparedStatement) cn2.prepareStatement("SELECT * FROM " + Pareo.ItemArchivoCargar.get(i).getText().toLowerCase());
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
                        PreparedStatement pst3 = (PreparedStatement) cn3.prepareStatement("select * from " + Pareo.ItemArchivoCargar.get(i).getText().toLowerCase() + " where ID = ?");
            
                        for(int m = 0; m < count; m++){
                            for(int r = 0; r < count; r++){
                                if(arr[m] == countArr[r]){
                                    try{
                                        String dei = "";
                    
                                        dei = Integer.toString(id[arr[m]]);
                                        pst3.setString(1, dei);
                    
                                        ResultSet rs3 = pst3.executeQuery();

                                        if(rs3.next()){
                                            Pareo.field1[m].setText(rs3.getString("concepto"));
                                            Pareo.field2[m].setText(rs3.getString("respuesta"));
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
            }  
        };
        for(int i = 0; i < registros; i++){
            Pareo.ItemArchivoCargar.get(i).addActionListener(eventoCargar);
        }
    }
    
    public void EventoModificarDatos(){
        ActionListener eventoModificar = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < registros; i++){
                    if(e.getSource() == ItemArchivoModificar.get(i)){
                        archivo = ItemArchivoModificar.get(i).getText();
                    }
                }
                //Pareo.pareo.VentanaModificar();
                CargarDatosGuardados();
            }  
        };
        for(int i = 0; i < registros; i++){
            ItemArchivoModificar.get(i).addActionListener(eventoModificar);
        }
    }
    
    public void modificar(){
        boolean campoVacio = false;
        boolean nombreVacio = false;
        
        if(Pareo.fieldArchivo.getText().trim().equals("")){
            nombreVacio = true;
        }
        
        for(int i = 0; i < 10; i++){
            if(Pareo.field1Guardar[i].getText().trim().equals("") || Pareo.field2Guardar[i].getText().trim().equals("")){
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
                
                    pst3.setString(1, Pareo.field1Guardar[i].getText().trim());
                    pst3.setString(2, Pareo.field2Guardar[i].getText().trim());
                    pst3.executeUpdate();
                }
                
                Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
                PreparedStatement pst = (PreparedStatement) cn.prepareStatement("rename table " + archivo.toLowerCase() + " to " + Pareo.fieldArchivo.getText());
                pst.executeUpdate();

                Connection cn4 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/conceptos_bd", "root", "");
                PreparedStatement pst4 = (PreparedStatement) cn4.prepareStatement("update archivos set archivo=? where archivo = '" + archivo + "'");
                
                pst4.setString(1, Pareo.fieldArchivo.getText().trim());
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
    
    //Carga los datos guardados a ventana modificar
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
}

