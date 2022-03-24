package fr.jfc.ptut.config;


import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
/**
 * Configuration de Spring Data Rest
 * Expose les id des entités 
 */
@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // Expose les id des entités
        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new));
        // Autorise les requêtes CORS
        cors.addMapping("/**") // Autorisé sur toutes les URL
            .allowedOrigins("*") // Autorisé sur toutes les origines
            .allowedMethods("*") // Autorisé pour toutes les méthodes HTTP
            .allowCredentials(false); // Interdit la transmission des infos d'authentification
    }
    
}
