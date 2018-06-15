package com.klib.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.test.myapplication.R;

/**
 * Created by yangke on 2017/9/7.
 */

public class ChatTextStampView extends FrameLayout {

    private TextView textView;
    private View timeStampLayout;
    private TextView timeTextView;

    private int gap = 2;

    public ChatTextStampView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ChatTextStampView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChatTextStampView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.chat_text_stamp, this);

        gap = (int) (2 * context.getResources().getDisplayMetrics().density);
        textView = findViewById(R.id.msgContentText);
        timeStampLayout = findViewById(R.id.chat_item_stamp_layout);
        timeTextView = timeStampLayout.findViewById(R.id.stamp_time);
    }

    public void setTextAndTime(String text, String time) {
        textView.setText(text);
        timeTextView.setText(time);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);

        final int lastLineIndex = textView.getLayout().getLineCount() - 1;
        final float LastRowWidth = textView.getLayout().getLineWidth(lastLineIndex);

        MarginLayoutParams lp = (MarginLayoutParams) timeStampLayout.getLayoutParams();
        final int extraWidth = timeStampLayout.getMeasuredWidth() + lp.leftMargin;
        final int extraHeight = timeStampLayout.getMeasuredHeight();

        int lastLineMinWidth = (int) (LastRowWidth + extraWidth);
        if (textView.getLayout().getLineLeft(lastLineIndex) > 0
                && textView.getLayout().getLineRight(lastLineIndex) == getMeasuredWidth()) {
            // 判断是从右边开始绘制的
            lastLineMinWidth = getMeasuredWidth() + extraWidth;
        }

        if (lastLineMinWidth > maxWidth) {
            // 需要增加高度，在下一行显示时间戳
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + extraHeight);
        } else if (lastLineMinWidth > getMeasuredWidth()) {
            // 最后一行超出文本宽度，增加宽度
            setMeasuredDimension(lastLineMinWidth, getMeasuredHeight() + gap);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());

        timeStampLayout.layout(r - l - timeStampLayout.getMeasuredWidth(),
                b - t - timeStampLayout.getMeasuredHeight(), r - l, b - t);
    }
}
