package com.slxt.rs.model;

import java.sql.Timestamp;


/**
 * HjdjAcdLogId entity. @author MyEclipse Persistence Tools
 */

public class HjdjAcdLogId  implements java.io.Serializable {


    // Fields    

     private Long logId;
     private Integer logType;
     private Timestamp logTime;
     private Integer acdNo;
     private Integer acdIndex;


    // Constructors

    /** default constructor */
    public HjdjAcdLogId() {
    }

    
    /** full constructor */
    public HjdjAcdLogId(Long logId, Integer logType, Timestamp logTime, Integer acdNo, Integer acdIndex) {
        this.logId = logId;
        this.logType = logType;
        this.logTime = logTime;
        this.acdNo = acdNo;
        this.acdIndex = acdIndex;
    }

   
    // Property accessors

    public Long getLogId() {
        return this.logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Integer getLogType() {
        return this.logType;
    }
    
    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public Timestamp getLogTime() {
        return this.logTime;
    }
    
    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    public Integer getAcdNo() {
        return this.acdNo;
    }
    
    public void setAcdNo(Integer acdNo) {
        this.acdNo = acdNo;
    }

    public Integer getAcdIndex() {
        return this.acdIndex;
    }
    
    public void setAcdIndex(Integer acdIndex) {
        this.acdIndex = acdIndex;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof HjdjAcdLogId) ) return false;
		 HjdjAcdLogId castOther = ( HjdjAcdLogId ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getLogType()==castOther.getLogType()) || ( this.getLogType()!=null && castOther.getLogType()!=null && this.getLogType().equals(castOther.getLogType()) ) )
 && ( (this.getLogTime()==castOther.getLogTime()) || ( this.getLogTime()!=null && castOther.getLogTime()!=null && this.getLogTime().equals(castOther.getLogTime()) ) )
 && ( (this.getAcdNo()==castOther.getAcdNo()) || ( this.getAcdNo()!=null && castOther.getAcdNo()!=null && this.getAcdNo().equals(castOther.getAcdNo()) ) )
 && ( (this.getAcdIndex()==castOther.getAcdIndex()) || ( this.getAcdIndex()!=null && castOther.getAcdIndex()!=null && this.getAcdIndex().equals(castOther.getAcdIndex()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getLogType() == null ? 0 : this.getLogType().hashCode() );
         result = 37 * result + ( getLogTime() == null ? 0 : this.getLogTime().hashCode() );
         result = 37 * result + ( getAcdNo() == null ? 0 : this.getAcdNo().hashCode() );
         result = 37 * result + ( getAcdIndex() == null ? 0 : this.getAcdIndex().hashCode() );
         return result;
   }   





}