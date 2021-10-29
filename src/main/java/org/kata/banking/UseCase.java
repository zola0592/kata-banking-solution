package org.kata.banking;

public interface UseCase<R, C> {
    R perform(C command);
}
