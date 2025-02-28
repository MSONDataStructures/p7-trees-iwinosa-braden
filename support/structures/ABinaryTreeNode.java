package structures;

/**
 * A concrete implementation of the BinaryTreeNode interface.
 *
 * @param <T> the type of data stored in this BinaryTreeNode
 */
public class ABinaryTreeNode<T> implements BinaryTreeNode<T> {

	private T data;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;

	/**
	 * Constructor to create a BinaryTreeNode with the specified data.
	 *
	 * @param data the data for this node
	 * @throws NullPointerException if the data is null
	 */
	public void BinaryTreeNodeImpl(T data) {
		if (data == null) {
			throw new NullPointerException("Data cannot be null.");
		}
		this.data = data;
		this.left = null;
		this.right = null;
	}

	@Override
	public T getData() {
		return data;
	}

	@Override
	public void setData(T data) {
		if (data == null) {
			throw new NullPointerException("Data cannot be null.");
		}
		this.data = data;
	}

	@Override
	public boolean hasLeftChild() {
		return left != null;
	}

	@Override
	public boolean hasRightChild() {
		return right != null;
	}

	@Override
	public BinaryTreeNode<T> getLeftChild() {
		if (left == null) {
			throw new IllegalStateException("This node does not have a left child.");
		}
		return left;
	}

	@Override
	public BinaryTreeNode<T> getRightChild() {
		if (right == null) {
			throw new IllegalStateException("This node does not have a right child.");
		}
		return right;
	}

	@Override
	public void setLeftChild(BinaryTreeNode<T> left) {
		this.left = left;
	}

	@Override
	public void setRightChild(BinaryTreeNode<T> right) {
		this.right = right;
	}
}
