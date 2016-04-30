/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulator.threads;

import com.simulator.entities.TblTransaksiBank;
import com.simulator.entities.TblUser;
import com.simulator.entities.TblValidasi;
import com.simulator.util.UtilDatabase;
import com.simulator.util.UtilGeneral;
import java.util.Date;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Emerio
 */
public class Thread2 extends Thread {

    private String threadId;
    private int totalData;
    private UtilDatabase localUtilDatabase;

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    @Override
    public void run() {
        localUtilDatabase = new UtilDatabase();
        
        for (int i = 0; i < totalData / 4; i++) {
            // get random user --> total user is 1000
            int userId = UtilGeneral.getInstance().getUserId();
            String noKartuKredit = getUserCreditCard(userId);
            String idValidasi = insertValidasi();
            insertTransaksiBank(noKartuKredit, idValidasi);
            //insertTransaksiKurir(idTransaksiBank);
        }
    }

    public String getUserCreditCard(int id) {
        localUtilDatabase.openConnection();
        EntityManager em = localUtilDatabase.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        TblUser tblUserResult = em.find(TblUser.class, id);
        et.commit();
        localUtilDatabase.getEntityManager().close();
        return tblUserResult.getNoKartuKredit();
    }

    public String insertValidasi() {
        String idValidasi = UUID.randomUUID().toString();

        TblValidasi tblValidasi = new TblValidasi();
        tblValidasi.setIdThread(threadId);
        tblValidasi.setIdValidasi(idValidasi);
        tblValidasi.setStatusTransaksi("SUCCESS");
        tblValidasi.setCreatedDate(new Date());

        localUtilDatabase.openConnection();
        EntityManager em = localUtilDatabase.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(tblValidasi);
        et.commit();
        localUtilDatabase.getEntityManager().close();
        return idValidasi;
    }

    public String insertTransaksiBank(String noKartuKredit, String idValidasi) {
        String idTransaksi = UUID.randomUUID().toString();

        TblTransaksiBank tblTransaksiBank = new TblTransaksiBank();

        tblTransaksiBank.setIdValidasi(idValidasi);
        tblTransaksiBank.setIdTransaksi(idTransaksi);
        tblTransaksiBank.setNoKartuKredit(noKartuKredit);
        tblTransaksiBank.setStatusTransaksi("FAILED_KARTU_KREDIT");
        tblTransaksiBank.setCreatedDate(new Date());

        localUtilDatabase.openConnection();
        EntityManager em = localUtilDatabase.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(tblTransaksiBank);
        et.commit();
        localUtilDatabase.getEntityManager().close();

        return idTransaksi;
    }

//    public String insertTransaksiKurir(String idTransaksiBank) {
//        String idTransaksi = UUID.randomUUID().toString();
//
//        TblTransaksiKurir tblTransaksiKurir = new TblTransaksiKurir();
//
//        tblTransaksiKurir.setIdTransaksi(idTransaksi);
//        tblTransaksiKurir.setIdTransaksiBank(idTransaksiBank);
//        tblTransaksiKurir.setStatusTransaksi("SUCCESS");
//
//        localUtilDatabase.openConnection();
//        EntityManager em = localUtilDatabase.getEntityManager();
//        EntityTransaction et = em.getTransaction();
//        et.begin();
//        em.persist(tblTransaksiKurir);
//        et.commit();
//        localUtilDatabase.getEntityManager().close();
//
//        return idTransaksi;
//    }

//    public static void main(String args[]) {
//        Thread1 tr1 = new Thread1();
//        tr1.setTotalData(100);
//        tr1.setThreadId(UUID.randomUUID().toString());
//        //tr1.insertValidasi();
//        int userId = UtilGeneral.getInstance().getUserId();
//        String noKartuKredit = tr1.getUserCreditCard(userId);
//        String idValidasi = tr1.insertValidasi();
//        tr1.insertTransaksiBank(noKartuKredit, idValidasi);
//    }
}
