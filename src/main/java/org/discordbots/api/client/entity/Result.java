package org.discordbots.api.client.entity;

import java.util.List;

public class Result<T> {
    /**
    * the search results
    */
    private List<T> results;
    
    /**
    * the search settings
    */
    private int limit, offset, count, total;


    /**
    * the results of the search
    *
    * @return List<T>, the results 
    */
    public List<T> getResults() {
        return results;
    }
    /**
    * the limit of the search
    *
    * @return int, the limit
    */
    public int getLimit() {
        return limit;
    }
    /**
    * the offset of the search
    *
    * @return int, the offset
    */
    public int getOffset() {
        return offset;
    }
    /**
    * the count of items in the search
    *
    * @return int, the count
    */
    public int getCount() {
        return count;
    }
    /**
    * the total of the search
    *
    * @return int, the total
    */
    public int getTotal() {
        return total;
    }

}
