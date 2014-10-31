package utils.extract;

import utils.validation.ValidationHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;

/**
 * Created by joaovictorchenccimarques on 30/10/14.
 */

public class LocationExtractorHelper {

    private enum States {
        AC("Acre"),
        AL("Alagoas"),
        AP("Amapá"),
        AM("Amazonas"),
        BA("Bahia"),
        CE("Ceará"),
        ES("Espírito_Santo_(estado)"),
        GO("Goiás"),
        MA("Maranhão"),
        MT("Mato_Grosso"),
        MS("Mato_Grosso_do_Sul"),
        MG("Minas_Gerais"),
        PA("Pará"),
        PB("Paraíba"),
        PR("Paraná"),
        PE("Pernambuco"),
        PI("Piauí"),
        RJ("Rio_de_Janeiro"),
        RN("Rio_Grande_do_Norte"),
        RS("Rio_Grande_do_Sul"),
        RO("Rondônia"),
        RR("Roraima"),
        SC("Santa_Catarina"),
        SP("São_Paulo"),
        SE("Sergipe"),
        TO("Tocantins");

        private String message;

        private States(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }

	public static LinkedHashMap getLocationData() throws IOException {
		LinkedHashMap res = new LinkedHashMap();

		//Each URL
		String baseUrl = "http://pt.wikipedia.org/wiki/";

        for (States s : States.values()) {
            String address = baseUrl + s.getMessage();
            LinkedHashMap entry = getSingleLocation(address);

            res.put(entry.get("name"), entry);
        }

        return res;
	}

    private static LinkedHashMap getSingleLocation(String address) throws IOException {
        LinkedHashMap res = new LinkedHashMap();

        URL url = new URL(address);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line = null;

            Boolean name = true;
            Boolean population = false;
            Boolean climate = false;

            while ((line = reader.readLine()) != null) {

                if (name) {

                    int nameLine = line.indexOf("Estado d");

                    if (nameLine != -1) {

                        int start = line.indexOf("Estado d");
                        int end = line.indexOf("</b>");

                        if (start != -1) {;
                            res.put("name", line.substring(start + "Estado d".length()+2, end));
                            name = false;
                            population = true;
                            continue;
                        }
                    }
                }

                if (population) {

                    int populationLine = line.indexOf("hab.");

                    if (populationLine != -1) {

                        int start = line.indexOf("<span style=\"white-space:nowrap;\">");
                        int end = line.indexOf("</span>");

						String populationTemp = line.substring(start + "<span style=\"white-space:nowrap;\">".length(), end);
						String delims = "&#160;";
						String[] populationPart = populationTemp.split(delims);
						String populationRes = "";

						for (String part : populationPart) {
							populationRes = populationRes + part;
						}

                        if (start != -1) {
                            res.put("population", populationRes);
                            population = false;
                            climate = true;
                            continue;
                        }
                    }
                }

                if (climate) {

                    int climateLine = line.indexOf("Clima");

                    if (climateLine != -1) {

                        line = reader.readLine();

                        int start = line.indexOf("title");
                        int end = line.indexOf("</a>");

                        String climateTemp = line.substring(start + "title".length(), end);

                        int startTemp = climateTemp.indexOf("\">");

						String climateRes = climateTemp.substring(startTemp + ">".length(), climateTemp.length());

                        if (start != -1) {
                            climateRes = ValidationHelper.isClimate(climateRes.toLowerCase());
                            res.put("climate", climateRes);
                            break;
                        }
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null)
                reader.close();
        }
        return res;
    }
}
