/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package process;

import entity.Book;

/**
 *
 * @author ggarvanese
 */
public class OutOfStockException extends Exception {

    public OutOfStockException(String isbn) {
        super(isbn);
    }

}
