import java.io.File 

// use @Grab() to download CSV package
@Grab('org.apache.commons:commons-csv:1.2') 
import static org.apache.commons.csv.CSVFormat.RFC4180 

// get csv file
def file = new File('./src/data.csv')

// read the header of csv file
def header = RFC4180.withHeader()
                    .parse(file.newReader())
                    .getHeaderMap().keySet()

// print the header               
for(item in header){
    print item
    print '\t'
}
println ''

// read records and handle data by row
RFC4180.withHeader() 
       .parse(file.newReader()) 
       .iterator().each { record ->
           def cols = record.mapping.keySet()
           for(item in cols){
               print record.get(item)
               print '\t'
           }
           println ''
       }