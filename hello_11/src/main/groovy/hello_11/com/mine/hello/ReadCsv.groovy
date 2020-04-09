package com.mine.hello

import java.io.File 

import static org.apache.commons.csv.CSVFormat.RFC4180 
// get csv file

class ReadCsv
{
    def readfile(path)
    {
        def file = new File(path)
        def header = RFC4180.withHeader()
                    .parse(file.newReader())
                    .getHeaderMap().keySet()
        def row = RFC4180.withHeader().parse(file.newReader()).iterator()
        return row
    }
}

