
    package Modelos;


    public class Usuarios
    {
        private int Carnet;
        private String Nombre;
        private String Apellido;
        private String Carrera;
        private String Password;
        private Usuarios Sgte;

        public Usuarios(int carnet, String nombre, String apellido, String carrera, String password)
        {
            Carnet = carnet;
            Nombre = nombre;
            Apellido = apellido;
            Carrera = carrera;
            Password = password;
            Sgte = null;
        }

        public int getCarnet()
        {
            return Carnet;
        }

        public void setCarnet(int carnet)
        {
            Carnet = carnet;
        }

        public String getNombre()
        {
            return Nombre;
        }

        public void setNombre(String nombre)
        {
            Nombre = nombre;
        }

        public String getApellido()
        {
            return Apellido;
        }

        public void setApellido(String apellido)
        {
            Apellido = apellido;
        }

        public String getCarrera() {
            return Carrera;
        }

        public void setCarrera(String carrera) {
            Carrera = carrera;
        }

        public String getPassword()
        {
            return Password;
        }

        public void setPassword(String password)
        {
            Password = password;
        }

        public Usuarios getSgte()
        {
            return Sgte;
        }

        public void setSgte(Usuarios sgte)
        {
            Sgte = sgte;
        }
    }
