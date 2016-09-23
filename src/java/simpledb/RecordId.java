package simpledb;

import java.io.Serializable;

/**
 * A RecordId is a reference to a specific tuple on a specific page of a
 * specific table.
 */
public class RecordId implements Serializable {

    private static final long serialVersionUID = 1L;

    private PageId pageId;
    private int tupleNum;
    /**
     * Creates a new RecordId referring to the specified PageId and tuple
     * number.
     * 
     * @param pid
     *            the pageid of the page on which the tuple resides
     * @param tupleno
     *            the tuple number within the page.
     */
    public RecordId(PageId pid, int tupleno) {
        // some code goes here - altered
        this.pageId = pid;
        this.tupleNum = tupleno;
    }

    /**
     * @return the tuple number this RecordId references.
     */
    public int tupleno() {
        // some code goes here - altered
        return tupleNum;
    }

    /**
     * @return the page id this RecordId references.
     */
    public PageId getPageId() {
        // some code goes here - altered
        return pageId;
    }

    /**
     * Two RecordId objects are considered equal if they represent the same
     * tuple.
     * 
     * @return True if this and o represent the same tuple
     */
    @Override
    public boolean equals(Object o) {
        // some code goes here - altered
        //throw new UnsupportedOperationException("implement this");
        if (o instanceof RecordId){
            if (((RecordId)o).hashCode() == hashCode()){
                return true;
            }
        }
        return false;
    }

    /**
     * You should implement the hashCode() so that two equal RecordId instances
     * (with respect to equals()) have the same hashCode().
     * 
     * @return An int that is the same for equal RecordId objects.
     */
    @Override
    public int hashCode() {
        // some code goes here - altered
        //throw new UnsupportedOperationException("implement this");
        //Hash value is derived by concatanating the pageId hash with the tupleNumber.
        String tupleNumberStr = Integer.toString(tupleNum);
        //System.out.format("tupleNumberStr is %s\n", tupleNumberStr);
        String pageIdHashStr = Integer.toString(pageId.hashCode());
        //System.out.format("pageIdHashStr is %s\n", pageIdHashStr);
        String recordIdHashStr = pageIdHashStr + tupleNumberStr;
        //System.out.format("recordIdHashStr is %s\n", recordIdHashStr);
        return Integer.parseInt(recordIdHashStr);
    }

}

