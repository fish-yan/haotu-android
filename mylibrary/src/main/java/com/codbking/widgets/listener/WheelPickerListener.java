package com.codbking.widgets.listener;

import com.codbking.widgets.bean.DateBean;
import com.codbking.widgets.bean.DateType;

/**
 * Created by codbking on 2016/9/22.
 */

public interface WheelPickerListener {
     void onSelect(DateType type, DateBean bean);
}
