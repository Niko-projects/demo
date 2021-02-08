package com.example.demo.controller;

import com.example.demo.event.StopIpcReadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;

@RestController
public class ManageController {
    private static final Logger log = LoggerFactory.getLogger(ManageController.class);
    private final ApplicationContext ctx;

    public ManageController(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> shutdownApplication() {
        log.info("Hello request arrived...");
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/stop")
    private void stopApplicationEvent() {
        log.info("Publishing stop event...");
        ctx.publishEvent(new StopIpcReadEvent(this));
        log.info("Publishing stop finished");
        SpringApplication.exit(ctx, () -> 0);
        //System.exit(0);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onAppStart() {
        log.info("onAppStart()");
        //new Thread(() -> threadService.start()).start();
        log.info("onAppStart() done");
    }

    @PreDestroy
    public void onDestroy() {
        log.info("onDestroy()");
    }
}
