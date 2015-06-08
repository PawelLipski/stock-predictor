/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Dariusz Hudziak
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Unrecognized method")
public class InvalidMethodException extends Exception {

    public InvalidMethodException(String string) {
        super(string);
    }
    
}
