package com.alex.post.boy.frame.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;

import java.text.MessageFormat;
import javax.swing.*;

/**
 * @author Aliaksandr_Shynkevich
 */
public final class ResponseUtil {

    private static final Gson GSON_PARSER = new GsonBuilder().setPrettyPrinting().create();

    private ResponseUtil() {
    }

    public static String getPrettyJson(String json) {
        if (StringUtils.isBlank(json)) {
            return StringUtils.EMPTY;
        }
        JsonElement parser = new JsonParser().parse(json);
        return GSON_PARSER.toJson(parser);
    }

    public static JComponent wrapForScroll(JComponent component) {
        JScrollPane scrollPane = new JScrollPane(component);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    public static String extractStatus(HttpResponse httpResponse) {
        return MessageFormat.format("<html><b>Http code:</b> <font color='blue' size='4'>{0}</font><html>",
                httpResponse.getStatusLine().getStatusCode());
    }
}
