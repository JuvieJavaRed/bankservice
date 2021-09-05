package com.wonderlabz.bankservice.util;

import java.math.BigDecimal;

public class BigDecimalConverter {

    /*
    * Method to convert String to Big Decimal
    * @param valueToConvert of type String
    * return BigDecimal
    */
    public BigDecimal changeStringToBigDecimal(String valueToConvert){
        BigDecimal convertedValue = new BigDecimal(valueToConvert);
        return convertedValue;
    }
}
