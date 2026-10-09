import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SmsrtRideTest {
    @Test
    public void shouldTestForcalculateFare(){
        assertEquals(334.54,SmartRide.calculateFare(3,));
    }


}
