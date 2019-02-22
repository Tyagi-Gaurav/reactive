package org.gt.shipping.routing.filter;

import javax.validation.constraints.NotNull;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext =
            new ThreadLocal<>();

    public static final UserContext getContext() {
        UserContext context = UserContextHolder.userContext.get();

        if (context == null) {
            userContext.set(createEmptyContext());
        }

        return userContext.get();
    }

    public static final void setUserContext(@NotNull UserContext context) {
        userContext.set(context);
    }

    private static UserContext createEmptyContext() {
        return new UserContext();
    }
}
