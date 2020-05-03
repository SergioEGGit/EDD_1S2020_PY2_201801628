
    package Metodos;

    import javax.swing.*;
    import Metodos.Variables;
    import Metodos.MD5;
    import Modelos.Usuarios;

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
            boolean Bandera = false;

            if(Variables.TablaHashUsuarios.BuscarUsuarios(Carnet) != null)
            {
                JOptionPane.showMessageDialog(null, "El Carnet Indicado Ya Existe En El Sistema", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                Contraseña = MD5.EncriptarPassword(Contraseña);
                Usuarios NuevoUsuario = new Usuarios(Carnet, Nombre, Apellido, Carrera, Contraseña);
                Variables.TablaHashUsuarios.InsertarUsuarios(NuevoUsuario);
                JOptionPane.showMessageDialog(null, "Usuario Agregado Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                Bandera = true;
            }
            return Bandera;
        }

        public static void ModificarUsuario(int Carnet, String Nombre, String Apellido, String Carrera, String Contraseña)
        {
            Variables.TablaHashUsuarios.ModificarUsuarios(Carnet, Nombre, Apellido, Carrera, Contraseña);
            JOptionPane.showMessageDialog(null, "Usuario Modificado Con EXito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
