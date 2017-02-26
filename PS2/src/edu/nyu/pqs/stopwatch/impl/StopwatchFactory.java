package edu.nyu.pqs.stopwatch.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import edu.nyu.pqs.stopwatch.api.Stopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for Stopwatch objects. It maintains
 * references to all created Stopwatch objects and provides a convenient method for getting a list
 * of those objects.
 *
 */
public class StopwatchFactory {
  // Used a CopyOnWriteArrayList to store the stopWatchList since it's a thread safe version
  // of the ArrayList
  private static final CopyOnWriteArrayList<Stopwatch> stopWatchList =
      new CopyOnWriteArrayList<Stopwatch>();
  // Similarly, a CopyOnWriteArraySet to store the usedID's , which are used to check if the
  // Id's already exist,while creating new Stopwatch Object. The main purpose of creating a
  // seperate usedIds Array was that at many times, the stopwatch could be in the process of
  // being used by another thread, and creating another new StopwatchObject should not wait
  // for all existing Stopewatch Object's getId() method to be called to check if Id exists.
  private static final CopyOnWriteArraySet<String> usedIds = new CopyOnWriteArraySet<String>();
  private static final Object lock = new Object();

  /**
   * Suppress default constructor so that different instances of StopwatchFactory cannot be created
   * this making it non instantiable
   */
  private StopwatchFactory() {
    throw new AssertionError();
  }

  /**
   * Creates and returns a new Stopwatch object
   * 
   * @param id The identifier of the new object
   * @return The new Stopwatch object
   * @throws IllegalArgumentException if <code>id</code> is empty, null, or already taken.
   */
  public static Stopwatch getStopwatch(String id) throws IllegalArgumentException {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("Id is null or blank");
    }
    synchronized (lock) {
      if (usedIds.contains(id)) {
        throw new IllegalArgumentException("Id:" + id + " is already being used");
      }
      Stopwatch stopwatchObject = new StopwatchImpl(id);
      usedIds.add(id);
      stopWatchList.add(stopwatchObject);
      return stopwatchObject;
    }
  }

  /**
   * Returns a list of all created stopwatches
   * 
   * @return a List of all creates Stopwatch objects. Returns an empty list if no Stopwatches have
   *         been created.
   */
  public static List<Stopwatch> getStopwatches() {
    List<Stopwatch> stopWatches;
    synchronized (lock) {
      stopWatches = stopWatchList.subList(0, stopWatchList.size());
    }
    return stopWatches;
  }

}
