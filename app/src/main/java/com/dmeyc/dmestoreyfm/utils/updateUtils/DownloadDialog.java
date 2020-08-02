package com.dmeyc.dmestoreyfm.utils.updateUtils;

import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;

import butterknife.Bind;

/**
 * Created by shadyY on 18/12/22.
 */

public class DownloadDialog extends BaseDialog {
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.tv_dialog_download_progress)
    TextView mTvProgress;

    public DownloadDialog(Context context) {
        super(context, com.dmeyc.dmestoreyfm.R.layout.dialog_download);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle();
    }

    private void setStyle() {
//        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        mProgressBar.setProgress(progress);
        mTvProgress.setText(progress + "%");
    }

}
