package com.boot.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    // private UserRepository userRepository;

//    @Autowired
//    public DataLoader(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public void run(ApplicationArguments args) {
    	System.out.println("==> init dataloader");
        //userRepository.save(new User("lala", "lala", "lala"));
    }
}