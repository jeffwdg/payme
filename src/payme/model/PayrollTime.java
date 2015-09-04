package payme.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class PayrollTime {

    private final SimpleIntegerProperty time_id;
    private final SimpleStringProperty emp_num;
    private final SimpleFloatProperty rate;
    private final SimpleFloatProperty days;
    /*private final SimpleFloatProperty reg_ot_hrs;
    private final SimpleFloatProperty reg_nd;
    private final SimpleFloatProperty reg_ot_nd;
    private final SimpleFloatProperty sunday_hrs;
    private final SimpleFloatProperty sunday_ot_hrs;
    private final SimpleFloatProperty sunday_nd;
    private final SimpleFloatProperty sunday_ot_nd;  
    private final SimpleFloatProperty reg_holiday;
    private final SimpleFloatProperty reg_holiday_ot;
    private final SimpleFloatProperty reg_holiday_nd;
    private final SimpleFloatProperty reg_holiday_ot_nd;
    private final SimpleFloatProperty special_holiday;
    private final SimpleFloatProperty special_holiday_ot;
    private final SimpleFloatProperty special_holiday_nd;
    private final SimpleFloatProperty special_holiday_ot_nd;
    private final SimpleFloatProperty month_13th;
    private final SimpleFloatProperty total_days_rendered;*/
 
 
    public PayrollTime(int ftime_id, String femp_num, float frate, float fdays){
    		/*float freg_ot_hrs, float freg_nd, float freg_ot_nd,
			float fsunday_hrs, float fsunday_ot_hrs, float fsunday_nd,float fsunday_ot_nd,
			float freg_holiday, float freg_holiday_ot, float freg_holiday_nd, float freg_holiday_ot_nd,
			float fspecial_holiday, float fspecial_holiday_ot, float fspecial_holiday_nd, float fspecial_holiday_ot_nd,
			float fmonth_13th, float ftotal_days_rendered) {*/
    	 this.time_id = new SimpleIntegerProperty(ftime_id);
    	 this.emp_num = new SimpleStringProperty(femp_num);
         this.rate = new SimpleFloatProperty(frate);
         this.days = new SimpleFloatProperty(fdays);
       /*  this.reg_ot_hrs = new SimpleFloatProperty(freg_ot_hrs);
         this.reg_nd = new SimpleFloatProperty(freg_nd);
         this.reg_ot_nd = new SimpleFloatProperty(freg_ot_nd);
         this.sunday_hrs = new SimpleFloatProperty(fsunday_hrs);
         this.sunday_ot_hrs = new SimpleFloatProperty(fsunday_ot_hrs);
         this.sunday_nd = new SimpleFloatProperty(fsunday_nd);
         this.sunday_ot_nd = new SimpleFloatProperty(fsunday_ot_nd);
         this.reg_holiday = new SimpleFloatProperty(freg_holiday);
         this.reg_holiday_ot = new SimpleFloatProperty(freg_holiday_ot);
         this.reg_holiday_nd = new SimpleFloatProperty(freg_holiday_nd);
         this.reg_holiday_ot_nd = new SimpleFloatProperty(freg_holiday_ot_nd);
         this.special_holiday = new SimpleFloatProperty(fspecial_holiday);
         this.special_holiday_ot = new SimpleFloatProperty(fspecial_holiday_ot);
         this.special_holiday_nd = new SimpleFloatProperty(fspecial_holiday_nd);
         this.special_holiday_ot_nd = new SimpleFloatProperty(fspecial_holiday_ot_nd);
         this.month_13th = new SimpleFloatProperty(fmonth_13th);
         this.total_days_rendered = new SimpleFloatProperty(ftotal_days_rendered);*/
    }


	public SimpleIntegerProperty getTimeId() {
		return time_id;
	}
	
	public void setTimeId(int ftime_id) {
		 time_id.set(ftime_id);
	}


	public SimpleStringProperty getEmpNum() {
		return emp_num;
	}
	public void setEmpNum(String femp_num) {
		emp_num.set(femp_num);
	}


	public SimpleFloatProperty getRate() {
		return rate;
	}
	public void setRate(float frate) {
		 rate.set(frate);
	}


	public SimpleFloatProperty getDays() {
		return days;
	}
	public void setDays(float fdays) {
		rate.set(fdays);
	}
	
	/*
	public SimpleFloatProperty getRegOtHrs() {
		return reg_ot_hrs;
	}
	public void setRegOtHrs(float freg_ot_hrs) {
		reg_ot_hrs.set(freg_ot_hrs);
	}


	public SimpleFloatProperty getRegNd() {
		return reg_nd;
	}
	public void setRegNd(float freg_nd) {
		reg_nd.set(freg_nd);
	}


	public SimpleFloatProperty getRegOtNd() {
		return reg_ot_nd;
	}	
	public void setRegOtNd(float freg_ot_nd) {
		reg_ot_nd.set(freg_ot_nd);
	}


	public SimpleFloatProperty getSundayHrs() {
		return sunday_hrs;
	}
	public void setSundayHrs(float fsunday_hrs) {
		sunday_hrs.set(fsunday_hrs);
	}


	public SimpleFloatProperty getSundayOtHrs() {
		return sunday_ot_hrs;
	}
	public void setSundayOtHrs(float fsunday_ot_hrs) {
		sunday_ot_hrs.set(fsunday_ot_hrs);
	}

	public SimpleFloatProperty getSundayNd() {
		return sunday_nd;
	}
	public void setSundayNd(float fsunday_nd) {
		sunday_nd.set(fsunday_nd);
	}

	public SimpleFloatProperty getSundayOtNd() {
		return sunday_ot_nd;
	}
	public void setSundayOtNd(float fsunday_ot_nd) {
		sunday_ot_nd.set(fsunday_ot_nd);
	}

	public SimpleFloatProperty getRegHoliday() {
		return reg_holiday;
	}
	public void setRegHoliday(float freg_holiday) {
		 reg_holiday.set(freg_holiday);
	}

	public SimpleFloatProperty getRegHolidayOt() {
		return reg_holiday_ot;
	}
	public void setRegHolidayOt(float freg_holiday_ot) {
		reg_holiday_ot.set(freg_holiday_ot);
	}

	public SimpleFloatProperty getRegHolidayNd() {
		return reg_holiday_nd;
	}
	public void setRegHolidayNd(float freg_holiday_nd) {
		reg_holiday_nd.set(freg_holiday_nd);
	}


	public SimpleFloatProperty getRegHolidayOtNd() {
		return reg_holiday_ot_nd;
	}
	public void setRegHolidayOtNd(float freg_holiday_ot_nd) {
		reg_holiday_ot_nd.set(freg_holiday_ot_nd);
	}


	public SimpleFloatProperty getSpecialHoliday() {
		return special_holiday;
	}	
	public void setSpecialHoliday(float fspecial_holiday) {
		special_holiday.set(fspecial_holiday);
	}


	public SimpleFloatProperty getSpecialHolidayOt() {
		return special_holiday_ot;
	}
	public void setSpecialHolidayOt(float fspecial_holiday_ot) {
		special_holiday_ot.set(fspecial_holiday_ot);
	}

	public SimpleFloatProperty getSpecialHolidayNd() {
		return special_holiday_nd;
	}
	public void setSpecialHolidayNd(float fspecial_holiday_nd) {
		special_holiday_nd.set(fspecial_holiday_nd);
	}

	public SimpleFloatProperty getSpecialHolidayOtNd() {
		return special_holiday_ot_nd;
	}
	public void setSpecialHolidayOtNd(float fspecial_holiday_ot_nd) {
		special_holiday_ot_nd.set(fspecial_holiday_ot_nd);
	}


	public SimpleFloatProperty getMonth13th() {
		return month_13th;
	}
	public void setMonth13th(float fmonth_13th) {
		month_13th.set(fmonth_13th);
	}


	public SimpleFloatProperty getTotalDaysRendered() {
		return total_days_rendered;
	}	
	public void setTotalDaysRendered(float ftotal_days_rendered) {
		total_days_rendered.set(ftotal_days_rendered);
	}

    */
    
    
} 