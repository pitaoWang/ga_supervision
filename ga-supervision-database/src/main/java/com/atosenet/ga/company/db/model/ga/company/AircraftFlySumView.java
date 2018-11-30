package com.atosenet.ga.company.db.model.ga.company;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**

 */
@Entity
@Data
@Table(name="v_flight_time_as_aircraft")
public class AircraftFlySumView implements Serializable{

   private static final long serialVersionUID = 1L;
	
   /**id*/
   @Id
   @Column(name="id")
   private long id;
   
   /** 航空器ID */
   @Column(name="aircraft_id")
   private long aircraftId;
   
   /** 归属企业ID */
   @ManyToOne
   @JoinColumn(name="company_id")
   private GaCompany companyId;
   
   /** 国籍登记证编号 */
   @Column(name="registernumber")
   private String registernumber;
   
   /** 航空器型号 */
   @Column(name="aircraft_model")
   private String aircraftModel;
   
   /** 类别 */
   @Column(name="aircraft_class")
   private String aircraftClass;
   
   /** 飞行日期*/
   @Column(name = "fly_date")
   private java.sql.Date flyDate;
   
   /**飞行总时长*/
   @Column(name = "sum_time")
   private Long sumTime;

}