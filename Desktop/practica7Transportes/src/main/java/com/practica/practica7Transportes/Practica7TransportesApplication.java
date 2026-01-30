package com.practica.practica7Transportes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class Practica7TransportesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Practica7TransportesApplication.class, args);
	}
        // Listado de endpoint hecho por ChatGpt -> Lo ordena por ruta de foram alfabeticamente
        @Bean
        public CommandLineRunner mostrarEndpoints(ApplicationContext context) {
            return args -> {
            // 1. Definimos anchos fijos exactos
            int wMetodo = 10;
            int wRuta = 55;
            int wHandler = 50;

            // 2. Construimos la línea horizontal y el formato de fila (mismo para cabecera
            // y datos)
            String lineaHorizontal = "+" + "-".repeat(wMetodo + 2) + "+" + "-".repeat(wRuta + 2) + "+"
            + "-".repeat(wHandler + 2) + "+";
            String formatoFila = "| %-" + wMetodo + "s | %-" + wRuta + "s | %-" + wHandler + "s |%n";

            System.out.println("\n" + lineaHorizontal);
            System.out.printf(formatoFila, "MÉTODO", "RUTA", "HANDLER");
            System.out.println(lineaHorizontal);

            RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);

            // 3. Obtenemos los métodos, los filtramos y los ORDENAMOS por la ruta
            mapping.getHandlerMethods().entrySet().stream()
            .filter(entry -> !entry.getKey().getPatternValues().contains("/error"))
            .sorted((e1, e2) -> {
            String ruta1 = e1.getKey().getPatternValues().toString();
            String ruta2 = e2.getKey().getPatternValues().toString();
            return ruta1.compareTo(ruta2); // Orden alfabético por ruta
            })
            .forEach(entry -> {
            var info = entry.getKey();
            var method = entry.getValue();

            String httpMethod = info.getMethodsCondition().getMethods().isEmpty()
            ? "[ALL]"
            : info.getMethodsCondition().getMethods().toString();

            String path = info.getPatternValues().toString();
            String handler = method.getBeanType().getSimpleName() + "." + method.getMethod().getName()
            + "()";

            // Recorte de seguridad para que no rompa el borde derecho si el nombre es muy
            // largo
            if (handler.length() > wHandler) {
            handler = handler.substring(0, wHandler - 3) + "...";
            }

            System.out.printf(formatoFila, httpMethod, path, handler);
            });

            System.out.println(lineaHorizontal + "\n");
            };
        }
}
