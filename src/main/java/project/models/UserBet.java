package project.models;

public class UserBet {
    private int userId;
    private int matchId;
    private int outcome;
    private double value;

    public UserBet() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getOutcome() {
        return outcome;
    }

    public void setOutcome(int outcome) {
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        return "UserBet{" +
                "userId=" + userId +
                ", matchId=" + matchId +
                ", outcome=" + outcome +
                ", value=" + value +
                '}';
    }
}
