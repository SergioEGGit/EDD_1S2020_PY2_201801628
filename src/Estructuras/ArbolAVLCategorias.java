
   package Estructuras;

   import Metodos.GenerarReportes;
   import Metodos.Variables;
   import Modelos.Libros;
   import javax.swing.*;
   import java.util.ArrayList;

   public class ArbolAVLCategorias
   {
      NodoArbolAVL Raiz;
      ArrayList<Integer> ISBN = new ArrayList<Integer>();
      Boolean Bandera = true;

      public int AlturaArbolAVL(NodoArbolAVL NuevoNodo) {
         return NuevoNodo == null ? -1 : NuevoNodo.getAltura();
      }

      public int MaximaAlturaArbolAVL(int AlturaIzquierda, int AlturaDerecha) {
         return AlturaIzquierda > AlturaDerecha ? AlturaIzquierda : AlturaDerecha;
      }

      public NodoArbolAVL RotacionSimpleIzquierdaArbolAVL(NodoArbolAVL NuevoNodo) {
         NodoArbolAVL Auxiliar = NuevoNodo.getHIzquierda();
         NuevoNodo.setHIzquierda(Auxiliar.getHDerecha());
         Auxiliar.setHDerecha(NuevoNodo);
         NuevoNodo.setAltura(MaximaAlturaArbolAVL(AlturaArbolAVL(NuevoNodo.getHIzquierda()), AlturaArbolAVL(NuevoNodo.getHDerecha())) + 1);
         Auxiliar.setAltura(MaximaAlturaArbolAVL(AlturaArbolAVL(Auxiliar.getHIzquierda()), NuevoNodo.getAltura()) + 1);
         return Auxiliar;
      }

      public NodoArbolAVL RotacionDobleIzquierdaArbolAVL(NodoArbolAVL NuevoNodo) {
         NuevoNodo.setHIzquierda(RotacionSimpleDerechaArbolAVL(NuevoNodo.getHIzquierda()));
         return RotacionSimpleIzquierdaArbolAVL(NuevoNodo);
      }

      public NodoArbolAVL RotacionSimpleDerechaArbolAVL(NodoArbolAVL NuevoNodo) {
         NodoArbolAVL Auxiliar = NuevoNodo.getHDerecha();
         NuevoNodo.setHDerecha(Auxiliar.getHIzquierda());
         Auxiliar.setHIzquierda(NuevoNodo);
         NuevoNodo.setAltura(MaximaAlturaArbolAVL(AlturaArbolAVL(NuevoNodo.getHIzquierda()), AlturaArbolAVL(NuevoNodo.getHDerecha())) + 1);
         Auxiliar.setAltura(MaximaAlturaArbolAVL(AlturaArbolAVL(Auxiliar.getHDerecha()), NuevoNodo.getAltura()) + 1);
         return Auxiliar;
      }

      public NodoArbolAVL RotacionDobleDerechaArbolAVL(NodoArbolAVL NuevoNodo) {
         NuevoNodo.setHDerecha(RotacionSimpleIzquierdaArbolAVL(NuevoNodo.getHDerecha()));
         return RotacionSimpleDerechaArbolAVL(NuevoNodo);
      }

      public NodoArbolAVL InsertarCategoriaArbolAVL(NodoArbolAVL ArbolAVL, String Categoria, int Carnet) {
         if (ArbolAVL == null)
         {
            ArbolAVL = new NodoArbolAVL(Categoria, null, null, Carnet);
            Variables.GenerarBloquesData.CrearCategoriaBloques(ArbolAVL);
         }
         else if (Categoria.compareTo(ArbolAVL.getCategoria()) < 0)
         {
            ArbolAVL.setHIzquierda(InsertarCategoriaArbolAVL(ArbolAVL.getHIzquierda(), Categoria, Carnet));

            if (AlturaArbolAVL(ArbolAVL.getHIzquierda()) - AlturaArbolAVL(ArbolAVL.getHDerecha()) < 0) {
               if (Categoria.compareTo(ArbolAVL.getHIzquierda().getCategoria()) < 0) {
                  ArbolAVL = RotacionSimpleIzquierdaArbolAVL(ArbolAVL);
               } else {
                  ArbolAVL = RotacionDobleIzquierdaArbolAVL(ArbolAVL);
               }
            }
         } else if (Categoria.compareTo(ArbolAVL.getCategoria()) > 0) {
            ArbolAVL.setHDerecha(InsertarCategoriaArbolAVL(ArbolAVL.getHDerecha(), Categoria, Carnet));

            if (AlturaArbolAVL(ArbolAVL.getHDerecha()) - AlturaArbolAVL(ArbolAVL.getHIzquierda()) == 2) {
               if (Categoria.compareTo(ArbolAVL.getHDerecha().getCategoria()) > 0) {
                  ArbolAVL = RotacionSimpleDerechaArbolAVL(ArbolAVL);
               } else {
                  ArbolAVL = RotacionDobleDerechaArbolAVL(ArbolAVL);
               }
            }
         } else {
            ArbolAVL.setAltura(MaximaAlturaArbolAVL(AlturaArbolAVL(ArbolAVL.getHIzquierda()), AlturaArbolAVL(ArbolAVL.getHDerecha())) + 1);
         }

         return ArbolAVL;
      }

      public void InsertarLibroArbolAVL(NodoArbolAVL NuevoNodo, Libros Libro) {
         if (ISBN.contains(Libro.getISBN())) {
            JOptionPane.showMessageDialog(null, "El Libro Indicado Ya Existe En El Sistema \nISBN: " + Libro.getISBN() + "\nTitulo: " + Libro.getTitulo(), "Advertencia!", JOptionPane.WARNING_MESSAGE);
            Variables.ExisteLibro = true;
         } else {
            ISBN.add(Libro.getISBN());
            NuevoNodo.getArbolB().InsertarArbolB(Libro);
            Variables.ExisteLibro = false;
         }
      }

      public void InicializarCategoriaArbolAVL(String Categoria, int Carnet) {
         Raiz = InsertarCategoriaArbolAVL(Raiz, Categoria, Carnet);
      }

      public Libros EliminarLibroArbolAVL(NodoArbolAVL NodoActual, int ISBN, int Carnet) {
         if (NodoActual != null) {
            Libros Aux = NodoActual.getArbolB().EliminarLibroArbolB(ISBN, Carnet);

            if (Aux != null) {
               return Aux;
            } else {
               if (NodoActual.getHIzquierda() != null && Aux == null) {
                  Aux = EliminarLibroArbolAVL(NodoActual.getHIzquierda(), ISBN, Carnet);
               }
               if (NodoActual.getHDerecha() != null && Aux == null) {
                  Aux = EliminarLibroArbolAVL(NodoActual.getHDerecha(), ISBN, Carnet);
               }
               return Aux;
            }
         }
         return null;
      }

      public Libros InicizalizarEliminacionArboAVL(int ISBN, int Carnet) {
         return EliminarLibroArbolAVL(Raiz, ISBN, Carnet);
      }

      public NodoArbolAVL BuscarCategoriaArbolAVL(NodoArbolAVL NuevoNodo, String Categoria) {
         if (NuevoNodo == null) {
            return NuevoNodo;
         } else {
            if (Categoria.compareTo(NuevoNodo.getCategoria()) == 0) {
               return NuevoNodo;
            } else if (Categoria.compareTo(NuevoNodo.getCategoria()) > 0) {
               return BuscarCategoriaArbolAVL(NuevoNodo.getHDerecha(), Categoria);
            } else if (Categoria.compareTo(NuevoNodo.getCategoria()) < 0) {
               return BuscarCategoriaArbolAVL(NuevoNodo.getHIzquierda(), Categoria);
            }
            return NuevoNodo;
         }
      }

      public NodoArbolAVL InicializarBuscarCategoriaArbolAVL(String Categoria) {
         return BuscarCategoriaArbolAVL(Raiz, Categoria);
      }

      public ArrayList<Libros> ListarTodosLosLibrosLista(NodoArbolAVL NodoActual) {
         ArrayList<Libros> ListaDeLibros = new ArrayList<Libros>();

         if (NodoActual != null) {
            ListaDeLibros.addAll(NodoActual.getArbolB().MostrarTodosLosLibrosArbolB());

            if (NodoActual.getHIzquierda() != null) {
               ListaDeLibros.addAll(ListarTodosLosLibrosLista(NodoActual.getHIzquierda()));
            }

            if (NodoActual.getHDerecha() != null) {
               ListaDeLibros.addAll(ListarTodosLosLibrosLista(NodoActual.getHDerecha()));
            }
         }
         return ListaDeLibros;
      }

      public ArrayList<Libros> ListarTodosLosLibrosArbolAVL() {
         return ListarTodosLosLibrosLista(Raiz);
      }

      public ArrayList<String> IniciarCatalogoArbolAVL() {
         return CatalogoArbolAVL(Raiz);
      }

      public ArrayList<String> CatalogoArbolAVL(NodoArbolAVL NodoActual) {
         ArrayList<String> Catalogo = new ArrayList<String>();

         if (NodoActual != null) {
            Catalogo.add(NodoActual.getCategoria());

            if (NodoActual.getHIzquierda() != null) {
               Catalogo.addAll(CatalogoArbolAVL(NodoActual.getHIzquierda()));
            }

            if (NodoActual.getHDerecha() != null) {
               Catalogo.addAll(CatalogoArbolAVL(NodoActual.getHDerecha()));
            }
         }
         return Catalogo;
      }

      public ArrayList<Libros> ListarLibrosCategoriaArbolAVL(NodoArbolAVL NodoActual, String Categoria) {
         ArrayList<Libros> ListaLibros = new ArrayList<Libros>();

         if (NodoActual != null) {
            if (NodoActual.getCategoria().compareTo(Categoria) == 0) {
               ListaLibros = NodoActual.getArbolB().MostrarTodosLosLibrosArbolB();
            } else {
               if (NodoActual.getCategoria().compareTo(Categoria) < 0) {
                  ListaLibros = ListarLibrosCategoriaArbolAVL(NodoActual.getHDerecha(), Categoria);
               } else if (NodoActual.getCategoria().compareTo(Categoria) > 0) {
                  ListaLibros = ListarLibrosCategoriaArbolAVL(NodoActual.getHIzquierda(), Categoria);
               }
            }
         }
         return ListaLibros;
      }

      public ArrayList<Libros> SeleccionarCategoriaLibrosAVL(String Categoria) {
         return ListarLibrosCategoriaArbolAVL(Raiz, Categoria);
      }

      public ArrayList<NodoArbolAVL> SeleccionarCategoriaArbolAVL(NodoArbolAVL NodoActual)
      {
         ArrayList<NodoArbolAVL> Categorias = new ArrayList<NodoArbolAVL>();

         if (NodoActual != null)
         {
            Categorias.add(NodoActual);

            if (NodoActual.getHIzquierda() != null)
            {
               Categorias.addAll(SeleccionarCategoriaArbolAVL(NodoActual.getHIzquierda()));
            }

            if (NodoActual.getHDerecha() != null)
            {
               Categorias.addAll(SeleccionarCategoriaArbolAVL(NodoActual.getHDerecha()));
            }
         }
         return Categorias;
      }

      public ArrayList<NodoArbolAVL> ListarCategoriasArbolAVL()
      {
         return SeleccionarCategoriaArbolAVL(Raiz);
      }

      public ArrayList<NodoArbolAVL> DevolverCategoriaArbolB(NodoArbolAVL NuevoNodo) {
         ArrayList<NodoArbolAVL> Categorias = new ArrayList<NodoArbolAVL>();

         if (NuevoNodo != null) {
            Categorias.add(NuevoNodo);

            if (NuevoNodo.getHIzquierda() != null) {
               Categorias.addAll(DevolverCategoriaArbolB(NuevoNodo.getHIzquierda()));
            }

            if (NuevoNodo.getHDerecha() != null) {
               Categorias.addAll(DevolverCategoriaArbolB(NuevoNodo.getHDerecha()));
            }
         }
         return Categorias;
      }

      public ArrayList<NodoArbolAVL> AgregarArbolAVL() {
         return DevolverCategoriaArbolB(Raiz);
      }

      public void RecorrerExisteCategoriaArbolAVL(NodoArbolAVL NuevoNodo, String Categoria)
      {
         if (NuevoNodo != null)
         {
            RecorrerExisteCategoriaArbolAVL(NuevoNodo.getHDerecha(), Categoria);

            if (Categoria.equals(NuevoNodo.getCategoria()))
            {
               Variables.ExisteCategoria = true;
            }

            RecorrerExisteCategoriaArbolAVL(NuevoNodo.getHIzquierda(), Categoria);

         }
      }

      public void ExisteCategoriaArbolAVL(String Categoria)
      {
         RecorrerExisteCategoriaArbolAVL(Raiz, Categoria);
      }

      public NodoArbolAVL ValorMinimo(NodoArbolAVL NuevoNodo)
      {
         NodoArbolAVL NodoActual = NuevoNodo;

         while(NodoActual.getHIzquierda() != null)
         {
            NodoActual = NodoActual.getHIzquierda();
         }

         return NodoActual;
      }

      public NodoArbolAVL EliminarCategoriaRecorrerArbolAVL(NodoArbolAVL Root, String Categoria, int Carnet)
      {
         if(Root == null)
         {
            return Root;
         }
         else if(Categoria.compareTo(Root.getCategoria()) < 0)
         {
             Root.setHIzquierda(EliminarCategoriaRecorrerArbolAVL(Root.getHIzquierda(), Categoria, Carnet));
         }
         else if(Categoria.compareTo(Root.getCategoria()) > 0)
         {
             Root.setHDerecha(EliminarCategoriaRecorrerArbolAVL(Root.getHDerecha(), Categoria, Carnet));
         }
         else if(Carnet == Root.getCarnet_Usuario_Creador())
         {
            Variables.CreeCategoria = true;
            if(Root.getHIzquierda() == null || Root.getHDerecha() == null)
            {
               NodoArbolAVL Aux =  null;

               if(Aux == Root.getHIzquierda())
               {
                  Aux = Root.getHDerecha();
               }
               else if(Aux == Root.getHDerecha())
               {
                  Aux = Root.getHIzquierda();
               }
               else
               {
                  Aux = null;
               }

               if(Aux == null)
               {
                  Aux = Root;
                  ArrayList<Integer> ListaEliminados = new ArrayList<Integer>();

                  for(Libros Libro: Root.getArbolB().getLibroArbolB())
                  {
                     if(Libro != null)
                     {
                        ListaEliminados.add(Libro.getISBN());
                        ISBN.remove((Object) Libro.getISBN());
                     }
                  }

                  ISBN.removeAll(ListaEliminados);

                  if(Bandera)
                  {
                     Variables.GenerarBloquesData.EliminarCategoriaBloques(Root);
                     Bandera = false;
                  }
                  Root = null;
               }
               else
               {
                   ArrayList<Integer> ListaEliminados = new ArrayList<Integer>();

                   for(Libros Libro: Root.getArbolB().getLibroArbolB())
                   {
                      if(Libro != null)
                      {
                         ListaEliminados.add(Libro.getISBN());
                         ISBN.remove((Object) Libro.getISBN());
                      }
                   }

                   ISBN.removeAll(ListaEliminados);

                   if(Bandera)
                   {
                      Variables.GenerarBloquesData.EliminarCategoriaBloques(Root);
                      Bandera = false;
                   }
                   Root = Aux;
               }
            }
            else
            {
               NodoArbolAVL Aux2 = ValorMinimo(Root.getHDerecha());
               ArrayList<Integer> ListaEliminados = new ArrayList<Integer>();

               for(Libros Libro: Root.getArbolB().getLibroArbolB())
               {
                  if(Libro != null)
                  {
                     ListaEliminados.add(Libro.getISBN());
                     ISBN.remove((Object) Libro.getISBN());
                  }
               }
               ISBN.removeAll(ListaEliminados);

               if(Bandera)
               {
                  Variables.GenerarBloquesData.EliminarCategoriaBloques(Root);
                  Bandera = false;
               }

               Root.setCategoria(Aux2.getCategoria());
               Root.setArbolB(Aux2.getArbolB());
               Root.setCarnet_Usuario_Creador(Aux2.getCarnet_Usuario_Creador());
               Root.setHDerecha(EliminarCategoriaRecorrerArbolAVL(Root.getHDerecha(), Root.getCategoria(), Root.getCarnet_Usuario_Creador()));
            }
         }

         if(Root == null)
         {
            return Root;
         }

         Root.setAltura(MaximaAlturaArbolAVL(AlturaArbolAVL(Root.getHIzquierda()), AlturaArbolAVL(Root.getHDerecha())) + 1);

         if(AlturaArbolAVL(Root.getHIzquierda()) - AlturaArbolAVL(Root.getHDerecha()) == 2)
         {
            if(Categoria.compareTo(Root.getHIzquierda().getCategoria()) < 0)
            {
               return RotacionDobleIzquierdaArbolAVL(Root);
            }
            else
            {
               try
               {
                  return RotacionDobleIzquierdaArbolAVL(Root);
               }
               catch(Exception Ex)
               {
                  return RotacionSimpleIzquierdaArbolAVL(Root);
               }
            }
         }
         else if(AlturaArbolAVL(Root.getHDerecha()) - AlturaArbolAVL(Root.getHIzquierda()) == 2)
         {
            if(Categoria.compareTo(Root.getHDerecha().getCategoria()) > 0)
            {
               return RotacionDobleDerechaArbolAVL(Root);
            }
            else
            {
               try
               {
                  return RotacionDobleDerechaArbolAVL(Root);
               }
               catch(Exception Ex)
               {
                  return RotacionSimpleDerechaArbolAVL(Root);
               }
            }
         }
         return Root;
      }

      public void EliminarCategoriaArbolAVL(String Categoria, int Carnet)
      {
         Bandera = true;
         Raiz = EliminarCategoriaRecorrerArbolAVL(Raiz, Categoria, Carnet);
      }

      public void GraficarLibrosArbolAVL(NodoArbolAVL NodoActual)
      {
         String Cadena = "";

         Cadena += "digraph G { \n node[shape = rect, fontname = Arial, color = darkorange, fontcolor = deepskyblue3]; \n";
         Cadena += NodoActual.getArbolB().MostrarGrafoArbolB();
         Cadena += "}";

         GenerarReportes ArbolBLibros = new GenerarReportes("ReporteLibrosArbolB", Cadena);
      }

      public String SubGrafica(NodoArbolAVL Root)
      {
         String Cadena = "";

         if(Root != null)
         {
            Cadena += Root.getCategoria() + " [label = \"" + Root.getCategoria() + "\nLibros: " + Root.getArbolB().getLibroArbolB().size() + "\"];\n";

            if(Root.getHIzquierda() != null)
            {
               Cadena += Root.getCategoria() + " -> " + Root.getHIzquierda().getCategoria() + "; \n";
               Cadena += SubGrafica(Root.getHIzquierda());
            }

            if(Root.getHDerecha() != null)
            {
               Cadena += Root.getCategoria() + " -> " + Root.getHDerecha().getCategoria() + "; \n";
               Cadena += SubGrafica(Root.getHDerecha());
            }
         }
         return Cadena;
      }

      public void GraficarArbolAVL()
      {
         String Cadena = "";

         Cadena += "digraph G { \n";
         Cadena += "node [fontname = Arial, color = deeppink3, fontcolor = crimson]";
         Cadena += SubGrafica(Raiz);
         Cadena += "}";

         GenerarReportes ArbolAVLCategorias = new GenerarReportes("ReporteCategoriasArbolAVL", Cadena);
      }

      public ArrayList<String> RecorridoPreOrdenArbolAVL(NodoArbolAVL NodoActual)
      {
         ArrayList<String> ListaCategorias = new ArrayList<String>();

         if (NodoActual != null)
         {
            ListaCategorias.add(NodoActual.getCategoria());

            if (NodoActual.getHIzquierda() != null)
            {
               ListaCategorias.addAll(RecorridoPreOrdenArbolAVL(NodoActual.getHIzquierda()));
            }

            if (NodoActual.getHDerecha() != null)
            {
               ListaCategorias.addAll(RecorridoPreOrdenArbolAVL(NodoActual.getHDerecha()));
            }
         }
         return ListaCategorias;
      }

      public ArrayList<String> RecorridoInOrdenArbolAVL(NodoArbolAVL NodoActual)
      {
         ArrayList<String> ListaCategorias = new ArrayList<String>();

         if (NodoActual != null)
         {
            if (NodoActual.getHIzquierda() != null)
            {
               ListaCategorias.addAll(RecorridoInOrdenArbolAVL(NodoActual.getHIzquierda()));
            }

            ListaCategorias.add(NodoActual.getCategoria());

            if (NodoActual.getHDerecha() != null)
            {
               ListaCategorias.addAll(RecorridoInOrdenArbolAVL(NodoActual.getHDerecha()));
            }
         }
         return ListaCategorias;
      }

      public ArrayList<String> RecorridoPostOrdenArbolAVL(NodoArbolAVL NodoActual)
      {
         ArrayList<String> ListaCategorias = new ArrayList<String>();

         if (NodoActual != null)
         {
            if (NodoActual.getHIzquierda() != null)
            {
               ListaCategorias.addAll(RecorridoPostOrdenArbolAVL(NodoActual.getHIzquierda()));
            }

            if (NodoActual.getHDerecha() != null)
            {
               ListaCategorias.addAll(RecorridoPostOrdenArbolAVL(NodoActual.getHDerecha()));
            }

            ListaCategorias.add(NodoActual.getCategoria());
         }
         return ListaCategorias;
      }

      public void GraficarRecorridosArbolAVL()
      {
          String Cadena = "";

          Cadena += "digraph G { \n";
          Cadena += "node[shape = box, fontname = Arial, color = firebrick1, fontcolor = blue4] \n";

          int Contador = 0;
          int ContadorAux;
          ArrayList<String> Temp;
          ArrayList<String> Categorias;
          String Same;
          String Asc;
          String Primero;
          String Segundo;
          String Tercero;


          ContadorAux = 0;
          Same = "";
          Asc = "";
          Temp = new ArrayList<>();
          Categorias = new ArrayList<>();
          Categorias = Variables.ArbolAVLCategorias.RecorridoPreOrdenArbolAVL(Raiz);
          Cadena += "A" + Contador + "[label = \"" + "Arbol AVL: Recorrido PreOrden" + "\"] \n";
          Primero = "A" + Contador;
          Temp.add(Primero);
          Contador++;

          for(String Categoria: Categorias)
          {
             Cadena += "A" + Contador + "[label = \"" + Categoria + "\"] \n";
             Temp.add("A" + Contador);
             Contador++;
          }

          for(String Nodo: Temp)
          {
             Same += Nodo + " ";

             if(ContadorAux < Temp.size() - 1)
             {
                Asc += Nodo + "->";
             }
             else
             {
                Asc += Nodo;
             }

             ContadorAux++;
          }

          Cadena += "{ rank = same " + Same + "} \n";
          Cadena += Asc + "\n";


         ContadorAux = 0;
         Same = "";
         Asc = "";
         Temp = new ArrayList<>();
         Categorias = new ArrayList<>();
         Categorias = Variables.ArbolAVLCategorias.RecorridoInOrdenArbolAVL(Raiz);
         Cadena += "B" + Contador + "[label = \"" + "Arbol AVL: Recorrido InOrden" + "\"] \n";
         Segundo = "B" + Contador;
         Temp.add(Segundo);
         Contador++;

         for(String Categoria: Categorias)
         {
            Cadena += "B" + Contador + "[label = \"" + Categoria + "\"] \n";
            Temp.add("B" + Contador);
            Contador++;
         }

         for(String Nodo: Temp)
         {
            Same += Nodo + " ";

            if(ContadorAux < Temp.size() - 1)
            {
               Asc += Nodo + "->";
            }
            else
            {
               Asc += Nodo;
            }

            ContadorAux++;
         }

         Cadena += "{ rank = same " + Same + "}\n";
         Cadena += Asc + "\n";


         ContadorAux = 0;
         Same = "";
         Asc = "";
         Temp = new ArrayList<>();
         Categorias = new ArrayList<>();
         Categorias = Variables.ArbolAVLCategorias.RecorridoPostOrdenArbolAVL(Raiz);
         Cadena += "C" + Contador + "[label = \"" + "Arbol AVL: Recorrido PostOrden" + "\"] \n";
         Tercero = "C" + Contador;
         Temp.add(Tercero);
         Contador++;

         for(String Categoria: Categorias)
         {
            Cadena += "C" + Contador + "[label = \"" + Categoria + "\"] \n";
            Temp.add("C" + Contador);
            Contador++;
         }

         for(String Nodo: Temp)
         {
            Same += Nodo + " ";

            if(ContadorAux < Temp.size() - 1)
            {
               Asc += Nodo + "->";
            }
            else
            {
               Asc += Nodo;
            }

            ContadorAux++;
         }

         Cadena += "{ rank = same " + Same + "}\n";
         Cadena += Asc + "\n";

         Cadena += Primero + " -> " + Segundo + " -> " + Tercero + "\n";
         Cadena += "}";

         GenerarReportes ArbolAVLRecorridos = new GenerarReportes("ReporteRecorridosArbolAVL", Cadena);
      }
   }
