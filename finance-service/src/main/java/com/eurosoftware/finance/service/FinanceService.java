package com.eurosoftware.finance.service;

import com.eurosoftware.finance.DTOs.CustomerFinanceDto;
import com.eurosoftware.finance.DTOs.ProjectFinanceDto;
import com.eurosoftware.finance.entity.Finance;
import com.eurosoftware.finance.repository.FinanceRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinanceService {

    private final FinanceRepository financeRepository;

    private final RestTemplate restTemplate;

    public FinanceService(FinanceRepository financeRepository, RestTemplate restTemplate) {
        this.financeRepository = financeRepository;
        this.restTemplate = restTemplate;
    }

    public Finance save(Finance user) {
        return financeRepository.save(user);
    }

    public List<CustomerFinanceDto> getCustomerFinances(Long id) {
        var list = new ArrayList<CustomerFinanceDto>();

        ResponseEntity<List<Long>> responseEntity = restTemplate.exchange("http://localhost:9003/by-customer/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        if(responseEntity.getStatusCode().value() != 200 || !responseEntity.hasBody()) return list;

        List<Long> listOfCustomersProjectsIds = responseEntity.getBody();

        if(listOfCustomersProjectsIds == null) return list;

        var result = new ArrayList<Finance>();

        for(var ids : listOfCustomersProjectsIds) {
            var finance = financeRepository.findAllByProjectId(ids);
            result.addAll(finance);
        }

        for(var item : result) {
            list.add(new CustomerFinanceDto(item.getId(), item.getProjectId(), item.getAmount()));
        }

        return list;
    }

    public void deleteByProjects(List<Long> ids) {
        for(var id : ids)
            financeRepository.deleteAllByProjectId(id);
    }

    public List<ProjectFinanceDto> getFinanceByProject(Long id) {
        var list = new ArrayList<ProjectFinanceDto>();
        var finance = financeRepository.findAllByProjectId(id);

        for(var item : finance) {
            list.add(new ProjectFinanceDto(item.getId(), item.getAmount()));
        }

        return list;
    }

    public List<Double> findAllNames() {
        var list = new ArrayList<Double>();
        var finance = financeRepository.findAll();
        for(var item : finance) list.add(item.getAmount());
        return list;
    }

}
