package project.models;

public class Match {
    private int id;
    private String teamOneLogo;
    private String teamOneName;
    private double teamOneK;
    private String teamTwoLogo;
    private String teamTwoName;
    private double teamTwoK;
    private String date;
    private String time;
    private double drawK;

    public Match(int id, String teamOneLogo, String teamOneName, double teamOneK, String teamTwoLogo, String teamTwoName, double teamTwoK, String date, String time, double drawK) {
        this.id = id;
        this.teamOneLogo = teamOneLogo;
        this.teamOneName = teamOneName;
        this.teamOneK = teamOneK;
        this.teamTwoLogo = teamTwoLogo;
        this.teamTwoName = teamTwoName;
        this.teamTwoK = teamTwoK;
        this.date = date;
        this.time = time;
        this.drawK = drawK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamOneLogo() {
        return teamOneLogo;
    }

    public void setTeamOneLogo(String teamOneLogo) {
        this.teamOneLogo = teamOneLogo;
    }

    public String getTeamOneName() {
        return teamOneName;
    }

    public void setTeamOneName(String teamOneName) {
        this.teamOneName = teamOneName;
    }

    public double getTeamOneK() {
        return teamOneK;
    }

    public void setTeamOneK(double teamOneK) {
        this.teamOneK = teamOneK;
    }

    public String getTeamTwoLogo() {
        return teamTwoLogo;
    }

    public void setTeamTwoLogo(String teamTwoLogo) {
        this.teamTwoLogo = teamTwoLogo;
    }

    public String getTeamTwoName() {
        return teamTwoName;
    }

    public void setTeamTwoName(String teamTwoName) {
        this.teamTwoName = teamTwoName;
    }

    public double getTeamTwoK() {
        return teamTwoK;
    }

    public void setTeamTwoK(double teamTwoK) {
        this.teamTwoK = teamTwoK;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getDrawK() {
        return drawK;
    }

    public void setDrawK(double drawK) {
        this.drawK = drawK;
    }
}