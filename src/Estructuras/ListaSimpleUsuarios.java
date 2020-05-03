
    package Estructuras;
    import Modelos.Usuarios;

    public class ListaSimpleUsuarios
    {
        private Usuarios Us;

        public boolean ListaVacia()
        {
            if(Us != null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }

        public void InsertarUsuarioLS(Usuarios NuevoUsuario)
        {
            if(ListaVacia())
            {
                Us = NuevoUsuario;
            }
            else
            {
                NuevoUsuario.setSgte(Us);
                Us = NuevoUsuario;
            }
        }

        public boolean BorrarUsuarioLS(int Carnet)
        {
            Usuarios Aux = Us;
            Usuarios Aux2 = Us;
            boolean Borrado = false;

            while(Aux != null)
            {
                if(Carnet == Aux.getCarnet() && Aux == Us)
                {
                    Us = Aux.getSgte();
                    Borrado = true;
                    break;
                }
                else if(Carnet == Aux.getCarnet() && Aux != Us)
                {
                    Aux2.setSgte(Aux.getSgte());
                    Aux = null;
                    Borrado = true;
                    break;
                }
                Aux2 = Aux;
                Aux = Aux.getSgte();
            }
            return Borrado;
        }

        public Usuarios BuscarUsuarioLS(int Carnet)
        {
            Usuarios Aux = Us;

            while(Aux != null)
            {
                if(Carnet == Aux.getCarnet())
                {
                    return Aux;
                }
                Aux = Aux.getSgte();
            }
            return null;
        }

        public Usuarios IngresoUsuarioLS(int Carnet, String Contraseña)
        {
            Usuarios Aux = Us;

            while(Aux != null)
            {
                if(Carnet == Aux.getCarnet() && Contraseña.equals(Aux.getPassword()))
                {
                    return Aux;
                }
                Aux = Aux.getSgte();
            }
            return null;
        }
    }
