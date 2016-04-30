/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulator.launcher;

import com.simulator.entities.TblProcess;
import com.simulator.util.UtilDatabase;
import com.simulator.util.UtilService;
import com.virtual.services.ServiceValidation;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Emerio
 */
public class SimulatorThread extends Thread {

    String threadId;
    Integer noSimulasi;
    Integer totalData;

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public Integer getNoSimulasi() {
        return noSimulasi;
    }

    public void setNoSimulasi(Integer noSimulasi) {
        this.noSimulasi = noSimulasi;
    }

    public Integer getTotalData() {
        return totalData;
    }

    public void setTotalData(Integer totalData) {
        this.totalData = totalData;
    }

    public SimulatorThread(String threadId, Integer noSimulasi, Integer totalData) {
        this.threadId = threadId;
        this.noSimulasi = noSimulasi;
        this.totalData = totalData;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        UtilService us = new UtilService();
        us.openServiceVirtual();
        ServiceValidation sv = us.getService().getPort(ServiceValidation.class);

        for (int i = 0; i < totalData / 4; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(SimulatorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            sv.validation(threadId, noSimulasi);
        }

        long estimatedTime = System.nanoTime() - startTime;
        UtilDatabase localUtilDatabase = new UtilDatabase();
        localUtilDatabase.openConnection();
        EntityManager em = localUtilDatabase.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        TblProcess tblProcess = new TblProcess();
        tblProcess.setIdThread(threadId);
        tblProcess.setStartTime(new Date());
        tblProcess.setTotalData(totalData);
        tblProcess.setNoSimulasi(noSimulasi);
        tblProcess.setElapsedTime(String.valueOf(estimatedTime));        
        em.persist(tblProcess);
        em.flush();
        et.commit();
        localUtilDatabase.getEntityManager().close();
    }
}
