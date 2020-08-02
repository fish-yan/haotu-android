package com.dmeyc.dmestoreyfm.api.exception;

import rx.Subscriber;

public abstract class ErrorSubscriber<T> extends Subscriber<T> {
    @Override
    public void onError(Throwable e) {
        onMyError(ExceptionEngine.handleException(e));
    }

    /**
     * 错误回调
     */
    protected abstract void onMyError(ApiException ex);
}