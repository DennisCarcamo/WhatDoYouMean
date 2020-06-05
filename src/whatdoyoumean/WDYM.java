package wdym;

import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author dejoc
 */
public class WDYM extends javax.swing.JFrame {

    String directorio = new File("").getAbsolutePath();
    String directorio2 = directorio.concat("\n Comandos disponibles: \n1- DD (clear screen)"
            + "                                               \n2- vi foldername (crear folder)"
            + "                                               \n3- ls (listar archivos y directorios)");
    
   //conexion a mySQL
    static Connection connection = null;
    static String databasename = "comands";
    static String url = "jdbc:mysql://localhost:3306/" + databasename;
    static String username = "root";
    static String password = "!Aml1020";

    boolean esperandoLS = false;
    boolean esperandoVI = false;
    boolean esperandoDD = false;

    //declaraciones regex
    String patronLS = "((l(w|a|d|s|x|z)|(j|m|k|o|l)s))"; // combinaciones posibles para el comando LS
    String patronDD = "((d(e|s|f|r|c|x|d)|(e|s|f|r|c|x|d)d))"; //combinaciones posibles para el comando DD
    String patronVI
            = "("
            + "(v(u|i|o|j|k|l))(\\s)[a-zA-Z0-9\\s]+" //combinaciones posibles con la v buena y el nombre del folder
            + "|"
            + "((c|f|g|b|v)i)(\\s)[a-zA-Z0-9\\s]+" //combinaciones posibles con la i buena  y el nombre del folder
            + ")";

    String patronViSinPArametro
            = "("
            + "((v|c|f|g|b)i)(\\s)*" //combinaciones posibles con la v buena
            + "|"
            + "(v(u|i|o|j|k|l))(\\s)*" //combinaciones posibles con la i buena
            + ")";
         

    ArrayList<String> comandos = new ArrayList<>();

    String LSInput = "";
    String VIinput = "";
    String DDinput = "";
    boolean ImprimirComandoMisc = true;

    public WDYM() throws SQLException {

        String query = ("SELECT comandoingresado FROM comands.comandos;");
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet response = preparedStatement.executeQuery();

        while (response.next()) {
            String comando = response.getString("comandoingresado");
            comandos.add(comando);
        }
        initComponents();
        Output.setText(directorio + ">");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Output = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        Input = new javax.swing.JTextArea();
        bt_clearCommands = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        Output.setEditable(false);
        Output.setBackground(new java.awt.Color(142, 65, 103));
        Output.setColumns(20);
        Output.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        Output.setForeground(new java.awt.Color(255, 255, 255));
        Output.setLineWrap(true);
        Output.setRows(5);
        jScrollPane1.setViewportView(Output);

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        Input.setBackground(new java.awt.Color(142, 65, 103));
        Input.setColumns(20);
        Input.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        Input.setForeground(new java.awt.Color(255, 255, 255));
        Input.setRows(5);
        Input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InputKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(Input);

        bt_clearCommands.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N
        bt_clearCommands.setForeground(new java.awt.Color(142, 65, 103));
        bt_clearCommands.setText("Clear Saved Commands");
        bt_clearCommands.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_clearCommandsActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Lucida Console", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("My Terminal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(415, 415, 415)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_clearCommands)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_clearCommands, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIresponseT:event_InputKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            ImprimirComandoMisc = true; //reset variable para imprimir lo que sea que no sea vi o dd o ls

            String value = Input.getText();

            if (esperandoLS) {
                if (value.trim().equals("y") || value.trim().equals("Y")) {  // guardamos el comando personalizado en la base de datos

                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO comandos(comandoingresado, comandoreal) VALUES ('" + LSInput + "','ls');");
                        int status = preparedStatement.executeUpdate();

                        if (status != 0) {
                            System.out.println("Comando " + LSInput + " agregado a la BD, referenciando a ls.");
                        }

                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                    esperandoLS = false;
                } else { //si se escogio n u otra cosa
                    esperandoLS = false;
                    Output.append("\n" + directorio + ">" + value.trim() + "\n");
                    ImprimirComandoMisc = false;
                    Input.setText("");
                }
            }

