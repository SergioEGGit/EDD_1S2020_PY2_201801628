
    package Metodos;


    import Estructuras.NodoArbolAVL;
    import Modelos.Bloques;
    import Modelos.Libros;
    import Modelos.Usuarios;
    import com.sun.org.apache.bcel.internal.generic.VariableLengthInstruction;
    import org.json.simple.JSONArray;
    import org.json.simple.JSONObject;
    import org.json.simple.parser.JSONParser;
    import java.io.*;
    import java.nio.charset.StandardCharsets;
    import java.sql.Timestamp;
    import java.text.SimpleDateFormat;
    import java.util.Date;

    public class GenerarBloquesJSON
    {
        public static void ObtenerBloques(String ruta)
        {
            try
            {
                JSONParser parser = new JSONParser();
                JSONObject archivo = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(ruta), "UTF-8"));

                long indexLong = (long) archivo.get("INDEX");
                int index = (int) indexLong;

                Timestamp time = new Timestamp(System.currentTimeMillis());

                try
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    Date parsedDate = dateFormat.parse((String) archivo.get("TIMESTAMP"));
                    time = new java.sql.Timestamp(parsedDate.getTime());
                }
                catch (Exception e)
                {
                    //Nada
                }

                long nonceLong = (long) archivo.get("NONCE");

                JSONArray operaciones = (JSONArray) archivo.get("DATA");

                for (Object obj : operaciones)
                {
                    if (obj != null)
                    {
                        JSONObject operacion = (JSONObject) obj;

                        try
                        {
                            JSONArray crearUsuario = (JSONArray) operacion.get("CREAR_USUARIO");

                            for (Object object : crearUsuario)
                            {
                                if (object != null)
                                {
                                    JSONObject usuario = (JSONObject) object;

                                    Long car = (long) usuario.get("Carnet");
                                    int carnet = car.intValue();

                                    String nombre = (String) usuario.get("Nombre");
                                    String apellido = (String) usuario.get("Apellido");
                                    String carrera = (String) usuario.get("Carrera");
                                    String password = (String) usuario.get("Password");

                                    Usuarios NuevoUsuario = new Usuarios(carnet, nombre, apellido, carrera, password);
                                    Variables.TablaHashUsuarios.InsertarUsuarios(NuevoUsuario);
                                }
                            }
                        }
                        catch (Exception ex)
                        {
                            //Nada
                        }

                        try
                        {
                            JSONArray editarUsuario = (JSONArray) operacion.get("EDITAR_USUARIO");

                            for (Object object : editarUsuario)
                            {
                                if (object != null)
                                {
                                    JSONObject usuario = (JSONObject) object;

                                    Long car = (long) usuario.get("Carnet");
                                    int carnet = car.intValue();

                                    String nombre = (String) usuario.get("Nombre");
                                    String apellido = (String) usuario.get("Apellido");
                                    String carrera = (String) usuario.get("Carrera");
                                    String password = (String) usuario.get("Password");

                                    Variables.TablaHashUsuarios.ModificarUsuarios(carnet, nombre, apellido, carrera, password);
                                }
                            }
                        }
                        catch (Exception ex)
                        {
                            //Nada
                        }

                        try
                        {
                            JSONArray eliminarUsuario = (JSONArray) operacion.get("ELIMINAR_USUARIO");

                            for (Object object : eliminarUsuario)
                            {
                                if (object != null)
                                {
                                    JSONObject usuario = (JSONObject) object;

                                    Long car = (long) usuario.get("Carnet");
                                    int carnet = car.intValue();

                                    Variables.TablaHashUsuarios.BorrarUsuarios(carnet);
                                }
                            }
                        }
                        catch (Exception ex)
                        {
                            //Nada
                        }

                        try
                        {
                            JSONArray crearCategoria = (JSONArray) operacion.get("CREAR_CATEGORIA");

                            for (Object object : crearCategoria)
                            {
                                if (object != null)
                                {
                                    JSONObject categoria = (JSONObject) object;

                                    String nombre = (String) categoria.get("Nombre");

                                    Long car = (long) categoria.get("Carnet");
                                    int carnet = car.intValue();

                                    Variables.ArbolAVLCategorias.InicializarCategoriaArbolAVL(nombre, carnet);
                                }
                            }
                        }
                        catch (Exception ex)
                        {
                            //Nada
                        }

                        try
                        {
                            JSONArray crearLibro = (JSONArray) operacion.get("CREAR_LIBRO");

                            for (Object object : crearLibro)
                            {
                                if (object != null)
                                {
                                    JSONObject libro = (JSONObject) object;

                                    Long isbn = (long) libro.get("ISBN");
                                    int ISBN = isbn.intValue();
                                    Long anio = (long) libro.get("Año");
                                    int año = anio.intValue();

                                    String idioma = (String) libro.get("Idioma");
                                    String titulo = (String) libro.get("Titulo");
                                    String editorial = (String) libro.get("Editorial");
                                    String autor = (String) libro.get("Autor");

                                    Long edic = (long) libro.get("Edicion");
                                    int edicion = edic.intValue();

                                    String categoria = (String) libro.get("Categoria");

                                    Long car = (long) libro.get("Carnet");
                                    int carnet = car.intValue();

                                    Libros NuevoLibro = new Libros(ISBN, titulo, autor, editorial, año ,edicion, categoria, idioma, carnet);

                                    NodoArbolAVL Libros = Variables.ArbolAVLCategorias.InicializarBuscarCategoriaArbolAVL(categoria);

                                    if (Libros != null)
                                    {
                                        Variables.ArbolAVLCategorias.InsertarLibroArbolAVL(Libros, NuevoLibro);
                                    }
                                    else
                                    {
                                        Variables.ArbolAVLCategorias.InicializarCategoriaArbolAVL(categoria, carnet);
                                        Libros = Variables.ArbolAVLCategorias.InicializarBuscarCategoriaArbolAVL(categoria);
                                        Variables.ArbolAVLCategorias.InsertarLibroArbolAVL(Libros, NuevoLibro);
                                    }
                                }
                            }
                        }
                        catch (Exception ex)
                        {
                            //Nada
                        }

                        try
                        {
                            JSONArray eliminarLibro = (JSONArray) operacion.get("ELIMINAR_LIBRO");

                            for (Object object : eliminarLibro)
                            {
                                if (object != null)
                                {
                                    JSONObject libro = (JSONObject) object;

                                    Long isbn = (long) libro.get("ISBN");
                                    int ISBN = isbn.intValue();

                                    String titulo = (String) libro.get("Titulo");
                                    String categoria = (String) libro.get("Categoria");

                                    Long car = (long) libro.get("Carnet");
                                    int carnet = car.intValue();

                                    Variables.ArbolAVLCategorias.InicizalizarEliminacionArboAVL(ISBN, carnet);
                                }
                            }
                        }
                        catch(Exception ex)
                        {
                            //Nada
                        }

                        try
                        {
                            JSONArray eliminarCategoria = (JSONArray) operacion.get("ELIMINAR_CATEGORIA");

                            for (Object object : eliminarCategoria)
                            {
                                if (object != null)
                                {
                                    JSONObject categoria = (JSONObject) object;

                                    String nombre = (String) categoria.get("Nombre");

                                    Long car = (long) categoria.get("Carnet");
                                    int carnet = car.intValue();

                                    Variables.ArbolAVLCategorias.EliminarCategoriaArbolAVL(nombre, carnet);
                                }
                            }
                        }
                        catch (Exception ex)
                        {
                            //Nada
                        }

                        String previoushash = (String) archivo.get("PREVIOUSHASH");
                        String hash = (String) archivo.get("HASH");

                        String data = "\"DATA\": [\n";
                        data += "{\n\"CREAR_USUARIO\": [\n" + Variables.GenerarBloquesData.getCrearUsuario() + "]\n}\n";
                        data += "{\n\"EDITAR_USUARIO\": [\n" + Variables.GenerarBloquesData.getEditarUsuario() + "]\n}\n";
                        data += "{\n\"ELIMINAR_USUARIO\": [\n" + Variables.GenerarBloquesData.getEliminarUsuario() + "]\n}\n";
                        data += "{\n\"CREAR_CATEGORIA\": [\n" + Variables.GenerarBloquesData.getCrearCategoria() + "]\n}\n";
                        data += "{\n\"CREAR_LIBRO\": [\n" + Variables.GenerarBloquesData.getCrearLibro() + "]\n}\n";
                        data += "{\n\"ELIMINAR_CATEGORIA\": [\n" + Variables.GenerarBloquesData.getEliminarCategoria() + "]\n}\n";
                        data += "{\n\"ELIMINAR_LIBRO\": [\n" + Variables.GenerarBloquesData.getEliminarLibro() + "]\n}\n";
                        data += "],\n";

                        Bloques NuevoBloque = new Bloques(index);

                        NuevoBloque .setIndex(index);
                        NuevoBloque .setNONCE((long) nonceLong);
                        NuevoBloque .setData(data);
                        NuevoBloque .setTimeStamp(time);
                        NuevoBloque .setPrevioushash(previoushash);
                        NuevoBloque .setHash(hash);

                        Variables.ListaDobleBloques.InsertarBloqueListaDoble(NuevoBloque);
                        Variables.GenerarBloquesData = new DataBloques();
                    }
                }
            }
            catch (Exception ex)
            {

                System.out.println("Error: Lectura De Archivo Bloque: "+ruta+" " + ex.toString());
            }
        }

        public static void GenerarBloques()
        {
            Bloques NuevoBloque = new Bloques(Variables.IndexBloque);
            NuevoBloque.setTimeStamp((new Timestamp(System.currentTimeMillis())));

            String Data = "\"DATA\": [\n";
            Data += "{\n\"CREAR_USUARIO\": [\n" + Variables.GenerarBloquesData.getCrearUsuario() + "]\n}\n";
            Data += "{\n\"EDITAR_USUARIO\": [\n" + Variables.GenerarBloquesData.getEditarUsuario() + "]\n}\n";
            Data += "{\n\"ELIMINAR_USUARIO\": [\n" + Variables.GenerarBloquesData.getEliminarUsuario() + "]\n}\n";
            Data += "{\n\"CREAR_CATEGORIA\": [\n" + Variables.GenerarBloquesData.getCrearCategoria() + "]\n}\n";
            Data += "{\n\"CREAR_LIBRO\": [\n" + Variables.GenerarBloquesData.getCrearLibro() + "]\n}\n";
            Data += "{\n\"ELIMINAR_CATEGORIA\": [\n" + Variables.GenerarBloquesData.getEliminarCategoria() + "]\n}\n";
            Data += "{\n\"ELIMINAR_LIBRO\": [\n" + Variables.GenerarBloquesData.getEliminarLibro() + "]\n}\n";
            Data += "],\n";

            NuevoBloque.setData(Data);

            Variables.ListaDobleBloques.InsertarBloqueListaDoble(NuevoBloque);
            String Ruta = "";

            try
            {
                if(Variables.OsName.equals("Windows10") || Variables.OsName.equals("Windows8") || Variables.OsName.equals("Windows7") || Variables.OsName.equals("Windows"))
                {
                    Ruta = System.getProperty("user.dir") + "\\Bloques\\Bloque" + Variables.IndexBloque + ".json";
                }
                else if(Variables.OsName.equals("Linux") || Variables.OsName.equals("MacOSX"))
                {
                    Ruta = System.getProperty("user.dir") + "/Bloques/Bloque" + Variables.IndexBloque + ".json";
                }

                BufferedWriter WriterBuffer = new BufferedWriter((new OutputStreamWriter(new FileOutputStream(Ruta), StandardCharsets.UTF_8)));
                String Cadena = "{ \n" + ArmarBloque(Variables.ListaDobleBloques.getInicio()) + "} \n";
                WriterBuffer.write(Cadena);
                WriterBuffer.close();
            }
            catch (IOException e)
            {
                System.out.println("Error: Escribir Bloque");
            }

            Variables.NodoListaDobleBloques.EnviarNodoRedListaDoble(Variables.ListaSimpleRed.getInicio(), Ruta);
            Variables.GenerarBloquesData = new DataBloques();
            Variables.IndexBloque++;
        }

        public static String ArmarBloque(Bloques NuevoBloque)
        {
            String Cadena = "";

            if(NuevoBloque != null)
            {
                Cadena += "\"INDEX\": " + NuevoBloque.getIndex() + ",\n";
                Cadena += "\"TIMESTAMP\": \"" + NuevoBloque.getTimeStamp() + "\",\n";
                Cadena += "\"NONCE\": " + NuevoBloque.getNONCE() + ",\n";
                Cadena += NuevoBloque.getData() + "\n";
                Cadena += "\"PREVIOUSHASH\": \"" + NuevoBloque.getPrevioushash() + "\",\n";
                Cadena += "\"HASH\": \"" + NuevoBloque.getHash() + "\",\n";
            }
            return Cadena;
        }
    }
