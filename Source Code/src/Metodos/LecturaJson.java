
    package Metodos;


    import Modelos.Libros;
    import Estructuras.NodoArbolAVL;
    import org.json.simple.JSONArray;
    import org.json.simple.JSONObject;
    import org.json.simple.parser.JSONParser;
    import Modelos.Usuarios;
    import javax.swing.*;
    import javax.swing.filechooser.FileNameExtensionFilter;
    import java.io.File;
    import java.io.FileInputStream;
    import java.io.InputStreamReader;
    import java.nio.charset.StandardCharsets;

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
                        JSONObject ObjectUser = (JSONObject) Parser.parse(new InputStreamReader(new FileInputStream(ArchivoName.getAbsoluteFile()), StandardCharsets.UTF_8));
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
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Usuarios Agregados Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch(Exception Ex)
                    {
                        JOptionPane.showMessageDialog(null, "Error Al Abrir El Archivo Seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        }

        public static void JSONLibros(JFrame Este)
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
                       int Contador = 1;
                       JSONParser Parser = new JSONParser();
                       JSONObject ArchivoUsers = (JSONObject) Parser.parse(new InputStreamReader(new FileInputStream(ArchivoName.getAbsoluteFile()), StandardCharsets.UTF_8));
                       JSONArray Libros = (JSONArray) ArchivoUsers.get("libros");

                       for(Object Libro: Libros)
                       {
                           if(Libro != null)
                           {
                               JSONObject LibroActual = (JSONObject) Libro;
                               Long ISBNLong = (long) LibroActual.get("ISBN");
                               int ISBN = ISBNLong.intValue();
                               Long YearLong = (long) LibroActual.get("Año");
                               int  Año = YearLong.intValue();
                               String Idioma = (String) LibroActual.get("Idioma");
                               String Titulo = (String) LibroActual.get("Titulo");
                               String Editorial = (String) LibroActual.get("Editorial");
                               String Autor = (String) LibroActual.get("Autor");
                               Long EdicionLong = (long) LibroActual.get("Edicion");
                               int Edicion = EdicionLong.intValue();
                               String Categoria = (String) LibroActual.get("Categoria");
                               int Carnet = Variables.NumeroCarnetUsuarioLog;
                               Libros NuevoLibro = new Libros(ISBN, Titulo, Autor, Editorial, Año, Edicion, Categoria, Idioma, Carnet);

                               NodoArbolAVL ArbolAVL = Variables.ArbolAVLCategorias.InicializarBuscarCategoriaArbolAVL(Categoria);

                               if(ArbolAVL != null)
                               {
                                   Variables.ArbolAVLCategorias.InsertarLibroArbolAVL(ArbolAVL, NuevoLibro);
                               }
                               else
                               {
                                   Variables.ArbolAVLCategorias.InicializarCategoriaArbolAVL(Categoria, Carnet);
                                   ArbolAVL = Variables.ArbolAVLCategorias.InicializarBuscarCategoriaArbolAVL(Categoria);
                                   Variables.ArbolAVLCategorias.InsertarLibroArbolAVL(ArbolAVL, NuevoLibro);
                               }
                           }
                       }

                       JOptionPane.showMessageDialog(null, "Libros Agregados Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch(Exception Ex)
                    {
                        JOptionPane.showMessageDialog(null, "Error Al Abrir El Archivo Seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        }
    }
