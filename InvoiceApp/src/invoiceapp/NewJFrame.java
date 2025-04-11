package invoiceapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.*;               // JFrame, JPanel, JButton, etc.
import java.awt.*;                  // BorderLayout, GridLayout, etc.
import java.awt.event.*;           // ActionListener, etc
import java.sql.*;                 // DB access
import java.util.Vector;          // For dynamic table rows
import java.text.SimpleDateFormat; // Date formatting
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSetMetaData;        // for ResultSetMetaData


public NewJFrame {
        initComponents();
        Connect();
        ShowData();
    }
    
    Connection con;
    PreparedStatement pst;




    public void Connect(){
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/invoicedb","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }



      private void ShowData(){
    
        try {
            int QQ;
            pst = con.prepareStatement("SELECT * FROM invoice");
            ResultSet Rs = pst.executeQuery();
            
            ResultSetMetaData RSMD = Rs.getMetaData();

            QQ = RSMD.getColumnCount();
            
            DefaultTableModel DFG =(DefaultTableModel)table1.getModel(); 
            
            DFG.setRowCount(0);
             
            while(Rs.next()){
        
            Vector v2 = new Vector();
             
            for(int aa=1; aa<=QQ; aa++){
                 
                v2.add(Rs.getString("clientid"));
                v2.add(Rs.getString("clientname"));
                v2.add(Rs.getString("email"));
                v2.add(Rs.getString("phone"));
                v2.add(Rs.getString("address"));
                v2.add(Rs.getString("product"));
                v2.add(Rs.getString("quantity"));
                v2.add(Rs.getString("price"));
                v2.add(Rs.getString("datecreated"));
             }
             
             DFG.addRow(v2);
        
        }
          
        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }




      Insert Code



       try {
            String clientid = txtId.getText();
            String clientname = txtName.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText();
            String address = txtAddress.getText();
            String product = txtProduct.getText();
            String quantity = txtQuantity.getText();
            String price = txtPrice.getText();
            
            pst = con.prepareStatement("INSERT INTO invoice (clientid,clientname,email,phone,address,product,quantity,price,datecreated)VALUES(?,?,?,?,?,?,?,?,?)");
            
             pst.setString(1,clientid);
             pst.setString(2,clientname);
             pst.setString(3,email);
             pst.setString(4,phone);
             pst.setString(5,address);
             pst.setString(6,product);
             pst.setString(7,quantity);
             pst.setString(8,price);
             
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String datecreated = sdf.format(Date_Chooser.getDate());
             pst.setString(9,datecreated);
             
             int a = pst.executeUpdate();
            
            
            if (a==1){
            
             JOptionPane.showMessageDialog(this,"Record Inserted Successfully");
             ShowData();
              
             txtId.setText("");
             txtName.setText("");
             txtEmail.setText("");
             txtPhone.setText("");
             txtAddress.setText("");
             txtProduct.setText("");
             txtQuantity.setText("");
             txtPrice.setText("");
              
            
        }else{
                
            JOptionPane.showMessageDialog(this,"Error, Please Try Again");         
                
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }                                         





       Delete Code


       try {
            String clientid = txtId.getText();
            pst=con.prepareStatement("DELETE FROM invoice WHERE clientid=?");
            pst.setString(1,clientid);
            
            int k = pst.executeUpdate();
            if(k==1){

             JOptionPane.showMessageDialog(this, "Record Deleted Successfully");
             ShowData();

            }else{

            JOptionPane.showMessageDialog(this, "Error, Please try again");

           }
        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 697, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    */
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    private void initComponents() {
        setTitle("Invoice Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Date_Chooser = new JDateChooser();
        add(Date_Chooser, BorderLayout.NORTH);

        // Add buttons, fields, table, etc.

        setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
