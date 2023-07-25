package com.drf.wy.web.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drf.wy.utils.Result;
import com.drf.wy.web.system.entity.HouseList;
import com.drf.wy.web.system.entity.HouseListParam;
import com.drf.wy.web.system.service.IHouseListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 房屋表 前端控制器
 * </p>
 *
 * @author 杜瑞丰
 * @since 2023-07-25
 */
@RestController
@RequestMapping("/api/house")
@Api(tags = "房屋管理控制类")
public class HouseListController {
    @Autowired
    private IHouseListService houseListService;

    @ApiOperation("房屋列表")
    @GetMapping("/list")
    public Result getList(HouseListParam param){
        System.out.println(param);
        IPage<HouseList> page = houseListService.getList(param);
        return Result.success(page);
    }

    @ApiOperation("添加房屋")
    @PostMapping
    public Result addHouse(@RequestBody HouseList houseList){
        boolean save = houseListService.save(houseList);
        if (save) {
            return Result.success();
        }
        return Result.error(500,"添加失败");
    }

    @ApiOperation("编辑房屋")
    @PutMapping
    public Result editHouse(@RequestBody HouseList houseList){
        boolean update = houseListService.updateById(houseList);
        if (update) {
            return Result.success();
        }
        return Result.error(500,"编辑失败");
    }

    @ApiOperation("删除房屋")
    @DeleteMapping("/{houseId}")
    public Result deleteHouse(@PathVariable("houseId") Integer houseId){
        boolean remove = houseListService.removeById(houseId);
        if (remove) {
            return Result.success();
        }
        return Result.error(500,"删除失败");
    }
}