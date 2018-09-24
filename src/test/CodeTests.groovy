package test

import code.util.Data
import code.util.GetLegal

class CodeTests extends GroovyTestCase{


    void testDataConnection(){
        assert sql.isConnected()
    }

    void testExecuteQuery(){
        def meaning = 'The term "United States" except as otherwise specifically herein provided, when used in a geographical sense, means the continental United States, Alaska, Hawaii, Puerto Rico, Guam, the Virgin Islands of the United States, and the Commonwealth of the Northern Mariana Islands.'
        String[] params = ['8','1101','a (38)','United States',meaning]
        assert sql.executeQuery('us_code', params)
    }

    void testGetLegal(){

        GetLegal legal = new GetLegal()

        println(legal.law('8','1401','a'))
    }
}
