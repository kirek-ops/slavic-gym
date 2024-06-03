package MMM.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import MMM.demo.Utils.LocationFetcher;

@SpringBootTest
class SlavicGymApplicationTests {

	@Test
	void TestLocationFetcher() {
			String city = "Krakow";
      String street = "Sliska 14";
			try {
      	Double[] ar = LocationFetcher.getLatLng(city, street);
				if (ar != null) {
					System.out.println(ar[0].toString() + " " + ar[1].toString());
				}
			} catch (Exception e) {
				System.out.println("Exception caught");
			}
	}

}