            if (esperandoVI) {
                if (value.trim().equals("y") || value.trim().equals("Y")) {  // agregamos a la base de datos

                    try {
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO comandos(comandoingresado, comandoreal) VALUES ('" + VIinput.trim() + "','vi');");
                        int status = ps.executeUpdate();

                        if (status != 0) {
                            System.out.println("Comando '" + VIinput + "' agregado a la BD, referenciando a vi.");
                        }

                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    esperandoVI = false;
                } else { //si ingreso n o cualquier otro caracter
                    esperandoVI = false;
                    Output.append("\n" + directorio + ">" + value.trim());
                    ImprimirComandoMisc = false;
                    Input.setText("");
                }
            }

            if (esperandoDD) {
                if (value.trim().equals("y") || value.trim().equals("Y")) {  // agregar a BD

                    try {
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO comandos(comandoingresado, comandoreal) VALUES ('" + DDinput.trim() + "','dd');");
                        int status = ps.executeUpdate();

                        if (status != 0) {
                            System.out.println("Comando '" + DDinput + "' agregado a la BD, referenciando a dd.");
                        }

                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    esperandoDD = false;
                } else { //si ingreso n o cualquier otro caracter
                    esperandoDD = false;
                    Output.append("\n" + directorio + ">" + value.trim());
                    ImprimirComandoMisc = false;
                    Input.setText("");
                }
            }

            if (value.trim().matches(patronLS)) { //encontro algo cerca de 'ls'
               
                if (!value.trim().equals("ls")) { //cualquier otra cosa que no es ls pero es cerca

                    ArrayList<String> search = new ArrayList<>();

                    try {
                        String query = ("SELECT * FROM comands.comandos WHERE comandoingresado = '" + value.trim() + "';");
                        PreparedStatement ps = connection.prepareStatement(query);
                        ResultSet rs = ps.executeQuery();

                        while (rs.next()) {
                            String comando = rs.getString("comandoingresado");
                            search.add(comando);
                        }

                        if (search.size() == 1) {
                            correrLS(value.trim());
                            ImprimirComandoMisc = false;
                        } else {
                            Output.append("\n" + directorio + ">" + value.trim() + "\n");
                            Output.append("Did you mean 'ls'? [y/n]" + "\n ...");
                            Input.setText("");
                            LSInput = value.trim();
                            esperandoLS = true; //esperando respuesta
                            ImprimirComandoMisc = false;
                        }

                    } catch (Exception e) {
                        e.toString();
                    }

                } else { //el comando ingresado ES ls
                    correrLS("ls");
                    ImprimirComandoMisc = false;
                }

            }

            if (value.trim().matches(patronDD)) { //encontro algo cerca de dd'
                
                if (!value.trim().equals("dd")) { //cualquier otra cosa que no es dd pero es cerca

                    ArrayList<String> search = new ArrayList<>();

                    try {
                        String query = ("SELECT * FROM comands.comandos WHERE comandoingresado = '" + value.trim() + "';");
                        PreparedStatement ps = connection.prepareStatement(query);
                        ResultSet rs = ps.executeQuery();

                        while (rs.next()) {
                            String comando = rs.getString("comandoingresado");
                            search.add(comando);
                        }

                        if (search.size() == 1) {
                            ClearByDD("clear");
                            ImprimirComandoMisc = false;
                        } else {
                            Output.append("\n" + directorio + ">" + value.trim() + "\n");
                            Output.append("Did you mean 'dd'? [y/n]" + "\n ...");
                            Input.setText("");
                            DDinput = value.trim();
                            esperandoDD = true; //esperando respuesta
                            ImprimirComandoMisc = false;
                        }

                    } catch (Exception e) {
                        e.toString();
                    }

                } else { //el comando ingresado es dd
                    ClearByDD("clear");
                    ImprimirComandoMisc = false;
                }

            }

            if (value.trim().matches(patronViSinPArametro)) { //encontro un vi sin parametro
                Output.append("\n" + directorio + ">" + "Falta parametro a vi, favor usar VI \"nombre de folder\" ");
                Input.setText("");
                ImprimirComandoMisc = false;
            } else {
                if (value.trim().matches(patronVI)) { //encontro mkdir

                    String[] split = value.trim().split(" ");

                    if (!split[0].trim().equals("vi")) { //cualquier otra cosa que no es vi pero es cerca

                        ArrayList<String> search = new ArrayList<>();

                        try {
                            String query = ("SELECT * FROM comands.comandos WHERE comandoingresado = '" + split[0].trim() + "';");
                            PreparedStatement ps = connection.prepareStatement(query);
                            ResultSet rs = ps.executeQuery();

                            while (rs.next()) {
                                String comando = rs.getString("comandoingresado");
                                search.add(comando);
                            }

                            if (search.size() == 1) {
                                CheckLength(split);
                                ImprimirComandoMisc = false;
                                Input.setText("");
                            } else {
                                Output.append("\n" + directorio + ">" + value.trim() + "\n");
                                Output.append("Did you mean vi \"FolderName\"'? [y/n]" + "\n ...");
                                Input.setText("");
                                VIinput = split[0];
                                esperandoVI = true; //esperando respuesta
                                ImprimirComandoMisc = false;

                            }

                        } catch (Exception e) {

                        }

                    } else { //si el comando vi esta correcto

                        CheckLength(split);

                    }
                }
            }
            
            if (ImprimirComandoMisc) { // cualquier otra cosa que se ha ingresado
                Output.append("\n" + directorio + ">" + value.trim());
                Input.setText("");
            }

            //cualquier otro comando o misc.
        }

    }

