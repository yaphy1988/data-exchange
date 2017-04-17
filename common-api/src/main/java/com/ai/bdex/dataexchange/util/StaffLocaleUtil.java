package com.ai.bdex.dataexchange.util;

import com.ai.bdex.dataexchange.common.dto.BaseStaff;

/**
 * Created by fangyunfeng on 2017/4/17.
 */
public class StaffLocaleUtil {
    private static ThreadLocal<BaseStaff> threadLocal = new ThreadLocal();

    public StaffLocaleUtil() {
    }

    public static BaseStaff getStaff() {
        BaseStaff staff = (BaseStaff)threadLocal.get();
        return staff == null?new BaseStaff():staff;
    }

    public static void setStaff(BaseStaff staff) {
        threadLocal.set(staff);
    }
}
