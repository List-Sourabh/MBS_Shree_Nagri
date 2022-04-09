package mbLib;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import shree_nagari.mbank.R;



public class NewErrorDialogClass extends Dialog implements OnClickListener 
{

	Activity activity;
	private Dialog d;
	private Button ok;
	private TextView txt_message; 
	public  String textMessage;
	public NewErrorDialogClass(Activity activity,String textMessage)  
	{
		super(activity);		
		this.textMessage=textMessage;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)  
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setCanceledOnTouchOutside(false);
		setContentView(R.layout.custom_dialog);		
		ok = (Button)findViewById(R.id.btn_ok);
		txt_message=(TextView)findViewById(R.id.txt_dia);
		txt_message.setText(textMessage);
		ok.setOnClickListener(this);		
	}//end onCreate

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
			case R.id.btn_ok:
				
				dismiss();
				
			  break;			
			default:
			  break;
		}
		dismiss();
	}
}//end class