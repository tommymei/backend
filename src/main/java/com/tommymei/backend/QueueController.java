package com.tommymei.backend;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Endpoint to simulate health state of the system
 * 
 * @author tommymei
 *
 */
@Controller
@RequestMapping("queue")
public class QueueController {

    private final ColorQueue colorQueue = new ColorQueueImpl();

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    @ResponseBody
    String fetch() throws Exception {
        FetchableColor fetch = this.colorQueue.fetch();
        checkNotNull(fetch, "Undefined fetch");
        ColorGson color = new ColorGson(fetch.getId().toString(), fetch
                .getColor().getRed().getValue(), fetch.getColor().getGreen()
                .getValue(), fetch.getColor().getBlue().getValue());
        return color.toJson();
    }

    @RequestMapping(value = "/set/{id}", method = RequestMethod.GET)
    @ResponseBody
    String set(@PathVariable("id") final String id) throws Exception {
        checkArgument(isNotBlank(id), "id is blank");

        UUID uuid = UUID.fromString(id);
        this.colorQueue.set(uuid);
        return "{\"response\": \"success\"}";
    }

}
