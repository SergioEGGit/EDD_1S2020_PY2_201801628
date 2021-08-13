
    package Estructuras;


    public class NodoArbolAVL
    {
        private String Categoria;
        private ArbolBLibros ArbolB;
        private NodoArbolAVL HIzquierda;
        private NodoArbolAVL HDerecha;
        private int Altura;
        private int Carnet_Usuario_Creador;

        public NodoArbolAVL(String categoria, NodoArbolAVL hIzquierda, NodoArbolAVL hDerecha, int Carnet)
        {
            Categoria = categoria;
            HIzquierda = hIzquierda;
            HDerecha = hDerecha;
            ArbolB = new ArbolBLibros(5);
            Altura = 0;
            Carnet_Usuario_Creador = Carnet;
        }

        public String getCategoria()
        {
            return Categoria;
        }

        public void setCategoria(String categoria)
        {
            Categoria = categoria;
        }

        public ArbolBLibros getArbolB()
        {
            return ArbolB;
        }

        public void setArbolB(ArbolBLibros arbolB)
        {
            ArbolB = arbolB;
        }

        public NodoArbolAVL getHIzquierda()
        {
            return HIzquierda;
        }

        public void setHIzquierda(NodoArbolAVL HIzquierda)
        {
            this.HIzquierda = HIzquierda;
        }

        public NodoArbolAVL getHDerecha()
        {
            return HDerecha;
        }

        public void setHDerecha(NodoArbolAVL HDerecha)
        {
            this.HDerecha = HDerecha;
        }

        public int getAltura()
        {
            return Altura;
        }

        public void setAltura(int altura)
        {
            Altura = altura;
        }

        public int getCarnet_Usuario_Creador()
        {
            return Carnet_Usuario_Creador;
        }

        public void setCarnet_Usuario_Creador(int carnet_Usuario_Creador)
        {
            Carnet_Usuario_Creador = carnet_Usuario_Creador;
        }
    }
