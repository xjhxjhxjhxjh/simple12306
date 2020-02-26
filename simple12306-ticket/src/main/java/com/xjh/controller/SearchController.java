package com.xjh.controller;

import com.xjh.dto.TrainShiftDTO;
import com.xjh.service.StationService;
import com.xjh.service.TrainShiftService;
import com.xjh.vo.ResultVO;
import com.xjh.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 16:49
 */
@CrossOrigin
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private TrainShiftService trainShiftService;

    @Autowired
    private StationService stationService;

    @GetMapping("{start}/{end}/{date}/{type}")
    public ResultVO<List<TrainShiftDTO>> getInfo(@PathVariable String start, @PathVariable String end,
                                                 @PathVariable String date, @PathVariable String type) {
        List<TrainShiftDTO> trainShiftList = null;
        try {
            trainShiftList = trainShiftService.findSeat(start, end, type, date);
        } catch (Exception e) {
            e.printStackTrace();
            new ResultVO(StatusCode.SERVER_ERROR, null);
        }
        return new ResultVO(StatusCode.SUCCESS, trainShiftList);
    }

    @GetMapping("{start}/{end}/{date}")
    public ModelAndView getInfo(@PathVariable String start, @PathVariable String end,
                                @PathVariable String date) {
        StringBuilder sb = new StringBuilder("/search/").append(start).
                append("/").append(end).append("/").append(date).append("/null");
        ModelAndView modelAndView = new ModelAndView(sb.toString());
        return modelAndView;
    }

    @GetMapping("station/{city}")
    public ResultVO searchStation(@PathVariable String city) {
        List<String> station = stationService.findStationCityOrStation(city);
        return new ResultVO(StatusCode.SUCCESS, station);
    }
}
