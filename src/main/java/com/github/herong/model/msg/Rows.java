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
 * @createTime 2013-7-17 上午10:39:42
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class Rows implements Serializable {
    private String key;
    private List<Row> rows;

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    public void addRow(Row row) {
        if (this.rows == null) {
            this.rows = new ArrayList<Row>(1);
        }
        this.rows.add(row);

    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the row
     */
    public List<Row> getRows() {
        return rows;
    }

    /**
     * @param row
     *            the row to set
     */
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

}
