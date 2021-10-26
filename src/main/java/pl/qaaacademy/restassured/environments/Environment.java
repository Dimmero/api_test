package pl.qaaacademy.restassured.environments;

public class Environment {

    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Environment(String host) {
        this.host = host;
    }
}
