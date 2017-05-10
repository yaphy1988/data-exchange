package com.ai.bdex.dataexchange.security;

import com.ai.bdex.dataexchange.annotation.Security;

public abstract interface PermissionCheckHandler
{
  public abstract String isPermission(Security paramSecurity)
    throws Exception;
}
