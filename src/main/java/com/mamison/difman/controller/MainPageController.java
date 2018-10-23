package com.mamison.difman.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mamison.difman.entity.Account;
import com.mamison.difman.entity.Daily;
import com.mamison.difman.repository.Dailyrepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.time.DateUtils.addDays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainPageController {

    private static final int SIX_BEFORE_MONTHS = -180;
    private final Gson gson;
    private final Dailyrepository dailyrepository;

    @GetMapping("/{email}")
    private String mainPage(@PathVariable("email") Account account){
        if(account==null){
            return gson.toJson(new Account());
        }
        SimpleDateFormat sdf
                = new SimpleDateFormat("yyyy-MM-dd");
        Date date = DateUtils.addDays(new Date(), SIX_BEFORE_MONTHS);
        List<Daily> dailyList = dailyrepository.findAllByDailyDateAfter(date);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",account.getEmail());
        jsonObject.addProperty("grassList",gson.toJson(dailyList));
        return gson.toJson(jsonObject);
    }

}
