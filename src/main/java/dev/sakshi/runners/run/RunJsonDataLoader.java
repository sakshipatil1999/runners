package dev.sakshi.runners.run;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.sakshi.runners.RunnersApplication;

@Component
public class RunJsonDataLoader implements CommandLineRunner{

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RunnersApplication.class);
    private final jdbcClientRunRepository runRepository;
    private final ObjectMapper objectMapper;
   
    public RunJsonDataLoader(jdbcClientRunRepository runRepository,ObjectMapper objectMapper){
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception{
        if(runRepository.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")){
                Runs allRuns = objectMapper.readValue(inputStream,Runs.class);
                log.info("Reading {} runs from JSON data and saving in-memory collection ", allRuns.runs().size());
                runRepository.saveAll(allRuns.runs());
            }catch (IOException e){
                throw new RuntimeException("Failed to read JSON data",e);
            }
        }else{
            log.info("Not loading Runs from JSON data because the collection cobtains data");
        }
    }

}
