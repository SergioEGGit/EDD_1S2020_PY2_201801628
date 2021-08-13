
    package Estructuras;


    import java.net.InetAddress;

    public class NodoListaSimple extends Thread
    {
        private int Puerto;
        private InetAddress Ip;
        private NodoListaSimple Sgte;

        public InetAddress getIp()
        {
            return Ip;
        }

        public void setIp(InetAddress ip)
        {
            Ip = ip;
        }

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
