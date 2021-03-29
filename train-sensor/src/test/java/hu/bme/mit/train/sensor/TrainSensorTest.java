package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController trainController;
    TrainSensor sensor;
    TrainUser trainUser;

    @Before
    public void before() {
        // TODO Add initializations
        trainController = mock(TrainController.class);
        trainUser = mock(TrainUser.class);
        sensor = new TrainSensorImpl(trainController, trainUser);
    }

    @Test
    public void OverAbsoluteMargin() {
        sensor.overrideSpeedLimit(501);
        Mockito.verify(trainUser, times(1)).setAlarmState(true);
    }

    @Test
    public void UnderAbsoluteMargin() {
        sensor.overrideSpeedLimit(-1);
        Mockito.verify(trainUser, times(1)).setAlarmState(true);

    }

    @Test
    public void SetLimitToLessThanHalf() {
        sensor.overrideSpeedLimit(500);
        when(trainController.getReferenceSpeed()).thenReturn(400);
        sensor.overrideSpeedLimit(50);
    }

    @Test
    public void isAlarmTrue(){
        when(trainController.getReferenceSpeed()).thenReturn(5);
        sensor.overrideSpeedLimit(500);
        when(trainUser.getAlarmState()).thenReturn(true);
    }
}
