package com.bibliotech;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.exception.SocioDuplicadoException;
import com.bibliotech.model.Docente;
import com.bibliotech.model.Ebook;
import com.bibliotech.model.Estudiante;
import com.bibliotech.model.LibroFisico;
import com.bibliotech.model.Prestamo;
import com.bibliotech.model.Recurso;
import com.bibliotech.model.Socio;
import com.bibliotech.repository.PrestamoRepository;
import com.bibliotech.repository.RecursoRepository;
import com.bibliotech.repository.SocioRepository;
import com.bibliotech.service.PrestamoService;
import com.bibliotech.service.RecursoService;
import com.bibliotech.service.SocioService;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SocioRepository socioRepository = new SocioRepository();
        RecursoRepository recursoRepository = new RecursoRepository();
        PrestamoRepository prestamoRepository = new PrestamoRepository();

        SocioService socioService = new SocioService(socioRepository);
        RecursoService recursoService = new RecursoService(recursoRepository);
        PrestamoService prestamoService = new PrestamoService(recursoRepository, socioRepository, prestamoRepository);

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("Welcome to BiblioTech!\n");

        while (continuar) {
            System.out.println("1) Alta socio");
            System.out.println("2) Alta recurso");
            System.out.println("3) Registrar prestamo");
            System.out.println("4) Registrar devolucion");
            System.out.println("5) Listar socios");
            System.out.println("6) Listar recursos");
            System.out.println("7) Listar prestamos");
            System.out.println("0) Salir");
            System.out.print("Opcion: ");

            String opcion = scanner.nextLine().trim();
            try {
                switch (opcion) {
                    case "1" -> altaSocio(scanner, socioService);
                    case "2" -> altaRecurso(scanner, recursoService);
                    case "3" -> registrarPrestamo(scanner, prestamoService);
                    case "4" -> registrarDevolucion(scanner, prestamoService);
                    case "5" -> listarSocios(socioService);
                    case "6" -> listarRecursos(recursoService);
                    case "7" -> listarPrestamos(prestamoRepository.buscarTodos());
                    case "0" -> continuar = false;
                    default -> System.out.println("Opcion invalida.\n");
                }
            } catch (SocioDuplicadoException e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            } catch (BibliotecaException e) {
                System.out.println("Error de negocio: " + e.getMessage() + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println("Datos invalidos: " + e.getMessage() + "\n");
            }
        }

        scanner.close();
        System.out.println("Hasta luego.");
    }

    private static void altaSocio(Scanner scanner, SocioService socioService) throws SocioDuplicadoException {
        System.out.println("Tipo de socio: 1) Estudiante 2) Docente");
        System.out.print("Opcion: ");
        String tipo = scanner.nextLine().trim();

        System.out.print("DNI: ");
        String dni = scanner.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        Socio socio;
        if ("2".equals(tipo)) {
            socio = new Docente(dni, nombre, email);
        } else {
            socio = new Estudiante(dni, nombre, email);
        }

        socioService.registrar(socio);
        System.out.println("Socio registrado.\n");
    }

    private static void altaRecurso(Scanner scanner, RecursoService recursoService) {
        System.out.println("Tipo de recurso: 1) Libro fisico 2) Ebook");
        System.out.print("Opcion: ");
        String tipo = scanner.nextLine().trim();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine().trim();
        System.out.print("Autor: ");
        String autor = scanner.nextLine().trim();
        System.out.print("Anio: ");
        int anio = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine().trim();

        Recurso recurso;
        if ("2".equals(tipo)) {
            System.out.print("Formato: ");
            String formato = scanner.nextLine().trim();
            recurso = new Ebook(isbn, titulo, autor, anio, categoria, formato);
        } else {
            System.out.print("Numero de estante: ");
            int estante = Integer.parseInt(scanner.nextLine().trim());
            recurso = new LibroFisico(isbn, titulo, autor, anio, categoria, estante);
        }

        recursoService.registrar(recurso);
        System.out.println("Recurso registrado.\n");
    }

    private static void registrarPrestamo(Scanner scanner, PrestamoService prestamoService) throws BibliotecaException {
        System.out.print("ISBN del recurso: ");
        String isbn = scanner.nextLine().trim();
        System.out.print("DNI del socio: ");
        String dni = scanner.nextLine().trim();

        Prestamo prestamo = prestamoService.realizarPrestamo(isbn, dni);
        System.out.println("Prestamo registrado con ID " + prestamo.id() + ".");
        System.out.println("Fecha fin: " + prestamo.fechaFin() + "\n");
    }

    private static void registrarDevolucion(Scanner scanner, PrestamoService prestamoService) throws BibliotecaException {
        System.out.print("ID del prestamo: ");
        long prestamoId = Long.parseLong(scanner.nextLine().trim());

        LocalDate fechaDevolucion = LocalDate.now();
        long diasRetraso = prestamoService.registrarDevolucion(prestamoId, fechaDevolucion);
        if (diasRetraso > 0) {
            System.out.println("Devolucion registrada. Dias de retraso: " + diasRetraso + "\n");
        } else {
            System.out.println("Devolucion registrada sin retraso.\n");
        }
    }

    private static void listarSocios(SocioService socioService) {
        List<Socio> socios = socioService.buscarTodos();
        if (socios.isEmpty()) {
            System.out.println("No hay socios registrados.\n");
            return;
        }
        for (Socio socio : socios) {
            System.out.println(socio.dni() + " - " + socio.nombre() + " - " + socio.email());
        }
        System.out.println();
    }

    private static void listarRecursos(RecursoService recursoService) {
        List<Recurso> recursos = recursoService.buscarTodos();
        if (recursos.isEmpty()) {
            System.out.println("No hay recursos registrados.\n");
            return;
        }
        for (Recurso recurso : recursos) {
            System.out.println(recurso.isbn() + " - " + recurso.titulo() + " - " + recurso.autor());
        }
        System.out.println();
    }

    private static void listarPrestamos(List<Prestamo> prestamos) {
        if (prestamos.isEmpty()) {
            System.out.println("No hay prestamos registrados.\n");
            return;
        }
        for (Prestamo prestamo : prestamos) {
            String estado = prestamo.fechaDevolucion().isEmpty() ? "ACTIVO" : "DEVUELTO";
            System.out.println(
                    prestamo.id() + " - " + prestamo.recurso().titulo() + " - " + prestamo.socio().nombre()
                            + " - " + estado
            );
        }
        System.out.println();
    }
}