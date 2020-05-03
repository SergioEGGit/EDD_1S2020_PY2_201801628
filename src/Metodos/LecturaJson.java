
    package Metodos;

    import org.json.simple.JSONArray;
    import org.json.simple.JSONObject;
    import org.json.simple.parser.JSONParser;
    import Metodos.Variables;
    import Metodos.MD5;
    import Modelos.Usuarios;
    import javax.swing.*;
    import javax.swing.filechooser.FileNameExtensionFilter;
    import java.io.File;
    import java.io.FileReader;

    public class LecturaJson
    {
        public static void JSONUsuarios(JFrame Este)
        {
            JFileChooser Ruta = new JFileChooser();
            Ruta.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            FileNameExtensionFilter JSONFilter = new FileNameExtensionFilter("Archivos JSON", "json");
            Ruta.setFileFilter(JSONFilter);

            int Result = Ruta.showOpenDialog(Este);

            if(Result != JFileChooser.CANCEL_OPTION)
            {
                File ArchivoName = Ruta.getSelectedFile();

                if((ArchivoName == null) || (ArchivoName.getName().equals("")))
                {
                    JOptionPane.showMessageDialog(null, "Error", "No Se Selecciono Ningún Archivo", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    try
                    {
                        JSONParser Parser = new JSONParser();
                        JSONObject ObjectUser = (JSONObject) Parser.parse(new FileReader(ArchivoName.getAbsoluteFile()));
                        JSONArray Usuarios = (JSONArray) ObjectUser.get("Usuarios");

                        for(Object User: Usuarios)
                        {
                            if(User != null)
                            {
                                JSONObject Usuario = (JSONObject) User;
                                Long CarnetLarge = (long) Usuario.get("Carnet");
                                int Carnet = CarnetLarge.intValue();
                                String Nombre = (String) Usuario.get("Nombre");
                                String Apellido = (String) Usuario.get("Apellido");
                                String Carrera = (String) Usuario.get("Carrera");
                                String Contraseña = (String) Usuario.get("Password");

                                String Pass = MD5.EncriptarPassword(Contraseña);

                                Usuarios NuevoUsuario = new Usuarios(Carnet, Nombre, Apellido, Carrera, Pass);
                                Variables.TablaHashUsuarios.InsertarUsuarios(NuevoUsuario);

                                JOptionPane.showMessageDialog(null, "Usuarios Agregados Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                    catch(Exception Ex)
                    {
                        JOptionPane.showMessageDialog(null, "Error Al Abrir El Archivo Seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        }
    }
