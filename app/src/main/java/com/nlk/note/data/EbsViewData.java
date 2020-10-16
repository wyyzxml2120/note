package com.nlk.note.data;

public class EbsViewData {

    public EbsViewData(boolean isShow){
        this.bottomIsShow = isShow;
    }

    private boolean bottomIsShow;

    public boolean isBottomIsShow() {
        return bottomIsShow;
    }

    public void setBottomIsShow(boolean bottomIsShow) {
        this.bottomIsShow = bottomIsShow;
    }
}
