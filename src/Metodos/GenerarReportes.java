
    package Metodos;

    import javax.swing.*;
    import java.io.*;
    import java.nio.charset.StandardCharsets;


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
            try
            {
                String Ruta = System.getProperty("user.dir") + "\\" + TipoReporte + ".txt";
                BufferedWriter Buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Ruta), StandardCharsets.UTF_8));
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
                String FileInput = "";
                String FileOutput = "";

                if(Variables.OsName.equals("Windows10") || Variables.OsName.equals("Windows8") || Variables.OsName.equals("Windows7") || Variables.OsName.equals("Windows"))
                {
                    FileOutput = System.getProperty("user.dir") + "\\" + TipoReporte + ".png";
                    FileInput = System.getProperty("user.dir") + "\\" + TipoReporte + ".txt";
                }
                else if(Variables.OsName.equals("Linux") || Variables.OsName.equals("MacOSX"))
                {
                    FileOutput = System.getProperty("user.dir") + "/" + TipoReporte + ".png";
                    FileInput = System.getProperty("user.dir") + "/" + TipoReporte + ".txt";
                }

                Builder = new ProcessBuilder("dot", "-Tpng", "-o", FileOutput, FileInput);
                Builder.redirectErrorStream(true);
                Builder.start();

                try
                {
                    Thread.sleep(10000);
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
                String FileOutput = "";
                String FileInput = "";

                if(Variables.OsName.equals("Windows10") || Variables.OsName.equals("Windows8") || Variables.OsName.equals("Windows7") || Variables.OsName.equals("Windows"))
                {
                    FileOutput = System.getProperty("user.dir") + "\\" + TipoReporte;
                    FileInput = System.getProperty("user.dir") + "\\" + TipoReporte + ".txt";
                }
                else if(Variables.OsName.equals("Linux") || Variables.OsName.equals("MacOSX"))
                {
                    FileOutput = System.getProperty("user.dir") + "/" + TipoReporte;
                    FileInput = System.getProperty("user.dir") + "/" + TipoReporte + ".txt";
                }

                GraphViz GenerarGrafica = new GraphViz();
                GenerarGrafica.addln(Cadena);
                GenerarGrafica.increaseDpi();

                File Output = new File(FileOutput + "." + Type);
                GenerarGrafica.writeGraphToFile(GenerarGrafica.getGraph(GenerarGrafica.getDotSource(), Type, RepresentationType), Output);

                try
                {
                    Thread.sleep(10000);
                }
                catch (Exception Ex)
                {
                    Ex.printStackTrace();
                }
                Variables.NombreReporte = FileOutput + "." + Type;
            }
        }
    }
