import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NewJFrame extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;

    // UI Components (Assumed to be defined via GUI builder or manually)
    private JTextField txtId, txtName, txtEmail, txtPhone, txtAddress, txtProduct, txtQuantity, txtPrice;
    private JTable table1;
    private com.toedter.calendar.JDateChooser Date_Chooser;

    public NewJFrame() {
        initComponents(); // Assuming this initializes the above components
        Connect();
        ShowData();
    }

    public void Connect() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/your_database", "root", "nitin");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ShowData() {
        try {
            pst = con.prepareStatement("SELECT * FROM invoice");
            ResultSet Rs = pst.executeQuery();
            ResultSetMetaData RSMD = Rs.getMetaData();
            int columnCount = RSMD.getColumnCount();

            DefaultTableModel DFG = (DefaultTableModel) table1.getModel();
            DFG.setRowCount(0);

            while (Rs.next()) {
                Vector<String> v2 = new Vector<>();
                v2.add(Rs.getString("clientid"));
                v2.add(Rs.getString("clientname"));
                v2.add(Rs.getString("email"));
                v2.add(Rs.getString("phone"));
                v2.add(Rs.getString("address"));
                v2.add(Rs.getString("product"));
                v2.add(Rs.getString("quantity"));
                v2.add(Rs.getString("price"));
                v2.add(Rs.getString("datecreated"));
                DFG.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertData() {
        try {
            String clientid = txtId.getText();
            String clientname = txtName.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText();
            String address = txtAddress.getText();
            String product = txtProduct.getText();
            String quantity = txtQuantity.getText();
            String price = txtPrice.getText();

            pst = con.prepareStatement("INSERT INTO invoice (clientid, clientname, email, phone, address, product, quantity, price, datecreated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pst.setString(1, clientid);
            pst.setString(2, clientname);
            pst.setString(3, email);
            pst.setString(4, phone);
            pst.setString(5, address);
            pst.setString(6, product);
            pst.setString(7, quantity);
            pst.setString(8, price);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String datecreated = sdf.format(Date_Chooser.getDate());
            pst.setString(9, datecreated);

            int a = pst.executeUpdate();

            if (a == 1) {
                JOptionPane.showMessageDialog(this, "Record Inserted Successfully");
                clearFields();
                ShowData();
            } else {
                JOptionPane.showMessageDialog(this, "Error, Please Try Again");
            }

        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteData() {
        try {
            String clientid = txtId.getText();
            pst = con.prepareStatement("DELETE FROM invoice WHERE clientid = ?");
            pst.setString(1, clientid);

            int k = pst.executeUpdate();

            if (k == 1) {
                JOptionPane.showMessageDialog(this, "Record Deleted Successfully");
                clearFields();
                ShowData();
            } else {
                JOptionPane.showMessageDialog(this, "Error, Please try again");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtProduct.setText("");
        txtQuantity.setText("");
        txtPrice.setText("");
        Date_Chooser.setDate(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new NewJFrame().setVisible(true);
        });
    }
    
}
