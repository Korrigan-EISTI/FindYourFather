package com.dreamteam.findyourfather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * La classe {@code FindYourFatherApplication} est la classe principale de l'application
 * "Find Your Father". Elle utilise le framework Spring Boot pour la configuration et le lancement
 * de l'application.
 * 
 * <p>Cette classe étend {@code SpringBootServletInitializer} pour permettre le déploiement
 * de l'application en tant que servlet dans un conteneur de servlets comme Tomcat.</p>
 *
 * <p>Le point d'entrée de l'application est la méthode {@code main}, qui initialise et lance
 * l'application Spring Boot.</p>
 *
 * <p>Pour exécuter cette application, utilisez la méthode statique {@code main} en passant
 * les arguments de la ligne de commande.</p>
 *
 * <pre>{@code
 * public static void main(String[] args) {
 *     SpringApplication.run(FindYourFatherApplication.class, args);
 * }
 * }</pre>
 *
 * <p>Assurez-vous d'avoir correctement configuré votre projet avec les dépendances Spring Boot
 * nécessaires pour assurer le bon fonctionnement de l'application.</p>
 * 
 * @author MARTIN MACE DE GASTINES, LOUIS-ALEXANDRE LAGUET, ALEXIS TOURRENC--LECERF
 * @version 1.0
 * @since 2024-01-03
 * @see SpringApplication
 * @see SpringBootServletInitializer
 */

@SpringBootApplication
public class FindYourFatherApplication extends SpringBootServletInitializer {

    /**
     * Point d'entrée de l'application. Initialise et lance l'application Spring Boot.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(FindYourFatherApplication.class);
        app.addListeners(new ApplicationStartup());
        app.run(args);
    }

    /**
     * {@code ApplicationListener} pour afficher un message au démarrage de l'application.
     */
    @Component
    public static class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println("--- Application is running at http://localhost:8080 ---");
        }
    }
}