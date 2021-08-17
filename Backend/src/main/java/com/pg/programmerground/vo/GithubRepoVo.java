package com.pg.programmerground.vo;

public class GithubRepoVo {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GithubRepoVo{" +
                "title='" + title + '\'' +
                '}';
    }
}
