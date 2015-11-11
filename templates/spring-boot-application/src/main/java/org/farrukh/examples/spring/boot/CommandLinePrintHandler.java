package org.farrukh.examples.spring.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Just command line arguments handler.
 */
@Component
public class CommandLinePrintHandler implements CommandLineRunner {

    @Override
    public void run(final String... args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }

}
