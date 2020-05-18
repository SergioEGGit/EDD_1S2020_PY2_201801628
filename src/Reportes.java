
    import Metodos.Variables;

    import javax.imageio.ImageIO;
    import javax.swing.*;
    import javax.swing.event.ChangeEvent;
    import javax.swing.event.ChangeListener;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.geom.AffineTransform;
    import java.awt.image.BufferedImage;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.net.MalformedURLException;

    public class Reportes extends JFrame
    {
       public Reportes()
       {
           ImagePanel Panel = new ImagePanel();
           ImageZoom Zoom = new ImageZoom(Panel);
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           getContentPane().add(Zoom.getUIPanel(), "North");
           getContentPane().add(new JScrollPane(Panel));
           setSize(500,500);
           setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
           setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           setLocationRelativeTo(null);
           setResizable(true);
           setTitle("Reportes");
       }

       class ImagePanel extends JPanel
       {
           BufferedImage Imagen;
           double Scale;

           public ImagePanel()
           {
               loadImage();
               Scale = 1.0;
               setBackground(Color.black);
           }

           protected void paintComponent(Graphics Grafica)
           {
               super.paintComponent(Grafica);
               Graphics2D Grafica2D = (Graphics2D) Grafica;
               Grafica2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
               int W = getWidth();
               int H = getHeight();
               int ImageWidth = Imagen.getWidth();
               int ImageHeight = Imagen.getHeight();
               double X = (W - Scale * ImageWidth) / 2;
               double Y = (H - Scale * ImageHeight) / 2;
               AffineTransform At = AffineTransform.getTranslateInstance(X, Y);
               At.scale(Scale, Scale);
               Grafica2D.drawRenderedImage(Imagen, At);
           }

           public Dimension getPreferredSize()
           {
               int W = (int) (Scale * Imagen.getWidth());
               int H = (int) (Scale * Imagen.getHeight());
               return new Dimension(W, H);
           }

           public void setScale(double S)
           {
               Scale = S;
               revalidate();
               repaint();
           }

           private void loadImage()
           {
               try
               {
                   Thread.sleep(3000);
               }
               catch (InterruptedException e)
               {
                   e.printStackTrace();
               }

              try
              {
                  Imagen = ImageIO.read(new FileInputStream(Variables.NombreReporte));
              }
              catch (MalformedURLException M)
              {
                  System.out.println("Error Con La Imagen: " + M.getMessage());
                  Variables.GenereReporte = false;
              }
              catch (IOException Ex)
              {
                  System.out.println("Error En Lectura: " + Ex.getMessage());
                  Variables.GenereReporte = false;
              }
           }
       }

       class ImageZoom
       {
           ImagePanel ImagenPanel;

           public ImageZoom(ImagePanel Ip)
           {
               ImagenPanel = Ip;
           }

           public JPanel getUIPanel()
           {
               SpinnerNumberModel Model = new SpinnerNumberModel(1.0, 0.1, 2.0, .1);
               final JSpinner Spinner = new JSpinner(Model);
               Button Bt_Regresar = new Button();
               Bt_Regresar.setLabel("Regresar");
               Bt_Regresar.setFont(new Font("Arial", Font.BOLD, 16));
               Bt_Regresar.setForeground(new Color(0, 153, 255));
               Bt_Regresar.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e)
                   {
                       dispose();
                   }
               });
               Spinner.setFont(new Font("Arial", Font.BOLD, 16));
               Spinner.setForeground(new Color(102, 102, 255));
               Spinner.setPreferredSize(new Dimension(45, Spinner.getPreferredSize().height));
               Spinner.addChangeListener(new ChangeListener()
               {
                   public void stateChanged(ChangeEvent e)
                   {
                       float Scale = ((Double) Spinner.getValue()).floatValue();
                       ImagenPanel.setScale(Scale);
                   }
               });
               JPanel Panel = new JPanel();
               JLabel Scala = new JLabel("Scale: ");
               Scala.setFont(new Font("Arial", Font.BOLD, 18));
               Scala.setForeground(new Color(0, 153, 255));
               Panel.add(Scala);
               Panel.add(Spinner);
               Panel.add(Bt_Regresar);
               return Panel;
           }
       }
    }
