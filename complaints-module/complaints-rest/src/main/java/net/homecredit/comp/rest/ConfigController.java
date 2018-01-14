package net.homecredit.comp.rest;

import net.homecredit.comp.dto.ConfigDto;
import net.homecredit.comp.dto.UrlDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vladut.stoica
 */
@RestController
public class ConfigController {

    @Value("${config.complain.url}")
    private String configComplaintsUrl;

    @Value("${config.boss.url}")
    private String configBossUrl;

    @Value("${security.sso.server.logout.url}")
    private String configLogoutUrl;

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public ConfigDto getConfig() {
        ConfigDto config = ConfigDto.builder()
                .url(
                        UrlDto.builder()
                                .complain(configComplaintsUrl)
                                .boss(configBossUrl)
                                .logout(configLogoutUrl)
                                .build()
                )
                .build();

        return config;
    }

}