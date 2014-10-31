package utils.extract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;

/**
 * Created by joaovictorchenccimarques on 30/10/14.
 */

public class VectorExtractorHelper {

    private enum Vectors {
        A("Carrapato-estrela"),
        B("Mosquito-palha"),
        C("Aedes_aegypti"),
        D("Anopheles");

        private String message;

        private Vectors(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }

	public static LinkedHashMap getVectorData() throws IOException {
		LinkedHashMap res = new LinkedHashMap();

		//Each URL
		String baseUrl = "http://pt.wikipedia.org/wiki/";

        for (Vectors s : Vectors.values()) {
            String address = baseUrl + s.getMessage();
            LinkedHashMap entry = getSingleVector(address);

            res.put(entry.get("name"), entry);
        }

        return res;
	}

    private static LinkedHashMap getSingleVector(String address) throws IOException {
        LinkedHashMap res = new LinkedHashMap();

        URL url = new URL(address);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line = null;

            Boolean scientificName = true;
            Boolean popularName = false;

            while ((line = reader.readLine()) != null) {

                if (scientificName) {

                    int nameLine = line.indexOf("margin:-2px");

                    if (nameLine != -1) {

                        int start = line.indexOf("title=\"");
                        int end = line.indexOf("\"><img");

                        if (start != -1) {;
                            res.put("name", line.substring(start + "title=\"".length()+2, end));
                            scientificName = false;
                            popularName = true;
                            continue;
                        }
                    }
                }

                if (popularName) {

                    int populationLine = line.indexOf("<p>");
                    int count = 0;

                    if (populationLine != -1) {

                        int start = line.indexOf("<span style=\"white-space:nowrap;\">");
                        int end = line.indexOf("</span>");

                        if (start != -1) {
                            res.put("population", line.substring(start + "<span style=\"white-space:nowrap;\">".length(), end));
//                            population = false;
//                            climate = true;
                            continue;
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
