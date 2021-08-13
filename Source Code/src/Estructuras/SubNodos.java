
    package Estructuras;

    import Modelos.Libros;


    public class SubNodos
    {
        private Libros Libro;
        private NodoArbolB HIzquierdo;
        private NodoArbolB HDerecha;

        public SubNodos(Libros Libro, NodoArbolB HIzquierdo, NodoArbolB HDerecha)
        {
            setLibro(Libro);
            setHIzquierdo(HIzquierdo);
            setHDerecha(HDerecha);
        }

        public Libros getLibro()
        {
            return Libro;
        }

        public void setLibro(Libros libro)
        {
            Libro = libro;
        }

        public NodoArbolB getHIzquierdo()
        {
            return HIzquierdo;
        }

        public void setHIzquierdo(NodoArbolB HIzquierdo)
        {
            this.HIzquierdo = HIzquierdo;
        }

        public NodoArbolB getHDerecha()
        {
            return HDerecha;
        }

        public void setHDerecha(NodoArbolB HDerecha)
        {
            this.HDerecha = HDerecha;
        }

        public NodoArbolB toRaiz()
        {
            NodoArbolB nodo = new NodoArbolB(getHDerecha().getOrden(), this);
            return nodo;
        }
    }
