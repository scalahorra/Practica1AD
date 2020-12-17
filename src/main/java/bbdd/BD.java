package bbdd;

import java.sql.*;
import java.util.Scanner;

public class BD {

	public static void main(String[] args) {
		
		
		try {
		//Creamos la conexion	
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
		
		Statement statement = connection.createStatement();
		
		
		//Menu
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		
		int eleccion;
		
		System.out.println("-----------------------------------------");
		System.out.println("¿Qué quiere hacer con la base de datos?");
		System.out.println("1. Añadir un cliente");
		System.out.println("2. Mostrar un cliente");
		System.out.println("3. Mostrar todos los clientes");
		System.out.println("4. Buscar clientes");
		System.out.println("5. Editar un producto");
		System.out.println("-----------------------------------------");
		
		eleccion = entrada.nextInt();
		
		
		switch(eleccion) {
		
		case 1: //Añadir un cliente 
		
			//Pedimos la informacion para añadir un cliente
			System.out.println("Introduzca el codigo del cliente nuevo");
			int codigo_cliente = entrada.nextInt();
			
			System.out.println("Introduzca el nombre del cliente nuevo");
			String nombre_cliente = entrada.next();
			nombre_cliente = nombre_cliente + entrada.nextLine();
			
			System.out.println("Introduzca el telefono");
			String telefonoCaso1 = entrada.next();
			telefonoCaso1 = telefonoCaso1 + entrada.nextLine();
			
			System.out.println("Introduzca el fax");
			String faxCaso1 = entrada.next();
			faxCaso1 = faxCaso1 + entrada.nextLine();
			
			System.out.println("Introduzca la direccion");
			String direccion = entrada.next();
			direccion = direccion + entrada.nextLine();
			
			System.out.println("Introduzca la ciudad");
			String ciudadCaso1 = entrada.next();
			ciudadCaso1 = ciudadCaso1 + entrada.nextLine();
			
			//Ejecutamos la sentencia para inserta en la tabla cliente los valores que no pueden ser nulos
			statement.executeUpdate("INSERT INTO cliente (codigo_cliente, nombre_cliente, telefono, fax, linea_direccion1, ciudad) VALUES ('" + codigo_cliente + "','" + nombre_cliente 
					+ "','" + telefonoCaso1 + "','" + faxCaso1 + "','" + direccion + "','" + ciudadCaso1 + "');");
			
			//Mensaje para confirmar que se ha insertado correctamente
			System.out.println("Cliente introducido correctamente");
			
			break;
			
			
			
		case 2: //Mostrar un cliente por codigo_cliente
			
			//Preguntamos por la id del cliente que se quiere buscar
			System.out.println("Introduzca la id del cliente que quiere buscar");
			int codigo_clienteBuscar = entrada.nextInt();
			
			//Ejecutamos la sentencia en la que seleccionamos todas las columnas de la tabla cliente donde el codigo cliente sea el escrito por el usuario
			ResultSet resultSetMostrarPorId = statement.executeQuery("SELECT * FROM cliente WHERE codigo_cliente = " + codigo_clienteBuscar);
			
			//Recorremos todos los datos hasta que se acaban
			while(resultSetMostrarPorId.next()) {
				
				//Ponemos en variable todas las columnas 
				int codigo = resultSetMostrarPorId.getInt("codigo_cliente");
				String nombreCliente = resultSetMostrarPorId.getString("nombre_cliente");
				String nombreContacto = resultSetMostrarPorId.getString("nombre_contacto");
				String apellidoContacto = resultSetMostrarPorId.getString("apellido_contacto");
				String telefono = resultSetMostrarPorId.getString("telefono");
				String fax = resultSetMostrarPorId.getString("fax");
				String direccion1 = resultSetMostrarPorId.getString("linea_direccion1");
				String direccion2 = resultSetMostrarPorId.getString("linea_direccion2");
				String ciudad = resultSetMostrarPorId.getString("ciudad");
				String region = resultSetMostrarPorId.getString("region");
				String pais = resultSetMostrarPorId.getString("pais");
				String codigoPostal = resultSetMostrarPorId.getString("codigo_postal");
				int codigoEmpleado = resultSetMostrarPorId.getInt("codigo_empleado_rep_ventas");
				float limiteCredito = resultSetMostrarPorId.getFloat("limite_credito");
				
				//Mostramos en pantalla todos los datos
				System.out.println("Codigo cliente: " + codigo + " | Nombre cliente: " + nombreCliente + " | Nombre contacto: " + nombreContacto + " | ApellidoContacto: " + 
						apellidoContacto + " | Telefono: " + telefono + " | Fax: " + fax + " | Direccion 1: " + direccion1 + " | Direccion 2: " + direccion2 + " | Ciudad: " +
						ciudad + " | Region: " + region + " | Pais: " + pais + " | Codigo postal: " + codigoPostal + " | Codigo empleado: " + codigoEmpleado + " | Limite credito " 
						+ limiteCredito);
			}
			
			
			break;
			
			
			
		case 3: //Mostrar todos los clientes
			
			//Introducimos la sentencia de que extraiga todas las columnas de cliente y las ordene por el nombre
			ResultSet resultSetMostrarTodos = statement.executeQuery("SELECT * FROM cliente ORDER BY nombre_cliente");
			
			//Recorremos toda la tabla hasta que ya no hay mas datos
			while (resultSetMostrarTodos.next()) {
				
				//De todas las columnas seleccionamos la columna del nombre
				 String nombre = resultSetMostrarTodos.getString("nombre_cliente");
				 
				 //Muestra en pantalla el nombre del cliente
				 System.out.println(nombre);
				}
			
		}
		
		
		//Cerramos la conexion
		connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
