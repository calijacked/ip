    package main.java;

    public abstract class Task {
        protected boolean isDone;
        protected String description;
        protected TaskType type;

        public Task(String description, TaskType type) {
            this.description = description.trim();
            this.type = type;
            this.isDone = false;
        }

        public void markDone() {
            isDone = true;
        }

        public void markUndone() {
            isDone = false;
        }

        protected String getStatusIcon() {
            return isDone ? "1" : "0";
        }

        public String toString() {
            return "[" + type.getSymbol() + "]" + (isDone ? "[X] " : "[ ] ") + description;
        }

        public abstract String toFileFormat();

    }
