package com.mcsumdu.hritsay.specialty.models;

public class Role {
    private int roleId;
    private String title;
    private int rank;

    public Role() {}


    public Role(int roleId, String title, int rank) {
        this.roleId = roleId;
        this.title = title;
        this.rank = rank;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", title='" + title + '\'' +
                ", rank=" + rank +
                '}';
    }
}
