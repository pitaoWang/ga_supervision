package com.atosenet.ga.company.db.repos.ga.company;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.GaMatianBaseRefere;

@RepositoryRestResource(collectionResourceRel = "GaMatianBaseRefereRepos", path = "GaMatianBaseRefereRepos")
public interface GaMatianBaseRefereRepository extends PagingAndSortingRepository<GaMatianBaseRefere, Integer> {

}
