package com.nlk.note.data.bean;

public class AddTaskBean {
    private int moduleType;
    private int scheduleTime;
    private String skillTitle;
    private String skillContent;
    private String ideaContent;
    private String scheduleContent;

    public int getModuleType() {
        return moduleType;
    }

    public void setModuleType(int moduleType) {
        this.moduleType = moduleType;
    }

    public int getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(int scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getSkillTitle() {
        return skillTitle;
    }

    public void setSkillTitle(String skillTitle) {
        this.skillTitle = skillTitle;
    }

    public String getSkillContent() {
        return skillContent;
    }

    public void setSkillContent(String skillContent) {
        this.skillContent = skillContent;
    }

    public String getIdeaContent() {
        return ideaContent;
    }

    public void setIdeaContent(String ideaContent) {
        this.ideaContent = ideaContent;
    }

    public String getScheduleContent() {
        return scheduleContent;
    }

    public void setScheduleContent(String scheduleContent) {
        this.scheduleContent = scheduleContent;
    }
}
