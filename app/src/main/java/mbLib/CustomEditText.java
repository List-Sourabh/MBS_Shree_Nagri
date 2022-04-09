package mbLib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomEditText extends EditText
{
	//Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Kozuka-Gothic-Pro-M_26793.ttf");
    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context) {
        super(context);
        init();
    }

    private void init() 
    {
    	//setTypeface(tf);
    	setTypeface(FontCache.get("fonts/calibri.ttf", getContext()));
    	//setBackgroundResource(R.drawable.edit_text_bg);
    }

}