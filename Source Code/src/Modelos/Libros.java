
    package Modelos;


    public class Libros
    {
        private int ISBN;
        private String Titulo;
        private String Autor;
        private String Editorial;
        private int Anio;
        private int Edicion;
        private String Categoria;
        private String Idioma;
        private int Carnet_Usuario_Creador;

        public Libros(int ISBN, String titulo, String autor, String editorial, int anio, int edicion, String categoria, String idioma, int carnet_Usuario_Creador)
        {
            this.ISBN = ISBN;
            Titulo = titulo;
            Autor = autor;
            Editorial = editorial;
            Anio = anio;
            Edicion = edicion;
            Categoria = categoria;
            Idioma = idioma;
            Carnet_Usuario_Creador = carnet_Usuario_Creador;
        }

        public Libros()
        {

        }

        public int getISBN()
        {
            return ISBN;
        }

        public String getTitulo()
        {
            return Titulo;
        }

        public String getAutor()
        {
            return Autor;
        }

        public String getEditorial()
        {
            return Editorial;
        }

        public int getAnio()
        {
            return Anio;
        }

        public int getEdicion()
        {
            return Edicion;
        }

        public String getCategoria()
        {
            return Categoria;
        }

        public String getIdioma()
        {
            return Idioma;
        }

        public int getCarnet_Usuario_Creador()
        {
            return Carnet_Usuario_Creador;
        }
    }
