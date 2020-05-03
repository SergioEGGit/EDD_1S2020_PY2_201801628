
    package Estructuras;

    import Metodos.Variables;
    import Modelos.Usuarios;

    import javax.swing.*;

    public class TablaHashUsuarios
    {
        private NodoHashUsuarios[] TablaUsuarios;
        private int Size;

        public TablaHashUsuarios(int Size)
        {
            this.Size = Size;
            this.TablaUsuarios = new NodoHashUsuarios[this.Size];
        }

        public int FuncionHash(int Carnet)
        {
            return Carnet % Size;
        }

        public void InsertarUsuarios(Usuarios NuevoUsuario)
        {
            int PosicionHash = FuncionHash(NuevoUsuario.getCarnet());

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
        }

        public Usuarios IngresarUsuarios(int Carnet, String Contraseña)
        {
            int PosicionHash = FuncionHash(Carnet);

            if(TablaUsuarios[PosicionHash] != null)
            {
                Usuarios UsuarioEncontrado = TablaUsuarios[PosicionHash].IngresarUsuarios(Carnet, Contraseña);

                if(UsuarioEncontrado != null)
                {
                    return UsuarioEncontrado;
                }
            }
            return null;
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

            if(TablaUsuarios[PosicionHash] != null)
            {
                return TablaUsuarios[PosicionHash].BorrarUsuarios(Carnet);
            }
            return false;
        }

        public void ModificarUsuarios(int Carnet, String Nombre, String Apellido, String Carrera, String Contraseña)
        {
            Usuarios UsuarioModificado = BuscarUsuarios(Carnet);

            if(UsuarioModificado != null)
            {
                UsuarioModificado.setApellido(Apellido);
                UsuarioModificado.setNombre(Nombre);
                UsuarioModificado.setCarrera(Carrera);
                UsuarioModificado.setPassword(Contraseña);
            }
            else
            {
                System.out.println("El Usuario No Existe");
            }
        }

        public void ReporteTablaHashUsuarios()
        {
            String Cadena = "";

            Cadena += "digraph G { \n";
            Cadena += "fontcolor = blue; \n fontsize = \"25\"; \n";
            Cadena += "label = \"Tabla Hash: Usuarios\"; \n";
            Cadena += "style = filled; \n charset = latin1; \n";
            Cadena += "bgcolor = white; \n color = red; \n";
            Cadena += "node[fillcolor = brown, fontcolor = blue, color = white, styled = filled, shape = component]; \n";

            for(int i = 0; i < Variables.TablaHashUsuarios.Size; i++)
            {
                if(TablaUsuarios[i] != null)
                {

                }
            }
        }
    }
