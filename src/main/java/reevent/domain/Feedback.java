package reevent.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
public class Feedback extends EntityBase {
    @ManyToOne(optional = false)
    User createdBy;

    String title;

    @Lob
    String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Rating rating;

    @ManyToOne(optional = false)
    Event event;

    public Feedback() {
    }

    public Feedback(Event event, User createdBy, Rating rating, String title, String text) {
        this(event, createdBy, rating);
        this.text = text;
        this.title = title;
    }

    public Feedback(Event event, User createdBy, Rating rating) {
        this.createdBy = createdBy;
        this.event = event;
        this.rating = rating;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("createdBy", createdBy.getUsername()).
                append("title", title).
                append("text", text).
                append("rating", rating).
                append("event", event.getShortId()).
                appendSuper(super.toString()).
                toString();
    }
}
