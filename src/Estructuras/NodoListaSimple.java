
    package Estructuras;


    public class NodoListaSimple extends Thread
    {
        private int Puerto;
        private NodoListaSimple Sgte;

        public int getPuerto()
        {
            return Puerto;
        }

        public void setPuerto(int puerto)
        {
            Puerto = puerto;
        }

        public NodoListaSimple getSgte()
        {
            return Sgte;
        }

        public void setSgte(NodoListaSimple sgte)
        {
            Sgte = sgte;
        }

        public NodoListaSimple()
        {

        }
    }
