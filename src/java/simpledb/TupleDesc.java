package simpledb;

import java.io.Serializable;
import java.util.*;

/**
 * TupleDesc describes the schema of a tuple.
 */
public class TupleDesc implements Serializable {

    /**
     * A help class to facilitate organizing the information of each field
     * */
    public static class TDItem implements Serializable {

        private static final long serialVersionUID = 1L;


        /**
         * The type of the field
         * */
        public final Type fieldType;
        
        /**
         * The name of the field
         * */
        public final String fieldName;

        public TDItem(Type t, String n) {
            this.fieldName = n;
            this.fieldType = t;

        }

        public String toString() {
            return fieldName + "(" + fieldType + ")";
        }
    }

    /**
     * @return
     *        An iterator which iterates over all the field TDItems
     *        that are included in this TupleDesc
     * */
    private ArrayList<TDItem> tupleDescriptorItems;

     public Iterator<TDItem> iterator() {
         //code goes here
        return tupleDescriptorItems.iterator();
    }

    private static final long serialVersionUID = 1L;

    /**
     * Create a new TupleDesc with typeAr.length fields with fields of the
     * specified types, with associated named fields.
     * 
     * @param typeAr
     *            array specifying the number of and types of fields in this
     *            TupleDesc. It must contain at least one entry.
     * @param fieldAr
     *            array specifying the names of the fields. Note that names may
     *            be null.
     */

    public TupleDesc(Type[] typeAr, String[] fieldAr) {
        // some code goes here - altered;
        if (typeAr.length > 1 && fieldAr.length == typeAr.length){
            tupleDescriptorItems = new ArrayList<TDItem>();
            for (int i = 0; i < typeAr.length; i++) {
                TDItem currentItem = new TDItem(typeAr[i], fieldAr[i]);
                tupleDescriptorItems.add(currentItem);
            }
        }
        else {
            throw new IllegalArgumentException("Either the typeAr argument passed into the TupleDesc constructor was zero-length or the length of typeAr was not equal to the length of fieldAr. typeAr should have at least one element and the typeAr and fieldAr arrays should have the same number of elements.");
        }


    }

    /**
     * Constructor. Create a new tuple desc with typeAr.length fields with
     * fields of the specified types, with anonymous (unnamed) fields.
     * 
     * @param typeAr
     *            array specifying the number of and types of fields in this
     *            TupleDesc. It must contain at least one entry.
     */
    public TupleDesc(Type[] typeAr) {
        tupleDescriptorItems = new ArrayList<>();
        for (int i = 0; i < typeAr.length; i++){
            TDItem currentItem = new TDItem(typeAr[i], null);
            tupleDescriptorItems.add(currentItem);
        }
    }

    /**
     * @return the number of fields in this TupleDesc
     */
    public int numFields() {
        // some code goes here -altered
        return tupleDescriptorItems.size();
    }

    /**
     * Gets the (possibly null) field name of the ith field of this TupleDesc.
     * 
     * @param i
     *            index of the field name to return. It must be a valid index.
     * @return the name of the ith field
     * @throws NoSuchElementException
     *             if i is not a valid field reference.
     */
    public String getFieldName(int i) throws NoSuchElementException {
        // some code goes here - altered
        TDItem requestedItem = tupleDescriptorItems.get(i);

        return requestedItem.fieldName;
    }

    /**
     * Gets the type of the ith field of this TupleDesc.
     * 
     * @param i
     *            The index of the field to get the type of. It must be a valid
     *            index.
     * @return the type of the ith field
     * @throws NoSuchElementException
     *             if i is not a valid field reference.
     */
    public Type getFieldType(int i) throws NoSuchElementException {
        // some code goes here - altered
        TDItem requestedItem = tupleDescriptorItems.get(i);
        return requestedItem.fieldType;
    }

