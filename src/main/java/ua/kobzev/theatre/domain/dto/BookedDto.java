package ua.kobzev.theatre.domain.dto;

/**
 * Created by kkobziev on 4/6/16.
 */
public class BookedDto {
    private String userEmail;
    private Integer assignedEventId;
    private Integer seat;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getAssignedEventId() {
        return assignedEventId;
    }

    public void setAssignedEventId(Integer assignedEventId) {
        this.assignedEventId = assignedEventId;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
