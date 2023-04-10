public class List<Arbitrary extends Base> {
    protected static final short END = 0;
    protected static final short FRONT = 1;
    private List<Arbitrary>.Node end = null;
    private long occupancy = 0L;

    public List() {
    }

    public Arbitrary insert(Arbitrary element, long where) {
        List<Arbitrary>.Node new_node = new List.Node(element);
        Arbitrary retval = new_node.data;
        if (this.occupancy == 0L) {
            this.end = new_node.next = new_node.pre = new_node;
        } else {
            new_node.next = this.end.next;
            new_node.pre = this.end;
            this.end.next.pre = new_node;
            this.end.next = new_node;
        }

        if (where == 0L) {
            this.end = this.end.next;
        }

        ++this.occupancy;
        return retval;
    }

    public Arbitrary remove(long where) {
        if (this.occupancy == 0L) {
            System.err.print("Popping from an empty list!!!\n");
            return null;
        } else {
            if (where == 0L) {
                this.end = this.end.pre;
            }

            List<Arbitrary>.Node old_node = this.end.next;
            Arbitrary retval = old_node.data;
            old_node.next.pre = this.end;
            this.end.next = old_node.next;
            --this.occupancy;
            return retval;
        }
    }

    public Arbitrary view(long where) {
        if (this.occupancy == 0L) {
            return null;
        } else {
            return where == 0L ? this.end.data : this.end.next.data;
        }
    }

    public boolean isEmpty() {
        return this.size() == 0L;
    }

    public long size() {
        return this.occupancy;
    }

    public String toString() {
        String temp = "";
        List<Arbitrary>.Node current = this.end.next;

        for(long count = 1L; count <= this.occupancy; ++count) {
            temp = temp + "" + current;
            current = current.next;
        }

        return temp;
    }

    private class Node {
        public Arbitrary data;
        public List<Arbitrary>.Node pre;
        public List<Arbitrary>.Node next;

        public Node(Arbitrary element) {
            this.data = element;
            this.pre = null;
            this.next = null;
        }

        public String toString() {
            return "" + this.data;
        }
    }
}
