
    package Estructuras;

    import Metodos.GenerarReportes;
    import Modelos.Bloques;
    import java.nio.charset.StandardCharsets;
    import java.security.MessageDigest;
    import java.security.NoSuchAlgorithmException;
    import java.util.logging.Level;
    import java.util.logging.Logger;

    public class ListaDobleBloques
    {
        private Bloques Inicio;
        private Bloques Final;

        public Bloques getInicio()
        {
            return Inicio;
        }

        public void setInicio(Bloques inicio)
        {
            Inicio = inicio;
        }

        public Bloques getFinal()
        {
            return Final;
        }

        public void setFinal(Bloques aFinal)
        {
            Final = aFinal;
        }

        public ListaDobleBloques()
        {

        }

        public boolean ListaVacia()
        {
            return getInicio() == null || getFinal() == null;
        }

        public static String ConvertToHexString(byte[] Cadena)
        {
            StringBuilder String = new StringBuilder();

            for(int i = 0; i < Cadena.length; i++)
            {
                String Aux = Integer.toHexString(0xff & Cadena[i]);

                if(Aux.length() == 1)
                {
                    String.append('0');
                }
                String.append(Aux);
            }
            return String.toString();
        }

        public static byte[] GenerarCadenaSHA256(String Cadena) throws NoSuchAlgorithmException
        {
            MessageDigest CadenaSHA = MessageDigest.getInstance("SHA-256");
            return CadenaSHA.digest(Cadena.getBytes(StandardCharsets.UTF_8));
        }

        public static String ObtenerCadenaSHA256(String Cadena)
        {
            try
            {
                MessageDigest SHA256 = MessageDigest.getInstance("SHA-256");
                byte[] Hash = SHA256.digest(Cadena.getBytes("UTF-8"));
                StringBuilder StringHex = new StringBuilder();

                for(int i = 0; i < Hash.length; i++)
                {
                    String Aux = Integer.toHexString(0xff & Hash[i]);

                    if(Aux.length() == 1)
                    {
                        StringHex.append('0');
                    }
                    StringHex.append(Aux);
                }
                return StringHex.toString();
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }

        public void InsertarBloqueListaDoble(Bloques NuevoNodo)
        {
            if(ListaVacia())
            {
                if(NuevoNodo.getPrevioushash() == null)
                {
                    NuevoNodo.setPrevioushash("0000");
                    NuevoNodo.setNONCE(0);

                    try
                    {
                        NuevoNodo.setHash(ConvertToHexString(GenerarCadenaSHA256("" + NuevoNodo.getIndex() + NuevoNodo.getTimeStamp() + NuevoNodo.getPrevioushash() + NuevoNodo.getData() + NuevoNodo.getNONCE())));
                    }
                    catch (NoSuchAlgorithmException e)
                    {
                        Logger.getLogger(ListaDobleBloques.class.getName()).log(Level.SEVERE, null, e);
                    }
                    while(!NuevoNodo.getHash().startsWith("0000"))
                    {
                        NuevoNodo.setNONCE(NuevoNodo.getNONCE() + 1);

                        try
                        {
                            NuevoNodo.setHash(ConvertToHexString(GenerarCadenaSHA256("" + NuevoNodo.getIndex() + NuevoNodo.getTimeStamp() + NuevoNodo.getPrevioushash() + NuevoNodo.getData() + NuevoNodo.getNONCE())));
                        }
                        catch (NoSuchAlgorithmException e)
                        {
                            Logger.getLogger(ListaDobleBloques.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                }
                setInicio(NuevoNodo);
                setFinal(NuevoNodo);
            }
            else
            {
                if(NuevoNodo.getPrevioushash() == null)
                {
                    NuevoNodo.setPrevioushash(getInicio().getHash());
                    NuevoNodo.setNONCE(0);
                    NuevoNodo.setHash(ObtenerCadenaSHA256("" + NuevoNodo.getIndex() + NuevoNodo.getTimeStamp() + NuevoNodo.getPrevioushash() + NuevoNodo.getData() + NuevoNodo.getNONCE()));
                    while(!NuevoNodo.getHash().startsWith("0000"))
                    {
                        NuevoNodo.setNONCE(NuevoNodo.getNONCE() + 1);
                        NuevoNodo.setHash(ObtenerCadenaSHA256("" + NuevoNodo.getIndex() + NuevoNodo.getTimeStamp() + NuevoNodo.getPrevioushash() + NuevoNodo.getData() + NuevoNodo.getNONCE()));
                    }
                }
                NuevoNodo.setSgte(getInicio());
                getInicio().setAnte(NuevoNodo);
                setInicio(NuevoNodo);
            }
        }

        public void GraficarBloquesListaDoble()
        {
            String Cadena = "";

            Cadena += "digraph G { \n rankdir = \"LR\": \n node[shape = rect, fontname = Arial, color = gold, fontcolor = cyan4]; \n";
            Bloques BloqueAuxiliar = Final;

            while(BloqueAuxiliar != null)
            {
                Cadena += "A" + BloqueAuxiliar.getIndex() + "[label = \" Index: " + BloqueAuxiliar.getIndex() + "\nTimeStamp: " + BloqueAuxiliar.getTimeStamp() + "\nNONCE: " + BloqueAuxiliar.getNONCE() + "\nPreviousHash: " + BloqueAuxiliar.getPrevioushash() + "\nHash: " + BloqueAuxiliar.getHash() + "\"]; \n";

                if(BloqueAuxiliar.getAnte() != null && BloqueAuxiliar.getAnte().getIndex()  != BloqueAuxiliar.getIndex())
                {
                    Cadena += "A" + BloqueAuxiliar.getIndex() + " -> A" + BloqueAuxiliar.getAnte().getIndex() + "; \n";
                    Cadena += "A" + BloqueAuxiliar.getAnte().getIndex() + " -> A" + BloqueAuxiliar.getIndex() + "; \n";
                }
                BloqueAuxiliar = BloqueAuxiliar.getAnte();
            }

            Cadena += "}";

            GenerarReportes ListaDobleBloques = new GenerarReportes("ReporteBloquesListaDoble", Cadena);
        }
    }
