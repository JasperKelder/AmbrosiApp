package nl.miwgroningen.cohort3.fortytwo.recipes.config;

import nl.miwgroningen.cohort3.fortytwo.recipes.RecipesApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

/*
* This class will take JSON objects and convert it to datasources which can be interpreted by the SQL database.
* */

@Configuration
public class JpaPopulator {

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator() {

        Logger logger = LoggerFactory.getLogger(RecipesApplication.class);

        // Jackson databind from the maven repo
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();

        // The factory variable will only take a resource list, we have to initialize it with the amount of dataSources.
        Resource[] dataSources = new Resource[2];
        dataSources[0] = new ClassPathResource("seedfiles/category-data.json");
        dataSources[1] = new ClassPathResource("seedfiles/cuisine-data.json");

        factory.setResources(dataSources);

        logger.info("Seedfiles loaded");

        return factory;
    }


}
