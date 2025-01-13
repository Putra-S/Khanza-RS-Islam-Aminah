/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smsobj;

import java.util.logging.Logger;

/**
 * @author perpustakaan
 */
public class Status {

    private String manufacture;

    private String model;

    private String serialNo;

    private String simImsi;

    private int signal;

    private int baterai;

    /**
     * @return
     */
    public int getBaterai() {
        return baterai;
    }

    /**
     * @param baterai
     */
    public void setBaterai(int baterai) {
        this.baterai = baterai;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    /**
     * @return
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * @param serialNo
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * @return
     */
    public int getSignal() {
        return signal;
    }

    /**
     * @param signal
     */
    public void setSignal(int signal) {
        this.signal = signal;
    }

    /**
     * @return
     */
    public String getSimImsi() {
        return simImsi;
    }

    public void setSimImsi(String simImsi) {
        this.simImsi = simImsi;
    }

    private static final Logger LOG = Logger.getLogger(Status.class.getName());

}
