
    package Metodos;

    import Estructuras.NodoArbolAVL;
    import Modelos.Bloques;
    import Modelos.Libros;
    import Modelos.Usuarios;
    import org.json.simple.JSONArray;
    import org.json.simple.JSONObject;
    import org.json.simple.parser.JSONParser;
    import org.json.simple.parser.ParseException;
    import javax.swing.*;
    import java.io.*;
    import java.nio.charset.StandardCharsets;
    import java.sql.Date;
    import java.sql.Timestamp;
    import java.text.SimpleDateFormat;

    public class GenerarBloquesJSON
    {
        public static void ObtenerBloques(String Cadena)
        {
            try
            {
                org.json.simple.parser.JSONParser Parser = new JSONParser();
                JSONObject Object = (JSONObject) Parser.parse(new InputStreamReader(new FileInputStream(Cadena), StandardCharsets.UTF_8));
                Long IndexAux = (long) Object.get("INDEX");
                int Index = IndexAux.intValue();
                Timestamp HoraCreacion = new Timestamp(System.currentTimeMillis());

                try
                {
                    SimpleDateFormat FormatoDeFecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    Date NuevaFecha = (Date) FormatoDeFecha.parse((String) Object.get("TIMESTAMP"));
                    HoraCreacion = new java.sql.Timestamp(NuevaFecha.getTime());
                }
                catch (java.text.ParseException e)
                {
                    e.printStackTrace();
                }

                Long NONCEAUX = (long) Object.get("NONCE");
                long NONCE = (long) NONCEAUX;

                JSONArray ObjectData = (JSONArray) Object.get("DATA");

                for(Object Objeto: ObjectData)
                {
                    if(Objeto != null)
                    {
                        JSONObject Data = (JSONObject) Objeto;

                        try
                        {
                            JSONArray NuevoUsuario = (JSONArray) Data.get("CREAR_USUARIO");

                            for(Object Obj: NuevoUsuario)
                            {
                                if(Obj != null)
                                {
                                    JSONObject Usuario = (JSONObject) Obj;

                                    Long CarnetAux = (long) Usuario.get("Carnet");
                                    int Carnet = CarnetAux.intValue();
                                    String Nombre = (String) Usuario.get("Nombre");
                                    String Apellido = (String) Usuario.get("Apellido");
                                    String Carrera = (String) Usuario.get("Carrera");
                                    String Pass = (String) Usuario.get("Password");

                                    Usuarios NuevoUsuarioAgregar = new Usuarios(Carnet, Nombre, Apellido, Carrera, Pass);
                                    Variables.TablaHashUsuarios.InsertarUsuarios(NuevoUsuarioAgregar);
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }


                        try
                        {
                            JSONArray EditarUsuario = (JSONArray) Data.get("EDITAR_USUARIO");

                            for(Object Obj: EditarUsuario)
                            {
                                if(Obj != null)
                                {
                                    JSONObject Usuario = (JSONObject) Obj;

                                    Long CarnetAux = (long) Usuario.get("Carnet");
                                    int Carnet = CarnetAux.intValue();
                                    String Nombre = (String) Usuario.get("Nombre");
                                    String Apellido = (String) Usuario.get("Apellido");
                                    String Carrera = (String) Usuario.get("Carrera");
                                    String Pass = (String) Usuario.get("Password");

                                    Variables.TablaHashUsuarios.ModificarUsuarios(Carnet, Nombre, Apellido, Carrera, Pass);
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        try
                        {
                            JSONArray EliminarUsuario = (JSONArray) Data.get("ELIMINAR_USUARIO");

                            for(Object Obj: EliminarUsuario)
                            {
                                if(Obj != null)
                                {
                                    JSONObject Usuario = (JSONObject) Obj;

                                    Long CarnetAux = (long) Usuario.get("Carnet");
                                    int Carnet = CarnetAux.intValue();

                                    Variables.TablaHashUsuarios.BorrarUsuarios(Carnet);
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        try
                        {
                            JSONArray CrearCategoria = (JSONArray) Data.get("CREAR_CATEGORIA");

                            for(Object Obj: CrearCategoria)
                            {
                                if(Obj != null)
                                {
                                    JSONObject Categoria = (JSONObject)  Obj;

                                    String Nombre = (String) Categoria.get("Nombre");
                                    Long CarnetAux = (long) Categoria.get("Carnet");
                                    int Carnet = CarnetAux.intValue();

                                    Variables.ArbolAVLCategorias.InicializarCategoriaArbolAVL(Nombre, Carnet);
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        try
                        {
                            JSONArray EliminarCategoria = (JSONArray) Data.get("ELIMINAR_CATEGORIA");

                            for(Object Obj: EliminarCategoria)
                            {
                                if(Obj != null)
                                {
                                    JSONObject Categoria = (JSONObject) Obj;

                                    String Nombre = (String) Categoria.get("Nombre");
                                    Long CarnetAux = (long) Categoria.get("Carnet");
                                    int Carnet = CarnetAux.intValue();

                                    Variables.ArbolAVLCategorias.EliminarCategoriaArbolAVL(Nombre, Carnet);
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        try
                        {
                            JSONArray CrearLibro = (JSONArray) Data.get("CREAR_LIBRO");

                            for(Object Obj: CrearLibro)
                            {
                                if(Obj != null)
                                {
                                    JSONObject Libro = (JSONObject) Obj;
                                    Long ISBNAux = (long) Libro.get("ISBN");
                                    int ISBN = ISBNAux.intValue();
                                    Long AnioAux = (long) Libro.get("Año");
                                    int Anio = AnioAux.intValue();
                                    String Idioma = (String) Libro.get("Idioma");
                                    String Titulo = (String) Libro.get("Titulo");
                                    String Editorial = (String) Libro.get("Editorial");
                                    String Autor = (String) Libro.get("Autor");
                                    Long EdicionAux = (long) Libro.get("Edicion");
                                    int Edicion = EdicionAux.intValue();
                                    String Categoria = (String) Libro.get("Categoria");
                                    Long CarnetAux = (long) Libro.get("Carnet");
                                    int Carnet = CarnetAux.intValue();


                                    Libros NuevoLibro = new Libros(ISBN, Titulo, Autor, Editorial, Anio, Edicion, Categoria, Idioma, Carnet);

                                    NodoArbolAVL LibrosLista = Variables.ArbolAVLCategorias.InicializarBuscarCategoriaArbolAVL(Categoria);

                                    if(LibrosLista != null)
                                    {
                                        Variables.ArbolAVLCategorias.InsertarLibroArbolAVL(LibrosLista, NuevoLibro);
                                    }
                                    else
                                    {
                                        Variables.ArbolAVLCategorias.InicializarCategoriaArbolAVL(Categoria, Carnet);
                                        LibrosLista = Variables.ArbolAVLCategorias.InicializarBuscarCategoriaArbolAVL(Categoria);
                                        Variables.ArbolAVLCategorias.InsertarLibroArbolAVL(LibrosLista, NuevoLibro);
                                    }

                                }
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        try
                        {
                            JSONArray EliminarCategoria = (JSONArray) Data.get("ELIMINAR_CATEGORIA");

                            for(Object Obj: EliminarCategoria)
                            {
                                if(Obj != null)
                                {
                                    JSONObject Libro = (JSONObject) Obj;

                                    Long ISBNAux = (long) Libro.get("ISBN");
                                    int ISBN = ISBNAux.intValue();
                                    String Titulo = (String) Libro.get("Titulo");
                                    String Categoria = (String) Libro.get("Categoria");
                                    Long CarnetAux = (long) Libro.get("Carnet");
                                    int Carnet = CarnetAux.intValue();

                                    Variables.ArbolAVLCategorias.InicizalizarEliminacionArboAVL(ISBN, Carnet);
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        String PreviousHash = (String) Object.get("PREVIOUSHASH");
                        String Hash = (String) Object.get("HASH");

                        String Writer = "";

                        Writer += "\"DATA\": [ \n";
                        Writer += "{ \n \"CREAR_USUARIO\": [ \n" + Variables.GenerarBloquesData.getCrearUsuario() + "] \n } \n";
                        Writer += "{ \n \"EDITAR_USUARIO\": [ \n" + Variables.GenerarBloquesData.getEditarUsuario() + "] \n } \n";
                        Writer += "{ \n \"ELIMINAR_USUARIO\": [ \n" + Variables.GenerarBloquesData.getEliminarUsuario() + "] \n } \n";
                        Writer += "{ \n \"CREAR_CATEGORIA\": [ \n" + Variables.GenerarBloquesData.getCrearCategoria() + "] \n } \n";
                        Writer += "{ \n \"CREAR_LIBRO\": [ \n" + Variables.GenerarBloquesData.getCrearLibro() + "] \n } \n";
                        Writer += "{ \n \"ELIMINAR_CATEGORIA\": [ \n" + Variables.GenerarBloquesData.getEliminarCategoria() + "] \n } \n";
                        Writer += "{ \n \"ELIMINAR_LIBRO\": [ \n" + Variables.GenerarBloquesData.getEliminarLibro() + "] \n } \n";
                        Writer += "], \n";

                        Bloques NuevoBloque = new Bloques(Index);

                        NuevoBloque.setIndex(Index);
                        NuevoBloque.setTimeStamp(HoraCreacion);
                        NuevoBloque.setNONCE(NONCE);
                        NuevoBloque.setData(Writer);
                        NuevoBloque.setPrevioushash(PreviousHash);
                        NuevoBloque.setHash(Hash);

                        Variables.ListaDobleBloques.InsertarBloqueListaDoble(NuevoBloque);
                        Variables.GenerarBloquesData = new DataBloques();
                    }
                }
            }
            catch (ParseException | IOException e)
            {
                JOptionPane.showMessageDialog(null, "Error Al Leer Lo Bloques Almacenados", "Error!", JOptionPane.ERROR);
            }
        }

        public static void GenerarBloques()
        {
            Bloques NuevoBloque = new Bloques(Variables.IndexBloque);
            NuevoBloque.setTimeStamp((new Timestamp(System.currentTimeMillis())));

            String Writer = "";

            Writer += "\"DATA\": [ \n";
            Writer += "{ \n \"CREAR_USUARIO\": [ \n" + Variables.GenerarBloquesData.getCrearUsuario() + "] \n } \n";
            Writer += "{ \n \"EDITAR_USUARIO\": [ \n" + Variables.GenerarBloquesData.getEditarUsuario() + "] \n } \n";
            Writer += "{ \n \"ELIMINAR_USUARIO\": [ \n" + Variables.GenerarBloquesData.getEliminarUsuario() + "] \n } \n";
            Writer += "{ \n \"CREAR_CATEGORIA\": [ \n" + Variables.GenerarBloquesData.getCrearCategoria() + "] \n } \n";
            Writer += "{ \n \"CREAR_LIBRO\": [ \n" + Variables.GenerarBloquesData.getCrearLibro() + "] \n } \n";
            Writer += "{ \n \"ELIMINAR_CATEGORIA\": [ \n" + Variables.GenerarBloquesData.getEliminarCategoria() + "] \n } \n";
            Writer += "{ \n \"ELIMINAR_LIBRO\": [ \n" + Variables.GenerarBloquesData.getEliminarLibro() + "] \n } \n";
            Writer += "], \n";

            NuevoBloque.setData(Writer);

            Variables.ListaDobleBloques.InsertarBloqueListaDoble(NuevoBloque);

            try
            {
                BufferedWriter WriterBuffer = new BufferedWriter((new OutputStreamWriter(new FileOutputStream("Bloques/Bloque" + Variables.IndexBloque + ".json"), StandardCharsets.UTF_8)));
                String Cadena = "{ \n" + ArmarBloque(Variables.ListaDobleBloques.getInicio()) + "} \n";
                WriterBuffer.write(Cadena);
                WriterBuffer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            Variables.NodoListaDobleBloques.EnviarNodoRedListaDoble(Variables.ListaSimpleRed.getInicio(), "Bloques/Bloque" + Variables.IndexBloque + ".json");
            Variables.GenerarBloquesData = new DataBloques();
            Variables.IndexBloque++;
        }

        public static String ArmarBloque(Bloques NuevoBloque)
        {
            String Cadena = "";

            if(NuevoBloque != null)
            {
                Cadena += "\"INDEX\": " + NuevoBloque.getIndex() + ", \n";
                Cadena += "\"TIMESTAMP\": \"" + NuevoBloque.getTimeStamp() + "\", \n";
                Cadena += "\"NONCE\": " + NuevoBloque.getNONCE() + ", \n";
                Cadena += NuevoBloque.getData() + "\n";
                Cadena += "\"PREVIOUSHASH\": \"" + NuevoBloque.getPrevioushash() + "\", \n";
                Cadena += "\"HASH\": \"" + NuevoBloque.getHash() + "\", \n";
            }
            return Cadena;
        }
    }
