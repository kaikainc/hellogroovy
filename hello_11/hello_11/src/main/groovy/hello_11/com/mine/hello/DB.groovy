package com.mine.hello

import groovy.sql.Sql
import org.apache.groovy.dateutil.extensions.*

class DB {
    Sql sql

    DB() {
        def url = 'jdbc:postgresql://localhost:5432/mydb'
        def user = 'test'
        def password = '1234'
        def driver = 'org.postgresql.Driver'
        sql = Sql.newInstance(url, user, password, driver)
    }

    //list子元素是map
    def getQryDtl(String tranDate, Integer flag) {
        def x = DateUtilStaticExtensions.parse(new java.util.Date(), 'yyyy-MM-dd', tranDate)
        def y = new java.sql.Date(x.getTime())

        List qry_dtl = sql.rows("""
            SELECT acc, tran_date, amt, dr_cr_flag, rpt_sum, timestampl
            FROM brch_qry_dtl
            where tran_date=? and dr_cr_flag=?
        """, [y, flag])

        List results = qry_dtl.collect { 
            ['acc': it.acc, 'tran_date': it.tran_date, 'amt': it.amt, 'rpt_sum': it.rpt_sum] 
        }
        return results
    }

    //list子元素是对象
    def getQryDtl_02(String tranDate, Integer flag) {
        List qry_dtl = sql.rows("""
            SELECT acc, tran_date, amt, dr_cr_flag, rpt_sum, timestampl
            FROM brch_qry_dtl
            where tran_date=cast(? as date) and dr_cr_flag=?
        """, [tranDate, flag])

        List results = []
        qry_dtl.each() { 
            def x = new QryDtl()
            x.acc = it.acc
            x.tranDate = it.tran_date
            x.amt = it.amt
            x.rptSum = it.rpt_sum
            results += x
        }
        return results
    }

    def setQryDtl(row)
    {
        def db = Db()
        row.each { 
        def cols = it.values
        def tran_date =  cols[0]
        def timestampl = cols[1] 
        def acc =  cols[2] 
        def amt = cols[3]
        def dr_cr_flag = cols[4]
        def rpt_sum = cols[5] 
        db.execute("INSERT INTO brch_qry_dtl (tran_date , timestampl , acc , amt , dr_cr_flag , rpt_sum) VALUES (cast(${tran_date} as date),${timestampl}, ${acc}, cast(${amt} as numeric), cast(${dr_cr_flag} as integer), ${rpt_sum})");
        }
    }
}
