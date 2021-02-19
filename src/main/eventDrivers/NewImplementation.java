package main.eventDrivers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NewImplementation {
    public static void main(String[] args) {
        PCLNewsAgency observable = new PCLNewsAgency();
        PCLNewsChannel observer = new PCLNewsChannel();

        observable.addPropertyChangeListener(observer);

        observable.setNews("newsName");

        System.out.println(observer.getNews());
    }

    /*
        private final PropertyChangeSupport support;

        public void addListener(PropertyChangeListener listener) {
            support.addPropertyChangeListener(listener);
        }

        support = new PropertyChangeSupport(this);

     */


    public static class PCLNewsAgency {
        private final PropertyChangeSupport support;
        private String news;

        public PCLNewsAgency() {
            support = new PropertyChangeSupport(this);
        }

        public void addPropertyChangeListener(PropertyChangeListener pcl) {
            support.addPropertyChangeListener(pcl);
        }

        public void removePropertyChangeListener(PropertyChangeListener pcl) {
            support.removePropertyChangeListener(pcl);
        }

        public void setNews(String value) {
            support.firePropertyChange("news", this.news, value);
            this.news = value;

        }
    }

    public static class PCLNewsChannel implements PropertyChangeListener {

        private String news;

        public void propertyChange(PropertyChangeEvent evt) {
            this.setNews((String) evt.getNewValue());
        }

        public String getNews() {
            return news;
        }

        public void setNews(String news) {
            this.news = news;
        }
    }

}
