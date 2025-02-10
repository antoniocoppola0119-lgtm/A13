package com.groom.manvsclass.model;

public enum ServiceURL {
    T4("127.0.0.1", 3000),
    T7("127.0.0.1", 1234),
    T8("127.0.0.1", 3080),
    T9("127.0.0.1", 9999);

    private final String host;
    private final int port;

    ServiceURL(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getServiceURL() {
        return String.format("%s:%d", host, port);
    }
}
