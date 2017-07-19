package aharoldk.finalproject.clases;

/**
 * Created by alwandy on 11/07/17.
 */

public class Fighters {
    private int wins;
    private int losses;
    private int draws;

    private String rank;
    private String pound_for_pound_rank;
    private String belt_thumbnail;
    private String link;
    private String weight_class;

    private String nickname;
    private String last_name;
    private String first_name;

    public Fighters() {
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return losses;
    }

    public void setLoses(int loses) {
        this.losses = loses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPound_for_pound_rank() {
        return pound_for_pound_rank;
    }

    public void setPound_for_pound_rank(String pound_for_pound_rank) {
        this.pound_for_pound_rank = pound_for_pound_rank;
    }

    public String getBelt_thumbnail() {
        return belt_thumbnail;
    }

    public void setBelt_thumbnail(String belt_thumbnail) {
        this.belt_thumbnail = belt_thumbnail;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getWeight_class() {
        return weight_class;
    }

    public void setWeight_class(String weight_class) {
        this.weight_class = weight_class;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFullName(){
        return first_name + " " + last_name;
    }

    public int getMatch(){
        return wins + draws + losses;
    }
}
