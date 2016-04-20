package org.github.brnmb.android.active.enums;

import org.github.brnmb.android.active.R;
import org.github.brnmb.android.active.utils.MyApplication;

public enum HeroRoleEnum {
    MID_LANE(MyApplication.getAppContext().getResources().getString(R.string.role_mid_lane)),
    OFF_LANE(MyApplication.getAppContext().getResources().getString(R.string.role_off_lane)),
    SAFE_LANE(MyApplication.getAppContext().getResources().getString(R.string.role_safe_lane));

    private String mRole;

    HeroRoleEnum(String role) {
        this.mRole = role;
    }

    public String getHeroRole() {
        return mRole;
    }
}
