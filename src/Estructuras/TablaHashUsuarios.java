
    package Estructuras;

    import Metodos.Variables;
    import Modelos.Usuarios;
    import Metodos.GenerarReportes;

    import javax.swing.*;
    import java.util.ArrayList;
    import java.util.Iterator;

    public class TablaHashUsuarios
    {
        private NodoHashUsuarios[] TablaUsuarios;
        private int Size;
        private ArrayList<Integer> CarnetArray = new ArrayList<Integer>();

        public TablaHashUsuarios(int size)
        {
            Size = size;
            TablaUsuarios = new NodoHashUsuarios[Size];
        }

        public int FuncionHash(int Carnet)
        {
            return Carnet % Size;
        }

        public void InsertarUsuarios(Usuarios NuevoUsuario)
        {
            int PosicionHash = FuncionHash(NuevoUsuario.getCarnet());

            if(CarnetArray.contains(NuevoUsuario.getCarnet()))
            {
                JOptionPane.showMessageDialog(null, "El Usuario Indicado Ya Existe En El Sistema \nCarnet: " + NuevoUsuario.getCarnet() + "\nNombre: " + NuevoUsuario.getNombre(), "Advertencia!", JOptionPane.WARNING_MESSAGE);

                Variables.ExisteUsuarios = false;
            }
            else
            {
                CarnetArray.add(NuevoUsuario.getCarnet());

                if(TablaUsuarios[PosicionHash] != null)
                {
                    TablaUsuarios[PosicionHash].InsetarUsuarios(NuevoUsuario);
                }
                else
                {
                    NodoHashUsuarios NuevoNodo = new NodoHashUsuarios();
                    TablaUsuarios[PosicionHash] = NuevoNodo;
                    TablaUsuarios[PosicionHash].InsetarUsuarios(NuevoUsuario);
                }

                Variables.ExisteUsuarios = true;
            }
        }

        public Usuarios BuscarUsuarios(int Carnet)
        {
            int PosicionHash = FuncionHash(Carnet);

            if(TablaUsuarios[PosicionHash] != null)
            {
                Usuarios UsuarioBuscado = TablaUsuarios[PosicionHash].BuscarUsuarios(Carnet);

                if(UsuarioBuscado != null)
                {
                    return UsuarioBuscado;
                }
            }
            return null;
        }

        public boolean BorrarUsuarios(int Carnet)
        {
            int PosicionHash = FuncionHash(Carnet);

            Iterator F = CarnetArray.iterator();

            String CarnetString = String.valueOf(Carnet);
            int Contador = 0;
            int PosicionArray = 0;

            while(F.hasNext())
            {
                if(F.next().toString().equals(CarnetString))
                {
                    PosicionArray = Contador;
                }
                Contador++;
            }

            CarnetArray.remove(PosicionArray);

            if(TablaUsuarios[PosicionHash] != null)
            {
                return TablaUsuarios[PosicionHash].BorrarUsuarios(Carnet);
            }
            return false;
        }

        public void ModificarUsuarios(int Carnet, String Nombre, String Apellido, String Carrera, String Contrasena)
        {
            Usuarios UsuarioModificado = BuscarUsuarios(Carnet);

            if(UsuarioModificado != null)
            {
                UsuarioModificado.setApellido(Apellido);
                UsuarioModificado.setNombre(Nombre);
                UsuarioModificado.setCarrera(Carrera);
                UsuarioModificado.setPassword(Contrasena);
            }
            else
            {
                System.out.println("El Usuario No Existe");
            }
        }

        public void ReporteTablaHashUsuarios()
        {
            String Cadena = "";
            Cadena += "digraph G \n { \n    rankdir = \"LR\"; \n    node[shape = rect color = brown fontcolor = lightbrown]; \n";
            Cadena += "parent[label =<\n <table border = '0' color = 'orange' cellspacing = '0' style = 'rounded' cellborder = '1'> \n";

            int Contador = 1;

            for(NodoHashUsuarios Tn: TablaUsuarios)
            {
                Cadena += "<tr><td bgcolor = \"lightblue\" port = 'port_" + Contador + "' HEIGHT = \"100\">" + Contador + "</td></tr>";
                Contador++;
            }

            Contador = 1;
            Cadena += "</table> \n >];";

            for(NodoHashUsuarios Tn: TablaUsuarios)
            {
                if(Tn != null)
                {
                    if(Tn.getListaUsuarios() != null)
                    {
                        Cadena += Tn.getListaUsuarios().TablaHashReporte(Contador);

                        if(Tn.getListaUsuarios().getUs() != null)
                        {
                            Cadena += "parent:port_" + Contador + " -> " + Tn.getListaUsuarios().getUs().getCarnet() + " [lhead = Usuario" + Contador + "]; \n";
                        }
                    }
                }
                Contador++;
            }

            Cadena += "}";

            GenerarReportes TablaHashUsuarios = new GenerarReportes("ReporteUsuariosTablaHash", Cadena);
        }
    }
