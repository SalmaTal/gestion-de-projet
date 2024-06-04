package demo.controller;

import demo.model.Worker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Stream;

@RestController
@RequestMapping("/workers")
public class RegisteryController {
    @Autowired
    private WorkerRepository workersRepo;
    private ArrayList<Worker> workersAvailable = new ArrayList<Worker>();

    @PostMapping()
    public ResponseEntity<Worker> put(@RequestBody Worker user) {
        workersRepo.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @Scheduled(fixedDelay = 120000)
    public void sendListToLoadBalancer() {

        workersAvailable.clear();
    }
}
