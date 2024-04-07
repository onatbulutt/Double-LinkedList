public class LinkedList implements Interface {

    DLNode head;

    public LinkedList()
    {
        this.head=null;
    }

    @Override
    public void Insert(int newElement, int pos) throws Exception {
        DLNode newNode = new DLNode(newElement); // creating new node with element newElement
        if(pos<0) // situation that selected position is less than 0
        {
            throw LinkedListException(); // invalid position number
        }
        else if (pos==0) // situation that selected position is equal to 0
        {
            if(head==null) // if head is null, created newNode became first node.
            {
                newNode.right=head;
                head=newNode;
            }
            else // if head is not null, created newNode became first node.
            {
                newNode.right=head;
                head.left=newNode;
                head=newNode;
            }
        }
        else // situation that selected position is greater than 0
        {
            DLNode temp=head;
            int count=0;
            while(temp.right!=null && count<pos-1) // go to previous position of desired position
            {
                temp=temp.right;
                count++;
            }
            if(temp.right==null) // situation that next node of the temp is empty.
            {
                throw LinkedListException(); // invalid position number
            }
            newNode.right=temp.right;
            temp.right.left=newNode;
            temp.right=newNode;
            newNode.left=temp;
            // created newNode go to desired position and old node in this position go to next position.
        }
    }

    @Override
    public int Delete(int pos) throws Exception {
        int value;
        if(pos<0) // situation that selected position is less than 0
        {
            throw LinkedListException(); // invalid position number
        }
        else if (pos==0) // situation that position number is equal to 0.
        {
            value=head.Element;
            head=head.right;

            if (head != null) {
                head.left = null;
            }
            // delete head node
        }
        else // situation that selected position is greater than 0
        {
            DLNode temp=head;
            int count=0;
            while(temp!=null && count<=pos-1) // go to desired position
            {
                temp=temp.right;
                count++;
            }
            if(temp==null) // situation that temp node is empty.
            {
                throw LinkedListException(); // invalid position number
            }
            else
            {
                value=temp.Element;
                temp.right.left=temp.left;
                temp.left.right=temp.right;
                // delete temp node
            }
        }
        return value; // return deleted value
    }

    @Override
    public void ReverseLink() {
        DLNode current=head;
        DLNode temp=null;
        while(current!=null) // continue until current node is empty.
        {
            temp=current.left;
            current.left=current.right;
            current.right=temp;
            current=current.left;
            // change next and previous pointers of node each other
        }
        head = temp.left; // add the head node in the end of the list
    }

    @Override
    public void SquashL() {
        DLNode temp = head;
        while (temp != null) // continue until temp is empty.
        {
            int count = 1;
            while (temp.right != null && temp.Element == temp.right.Element) // continue until next node is not empty and as long as element of temp is equal to element of next of temp.
            {
                temp = temp.right; // move next node
                count++; // increment count
            }

            if (count == 1) // situation that count is equal to 1
            {
                DLNode countNode = new DLNode(1); // create countNode with a value of 1
                countNode.right = temp.right;
                temp.right = countNode;
                // move the countNode to next of the temp node
                temp = temp.right.right;
                // temp node goes right 2 times.
            }
            else // situation that count is greater than 1
            {
                DLNode countNode = new DLNode(count); // create countNode with a value of count
                countNode.right = temp.right;
                temp.right = countNode;
                // move the countNode to next of the temp node
                int i = 1;
                DLNode previous = temp.left;
                while (previous != null && temp.Element == previous.Element && i < count) // continue until previous of temp is empty, i is less than count and as long as element of temp is equal to element of previous of temp.
                {
                    previous.right = countNode;
                    countNode.left = previous;
                    previous = previous.left;
                    i++;
                    // delete nodes with the same value as the value of the temp node
                }
                temp = countNode.right; // move temp node to right
            }
        }
    }
    @Override
    public void OplashL() {
        DLNode temp = head;
        int count;
        while (temp != null && temp.right != null) // continue until temp node and next node of temp is empty
        {
            count = temp.right.Element; // assign element of the next node of the temp to count variable
            if (count == 1) // situation that count is equal to 1
            {
                temp.right = temp.right.right;
                if (temp.right != null) {
                    temp.right.left = temp;
                }
                // delete the node that shows how many elements of the temp node
            }
            if(count>1) // situation that count is greater than 1
            {

                temp.right=temp.right.right; //delete the node that shows how many elements of the temp node
                for(int i=0;i<count-1;i++)
                {
                    DLNode newMode =new DLNode(temp.Element);
                    newMode.right=temp.right;
                    temp.right=newMode;
                    temp=temp.right;
                }
                // add count-1 node to the list with the same item as the temp node
            }
            temp = temp.right;
        }
    }


    @Override
    public void Output() {
        DLNode current=head;
        System.out.print("The Elements in the list are : ");
        while(current!=null) // continue until current node is empty.
        {
            System.out.print(current.Element); // print the current node
            System.out.print(" ");
            current=current.right; //move next node
        }
        System.out.println();
    }

    @Override
    public void ROutput() {
        DLNode current =head;
        while(current != null && current.right != null)
        {
            current=current.right;
        } // go to the last element of the list
        System.out.print("The Reverse Elements in the list are : ");
        while(current!=null) // continue until current node is empty.
        {
            System.out.print(current.Element); // print the current node
            System.out.print(" ");
            current=current.left; // move previous node
        }
        System.out.println();
    }
    @Override
    public String toString()
    {
        String list=""; // created empty list
        DLNode current =head;
        while(current!=null) // continue until current node is empty.
        {
            list+=current.Element; // add current node in the list
            list+=" ";
            current=current.right; // move next node
        }
        return list;
    }
    @Override
    public Exception LinkedListException() {
        return new Exception("Invalid position");
    }
}
