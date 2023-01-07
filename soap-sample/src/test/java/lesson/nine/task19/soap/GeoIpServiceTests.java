package lesson.nine.task19.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {
    @Test
    public void testMyIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("178.134.234.228");
        assertEquals(geoIp, "<GeoIP><Country>GE</Country><State>13</State></GeoIP>");
    }

    @Test(alwaysRun = false)
    public void testInvalidIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("178.134.234.xxx");
        assertEquals(geoIp, "<GeoIP><Country>GE</Country><State>13</State></GeoIP>");
    }
}
