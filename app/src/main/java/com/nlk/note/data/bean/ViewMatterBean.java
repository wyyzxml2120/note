package com.nlk.note.data.bean;

public class ViewMatterBean {
    private String addContent;
    private boolean addIconShow;
    private boolean addColumnShow;
    private int addType;

    public String getAddContent() {
        return addContent;
    }

    public void setAddContent(String addContent) {
        this.addContent = addContent;
    }

    public boolean isAddIconShow() {
        return addIconShow;
    }

    public void setAddIconShow(boolean addIconShow) {
        this.addIconShow = addIconShow;
    }

    public boolean isAddColumnShow() {
        return addColumnShow;
    }

    public void setAddColumnShow(boolean addColumnShow) {
        this.addColumnShow = addColumnShow;
    }

    public int getAddType() {
        return addType;
    }

    public void setAddType(int addType) {
        this.addType = addType;
    }
}
