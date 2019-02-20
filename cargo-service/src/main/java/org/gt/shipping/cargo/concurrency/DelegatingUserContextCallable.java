package org.gt.shipping.cargo.concurrency;

import org.gt.shipping.cargo.filter.UserContext;
import org.gt.shipping.cargo.filter.UserContextHolder;

import java.util.concurrent.Callable;

public class DelegatingUserContextCallable<T> implements Callable<T>{
    private Callable<T> delegate;
    private UserContext context;

    public DelegatingUserContextCallable(Callable<T> callable, UserContext context) {
        this.delegate = callable;
        this.context = context;
    }

    @Override
    public T call() throws Exception {
        UserContextHolder.setUserContext(context);
        try {
            return delegate.call();
        } finally {
            this.context = null;
        }
    }

    public static <T> Callable<T> create(Callable<T> delegate, UserContext userContext) {
        return new DelegatingUserContextCallable<>(delegate, userContext);
    }
}
