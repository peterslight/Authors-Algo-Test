package com.peterstev.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("submitted")
    @Expose
    private Integer submitted;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("submission_count")
    @Expose
    private Integer submissionCount;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Integer getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Integer submitted) {
        this.submitted = submitted;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getSubmissionCount() {
        return submissionCount;
    }

    public void setSubmissionCount(Integer submissionCount) {
        this.submissionCount = submissionCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", about='" + about + '\'' +
                ", submitted=" + submitted +
                ", updatedAt='" + updatedAt + '\'' +
                ", submissionCount=" + submissionCount +
                ", commentCount=" + commentCount +
                ", createdAt=" + createdAt +
                '}';
    }
}
