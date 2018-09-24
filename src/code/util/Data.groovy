package code.util

import groovy.sql.Sql

import java.sql.SQLException

class Data {

    Password pass = new Password()

    final def JDBC_DRIVER = 'com.mysql.cj.jdbc.Driver'
    final def CONNSTR = 'jdbc:mysql://localhost:3306/law_dictionary?autoReconnect=true&useSSL=false'
    final def USER = 'tetrapro'
    final def PASS = pass.getPass()
    def conn

    Data(){
        conn = Sql.newInstance(CONNSTR, USER, new String(PASS), JDBC_DRIVER)
    }

    def isConnected(){
        conn? true : false
    }

    /**
     * INSERT INTO $table (title, section, paragraph, term, meaning) VALUES (?,?,?,?,?)
     * @param query
     * @param values
     * @return
     */
    def executeQuery(String table, String[] values){
        boolean isSuccessful = false
        try {
            def sql

            if (table == 'us_code' || table == 'implementing_regs') {
                sql = "INSERT INTO $table (title, section, paragraph, term, meaning) VALUES (?,?,?,?,?)"
            } else if (table == 'blacks_law') {
                sql = "INSTER INTO $table (edition, page, term, definition) VALUES (?,?,?,?)"
            } else if (table == 'cornell_links') {
                sql = "INSTER INTO $table (reference, link) VALUES (?,?)"
            } else if (table == 'crimes') {
                sql = "INSTER INTO $table (title, section, explanation) VALUES (?,?,?)"
            }else if(table == 'supreme_court'){
                sql = "INSTER INTO $table (reference, interpretation) VALUES (?,?)"
            }else{
                sql = "INSTER INTO $table (term, us_code_id) VALUES (?,?)"
            }


            conn.execute sql, values

            isSuccessful = true

            System.out.println("Data updated successfully!")
        } catch (SQLException e) {
            e.printStackTrace()
        } catch (ClassNotFoundException e) {
            e.printStackTrace()
        }
        isSuccessful
    }
}
