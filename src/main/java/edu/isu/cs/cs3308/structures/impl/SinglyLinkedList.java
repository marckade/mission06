package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.List;

public class SinglyLinkedList<E> implements List<E>
{
    public class Node<E>
    {
        private E data;
        private Node<E> next;

        public Node(E data)
        {
            this.data = data;
        }

        public E getData()
        {
            return data;
        }

        public void setData(E _data)
        {
            data = _data;
        }

        public Node<E> getNext()
        {
            return next;
        }

        public void setNext(Node<E> node)
        {
            next = node;
        }
    }


    protected Node<E> head;
    protected Node<E> tail;
    protected int size = 0;


    // Properties
    @Override
    public E first()
    {
        if (isEmpty())
            return null;

        return head.getData();
    }

    @Override
    public E last()
    {
        if (isEmpty())
            return null;

        return tail.getData();
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    // Methods
    @Override
    public void addLast(E element)
    {
        if (element == null)
            return;

        Node<E> newNode = new Node<>(element);

        if (isEmpty())
            head = newNode;
        else
            // tail is null if isEmpty()
            tail.setNext(newNode);

        tail = newNode;
        size++;
    }

    @Override
    public void addFirst(E element)
    {
        // from class 01-15
        if (element == null)
            return;

        Node<E> newNode = new Node<>(element);
        newNode.setNext(head); // if empty, sets next == null
        head = newNode;

        // node is both head & tail if alone
        if (size == 0)
            tail = newNode;

        size++;
    }

    @Override
    public E removeFirst()
    {
        if (isEmpty())
            return null;

        E temp = head.getData();
        head = head.getNext();

        if (head == null)
            tail = null;

        size--;
        return temp;
    }

    @Override
    public E removeLast()
    {
        if (isEmpty())
            return null;

        E temp = tail.getData();
        Node<E> current = head;

        if (size == 1)
            return removeFirst();

        for (int i = 0; i < size - 2; i++)
            current = current.getNext();

        current.setNext(null);
        tail = current;
        size--;
        return temp;
    }

    @Override
    public void insert(E element, int index)
    {
        if (element == null || index < 0)
            return;

        // special case
        if (index == 0)
        {
            addFirst(element);
            return;
        }
        if (index >= size)
        {
            addLast(element);
            return;
        }

        // Get node at index - 1, insert after
        Node<E> current = head;
        for (int i = 0; i < index - 1; i++)
            current = current.getNext();

        Node<E> newNode = new Node<>(element);
        newNode.setNext(current.getNext());
        current.setNext(newNode);

        size++;
    }

    @Override
    public E remove(int index)
    {
        if (isEmpty() || index < 0 || index >= size)
            return null;

        // Loop-logic breaks with these 2 conditions
        if (index == 0)
            return removeFirst();
        //else if (index == size - 1)
        //    return removeLast();

        Node<E> current = head;

        // Get node at index - 1
        for (int i = 0; i < index - 1; i++)
            current = current.getNext();

        Node<E> toRemove = current.getNext();
        current.setNext(toRemove.getNext());
        toRemove.setNext(null);
        size--;
        return toRemove.getData();
    }

    public E remove(E item)
    {
        if (isEmpty() || item == null)
            return null;

        Node<E> current = head;

        if (current == item)
            removeFirst();

        while (current.getNext() != null)
        {
            if (current.getNext().getData() == item)
            {
                Node<E> toRemove = current.getNext();
                current.setNext(toRemove.getNext());
                toRemove.setNext(null);
                size--;
                return toRemove.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public E get(int index)
    {
        if (isEmpty() || index < 0 || index >= size)
            return null;

        Node<E> current = head;

        for (int i = 0; i < index; i++)
            current = current.getNext();

        return current.getData();
    }

    public boolean contains(E element)
    {
        Node<E> current = head;

        for (int i = 0; i < size; i++){
            if (current.getData().equals(element))
                return true;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void printList()
    {
        if (isEmpty())
            return;

        Node<E> current = head;
        for (int i = 0; i < size; i++)
        {
            System.out.println(current.getData().toString());
            current = current.getNext();
        }
    }


}