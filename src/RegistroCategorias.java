

import java.awt.event.*;
    import Estructuras.NodoArbolAVL;
    import Metodos.Variables;
import jdk.nashorn.internal.scripts.JO;

import java.awt.*;
    import java.util.ArrayList;
import java.util.jar.JarOutputStream;
import javax.swing.*;
    import javax.swing.table.*;

    /*
     * Created by JFormDesigner on Sun May 10 17:07:15 CDT 2020
     */

    /**
     * @author Sergio EG
     */

    public class RegistroCategorias extends JFrame
    {
        ArrayList<NodoArbolAVL> Categorias = new ArrayList<NodoArbolAVL>();

        public RegistroCategorias()
        {
            initComponents();
            Categorias = Variables.ArbolAVLCategorias.ListarCategoriasArbolAVL();
            ObtenerCategorias();
            setLocationRelativeTo(null);
        }

        public void ObtenerCategorias()
        {
            DefaultTableModel Modelo = new DefaultTableModel();

            Modelo.addColumn("Categoría");
            Modelo.addColumn("Cantidad De Libros");

            for(NodoArbolAVL Categoria: Categorias)
            {
                if(Categoria != null)
                {
                    Object[] Fila = new Object[]
                            {
                                    Categoria.getCategoria(),
                                    Categoria.getArbolB().getLibroArbolB().size()
                            };
                    Modelo.addRow(Fila);
                }
            }

            Tb_Categorias.setModel(Modelo);
        }

        private void Bt_AgregarActionPerformed(ActionEvent e)
        {
            try
            {
                String NuevaCategoria = Categoria_Field.getText();

                Variables.ExisteCategoria = false;

                Variables.ArbolAVLCategorias.ExisteCategoriaArbolAVL(NuevaCategoria);

                if(NuevaCategoria.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "No Se Ha Indicado Ninguna Categoria", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    if(Variables.ExisteCategoria)
                    {
                        JOptionPane.showMessageDialog(null, "Ya Existe La Categoría Indicada", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                        Categoria_Field.setText("");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Categoría Agregada Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                        Variables.ArbolAVLCategorias.InicializarCategoriaArbolAVL(NuevaCategoria, Variables.NumeroCarnetUsuarioLog);
                        Categorias = Variables.ArbolAVLCategorias.AgregarArbolAVL();

                        Categoria_Field.setText("");

                        ObtenerCategorias();
                    }
                }
            }
            catch (HeadlessException ex)
            {
                ex.printStackTrace();
            }
        }

        private void Tb_CategoriasMouseClicked(MouseEvent e)
        {
            int Columna = 0;
            int Fila = Tb_Categorias.getSelectedRow();

            String Valor = Tb_Categorias.getValueAt(Fila, Columna).toString();

            Eliminar_Field.setText(Valor);
        }

        private void Bt_EliminarActionPerformed(ActionEvent e)
        {
            String Categoria = Eliminar_Field.getText();

            Variables.CreeCategoria = false;

            Variables.ArbolAVLCategorias.EliminarCategoriaArbolAVL(Categoria, Variables.NumeroCarnetUsuarioLog);
            Categorias = Variables.ArbolAVLCategorias.AgregarArbolAVL();

            if(Categoria.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Debe De Seleccionar Un Libro", "Advertencia!", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                if(Variables.CreeCategoria)
                {
                    JOptionPane.showMessageDialog(null, "Categoría Eliminada Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                    ObtenerCategorias();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No Se Puede Eliminar La Categoría \nDebido A Que Usted No Es El Creador De La Categoría", "Advertencia!", JOptionPane.WARNING_MESSAGE);
                }
            }

            Eliminar_Field.setText("");
        }

        private void Bt_RegresarActionPerformed(ActionEvent e)
        {
            this.dispose();
        }

        private void initComponents()
        {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            label1 = new JLabel();
            label2 = new JLabel();
            label3 = new JLabel();
            label4 = new JLabel();
            label5 = new JLabel();
            Categoria_Field = new JTextField();
            Bt_Agregar = new JButton();
            label6 = new JLabel();
            Eliminar_Field = new JTextField();
            Bt_Eliminar = new JButton();
            scrollPane1 = new JScrollPane();
            Tb_Categorias = new JTable();
            Bt_Regresar = new JButton();

            //======== this ========
            setTitle("Categorias");
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setResizable(false);
            Container contentPane = getContentPane();
            contentPane.setLayout(null);

            //---- label1 ----
            label1.setText("Categor\u00edas");
            label1.setFont(new Font("Arial", Font.BOLD, 22));
            label1.setForeground(new Color(102, 102, 255));
            contentPane.add(label1);
            label1.setBounds(new Rectangle(new Point(360, 45), label1.getPreferredSize()));

            //---- label2 ----
            label2.setText("Agregar Categor\u00eda");
            label2.setFont(new Font("Arial", Font.BOLD, 18));
            label2.setForeground(new Color(102, 102, 255));
            contentPane.add(label2);
            label2.setBounds(new Rectangle(new Point(145, 125), label2.getPreferredSize()));

            //---- label3 ----
            label3.setText("Eliminar Categor\u00eda");
            label3.setFont(new Font("Arial", Font.BOLD, 18));
            label3.setForeground(new Color(102, 102, 255));
            contentPane.add(label3);
            label3.setBounds(500, 125, 170, 19);

            //---- label4 ----
            label4.setText("Nombre:");
            label4.setFont(new Font("Arial", Font.BOLD, 16));
            label4.setForeground(new Color(0, 153, 255));
            contentPane.add(label4);
            label4.setBounds(new Rectangle(new Point(100, 195), label4.getPreferredSize()));
            contentPane.add(label5);
            label5.setBounds(820, 645, 35, 60);

            //---- Categoria_Field ----
            Categoria_Field.setFont(new Font("Arial", Font.BOLD, 16));
            Categoria_Field.setForeground(new Color(102, 102, 255));
            contentPane.add(Categoria_Field);
            Categoria_Field.setBounds(185, 190, 155, Categoria_Field.getPreferredSize().height);

            //---- Bt_Agregar ----
            Bt_Agregar.setText("Agregar");
            Bt_Agregar.setFont(new Font("Arial", Font.BOLD, 16));
            Bt_Agregar.setForeground(new Color(0, 153, 255));
            Bt_Agregar.addActionListener(e -> Bt_AgregarActionPerformed(e));
            contentPane.add(Bt_Agregar);
            Bt_Agregar.setBounds(new Rectangle(new Point(180, 250), Bt_Agregar.getPreferredSize()));

            //---- label6 ----
            label6.setText("Seleccione La Categor\u00eda Que Desea Eliminar");
            label6.setFont(new Font("Arial", Font.BOLD, 16));
            label6.setForeground(new Color(102, 102, 255));
            contentPane.add(label6);
            label6.setBounds(new Rectangle(new Point(425, 180), label6.getPreferredSize()));

            //---- Eliminar_Field ----
            Eliminar_Field.setFont(new Font("Arial", Font.BOLD, 16));
            Eliminar_Field.setForeground(new Color(102, 102, 255));
            Eliminar_Field.setEditable(false);
            contentPane.add(Eliminar_Field);
            Eliminar_Field.setBounds(450, 235, 180, Eliminar_Field.getPreferredSize().height);

            //---- Bt_Eliminar ----
            Bt_Eliminar.setText("Eliminar");
            Bt_Eliminar.setForeground(new Color(0, 153, 255));
            Bt_Eliminar.setFont(new Font("Arial", Font.BOLD, 16));
            Bt_Eliminar.addActionListener(e -> Bt_EliminarActionPerformed(e));
            contentPane.add(Bt_Eliminar);
            Bt_Eliminar.setBounds(660, 235, 115, Bt_Eliminar.getPreferredSize().height);

            //======== scrollPane1 ========
            {

                //---- Tb_Categorias ----
                Tb_Categorias.setModel(new DefaultTableModel(2, 0));
                Tb_Categorias.setFont(new Font("Arial", Font.PLAIN, 16));
                Tb_Categorias.setForeground(new Color(0, 0, 204));
                Tb_Categorias.setBackground(Color.white);
                Tb_Categorias.setGridColor(new Color(255, 51, 0));
                Tb_Categorias.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Tb_CategoriasMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(Tb_Categorias);
            }
            contentPane.add(scrollPane1);
            scrollPane1.setBounds(160, 345, 535, 307);

            //---- Bt_Regresar ----
            Bt_Regresar.setText("Regresar");
            Bt_Regresar.setFont(new Font("Arial", Font.BOLD, 16));
            Bt_Regresar.setForeground(new Color(0, 153, 255));
            Bt_Regresar.addActionListener(e -> Bt_RegresarActionPerformed(e));
            contentPane.add(Bt_Regresar);
            Bt_Regresar.setBounds(new Rectangle(new Point(610, 45), Bt_Regresar.getPreferredSize()));

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
        private JLabel label2;
        private JLabel label3;
        private JLabel label4;
        private JLabel label5;
        private JTextField Categoria_Field;
        private JButton Bt_Agregar;
        private JLabel label6;
        private JTextField Eliminar_Field;
        private JButton Bt_Eliminar;
        private JScrollPane scrollPane1;
        private JTable Tb_Categorias;
        private JButton Bt_Regresar;
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }
