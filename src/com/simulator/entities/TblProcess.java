/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulator.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Emerio
 */
@Entity
@Table(name = "tbl_process")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProcess.findAll", query = "SELECT t FROM TblProcess t"),
    @NamedQuery(name = "TblProcess.findById", query = "SELECT t FROM TblProcess t WHERE t.id = :id"),
    @NamedQuery(name = "TblProcess.findByIdThread", query = "SELECT t FROM TblProcess t WHERE t.idThread = :idThread"),
    @NamedQuery(name = "TblProcess.findByStartTime", query = "SELECT t FROM TblProcess t WHERE t.startTime = :startTime"),
    @NamedQuery(name = "TblProcess.findByEndTime", query = "SELECT t FROM TblProcess t WHERE t.endTime = :endTime"),
    @NamedQuery(name = "TblProcess.findByTotalData", query = "SELECT t FROM TblProcess t WHERE t.totalData = :totalData"),
    @NamedQuery(name = "TblProcess.findByElapsedTime", query = "SELECT t FROM TblProcess t WHERE t.elapsedTime = :elapsedTime"),
    @NamedQuery(name = "TblProcess.findByNoSimulasi", query = "SELECT t FROM TblProcess t WHERE t.noSimulasi = :noSimulasi")})
public class TblProcess implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ID_THREAD")
    private String idThread;
    @Basic(optional = false)
    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Basic(optional = false)
    @Column(name = "TOTAL_DATA")
    private int totalData;
    @Basic(optional = false)
    @Column(name = "ELAPSED_TIME")
    private String elapsedTime;
    @Basic(optional = false)
    @Column(name = "NO_SIMULASI")
    private int noSimulasi;

    public TblProcess() {
    }

    public TblProcess(Integer id) {
        this.id = id;
    }

    public TblProcess(Integer id, String idThread, Date startTime, int totalData, String elapsedTime, int noSimulasi) {
        this.id = id;
        this.idThread = idThread;
        this.startTime = startTime;
        this.totalData = totalData;
        this.elapsedTime = elapsedTime;
        this.noSimulasi = noSimulasi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdThread() {
        return idThread;
    }

    public void setIdThread(String idThread) {
        this.idThread = idThread;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getNoSimulasi() {
        return noSimulasi;
    }

    public void setNoSimulasi(int noSimulasi) {
        this.noSimulasi = noSimulasi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProcess)) {
            return false;
        }
        TblProcess other = (TblProcess) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.simulator.entities.TblProcess[ id=" + id + " ]";
    }
    
}
