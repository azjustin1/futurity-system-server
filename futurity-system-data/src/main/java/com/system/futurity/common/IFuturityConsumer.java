package com.system.futurity.common;

import java.util.List;

import com.system.futurity.messaging.RequestMessage;

public interface IFuturityConsumer<E, T , U, V, K> {
  public List<E> read(T request);
  public E create(U request);
  public E update(V request);
  public Boolean delete(K request);
}
