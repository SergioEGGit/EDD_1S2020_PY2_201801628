
    import java.awt.event.*;

    import Metodos.Variables;
    import Modelos.Libros;

    import java.awt.*;
    import java.util.ArrayList;
    import javax.swing.*;
    import javax.swing.table.*;

    /*
     *  Created by JFormDesigner on Sun May 10 11:11:00 CDT 2020
     */

    /**
     * @author Sergio EG
     */

    public class CatalogoLibros extends JFrame
    {
        ArrayList<Libros> ArrayLibros = new ArrayList<Libros>();

        public CatalogoLibros()
        {
            initComponents();
            ArrayLibros = Variables.ArbolAVLCategorias.ListarTodosLosLibrosArbolAVL();
            ObtenerLibros();
            ObtenerCategorias();
        }

        public void ObtenerLibros()
        {
            DefaultTableModel Modelo = new DefaultTableModel();
            Modelo.addColumn("ISBN");
            Modelo.addColumn("Título");
            Modelo.addColumn("Autor");
            Modelo.addColumn("Editorial");
            Modelo.addColumn("Año");
            Modelo.addColumn("Edición");
            Modelo.addColumn("Categoría");
            Modelo.addColumn("Idioma");
            Modelo.addColumn("Usuario_Creador");

            for(Libros Libro: ArrayLibros)
            {
                if (Libro != null)
                {
                    Object[] Fila = new Object[]
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
                    Modelo.addRow(Fila);
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

        private void ObtenerCategorias()
        {
            ArrayList<String> Catalogo = Variables.ArbolAVLCategorias.IniciarCatalogoArbolAVL();

            Cb_Categorias.addItem("Todos Los Libros");

            for(String Categoria: Catalogo)
            {
                if(Categoria != null)
                {
                    Cb_Categorias.addItem(Categoria);
                }
            }
        }

        private void Cb_CategoriasItemStateChanged(ItemEvent e)
        {
            try
            {
                String Cadena = Cb_Categorias.getSelectedItem().toString();

                if(Cadena.equals("Todos Los Libros"))
                {
                    ArrayLibros = Variables.ArbolAVLCategorias.ListarTodosLosLibrosArbolAVL();
                }
                else
                {
                    ArrayLibros = Variables.ArbolAVLCategorias.SeleccionarCategoriaLibrosAVL(Cadena);
                }
                ObtenerLibros();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        private void Bt_RegresarActionPerformed(ActionEvent e)
        {
            this.dispose();;
        }

        private void initComponents()
        {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            label2 = new JLabel();
            Cb_Categorias = new JComboBox();
            label4 = new JLabel();
            label3 = new JLabel();
            label1 = new JLabel();
            scrollPane1 = new JScrollPane();
            Tb_Libros = new JTable();
            Bt_Regresar = new JButton();

            //======== this ========
            Container contentPane = getContentPane();
            contentPane.setLayout(null);
            contentPane.add(label2);
            label2.setBounds(1115, 645, 50, 65);

            //---- Cb_Categorias ----
            Cb_Categorias.setForeground(new Color(0, 153, 255));
            Cb_Categorias.setFont(new Font("Arial", Font.BOLD, 16));
            Cb_Categorias.addItemListener(e -> Cb_CategoriasItemStateChanged(e));
            contentPane.add(Cb_Categorias);
            Cb_Categorias.setBounds(635, 115, 180, 30);

            //---- label4 ----
            label4.setText("Categor\u00edas:");
            label4.setFont(new Font("Arial", Font.BOLD, 16));
            label4.setForeground(new Color(0, 153, 255));
            contentPane.add(label4);
            label4.setBounds(535, 120, 89, 19);

            //---- label3 ----
            label3.setText("Seleccione Una Manera\n De Filtrar Los Libros: ");
            label3.setFont(new Font("Arial", Font.BOLD, 16));
            label3.setForeground(new Color(102, 102, 255));
            contentPane.add(label3);
            label3.setBounds(495, 70, 348, 19);

            //---- label1 ----
            label1.setText("Cat\u00e1logo De Libros");
            label1.setFont(new Font("Arial", Font.BOLD, 22));
            label1.setForeground(new Color(102, 102, 255));
            contentPane.add(label1);
            label1.setBounds(560, 20, 195, 26);

            //======== scrollPane1 ========
            {

                //---- Tb_Libros ----
                Tb_Libros.setModel(new DefaultTableModel(1, 0));
                Tb_Libros.setBackground(Color.white);
                Tb_Libros.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
                Tb_Libros.setGridColor(new Color(255, 51, 51));
                Tb_Libros.setForeground(new Color(0, 0, 153));
                Tb_Libros.setFont(new Font("Arial", Font.PLAIN, 14));
                scrollPane1.setViewportView(Tb_Libros);
            }
            contentPane.add(scrollPane1);
            scrollPane1.setBounds(20, 180, 1260, 455);

            //---- Bt_Regresar ----
            Bt_Regresar.setText("Regresar");
            Bt_Regresar.setFont(new Font("Arial", Font.BOLD, 16));
            Bt_Regresar.setForeground(new Color(0, 153, 255));
            Bt_Regresar.addActionListener(e -> Bt_RegresarActionPerformed(e));
            contentPane.add(Bt_Regresar);
            Bt_Regresar.setBounds(990, 75, 135, Bt_Regresar.getPreferredSize().height);

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
        private JLabel label2;
        private JComboBox Cb_Categorias;
        private JLabel label4;
        private JLabel label3;
        private JLabel label1;
        private JScrollPane scrollPane1;
        private JTable Tb_Libros;
        private JButton Bt_Regresar;
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }
