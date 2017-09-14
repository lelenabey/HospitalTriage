package PII;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Charga
 *
 */
public class PatientRecord {
	
	/* The arrival time of this patient */
	private Date arrivalTime;
	
	/* List of Symptoms and Vital Signs of this patient */
	private List<SymptomVitalSign> symptomsVitalSigns;
	
	/* The time that the doctor checked this patient. */
	protected Date doctorDateTime;
	
	private int age;
	
	/**
	 * Constructs the PatientRecord.
	 * @param arrivalTime
	 * @param symptomsVitalSigns
	 */
	public PatientRecord(Date arrivalTime, List<SymptomVitalSign> symptomsVitalSigns, 
			int age){
		this.arrivalTime = arrivalTime;
		this.symptomsVitalSigns = symptomsVitalSigns;
		this.age = age;
		doctorDateTime = new Date(0);
	}

	/**
	 * Returns the arrivalTime of this patient record.
	 * @return the arrivalTime of this patient.
	 */
	public Date getArrivalTime() {
		return arrivalTime;
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
	
	/**
	 * Sets the time this patient has been seen by a doctor.
	 * @param time
	 */
	public void setSeenByDoctorTime (Date time){
		this.doctorDateTime = time;
	}
	
	/**
	 * Returns the time this patient has been seen by a doctor.
	 * @return the time this patient was last seen by a doctor.
	 */
	public Date getSeenByDoctorTime (){
		return this.doctorDateTime;
	}

	/**
	 * Returns the age of this patient.
	 * @return the age.
	 */
	public int getAge() {
		return age;
	}
}
