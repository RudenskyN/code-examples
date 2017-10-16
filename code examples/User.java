package ru.myaround.egorshashkov.around.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by egorshashkov on 17/04/2017.
 */

public class User {
    String username;
    Session session;
    String avatar;
    String description;
    String name;
    String banner;
    @SerializedName("ID")
    @Expose
    int id;
    int followed;
    @SerializedName("Isi")
    @Expose
    int isi;
    int subs;
    int subrs;
    int events_count;
    @SerializedName("replies_count")
    @Expose
    int repliesCount;
    @SerializedName("replies_sum")
    @Expose
    int repliesSum;
    String vkid;
    String fbid;
    int countNotifications;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollowed() {
        return followed;
    }

    public void setFollowed(int followed) {
        this.followed = followed;
    }

    public int getIsi() {
        return isi;
    }

    public void setIsi(int isi) {
        this.isi = isi;
    }

    public int getSubs() {
        return subs;
    }

    public void setSubs(int subs) {
        this.subs = subs;
    }

    public int getSubrs() {
        return subrs;
    }

    public void setSubrs(int subrs) {
        this.subrs = subrs;
    }

    public int getEvents_count() {
        return events_count;
    }

    public void setEvents_count(int events_count) {
        this.events_count = events_count;
    }

    public int getRepliesCount() {
        return repliesCount;
    }

    public void setRepliesCount(int repliesCount) {
        this.repliesCount = repliesCount;
    }

    public int getRepliesSum() {
        return repliesSum;
    }

    public void setRepliesSum(int repliesSum) {
        this.repliesSum = repliesSum;
    }

    public String getVkid() {
        return vkid;
    }

    public void setVkid(String vkid) {
        this.vkid = vkid;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public int getCountNotifications() {
        return countNotifications;
    }

    public void setCountNotifications(int countNotifications) {
        this.countNotifications = countNotifications;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
