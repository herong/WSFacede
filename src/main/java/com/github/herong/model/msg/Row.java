/**
 * 
 */
package com.github.herong.model.msg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO 这里用文字描述这个类的主要作用
 * 
 * @author herong
 * @createTime 2013-7-17 上午10:40:16
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class Row implements Serializable {
    private String id;
    private List<Col> cols;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the col
     */
    public List<Col> getCols() {
        return cols;
    }

    /**
     * @param col
     *            the col to set
     */
    public void setCol(List<Col> cols) {
        this.cols = cols;
    }

    public void addCol(Col col) {
        if (this.cols == null) {
            this.cols = new ArrayList<Col>(1);
        }
        this.cols.add(col);

    }

}
