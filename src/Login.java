
    import Metodos.Metodos;
    import Metodos.Variables;
    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;

    /*
     * Created by JFormDesigner on Wed Apr 29 09:07:58 CDT 2020
     */

    /**
     * @author Sergio EG
     */

    public class Login extends JFrame
    {
        public Login()
        {
            initComponents();
            Variables.EstoyEnLogin = true;

        }

        private void BT_IngresarActionPerformed(ActionEvent e)
        {
            if(Metodos.ValidarUsuario(User_Field.getText(), Pass_Field.getText()))
            {
                this.dispose();
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run()
                    {
                        new Principal().setVisible(true);
                    }
                });
            }
            else
            {
                User_Field.setText("");
                Pass_Field.setText("");
            }
        }

        private void BT_RegistrarActionPerformed(ActionEvent e)
        {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run()
                {
                    new RegistroUsuario().setVisible(true);
                }
            });
        }

        private void User_FieldKeyTyped(KeyEvent e)
        {
            char Caracter = e.getKeyChar();
            if((Caracter < '0' || Caracter > '9'))
            {
                e.consume();
            }
        }

        private void Bt_ConfiguracionActionPerformed(ActionEvent e)
        {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run()
                {
                    new Configuraciones().setVisible(true);
                }
            });
        }

        private void thisWindowClosing(WindowEvent e)
        {
            this.dispose();
            Variables.NodoListaDobleBloques.SalirNodoRedListaDoble();
            Variables.NodoListaDobleBloques.stop();
            System.exit(0);
        }

        private void initComponents()
        {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            User_Field = new JTextField();
            BT_Ingresar = new JButton();
            Pass_Field = new JPasswordField();
            BT_Registrar = new JButton();
            label1 = new JLabel();
            label2 = new JLabel();
            label3 = new JLabel();
            label4 = new JLabel();
            Bt_Configuracion = new JButton();

            //======== this ========
            setTitle("Ingreso");
            setFont(new Font("Arial", Font.BOLD, 14));
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setResizable(false);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    thisWindowClosing(e);
                }
            });
            Container contentPane = getContentPane();
            contentPane.setLayout(null);

            //---- User_Field ----
            User_Field.setFont(new Font("Arial", Font.BOLD, 16));
            User_Field.setForeground(new Color(102, 102, 255));
            User_Field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    User_FieldKeyTyped(e);
                }
            });
            contentPane.add(User_Field);
            User_Field.setBounds(260, 150, 195, 35);

            //---- BT_Ingresar ----
            BT_Ingresar.setText("Ingresar");
            BT_Ingresar.setForeground(new Color(0, 153, 255));
            BT_Ingresar.setFont(new Font("Arial", Font.BOLD, 16));
            BT_Ingresar.addActionListener(e -> BT_IngresarActionPerformed(e));
            contentPane.add(BT_Ingresar);
            BT_Ingresar.setBounds(new Rectangle(new Point(175, 285), BT_Ingresar.getPreferredSize()));

            //---- Pass_Field ----
            Pass_Field.setFont(new Font("Arial", Font.BOLD, 16));
            Pass_Field.setForeground(new Color(102, 102, 255));
            contentPane.add(Pass_Field);
            Pass_Field.setBounds(260, 210, 195, 35);

            //---- BT_Registrar ----
            BT_Registrar.setText("Registrarse");
            BT_Registrar.setForeground(new Color(0, 153, 255));
            BT_Registrar.setFont(new Font("Arial", Font.BOLD, 16));
            BT_Registrar.addActionListener(e -> BT_RegistrarActionPerformed(e));
            contentPane.add(BT_Registrar);
            BT_Registrar.setBounds(new Rectangle(new Point(305, 285), BT_Registrar.getPreferredSize()));
            contentPane.add(label1);
            label1.setBounds(570, 380, 20, 45);

            //---- label2 ----
            label2.setText("Carnet:");
            label2.setFont(new Font("Arial", Font.BOLD, 18));
            label2.setForeground(new Color(0, 153, 255));
            contentPane.add(label2);
            label2.setBounds(new Rectangle(new Point(145, 155), label2.getPreferredSize()));

            //---- label3 ----
            label3.setText("Contrase\u00f1a:");
            label3.setForeground(new Color(0, 153, 255));
            label3.setFont(new Font("Arial", Font.BOLD, 18));
            contentPane.add(label3);
            label3.setBounds(new Rectangle(new Point(145, 215), label3.getPreferredSize()));

            //---- label4 ----
            label4.setText("Ingreso De Usuarios");
            label4.setFont(new Font("Arial", Font.BOLD, 20));
            label4.setForeground(new Color(102, 102, 255));
            contentPane.add(label4);
            label4.setBounds(new Rectangle(new Point(200, 70), label4.getPreferredSize()));

            //---- Bt_Configuracion ----
            Bt_Configuracion.setText("Configuraci\u00f3n");
            Bt_Configuracion.setFont(new Font("Arial", Font.BOLD, 16));
            Bt_Configuracion.setForeground(new Color(0, 153, 255));
            Bt_Configuracion.addActionListener(e -> Bt_ConfiguracionActionPerformed(e));
            contentPane.add(Bt_Configuracion);
            Bt_Configuracion.setBounds(new Rectangle(new Point(410, 15), Bt_Configuracion.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < contentPane.getComponentCount(); i++) {
                    Rectangle bounds = contentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = contentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                contentPane.setMinimumSize(preferredSize);
                contentPane.setPreferredSize(preferredSize);
            }
            pack();
            setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        private JTextField User_Field;
        private JButton BT_Ingresar;
        private JPasswordField Pass_Field;
        private JButton BT_Registrar;
        private JLabel label1;
        private JLabel label2;
        private JLabel label3;
        private JLabel label4;
        private JButton Bt_Configuracion;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    }
