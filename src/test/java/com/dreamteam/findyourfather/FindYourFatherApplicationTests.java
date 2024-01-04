package com.dreamteam.findyourfather;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Les tests de l'application Find Your Father sont gérés par la classe {@code FindYourFatherApplicationTests}.
 *
 * <p>Cette classe utilise l'annotation {@code @SpringBootTest} pour indiquer qu'il s'agit d'un test Spring Boot.
 * Le test de chargement de contexte est effectué dans la méthode {@code contextLoads()}.</p>
 *
 * <pre>{@code
 * @SpringBootTest
 * class FindYourFatherApplicationTests {
 *     @Test
 *     void contextLoads() {
 *         // ...
 *     }
 * }
 * }</pre>
 *
 * <p>Assurez-vous que les annotations et les dépendances nécessaires sont correctement
 * configurées pour une utilisation avec Spring Boot.</p>
 * 
 * @version 1.0
 * @since 2024-01-03
 * @see Test
 * @see SpringBootTest
 */
@SpringBootTest
class FindYourFatherApplicationTests {

    /**
     * Vérifie que le contexte de l'application peut être correctement chargé.
     */
    @Test
    void contextLoads() {
        // ...
    }
}
