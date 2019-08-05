package com.business.nation.dprnow.pengaduan;

public class ModelPengaduan {
    String username, location, typePengaduan, titlePengaduan, messagePengaduan, likePengaduan,
    commentPengaduan, timePengaduan;

    public ModelPengaduan(String username, String location, String typePengaduan, String titlePengaduan, String messagePengaduan, String likePengaduan, String commentPengaduan, String timePengaduan) {
        this.username = username;
        this.location = location;
        this.typePengaduan = typePengaduan;
        this.titlePengaduan = titlePengaduan;
        this.messagePengaduan = messagePengaduan;
        this.likePengaduan = likePengaduan;
        this.commentPengaduan = commentPengaduan;
        this.timePengaduan = timePengaduan;
    }

    public ModelPengaduan() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTypePengaduan() {
        return typePengaduan;
    }

    public void setTypePengaduan(String typePengaduan) {
        this.typePengaduan = typePengaduan;
    }

    public String getTitlePengaduan() {
        return titlePengaduan;
    }

    public void setTitlePengaduan(String titlePengaduan) {
        this.titlePengaduan = titlePengaduan;
    }

    public String getMessagePengaduan() {
        return messagePengaduan;
    }

    public void setMessagePengaduan(String messagePengaduan) {
        this.messagePengaduan = messagePengaduan;
    }

    public String getLikePengaduan() {
        return likePengaduan;
    }

    public void setLikePengaduan(String likePengaduan) {
        this.likePengaduan = likePengaduan;
    }

    public String getCommentPengaduan() {
        return commentPengaduan;
    }

    public void setCommentPengaduan(String commentPengaduan) {
        this.commentPengaduan = commentPengaduan;
    }

    public String getTimePengaduan() {
        return timePengaduan;
    }

    public void setTimePengaduan(String timePengaduan) {
        this.timePengaduan = timePengaduan;
    }
}
