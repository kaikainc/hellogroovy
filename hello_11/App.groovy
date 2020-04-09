package hello_11

import com.mine.hello.DB
import com.mine.hello.ReadCsv


class GroovyToPg {
    static void main(args) {
        def row = ReadCsv.readfile('/home/hde/dev/data.csv')//读csv文件
        Db.insertDb(row)//将数据写入数据库
        def result = Db.selectDb_01()//查询数据
        result.each{
            println "这个是用map方式存放查询结果"
            println "${it['tran_date']},${it['timestampl']},${it['acc']},${it['amt']},${it['dr_cr_flag']},${it['rpt_sum']}"
        }
        result = Db.selectDb_02()//查询数据
        result.each{
            println "这个是用对象方式存放查询结果"
            println "${it.tran_date},${it.timestampl},${it.acc},${it.amt},${it.dr_cr_flag},${it.rpt_sum}"
        }
    }
}