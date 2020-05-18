
    package Estructuras;

    import Metodos.Variables;
    import Modelos.Libros;
    import java.util.ArrayList;
    import java.util.LinkedList;
    import java.util.Queue;

    public class ArbolBLibros
    {
        private int OrdenArbol;
        private ArrayList<Libros> LibroArbolB;
        private NodoArbolB Nodo;
        private int Contador = 0;

        public void setOrdenArbol(int ordenArbol)
        {
            OrdenArbol = ordenArbol;
        }

        public ArrayList<Libros> getLibroArbolB()
        {
            return LibroArbolB;
        }

        public void setNodo(NodoArbolB nodo)
        {
            Nodo = nodo;
        }

        public ArbolBLibros(int Orden)
        {
            setOrdenArbol(Orden);
            setNodo(new NodoArbolB(Orden, true));
            LibroArbolB = new ArrayList<Libros>();
        }

        public void InsertarArbolB(Libros Libro)
        {
            if(LibroArbolB.contains(Libro))
            {
                System.out.print("Soy El Libro");
            }
            else
            {
                LibroArbolB.add(Libro);
                Variables.GenerarBloquesData.CrearLibroBloques(Libro);
                SubNodos SubNodoAuxiliar = Nodo.InsertarNodoArbolB(Libro);

                if(SubNodoAuxiliar != null)
                {
                    NodoArbolB NuevoNodo = new NodoArbolB(OrdenArbol, SubNodoAuxiliar);
                    Nodo = NuevoNodo;
                }
            }
        }

        public String GenerarGraficaArbolB(NodoArbolB NodoActual)
        {
            String Cadena = "";
            int ContadorAux = Contador;

            if(NodoActual != null)
            {
                Cadena += "parent" + Contador + "[label = <\n <table border = '1' cellborder = '1'> \n";

                int ContadorAux2 = 0;
                ContadorAux = Contador;

                Cadena += "<tr>";
                Cadena += "<td port ='port_" + ContadorAux + "" + ContadorAux2 + "'>H" + ContadorAux2 + "</td>";
                ContadorAux2++;

                for(Libros Libro: NodoActual.getLibrosNodo())
                {
                    if(Libro != null)
                    {
                        Cadena += "<td ><table cellspacing='0'>"
                                +"<tr><td>ISBN: "+ Libro.getISBN() + "</td></tr>"
                                +"<tr><td>Título: "+ Libro.getTitulo().replaceAll(";", "").replaceAll("&", "&#38;")+"</td></tr>"
                                +"</table></td>";
                        Cadena += "<td port='port_" + ContadorAux + "" + ContadorAux2 + "'>N" + ContadorAux2 + "</td>";
                        ContadorAux2++;
                    }
                }

                Cadena += "</tr>";
                Cadena += "</table> \n >]; \n";

                ContadorAux2 = 0;
                Contador++;

                for(NodoArbolB SubNodo: NodoActual.getSubNodo())
                {
                    if(SubNodo != null)
                    {
                        Cadena += "parent" + ContadorAux + ":port_" + ContadorAux2 + " -> parent" + Contador + "; \n";
                        Cadena += GenerarGraficaArbolB(SubNodo);
                        ContadorAux2++;
                    }
                }
            }
            return Cadena;
        }

        public String MostrarGrafoArbolB()
        {
            String Cadena = "";
            Cadena += GenerarGraficaArbolB(Nodo);
            return Cadena;
        }

        public ArrayList<Libros> MostrarTodosLosLibrosArbolB()
        {
            Queue<NodoArbolB> Auxiliar = new LinkedList<NodoArbolB>();
            ArrayList<NodoArbolB> Pop = new ArrayList<NodoArbolB>();

            Pop.add(Nodo);
            Auxiliar.add(Nodo);

            while(!Auxiliar.isEmpty())
            {
                NodoArbolB Aux = Auxiliar.poll();

                for(NodoArbolB Hijos: Aux.getSubNodo())
                {
                    Hijos.setNivel(Aux.getNivel() + 1);
                    Pop.add(Hijos);
                    Auxiliar.add(Hijos);
                }
            }

            ArrayList<Libros> ArrayLibros = new ArrayList<Libros>();

            for(NodoArbolB Node: Pop)
            {
                if(Node != null)
                {
                    for(Libros Libro: Node.getLibrosNodo())
                    {
                        if(Libro != null)
                        {
                            ArrayLibros.add(Libro);
                        }
                    }
                }
            }
            return ArrayLibros;
        }

        protected Libros EliminarLibroArbolB(int ISBN, int Carnet)
        {
            for (Libros LibroActual: LibroArbolB)
            {
                if(LibroActual != null)
                {
                    if(LibroActual.getISBN() == ISBN && LibroActual.getCarnet_Usuario_Creador() == Carnet)
                    {
                        if(LibroArbolB.contains(LibroActual))
                        {
                            NodoArbolB NuevoNodo = new NodoArbolB(OrdenArbol, true);
                            SubNodos SubNodoAuxilar;
                            Libros LibroEliminar = new Libros();
                            LibroEliminar = LibroActual;
                            Variables.GenerarBloquesData.EliminarLibroBloques(LibroActual);
                            LibroArbolB.remove(LibroEliminar);

                            for(Libros Int : LibroArbolB)
                            {
                                SubNodoAuxilar = NuevoNodo.InsertarNodoArbolB(Int);
                                if(SubNodoAuxilar != null)
                                {
                                    NuevoNodo = SubNodoAuxilar.toRaiz();
                                }
                            }
                            Nodo = NuevoNodo;
                            return LibroActual;
                        }
                        else
                        {
                            return null;
                        }
                    }
                }
            }
            return null;
        }


    }
