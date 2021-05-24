package com.minesweeper.eventDrivers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NewImplementation {
    public static void main(String[] args) {
        Sender sender = new Sender();
        Receiver receiver = new Receiver();

        sender.addPropertyChangeListener(receiver);

        sender.fire();
    }

    public static class Sender {
        private final PropertyChangeSupport support;

        public Sender() {
            support = new PropertyChangeSupport(this);
        }

        public void addPropertyChangeListener(PropertyChangeListener pcl) {
            support.addPropertyChangeListener(pcl);
        }

        public void removePropertyChangeListener(PropertyChangeListener pcl) {
            support.removePropertyChangeListener(pcl);
        }

        public void fire() {
            support.firePropertyChange("news", null, "new val");
        }

    }

    public static class Receiver implements PropertyChangeListener {

        public void propertyChange(PropertyChangeEvent evt) {
            System.out.println((String) evt.getNewValue());
        }

    }
}
