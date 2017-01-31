package org.geotools.mbstyle.parse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.geotools.mbstyle.MBFormatException;
import org.geotools.mbstyle.MBStyle;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Given JSON input (as a {@link String} or {@link Reader}, parses and returns a {@link MBStyle}
 *
 * @author tbarsballe
 */
public class MBStyleParser {

    JSONParser parser;

    public MBStyleParser() {
        parser = new JSONParser();
    }

    public MBStyle parse(String json) throws ParseException, MBFormatException {
        return new MBStyle(asJSONObject(parser.parse(json)));
    }

    public MBStyle parse(Reader json) throws ParseException, IOException, MBFormatException {
        return new MBStyle(asJSONObject(parser.parse(json)));
    }

    public MBStyle parse(InputStream json) throws ParseException, IOException, MBFormatException {
        return new MBStyle(asJSONObject(parser.parse(new InputStreamReader(json))));
    }

    private JSONObject asJSONObject(Object obj) throws MBFormatException {
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        throw new MBFormatException("Invalid MapBox Style JSON - Root must be a JSON Object:" + (obj == null ? "null" : obj.toString()));
    }
}