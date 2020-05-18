
    package Estructuras;

    import Modelos.Libros;
    import java.util.ArrayList;

    public class NodoArbolB
    {
        private int Orden;
        private boolean Hoja;
        private ArrayList<Libros> LibrosNodo;
        private ArrayList<NodoArbolB> SubNodo;
        private int Nivel;

        public int getOrden()
        {
            return Orden;
        }

        public void setOrden(int orden)
        {
            Orden = orden;
        }

        public void setHoja(boolean hoja)
        {
            Hoja = hoja;
        }

        public ArrayList<Libros> getLibrosNodo()
        {
            return LibrosNodo;
        }

        public void setLibrosNodo(ArrayList<Libros> librosNodo)
        {
            LibrosNodo = librosNodo;
        }

        public ArrayList<NodoArbolB> getSubNodo()
        {
            return SubNodo;
        }

        public void setSubNodo(ArrayList<NodoArbolB> subNodo)
        {
            SubNodo = subNodo;
        }

        public int getNivel()
        {
            return Nivel;
        }

        public void setNivel(int nivel)
        {
            Nivel = nivel;
        }

        public NodoArbolB(int Orden, boolean Hoja)
        {
            setOrden(Orden);
            setHoja(Hoja);
            setLibrosNodo(new ArrayList<Libros>());
            setSubNodo(new ArrayList<NodoArbolB>());
            setNivel(0);
        }

        public NodoArbolB(int Orden, SubNodos Nodo)
        {
            setOrden(Orden);
            setHoja(false);
            setLibrosNodo(new ArrayList<Libros>());
            setSubNodo(new ArrayList<NodoArbolB>());
            Nivel = 0;
            LibrosNodo.add(Nodo.getLibro());
            SubNodo.add(Nodo.getHIzquierdo());
            SubNodo.add(Nodo.getHDerecha());
        }

        public NodoArbolB(int Orden, boolean Hoja, ArrayList<Libros> LibrosArray, ArrayList<NodoArbolB> Hijos)
        {
            setOrden(Orden);
            setHoja(Hoja);
            setLibrosNodo(LibrosArray);
            setSubNodo(Hijos);
        }

        public NodoArbolB BuscarNodoArbolB(Libros Libro)
        {
            if(LibrosNodo.contains(Libro))
            {
                return this;
            }
            else
            {
                if(Hoja)
                {
                    return null;
                }
                else
                {
                    int Contador = 0;

                    while(Contador < LibrosNodo.size() && Libro.getISBN() > LibrosNodo.get(Contador).getISBN())
                    {
                        Contador++;
                    }

                    return SubNodo.get(Contador).BuscarNodoArbolB(Libro);
                }
            }
        }

        public SubNodos DividirNodoArbolB()
        {
            ArrayList<Libros> LibrosIzquierda = new ArrayList<Libros>();
            ArrayList<Libros> LibrosDerecha = new ArrayList<Libros>();

            ArrayList<NodoArbolB> NodosIzquierda = new ArrayList<NodoArbolB>();
            ArrayList<NodoArbolB> NodosDerecha = new ArrayList<NodoArbolB>();

            int Contador = 0;

            while(Contador < LibrosNodo.size() / 2)
            {
                LibrosIzquierda.add(LibrosNodo.get(Contador));
                Contador++;
            }

            Libros LibroIndividual = LibrosNodo.get(Contador);
            Contador++;

            while(Contador < LibrosNodo.size())
            {
                LibrosDerecha.add(LibrosNodo.get(Contador));
                Contador++;
            }

            if(!Hoja)
            {
                for(int i = 0; i <= Orden; i++)
                {
                    if(i < Orden - 2)
                    {
                        NodosIzquierda.add(SubNodo.get(i));
                    }
                    else
                    {
                        NodosDerecha.add(SubNodo.get(Contador));
                    }
                }
            }

            NodoArbolB Izquierda = new NodoArbolB(Orden, Hoja, LibrosIzquierda, NodosIzquierda);
            NodoArbolB Derecha = new NodoArbolB(Orden, Hoja, LibrosDerecha, NodosDerecha);
            SubNodos SubNodoAuxiliar = new SubNodos(LibroIndividual, Izquierda, Derecha);

            return SubNodoAuxiliar;
        }

        public SubNodos SizeNodos()
        {
            SubNodos SubNodoAuxiliar;

            if(LibrosNodo.size() > (Orden - 1))
            {
                SubNodoAuxiliar = DividirNodoArbolB();
                return SubNodoAuxiliar;
            }
            return null;
        }

        public SubNodos InsertarNodoArbolB(Libros Libro)
        {
            SubNodos SubNodoAuxiliar;

            if(Hoja)
            {
                int Contador = 0;

                while(Contador < LibrosNodo.size() && Libro.getISBN() > LibrosNodo.get(Contador).getISBN())
                {
                    Contador++;
                }

                if(LibrosNodo.contains(Libro))
                {
                    System.out.println("Si Lo Contengo");
                }
                else
                {
                    LibrosNodo.add(Contador, Libro);
                }

                SubNodoAuxiliar = SizeNodos();
                return SubNodoAuxiliar;
            }
            else
            {
                int Contador = 0;

                while((Contador < LibrosNodo.size()) && (Libro.getISBN() > LibrosNodo.get(Contador).getISBN()))
                {
                    Contador++;
                }

                SubNodoAuxiliar = SubNodo.get(Contador).InsertarNodoArbolB(Libro);

                if(SubNodoAuxiliar != null)
                {
                    SubNodo.remove(Contador);
                    Libros LibroIndividual = SubNodoAuxiliar.getLibro();
                    NodoArbolB Izquierda = SubNodoAuxiliar.getHIzquierdo();
                    NodoArbolB Derecha = SubNodoAuxiliar.getHDerecha();

                    LibrosNodo.add(Contador, LibroIndividual);
                    SubNodo.add(Contador, Izquierda);
                    SubNodo.add(Contador, Derecha);

                    SubNodoAuxiliar = SizeNodos();

                    return SubNodoAuxiliar;
                }
                else
                {
                    return null;
                }
            }
        }
    }
