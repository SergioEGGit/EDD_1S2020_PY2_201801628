
    package Metodos;

    import javax.swing.*;
    import java.io.*;

    public class GenerarReportes
    {
        private static String TipoReporte;

        public GenerarReportes(String tipoReporte, String Cadena)
        {
            TipoReporte = tipoReporte;
            GenerarArchivoDot(Cadena);
            GenerarGrafica(Cadena);
        }

        public static synchronized void GenerarArchivoDot(String Cadena)
        {
            File Archivo;
            FileWriter Writer;

            try
            {
                String Ruta = System.getProperty("user.dir") + "\\" + TipoReporte + ".txt";
                Archivo = new File(Ruta);
                Writer = new FileWriter(Archivo);
                BufferedWriter Buffer = new BufferedWriter(Writer);
                PrintWriter Print = new PrintWriter(Buffer);
                Print.write(Cadena + "\n");
                Print.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error Al Escribir El Archivo", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }

        public static synchronized void GenerarGrafica(String Cadena)
        {
            try
            {
                ProcessBuilder Builder;

                String FileOutput = System.getProperty("user.dir") + "\\" + TipoReporte + ".png";
                String FileInput = System.getProperty("user.dir") + "\\" + TipoReporte + ".txt";

                Builder = new ProcessBuilder("dote", "-Tpng", "-o", FileOutput, FileInput);
                Builder.redirectErrorStream(true);
                Builder.start();

                try
                {
                    Thread.sleep(9000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                Variables.GenereReporte = true;
                Variables.NombreReporte = FileOutput;
            }
            catch (IOException e)
            {
                String Type = "png";
                String RepresentationType = "dot";
                String FileOutput = System.getProperty("user.dir") + "\\" + TipoReporte;
                String FileInput = System.getProperty("user.dir") + "\\" + TipoReporte + ".txt";

                GraphViz GenerarGrafica = new GraphViz();
                GenerarGrafica.addln(Cadena);
                GenerarGrafica.increaseDpi();

                File Output = new File(FileOutput + "." + Type);
                GenerarGrafica.writeGraphToFile(GenerarGrafica.getGraph(GenerarGrafica.getDotSource(), Type, RepresentationType), Output);

                try
                {
                    Thread.sleep(6000);
                }
                catch (Exception Ex)
                {
                    Ex.printStackTrace();
                }
                Variables.NombreReporte = FileOutput + "." + Type;

                if(!Variables.GenereReporte)
                {
                    JOptionPane.showMessageDialog(null, "El Reporte No Se Pudo Generar Con Exito \nVerifique Que Graphviz Se Encuentre Configurado De Manera Correcta", "Error!", JOptionPane.ERROR);
                }
            }
        }
    }
