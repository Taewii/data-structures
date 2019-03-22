import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p02_vani_planning.main.Agency;
import p02_vani_planning.main.AgencyImpl;

public class CTest05 {

    private Agency agency;

    @Before
    public void setup() {
        this.agency = new AgencyImpl();
    }

    @Test
    public void test_contains_with_incorrect_data() {

        final boolean expectedContains = this.agency.contains("5");

        Assert.assertFalse("Incorrect contains behavior", expectedContains);
    }
}
