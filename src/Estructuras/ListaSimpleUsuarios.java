
    package Estructuras;

    import Modelos.Usuarios;
    import Metodos.Variables;

    public class ListaSimpleUsuarios
    {
        private Usuarios Us;

        public Usuarios getUs()
        {
            return Us;
        }

        public void setUs(Usuarios us)
        {
            Us = us;
        }

        public boolean ListaVacia()
        {
            if(getUs() != null)
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
                setUs(NuevoUsuario);
            }
            else
            {
                NuevoUsuario.setSgte(getUs());
                setUs(NuevoUsuario);
            }
        }

        public boolean BorrarUsuarioLS(int Carnet)
        {
            Usuarios Aux = getUs();
            Usuarios Aux2 = getUs();
            boolean Borrado = false;

            while(Aux != null)
            {
                if(Carnet == Aux.getCarnet() && Aux == getUs())
                {
                    setUs(Aux.getSgte());
                    Variables.GenerarBloquesData.EliminarUsuarioBloques(Aux);
                    Borrado = true;
                    break;
                }
                else if(Carnet == Aux.getCarnet() && Aux != getUs())
                {
                    Aux2.setSgte(Aux.getSgte());
                    Variables.GenerarBloquesData.EliminarUsuarioBloques(Aux);
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
            Usuarios Aux = getUs();

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

        public String TablaHashReporte(int Identificador)
        {
            String Cadena = "";

            Cadena += "subgraph Usuario" + Identificador + "\n { \n    rankdir = \"LR\"; \n    node[shape = rect color = brown1 fontcolor = chartreuse4]; \n";
            Usuarios Aux = getUs();

            while(Aux != null)
            {
                Cadena += Aux.getCarnet() + "[label = \"No.Carnet: " + Aux.getCarnet() + "\n Nombre: " + Aux.getNombre() + "\n Apellido: " + Aux.getApellido() + "\n Carrera: " + Aux.getCarrera() + "\n Password: " + Aux.getPassword() + "\"];\n";
                Usuarios Puntero = Aux.getSgte();

                if(Puntero != null)
                {
                    Cadena += Aux.getCarnet() + " -> " + Puntero.getCarnet() + "; \n";
                }
                Aux = Aux.getSgte();
            }

            Cadena += "} \n";
            return Cadena;
        }
    }
