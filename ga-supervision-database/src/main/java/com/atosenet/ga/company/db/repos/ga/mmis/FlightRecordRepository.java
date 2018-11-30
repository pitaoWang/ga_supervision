package com.atosenet.ga.company.db.repos.ga.mmis;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.mmis.FlightRecord;

@RepositoryRestResource(collectionResourceRel = "flightRecord", path = "flightRecord")
public interface FlightRecordRepository extends PagingAndSortingRepository<FlightRecord, Serializable> {

	@Query(value="select t.* from mmis_mc_flight_record t where if(?1 !='', t.no = ?1, 1=1) and if(?2 !='', t.aircraft_model = ?2, 1=1) and if(?3 !='', t.aircraft_reg_no = ?3, 1=1) and if(?4 !='', t.releaser = ?4, 1=1) and if(?5 !='', date_format(t.release_time, '%Y-%m-%d') = ?5, 1=1) and t.is_delete = 0 \n-- #pageable\n",
			countQuery="select count(*) from mmis_mc_flight_record t where if(?1 !='', t.no = ?1, 1=1) and if(?2 !='', t.aircraft_model = ?2, 1=1) and if(?3 !='', t.aircraft_reg_no = ?3, 1=1) and if(?4 !='', t.releaser = ?4, 1=1) and if(?5 !='', date_format(t.release_time,'%Y-%m-%d') = ?5, 1=1) and t.is_delete = 0",
			nativeQuery=true)
	Page<FlightRecord> findFlightRecordListByPage(String no, String aircraftModel, String aircraftRegNo, String releaser, String releaseTime, Pageable pageable);
}
