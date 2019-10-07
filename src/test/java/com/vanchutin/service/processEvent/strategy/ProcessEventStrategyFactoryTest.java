package com.vanchutin.service.processEvent.strategy;

import com.vanchutin.event.ErrorEvent;
import com.vanchutin.event.RestoreEvent;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.springframework.context.ApplicationContext;

import static org.junit.Assert.*;

public class ProcessEventStrategyFactoryTest {

    ProcessEventStrategyFactory processEventStrategyFactory = new ProcessEventStrategyFactory();
    ApplicationContext applicationContext = mock(ApplicationContext.class);

    @Before
    public void init(){
        processEventStrategyFactory.setApplicationContext(applicationContext);
    }

    @Test
    public void getProcessStrategy() {
        when(applicationContext.getBean(ProcessErrorStrategy.class)).thenReturn(new ProcessErrorStrategy());
        when(applicationContext.getBean(ProcessRestoreStrategy.class)).thenReturn(new ProcessRestoreStrategy());

        Class errorStrategyClass = processEventStrategyFactory.getProcessStrategy(new RestoreEvent()).getClass();
        Class restoreStrategyClass = processEventStrategyFactory.getProcessStrategy(new ErrorEvent()).getClass();

        assertEquals(ProcessRestoreStrategy.class, errorStrategyClass);
        assertEquals(ProcessErrorStrategy.class, restoreStrategyClass);
    }
}