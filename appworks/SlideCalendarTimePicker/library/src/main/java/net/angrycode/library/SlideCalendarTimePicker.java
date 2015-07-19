package net.angrycode.library;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeDialogFragment;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;

import java.util.Date;

/**
 * <p>This class contains methods for the library client to create
 * a new {@code SlideCalendarTimePicker}.</p>
 *
 * <p>It also implements a Builder API that offers more convenient
 * object creation.</p>
 *
 * @author ACCoder
 *
 */
public class SlideCalendarTimePicker
{
    public static final int HOLO_DARK = 1;
    public static final int HOLO_LIGHT = 2;

    private FragmentManager mFragmentManager;
    private SlideDateTimeListener mListener;
    private Date mInitialDate;
    private Date mMinDate;
    private Date mMaxDate;
    private boolean mIsClientSpecified24HourTime;
    private boolean mIs24HourTime;
    private int mTheme;
    private int mIndicatorColor;

    /**
     * Creates a new instance of {@code SlideCalendarTimePicker}.
     *
     * @param fm  The {@code FragmentManager} from the calling activity that is used
     *            internally to show the {@code DialogFragment}.
     */
    public SlideCalendarTimePicker(FragmentManager fm)
    {
        // See if there are any DialogFragments from the FragmentManager
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag(SlideDateTimeDialogFragment.TAG_SLIDE_DATE_TIME_DIALOG_FRAGMENT);

        // Remove if found
        if (prev != null)
        {
            ft.remove(prev);
            ft.commit();
        }

        mFragmentManager = fm;
    }

    /**
     * <p>Sets the listener that is used to inform the client when
     * the user selects a new date and time.</p>
     *
     * <p>This must be called before {@link #show()}.</p>
     *
     * @param listener
     */
    public void setListener(SlideDateTimeListener listener)
    {
        mListener = listener;
    }

    /**
     * <p>Sets the initial date and time to display in the date
     * and time pickers.</p>
     *
     * <p>If this method is not called, the current date and time
     * will be displayed.</p>
     *
     * @param initialDate  the {@code Date} object used to determine the
     *                     initial date and time to display
     */
    public void setInitialDate(Date initialDate)
    {
        mInitialDate = initialDate;
    }

    /**
     * <p>Sets the minimum date that the DatePicker should show.</p>
     *
     * <p>This must be called before {@link #show()}.</p>
     *
     * @param minDate  the minimum selectable date for the DatePicker
     */
    public void setMinDate(Date minDate)
    {
        mMinDate = minDate;
    }

    /**
     * <p>Sets the maximum date that the DatePicker should show.</p>
     *
     * <p>This must be called before {@link #show()}.</p>
     *
     * @param maxDate  the maximum selectable date for the DatePicker
     */
    public void setMaxDate(Date maxDate)
    {
        mMaxDate = maxDate;
    }

    private void setIsClientSpecified24HourTime(boolean isClientSpecified24HourTime)
    {
        mIsClientSpecified24HourTime = isClientSpecified24HourTime;
    }

    /**
     * <p>Sets whether the TimePicker displays its time in 12-hour
     * (AM/PM) or 24-hour format.</p>
     *
     * <p>If this method is not called, the device's default time
     * format is used.</p>
     *
     * <p>This also affects the time displayed in the tab.</p>
     *
     * <p>Must be called before {@link #show()}.</p>
     *
     * @param is24HourTime  <tt>true</tt> to force 24-hour time format,
     *                      <tt>false</tt> to force 12-hour (AM/PM) time
     *                      format.
     */
    public void setIs24HourTime(boolean is24HourTime)
    {
        setIsClientSpecified24HourTime(true);
        mIs24HourTime = is24HourTime;
    }

    /**
     * Sets the theme of the dialog. If no theme is specified, it
     * defaults to holo light.
     *
     * @param theme  {@code SlideDateTimePicker.HOLO_DARK} for a dark theme, or
     *               {@code SlideDateTimePicker.HOLO_LIGHT} for a light theme
     */
    public void setTheme(int theme)
    {
        mTheme = theme;
    }

    /**
     * Sets the color of the underline for the currently selected tab.
     *
     * @param indicatorColor  the color of the selected tab's underline
     */
    public void setIndicatorColor(int indicatorColor)
    {
        mIndicatorColor = indicatorColor;
    }

