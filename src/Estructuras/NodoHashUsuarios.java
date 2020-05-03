
    package Estructuras;

    import Modelos.Usuarios;

    public class NodoHashUsuarios
    {
        private ListaSimpleUsuarios ListaUsuarios;

        public ListaSimpleUsuarios getListaUsuarios()
        {
            return ListaUsuarios;
        }

        public void setListaUsuarios(ListaSimpleUsuarios listaUsuarios)
        {
            ListaUsuarios = listaUsuarios;
        }

        public NodoHashUsuarios()
        {
            ListaUsuarios = new ListaSimpleUsuarios();
        }

        public void InsetarUsuarios(Usuarios NuevoUsuario)
        {
            ListaUsuarios.InsertarUsuarioLS(NuevoUsuario);
        }

        public boolean BorrarUsuarios(int Carnet)
        {
            return ListaUsuarios.BorrarUsuarioLS(Carnet);
        }

        public Usuarios BuscarUsuarios(int Carnet)
        {
            return ListaUsuarios.BuscarUsuarioLS(Carnet);
        }

        public Usuarios IngresarUsuarios(int Carnet, String Contraseña) {return ListaUsuarios.IngresoUsuarioLS(Carnet, Contraseña);}
    }