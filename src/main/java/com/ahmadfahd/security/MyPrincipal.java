package com.ahmadfahd.security;

import javax.security.auth.Subject;
import java.security.Principal;

public class MyPrincipal implements Principal {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
