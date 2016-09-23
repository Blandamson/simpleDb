package simpledb;

import java.util.NoSuchElementException;

/**
 * Created by maxtaggart on 9/23/16.
 *
 * Returns an iterator over all the tuples stored in this DbFile. The
 * iterator must use {@link BufferPool#getPage}, rather than
 * {@link DbFile#readPage} to iterate through the pages.
 */

public class DbFileIteratorClass implements DbFileIterator {

    public DbFileIteratorClass(TransactionId id, int tableId){

    }

    /**
     * Opens the iterator
     * @throws DbException when there are problems opening/accessing the database.
     */
    public void open()
            throws DbException, TransactionAbortedException{

    }

    /** @return true if there are more tuples available, false if no more tuples or iterator isn't open. */
    public boolean hasNext()
            throws DbException, TransactionAbortedException{

        return false;
    }

    /**
     * Gets the next tuple from the operator (typically implementing by reading
     * from a child operator or an access method).
     *
     * @return The next tuple in the iterator.
     * @throws NoSuchElementException if there are no more tuples
     */
    public Tuple next()
            throws DbException, TransactionAbortedException, NoSuchElementException{

        return null;
    }

    /**
     * Resets the iterator to the start.
     * @throws DbException When rewind is unsupported.
     */
    public void rewind() throws DbException, TransactionAbortedException{

    }

    /**
     * Closes the iterator.
     */
    public void close(){

    }

}
