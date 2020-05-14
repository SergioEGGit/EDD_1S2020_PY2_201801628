
     import java.awt.*;
     import java.awt.event.*;
     import java.util.ArrayList;
     import javax.swing.*;
     import Estructuras.NodoArbolAVL;
     import Estructuras.NodoListaDoble;
     import Estructuras.NodoListaSimple;
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
             Variables.Principal = this;
         }

         public static void main(String[] args)
         {
             Metodos.Metodos.CalcularNumeroIndex();
             Variables.NodoListaDobleBloques = new NodoListaDoble();
             Variables.NodoListaDobleBloques.start();
             NodoListaSimple NuevoBloque = new NodoListaSimple();
             NuevoBloque.setPuerto(Variables.NodoListaDobleBloques.getSocket().getLocalPort());
             Variables.ListaSimpleRed.InsertarNodoRedListaSimple(NuevoBloque);
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
             java.awt.EventQueue.invokeLater(new Runnable() {
                 @Override
                 public void run()
                 {
                     new RegistroLibros().setVisible(true);
                 }
             });
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

         private void Bt_CategoriasActionPerformed(ActionEvent e)
         {
             java.awt.EventQueue.invokeLater(new Runnable() {
                 @Override
                 public void run()
                 {
                     new RegistroCategorias().setVisible(true);
                 }
             });
         }

         private void Bt_CatalogoActionPerformed(ActionEvent e)
         {
             java.awt.EventQueue.invokeLater(new Runnable() {
                 @Override
                 public void run()
                 {
                     new CatalogoLibros().setVisible(true);
                 }
             });
         }

         private void Bt_EliminacionActionPerformed(ActionEvent e)
         {
             java.awt.EventQueue.invokeLater(new Runnable() {
                 @Override
                 public void run()
                 {
                     new LibrosOperaciones().setVisible(true);
                 }
             });
         }

         private void Bt_JSONUsuariosActionPerformed(ActionEvent e)
         {
             LecturaJson.JSONUsuarios(this);
         }

         private void Bt_JSONLibrosActionPerformed(ActionEvent e)
         {
             LecturaJson.JSONLibros(this);
         }

         private void Bt_UsuariosReporteActionPerformed(ActionEvent e)
         {
             Variables.NombreReporte = "ReporteUsuariosTablaHash.png";
             Variables.TablaHashUsuarios.ReporteTablaHashUsuarios();

             if(Variables.GenereReporte)
             {
                 JOptionPane.showMessageDialog(null, "Reporte Generado Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);

                 java.awt.EventQueue.invokeLater(new Runnable() {
                     @Override
                     public void run()
                     {
                         new Reportes().setVisible(true);
                     }
                 });
             }
         }

         private void Bt_CategoriasReporteActionPerformed(ActionEvent e)
         {
             Variables.NombreReporte = "ReporteCategoriasArbolAVL.png";
             Variables.ArbolAVLCategorias.GraficarArbolAVL();

             if(Variables.GenereReporte)
             {
                 JOptionPane.showMessageDialog(null, "Reporte Generado Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);

                 java.awt.EventQueue.invokeLater(new Runnable() {
                     @Override
                     public void run()
                     {
                         new Reportes().setVisible(true);
                     }
                 });
             }
         }

         private void Bt_LibrosReporteActionPerformed(ActionEvent e)
         {
             ArrayList<String> Categorias = Variables.ArbolAVLCategorias.IniciarCatalogoArbolAVL();

             JComboBox<String> Cat = new JComboBox<String>();
             Cat.setEditable(true);
             Cat.setFont(new Font("Arial", Font.BOLD, 16));
             Cat.setForeground(new Color(0, 153, 255));

             for(String Categoria: Categorias)
             {
                 if(Categoria != null)
                 {
                     Cat.addItem(Categoria);
                 }
             }

             int Mensaje = JOptionPane.showConfirmDialog(null, Cat, "Seleccione Una Categoría", JOptionPane.OK_CANCEL_OPTION);

             if(Mensaje == JOptionPane.OK_OPTION)
             {
                 if(!Cat.getSelectedItem().toString().equals(""))
                 {
                     String Categoria = "";

                     Categoria = Cat.getSelectedItem().toString();

                     NodoArbolAVL ArbolB = Variables.ArbolAVLCategorias.InicializarBuscarCategoriaArbolAVL(Categoria);

                     if(ArbolB != null)
                     {
                         Variables.NombreReporte = "ReporteLibrosArbolB.png";
                         Variables.ArbolAVLCategorias.GraficarLibrosArbolAVL(ArbolB);

                         if(Variables.GenereReporte)
                         {
                             JOptionPane.showMessageDialog(null, "Reporte Generado Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);

                             java.awt.EventQueue.invokeLater(new Runnable() {
                                 @Override
                                 public void run()
                                 {
                                     new Reportes().setVisible(true);
                                 }
                             });
                         }
                     }
                 }
             }
             else
             {
                 System.out.println("Nada");
             }
         }

         private void Bt_RecorridosReporteActionPerformed(ActionEvent e)
         {
             Variables.NombreReporte = "ReporteRecorridosArbolAVL.png";
             Variables.ArbolAVLCategorias.GraficarRecorridosArbolAVL();

             if(Variables.GenereReporte)
             {
                 JOptionPane.showMessageDialog(null, "Reporte Generado Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);

                 java.awt.EventQueue.invokeLater(new Runnable() {
                     @Override
                     public void run()
                     {
                         new Reportes().setVisible(true);
                     }
                 });
             }
         }

         private void Bt_NodosReporteActionPerformed(ActionEvent e)
         {
             Variables.NombreReporte = "ReporteNodoDeRedListaSimple.png";
             Variables.ListaSimpleRed.GraficarRedListaSimple();

             if(Variables.GenereReporte)
             {
                 JOptionPane.showMessageDialog(null, "Reporte Generado Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);

                 java.awt.EventQueue.invokeLater(new Runnable() {
                     @Override
                     public void run()
                     {
                         new Reportes().setVisible(true);
                     }
                 });
             }
         }

         private void Bt_BlockchainReporteActionPerformed(ActionEvent e)
         {
             Variables.NombreReporte = "ReporteBloquesListaDoble.png";
             Variables.ListaSimpleRed.GraficarRedListaSimple();

             if(Variables.GenereReporte)
             {
                 JOptionPane.showMessageDialog(null, "Reporte Generado Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);

                 java.awt.EventQueue.invokeLater(new Runnable() {
                     @Override
                     public void run()
                     {
                         new Reportes().setVisible(true);
                     }
                 });
             }
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
             Bt_JSONLibros = new JMenuItem();
             menu3 = new JMenu();
             Bt_UsuariosReporte = new JMenuItem();
             Bt_CategoriasReporte = new JMenuItem();
             Bt_RecorridosReporte = new JMenuItem();
             Bt_LibrosReporte = new JMenuItem();
             Bt_NodosReporte = new JMenuItem();
             Bt_BlockchainReporte = new JMenuItem();
             label1 = new JLabel();
             label2 = new JLabel();
             label3 = new JLabel();
             Bt_Perfil = new JButton();
             Bt_Catalogo = new JButton();
             Bt_Categorias = new JButton();
             Bt_Eliminacion = new JButton();

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
                     menu1.setIcon(new ImageIcon(getClass().getResource("/Assets/Archivo.png")));

                     //---- Bt_AgregarUsuarios ----
                     Bt_AgregarUsuarios.setText("Agregar Usuarios");
                     Bt_AgregarUsuarios.setForeground(Color.blue);
                     Bt_AgregarUsuarios.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_AgregarUsuarios.setIcon(new ImageIcon(getClass().getResource("/Assets/Usuarios.jpg")));
                     Bt_AgregarUsuarios.addActionListener(e -> Bt_AgregarUsuariosActionPerformed(e));
                     menu1.add(Bt_AgregarUsuarios);

                     //---- Bt_AgregarLibros ----
                     Bt_AgregarLibros.setText("Agregar LIbros ");
                     Bt_AgregarLibros.setForeground(Color.blue);
                     Bt_AgregarLibros.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_AgregarLibros.setIcon(new ImageIcon(getClass().getResource("/Assets/Libros.png")));
                     Bt_AgregarLibros.addActionListener(e -> Bt_AgregarLibrosActionPerformed(e));
                     menu1.add(Bt_AgregarLibros);

                     //---- Bt_Logout ----
                     Bt_Logout.setText("Cerrar Sesi\u00f3n");
                     Bt_Logout.setForeground(Color.blue);
                     Bt_Logout.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_Logout.setIcon(new ImageIcon(getClass().getResource("/Assets/CerrarSesion.png")));
                     Bt_Logout.addActionListener(e -> Bt_LogoutActionPerformed(e));
                     menu1.add(Bt_Logout);

                     //---- Bt_Salir ----
                     Bt_Salir.setText("Salir");
                     Bt_Salir.setForeground(Color.blue);
                     Bt_Salir.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_Salir.setIcon(new ImageIcon(getClass().getResource("/Assets/Salir.jpg")));
                     Bt_Salir.addActionListener(e -> Bt_SalirActionPerformed(e));
                     menu1.add(Bt_Salir);
                 }
                 menuBar1.add(menu1);

                 //======== menu2 ========
                 {
                     menu2.setText("Herramientas");
                     menu2.setForeground(Color.black);
                     menu2.setFont(new Font("Arial", Font.BOLD, 16));
                     menu2.setIcon(new ImageIcon(getClass().getResource("/Assets/Herramientas.jpg")));

                     //---- menuItem3 ----
                     menuItem3.setText("Configuraci\u00f3n IP/Puerto");
                     menuItem3.setForeground(Color.blue);
                     menuItem3.setFont(new Font("Arial", Font.BOLD, 16));
                     menuItem3.setIcon(new ImageIcon(getClass().getResource("/Assets/Ip.jpg")));
                     menu2.add(menuItem3);

                     //---- Bt_JSONUsuarios ----
                     Bt_JSONUsuarios.setText("Cargar Archivos Json Usuarios");
                     Bt_JSONUsuarios.setForeground(Color.blue);
                     Bt_JSONUsuarios.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_JSONUsuarios.setIcon(new ImageIcon(getClass().getResource("/Assets/Json.png")));
                     Bt_JSONUsuarios.addActionListener(e -> Bt_JSONUsuariosActionPerformed(e));
                     menu2.add(Bt_JSONUsuarios);

                     //---- Bt_JSONLibros ----
                     Bt_JSONLibros.setText("Cargar Archivo Json Libros");
                     Bt_JSONLibros.setForeground(Color.blue);
                     Bt_JSONLibros.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_JSONLibros.setIcon(new ImageIcon(getClass().getResource("/Assets/Json.png")));
                     Bt_JSONLibros.addActionListener(e -> Bt_JSONLibrosActionPerformed(e));
                     menu2.add(Bt_JSONLibros);
                 }
                 menuBar1.add(menu2);

                 //======== menu3 ========
                 {
                     menu3.setText("Reportes");
                     menu3.setForeground(Color.black);
                     menu3.setFont(new Font("Arial", Font.BOLD, 16));
                     menu3.setIcon(new ImageIcon(getClass().getResource("/Assets/Reporte.png")));

                     //---- Bt_UsuariosReporte ----
                     Bt_UsuariosReporte.setText("Tabla Hash Usuarios");
                     Bt_UsuariosReporte.setForeground(Color.blue);
                     Bt_UsuariosReporte.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_UsuariosReporte.setIcon(new ImageIcon(getClass().getResource("/Assets/TablaHash.png")));
                     Bt_UsuariosReporte.addActionListener(e -> Bt_UsuariosReporteActionPerformed(e));
                     menu3.add(Bt_UsuariosReporte);

                     //---- Bt_CategoriasReporte ----
                     Bt_CategoriasReporte.setText("Arbol AVL Categor\u00edas");
                     Bt_CategoriasReporte.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_CategoriasReporte.setForeground(Color.blue);
                     Bt_CategoriasReporte.setIcon(new ImageIcon(getClass().getResource("/Assets/ArbolAVL.png")));
                     Bt_CategoriasReporte.addActionListener(e -> Bt_CategoriasReporteActionPerformed(e));
                     menu3.add(Bt_CategoriasReporte);

                     //---- Bt_RecorridosReporte ----
                     Bt_RecorridosReporte.setText("Arbol AVL Recorridos");
                     Bt_RecorridosReporte.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_RecorridosReporte.setForeground(Color.blue);
                     Bt_RecorridosReporte.setIcon(new ImageIcon(getClass().getResource("/Assets/Recorridos.png")));
                     Bt_RecorridosReporte.addActionListener(e -> Bt_RecorridosReporteActionPerformed(e));
                     menu3.add(Bt_RecorridosReporte);

                     //---- Bt_LibrosReporte ----
                     Bt_LibrosReporte.setText("Arbol B Libros");
                     Bt_LibrosReporte.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_LibrosReporte.setForeground(Color.blue);
                     Bt_LibrosReporte.setIcon(new ImageIcon(getClass().getResource("/Assets/ArbolB.jpg")));
                     Bt_LibrosReporte.addActionListener(e -> Bt_LibrosReporteActionPerformed(e));
                     menu3.add(Bt_LibrosReporte);

                     //---- Bt_NodosReporte ----
                     Bt_NodosReporte.setText("Lista Simple Nodos De Red");
                     Bt_NodosReporte.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_NodosReporte.setForeground(Color.blue);
                     Bt_NodosReporte.setIcon(new ImageIcon(getClass().getResource("/Assets/ListaSimple.jpg")));
                     Bt_NodosReporte.addActionListener(e -> Bt_NodosReporteActionPerformed(e));
                     menu3.add(Bt_NodosReporte);

                     //---- Bt_BlockchainReporte ----
                     Bt_BlockchainReporte.setText("Lista Doble BlockChain");
                     Bt_BlockchainReporte.setFont(new Font("Arial", Font.BOLD, 16));
                     Bt_BlockchainReporte.setForeground(Color.blue);
                     Bt_BlockchainReporte.setIcon(new ImageIcon(getClass().getResource("/Assets/ListaDoble.jpg")));
                     Bt_BlockchainReporte.addActionListener(e -> Bt_BlockchainReporteActionPerformed(e));
                     menu3.add(Bt_BlockchainReporte);
                 }
                 menuBar1.add(menu3);
             }
             setJMenuBar(menuBar1);
             contentPane.add(label1);
             label1.setBounds(675, 405, 45, 55);

             //---- label2 ----
             label2.setText("Usac Library SEG");
             label2.setFont(new Font("Arial", Font.BOLD, 22));
             label2.setForeground(new Color(102, 102, 255));
             contentPane.add(label2);
             label2.setBounds(new Rectangle(new Point(270, 70), label2.getPreferredSize()));

             //---- label3 ----
             label3.setText("Seleccione Una De Las Siguientes Opciones:");
             label3.setFont(new Font("Arial", Font.BOLD, 14));
             label3.setForeground(new Color(102, 102, 255));
             contentPane.add(label3);
             label3.setBounds(new Rectangle(new Point(205, 120), label3.getPreferredSize()));

             //---- Bt_Perfil ----
             Bt_Perfil.setText("Perfil Usuario");
             Bt_Perfil.setFont(new Font("Arial", Font.BOLD, 20));
             Bt_Perfil.setForeground(new Color(0, 153, 255));
             Bt_Perfil.addActionListener(e -> Bt_PerfilActionPerformed(e));
             contentPane.add(Bt_Perfil);
             Bt_Perfil.setBounds(new Rectangle(new Point(280, 165), Bt_Perfil.getPreferredSize()));

             //---- Bt_Catalogo ----
             Bt_Catalogo.setText("Cat\u00e1logo De Libros");
             Bt_Catalogo.setFont(new Font("Arial", Font.BOLD, 20));
             Bt_Catalogo.setForeground(new Color(0, 153, 255));
             Bt_Catalogo.addActionListener(e -> Bt_CatalogoActionPerformed(e));
             contentPane.add(Bt_Catalogo);
             Bt_Catalogo.setBounds(new Rectangle(new Point(255, 275), Bt_Catalogo.getPreferredSize()));

             //---- Bt_Categorias ----
             Bt_Categorias.setText("Categor\u00edas");
             Bt_Categorias.setFont(new Font("Arial", Font.BOLD, 20));
             Bt_Categorias.setForeground(new Color(0, 153, 255));
             Bt_Categorias.addActionListener(e -> Bt_CategoriasActionPerformed(e));
             contentPane.add(Bt_Categorias);
             Bt_Categorias.setBounds(275, 225, 170, Bt_Categorias.getPreferredSize().height);

             //---- Bt_Eliminacion ----
             Bt_Eliminacion.setText("Busqueda Y Eliminaci\u00f3n De Libros");
             Bt_Eliminacion.setFont(new Font("Arial", Font.BOLD, 20));
             Bt_Eliminacion.setForeground(new Color(0, 153, 255));
             Bt_Eliminacion.addActionListener(e -> Bt_EliminacionActionPerformed(e));
             contentPane.add(Bt_Eliminacion);
             Bt_Eliminacion.setBounds(new Rectangle(new Point(185, 330), Bt_Eliminacion.getPreferredSize()));

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
         private JMenuItem Bt_JSONLibros;
         private JMenu menu3;
         private JMenuItem Bt_UsuariosReporte;
         private JMenuItem Bt_CategoriasReporte;
         private JMenuItem Bt_RecorridosReporte;
         private JMenuItem Bt_LibrosReporte;
         private JMenuItem Bt_NodosReporte;
         private JMenuItem Bt_BlockchainReporte;
         private JLabel label1;
         private JLabel label2;
         private JLabel label3;
         private JButton Bt_Perfil;
         private JButton Bt_Catalogo;
         private JButton Bt_Categorias;
         private JButton Bt_Eliminacion;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
     }
