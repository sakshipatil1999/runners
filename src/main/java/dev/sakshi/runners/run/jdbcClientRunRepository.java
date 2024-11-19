package dev.sakshi.runners.run;

// import java.time.LocalDateTime;
// import java.time.temporal.ChronoUnit;
// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

//import jakarta.annotation.PostConstruct;

@Repository
public class jdbcClientRunRepository {

    private final JdbcClient jdbcClient;

    public jdbcClientRunRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    //private List<Run> runs = new ArrayList<>();

    public List<Run> findAll(){
        return jdbcClient.sql("select * from run")
        .query(Run.class)
        .list();
     }

    public Optional<Run> findById(Integer id){
        return jdbcClient.sql("select * from rum where id = :id")
        .param("id" , id)
        .query(Run.class)
        .optional();
    }

    public void create(Run run){
       var updated = jdbcClient.sql("insert into run values (?,?,?,?,?,?)")
       .params(List.of(run.id(),run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString()))
       .update();

       Assert.state(updated == 1, "Failed to create run" + run.title());
    }

    public void update(Run run,Integer id){
        var updated = jdbcClient.sql("update run set title=?,started_on=?,completed_on=?,miles=?,location=? where id = ?")
        .params(List.of(run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString(),run.id()))
        .update();
 
        Assert.state(updated == 1, "Failed to update run" + id);
    }

    public void delete(Integer id){
        var updated = jdbcClient.sql("delete from run where id = :id")
        .params("id",id)
        .update();
 
        Assert.state(updated == 1, "Failed to delete run" + id);

    }

    public int count(){
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }

    public void saveAll(List<Run> runs){
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(String location){
        return jdbcClient.sql("select * from rum where location = :location")
        .param("location" ,location)
        .query(Run.class)
        .list();
    }



    // @PostConstruct
    // private void init(){
    //     runs.add(new Run(1, 
    //     " Monday Morning run", 
    //     LocalDateTime.now(),
    //     LocalDateTime.now().plus(30,ChronoUnit.MINUTES),
    //     3, Location.INDOOR));

    //     runs.add(new Run(2,
    //     "Wednesday Evening Run",
    //     LocalDateTime.now(),
    //     LocalDateTime.now().plus(60,ChronoUnit.MINUTES),
    //     6, Location.INDOOR));
    // }
}
