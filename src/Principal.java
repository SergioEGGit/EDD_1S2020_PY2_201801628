
     import java.awt.*;
     import java.awt.event.*;
     import javax.swing.*;
     import Metodos.Variables;
     import Metodos.LecturaJson;

     /*
      * Created by JFormDesigner on Mon Apr 27 16:33:26 CDT 2020
      */


     /**
      * @author Sergio EG
      */

     public class Principal extends JFrame
     {
         public Principal()
         {
             initComponents();
         }

         public static void main(String[] args)
         {
             java.awt.EventQueue.invokeLater(new Runnable() {
                 @Override
                 public void run()
                 {
                     new Login().setVisible(true);
                 }
             });
         }

         private void Bt_AgregarUsuariosActionPerformed(ActionEvent e)
         {
             java.awt.EventQueue.invokeLater(new Runnable() {
                 @Override
                 public void run()
                 {
                     new RegistroUsuario().setVisible(true);
                 }
             });
         }

         private void Bt_AgregarLibrosActionPerformed(ActionEvent e)
         {
             // TODO add your code here
         }

         private void Bt_LogoutActionPerformed(ActionEvent e)
         {
             this.setVisible(false);
             Variables.EstoyEnLogin = true;
             Variables.NumeroCarnetUsuarioLog = 0;
             java.awt.EventQueue.invokeLater(new Runnable() {
                 @Override
                 public void run()
                 {
                     new Login().setVisible(true);
                 }
             });
         }

         private void Bt_SalirActionPerformed(ActionEvent e)
         {
             this.dispose();
         }

         private void Bt_PerfilActionPerformed(ActionEvent e)
         {
             java.awt.EventQueue.invokeLater(new Runnable() {
                 @Override
                 public void run()
                 {
                     new Perfil().setVisible(true);
                 }
             });
         }

         private void Bt_JSONUsuariosActionPerformed(ActionEvent e)
         {
             LecturaJson.JSONUsuarios(this);
         }

         private void Bt_UsuariosReporteActionPerformed(ActionEvent e)
         {
             Variables.TablaHashUsuarios.ReporteTablaHashUsuarios();
         }

         private void initComponents()
         {
             // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
             menuBar1 = new JMenuBar();
             menu1 = new JMenu();
             Bt_AgregarUsuarios = new JMenuItem();
             Bt_AgregarLibros = new JMenuItem();
             Bt_Logout = new JMenuItem();
             Bt_Salir = new JMenuItem();
             menu2 = new JMenu();
             menuItem3 = new JMenuItem();
             Bt_JSONUsuarios = new JMenuItem();
             menuItem4 = new JMenuItem();
             menu3 = new JMenu();
             Bt_UsuariosReporte = new JMenuItem();
             label1 = new JLabel();
             label2 = new JLabel();
             label3 = new JLabel();
             Bt_Perfil = new JButton();
             button2 = new JButton();
             button3 = new JButton();

             //======== this ========
             setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
             setForeground(SystemColor.textHighlight);
             Container contentPane = getContentPane();
             contentPane.setLayout(null);

             //======== menuBar1 ========
             {

                 //======== menu1 ========
                 {
                     menu1.setText("Archivo");
                     menu1.setFont(new Font("Arial", Font.BOLD, 16));
                     menu1.setForeground(Color.black);
                     menu1.setIcon(new ImageIcon("C:\\Usac\\Estructuras De Datos\\Lab\\Proyecto 2\\EDD_1S2020_PY2_201801628\\Assets\\Archivo.png"));

                     //---- Bt_AgregarUsuarios ----
                     Bt_AgregarUsuarios.setText("Agregar Usuarios");
                     Bt_AgregarUsuarios.setForeground(Color.blue);
                     Bt_AgregarUsuarios.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_AgregarUsuarios.setIcon(new ImageIcon("C:\\Usac\\Estructuras De Datos\\Lab\\Proyecto 2\\EDD_1S2020_PY2_201801628\\Assets\\Usuarios.jpg"));
                     Bt_AgregarUsuarios.addActionListener(e -> Bt_AgregarUsuariosActionPerformed(e));
                     menu1.add(Bt_AgregarUsuarios);

                     //---- Bt_AgregarLibros ----
                     Bt_AgregarLibros.setText("Agregar LIbros ");
                     Bt_AgregarLibros.setForeground(Color.blue);
                     Bt_AgregarLibros.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_AgregarLibros.setIcon(new ImageIcon("C:\\Usac\\Estructuras De Datos\\Lab\\Proyecto 2\\EDD_1S2020_PY2_201801628\\Assets\\Libros.png"));
                     Bt_AgregarLibros.addActionListener(e -> Bt_AgregarLibrosActionPerformed(e));
                     menu1.add(Bt_AgregarLibros);

                     //---- Bt_Logout ----
                     Bt_Logout.setText("Cerrar Sesi\u00f3n");
                     Bt_Logout.setForeground(Color.blue);
                     Bt_Logout.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_Logout.setIcon(new ImageIcon("C:\\Usac\\Estructuras De Datos\\Lab\\Proyecto 2\\EDD_1S2020_PY2_201801628\\Assets\\CerrarSesion.png"));
                     Bt_Logout.addActionListener(e -> Bt_LogoutActionPerformed(e));
                     menu1.add(Bt_Logout);

                     //---- Bt_Salir ----
                     Bt_Salir.setText("Salir");
                     Bt_Salir.setForeground(Color.blue);
                     Bt_Salir.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_Salir.setIcon(new ImageIcon("C:\\Usac\\Estructuras De Datos\\Lab\\Proyecto 2\\EDD_1S2020_PY2_201801628\\Assets\\Salir.jpg"));
                     Bt_Salir.addActionListener(e -> Bt_SalirActionPerformed(e));
                     menu1.add(Bt_Salir);
                 }
                 menuBar1.add(menu1);

                 //======== menu2 ========
                 {
                     menu2.setText("Herramientas");
                     menu2.setForeground(Color.black);
                     menu2.setFont(new Font("Arial", Font.BOLD, 16));
                     menu2.setIcon(new ImageIcon("C:\\Usac\\Estructuras De Datos\\Lab\\Proyecto 2\\EDD_1S2020_PY2_201801628\\Assets\\Herramientas.jpg"));

                     //---- menuItem3 ----
                     menuItem3.setText("Configuraci\u00f3n IP/Puerto");
                     menuItem3.setForeground(Color.blue);
                     menuItem3.setFont(new Font("Arial", Font.BOLD, 16));
                     menuItem3.setIcon(new ImageIcon("C:\\Usac\\Estructuras De Datos\\Lab\\Proyecto 2\\EDD_1S2020_PY2_201801628\\Assets\\Ip.jpg"));
                     menu2.add(menuItem3);

                     //---- Bt_JSONUsuarios ----
                     Bt_JSONUsuarios.setText("Cargar Archivos Json Usuarios");
                     Bt_JSONUsuarios.setForeground(Color.blue);
                     Bt_JSONUsuarios.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_JSONUsuarios.setIcon(new ImageIcon("C:\\Usac\\Estructuras De Datos\\Lab\\Proyecto 2\\EDD_1S2020_PY2_201801628\\Assets\\Json.png"));
                     Bt_JSONUsuarios.addActionListener(e -> Bt_JSONUsuariosActionPerformed(e));
                     menu2.add(Bt_JSONUsuarios);

                     //---- menuItem4 ----
                     menuItem4.setText("Cargar Archivo Json Libros");
                     menuItem4.setForeground(Color.blue);
                     menuItem4.setFont(new Font("Arial", Font.BOLD, 16));
                     menuItem4.setIcon(new ImageIcon("C:\\Usac\\Estructuras De Datos\\Lab\\Proyecto 2\\EDD_1S2020_PY2_201801628\\Assets\\Json.png"));
                     menu2.add(menuItem4);
                 }
                 menuBar1.add(menu2);

                 //======== menu3 ========
                 {
                     menu3.setText("Reportes");
                     menu3.setForeground(Color.black);
                     menu3.setFont(new Font("Arial", Font.BOLD, 16));
                     menu3.setIcon(new ImageIcon("C:\\Usac\\Estructuras De Datos\\Lab\\Proyecto 2\\EDD_1S2020_PY2_201801628\\Assets\\Reporte.png"));

                     //---- Bt_UsuariosReporte ----
                     Bt_UsuariosReporte.setText("Reporte TablaHash Usuarios");
                     Bt_UsuariosReporte.setForeground(Color.blue);
                     Bt_UsuariosReporte.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_UsuariosReporte.setIcon(new ImageIcon("C:\\Usac\\Estructuras De Datos\\Lab\\Proyecto 2\\EDD_1S2020_PY2_201801628\\Assets\\TablaHash.png"));
                     Bt_UsuariosReporte.addActionListener(e -> Bt_UsuariosReporteActionPerformed(e));
                     menu3.add(Bt_UsuariosReporte);
                 }
                 menuBar1.add(menu3);
             }
             setJMenuBar(menuBar1);
             contentPane.add(label1);
             label1.setBounds(625, 405, 45, 55);

             //---- label2 ----
             label2.setText("Usac Library SEG");
             label2.setFont(new Font("Arial", Font.BOLD, 22));
             label2.setForeground(new Color(102, 102, 255));
             contentPane.add(label2);
             label2.setBounds(new Rectangle(new Point(215, 65), label2.getPreferredSize()));

             //---- label3 ----
             label3.setText("Seleccione Una De Las Siguientes Opciones:");
             label3.setFont(new Font("Arial", Font.BOLD, 14));
             label3.setForeground(new Color(102, 102, 255));
             contentPane.add(label3);
             label3.setBounds(new Rectangle(new Point(155, 115), label3.getPreferredSize()));

             //---- Bt_Perfil ----
             Bt_Perfil.setText("Perfil Usuario");
             Bt_Perfil.setFont(new Font("Arial", Font.BOLD, 20));
             Bt_Perfil.setForeground(new Color(0, 153, 255));
             Bt_Perfil.addActionListener(e -> Bt_PerfilActionPerformed(e));
             contentPane.add(Bt_Perfil);
             Bt_Perfil.setBounds(new Rectangle(new Point(95, 170), Bt_Perfil.getPreferredSize()));

             //---- button2 ----
             button2.setText("Cat\u00e1logo De Libros");
             button2.setFont(new Font("Arial", Font.BOLD, 20));
             button2.setForeground(new Color(0, 153, 255));
             contentPane.add(button2);
             button2.setBounds(new Rectangle(new Point(340, 170), button2.getPreferredSize()));

             //---- button3 ----
             button3.setText("B\u00fasqueda De Libros");
             button3.setFont(new Font("Arial", Font.BOLD, 20));
             button3.setForeground(new Color(0, 153, 255));
             contentPane.add(button3);
             button3.setBounds(new Rectangle(new Point(60, 245), button3.getPreferredSize()));

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
         private JMenuBar menuBar1;
         private JMenu menu1;
         private JMenuItem Bt_AgregarUsuarios;
         private JMenuItem Bt_AgregarLibros;
         private JMenuItem Bt_Logout;
         private JMenuItem Bt_Salir;
         private JMenu menu2;
         private JMenuItem menuItem3;
         private JMenuItem Bt_JSONUsuarios;
         private JMenuItem menuItem4;
         private JMenu menu3;
         private JMenuItem Bt_UsuariosReporte;
         private JLabel label1;
         private JLabel label2;
         private JLabel label3;
         private JButton Bt_Perfil;
         private JButton button2;
         private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
     }
