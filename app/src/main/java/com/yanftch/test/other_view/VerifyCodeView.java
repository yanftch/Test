package com.yanftch.test.other_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * User : yanftch
 * Date : 2017/5/2
 * Time : 14:31
 * Desc : 图片验证码简单实现
 */

public class VerifyCodeView extends View implements View.OnClickListener {
    private static final String TAG = "dah_CodeView";
    private int lineNum = 6;//干扰线条数
    private int width, height;
    private final String DEFAULT_COLOR = "#cccccc";
    private Paint codePaint;//验证码画笔
    private Paint linePaint;//干扰线画笔
    private Path path;
    private String checkCodeText = "1234";//验证码的值
    private int codeTextLength = 4;//验证码的长度
    private float hSpace;//水平方向两个code之间的间距   的   1/2
    private final float TEXTSIZE = 30;//默认文本大小
    private final int BACKGROUND_COLOR = Color.WHITE;//默认背景色

    private void init() {
        path = new Path();
        this.setBackgroundColor(BACKGROUND_COLOR);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawBorder(canvas);//是否需要边框？
        //绘制验证码文本
        drawCodeText(canvas);
        //绘制干扰线
        drawLines(canvas);
    }

    /**
     * 绘制验证码文本
     *
     * @param canvas
     */
    private void drawCodeText(Canvas canvas) {
        char[] chars = checkCodeText.toCharArray();
        hSpace = width / codeTextLength / 2;//开始绘制的文本的x位置
        Log.e(TAG, "drawCodeText: hSpace == " + hSpace);
        for (int i = 0; i < codeTextLength; i++) {
            canvas.drawText(chars[i] + "", hSpace, generateRandomHeight(), getCodePaint());
            hSpace += width / (codeTextLength + 1);
        }
    }

    /**
     * 绘制干扰线
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        for (int i = 0; i < lineNum; i++) {
            int[] lines = generateLines();
            canvas.drawLine(lines[0], lines[1], lines[2], lines[3], getLinePaint());
        }
    }

    /**
     * 生成随机位置的高度值（Y值）
     *
     * @return Yheight
     */
    private float generateRandomHeight() {
        int y = (int) (Math.random() * height);
        // TODO: 2017/5/7 需要根据控件大小进行调整
        if (y < 20) {
            y += 20;
        }
        Log.e(TAG, "generateRandomHeight: y === " + y);
        return y;
    }

    /**
     * 生成线条的起始位置
     *
     * @return
     */
    public int[] generateLines() {
        int[] tmpLines = {0, 0, 0, 0};
        for (int i = 0; i < 4; i += 2) {
            tmpLines[i] = (int) (Math.random() * width);
            tmpLines[i + 1] = (int) (Math.random() * height);
        }
        return tmpLines;
    }

    //绘制边框
    private void drawBorder(Canvas canvas) {
        path.lineTo(width, 0);
        path.lineTo(width, height);
        path.lineTo(0, height);
        path.close();
        canvas.drawPath(path, getDefaultPaint());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        Log.e(TAG, "onSizeChanged: width == " + width + "         height == " + height);
    }

    /**
     * 验证码画笔
     *
     * @return codePaint
     */
    private Paint getCodePaint() {
        codePaint = getDefaultPaint();
        codePaint.setColor(generateColor(1));
        return codePaint;
    }

    /**
     * 干扰线画笔
     *
     * @return linePaint
     */
    private Paint getLinePaint() {
        linePaint = getDefaultPaint();
        linePaint.setStrokeWidth(1);
        linePaint.setColor(generateColor(1));
        return linePaint;
    }

    //默认画笔
    private Paint getDefaultPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setTextSize(TEXTSIZE);
        paint.setColor(Color.parseColor(DEFAULT_COLOR));
        return paint;
    }

    /**
     * 生成不同的颜色
     *
     * @param rate 色值比例
     * @return
     */
    private int generateColor(int rate) {
        Random random = new Random();
        int r = random.nextInt(256) / rate;
        int g = random.nextInt(256) / rate;
        int b = random.nextInt(256) / rate;
        return Color.rgb(r, g, b);
    }

    public VerifyCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerifyCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void onClick(View v) {

    }
    /**-----------------------------------------------------------------**/
    /**
     * 赋值验证码的值
     * 可以作为每次点击的时候更换验证码的作用
     *
     * @param codeString 验证码
     */
    public void setCheckCodeText(String codeString) {
        this.checkCodeText = codeString;
        this.codeTextLength = TextUtils.isEmpty(codeString) ? 4 : codeString.length();
        this.invalidate();
    }

    /**
     * 对外获得验证码值de方法
     *
     * @return checkCodeText
     */
    public String getCode() {
        return checkCodeText;
    }
}
