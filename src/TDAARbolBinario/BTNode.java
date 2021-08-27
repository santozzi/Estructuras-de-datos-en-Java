package TDAARbolBinario;

public class BTNode<E> implements BTPosition<E> {

	protected BTPosition<E> parent;
	protected E element;
	protected BTPosition<E> lc,rc;
	

	
	public BTNode(BTPosition<E> parent, E element, BTPosition<E> lc,BTPosition<E> rc) {
		
		this.parent = parent;
		this.element = element;
		this.lc = lc;
		this.rc = rc;
	}

	public BTNode(BTPosition<E> parent, E element) {
		this(parent,element,null,null);
		
	}
	
	@Override
	public E element() {
		
		return this.element;
	}

	@Override
	public void setLeft(BTPosition<E> v) {
		this.lc= v;

	}

	@Override
	public void setRight(BTPosition<E> v) {
		this.rc= v;
	}

	@Override
	public BTPosition<E> getLeft() {
		return this.lc;
	}

	@Override
	public BTPosition<E> getRight() {
		return this.rc;
	}

	@Override
	public void setElement(E element) {
		this.element= element;

	}

	@Override
	public void setParent(BTPosition<E> p) {
		this.parent= p;

	}

	@Override
	public BTPosition<E> getParent() {
		return this.parent;
	}

}
