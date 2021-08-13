
    package Metodos;

    import javax.swing.*;

    import Estructuras.NodoArbolAVL;
    import Modelos.Libros;
    import Modelos.Usuarios;

    import java.io.BufferedReader;
    import java.io.File;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.net.*;
    import java.sql.Timestamp;
    import java.util.Enumeration;

    public class Metodos
    {
        public static boolean ValidarUsuario(String Usuario, String Pass)
        {
            boolean Bandera = false;

            if(Usuario.equals(""))
            {
                JOptionPane.showMessageDialog(null, "El Campo Usuario Es Obligatorio", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                int Carnet = Integer.parseInt(Usuario);

                if(Variables.TablaHashUsuarios.BuscarUsuarios(Carnet) == null)
                {
                    JOptionPane.showMessageDialog(null, "El Usuario No Existe En El Sistema", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    Usuarios UsuarioBuscar = Variables.TablaHashUsuarios.BuscarUsuarios(Carnet);

                    if(UsuarioBuscar.getPassword().equals(MD5.EncriptarPassword(Pass)))
                    {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + UsuarioBuscar.getNombre() + " " + UsuarioBuscar.getApellido(), "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                        Variables.NumeroCarnetUsuarioLog = Carnet;
                        Variables.EstoyEnLogin = false;
                        Bandera = true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "La Contraseña Es Incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            return Bandera;
        }

        public static boolean RegistrarUsuario(int Carnet, String Nombre, String Apellido, String Carrera, String Contraseña)
        {
            Contraseña = MD5.EncriptarPassword(Contraseña);
            Usuarios NuevoUsuario = new Usuarios(Carnet, Nombre, Apellido, Carrera, Contraseña);
            Variables.TablaHashUsuarios.InsertarUsuarios(NuevoUsuario);

            return Variables.ExisteUsuarios;
        }

        public static void ModificarUsuario(int Carnet, String Nombre, String Apellido, String Carrera, String Contraseña)
        {
            Variables.TablaHashUsuarios.ModificarUsuarios(Carnet, Nombre, Apellido, Carrera, Contraseña);
            JOptionPane.showMessageDialog(null, "Usuario Modificado Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
        }

        public static void AgregarLibros(int ISBN, String Titulo, String Autor, String Editorial, int Año, int Edicion, String Categoria, String Idioma, int Carnet_Usuario_Creador)
        {
            Libros NuevoLibro = new Libros(ISBN, Titulo, Autor, Editorial, Año, Edicion, Categoria, Idioma, Carnet_Usuario_Creador);

            NodoArbolAVL Categorias = Variables.ArbolAVLCategorias.InicializarBuscarCategoriaArbolAVL(Categoria);

            if(Categorias != null)
            {
                Variables.ArbolAVLCategorias.InsertarLibroArbolAVL(Categorias, NuevoLibro);
            }
            else
            {
                Variables.ArbolAVLCategorias.InicializarCategoriaArbolAVL(Categoria, Carnet_Usuario_Creador);
                Categorias = Variables.ArbolAVLCategorias.InicializarBuscarCategoriaArbolAVL(Categoria);
                Variables.ArbolAVLCategorias.InsertarLibroArbolAVL(Categorias, NuevoLibro);
            }
        }

        public static void CalcularNumeroIndex()
        {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            String Ruta = "";

            System.out.println(Variables.OsName);

            if(Variables.OsName.equals("Windows10") || Variables.OsName.equals("Windows8") || Variables.OsName.equals("Windows7") || Variables.OsName.equals("Windows"))
            {
                Ruta = System.getProperty("user.dir") + "\\Bloques";
            }
            else if(Variables.OsName.equals("Linux") || Variables.OsName.equals("MacOSX"))
            {
                Ruta = System.getProperty("user.dir") + "/Bloques";
            }

            File Archivo = new File(Ruta);

            if(Archivo.exists())
            {
                String[] Array = Archivo.list();

                if(Array == null || Array.length == 0)
                {
                    System.out.println("No hay elementos dentro de la carpeta actual");
                }
                else
                {
                    for(int i = 0; i < Array.length; i++)
                    {
                        System.out.println(Array[i]);
                        GenerarBloquesJSON.ObtenerBloques(Ruta + "\\" + Array[i]);
                        Variables.IndexBloque = i + 1;
                    }
                    System.out.println("Siguiente Bloque: "+ Variables.IndexBloque);
                }
            }
            else
            {
                System.out.println("No Existe");
                Archivo.mkdir();
            }
        }

        public static void ObtenerExternalIp()
        {
            try
            {
                URL IpAdress = new URL("http://myexternalip.com/raw");
                BufferedReader Entrada = new BufferedReader(new InputStreamReader(IpAdress.openStream()));

                Variables.ExternalIp = Entrada.readLine();
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        public static void ObtenerInternalIp()
        {
            try
            {
                Variables.InternalIp = InetAddress.getLocalHost().getHostAddress();
            }
            catch (UnknownHostException e)
            {
                e.printStackTrace();
            }
        }
    }
