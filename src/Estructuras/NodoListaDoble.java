
    package Estructuras;

    import Metodos.Variables;
    import javax.swing.*;
    import java.io.IOException;
    import java.net.*;
    import java.util.logging.Level;
    import java.util.logging.Logger;

    public class NodoListaDoble extends Thread
    {
        private InetAddress Address;
        private DatagramSocket Socket;

        public InetAddress getAddress()
        {
            return Address;
        }

        public void setAddress(InetAddress address)
        {
            Address = address;
        }

        public DatagramSocket getSocket()
        {
            return Socket;
        }

        public void setSocket(DatagramSocket socket)
        {
            Socket = socket;
        }

        public NodoListaDoble()
        {
            try
            {
                Address = InetAddress.getByName("localhost");
                Socket = new DatagramSocket();
            }
            catch(UnknownHostException | SocketException e)
            {
                Logger.getLogger(NodoListaSimple.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        public void RunNodoRedListaDoble()
        {
            while(true)
            {
                try
                {
                    System.out.println("Listening....");
                    byte[] Cadena = new byte[1024];
                    DatagramPacket Peticiones = new DatagramPacket(Cadena, Cadena.length);
                    Socket.receive(Peticiones);
                    System.out.println("Get Request");
                    String Message = new String(Peticiones.getData());
                    System.out.println(Message);

                    if(Message.contains("Conectar"))
                    {
                        NodoListaSimple NuevoNodo =  new NodoListaSimple();
                        NuevoNodo.setPuerto(Peticiones.getPort());
                        NodoListaSimple Inicio = Variables.ListaSimpleRed.getInicio();
                        Variables.ListaSimpleRed.InsertarNodoRedListaSimple(NuevoNodo);
                        String NuevoPuerto = "Agregar:" + NuevoNodo.getPuerto();
                        byte[] Buffer = new byte[1024];
                        Buffer = NuevoPuerto.getBytes();

                        while(Inicio != null)
                        {
                            if(Inicio.getPuerto() != NuevoNodo.getPuerto() && Inicio.getPuerto() != Variables.NodoListaDobleBloques.getSocket().getLocalPort())
                            {
                                DatagramPacket NuevaPeticion = new DatagramPacket(Buffer, Buffer.length, Address, Inicio.getPuerto());
                                Socket.send(NuevaPeticion);
                            }
                            Inicio = Inicio.getSgte();
                        }

                        Inicio = Variables.ListaSimpleRed.getInicio();

                        while(Inicio != null)
                        {
                            if(Inicio.getPuerto() != NuevoNodo.getPuerto())
                            {
                                String Cadena2 = "Aregar:" + Inicio.getPuerto();
                                byte[] Cadena2Array = new byte[1024];
                                Cadena2Array = Cadena2.getBytes();
                                DatagramPacket NuevaPeticion = new DatagramPacket(Cadena2Array, Cadena2Array.length, Address, NuevoNodo.getPuerto());
                                Socket.send(NuevaPeticion);
                            }
                            Inicio = Inicio.getSgte();
                        }
                    }
                    else if(Message.contains("Agregar:"))
                    {
                        String[] Puerto = Message.trim().split(":");

                        try
                        {
                            Long Identificador = new Long(Puerto[1].trim());
                            int NumeroPuerto = Identificador.intValue();
                            NodoListaSimple NuevaRed = new NodoListaSimple();
                            NuevaRed.setPuerto(NumeroPuerto);
                            Variables.ListaSimpleRed.InsertarNodoRedListaSimple(NuevaRed);
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Error: " + Puerto[1]);
                        }
                    }
                    else if(Message.contains("Eliminar:"))
                    {
                        String[] Puerto = Message.trim().split(":");

                        try
                        {
                            Long Identificador = new Long(Puerto[1].trim());
                            int NumeroPuerto = Identificador.intValue();
                            NodoListaSimple RedEliminar = Variables.ListaSimpleRed.BuscarNodoRedListaSimple(NumeroPuerto);
                            int NumeroPuertoNuevo = RedEliminar.getPuerto();
                            Variables.ListaSimpleRed.EliminarNodoRedListaSimple(RedEliminar);
                            NodoListaSimple Inicio = Variables.ListaSimpleRed.getInicio();
                            String Peticion = "Eliminen:" + RedEliminar.getPuerto();
                            byte[] Buffer = new byte[1024];
                            Buffer = Peticion.getBytes();

                            while(Inicio != null)
                            {
                                if(Inicio.getPuerto() != NumeroPuertoNuevo && Inicio.getPuerto() != Variables.NodoListaDobleBloques.getSocket().getLocalPort())
                                {
                                    DatagramPacket NuevaPeticion = new DatagramPacket(Buffer, Buffer.length, Address, Inicio.getPuerto());
                                    Socket.send(NuevaPeticion);
                                }
                                Inicio = Inicio.getSgte();
                            }
                        }
                        catch(NumberFormatException e)
                        {
                            System.out.println("Error: " + Puerto[1]);
                        }
                    }
                    else if(Message.contains("Bloques"))
                    {
                        Metodos.GenerarBloquesJSON.ObtenerBloques(Message.trim());
                        Variables.IndexBloque++;
                    }
                    else if(Message.contains("Eliminen:"))
                    {
                        String[] Puerto = Message.trim().split(":");

                        try
                        {
                            Long ISBN = new Long(Puerto[1].trim());
                            int NumeroPuerto = ISBN.intValue();
                            NodoListaSimple NuevoBloque = Variables.ListaSimpleRed.BuscarNodoRedListaSimple(NumeroPuerto);
                            Variables.ListaSimpleRed.EliminarNodoRedListaSimple(NuevoBloque);
                        }
                        catch (NumberFormatException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                catch (IOException e)
                {
                    Logger.getLogger(NodoListaSimple.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        public void EnviarNodoRedListaDoble(NodoListaSimple Inicio, String Cadena)
        {
            try
            {
                NodoListaSimple Aux = Variables.ListaSimpleRed.getInicio();

                while(Aux != null)
                {
                    if(Aux.getPuerto() != Variables.NodoListaDobleBloques.getSocket().getLocalPort())
                    {
                        byte[] Buffer = new byte[1024];
                        Buffer = Cadena.getBytes();
                        DatagramPacket Peticion = new DatagramPacket(Buffer, Buffer.length, Address, Aux.getPuerto());

                        Socket.send(Peticion);
                    }
                    Aux = Aux.getSgte();
                }
            }
            catch (IOException e)
            {
                Logger.getLogger(NodoListaSimple.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        public void SalirNodoRedListaDoble()
        {
            try
            {
                NodoListaSimple Aux = Variables.ListaSimpleRed.getInicio();
                String Array = "Eliminar:" + Variables.NodoListaDobleBloques.getSocket().getLocalPort();
                byte[] ListaArray = new byte[1024];
                ListaArray = Array.getBytes();

                DatagramPacket Peticion = new DatagramPacket(ListaArray, ListaArray.length, Address, Aux.getPuerto());

                Socket.send(Peticion);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        public void ConectarNodoRedListaDoble(int Puerto)
        {
            String Cadena = "Conectar";
            byte[] Buffer = new byte[1024];

            try
            {
                Buffer = Cadena.getBytes();
                DatagramPacket Peticion = new DatagramPacket(Buffer, Buffer.length, Address, Puerto);

                Socket.send(Peticion);
                JOptionPane.showMessageDialog(null, "Conexion Realizada Con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException e)
            {
                Logger.getLogger(NodoListaSimple.class.getName()).log(Level.SEVERE, null, e);
                JOptionPane.showMessageDialog(null, "Fallo En Conexion", "Error!", JOptionPane.ERROR);
            }
        }

    }
