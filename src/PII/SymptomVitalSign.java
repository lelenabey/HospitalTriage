package PII;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * 
 * @author Charga
 *
 */
public class SymptomVitalSign {
	
	/* The nurseID (username) */
	protected String nurseID;
	
	/* The temperature value  */
	private double tempValue;
	
	/* The systolic value */
	private double systolicVal;
	
	/* The diastolic value */
	private double diastolicVal;
	
	/* The heart rate reading */
	private double heartRate;
	
	/* The time this symptom/vital was recorded. */
	private String date;
	
	/* The description of this symptom */
	private String symptomDescription;
	
	/**
	 * Construct a new symptom or vital sign.
	 * @param nurseID
	 * @param date
	 */
	public SymptomVitalSign(String nurseID, String date){
		this.nurseID = nurseID;
		this.date = date;
	}

   public SymptomVitalSign( double tempValue, double systolicValue,
            double diastolicValue, double heartRateValue ) {
        this.tempValue = tempValue;
        this.systolicVal = systolicValue;
        this.diastolicVal = diastolicValue;
        this.heartRate = heartRateValue;

        this.date = Calendar.getInstance().getTime().toString();
    }
	    
   public SymptomVitalSign( double tempValue, double systolicValue,
            double diastolicValue, double heartRateValue, String date ) {
        this.tempValue = tempValue;
        this.systolicVal = systolicValue;
        this.diastolicVal = diastolicValue;
        this.heartRate = heartRateValue;
        this.date = date;
    }
	       
	/**
	 * Returns the temperature value of this recording.
	 * @return the temperature value.
	 */
	public double getTempValue() {
		return tempValue;
	}

	/**
	 * Sets the temperature value.
	 * @param tempValue
	 */
	public void setTempValue(double tempValue) {
		this.tempValue = tempValue;
	}

	/**
	 * Returns the Systolic value.
	 * @return Systolic value.
	 */
	public double getSystolicVal() {
		return systolicVal;
	}

	/**
	 * Sets the Systolic value.
	 * @param systolicVal
	 */
	public void setSystolicVal(double systolicVal) {
		this.systolicVal = systolicVal;
	}

	/**
	 * Returns the diastolic value.
	 * @return diastolic value.
	 */
	public double getDiastolicVal() {
		return diastolicVal;
	}

	/**SymptomVitalSign
	 * Sets the diastolic value.
	 * @param diastolicVal
	 */
	public void setDiastolicVal(double diastolicVal) {
		this.diastolicVal = diastolicVal;
	}

	/**
	 * Returns the heart rate.
	 * @return the heart rate.
	 */
	public double getHeartRate() {
		return heartRate;
	}

	/**
	 * Sets the heart rate.
	 * @param heartRate
	 */
	public void setHeartRate(double heartRate) {
		this.heartRate = heartRate;
	}

	/**
	 * Returns the symptom description.
	 * @return the symptom description.
	 */
	public String getSymptomDescription() {
		return symptomDescription;
	}

	/**
	 * Sets the symptom description.
	 * @param symptomDescription
	 */
	public void setSymptomDescription(String symptomDescription) {
		this.symptomDescription = symptomDescription;
	}

	/**
	 * Returns the nurse's ID.
	 * @return the nurse's ID.
	 */
	public String getNurseID() {
		return nurseID;
	}

	/**
	 * Returns the time this was recorded.
	 * @return the time.
	 */
	public String getDate() {
		return date;
	}	

	public void updateToFile( File file ) 
	        throws FileNotFoundException, IOException {
	    Operation.updateContent(file, this.toString()) ;
	}
	
	
   public String toString() {
        //Format formatter = new SimpleDateFormat( "MM/dd/yyyy") ;
        String strDate = this.date ;
        
        return Double.toString(this.tempValue) + "," 
                + Double.toString( this.systolicVal) + ","
                + Double.toString(this.diastolicVal) + ","
                + Double.toString(this.heartRate) + ","
                + strDate + ","
                + this.symptomDescription ;
    }
   
   public static void updateToFile( File file, 
           ArrayList<SymptomVitalSign> arrayObject ) 
                   throws FileNotFoundException, IOException {
       
       ArrayList<String> arrayContent = new ArrayList<String>() ;
       
       for(SymptomVitalSign svs : arrayObject ) {
           arrayContent.add( svs.toString()) ;
       }
       
       Operation.updateContent(file, arrayContent) ;
   }
   
   public static ArrayList<SymptomVitalSign> getAllSVS( File file) 
           throws FileNotFoundException, ParseException  {
       return Operation.getAllSVS(file) ;
   }
}
