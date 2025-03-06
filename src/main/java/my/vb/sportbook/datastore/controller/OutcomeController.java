package my.vb.sportbook.datastore.controller;

import lombok.RequiredArgsConstructor;
import my.vb.sportbook.datastore.dto.OutcomeDTO;
import my.vb.sportbook.datastore.model.Outcome;
import my.vb.sportbook.datastore.service.OutcomeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/outcome")
@RequiredArgsConstructor
//public class OutcomeController implements CRUDControllerInterface<OutcomeDTO, Outcome, CRUDInterface<Outcome, MongoRepository<Outcome, Long>, OutcomeDTO>>{
public class OutcomeController implements CRUDControllerInterface<OutcomeDTO, Outcome, OutcomeService>{

    private final OutcomeService outcomeService;

    @Override
    public OutcomeService getService() {
        return outcomeService;
    }
}
