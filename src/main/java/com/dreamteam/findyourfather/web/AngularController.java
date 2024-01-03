package com.dreamteam.findyourfather.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Le contrôleur {@code AngularController} gère les requêtes liées à l'application Angular
 * dans le cadre de l'application "Find Your Father".
 * 
 * <p>Ce contrôleur utilise l'annotation {@code @Controller} pour indiquer qu'il est un composant
 * de contrôleur Spring. Il expose une méthode {@code getAngularApp()} qui renvoie la page
 * d'index de l'application Angular.</p>
 *
 * <pre>{@code
 * @Controller
 * public class AngularController {
 *     // ...
 * }
 * }</pre>
 *
 * <p>Assurez-vous que les annotations {@code @Controller} et {@code @GetMapping} sont correctement
 * configurées pour une utilisation avec Spring Framework.</p>
 * 
 * @author MARTIN MACE DE GASTINES
 * @author LOUIS-ALEXANDRE LAGUET
 * @author ALEXIS TOURRENC--LECERF
 * @version 1.0
 * @since 2024-01-03
 * @see Controller
 * @see GetMapping
 */
@Controller
public class AngularController {

    /**
     * Renvoie la page d'index de l'application Angular.
     *
     * @return Le nom de la page d'index (index.html).
     */
    @GetMapping("/angular")
    public String getAngularApp() {
        return "index.html";
    }
}

