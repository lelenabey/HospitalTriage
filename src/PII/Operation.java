/**
 * 
 */
package PII;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * @author g1wanson
 *
 */
public class Operation {
    //private static String PATIENT_FILE = "patient.txt" ;
    //private static String PATIENT_SVS_FILE = "patientsvs.txt";
    
    //private final static int PATIENT_ID_LOCATION = 0;
    private final static int PATIENT_NAME_LOCATION = 1;
    private final static int PATIENT_BIRTHDAY_LOCATION = 2;
    private final static int PATIENT_HCN_LOCATION = 0;
    private final static int PATIENT_ARRIVAL_LOCATION = 3;
    
    public static Patient LookupPatient( File filePatient, long HealthCardNo )  
            throws FileNotFoundException, ParseException {
        
        Scanner scanner = new Scanner(filePatient);
        String [] record;
        Patient patientResult = null;
        String strHealthCardNo = Long.toString(HealthCardNo);
        
        while(scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            
            if ( record[PATIENT_HCN_LOCATION] == strHealthCardNo ) {
                String name = record[PATIENT_NAME_LOCATION];
                String strBirthday = record[PATIENT_BIRTHDAY_LOCATION];
                //String dateBirthday = new SimpleDateFormat( "MM/dd/yyyy", 
                //        Locale.ENGLISH).parse( strBirthday );
                
                patientResult = new Patient( name, strBirthday, 
                        HealthCardNo ) ;
            }
        }
        scanner.close();
        
        return patientResult;
    }
    
    public static ArrayList<Patient> getAllPatient( File file)
     throws FileNotFoundException, ParseException {
        
        Scanner scanner = new Scanner( file );
        String [] record;
        ArrayList<Patient> result = new ArrayList<Patient>() ;

        while(scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            
            String name = record[PATIENT_NAME_LOCATION];
            String strValue = record[PATIENT_ARRIVAL_LOCATION];
            String strBirthday = record[PATIENT_BIRTHDAY_LOCATION];
            long HealthCardNo = Long.parseLong(record[PATIENT_HCN_LOCATION]) ;
            //Date dateArrival = new SimpleDateFormat( "MM/dd/yyyy", 
            //        Locale.ENGLISH).parse( strValue );
            
            result.add( new Patient( name, strBirthday, 
                    HealthCardNo, strValue ) );
        }
        scanner.close();
        
        return result;
        
    }
    
    public static ArrayList<SymptomVitalSign> getAllSVS( File file) 
        throws FileNotFoundException, ParseException {
        
        Scanner scanner = new Scanner( file );
        String [] record;
        ArrayList<SymptomVitalSign> result = new ArrayList<SymptomVitalSign>() ;

        while(scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            

/*            return Double.toString(this.tempValue) + "," 
            + Double.toString( this.systolicVal) + ","
            + Double.toString(this.diastolicVal) + ","
            + Double.toString(this.heartRate) + ","
            + strDate + ","
            + this.symptomDescription ;*/
            
            double tempValue = Double.parseDouble(record[0]) ;
            double systolicValue = Double.parseDouble(record[1]) ;
            double diastolicValue = Double.parseDouble(record[2]) ;
            double heartRateValue = Double.parseDouble(record[3]) ;
            String date = record[4];
            //Date date = new SimpleDateFormat( "MM/dd/yyyy", 
            //        Locale.ENGLISH).parse( record[4] );
            //String strDesc = record[5];
            
            result.add( new SymptomVitalSign( tempValue, systolicValue,
                    diastolicValue, heartRateValue, date) );
        }
        scanner.close();
        
        return result;
        
    }
/*    public static void AddPatient(File filePatient, Patient patient ) 
            throws IOException {
        
        if ( patient != null ) {
            FileOutputStream outputStream = new FileOutputStream( filePatient );
            String[] record;
            
            outputStream.write((patient.toString() + "\n").getBytes());
            outputStream.close();
        }
    }*/
    
    public static void AddContent( File file, String strContent) 
            throws FileNotFoundException, IOException {
        
        ArrayList<String> result = new ArrayList<String>() ;
        
        result.add( strContent );
        result.addAll( getAllContents( file ));
        writeText( file, result ) ;
    }
    
    public static void updateContent( File file, String strContent ) 
                    throws FileNotFoundException, IOException {
        
        ArrayList<String> updateArray = new ArrayList<String>() ;
        updateArray.add( strContent );
        updateContent( file, updateArray );
    }

    public static void updateContent( File file, 
            ArrayList<String> arrayContent ) 
                    throws FileNotFoundException, IOException {
        writeText( file, updateList( file, arrayContent)) ;
    }
    
    
    public static void RecordVitalSigns( File fileSVS,
            List<SymptomVitalSign> symptomVitalSignList ) throws IOException  {

        ArrayList<String> arrayContent = new ArrayList<String>() ;
        
        for( SymptomVitalSign svs : symptomVitalSignList ) {
            arrayContent.add( svs.toString()) ;
        }
        
        writeText( fileSVS, arrayContent) ;
/*        FileOutputStream outputStream = new FileOutputStream( fileSVS );

        for( SymptomVitalSign svs : symptomVitalSignList ) {
            outputStream.write((svs.toString() + "\n").getBytes());
        }
        outputStream.close();        
*/    }
    
    private static ArrayList<String> updateList( File file, 
            ArrayList<String> updateContent ) throws FileNotFoundException {
    
        Scanner scanner = new Scanner( file ) ;
        String record;
        
        ArrayList<String> result = new ArrayList<String>() ;
        Boolean isFound ;
        
        while( scanner.hasNextLine()) {
            isFound = false;
            
            record = scanner.nextLine();
            
            for( String lsContent : updateContent ) {
                if ( record.split(",")[0].equals(lsContent.split(".")[0])){
                    isFound = true;
                }
            }
            
            if ( ! isFound ) {
                result.add( record) ;
            }
        }
        
        result.addAll( updateContent ) ;
        scanner.close() ;
        
        return result;
        
    }
    
    
    private static ArrayList<String> getAllContents( File file ) 
    throws FileNotFoundException {
        Scanner scanner = new Scanner( file ) ;
        ArrayList<String> result = new ArrayList<String>();
        String record;
        
        while ( scanner.hasNextLine()) {
            record = scanner.nextLine();
            result.add( record ) ;
        }
        
        scanner.close();
        return result;
    }
    
    private static void writeText( File file, ArrayList<String> content) 
            throws IOException {
        
        FileOutputStream output = new FileOutputStream( file ) ;
        try {
            for( String strCont : content ) {
                output.write( (strCont+ "\r\n").getBytes() ) ;
            }
            
        }catch ( IOException e) {
            e.printStackTrace();
        } finally {
            output.close();
        }
    }
}
