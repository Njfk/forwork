package com.forwork.com.forwork.reader.view;

import android.support.annotation.IntDef;

/**
 * Created by Administrator on 2018/10/17.
 */

public class PageFactory {
    public static final int OPEING = 1;
    public static final int FINISH = 2;
    public static final int FAIL = 3;

    public static int mStatus = PageFactory.OPEING;

    @IntDef({OPEING,FINISH,FAIL})
    public @interface Status {

    }

    public static void setmStatus(@Status int mStatus) {
       PageFactory.mStatus = mStatus;
    }

    public static int getmStatus() {
        return mStatus;
    }
}
