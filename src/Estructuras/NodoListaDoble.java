
    package Estructuras;

    import Metodos.Variables;
    import javax.swing.*;
    import java.io.IOException;
    import java.net.*;
    import java.util.logging.Level;
    import java.util.logging.Logger;


    public class NodoListaDoble extends Thread
    {
        private InetAddress direccion;
        private DatagramSocket socketUDP;

        public InetAddress getDireccion()
        {
            return direccion;
        }

        public void setDireccion(InetAddress direccion)
        {
            this.direccion = direccion;
        }

        public DatagramSocket getSocketUDP()
        {
            return socketUDP;
        }

        public void setSocketUDP(DatagramSocket socketUDP)
        {
            this.socketUDP = socketUDP;
        }

        public NodoListaDoble()
        {
            try
            {
                direccion = InetAddress.getByName("localhost");
                socketUDP = new DatagramSocket();
            }
            catch (UnknownHostException | SocketException e)
            {
                Logger.getLogger(NodoListaSimple.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        public void run()
        {
            while(true)
            {
                try
                {
                    System.out.println("Listining...");
                    byte[] Buffer = new byte[1024];
                    DatagramPacket Peticion = new DatagramPacket(Buffer, Buffer.length);
                    socketUDP.receive(Peticion);
                    System.out.println("Peticion");
                    String Mensaje = new String(Peticion.getData());
                    System.out.println(Mensaje);

                    if (Mensaje.contains("Conectar"))
                    {
                        NodoListaSimple Nodo = new NodoListaSimple();
                        Nodo.setPuerto(Peticion.getPort());
                        InetAddress Ip = InetAddress.getByName(Variables.InternalIp);
                        Nodo.setIp(Ip);
                        NodoListaSimple Inicio = Variables.ListaSimpleRed.getInicio();
                        Variables.ListaSimpleRed.InsertarNodoRedListaSimple(Nodo);
                        String Agregar = "Agregar:" + Nodo.getPuerto();
                        byte[] Buff = new byte[1024];
                        Buff = Agregar.getBytes();

                        while(Inicio != null)
                        {
                            if(Inicio.getPuerto() != Nodo.getPuerto() && Inicio.getPuerto() != Variables.NodoListaDobleBloques.getSocketUDP().getLocalPort())
                            {

                                DatagramPacket Agreguen = new DatagramPacket(Buff, Buff.length, direccion, Inicio.getPuerto());
                                socketUDP.send(Agreguen);
                            }
                            Inicio = Inicio.getSgte();
                        }

                        Inicio = Variables.ListaSimpleRed.getInicio();

                        while (Inicio != null)
                        {
                            if (Inicio.getPuerto() != Nodo.getPuerto())
                            {
                                String Lista = "Agregar:" + Inicio.getPuerto();
                                byte[] List = new byte[1024];
                                List = Lista.getBytes();
                                DatagramPacket Agreguen = new DatagramPacket(List, List.length, direccion, Nodo.getPuerto());
                                socketUDP.send(Agreguen);
                            }
                            Inicio = Inicio.getSgte();
                        }
                    }
                    else if (Mensaje.contains("Agregar:"))
                    {
                        String[] Puerto = Mensaje.trim().split(":");
                        try {
                            Long ISBN = new Long(Puerto[1].trim());
                            int P = ISBN.intValue();
                            NodoListaSimple Nodo = new NodoListaSimple();
                            InetAddress Ip = InetAddress.getByName(Variables.InternalIp);
                            Nodo.setIp(Ip);
                            Nodo.setPuerto(P);
                            Variables.ListaSimpleRed.InsertarNodoRedListaSimple(Nodo);
                        }
                        catch (NumberFormatException ex)
                        {
                            System.out.println("Error NumberFormatException value: " + Puerto[1]);
                        }
                    }
                    else if (Mensaje.contains("Eliminar:"))
                    {
                        String[] Puerto = Mensaje.trim().split(":");

                        try
                        {
                            Long ISBN = new Long(Puerto[1].trim());
                            int P = ISBN.intValue();
                            NodoListaSimple Nodo = Variables.ListaSimpleRed.BuscarNodoRedListaSimple(P);
                            System.out.println(Mensaje + " " + P);
                            int PuertoNodo = Nodo.getPuerto();
                            Variables.ListaSimpleRed.EliminarNodoRedListaSimple(Nodo);
                            NodoListaSimple Inicio = Variables.ListaSimpleRed.getInicio();
                            String Agregar = "Eliminen:" + Nodo.getPuerto();
                            byte[] Buff = new byte[1024];
                            Buff = Agregar.getBytes();

                            while (Inicio != null)
                            {
                                if (Inicio.getPuerto() != PuertoNodo && Inicio.getPuerto() != Variables.NodoListaDobleBloques.getSocketUDP().getLocalPort())
                                {

                                    DatagramPacket Agreguen = new DatagramPacket(Buff, Buff.length, direccion, Inicio.getPuerto());
                                    socketUDP.send(Agreguen);
                                }
                                Inicio = Inicio.getSgte();
                            }
                        }
                        catch (NumberFormatException ex)
                        {
                            System.out.println("Error NumberFormatException value: " + Puerto[1]);
                        }
                    }
                    else if (Mensaje.contains("Bloques"))
                    {
                        System.out.print(Mensaje);
                        Metodos.GenerarBloquesJSON.ObtenerBloques(Mensaje.trim());
                        Variables.IndexBloque++;
                        System.out.println(Variables.IndexBloque);
                    }
                    else if (Mensaje.contains("Eliminen:"))
                    {
                        String[] Puerto = Mensaje.trim().split(":");

                        try
                        {
                            Long ISBN = new Long(Puerto[1].trim());
                            int P = ISBN.intValue();
                            NodoListaSimple Nodo = Variables.ListaSimpleRed.BuscarNodoRedListaSimple(P);
                            System.out.println(Mensaje + " " + P);
                            Variables.ListaSimpleRed.EliminarNodoRedListaSimple(Nodo);
                        }
                        catch (NumberFormatException ex)
                        {
                            System.out.println("Error NumberFormatException value: " + Puerto[1]);
                        }
                    }
                }
                catch (IOException ex)
                {
                    Logger.getLogger(NodoListaSimple.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Error");
                }
            }
        }

        public void EnviarNodoRedListaDoble(NodoListaSimple Inicio, String Ruta)
        {
            try
            {
                NodoListaSimple Aux = Variables.ListaSimpleRed.getInicio();

                while (Aux != null)
                {
                    if (Aux.getPuerto() != Variables.NodoListaDobleBloques.getSocketUDP().getLocalPort())
                    {
                        byte[] Buffer = new byte[1024];
                        Buffer = Ruta.getBytes();
                        DatagramPacket Respuesta = new DatagramPacket(Buffer, Buffer.length, direccion, Aux.getPuerto());

                        socketUDP.send(Respuesta);
                    }
                    Aux = Aux.getSgte();
                }
            }
            catch (IOException ex)
            {
                Logger.getLogger(NodoListaSimple.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void SalirNodoRedListaDoble()
        {
            try
            {
                NodoListaSimple Aux = Variables.ListaSimpleRed.getInicio();
                String Lista = "Eliminar:" + Variables.NodoListaDobleBloques.getSocketUDP().getLocalPort();
                byte[] List = new byte[1024];
                List = Lista.getBytes();

                DatagramPacket Respuesta = new DatagramPacket(List, List.length, direccion, Aux.getPuerto());

                socketUDP.send(Respuesta);
            }
            catch (IOException ex)
            {
                Logger.getLogger(NodoListaSimple.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        public void ConectarNodoRedListaDoble(int Puerto, String Ip)
        {
            String Conectar = "Conectar";
            byte[] Buffer = new byte[1024];

            try
            {
                InetAddress IpServer = InetAddress.getByName(Ip);
                Buffer = Conectar.getBytes();
                DatagramPacket Respuesta = new DatagramPacket(Buffer, Buffer.length, direccion, Puerto);

                socketUDP.send(Respuesta);
                JOptionPane.showMessageDialog(null,"Conexion Realizada Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException ex)
            {
                Logger.getLogger(NodoListaSimple.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Fallo En Conexion", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
