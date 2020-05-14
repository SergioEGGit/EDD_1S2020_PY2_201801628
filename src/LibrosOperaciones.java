
import java.awt.event.*;
   import Metodos.Variables;
   import Modelos.Libros;
import javafx.scene.control.TableRow;

import java.awt.*;
   import java.util.ArrayList;
   import javax.swing.*;
   import javax.swing.table.*;

   /*
    *  Created by JFormDesigner on Mon May 11 11:20:56 CDT 2020
    */


   /**
    * @author Sergio EG
    */

    public class LibrosOperaciones extends JFrame
    {

        ArrayList<Libros> ListaDeLibros =new ArrayList<Libros>();
        DefaultTableModel Modelo;

        public LibrosOperaciones()
        {
            initComponents();
            ListaDeLibros = Variables.ArbolAVLCategorias.ListarTodosLosLibrosArbolAVL();
            ObtenerLibros();
        }

        public void ObtenerLibros()
        {
            Modelo = new DefaultTableModel();

            Modelo.addColumn("ISBN");
            Modelo.addColumn("Título");
            Modelo.addColumn("Autor");
            Modelo.addColumn("Editorial");
            Modelo.addColumn("Año");
            Modelo.addColumn("Edición");
            Modelo.addColumn("Categoría");
            Modelo.addColumn("Idioma");
            Modelo.addColumn("Carnet_Creador");

            for(Libros Libro: ListaDeLibros)
            {
                if(Libro != null)
                {
                    Object[] Valor = new Object[]
                            {
                                    Libro.getISBN(),
                                    Libro.getTitulo(),
                                    Libro.getAutor(),
                                    Libro.getEditorial(),
                                    Libro.getAnio(),
                                    Libro.getEdicion(),
                                    Libro.getCategoria(),
                                    Libro.getIdioma(),
                                    Libro.getCarnet_Usuario_Creador()
                            };
                    Modelo.addRow(Valor);
                }
            }

            Tb_Libros.setModel(Modelo);

            Tb_Libros.getColumnModel().getColumn(0).setPreferredWidth(10);
            Tb_Libros.getColumnModel().getColumn(1).setPreferredWidth(400);
            Tb_Libros.getColumnModel().getColumn(2).setPreferredWidth(100);
            Tb_Libros.getColumnModel().getColumn(3).setPreferredWidth(100);
            Tb_Libros.getColumnModel().getColumn(4).setPreferredWidth(5);
            Tb_Libros.getColumnModel().getColumn(5).setPreferredWidth(5);
            Tb_Libros.getColumnModel().getColumn(6).setPreferredWidth(40);
            Tb_Libros.getColumnModel().getColumn(7).setPreferredWidth(40);
            Tb_Libros.getColumnModel().getColumn(8).setPreferredWidth(40);
            Tb_Libros.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        }

        private void Bt_RegresarActionPerformed(ActionEvent e)
        {
            this.dispose();
        }

        public void FiltrarTabla(String Libro, JTable TablaBuscar)
        {
            TableRowSorter<DefaultTableModel> Filtro = new TableRowSorter<>(Modelo);

            TablaBuscar.setRowSorter(Filtro);
            Filtro.setRowFilter(RowFilter.regexFilter(Libro, 1));
        }

        private void Buscar_FieldKeyReleased(KeyEvent e)
        {
            FiltrarTabla(Buscar_Field.getText(), Tb_Libros);
        }

        private void Tb_LibrosMouseClicked(MouseEvent e)
        {
            int Columna = 0;
            int Fila = Tb_Libros.getSelectedRow();

            String Valor = Tb_Libros.getValueAt(Fila, Columna).toString();

            Eliminar_Field.setText(Valor);
        }

        private void Bt_EliminarActionPerformed(ActionEvent e)
        {
            try
            {
                int ISBN = Integer.parseInt(Eliminar_Field.getText());
                Libros LibroBorrar = Variables.ArbolAVLCategorias.InicizalizarEliminacionArboAVL(ISBN, Variables.NumeroCarnetUsuarioLog);

                if(LibroBorrar != null)
                {
                    JOptionPane.showMessageDialog(null, "Libro Eliminado Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No Se Puede Eliminar El Libro Indicado \nDebido A Que Usted No Agrego Ese Libro", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(Exception Ex)
            {
                JOptionPane.showMessageDialog(null, "No Se Ha Seleccionado Un Libro", "Advertencia!", JOptionPane.WARNING_MESSAGE);
            }

            Buscar_Field.setText("");
            Eliminar_Field.setText("");
            ListaDeLibros = Variables.ArbolAVLCategorias.ListarTodosLosLibrosArbolAVL();
            ObtenerLibros();
        }

        private void initComponents()
        {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            label1 = new JLabel();
            scrollPane1 = new JScrollPane();
            Tb_Libros = new JTable();
            label2 = new JLabel();
            Bt_Regresar = new JButton();
            Buscar_Field = new JTextField();
            label3 = new JLabel();
            label6 = new JLabel();
            Eliminar_Field = new JTextField();
            Bt_Eliminar = new JButton();

            //======== this ========
            Container contentPane = getContentPane();
            contentPane.setLayout(null);

            //---- label1 ----
            label1.setText("Eliminaci\u00f3n De Libros");
            label1.setFont(new Font("Arial", Font.BOLD, 22));
            label1.setForeground(new Color(102, 102, 255));
            contentPane.add(label1);
            label1.setBounds(new Rectangle(new Point(540, 30), label1.getPreferredSize()));

            //======== scrollPane1 ========
            {

                //---- Tb_Libros ----
                Tb_Libros.setModel(new DefaultTableModel(2, 0));
                Tb_Libros.setFont(new Font("Arial", Font.PLAIN, 14));
                Tb_Libros.setBackground(Color.white);
                Tb_Libros.setForeground(new Color(0, 0, 153));
                Tb_Libros.setGridColor(new Color(255, 51, 51));
                Tb_Libros.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Tb_LibrosMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(Tb_Libros);
            }
            contentPane.add(scrollPane1);
            scrollPane1.setBounds(45, 185, 1230, 505);
            contentPane.add(label2);
            label2.setBounds(1280, 665, 35, 45);

            //---- Bt_Regresar ----
            Bt_Regresar.setText("Regresar");
            Bt_Regresar.setFont(new Font("Arial", Font.BOLD, 16));
            Bt_Regresar.setForeground(new Color(0, 153, 255));
            Bt_Regresar.addActionListener(e -> Bt_RegresarActionPerformed(e));
            contentPane.add(Bt_Regresar);
            Bt_Regresar.setBounds(new Rectangle(new Point(1050, 75), Bt_Regresar.getPreferredSize()));

            //---- Buscar_Field ----
            Buscar_Field.setForeground(new Color(0, 153, 255));
            Buscar_Field.setFont(new Font("Arial", Font.BOLD, 16));
            Buscar_Field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    Buscar_FieldKeyReleased(e);
                }
            });
            contentPane.add(Buscar_Field);
            Buscar_Field.setBounds(155, 150, 300, Buscar_Field.getPreferredSize().height);

            //---- label3 ----
            label3.setText("Buscar Libro:");
            label3.setFont(new Font("Arial", Font.BOLD, 16));
            label3.setForeground(new Color(0, 153, 255));
            contentPane.add(label3);
            label3.setBounds(45, 145, 115, 30);

            //---- label6 ----
            label6.setText("Seleccione El Libro Que Desea Eliminar");
            label6.setFont(new Font("Arial", Font.BOLD, 16));
            label6.setForeground(new Color(102, 102, 255));
            contentPane.add(label6);
            label6.setBounds(495, 80, 310, 19);

            //---- Eliminar_Field ----
            Eliminar_Field.setFont(new Font("Arial", Font.BOLD, 16));
            Eliminar_Field.setForeground(new Color(102, 102, 255));
            Eliminar_Field.setEditable(false);
            contentPane.add(Eliminar_Field);
            Eliminar_Field.setBounds(565, 120, 180, 35);

            //---- Bt_Eliminar ----
            Bt_Eliminar.setText("Eliminar");
            Bt_Eliminar.setFont(new Font("Arial", Font.BOLD, 16));
            Bt_Eliminar.setForeground(new Color(0, 153, 255));
            Bt_Eliminar.addActionListener(e -> Bt_EliminarActionPerformed(e));
            contentPane.add(Bt_Eliminar);
            Bt_Eliminar.setBounds(new Rectangle(new Point(775, 120), Bt_Eliminar.getPreferredSize()));

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
        private JLabel label1;
        private JScrollPane scrollPane1;
        private JTable Tb_Libros;
        private JLabel label2;
        private JButton Bt_Regresar;
        private JTextField Buscar_Field;
        private JLabel label3;
        private JLabel label6;
        private JTextField Eliminar_Field;
        private JButton Bt_Eliminar;
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }
