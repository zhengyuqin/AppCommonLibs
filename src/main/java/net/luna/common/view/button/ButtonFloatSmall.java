package net.luna.common.view.button;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import net.luna.commom.R;
import net.luna.common.util.DpUtils;

public class ButtonFloatSmall extends ButtonFloat {
	
	public ButtonFloatSmall(Context context, AttributeSet attrs) {
		super(context, attrs);
		sizeRadius = 20;
		sizeIcon = 20;
		setDefaultProperties();
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(DpUtils.dpToPx(sizeIcon, getResources()), DpUtils.dpToPx(sizeIcon, getResources()));
		params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		icon.setLayoutParams(params);
	}
	
	protected void setDefaultProperties(){
		rippleSpeed = DpUtils.dpToPx(2, getResources());
		rippleSize = 10;		
		// Min size
		setMinimumHeight(DpUtils.dpToPx(sizeRadius * 2, getResources()));
		setMinimumWidth(DpUtils.dpToPx(sizeRadius * 2, getResources()));
		// Background shape
		setBackgroundResource(R.drawable.background_button_float);
//		setBackgroundColor(backgroundColor);
	}

}
