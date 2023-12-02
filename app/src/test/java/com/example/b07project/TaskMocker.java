package com.example.b07project;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.android.gms.tasks.Task;


     class TaskMocker {

        public static <T> Task<T> mockTask(T result) {
            Task<T> task = mock(Task.class);
            when(task.isSuccessful()).thenReturn(true);
            when(task.getResult()).thenReturn(result);
            return task;
        }

        public static <T> Task<T> mockTask(boolean success) {
            Task<T> task = mock(Task.class);
            when(task.isSuccessful()).thenReturn(success);
            return task;
        }
    }

