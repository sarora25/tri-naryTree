import java.util.ArrayList;

/*
 * Implement insert and delete in a tri-nary tree.  A tri-nary tree is much like a binary 
tree but with three child nodes for each parent instead of two -- with the left node being values 
less than the parent, the right node values greater than the parent, and the middle nodes values 
equal to the parent.
 * For example, suppose I added the following nodes to the tree in this order: 5, 4, 9, 5, 7, 2, 2.
 */
public class question2 {
	
	public static triNode insert(triNode root, int value){
		/* Base Case: An empty tree, with no nodes. Create a node with the given value and make it the root node*/
		if(root == null){
			triNode node = new triNode(value);
			node.left = null;
			node.right = null;
			node.mid = null;
			return node;
		}
		/*Compare the value of the node to be inserted with the value of the root node. 
		 * Use Recursion to insert the node at the correct position.*/
		if(value == root.data)
			root.mid = insert(root.mid,value);
		else if(value < root.data)
			root.left = insert(root.left,value);
		else root.right = insert(root.right,value);
		return root;
	}
	
	public static triNode delete(triNode root, int value){
		triNode node = root;
		/*In case of an empty tree*/
		if(node == null){
			return null;
		}	
		/*If the node to be deleted is in the right subtree*/
		if(value > node.data){
			  node.right = delete(node.right, value);
			  return node;
		}
		/*If the node to be deleted lies in the left subtree*/
		else if(value < node.data){
				node.left = delete(node.left, value);
				return node;
			}
		/*If the node to be deleted has a middle node*/
		else if(node.mid != null){
			node.mid = delete(node.mid,value);
			return node;
		}
		/*Delete the key here*/
		/*If the node has the right child*/
		else if(node.right != null){
			//Delete the node and replace with the smallest element in the right subtree
			triNode next = min(node.right);
			/*Point the child nodes to the new parent*/
			if(node.left != null){
				next.left = node.left;
			}
			/*If there is only one element in the right subtree, it is already been replaced as the new parent*/
			if(next != node.right){
				next.right = node.right;
			}
			return next;
		}
		/*If the node has only left child*/
		else if(node.left !=null && node.right == null){
			triNode next = node.left;
			node =next;
			return node;
		}
		/*Base case: No children*/
		else {
			node = null;
			return node;
		}
	}
	/*The leftmost child in the right subtree is the element with minimum value*/
	public static triNode min(triNode node){
		if(node.mid != null)
			return node.mid;
		else{
			triNode nodetemp = node;
			triNode noderight = null;
			if(node.left == null){
				return node;
			}
			else{
				/*Traverse till the leaf node in the left and use it to replace the deleted node*/
				while(node.left != null){
					nodetemp = node;
					node = node.left;
				}
				/*If the leftmost node has a right child, remove the leftmost node to replace the 
				 * deleted node and the right child will replace the leftmost node*/
				if(node.right != null){
					noderight = node.right;
					node.right = null;
					nodetemp.left = noderight;
					return node;
				}
				//else make the left node as null
				nodetemp.left = null;
				return node;
			}
		}
	}

	public static void main(String[] args){
		triNode root = new triNode(10);
		insert(root,10);
		insert(root,6);
		insert(root,14);
		insert(root,5);
		insert(root,7);
		insert(root,12);
		insert(root,15);
		insert(root,11);
		insert(root,12);
		insert(root,13);
		insert(root,8);
		root = delete(root,10);
		root = delete(root,12);
		root = delete(root,12);
		insert(root,12);
		insert(root,9);
		insert(root,10);
		root = delete(root,6);
		root = delete(root,7);
		/*root = delete(root,7);
		
		root = delete(root,11);
		root = delete(root,14);
		root = delete(root,5);
		root = delete(root,15);
		
		root = delete(root,12);*/
		System.out.println(root.data);
		System.out.println(root.left.data);
		System.out.println(root.right.data);
		System.out.println(root.mid.data);
		System.out.println(root.left.left.data);
		System.out.println(root.left.right.data);
		System.out.println(root.right.left.data);
		System.out.println(root.right.left.left.data);
		System.out.println(root.right.left.left.right.data);
		System.out.println(root.right.right.data);
		//System.out.println(root.right.left.right.data);
		System.out.println(root.left.right.right.data);
		System.out.println(root.left.right.right.right.data);
		System.out.println(root.right.left.left.left.data);
	}
	public static ArrayList<triNode> getChildren(triNode node){
		ArrayList<triNode> list = new ArrayList<triNode>();
		if(node.left != null){
			list.add(node.left);
		}
		if(node.mid != null){
			list.add(node.mid);
		}
		if(node.right != null){
			list.add(node.right);
		}
		return list;
	}
	public static void inorder(triNode root){
		ArrayList<triNode> list = new ArrayList<triNode>();
		ArrayList<triNode> children = new ArrayList<triNode>();
		if(root == null)
			System.out.println("Empty");
		list.add(root);
		int len = list.size();
		for(int i=0; i<len; i++){
			triNode top = list.get(i);
			children = getChildren(top);
			list.remove(top);
			list.addAll(0, children);
			print(list);
			System.out.println();
		}
	}
	public static void print(ArrayList<triNode> node){
		ArrayList<triNode> nodes = node;
		int size = nodes.size();
		for(int i=0; i<size; i++){
			triNode item = (triNode) nodes.get(i);
			int d = item.data;
			System.out.print(d);
			//nodes.remove(0);
		}
	}

}
