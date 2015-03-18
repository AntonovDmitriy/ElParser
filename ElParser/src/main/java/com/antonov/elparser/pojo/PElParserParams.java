/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonov.elparser.pojo;

/**
 *
 * @author Antonov
 */
public class PElParserParams {

    private String FILE_EXCEL_PATH;

    public String getFILE_EXCEL_PATH() {
        return FILE_EXCEL_PATH;
    }

    public void setFILE_EXCEL_PATH(String FILE_EXCEL_PATH) {
        String oldFILE_EXCEL_PATH = this.FILE_EXCEL_PATH;
        this.FILE_EXCEL_PATH = FILE_EXCEL_PATH;
        propertyChangeSupport.firePropertyChange("FILE_EXCEL_PATH", oldFILE_EXCEL_PATH, this.FILE_EXCEL_PATH);
    }

    protected transient final java.beans.PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
