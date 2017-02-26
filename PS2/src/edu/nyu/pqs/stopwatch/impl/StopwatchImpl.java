package edu.nyu.pqs.stopwatch.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.Stopwatch;
import edu.nyu.pqs.stopwatch.api.StopwatchState;

/**
 * 
 * @author Rachit
 *
 */
public class StopwatchImpl implements Stopwatch {

  private final String id;
  private StopwatchState stopwatchState;
  private Object lock = new Object();
  private List<Long> lapTimes;
  private long startTime;

  public StopwatchImpl(String id) {
    this.id = id;
    stopwatchState = StopwatchState.RESET;
    lapTimes = new LinkedList<Long>();
    startTime = 0;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public void start() throws IllegalStateException {
    synchronized (lock) {
      if (stopwatchState == StopwatchState.RUNNING) {
        throw new IllegalStateException("Id:" + id + " is already running");
      }
      if (lapTimes.size() == 0) {
        startTime = System.currentTimeMillis();
      }
      stopwatchState = StopwatchState.RUNNING;
    }
  }

  @Override
  public void lap() throws IllegalStateException {
    synchronized (lock) {
      if (stopwatchState != StopwatchState.RUNNING) {
        throw new IllegalStateException("Id:" + id + " is not running!");
      }
      long currentTime = System.currentTimeMillis();
      long lapTime = currentTime - startTime;
      lapTimes.add(lapTime);
      startTime = currentTime;

    }
  }

  @Override
  public void stop() throws IllegalStateException {
    synchronized (lock) {
      if (stopwatchState != StopwatchState.RUNNING) {
        throw new IllegalStateException("Id:" + id + " is not running!");
      }
      lap();
      stopwatchState = StopwatchState.STOP;

    }
  }

  @Override
  public void reset() {
    synchronized (lock) {
      lapTimes.clear();
      stopwatchState = StopwatchState.RESET;

    }
  }

  @Override
  public List<Long> getLapTimes() {
    synchronized (lock) {
      return Collections.unmodifiableList(lapTimes);
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 13;
    synchronized (lock) {
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    StopwatchImpl other = (StopwatchImpl) obj;

    if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuffer output = new StringBuffer();
    output.append("Stopwatch " + this.id + ": \n");
    synchronized (lock) {
      if (lapTimes.isEmpty()) {
        output.append("Zero Laps recorded\n");
      } else {
        int lapNumber = 0;
        for (long lap : lapTimes) {
          lapNumber++;
          output.append("Lap " + lapNumber + ": " + lap + " milliseconds\n");
        }
      }
    }
    return output.toString();
  }

}
