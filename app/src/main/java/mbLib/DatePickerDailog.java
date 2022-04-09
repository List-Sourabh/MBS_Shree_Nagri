package mbLib;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

import com.wheel.ArrayWheelAdapter;
import com.wheel.NumericWheelAdapter;
import com.wheel.OnWheelChangedListener;
import com.wheel.WheelView;

import java.util.Calendar;

import shree_nagari.mbank.R;

public class DatePickerDailog extends Dialog {

    private final Context Mcontex;

    private final int NoOfYear = 100;

    public DatePickerDailog(Context context, Calendar calendar,final DatePickerListner dtp) {

        super(context);
        Mcontex = context;
        LinearLayout lytmain = new LinearLayout(Mcontex);
        lytmain.setOrientation(LinearLayout.VERTICAL);
        LinearLayout lytdate = new LinearLayout(Mcontex);
        LinearLayout lytbutton = new LinearLayout(Mcontex);

        Button btnset = new Button(Mcontex);
        Button btncancel = new Button(Mcontex);

        btnset.setText("Set");
        btncancel.setText("Cancel");

        btnset.setTextColor(context.getResources().getColor(R.color.white));
        btncancel.setTextColor(context.getResources().getColor(R.color.white));

        btnset.setBackgroundResource(R.drawable.button_style);
        btncancel.setBackgroundResource(R.drawable.button_style);

        int paddingPixel = 5;
        float density = context.getResources().getDisplayMetrics().density;
        int paddingDp = (int) (paddingPixel * density);

        btnset.setPadding(0, paddingDp, 0, paddingDp);
        btncancel.setPadding(0, paddingDp, 0, paddingDp);

        final WheelView month = new WheelView(Mcontex);
        final WheelView year = new WheelView(Mcontex);
        final WheelView day = new WheelView(Mcontex);

        LayoutParams btnSetPara = new LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        btnSetPara.rightMargin = 5;

        LayoutParams btnCancelPara = new LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        btnCancelPara.leftMargin = 5;

        lytdate.addView(day, new LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1.2f));
        lytdate.addView(month, new LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0.8f));
        lytdate.addView(year, new LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        lytbutton.addView(btnset, btnSetPara);

        lytbutton.addView(btncancel, btnCancelPara);
        lytbutton.setPadding(5, 5, 5, 5);

        lytmain.addView(lytdate);
        lytmain.addView(lytbutton);

        setContentView(lytmain);

        getWindow().setLayout(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT);
        OnWheelChangedListener listener = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                updateDays(year, month, day);

            }
        };

        // month
        int curMonth = calendar.get(Calendar.MONTH);
        String[] months = new String[]{"January", "February", "March",
                "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};
        month.setViewAdapter(new DateArrayAdapter(context, months, curMonth));
        month.setCurrentItem(curMonth);
        month.addChangingListener(listener);

        Calendar cal = Calendar.getInstance();
        // year
        int curYear = calendar.get(Calendar.YEAR);
        int Year = cal.get(Calendar.YEAR);


        year.setViewAdapter(new DateNumericAdapter(context, Year - NoOfYear,
                Year, NoOfYear));
        year.setCurrentItem(curYear - (Year - NoOfYear));
        year.addChangingListener(listener);

        // day
        updateDays(year, month, day);
        day.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);

        btnset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar c = updateDays(year, month, day);
                dtp.OnDoneButton(DatePickerDailog.this, c);
            }
        });
        btncancel.setOnClickListener(v -> dtp.OnCancelButton(DatePickerDailog.this));

    }

    Calendar updateDays(WheelView year, WheelView month, WheelView day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR) + (year.getCurrentItem() - NoOfYear));
        calendar.set(Calendar.MONTH, month.getCurrentItem());

        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        day.setViewAdapter(new DateNumericAdapter(Mcontex, 1, maxDays, calendar.get(Calendar.DAY_OF_MONTH) - 1));
        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
        day.setCurrentItem(curDay - 1, true);
        calendar.set(Calendar.DAY_OF_MONTH, curDay);
        return calendar;
    }

    public interface DatePickerListner {
        void OnDoneButton(Dialog datedialog, Calendar c);

        void OnCancelButton(Dialog datedialog);
    }

    private class DateNumericAdapter extends NumericWheelAdapter {
        int currentItem;
        int currentValue;

        public DateNumericAdapter(Context context, int minValue, int maxValue,
                                  int current) {
            super(context, minValue, maxValue);
            this.currentValue = current;
            setTextSize(20);
        }

        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            if (currentItem == currentValue) {
                view.setTextColor(context.getResources().getColor(R.color.cyan));
            }
            view.setTypeface(null, Typeface.BOLD);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            currentItem = index;
            return super.getItem(index, cachedView, parent);
        }
    }

    private class DateArrayAdapter extends ArrayWheelAdapter<String> {
        int currentItem;
        int currentValue;

        public DateArrayAdapter(Context context, String[] items, int current) {
            super(context, items);
            this.currentValue = current;
            setTextSize(20);
        }

        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            if (currentItem == currentValue) {
                view.setTextColor(context.getResources().getColor(R.color.cyan));
            }
            view.setTypeface(null, Typeface.BOLD);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            currentItem = index;
            return super.getItem(index, cachedView, parent);
        }
    }
}