    private void ClearByDD(String value) {
        if (value.trim().equals("clear")) { //solo para limpiar
            Output.setText("");
            Output.setText(directorio + ">clear");
            Input.setText("");
        }
    }

    private void bt_clearCommandsActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("truncate table comands.comandos;");
            int status = preparedStatement.executeUpdate();

            if (status != 0) {
                System.out.println("Comandos eliminados de la Base de Datos");
            }
            int input = JOptionPane.showConfirmDialog(this, "Comandos Guardados Eliminados!", "Alert!", JOptionPane.DEFAULT_OPTION);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void CheckLength(String[] split) { //dividiendo vi del nombre del directorio
        if (split.length >= 3) { //revisar si el nombre es palabra o letras
            String foldername = "";

            for (int i = 1; i < split.length; i++) {
                foldername += split[i] + " ";
            }
            corrervi(foldername);
            Input.setText("");
            ImprimirComandoMisc = false;

        } else { //si el nombre es solo una palabra
            corrervi(split[1]);
            Input.setText("");
            ImprimirComandoMisc = false;
        }
    }

    public void correrLS(String value) {
        File folder = new File(directorio);
        File[] listOfFiles = folder.listFiles();

        Output.append(value.trim() + "\n");
        Input.setText("");

        for (File file : listOfFiles) {
            if (file.isDirectory()) {
                Output.append(file.getName() + "(folder)" + "\n");
            } else {
                Output.append(file.getName() + "\n");

            }
        }
        Output.append("\n" + directorio + ">");
    }

    public boolean corrervi(String value) {
        File f = new File(directorio + "/" + value);

        if (f.mkdir()) {

            System.out.println("Directorio Creado");
            Output.append("\n" + directorio + ">Directorio '" + value + "' fue creado exitosamente." + "\n");
            return true;
        } else {
            Output.append("\n" + directorio + ">Directorio '" + value + "' ya existe." + "\n");
            System.out.println("El Directorio no se pudo crear");
            return false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WDYM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WDYM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WDYM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WDYM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(url, username, password);
        try {
           
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new WDYM().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(WDYM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextArea Input;
    private javax.swing.JTextArea Output;
    private javax.swing.JButton bt_clearCommands;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;

}
