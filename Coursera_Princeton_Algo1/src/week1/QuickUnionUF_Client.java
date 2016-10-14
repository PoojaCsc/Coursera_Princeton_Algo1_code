package week1;

public class QuickUnionUF_Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickUnionUF obj = new QuickUnionUF(8);
		System.out.println(obj.connected(2, 3));
		obj.union(1, 2);
		obj.union(2, 6);
		obj.union(3, 4);
		System.out.println(obj.connected(1, 6));
	}

}
