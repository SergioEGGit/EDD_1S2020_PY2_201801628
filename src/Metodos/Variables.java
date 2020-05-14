
    package Metodos;

    import Estructuras.*;
    import javax.swing.*;

    public class Variables
    {
        public static TablaHashUsuarios TablaHashUsuarios = new TablaHashUsuarios(45);
        public static int NumeroCarnetUsuarioLog = 0;
        public static boolean EstoyEnLogin = true;
        public static ArbolAVLCategorias ArbolAVLCategorias = new ArbolAVLCategorias();
        public static boolean ExisteUsuarios = false;
        public static boolean ExisteLibro = false;
        public static JFrame Principal = new JFrame();
        public static String NombreReporte = "";
        public static boolean ExisteCategoria = false;
        public static boolean CreeCategoria = false;
        public static boolean GenereReporte = false;
        public static ListaSimpleRed ListaSimpleRed = new ListaSimpleRed();
        public static ListaDobleBloques ListaDobleBloques = new ListaDobleBloques();
        public static NodoListaDoble NodoListaDobleBloques = new NodoListaDoble();
        public static DataBloques GenerarBloquesData = new DataBloques();
        public static int IndexBloque = 0;
    }
