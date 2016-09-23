package simpledb;

import java.io.*;
import java.nio.Buffer;
import java.util.*;

/**
 * HeapFile is an implementation of a DbFile that stores a collection of tuples
 * in no particular order. Tuples are stored on pages, each of which is a fixed
 * size, and the file is simply a collection of those pages. HeapFile works
 * closely with HeapPage. The format of HeapPages is described in the HeapPage
 * constructor.
 * 
 * @see simpledb.HeapPage#HeapPage
 * @author Sam Madden
 */
public class HeapFile implements DbFile {

    private File file;
    private TupleDesc tupleDescriptor;
    /**
     * Constructs a heap file backed by the specified file.
     * 
     * @param f
     *            the file that stores the on-disk backing store for this heap
     *            file.
     */
    public HeapFile(File f, TupleDesc td) {
        // some code goes here - altered
        this.file = f;
        this.tupleDescriptor = td;
    }

    /**
     * Returns the File backing this HeapFile on disk.
     * 
     * @return the File backing this HeapFile on disk.
     */
    public File getFile() {
        // some code goes here - altered
        return file;
    }

    /**
     * Returns an ID uniquely identifying this HeapFile. Implementation note:
     * you will need to generate this tableid somewhere ensure that each
     * HeapFile has a "unique id," and that you always return the same value for
     * a particular HeapFile. We suggest hashing the absolute file name of the
     * file underlying the heapfile, i.e. f.getAbsoluteFile().hashCode().
     * 
     * @return an ID uniquely identifying this HeapFile.
     */
    public int getId() {
        // some code goes here - altered
        //throw new UnsupportedOperationException("implement this");
        return file.getAbsoluteFile().hashCode();
    }

    /**
     * Returns the TupleDesc of the table stored in this DbFile.
     * 
     * @return TupleDesc of this DbFile.
     */
    public TupleDesc getTupleDesc() {
        // some code goes here - altered
       // throw new UnsupportedOperationException("implement this");
        return tupleDescriptor;
    }

    // see DbFile.java for javadocs
    public Page readPage(PageId pid) {
        // some code goes here - altered
        //From pid we can get pageNumber, and from BufferPool we can get default page size (4KB). Using this
        //information we can scan the file to the start of the page, write the page to a byte array, and then create
        //a new HeapPage using the pageId and the byte array. Theoretically.
        FileInputStream inputStream = null;
        try {
             inputStream = new FileInputStream(file);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found. From HeapFile.java readPage()");
        }
        int pageNum = pid.pageNumber(); //pageNum is assumed to be 1-based, not 0-based.
        int pageSize = BufferPool.getPageSize();
        long startOfPage = (pageNum -1) * pageSize;
        byte[] pageData = new byte[pageSize];
        if (inputStream != null){
            try{
                inputStream.skip(startOfPage);
                inputStream.read(pageData ,0 ,pageSize);
            }
            catch (Exception e) {
                System.out.println("Problem reading from the file input stream. HeapFile.java readPage().");
            }
        }
        HeapPage requestedPage = null;
        try{
            requestedPage = new HeapPage((HeapPageId)pid, pageData);
        }
        catch (IOException e){
            System.out.println("Problem creating a new Heap Page from the File. HeapFile.java readPage().");
        }
        return requestedPage;
    }

    // see DbFile.java for javadocs
    public void writePage(Page page) throws IOException {
        // some code goes here
        // not necessary for lab1
    }

    /**
     * Returns the number of pages in this HeapFile.
     */
    public int numPages() {
        // some code goes here - altered
        long length = file.length();
        int pageSize = BufferPool.getPageSize();
        return (int)length/pageSize;
    }

    // see DbFile.java for javadocs
    public ArrayList<Page> insertTuple(TransactionId tid, Tuple t)
            throws DbException, IOException, TransactionAbortedException {
        // some code goes here
        return null;
        // not necessary for lab1
    }

    // see DbFile.java for javadocs
    public ArrayList<Page> deleteTuple(TransactionId tid, Tuple t) throws DbException,
            TransactionAbortedException {
        // some code goes here
        return null;
        // not necessary for lab1
    }

    // see DbFile.java for javadocs
    public DbFileIterator iterator(TransactionId tid) {
        // some code goes here - altered
        //The helper class "DbFileIteratorClass" was added by Max as a concrete implementation of
        // the DbFileIterator interface.
        return null;
    }

}

