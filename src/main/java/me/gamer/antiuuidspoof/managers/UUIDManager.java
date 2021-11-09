package me.gamer.antiuuidspoof.managers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

public class UUIDManager {

    public UUID fetchUUID(String s) throws Exception {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + s);

        String uuid = (String)((JSONObject)(new JSONParser()).parse(new InputStreamReader(url.openStream()))).get("id");
        String realUUID = uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20, 32);
        return UUID.fromString(realUUID);
    }

}
