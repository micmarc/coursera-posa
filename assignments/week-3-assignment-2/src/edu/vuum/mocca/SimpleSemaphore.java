package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

/**
 * @class SimpleSemaphore
 *
 * @brief This class provides a simple counting semaphore
 *        implementation using Java a ReentrantLock and a
 *        ConditionObject.  It must implement both "Fair" and
 *        "NonFair" semaphore semantics, just liked Java Semaphores. 
 */
public class SimpleSemaphore {
    /**
     * Constructor initialize the data members.  
     */
    public SimpleSemaphore (int permits,
                            boolean fair)
    { 
        // TODO - you fill in here
    	mAvailablePermits = permits;
    	mLock = new ReentrantLock(fair);
    	mPermitsNotZero = mLock.newCondition();
    }

    /**
     * Acquire one permit from the semaphore in a manner that can
     * be interrupted.
     */
    public void acquire() throws InterruptedException {
        // TODO - you fill in here
    	mLock.lockInterruptibly();
    	try {
	    	while (mAvailablePermits == 0) {
	    		mPermitsNotZero.await();
	    	}
	    	--mAvailablePermits;
    	} finally {
    		mLock.unlock();
    	}
    }

    /**
     * Acquire one permit from the semaphore in a manner that
     * cannot be interrupted.
     */
    public void acquireUninterruptibly() {
        // TODO - you fill in here
    	mLock.lock();
    	try {
	    	while (mAvailablePermits == 0) {
	    		mPermitsNotZero.awaitUninterruptibly();
	    	}
	    	--mAvailablePermits;
    	} finally {
    		mLock.unlock();
    	}
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        // TODO - you fill in here
    }
    
    /**
     * Return the number of permits available.
     */
    public int availablePermits(){
    	// TODO - you fill in here
    	return mAvailablePermits; 
    }
    
    /**
     * Define a ReentrantLock to protect the critical section.
     */
    // TODO - you fill in here
    private ReentrantLock mLock;

    /**
     * Define a ConditionObject to wait while the number of
     * permits is 0.
     */
    // TODO - you fill in here
    private Condition mPermitsNotZero;

    /**
     * Define a count of the number of available permits.
     */
    // TODO - you fill in here
    private int mAvailablePermits;
}

