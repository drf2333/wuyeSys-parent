package com.drf.wy.web.repair.controller;



import com.drf.wy.config.cors.CorsConfig;
import com.drf.wy.utils.Result;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther：杜瑞丰
 * @Date：2023/8/4 10:01
 * @Project_Name:wuyeSys-parent
 */

@Api(tags = "视频控制类")
@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridFSBucket gridFSBucket;
    @Autowired
    private CorsConfig config;

    @ApiOperation("上传视频")
    @PostMapping("/upload")
    public Result upload(MultipartFile video) throws Exception{
        //视频id
        ObjectId videoId;
        //视频流
        InputStream inputStream = video.getInputStream();
        //视频名字
        String fileName = video.getOriginalFilename();
        try {
            //上传视频返回id
            videoId = gridFsTemplate.store(inputStream, fileName,video.getContentType());
        } finally {
            inputStream.close();
        }
        //封装响应结果，视频id与名字
        Map map = new HashMap();
        map.put("videoId",videoId.toString());
        map.put("videoName",fileName);
        return Result.success(map);
    }

    @ApiOperation("根据id删除视频")
    @DeleteMapping("/{videoId}")
    public Result deleteVideo(@PathVariable("videoId") String videoId){
        //封装删除条件
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(videoId));
        //删除视频
        gridFsTemplate.delete(query);
        return Result.success();
    }


    @ApiOperation("播放视频")//bytes=32768-   bytes=22478848-
    @GetMapping("/{videoId}")
    public void viewTo(HttpServletResponse response, HttpServletRequest request, @PathVariable("videoId") String videoId) throws Exception {
        long  skip = -1;
        long  length = -1;
        Query gridQuery = new Query().addCriteria(Criteria.where("_id").is(new ObjectId(videoId)));
        //根据id查询文件
        GridFSFile gridFSDBFile = gridFsTemplate.findOne(gridQuery);
        response.setHeader("Content-Type", "video/mp4");
        long fileLength = gridFSDBFile.getLength();
        long end = fileLength - 1;
        String range = request.getHeader("Range");
        if (range != null && range.length() > 0) {
            int idx = range.indexOf("-");
            skip = Long.parseLong(range.substring(6, idx));
            if ((idx + 1) < range.length()) {
                end = Long.parseLong(range.substring(idx + 1));
            }
            length = end - skip + 1;
        }

        if (range == null || range.length() <= 0) {//bytes=32523-32523
            response.setHeader("Content-Length", "" + fileLength);
            response.setStatus(200);
        } else {
            response.setHeader("Content-Length", "" + length);
            response.setHeader("Content-Range", "bytes " + skip + "-" + end + "/" + fileLength);
            response.setStatus(206);
        }
        System.out.println("bytes " + skip + "-" + end + "/" + fileLength);
        download(response.getOutputStream(), gridFSDBFile, skip, length);
    }

    /**
     * 文件下载基础类
     * 断点续读
     * @param outputStream 文件输出流
     * @param fsFile mongo文件
     * @param skip         跳过多少字节 <=0忽略
     * @param length       输出字节长度 <=忽略
     * @throws Exception
     */
    public void download(ServletOutputStream outputStream, GridFSFile fsFile, long skip, long length) throws Exception {
        InputStream inputStream = null;
        try {
            //打开流下载对象
            GridFSDownloadStream in = gridFSBucket.openDownloadStream(fsFile.getObjectId());
            //获取流对象
            GridFsResource resource = new GridFsResource(fsFile, in);
            inputStream = resource.getInputStream();
            if (skip > 0) {
                inputStream.skip(skip);
            }
            byte[] bs = new byte[1024];
            int len;
            while ((len = inputStream.read(bs)) != -1) {
                if (length > 0) {
                    if (length > len) {
                        outputStream.write(bs, 0, len);
                        outputStream.flush();
                        length -= len;
                    } else {
                        outputStream.write(bs, 0, (int) length);
                        outputStream.flush();
                        break;
                    }
                } else {
                    outputStream.write(bs, 0, len);
                    outputStream.flush();
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
    @GetMapping("/asd")
    public Result test(){
        return Result.success(config.corsFilter());
    }
}
