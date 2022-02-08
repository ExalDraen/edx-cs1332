package org.alnx.edx.mod9;

import java.util.Comparator;

public class CountingComparator<T> implements Comparator<T> {
    private final Comparator<T> delegate;
    private int compareCount = 0;

    public CountingComparator(Comparator<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public int compare(T o1, T o2) {
        compareCount++;
        return delegate.compare(o1,o2);
    }

    @Override
    public boolean equals(Object obj) {
        return delegate.equals(obj);
    }

    public int count() {
        return compareCount;
    }

    public void clearCount() {
        compareCount = 0;
    }
}
