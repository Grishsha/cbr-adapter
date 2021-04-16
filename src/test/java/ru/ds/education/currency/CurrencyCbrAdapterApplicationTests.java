package ru.ds.education.currency;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ds.education.currency.controller.JmsController;

import javax.xml.datatype.DatatypeConfigurationException;

@SpringBootTest
class CurrencyCbrAdapterApplicationTests {

	@Test
	public void cbrTest() throws JSONException, DatatypeConfigurationException, JsonProcessingException {
		String request = "{\"onDate\":\"2020-03-03\"}";

		String actual = JmsController.getMessage(request);

		String expectedResponse = "{\"onDate\":\"2020-03-03\",\"rates\":[" +
				 "{\"currency\":\"AUD\",\"curs\":43.5373},{\"currency\":\"AZN\",\"curs\":39.0966}" +
				",{\"currency\":\"GBP\",\"curs\":84.8195},{\"currency\":\"AMD\",\"curs\":13.8688}" +
				",{\"currency\":\"BYN\",\"curs\":29.6860},{\"currency\":\"BGN\",\"curs\":37.6006}" +
				",{\"currency\":\"BRL\",\"curs\":14.8264},{\"currency\":\"HUF\",\"curs\":21.8312}" +
				",{\"currency\":\"HKD\",\"curs\":85.2746},{\"currency\":\"DKK\",\"curs\":98.4071}" +
				",{\"currency\":\"USD\",\"curs\":66.3274},{\"currency\":\"EUR\",\"curs\":73.4178}" +
				",{\"currency\":\"INR\",\"curs\":92.0013},{\"currency\":\"KZT\",\"curs\":17.4076}" +
				",{\"currency\":\"CAD\",\"curs\":49.7580},{\"currency\":\"KGS\",\"curs\":94.9199}" +
				",{\"currency\":\"CNY\",\"curs\":95.3363},{\"currency\":\"MDL\",\"curs\":37.7289}" +
				",{\"currency\":\"NOK\",\"curs\":71.2317},{\"currency\":\"PLN\",\"curs\":17.0578}" +
				",{\"currency\":\"RON\",\"curs\":15.2733},{\"currency\":\"XDR\",\"curs\":91.0861}" +
				",{\"currency\":\"SGD\",\"curs\":47.7553},{\"currency\":\"TJS\",\"curs\":68.5200}" +
				",{\"currency\":\"TRY\",\"curs\":10.6689},{\"currency\":\"TMT\",\"curs\":18.9778}" +
				",{\"currency\":\"UZS\",\"curs\":69.6259},{\"currency\":\"UAH\",\"curs\":26.8030}" +
				",{\"currency\":\"CZK\",\"curs\":28.9665},{\"currency\":\"SEK\",\"curs\":69.6819}" +
				",{\"currency\":\"CHF\",\"curs\":68.8258},{\"currency\":\"ZAR\",\"curs\":42.7913}" +
				",{\"currency\":\"KRW\",\"curs\":55.7411},{\"currency\":\"JPY\",\"curs\":61.2922}]}";

		JSONAssert.assertEquals(expectedResponse, actual,true);

	}
}
