package PII;

/**
 * 
 * @author Charga
 *
 */
public class VitalSignCategory {
	
	/* The patient record */
	PatientRecord patient;
	
	/**
	 * Constructs the urgency.
	 * @param p
	 */
	public VitalSignCategory(PatientRecord p){
		this.patient = p;
	}
	
	/**
	 * Returns the urgency.
	 * @return the urgency.
	 */
	public int calculateCategory(){
		int urgency = 0;
		SymptomVitalSign s = patient.getSymptomsVitalSigns().get(
				patient.getSymptomsVitalSigns().size() - 1);
		if (patient.getAge() < 2){
			urgency += 1;
		}
		if (s.getTempValue() >= 39.0){
			urgency += 1;
		}
		if(s.getSystolicVal() >= 140 ||
				s.getDiastolicVal() >= 90){
			urgency += 1;
		}
		if(s.getHeartRate() >= 100 ||
				s.getHeartRate() <= 50){
			urgency += 1;
		}
		return urgency;
	}

}
