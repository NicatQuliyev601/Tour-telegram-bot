package az.nicat.tourbotapi.controller;

import az.nicat.tourbotapi.service.AgentServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/agents")
@CrossOrigin
public class AgentController {
    private final AgentServiceImpl agentServiceImpl;

    public AgentController(AgentServiceImpl agentServiceImpl) {
        this.agentServiceImpl = agentServiceImpl;
    }




}
