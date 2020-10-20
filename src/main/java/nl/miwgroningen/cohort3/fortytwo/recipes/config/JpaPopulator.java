package nl.miwgroningen.cohort3.fortytwo.recipes.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.miwgroningen.cohort3.fortytwo.recipes.RecipesApplication;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Category;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
* This class will take JSON objects and convert it to datasources which can be interpreted by the SQL database.
* */

@Configuration
public class JpaPopulator {

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator() throws IOException {

        Logger logger = LoggerFactory.getLogger(RecipesApplication.class);

        // Jackson databind from the maven repo
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();

        // The factory variable will only take a resource list, we have to initialize it with the amount of dataSources.
        ObjectMapper objectMapper = new ObjectMapper();
        Resource[] dataSources = new Resource[1];
        dataSources[0] = objectMapper.readValue(new File(returnJsonStringFromModel()), Category.class);

        factory.setResources(dataSources);

        logger.info("Seedfiles loaded");

        return factory;
    }

    public String returnJsonStringFromModel() throws JsonProcessingException {
        Category category = new Category();
        category.setCategoryId(1);
        category.setCategoryName("Asian");

        // Create objectmapper object
        ObjectMapper mapper = new ObjectMapper();

        // Convert object model to JSON string

        String jsonString = mapper.writeValueAsString(category);

        return jsonString;
    }

}
