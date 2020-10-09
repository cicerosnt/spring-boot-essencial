package br.cicerosnt.project3.repository;

import br.cicerosnt.project3.model.Administrator;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdministratorRepository extends PagingAndSortingRepository<Administrator, Long> {
    Administrator findByuserName(String username);
}
