package com.ai.bdex.dataexchange.aipcenter.dubbo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ServiceDTO implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_info.TYPE
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String type;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_info.SERVICE_NAME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String serviceName;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_info.SERVICE_DESC
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String serviceDesc;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_info.PROVIDER_ID
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String providerId;
   
   private String providerName;
   
   private String providerStatus;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_info.STATUS
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String status;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_info.CREATE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private Date createTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_info.CREATE_STAFF
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String createStaff;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_info.UPDATE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private Date updateTime;

   /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column t_aip_service_info.UPDATE_STAFF
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   private String updateStaff;

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_info.TYPE
    *
    * @return the value of t_aip_service_info.TYPE
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getType() {
       return type;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_info.TYPE
    *
    * @param type the value for t_aip_service_info.TYPE
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setType(String type) {
       this.type = type == null ? null : type.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_info.SERVICE_NAME
    *
    * @return the value of t_aip_service_info.SERVICE_NAME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getServiceName() {
       return serviceName;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_info.SERVICE_NAME
    *
    * @param serviceName the value for t_aip_service_info.SERVICE_NAME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setServiceName(String serviceName) {
       this.serviceName = serviceName == null ? null : serviceName.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_info.SERVICE_DESC
    *
    * @return the value of t_aip_service_info.SERVICE_DESC
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getServiceDesc() {
       return serviceDesc;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_info.SERVICE_DESC
    *
    * @param serviceDesc the value for t_aip_service_info.SERVICE_DESC
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setServiceDesc(String serviceDesc) {
       this.serviceDesc = serviceDesc == null ? null : serviceDesc.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_info.PROVIDER_ID
    *
    * @return the value of t_aip_service_info.PROVIDER_ID
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getProviderId() {
       return providerId;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_info.PROVIDER_ID
    *
    * @param providerId the value for t_aip_service_info.PROVIDER_ID
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setProviderId(String providerId) {
       this.providerId = providerId == null ? null : providerId.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_info.STATUS
    *
    * @return the value of t_aip_service_info.STATUS
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getStatus() {
       return status;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_info.STATUS
    *
    * @param status the value for t_aip_service_info.STATUS
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setStatus(String status) {
       this.status = status == null ? null : status.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_info.CREATE_TIME
    *
    * @return the value of t_aip_service_info.CREATE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public Date getCreateTime() {
       return createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_info.CREATE_TIME
    *
    * @param createTime the value for t_aip_service_info.CREATE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setCreateTime(Date createTime) {
       this.createTime = createTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_info.CREATE_STAFF
    *
    * @return the value of t_aip_service_info.CREATE_STAFF
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getCreateStaff() {
       return createStaff;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_info.CREATE_STAFF
    *
    * @param createStaff the value for t_aip_service_info.CREATE_STAFF
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setCreateStaff(String createStaff) {
       this.createStaff = createStaff == null ? null : createStaff.trim();
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_info.UPDATE_TIME
    *
    * @return the value of t_aip_service_info.UPDATE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public Date getUpdateTime() {
       return updateTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_info.UPDATE_TIME
    *
    * @param updateTime the value for t_aip_service_info.UPDATE_TIME
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setUpdateTime(Date updateTime) {
       this.updateTime = updateTime;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column t_aip_service_info.UPDATE_STAFF
    *
    * @return the value of t_aip_service_info.UPDATE_STAFF
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public String getUpdateStaff() {
       return updateStaff;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column t_aip_service_info.UPDATE_STAFF
    *
    * @param updateStaff the value for t_aip_service_info.UPDATE_STAFF
    *
    * @mbg.generated Mon Apr 24 10:27:08 CST 2017
    */
   public void setUpdateStaff(String updateStaff) {
       this.updateStaff = updateStaff == null ? null : updateStaff.trim();
   }

	public String getProviderName() {
		return providerName;
	}
	
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderStatus() {
		return providerStatus;
	}

	public void setProviderStatus(String providerStatus) {
		this.providerStatus = providerStatus;
	}
   private List<AipServiceInParaDTO> serviceInParas;
   private List<AipServiceOutParaDTO> serviceOutParas;
   private List<AipServiceErrorInfoDTO> serviceErrores;
   private List<AipServiceCodeInfoDTO> serviceCodeInfoes;

public List<AipServiceInParaDTO> getServiceInParas() {
	return serviceInParas;
}

public void setServiceInParas(List<AipServiceInParaDTO> serviceInParas) {
	this.serviceInParas = serviceInParas;
}

public List<AipServiceOutParaDTO> getServiceOutParas() {
	return serviceOutParas;
}

public void setServiceOutParas(List<AipServiceOutParaDTO> serviceOutParas) {
	this.serviceOutParas = serviceOutParas;
}

public List<AipServiceErrorInfoDTO> getServiceErrores() {
	return serviceErrores;
}

public void setServiceErrores(List<AipServiceErrorInfoDTO> serviceErrores) {
	this.serviceErrores = serviceErrores;
}

public List<AipServiceCodeInfoDTO> getServiceCodeInfoes() {
	return serviceCodeInfoes;
}

public void setServiceCodeInfoes(List<AipServiceCodeInfoDTO> serviceCodeInfoes) {
	this.serviceCodeInfoes = serviceCodeInfoes;
}
   
   
}
