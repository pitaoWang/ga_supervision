package com.atosenet.ga.company.mgt.service.ga.Document;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.document.DocList;
import com.atosenet.ga.company.db.model.ga.document.DocListDto;
import com.atosenet.ga.company.db.model.ga.document.DocListNewDto;
import com.atosenet.ga.company.db.repos.ga.document.DocListRepository;
import com.atosenet.ga.company.db.repos.ga.document.DocListDtoRespository;
import com.atosenet.ga.company.db.repos.ga.document.DocListNewRespository;


@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class DocListService {
	@Autowired
	DocListRepository DocListRepository;
	@Autowired
	DocListDtoRespository DocListDtoRespository;
	@Autowired
	DocListNewRespository docNewRes;
	
	@Transactional(readOnly=false)
    public DocList save(DocList DocList){
		return DocListRepository.save(DocList);

    }
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void delete(long id){
		DocListRepository.delete(id);
    }
	
	/**
	 * 查询
	 */
	@Transactional(readOnly=true)
    public DocList findById(long id){	
		return  DocListRepository.findOne(id);
    }
	
	public Page<DocList> finddoclistcompany(String jobTitle,String companyId,Pageable pageable){
		return  DocListRepository.finddoclistcompany(jobTitle,companyId,pageable);
	}
	/*public Page<DocListDto> finddoclist(String jobTitle,String companyId,Pageable pageable){
		return  DocListDtoRespository.finddoclist(jobTitle,companyId,pageable);
	}*/
	public Page<DocListDto> finddoclist(String companyId,Pageable pageable){
		return  DocListDtoRespository.finddoclist(companyId,pageable);
	}
	public Page<DocListNewDto> geilistbycreator(long employeeid,Pageable pageable){
		return  docNewRes.geilistbycreator(employeeid,pageable);
	}
	public Page<DocList> geilistbysuperviseId(long superviseId,Pageable pageable){
		return  DocListRepository.geilistbysuperviseId(superviseId,pageable);
	}
	public List<Object> findnamebyemployeeid(long employeeid){
		return  DocListRepository.findnamebyemployeeid(employeeid);
	}
	
	public Page<DocListNewDto> getListBySupervisionId(long superviseId,Pageable pageable){
		return docNewRes.getListBySupervisionId(superviseId, pageable);
	}
}
