package api.tests.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Fact {

    @SerializedName("_id")
    private String id;

    private String text;
    private String type;
    private String upvotes;
    private String userUpvoted;
    private User  user;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(String upvotes) {
        this.upvotes = upvotes;
    }

    public String getUserUpvoted() {
        return userUpvoted;
    }

    public void setUserUpvoted(String userUpvoted) {
        this.userUpvoted = userUpvoted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fact fact = (Fact) o;
        return Objects.equals(id, fact.id) &&
                Objects.equals(text, fact.text) &&
                Objects.equals(type, fact.type) &&
                Objects.equals(upvotes, fact.upvotes) &&
                Objects.equals(userUpvoted, fact.userUpvoted) &&
                Objects.equals(user, fact.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, type, upvotes, userUpvoted, user);
    }
}
