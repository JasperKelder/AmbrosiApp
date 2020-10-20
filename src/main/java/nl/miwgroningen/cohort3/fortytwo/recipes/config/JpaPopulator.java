package nl.miwgroningen.cohort3.fortytwo.recipes.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.miwgroningen.cohort3.fortytwo.recipes.RecipesApplication;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import java.io.File;
import java.io.FileWriter;
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
        Category category = new Category();
        category.setCategoryName("Asian");
        objectMapper.writeValue(new File("src/main/resources/seedfiles/category.json"), category);
        Resource[] dataSources = new Resource[1];
        dataSources[0] = new ClassPathResource("src/main/resources/seedfiles/category.json");

        factory.setResources(dataSources);

        logger.info("Seedfiles loaded");

        return factory;
    }
    private FileWriter getJsonStringInFile(Category category) throws IOException {
        FileWriter file;
        // Before converting to GSON check value of id
        Gson gson = null;
        if (category.getCategoryId() == 0) {
            gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
        } else {
            gson = new Gson();
        }
        file = new FileWriter("employees.json");

            file.write(gson.toJson(category));
            file.flush();

            return file;
    }

}
