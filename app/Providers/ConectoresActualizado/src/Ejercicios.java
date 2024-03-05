
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Ejercicios {
    // No sé como establecer la conexión me dice que es nula

    private Connection conexion;
    boolean existeA = false;
    boolean existe = false;
    Scanner sc = new Scanner(System.in);
    private PreparedStatement ps = null;
    private PreparedStatement ps2 = null;

    public void abrirConexion(String bd, String servidor, String usuario,
            String password) {

        try {
            String url = String.format("jdbc:mariadb://%s:3306/%s", servidor, bd);
            // String url = String.format("jdbc:mysql://%s:3306/%s", servidor, bd);

            // Establecemos la conexión con la BD
            // true-->para establecer sentencias preparadas
            this.conexion = DriverManager.getConnection(url + "?useServerPrepStmts=true", usuario, password);

            if (this.conexion != null) {
                System.out.println("Conectado a " + bd + " en " + servidor);
            } else {
                System.out.println("No conectado a " + bd + " en " + servidor);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getLocalizedMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Código error: " + e.getErrorCode());
        }
    }

    public void cerrarConexion() {
        try {
            this.conexion.close();
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexión");
        }
    }

    // Ejercicio 1
    public void consultarAlumnos(String cad) {

        try (Statement stmt = this.conexion.createStatement()) {
            String query = "select nombre from alumnos where nombre like '%" + cad + "%'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }

        } catch (SQLException e) {

        }
    }

    public boolean comprobarAsignatura(String nameAsig) throws SQLException {
        existeA = false;
        try (Statement st = this.conexion.createStatement()) {
            String query = "Select NOMBRE from asignaturas";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                if (rs.getString("NOMBRE").equals(nameAsig)) {
                    existeA = true;
                    return true;
                }
            }

        }
        System.out.println(existeA + " " + nameAsig);
        return existeA;
    }

    public boolean comprobarAlumno(int codigo) throws SQLException {
        existe = false;
        try (Statement st = this.conexion.createStatement()) {
            String query = "Select codigo from alumnos";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                if (rs.getInt("codigo") == codigo) {
                    existe = true;
                    return existe;
                } else {
                    existe = false;
                }
            }

        }

        return existe;
    }

    // Ejercicio 2
    public void altaAsignaturas(String nameAsig) {
        try (Statement stmt = this.conexion.createStatement()) {

            if (!comprobarAsignatura(nameAsig)) {

                String query = String.format("Insert into asignaturas (NOMBRE) VALUES ('%s')", nameAsig);
                stmt.executeUpdate(query);
                System.out.println("Asignatura añadida a la base de datos");
            } else {
                System.out.println("El nombre de la asignatura ya existe en la base de datos");
            }

        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());

        }
    }

    public void altaAlumnos(String nombre, String apellidos, int altura, int aula) {
        try (Statement stmt = this.conexion.createStatement()) {

            if (!existe) {

                String query2 = String.format(
                        "INSERT INTO alumnos (nombre,apellidos,altura,aula) VALUES ('%s','%s',%d,%d)", nombre,
                        apellidos, altura, aula);
                stmt.executeUpdate(query2);
                System.out.println("Alumno añadido correctamente");
            } else {
                System.out.println("El alumno ya existe en la base de datos");
            }

        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());

        }
    }

    // Ejercicio 3
    public void bjaAlumnos(int codAlumno) {

        try (Statement stmt = this.conexion.createStatement()) {

            if (existe) {

                String query2 = String.format("DELETE FROM alumnos WHERE codigo=%d", codAlumno);
                stmt.executeUpdate(query2);
                System.out.println("El alumno ha sido eliminado");
            } else {
                System.out.println("El alumno no consta en la base de datos");
            }

        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        }

    }

    public void bjaAsignaturas(int codAsig) {

        try (Statement stmt = this.conexion.createStatement()) {
            if (existeA) {
                String query = String.format("DELETE FROM asignaturas where COD=%d", codAsig);
                stmt.executeUpdate(query);
                System.out.println("La asignatura ha sido eliminada de la base de datos");
            } else {
                System.out.println(existeA);
                System.out.println("La asignatura no consta en la base de datos");
            }

        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        }
        // Se cierra la conexión automáticamente?
    }

    // Ejercicio 4--modificar el nombre de las columnas?
    public void modificarAlumAsig(String tabla, String nombreColumna) {

        try (Statement stm = this.conexion.createStatement()) {
            String query = "INSERT INTRO " + tabla + " MODIFY " + nombreColumna;
            int filasAfectadas1 = stm.executeUpdate(query);
            System.out.println("Filas afectadas de asignaturas " + filasAfectadas1);
        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        }
    }

    // Ejercicio 5
    // a
    public void nameAulasAlumnos() {
        try (Statement st = this.conexion.createStatement()) {
            String query = "SELECT nombreAula FROM aulas WHERE puestos>=1 && nombreAula IS not NULL";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                System.out.println(rs.getString("nombreAula"));
            }

        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());

        }
    }

    // b
    public void nAlumAsigNotaAprobados() {
        try (Statement st = this.conexion.createStatement()) {
            String query = "SELECT alumnos.nombre,asignaturas.nombre AS asignatura,NOTA AS nota FROM notas JOIN alumnos ON notas.alumno=alumnos.codigo JOIN ASIGNATURAS ON notas.asignatura=asignaturas.cod WHERE NOTA>5";
            ResultSet rs = st.executeQuery(query);
            // recojo los valores y los imprimo según el tipo de dato
            while (rs.next()) {
                System.out.println(
                        rs.getString("nombre") + "----" + rs.getString("asignatura") + "----" + rs.getInt("nota"));
            }
        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        }
    }

    // c
    public void asigSinAlum() {
        try (Statement st = this.conexion.createStatement()) {
            String query = "SELECT NOMBRE FROM asignaturas WHERE COD not IN (SELECT asignatura FROM notas)";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());

        }
    }

    // Ejercicio 6
    // consultas preparadas
    public void patrónAlumnoPS(String patron, int valor, int limit) {
        try {
            String query = "SELECT nombre FROM alumnos WHERE nombre LIKE ? && altura>  ? LIMIT ?";
            if (this.ps == null) {
                this.ps = this.conexion.prepareStatement(query); // preparo la consulta
            }
            // meto los valores al procedimiento
            ps.setString(1, patron);
            ps.setInt(2, valor);
            ps.setInt(3, limit);
            // ejecuto la query desde el Statement, sin parámetros
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }

        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());

        }
    }

    // 6 sin sentencias preparadas
    public void patronAlum(String patron, int valor, int limit) {
        try (Statement st = this.conexion.createStatement()) {
            // se pone comillas para patrones '%s'
            String query = String.format("SELECT nombre FROM alumnos WHERE nombre LIKE '%s' && altura> %d LIMIT %d",
                    patron, valor, limit);
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }

        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());

        }

    }

    // Ejercicio 7
    // Revisar Hshtable
    public void ejecutarConsultas(int veces) throws SQLException {
        long inicio = System.currentTimeMillis();
        for (int i = 0; i < veces; i++) {

            abrirConexion("add", "localhost", "root", "");
            // Ejercicio 1
            consultarAlumnos("u");
            // Ejercicio 2
            altaAlumnos("Pedro", "Cendón", 170, 11);
            boolean existe = false;
            try {
                existe = comprobarAsignatura("Filología");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (!existe) {
                altaAsignaturas("Filología");

            } else {
                System.out.println("El nombre de la asignatura ya existe en la base de datos");
            }
            // Ejercicio 3
            boolean existeAlumno = comprobarAlumno(4);
            if (existeAlumno) {
                bjaAlumnos(4);
            } else {
                System.out.println("El alumno no existe en la base de datos");
            }

            if (comprobarAsignatura("Filología")) {
                bjaAsignaturas(9);

            } else {
                System.out.println("La asignatura no existe en la base de datos");
            }
            nameAulasAlumnos();

            // Ejercicio 4
            modificarAlumAsig(null, null);

            // Ejercicio 5
            nameAulasAlumnos();
            nAlumAsigNotaAprobados();
            asigSinAlum();

            // Ejercicio 6

            patrónAlumnoPS("%r%", 130, 5);
            patronAlum("%r%", 130, 5);
        }
        long fin = System.currentTimeMillis();

        System.out.printf("La cantidad de milisegundos que ha tardado la ejecución de %d veces es de %d ms", veces,
                fin - inicio);

    }

    // Ejercicio 8-añadir colum a tabla
    public void mCuatroParams(String tabla, String nombreCampo, String tipoDato, String propiedades) {
        try (Statement st = this.conexion.createStatement()) {
            // propiedad ej not null
            String query = String.format("ALTER TABLE %s ADD %s %s %s", tabla, nombreCampo, tipoDato, propiedades);
            System.out.println(query);
            int n = st.executeUpdate(query); // filas afectadas
            System.out.println("La cantidad de filas afectadas es de " + n);

        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());

        }

    }

    // Ejercicio 9
    public void obtenerInfoBD_tabla(String bd, int opc) {
        DatabaseMetaData dbmt;
        ResultSet tablas, columnas;
        // try {
        // dbmt = this.conexion.getMetaData();
        // tablas = dbmt.getTables(bd, null, null, null);
        // while (tablas.next()) {
        // System.out.println(String.format("%s %s",
        // tablas.getString("TABLE_NAME"), tablas.getString("TABLE_TYPE")));
        // columnas = dbmt.getColumns(bd, null,
        // tablas.getString("TABLE_NAME"), null);
        // while (columnas.next()) {
        // System.out.println(String.format(" %s %s %d %s %s",
        // columnas.getString("COLUMN_NAME"),
        // columnas.getString("TYPE_NAME"),
        // columnas.getInt("COLUMN_SIZE"),
        // columnas.getString("IS_NULLABLE"),
        // columnas.getString("IS_AUTOINCREMENT")));
        // }
        // }
        // } catch (SQLException e) {
        // System.out.println("Error obteniendo datos " + e.getLocalizedMessage());
        // }

        switch (opc) {
            case 1:
                try {
                    dbmt = this.conexion.getMetaData();
                    System.out.println(String.format("%s s%s %s %s %s %s", dbmt.getDriverName(),
                            dbmt.getDriverVersion(), dbmt.getURL(), dbmt.getUserName(), dbmt.getJDBCMajorVersion(),
                            dbmt.getSQLKeywords()));

                } catch (Exception e) {
                    System.out.println("Error obteniendo datos " + e.getLocalizedMessage());

                }

                break;
            case 2:
                try {
                    dbmt = this.conexion.getMetaData();
                    ResultSet bd_cat = dbmt.getCatalogs();
                    while (bd_cat.next()) {
                        System.out.println(String.format("%s",
                                bd_cat.getString("TABLE_CAT")));
                    }

                } catch (Exception e) {
                    System.out.println("Error obteniendo datos " + e.getLocalizedMessage());

                }

                break;
            case 3:
                try {
                    dbmt = this.conexion.getMetaData();
                    tablas = dbmt.getTables(bd, null, null, null);
                    while (tablas.next()) {
                        System.out.println(String.format("%s %s",
                                tablas.getString("TABLE_NAME"), tablas.getString("TABLE_TYPE")));
                    }
                } catch (Exception e) {
                    System.out.println("Error obteniendo datos " + e.getLocalizedMessage());
                }

                break;
            case 4:
                try {
                    dbmt = this.conexion.getMetaData();
                    // mostrar las vistas, son tablas
                    tablas = dbmt.getTables("add", null, null, new String[] { "view" });
                    while (tablas.next()) {
                        System.out.println(String.format("%s %s",
                                tablas.getString("TABLE_NAME"), tablas.getString("TABLE_TYPE")));
                    }
                } catch (Exception e) {
                    System.out.println("Error obteniendo datos " + e.getLocalizedMessage());
                }

                break;
            case 5:
                System.out.println("Selecciona una opción: \n1:Mostrar Catalogs\n2:Mostrar las tablas de add");
                int bc = sc.nextInt();
                try {
                    dbmt = this.conexion.getMetaData();

                    if (bc == 1) {
                        ResultSet bd_cat = dbmt.getCatalogs();
                        while (bd_cat.next()) {
                            System.out.println(String.format("%s",
                                    bd_cat.getString("TABLE_CAT")));
                        }

                    } else if (bc == 2) {
                        tablas = dbmt.getTables(bd, null, null, null);
                        while (tablas.next()) {
                            System.out.println(String.format("%s %s",
                                    tablas.getString("TABLE_NAME"), tablas.getString("TABLE_TYPE")));
                        }
                    } else {
                        System.out.println("La opción no es válida");
                    }

                } catch (Exception e) {
                    System.out.println("Error obteniendo datos " + e.getLocalizedMessage());

                }

                break;
            case 6:
                try {
                    dbmt = this.conexion.getMetaData();
                    ResultSet procedimientos = dbmt.getProcedures("add", null, null);
                    while (procedimientos.next()) {
                        System.out.println(String.format("%s", procedimientos.getString("PROCEDURE_NAME")));
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }

                break;

            case 7:
                try {
                    dbmt = this.conexion.getMetaData();
                    // mostrar las vistas, son tablas
                    tablas = dbmt.getColumns(bd, null, "a%", null);
                    while (tablas.next()) {
                        System.out.println(String.format("%s", tablas.getString("ORDINAL_POSITION")));
                        System.out.println(String.format("%s %s %s %s %S %d",
                                tablas.getString("TABLE_CAT"),

                                tablas.getString("TABLE_NAME"),
                                // tablas.getString("TABLE_SCHEM"),
                                tablas.getString("COLUMN_NAME"),
                                tablas.getString("TYPE_NAME"),
                                tablas.getString("IS_AUTOINCREMENT"),

                                tablas.getInt("NULLABLE")));

                    }

                } catch (Exception e) {
                    System.out.println("Error obteniendo datos " + e.getLocalizedMessage());
                }

                break;
            case 8:
                try {
                    dbmt = this.conexion.getMetaData();
                    ResultSet pk = dbmt.getPrimaryKeys(bd, null, null);// null--> coge todas las tabals de la bd
                    while (pk.next()) {
                        System.out.println(String.format("%s", pk.getString("TABLE_CAT")));
                        System.out.println(String.format("%s", pk.getString("TABLE_NAME")));
                        System.out.println(String.format("%s", pk.getString("COLUMN_NAME")));

                        System.out.println(String.format("%s", pk.getString("PK_NAME")));
                    }
                    ResultSet rs = dbmt.getExportedKeys(bd, null, null);
                    while (rs.next()) {
                        System.out.println(String.format("\n%s", rs.getString("PKTABLE_CAT")));
                        System.out.println(String.format("%s", rs.getString("PKTABLE_NAME")));
                        System.out.println(String.format("%s", rs.getString("PKCOLUMN_NAME")));
                    }
                } catch (Exception e) {
                    System.out.println("Error obteniendo datos " + e.getLocalizedMessage());
                }
                break;

            default:
                break;
        }
    }
    // Ejercicio 10

    public void obtenerDatos_ColumnasDevueltas() {
        try (Statement stm = this.conexion.createStatement()) {
            String query = "select *, nombre as non from alumnos";
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmt = rs.getMetaData();
            System.out.println("Num    \tNombre     \tAlias     \tTipoDatos");
            for (int i = 1; i < rsmt.getColumnCount(); i++) {
                System.out.println(String.format("%d \t %s \t %s \t %s", i,
                        rsmt.getColumnName(i),
                        rsmt.getColumnLabel(i),
                        rsmt.getColumnTypeName(i)));

            }

        } catch (Exception e) {
            // TODO: handle exception

        }
    }

    // Ejercicio 11
    public void lista_datos_JDBC() {
        // try {
        Enumeration<Driver> d = DriverManager.getDrivers();

        Driver driver = (Driver) d.nextElement();
        System.out.println("Nombre: " + driver.getClass().getName());
        System.out.println("Version: " + driver.getMajorVersion());
        System.out.println("Release: " + driver.getMinorVersion());

        if (driver.jdbcCompliant()) {
            System.out.println("Es un dirver JDBC Compliant");
        }
        // } catch (SQLException e) {
        // TODO: handle exception
        // }
    }

    // Ejercicio 12
    /*
     * Para que la base de datos quede en un estado inicial si una inserción falla
     * se necesita gestionar
     * una transacción, cumpliendo con las propiedades ACID
     * (atomicidad,consistencia,aislamiento y durabilidad)
     * Esto garantiza que la base de datos quede en un estado consistente tras su
     * ejecución
     * Ejemplo 1: hacer un commit, se confirman los cambios guardados por la
     * transacción
     * Ejemplo 2: dentro del catch se realiza un rollback(), eso significa que si se
     * produce un error se
     * dehacen los cambios
     * savepoint: definir un punto de salvaguarda dentro de una transacción, para
     * evitar deshacer todos los cambios
     * Ejemplo de savepoint:
     * INSERT INTO DEPARTMENT VALUES ('A20', 'MARKETING', 301)
     * SAVEPOINT SAVEPOINT1 ON ROLLBACK RETAIN CURSORS
     * instrucción + nombreSavePoint +respuesta del sistema a este punto de
     * salvaguarda
     * Si salta una excepción será de tipo SQLException que contiene un método para
     * recoger el código de error:
     * System.out.println(e.getErrorCode());
     * 
     */

    // Ejercicio 13-->Leer y almacenar objetos binarios con flujo de archivos
    public void leer_y_almacenar_OB(int opc) {

        switch (opc) {
            case 1:
                try (Statement stm = this.conexion.createStatement()) {
                    String query = "SELECT nombre,imagen from images";
                    ResultSet imagenes = stm.executeQuery(query);
                    while (imagenes.next()) {
                        try (InputStream leer_img = imagenes.getBinaryStream("imagen");
                                FileOutputStream sos = new FileOutputStream(imagenes.getString("nombre"));) {
                            int i;

                            while ((i = leer_img.read()) != -1) {

                                sos.write(i);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (SQLException s) {
                    s.printStackTrace();
                }

                break;
            case 2:

                // System.out.println("Dime la ruta de la imagen que quieres almacenar en la
                // base de datos");
                // String rutaImg = sc.nextLine();
                System.out.println("Dime el nombre de la imagen con el que la quieres guardar");
                String nombre = sc.nextLine();

                try {
                    String rutaImg = "C:\\Users\\Cristina\\Desktop\\subaru.jpg";
                    String query = "Insert into images VALUES(?,?)";
                    if (this.ps2 == null) {
                        this.ps2 = this.conexion.prepareStatement(query); // preparo la consulta
                    }
                    // New FileInputStream se encarga de hacer todo

                    ps2.setString(1, nombre);
                    ps2.setBinaryStream(2, new FileInputStream(new File(rutaImg)));

                    ResultSet rs = ps2.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getString("nombre"));
                    }

                } catch (SQLException e) {
                    e.getLocalizedMessage();
                    e.printStackTrace();
                } catch (FileNotFoundException i) {

                }

                break;

            default:
                System.out.println("Opción no válida");
                break;
        }

    }

    //Ejercicio 14
}
