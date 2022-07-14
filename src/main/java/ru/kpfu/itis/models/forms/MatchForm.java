package ru.kpfu.itis.models.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchForm {

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
