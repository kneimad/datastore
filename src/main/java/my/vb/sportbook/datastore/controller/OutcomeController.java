package my.vb.sportbook.datastore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.vb.sportbook.datastore.dto.OutcomeDTO;
import my.vb.sportbook.datastore.model.Outcome;
import my.vb.sportbook.datastore.service.OutcomeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/outcome")
@RequiredArgsConstructor
//public class OutcomeController implements CRUDControllerInterface<OutcomeDTO, Outcome, CRUDInterface<Outcome, MongoRepository<Outcome, Long>, OutcomeDTO>>{
public class OutcomeController implements CRUDControllerInterface<OutcomeDTO, Outcome, OutcomeService>{

    private final OutcomeService outcomeService;

    @Override
    public OutcomeService getService() {
        log.info("getService: OutcomeService");
        return outcomeService;
    }
}
