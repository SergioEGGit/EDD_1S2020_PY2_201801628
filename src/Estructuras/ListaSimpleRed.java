
    package Estructuras;

    import Metodos.GenerarReportes;

    public class ListaSimpleRed
    {
        private NodoListaSimple Inicio;

        public NodoListaSimple getInicio()
        {
            return Inicio;
        }

        public ListaSimpleRed()
        {

        }

        public boolean ListaVacia()
        {
            return Inicio == null;
        }

        public void InsertarNodoRedListaSimple(NodoListaSimple NuevoNodo)
        {
            if(ListaVacia())
            {
                Inicio = NuevoNodo;
            }
            else
            {
                NuevoNodo.setSgte(Inicio);
                Inicio = NuevoNodo;
            }
        }

        public void EliminarNodoRedListaSimple(NodoListaSimple NodoActual)
        {
            NodoListaSimple Aux = Inicio;
            NodoListaSimple Aux2 = Inicio;

            while(Aux != null)
            {
                if(Aux.getPuerto() == NodoActual.getPuerto())
                {
                    if(Aux.getPuerto() != Inicio.getPuerto())
                    {
                        Aux2.setSgte(Aux.getSgte());
                        Aux = null;
                        break;
                    }
                    else
                    {
                        if(Aux.getSgte() != null)
                        {
                            Inicio = Aux.getSgte();
                        }
                        else
                        {
                            Inicio = null;
                        }
                    }
                    break;
                }

                Aux2 = Aux;
                Aux = Aux.getSgte();
            }
        }

        public NodoListaSimple BuscarNodoRedListaSimple(int Puerto)
        {
            NodoListaSimple Aux = Inicio;

            while(Aux != null)
            {
                if(Aux.getPuerto() == Puerto)
                {
                    return Aux;
                }
                Aux = Aux.getSgte();
            }
            return null;
        }

        public void GraficarRedListaSimple()
        {
            String Cadena = "";

            Cadena += "digraph G { \n rankdir = \"LR\"; \n node[shape = rect, fontname = Arial, color = chartreuse1, fontcolor = firebrick]; \n";

            NodoListaSimple Aux = Inicio;

            while(Aux != null)
            {
                Cadena += "A" + Aux.getPuerto() + "[label = \" Puerto: " + Aux.getPuerto() + "\"]; \n";

                if(Aux.getSgte() != null)
                {
                    Cadena += "A" + Aux.getPuerto() + " -> A" + Aux.getSgte().getPuerto() + "; \n";
                }
                Aux = Aux.getSgte();
            }

            Cadena += "}";

            GenerarReportes ListaSimpleRed = new GenerarReportes("ReporteNodoDeRedListaSimple", Cadena);
        }
    }
