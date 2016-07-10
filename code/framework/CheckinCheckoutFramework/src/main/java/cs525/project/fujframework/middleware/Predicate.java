/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.middleware;
/**
 * This Generic Type predicate interface will be implemented by the user 
 * to sort the list of person based on first name or last name
 * 
 * @author Fish
 * @since 1.0.0
 * @param <T>
 */

public interface Predicate <T>{
 /**
  *  predicate interface implemented by user  for sorting 
  * @param t
  * @return 
  */
  public boolean apply(T t);
}
