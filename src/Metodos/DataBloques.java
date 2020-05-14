
    package Metodos;

    import Estructuras.NodoArbolAVL;
    import Modelos.Libros;
    import Modelos.Usuarios;

    public class DataBloques
    {
        private String CrearUsuario;
        private String EditarUsuario;
        private String EliminarUsuario;
        private String CrearLibro;
        private String EliminarLibro;
        private String CrearCategoria;
        private String EliminarCategoria;

        public String getCrearUsuario()
        {
            return CrearUsuario;
        }

        public void setCrearUsuario(String crearUsuario)
        {
            CrearUsuario = crearUsuario;
        }

        public String getEditarUsuario()
        {
            return EditarUsuario;
        }

        public void setEditarUsuario(String editarUsuario)
        {
            EditarUsuario = editarUsuario;
        }

        public String getEliminarUsuario()
        {
            return EliminarUsuario;
        }

        public void setEliminarUsuario(String eliminarUsuario)
        {
            EliminarUsuario = eliminarUsuario;
        }

        public String getCrearLibro()
        {
            return CrearLibro;
        }

        public void setCrearLibro(String crearLibro)
        {
            CrearLibro = crearLibro;
        }

        public String getEliminarLibro()
        {
            return EliminarLibro;
        }

        public void setEliminarLibro(String eliminarLibro)
        {
            EliminarLibro = eliminarLibro;
        }

        public String getCrearCategoria()
        {
            return CrearCategoria;
        }

        public void setCrearCategoria(String crearCategoria)
        {
            CrearCategoria = crearCategoria;
        }

        public String getEliminarCategoria()
        {
            return EliminarCategoria;
        }

        public void setEliminarCategoria(String eliminarCategoria)
        {
            EliminarCategoria = eliminarCategoria;
        }

        public DataBloques()
        {
            CrearUsuario = "";
            EditarUsuario = "";
            EliminarUsuario = "";
            CrearLibro = "";
            EliminarLibro = "";
            CrearCategoria = "";
            EliminarCategoria = "";
        }

        public void CrearUsuarioBloques(Usuarios NuevoUsuario)
        {
            CrearUsuario += "{ \n";
            CrearUsuario += "\"Carnet\": " + NuevoUsuario.getCarnet() + ", \n";
            CrearUsuario += "\"Nombre\": \"" + NuevoUsuario.getNombre() + "\", \n";
            CrearUsuario += "\"Apellido\": \"" + NuevoUsuario.getApellido() + "\", \n";
            CrearUsuario += "\"Carrera\": \"" + NuevoUsuario.getCarrera() + "\", \n";
            CrearUsuario += "\"Password\": \"" + NuevoUsuario.getPassword() + "\", \n";
            CrearUsuario += "}, \n";
        }

        public void EditarUsuarioBloques(Usuarios NuevoUsuario)
        {
            EditarUsuario += "{ \n";
            EditarUsuario += "\"Carnet\": " + NuevoUsuario.getCarnet() + "\", \n";
            EditarUsuario += "\"Nombre\": \"" + NuevoUsuario.getNombre() + "\", \n";
            EditarUsuario += "\"Apellido\": \"" + NuevoUsuario.getApellido() + "\", \n";
            EditarUsuario += "\"Carrera\": \"" + NuevoUsuario.getCarrera() + "\", \n";
            EditarUsuario += "\"Password\": \"" + NuevoUsuario.getPassword() + "\", \n";
            EditarUsuario += "}, \n";
        }

        public void EliminarUsuarioBloques(Usuarios NuevoUsuario)
        {
            EliminarUsuario += "{ \n";
            EliminarUsuario += "\"Carnet\": " + NuevoUsuario.getCarnet() + ", \n";
            EliminarUsuario += "}, \n";
        }

        public void CrearLibroBloques(Libros NuevoLibro)
        {
            CrearLibro += "{ \n";
            CrearLibro += "\"ISBN\": " + NuevoLibro.getISBN() + ", \n";
            CrearLibro += "\"Año\": " + NuevoLibro.getAnio() + ", \n";
            CrearLibro += "\"Idioma\": \"" + NuevoLibro.getIdioma() + "\", \n";
            CrearLibro += "\"Titulo\": \"" + NuevoLibro.getTitulo() + "\", \n";
            CrearLibro += "\"Editorial\": \"" + NuevoLibro.getEditorial() + "\", \n";
            CrearLibro += "\"Autor\": \"" + NuevoLibro.getAutor() + "\", \n";
            CrearLibro += "\"Edicion\": " + NuevoLibro.getEdicion() + ", \n";
            CrearLibro += "\"Categoria\": \"" + NuevoLibro.getCategoria() + "\", \n";
            CrearLibro += "\"Carnet\": " + NuevoLibro.getCarnet_Usuario_Creador() + ", \n";
            CrearLibro += "}, \n";
        }

        public void EliminarLibroBloques(Libros NuevoLibro)
        {
            EliminarLibro += "{ \n";
            EliminarLibro += "\"ISBN\": " + NuevoLibro.getISBN() + ", \n";
            EliminarLibro += "\"Titulo\": \"" + NuevoLibro.getTitulo() + "\", \n";
            EliminarLibro += "\"Categoria\": \"" + NuevoLibro.getCategoria() + "\", \n";
            EliminarLibro += "\"Carnet\": " + NuevoLibro.getCarnet_Usuario_Creador() + ", \n";
            EliminarLibro += "}, \n";
        }

        public void CrearCategoriaBloques(NodoArbolAVL NuevaCategoria)
        {
            CrearCategoria += "{ \n";
            CrearCategoria += "\"Nombre\": \"" + NuevaCategoria.getCategoria() + "\", \n";
            CrearCategoria += "\"Carnet\": " + NuevaCategoria.getCarnet_Usuario_Creador() + ", \n";
            CrearCategoria += "}, \n";
        }

        public void EliminarCategoriaBloques(NodoArbolAVL NuevaCategoria)
        {
            EliminarCategoria += "{ \n";
            EliminarCategoria += "\"Nombre\": \"" + NuevaCategoria.getCategoria() + "\", \n";
            EliminarCategoria += "\"Carnet\": " + NuevaCategoria.getCarnet_Usuario_Creador() + ", \n";
            EliminarCategoria += "}, \n";
        }
    }
