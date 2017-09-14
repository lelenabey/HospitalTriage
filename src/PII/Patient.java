package PII;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;

/**
 * A Patient.
 * @author group0724
 */
public class Patient implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2214605243600559736L;

	/** This Patient's name. */ 
    private String Name;
    
    /** This Patient's birthday. */ 
    private String Birthday;
    
    /** This Patient's health card number. */ 
    private long HealthCardNumber;
    
    private String arrivalTime;
    
    /* List of Symptoms and Vital Signs of this patient */
    private List<SymptomVitalSign> symptomsVitalSigns;
    
    /**
     * Constructs a new Patient with ID from ID, name from Name, birthday from
     * Birthday, and health card number from HealthCardNo.
     * password.
     * @param ID This Patient's ID.
     * @param Name This Patient's name .
     * @param Birthday This Patient's birthday.
     * @param HealthCardNo This Patient's password.
     */
    public Patient(String Name, String Birthday, long HealthCardNo ) {
        this.Name = Name;
        this.Birthday = Birthday;
        this.HealthCardNumber = HealthCardNo;
    }

    public Patient(String Name, String Birthday, long HealthCardNo, 
            String ArrivalTime ) {
        
        this( Name, Birthday, HealthCardNo ) ;
        this.arrivalTime = ArrivalTime;
    }

    public Patient(String Name, String Birthday, long HealthCardNo, 
            String ArrivalTime, List<SymptomVitalSign> symptomsVitalSigns ) {
        
        this( Name, Birthday, HealthCardNo, ArrivalTime ) ;
        this.symptomsVitalSigns = symptomsVitalSigns;
    }
    
    /**
     * Returns this Patient's name.
     * @return This Patient's name.
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets the name of this Patient to name.
     * @param name The new name of this Patient.
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Returns this Patient's birthday.
     * @return This Patient's birthday.
     */
    public String getBirthday() {
        return Birthday;
    }

    /**
     * Sets the birthday of this Patient to birthday.
     * @param birthday The new birthday of this Patient.
     */
    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    /**
     * Returns this Patient's health card number.
     * @return This Patient's health card number.
     */
    public long getHealthCardNumber() {
        return HealthCardNumber;
    }

    /**
     * Sets the health card number of this Patient to healthCardNumber.
     * @param healthCardNumber The new health card number of this Patient.
     */
    public void setHealthCardNumber(long healthCardNumber) {
        HealthCardNumber = healthCardNumber;
    }

    /**
     * Returns the arrivalTime of this patient record.
     * @return the arrivalTime of this patient.
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime( String arrivalTime ) {
        this.arrivalTime = arrivalTime;
    }
    
    /**
     * Returns the Symptoms and Vital Signs of this patient record.
     * @return the Symptoms and Vital Signs.
     */
    public List<SymptomVitalSign> getSymptomsVitalSigns() {
        return symptomsVitalSigns;
    }

    /**
     * Sets the Symptoms and Vital Signs of this patient record.
     * @param symptomsVitalSigns
     */
    public void setSymptomsVitalSigns(List<SymptomVitalSign> symptomsVitalSigns) {
        this.symptomsVitalSigns = symptomsVitalSigns;
    }
    
    
    public void savePatientToFile( File filePatient ) 
            throws FileNotFoundException, IOException {
        Operation.AddContent(filePatient, this.toString());
    }
    
    public String toString() {
        String strBirthday = Birthday ;
        String strArrivalTime = arrivalTime;
        
        return Long.toString(this.HealthCardNumber) + ","  
            + this.Name + "," + strBirthday + ","
            + strArrivalTime;
            
    }
    
    public static ArrayList<Patient> getAllPatients( File file ) 
            throws FileNotFoundException, ParseException {
        return Operation.getAllPatient(file) ;
    }
}
