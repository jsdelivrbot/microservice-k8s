package com.epam.testcommon.performance.utilities;

import com.epam.testcommon.utilities.ConfigUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

public class GrafanaUtilities {

    public static final String GRAFANA_AUTHPROZATION_TOKEN_ENVIRONMENT_VARIABLE_NAME = "GRAFANA_AUTHORIZATION";
    private static final Logger LOGGER = LoggerFactory.getLogger("binary_data_logger");

    public void saveGrafanaTaskBoardPNG(long startTime, long endTime) throws IOException {
        URL url = new URL(ConfigUtilities.getGrafanaURL() +
                "?from=" + startTime +
                "&to=" + endTime);

        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + System.getProperty(GRAFANA_AUTHPROZATION_TOKEN_ENVIRONMENT_VARIABLE_NAME));
        InputStream is = connection.getInputStream();
        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        os.close();
        is.close();
        LOGGER.info("RP_MESSAGE#BASE64#{}#{}", Base64.getEncoder().encodeToString(os.toByteArray()), "Grafana");
    }
}
