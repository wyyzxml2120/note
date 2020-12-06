package com.nlk.baseframe.ui.callback;

public class UnPeekLiveData<T> extends ProtectedUnPeekLiveData<T> {

    @Override
    public void setValue(T value) {
        super.setValue(value);
    }

    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    public static class Builder<T> {

        /**
         * 消息的生存时长
         */
        private int eventSurvivalTime = 1000;

        /**
         * 是否允许传入 null value
         */
        private boolean isAllowNullValue;

        /**
         * 是否允许自动清理，默认 true
         */
        private boolean isAllowToClear = true;

        public Builder<T> setEventSurvivalTime(int eventSurvivalTime) {
            this.eventSurvivalTime = eventSurvivalTime;
            return this;
        }

        public Builder<T> setAllowNullValue(boolean allowNullValue) {
            this.isAllowNullValue = allowNullValue;
            return this;
        }

        public Builder<T> setAllowToClear(boolean allowToClear) {
            this.isAllowToClear = allowToClear;
            return this;
        }

        public UnPeekLiveData<T> create() {
            UnPeekLiveData<T> liveData = new UnPeekLiveData<>();
            liveData.DELAY_TO_CLEAR_EVENT = this.eventSurvivalTime;
            liveData.isAllowNullValue = this.isAllowNullValue;
            liveData.isAllowToClear = this.isAllowToClear;
            return liveData;
        }
    }
}
