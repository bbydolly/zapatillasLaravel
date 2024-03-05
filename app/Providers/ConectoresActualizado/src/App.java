public class App {
    //F5 ejecutar en VSC
    //F9 ejecutar en Heidy
    // Crear un proyecto java y agregar el jar en la carpeta lib
    // Crear proyecto maven y añadir la dependencia al pom
    public static void main(String[] args) throws Exception {
        Ejercicios e = new Ejercicios();
        //e.abrirConexion("add", "localhost", "root", "");
        //Ejercicio 1
        // e.consultarAlumnos("u");
        //Ejercicio 2
        // e.altaAlumnos("Pedro", "Cendón", 170, 11);
        // boolean existe = e.comprobarAsignatura("Filología");
        // if (!existe) {
        // e.altaAsignaturas("Filología");

        // } else {
        // System.out.println("El nombre de la asignatura ya existe en la base de
        // datos");
        // }
        //Ejercicio 3
        // boolean existeAlumno=e.comprobarAlumno(4);
        // if(existeAlumno){
        // e.bjaAlumnos(4);
        // }else{
        // System.out.println("El alumno no existe en la base de datos");
        // }

        // if (e.comprobarAsignatura("Filología")) {
        //     e.bjaAsignaturas(9);

        // } else {
        //     System.out.println("La asignatura no existe en la base de datos");
        // }
        // e.nameAulasAlumnos();

        //Ejercicio 4
        //e.modificarAlumAsig(null, null);

        //Ejercicio 5
        // e.nameAulasAlumnos();
        // e.nAlumAsigNotaAprobados();
        // e.asigSinAlum();

        //Ejercicio 6
        //Los patrones se escriben: %letra%
        //e.patrónAlumnoPS("%r%",130 , 5);
        //e.patronAlum("%r%",130 , 5);
       // e.ejecutarConsultas(1000);
        //Conclusión, que cuantas más veces se ejecuta, más tiempo tarda 
        //e.mCuatroParams("alumnos", "matricula", "varchar(5)", "null");
        //e.obtenerInfoBD_tabla("add",8);
        //e.obtenerDatos_ColumnasDevueltas();
        //e.lista_datos_JDBC();

        //Ejercicio 13
        e.abrirConexion("ad", "localhost", "root", "");
        
        e.leer_y_almacenar_OB(2);
    }
}
