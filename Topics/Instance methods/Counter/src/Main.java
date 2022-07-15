class Counter {
    int current;

    public int inc() {
        this.current = current +1;
        return getCurrent();
    }

    public int getCurrent() {
        return current;
    }
}