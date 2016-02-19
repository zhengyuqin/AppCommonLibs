package net.luna.common.view.button;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.luna.commom.R;
import net.luna.common.util.DpUtils;

/**
 * Created by bintou on 15/11/4.
 */
public class RappleImageButton extends Button {


    ImageView imageBtn;

    public RappleImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        imageBtn = new ImageView(context);
        imageBtn.setAdjustViewBounds(true);
        imageBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
        imageBtn.setLayoutParams(params);
        addView(imageBtn);
        rippleSpeed = 24f;
    }

    public void setRippleSpeed(float rippleSpeed) {
        this.rippleSpeed = rippleSpeed;
    }

    protected void setDefaultProperties() {
        minHeight = 36;
        minWidth = 88;
        rippleSize = 3;
        // Min size
        setMinimumHeight(DpUtils.dpToPx(minHeight, getResources()));
        setMinimumWidth(DpUtils.dpToPx(minWidth, getResources()));
        setBackgroundResource(R.drawable.background_transparent);
    }

    @Override
    protected void setAttributes(AttributeSet attrs) {
        int bacgroundColor = attrs.getAttributeResourceValue(ANDROIDXML, "background", -1);
        if (bacgroundColor != -1) {
            setBackgroundColor(getResources().getColor(bacgroundColor));
        } else {
            // Color by hexadecimal
            // Color by hexadecimal
            background = attrs.getAttributeIntValue(ANDROIDXML, "background", -1);
            if (background != -1)
                setBackgroundColor(background);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (x != -1) {

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(makePressColor());
            canvas.drawCircle(x, y, radius, paint);
            if (radius > getHeight() / rippleSize)
                radius += rippleSpeed;
            if (radius >= getWidth()) {
                x = -1;
                y = -1;
                radius = getHeight() / rippleSize;
                if (onClickListener != null && clickAfterRipple)
                    onClickListener.onClick(this);
            }
            invalidate();
        }

    }

    /**
     * Make a dark color to ripple effect
     *
     * @return
     */
    @Override
    protected int makePressColor() {
        return Color.parseColor("#10DDDDDD");
    }

    public void setImageResource(int resId) {
        imageBtn.setImageResource(resId);
    }

    // Set color of background
    public void setBackgroundColor(int color) {
        backgroundColor = color;
        if (isEnabled())
            beforeBackground = backgroundColor;
        imageBtn.setBackgroundColor(color);
    }

    @Override
    /**
     * 此方法无效
     */
    public TextView getTextView() {
        return null;
    }

    public ImageView getImageView() {
        return imageBtn;
    }

}