    /**
     * Shows the dialog to the user. Make sure to call
     * {@link #setListener(SlideDateTimeListener)} before calling this.
     */
    public void show()
    {
        if (mListener == null)
        {
            throw new NullPointerException(
                    "Attempting to bind null listener to SlideDateTimePicker");
        }

        if (mInitialDate == null)
        {
            setInitialDate(new Date());
        }

        SlideCalendarTimeDialogFragment dialogFragment =
                SlideCalendarTimeDialogFragment.newInstance(
                        mListener,
                        mInitialDate,
                        mMinDate,
                        mMaxDate,
                        mIsClientSpecified24HourTime,
                        mIs24HourTime,
                        mTheme,
                        mIndicatorColor);

        dialogFragment.show(mFragmentManager,
                SlideDateTimeDialogFragment.TAG_SLIDE_DATE_TIME_DIALOG_FRAGMENT);
    }

    public SlideCalendarTimeDialogFragment create() {
        if (mListener == null)
        {
            throw new NullPointerException(
                    "Attempting to bind null listener to SlideDateTimePicker");
        }

        if (mInitialDate == null)
        {
            setInitialDate(new Date());
        }

        SlideCalendarTimeDialogFragment dialogFragment =
                SlideCalendarTimeDialogFragment.newInstance(
                        mListener,
                        mInitialDate,
                        mMinDate,
                        mMaxDate,
                        mIsClientSpecified24HourTime,
                        mIs24HourTime,
                        mTheme,
                        mIndicatorColor);
        return dialogFragment;
    }

    /*
     * The following implements the builder API to simplify
     * creation and display of the dialog.
     */
    public static class Builder
    {
        // Required
        private FragmentManager fm;
        private SlideDateTimeListener listener;

        // Optional
        private Date initialDate;
        private Date minDate;
        private Date maxDate;
        private boolean isClientSpecified24HourTime;
        private boolean is24HourTime;
        private int theme;
        private int indicatorColor;

        public Builder(FragmentManager fm)
        {
            this.fm = fm;
        }

        /**
         * @see SlideCalendarTimePicker#setListener(SlideDateTimeListener)
         */
        public Builder setListener(SlideDateTimeListener listener)
        {
            this.listener = listener;
            return this;
        }

        /**
         * @see SlideCalendarTimePicker#setInitialDate(Date)
         */
        public Builder setInitialDate(Date initialDate)
        {
            this.initialDate = initialDate;
            return this;
        }

        /**
         * @see SlideCalendarTimePicker#setMinDate(Date)
         */
        public Builder setMinDate(Date minDate)
        {
            this.minDate = minDate;
            return this;
        }

        /**
         * @see SlideCalendarTimePicker#setMaxDate(Date)
         */
        public Builder setMaxDate(Date maxDate)
        {
            this.maxDate = maxDate;
            return this;
        }

        /**
         * @see SlideCalendarTimePicker#setIs24HourTime(boolean)
         */
        public Builder setIs24HourTime(boolean is24HourTime)
        {
            this.isClientSpecified24HourTime = true;
            this.is24HourTime = is24HourTime;
            return this;
        }

        /**
         * @see SlideCalendarTimePicker#setTheme(int)
         */
        public Builder setTheme(int theme)
        {
            this.theme = theme;
            return this;
        }

        /**
         * @see SlideCalendarTimePicker#setIndicatorColor(int)
         */
        public Builder setIndicatorColor(int indicatorColor)
        {
            this.indicatorColor = indicatorColor;
            return this;
        }

        /**
         * <p>Build and return a {@code SlideDateTimePicker} object based on the previously
         * supplied parameters.</p>
         *
         * <p>You should call {@link #show()} immediately after this.</p>
         *
         * @return
         */
        public SlideCalendarTimePicker build()
        {
            SlideCalendarTimePicker picker = new SlideCalendarTimePicker(fm);
            picker.setListener(listener);
            picker.setInitialDate(initialDate);
            picker.setMinDate(minDate);
            picker.setMaxDate(maxDate);
            picker.setIsClientSpecified24HourTime(isClientSpecified24HourTime);
            picker.setIs24HourTime(is24HourTime);
            picker.setTheme(theme);
            picker.setIndicatorColor(indicatorColor);

            return picker;
        }
    }
}
