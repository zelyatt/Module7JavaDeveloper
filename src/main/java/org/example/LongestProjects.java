package org.example;

import javax.xml.crypto.Data;

public class LongestProjects {
    private int clientId;
    private int monthCount;

    @Override
    public String toString() {
        return "LongestProjects{" +
                "clientId=" + clientId +
                ", monthDuration=" + monthCount +
                '}';
    }

    public LongestProjects(int clientId, int monthCount) {
        this.clientId = clientId;
        this.monthCount = monthCount;
    }

    public void setMonthDuration(int projectDuration) {
        this.monthCount = projectDuration;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getMonthDuration() {
        return monthCount;
    }
}