    /**
     * Find the index of the field with a given name.
     * 
     * @param name
     *            name of the field.
     * @return the index of the field that is first to have the given name.
     * @throws NoSuchElementException
     *             if no field with a matching name is found.
     */
    public int fieldNameToIndex(String name) throws NoSuchElementException {
        // some code goes here - altered
        for(int i = 0; i < tupleDescriptorItems.size(); i++){
            TDItem tempItem = tupleDescriptorItems.get(i);
            String tempName = tempItem.fieldName;
            if (tempName.equals(name)){
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * @return The size (in bytes) of tuples corresponding to this TupleDesc.
     *         Note that tuples from a given TupleDesc are of a fixed size.
     */
    public int getSize() {
        // some code goes here - altered
        int size = 0;
        for(int i = 0; i < tupleDescriptorItems.size(); i++){
            TDItem tempItem = tupleDescriptorItems.get(i);
            size += tempItem.fieldType.getLen();
        }
        return size;
    }

    /**
     * Merge two TupleDescs into one, with td1.numFields + td2.numFields fields,
     * with the first td1.numFields coming from td1 and the remaining from td2.
     * 
     * @param td1
     *            The TupleDesc with the first fields of the new TupleDesc
     * @param td2
     *            The TupleDesc with the last fields of the TupleDesc
     * @return the new TupleDesc
     */
    public static TupleDesc merge(TupleDesc td1, TupleDesc td2) {
        // some code goes here - altered
        //Delete copies if fieldNames aren't unique
        List mergedTypeList = new ArrayList<Type>();
        List mergedNameList = new ArrayList<String>();
        for (int i = 0; i < td1.tupleDescriptorItems.size(); i++){
            mergedTypeList.add(td1.getFieldType(i));
            mergedNameList.add(td1.getFieldName(i));
        }
        for(int i = 0; i< td2.tupleDescriptorItems.size(); i++){
            String tempName = td2.getFieldName(i);
            if (!mergedNameList.contains(tempName)){
                mergedNameList.add(tempName);
                mergedTypeList.add(td2.getFieldType(i));
            }
        }
        Type[] typeAr = new Type[mergedTypeList.size()];
        String[] nameAr = new String[mergedNameList.size()];
        for (int i = 0; i < mergedNameList.size(); i++){
            typeAr[i] = (Type)mergedTypeList.get(i);
            nameAr[i] = (String)mergedNameList.get(i);
        }
        TupleDesc newDescriptor = new TupleDesc(typeAr, nameAr);
        return newDescriptor;
    }

    /**
     * Compares the specified object with this TupleDesc for equality. Two
     * TupleDescs are considered equal if they are the same size and if the n-th
     * type in this TupleDesc is equal to the n-th type in td.
     * 
     * @param o
     *            the Object to be compared for equality with this TupleDesc.
     * @return true if the object is equal to this TupleDesc.
     */
    public boolean equals(Object o) {
        // some code goes here - altered
        if (tupleDescriptorItems.size() == ((TupleDesc)o).numFields()){
            for (int i = 0; i < tupleDescriptorItems.size(); i++){
                TDItem ourItem = tupleDescriptorItems.get(i);
                Type ourType = ourItem.fieldType;
                Type theirType = ((TupleDesc)o).getFieldType(i);
                if (!(ourType == theirType)){
                    return false;
                }
            }
        }
        else{
            return false;
        }
        return true;
    }

    public int hashCode() {
        // If you want to use TupleDesc as keys for HashMap, implement this so
        // that equal objects have equals hashCode() results
        throw new UnsupportedOperationException("unimplemented");
    }

    /**
     * Returns a String describing this descriptor. It should be of the form
     * "fieldType[0](fieldName[0]), ..., fieldType[M](fieldName[M])", although
     * the exact format does not matter.
     * 
     * @return String describing this descriptor.
     */
    public String toString() {
        // some code goes here - altered
        String descriptorString = "";
        for (int i = 0; i < tupleDescriptorItems.size(); i++){
            Type currentType = this.getFieldType(i);
            String name = this.getFieldName(i);

            descriptorString += currentType.toString() +" (" + name + "), ";
        }
        return descriptorString;
    }
}
