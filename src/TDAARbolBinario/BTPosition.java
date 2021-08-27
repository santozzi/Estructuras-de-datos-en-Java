package TDAARbolBinario;

import TDALista.Position;

public interface BTPosition<E> extends Position<E>{
  public void setLeft(BTPosition<E> v);
  public void setRight(BTPosition<E> v);
  public BTPosition<E> getLeft();
  public BTPosition<E> getRight();
  public void setElement(E element);
  public void setParent(BTPosition<E> p);
  public BTPosition<E> getParent();
}
