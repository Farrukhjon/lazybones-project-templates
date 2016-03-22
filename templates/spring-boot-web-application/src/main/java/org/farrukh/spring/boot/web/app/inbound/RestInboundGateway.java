package org.farrukh.spring.boot.web.app.inbound;

import org.farrukh.spring.boot.web.app.core.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestInboundGateway {

    private final CoreService service;

    @Autowired
    public RestInboundGateway(final CoreService service) {
        this.service = service;
    }


}
