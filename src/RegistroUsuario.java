

    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
    import Metodos.Variables;
    import Metodos.Metodos;

    /*
     * Created by JFormDesigner on Wed Apr 29 09:36:35 CDT 2020
     */

    /**
     * @author Sergio EG
     */

    public class RegistroUsuario extends JFrame
    {
        public RegistroUsuario()
        {
            initComponents();
        }

        private void Carnet_FieldKeyTyped(KeyEvent e)
        {
            char Caracter = e.getKeyChar();

            if((Caracter < '0' || Caracter > '9'))
            {
                e.consume();
            }
        }

        private void Nombre_FieldKeyTyped(KeyEvent e)
        {
            char Caracter = e.getKeyChar();

            if (!(Character.isLetter(Caracter) || Character.isSpaceChar(Caracter)))
            {
                e.consume();
            }
        }

        private void Apellido_FieldKeyTyped(KeyEvent e)
        {
            char Caracter = e.getKeyChar();

            if (!(Character.isLetter(Caracter) || Character.isSpaceChar(Caracter)))
            {
                e.consume();
            }
        }

        private void Carrera_FieldKeyTyped(KeyEvent e)
        {
            char Caracter = e.getKeyChar();

            if (!(Character.isLetter(Caracter) || Character.isSpaceChar(Caracter)))
            {
                e.consume();
            }
        }

        private void Bt_RegresarActionPerformed(ActionEvent e)
        {
            if(Variables.EstoyEnLogin)
            {
                this.dispose();
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run()
                    {
                        new Login().setVisible(true);
                    }
                });
            }
            else
            {
                this.dispose();
            }

        }

        private void Bt_RegistrarActionPerformed(ActionEvent e)
        {
            int Carnet = Integer.parseInt(Carnet_Field.getText());

            if(Metodos.RegistrarUsuario(Carnet, Nombre_Field.getText(), Apellido_Field.getText(), Carrera_Field.getText(), Contraseña_Field.getText()))
            {
                if(Variables.EstoyEnLogin)
                {
                    int Button = JOptionPane.YES_NO_OPTION;
                    int Result = JOptionPane.showConfirmDialog(this, "Desea Agregar Otro Usuario?", "Pregunta?", Button);
                    if(Result == 0)
                    {
                        Carnet_Field.setText("");
                        Nombre_Field.setText("");
                        Apellido_Field.setText("");
                        Carrera_Field.setText("");
                        Contraseña_Field.setText("");
                    }
                    else
                    {
                        this.dispose();
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run()
                            {
                                new Login().setVisible(true);
                            }
                        });
                    }
                }
                else
                {
                    Carnet_Field.setText("");
                    Nombre_Field.setText("");
                    Apellido_Field.setText("");
                    Carrera_Field.setText("");
                    Contraseña_Field.setText("");
                }
            }
            else
            {
                Carnet_Field.setText("");
                Nombre_Field.setText("");
                Apellido_Field.setText("");
                Carrera_Field.setText("");
                Contraseña_Field.setText("");
            }
        }

        private void initComponents()
        {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            Carnet_Field = new JTextField();
            Nombre_Field = new JTextField();
            Apellido_Field = new JTextField();
            Carrera_Field = new JTextField();
            Contraseña_Field = new JPasswordField();
            label1 = new JLabel();
            label2 = new JLabel();
            label3 = new JLabel();
            label4 = new JLabel();
            label5 = new JLabel();
            Bt_Registrar = new JButton();
            label6 = new JLabel();
            label7 = new JLabel();
            Bt_Regresar = new JButton();

            //======== this ========
            setTitle("Registro Usuario");
            Container contentPane = getContentPane();
            contentPane.setLayout(null);

            //---- Carnet_Field ----
            Carnet_Field.setFont(new Font("Arial", Font.BOLD, 16));
            Carnet_Field.setForeground(new Color(102, 102, 255));
            Carnet_Field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    Carnet_FieldKeyTyped(e);
                }
            });
            contentPane.add(Carnet_Field);
            Carnet_Field.setBounds(300, 130, 190, 35);

            //---- Nombre_Field ----
            Nombre_Field.setFont(new Font("Arial", Font.BOLD, 16));
            Nombre_Field.setForeground(new Color(102, 102, 255));
            Nombre_Field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    Nombre_FieldKeyTyped(e);
                }
            });
            contentPane.add(Nombre_Field);
            Nombre_Field.setBounds(300, 185, 190, 35);

            //---- Apellido_Field ----
            Apellido_Field.setForeground(new Color(102, 102, 255));
            Apellido_Field.setFont(new Font("Arial", Font.BOLD, 16));
            Apellido_Field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    Apellido_FieldKeyTyped(e);
                }
            });
            contentPane.add(Apellido_Field);
            Apellido_Field.setBounds(300, 240, 190, 35);

            //---- Carrera_Field ----
            Carrera_Field.setForeground(new Color(102, 102, 255));
            Carrera_Field.setFont(new Font("Arial", Font.BOLD, 16));
            Carrera_Field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    Carrera_FieldKeyTyped(e);
                }
            });
            contentPane.add(Carrera_Field);
            Carrera_Field.setBounds(300, 290, 190, 35);

            //---- Contraseña_Field ----
            Contraseña_Field.setForeground(new Color(102, 102, 255));
            Contraseña_Field.setFont(new Font("Arial", Font.BOLD, 16));
            contentPane.add(Contraseña_Field);
            Contraseña_Field.setBounds(300, 340, 190, 35);

            //---- label1 ----
            label1.setText("Carnet:");
            label1.setForeground(new Color(0, 153, 255));
            label1.setFont(new Font("Arial", Font.BOLD, 18));
            contentPane.add(label1);
            label1.setBounds(new Rectangle(new Point(165, 135), label1.getPreferredSize()));

            //---- label2 ----
            label2.setText("Nombre:");
            label2.setForeground(new Color(0, 153, 255));
            label2.setFont(new Font("Arial", Font.BOLD, 18));
            contentPane.add(label2);
            label2.setBounds(new Rectangle(new Point(165, 190), label2.getPreferredSize()));

            //---- label3 ----
            label3.setText("Apellido:");
            label3.setForeground(new Color(0, 153, 255));
            label3.setFont(new Font("Arial", Font.BOLD, 18));
            contentPane.add(label3);
            label3.setBounds(new Rectangle(new Point(165, 245), label3.getPreferredSize()));

            //---- label4 ----
            label4.setText("Carrera:");
            label4.setForeground(new Color(0, 153, 255));
            label4.setFont(new Font("Arial", Font.BOLD, 18));
            contentPane.add(label4);
            label4.setBounds(new Rectangle(new Point(165, 295), label4.getPreferredSize()));

            //---- label5 ----
            label5.setText("Contrase\u00f1a:");
            label5.setForeground(new Color(0, 153, 255));
            label5.setFont(new Font("Arial", Font.BOLD, 18));
            contentPane.add(label5);
            label5.setBounds(new Rectangle(new Point(165, 345), label5.getPreferredSize()));

            //---- Bt_Registrar ----
            Bt_Registrar.setText("Registrar");
            Bt_Registrar.setForeground(new Color(0, 153, 255));
            Bt_Registrar.setFont(new Font("Arial", Font.BOLD, 18));
            Bt_Registrar.addActionListener(e -> Bt_RegistrarActionPerformed(e));
            contentPane.add(Bt_Registrar);
            Bt_Registrar.setBounds(190, 405, 125, 40);

            //---- label6 ----
            label6.setText("Registro De Usuarios");
            label6.setFont(new Font("Arial", Font.BOLD, 20));
            label6.setForeground(new Color(102, 102, 255));
            contentPane.add(label6);
            label6.setBounds(new Rectangle(new Point(220, 65), label6.getPreferredSize()));
            contentPane.add(label7);
            label7.setBounds(610, 465, 30, 35);

            //---- Bt_Regresar ----
            Bt_Regresar.setText("Regresar");
            Bt_Regresar.setFont(new Font("Arial", Font.BOLD, 18));
            Bt_Regresar.setForeground(new Color(0, 153, 255));
            Bt_Regresar.addActionListener(e -> Bt_RegresarActionPerformed(e));
            contentPane.add(Bt_Regresar);
            Bt_Regresar.setBounds(360, 405, 120, 40);

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
        private JTextField Carnet_Field;
        private JTextField Nombre_Field;
        private JTextField Apellido_Field;
        private JTextField Carrera_Field;
        private JPasswordField Contraseña_Field;
        private JLabel label1;
        private JLabel label2;
        private JLabel label3;
        private JLabel label4;
        private JLabel label5;
        private JButton Bt_Registrar;
        private JLabel label6;
        private JLabel label7;
        private JButton Bt_Regresar;
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }
