package TDAABB;

public class NodoABB<E> {
    protected E rotulo;
    protected NodoABB<E> parent, left,right;
	public NodoABB( NodoABB<E> parent, E rotulo) {
		this.rotulo = rotulo;
		this.parent = parent;
		this.left=null;
		this.right=null;
	}
	public E getRotulo() {
		return rotulo;
	}
	public void setRotulo(E rotulo) {
		this.rotulo = rotulo;
	}
	public NodoABB<E> getParent() {
		return parent;
	}
	public void setParent(NodoABB<E> parent) {
		this.parent = parent;
	}
	public NodoABB<E> getLeft() {
		return left;
	}
	public void setLeft(NodoABB<E> left) {
		this.left = left;
	}
	public NodoABB<E> getRight() {
		return right;
	}
	public void setRight(NodoABB<E> right) {
		this.right = right;
	}
    
    
    
    
}
