package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Set;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class implSet<E> implements Set<E> {

    private ArrayList<E>[] buckets;
    public final int size = 128;


    public implSet(int _max)
    {
        ArrayList<E> temp = new ArrayList<>();
        buckets = (ArrayList<E>[]) Array.newInstance(temp.getClass(), size);

        for(int i = 0; i < size; i++)
        {
            buckets[i] = new ArrayList<>();
        }
    }

    public int hash(E e)
    {
        // Take the parameter to hash and divide by size of set.
        int hash = e.hashCode() % size;

        //If the hash has no space add another 128 before returning
        if(hash < 0)
        {
            hash += size;
        }

        // Return the hash
        return hash;
    }
    /**
     * Add element e to the set, unless e already exists in the set or e is null.
     *
     * @param e Item to add to the set
     */
    @Override
    public void add(E e) {

        int hashedIndex = hash(e);
        ArrayList<E> bucket = buckets[hashedIndex];

        if(!contains(e))
        {
            bucket.add(e);
        }

    }

    /**
     * Remove element e from the set, unless e does not exist in the set, or e is null
     *
     * @param e Item to remove from the set
     */
    @Override
    public void remove(E e) {

        int hashedIndex = hash(e);
        ArrayList<E> bucket = buckets[hashedIndex];

        if(e == null)
        {

        }
        else
        {
            bucket.remove(e);
        }
    }

    /**
     * Test whether e is a member of this set.
     *
     * @param e item to check membership of
     * @return true if e is a member of this set, false if not or e is null.
     */
    @Override
    public boolean contains(E e) {

        int hashedIndex = hash(e);

        if(buckets[hashedIndex].contains(e))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Method to generate an iterator to iterate across the contents of the set.
     *
     * @return Iterator for this set.
     */
    @Override
    public Iterator<E> iterator() {

        ArrayList<E> temp = new ArrayList<>();

        for(int i = 0; i < size; i++)
        {
            temp.addAll(buckets[i]);
        }

        return temp.iterator();
    }

    /**
     * Check if the set is empty
     *
     * @return true if there are no items in the set, false otherwise
     */
    @Override
    public boolean isEmpty() {
         int isEmptySize = 0;

         for(int i = 0; i < size; i++)
         {
             isEmptySize += buckets[i].size();
         }

         if(isEmptySize != 0)
         {
             return false;
         }
         else
         {
             return true;
         }
    }

    /**
     * Adds all items from Set s to this set, if those items are not already in this set.
     * This is equivalent to the set union operation.
     *
     * @param s Set containing the items to be added to this set.
     */
    @Override
    public void addAll(Set<E> s) {

        for(Iterator<E> i = this.iterator(); i.hasNext();)
        {
            E tempNext = i.next();

            add(tempNext);
        }
    }

    /**
     * Removes all items from this set, which are not items contained in the provided set.
     * This is equivalent to the set intersection operation.
     *
     * @param s The set defining which items are to be kept in this set.
     */
    @Override
    public void retainAll(Set<E> s) {

        for(Iterator<E> i = this.iterator(); i.hasNext();)
        {
            E tempNext = i.next();

            if(!s.contains(tempNext))
            {
                remove(tempNext);
            }
        }
    }

    /**
     * Removes all items found in the provided set from this set.
     * This is equivalent to the set difference operation.
     *
     * @param s Set defining the items to be removed from this set.
     */
    @Override
    public void removeAll(Set<E> s) {

        for(Iterator<E> i = this.iterator(); i.hasNext();)
        {
            E tempNext = i.next();

            if(s.contains(tempNext))
            {
                remove(tempNext);
            }
        }
    }
}