//Noga Gottlieb
package A2;
import java.util.Objects;


public class ListNode<T> {
	
	//creating Node class 
	private class Node<T>{
		Object data;
		Node next;
	
		public Node(Object value)
		{
			data=value;
			next=null;
		}
	}
	
	private Node head;
	private int size;
	
	ListNode() {
		
		head = null;
		size = 0;
		
	}
	
	/**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param item element to be appended to this list
     * @return {@code true}
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     * @throws IllegalArgumentException      if some property of this element
     *                                       prevents it from being added to this list
     */
    public boolean add(Object item) {
    	
    	if(item==null) 
        	throw new NullPointerException("The item is null, try a different one");
  
 	
        if(head==null) 
        {
        	head= new Node(item);
        	size++;
        	return true;
        }
        
        if(head.next==null)
        {
        	Node node=new Node(item);
        	head.next=node;
        	size++;
        	return true;
        }
        
        Node prev=head;
        for(int i=0; i<size-1; i++)
        {
        	prev=prev.next;
        	
        }
        Node node=new Node(item);
        prev.next = node;
    	size++;
    	return true;
        
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param position   index at which the specified element is to be inserted
     * @param item element to be inserted
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index > size()})
     */
    public void add(int position, Object item) {
    	
    	if(position>size+1)
    		throw new IndexOutOfBoundsException("Position is not valid");
    	
    	if(position<0)
    		throw new IllegalArgumentException("Position is not valid");
    	
    	if(item==null) 
    		throw new NullPointerException("The item is null, try a different one");
    	
    	if(position==0)
        {
        	Node temp = new Node(item);
        	temp.next = head;
        	head = temp;
        	size++;
        	
        }
        
        else
        {
        	Node prev = head;
        	for(int i=0; i<position-1; i++)
        	{
        		prev = prev.next;
        		
        	}
        	Node temp = new Node(item);
    		temp.next=prev.next;
    		prev.next=temp;
    		size++;
        }
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param item element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     */
    public boolean contains(Object item) {
        
    	if(item==null) 
        	throw new NullPointerException("The item is null, try a different one");
    	
    	Node current=head;
    	while(current.next!=null ) {
    		if(current.data==item)
    			return true;
    		current=current.next;
    	}
    	return false;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param position index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    public Object get(int position) {
        
    	if(position>size || position<0)
        	throw new IndexOutOfBoundsException("Position is not exist");
        	
    	if(position==1 && head.next==null)
    		throw new IndexOutOfBoundsException("Position is not exist");
    		
        Node current=head;
        for(int i=0; i<position; i++)
        {	
        	current=current.next;
        }
        	return current.data;

    }

    /**
     * Removes the last element in this list. Returns the element that was removed from the
     * list.
     *
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
    public Object removeLast() {
    	
    	if(head==null)
    		return null;
    	
    	if(head.next==null)
    		return null;
    	
    	
    	
    	Node current=head;
    	for(int i=0; i<size-2; i++)	
        	current=current.next;
        
    	
    	Node temp = current.next;
    	current.next=null;
    	size--;
    	
    	return temp.data;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param item element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws ClassCastException            if the type of the specified element
     *                                       is incompatible with this list
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     */
    public boolean remove(Object item) {
        
    	if(item==null) 
        	throw new NullPointerException("The item is null, try a different one");
    	
    	int pos=-1;
    	
    	//run a loop to find the position of the item
    	for(int i=0; i<size; i++)
    	{
    		if(Objects.equals(item, get(i)))
    			pos=i;
    	}
    	
    	if(pos==-1)
    		return false;
    	
    	if(pos==0)
    	{
    		Node node = head;
    		head = head.next;
    		--size;
    		return true;
    	}
    	
    	else
    	{
    		Node prev = head;
    		for (int i=0; i < pos-1; i++)
    			prev = prev.next;
    		Node node = prev.next;
    		prev.next = node.next;
    		--size;
    		return true;
    	}
    	
    	
    	
    }
    	

    /**
     * Removes the element at the specified position from the end of the list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the head of the list.
     *
     * @param position the index of the item the end of the list to be removed
     * @return the head of the list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
    public Object removeFromEnd(int position){
    	//return the removed node.data
    	
    	if(position>size || position<0)
        	throw new IndexOutOfBoundsException("Position is not exist");
        	
    	if(position==1 && head.next==null)
    		throw new IndexOutOfBoundsException("Position is not exist");
    	
    	if(position==0)
    		return null;
    	
    	else
    	{
    		int pos=size-position;  //finding the position of the item (from the beginning)
    		Node prev = head;
    		
    		if(pos==0)				//if the new position is 0, return the head
    		{
    			Node node = head;
        		head = head.next;
        		--size;
    			return node.data;
    		}
    		for (int i=0; i < pos-1; i++) 	
    			prev = prev.next;
    		Node node = prev.next;
    		prev.next = node.next;
    		--size;
    		return node.data;
    	}
    	
    }

    /**
     * Shows ListNode as a String, with each object in parentheses separated by “arrows” (->).
     * A ListNode of {1, 2, 3} should return the String "(1) -> (2) -> (3)".
     * @return String representation of the ListNode
     */
    @Override
    public String toString() {
        
    	StringBuilder result = new StringBuilder();
    	
        if(size==0)
    		return result.toString();
    	
    	if(size==1)
    	{
    		result.append("[("+ head.data +")]");
    		return result.toString();
    	}
        
    	else 
    	{
    	Node current=head;
    	result.append("[("+ current.data +")" + " -> " );
    	current=current.next;
        for(int i=1; i<size-1; i++)
        {
        	
        	result.append("("+ current.data +")" + " -> " );  
        	current=current.next;
        }
        result.append("("+ current.data +")]" );
        return result.toString();
    	
    	}
    }
    	

    public static void main(String[] args){
        System.out.println("Hello gradle!");
        ListNode<String> lst = new ListNode<>();
    }

}

