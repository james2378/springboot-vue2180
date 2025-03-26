
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 合同
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/hetong")
public class HetongController {
    private static final Logger logger = LoggerFactory.getLogger(HetongController.class);

    @Autowired
    private HetongService hetongService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private KehuService kehuService;
    @Autowired
    private YonghuService yonghuService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = hetongService.queryPage(params);

        //字典表数据转换
        List<HetongView> list =(List<HetongView>)page.getList();
        for(HetongView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        HetongEntity hetong = hetongService.selectById(id);
        if(hetong !=null){
            //entity转view
            HetongView view = new HetongView();
            BeanUtils.copyProperties( hetong , view );//把实体数据重构到view中

                //级联表
                KehuEntity kehu = kehuService.selectById(hetong.getKehuId());
                if(kehu != null){
                    BeanUtils.copyProperties( kehu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setKehuId(kehu.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(hetong.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody HetongEntity hetong, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,hetong:{}",this.getClass().getName(),hetong.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            hetong.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<HetongEntity> queryWrapper = new EntityWrapper<HetongEntity>()
            .eq("yonghu_id", hetong.getYonghuId())
            .eq("kehu_id", hetong.getKehuId())
            .eq("hetong_uuid_number", hetong.getHetongUuidNumber())
            .eq("hetong_types", hetong.getHetongTypes())
            .eq("changduan_types", hetong.getChangduanTypes())
            .eq("shouyiren_name", hetong.getShouyirenName())
            .eq("shouyiren_phone", hetong.getShouyirenPhone())
            .eq("shouyiren_id_number", hetong.getShouyirenIdNumber())
            .eq("shouyiren_address", hetong.getShouyirenAddress())
            .eq("zhixing_time", new SimpleDateFormat("yyyy-MM-dd").format(hetong.getZhixingTime()))
            .eq("daoqi_time", new SimpleDateFormat("yyyy-MM-dd").format(hetong.getDaoqiTime()))
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HetongEntity hetongEntity = hetongService.selectOne(queryWrapper);
        if(hetongEntity==null){
            hetong.setInsertTime(new Date());
            hetong.setCreateTime(new Date());
            hetongService.insert(hetong);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody HetongEntity hetong, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,hetong:{}",this.getClass().getName(),hetong.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
//        else if("用户".equals(role))
//            hetong.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<HetongEntity> queryWrapper = new EntityWrapper<HetongEntity>()
            .notIn("id",hetong.getId())
            .andNew()
            .eq("yonghu_id", hetong.getYonghuId())
            .eq("kehu_id", hetong.getKehuId())
            .eq("hetong_uuid_number", hetong.getHetongUuidNumber())
            .eq("hetong_types", hetong.getHetongTypes())
            .eq("changduan_types", hetong.getChangduanTypes())
            .eq("shouyiren_name", hetong.getShouyirenName())
            .eq("shouyiren_phone", hetong.getShouyirenPhone())
            .eq("shouyiren_id_number", hetong.getShouyirenIdNumber())
            .eq("shouyiren_address", hetong.getShouyirenAddress())
            .eq("zhixing_time", new SimpleDateFormat("yyyy-MM-dd").format(hetong.getZhixingTime()))
            .eq("daoqi_time", new SimpleDateFormat("yyyy-MM-dd").format(hetong.getDaoqiTime()))
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HetongEntity hetongEntity = hetongService.selectOne(queryWrapper);
        if("".equals(hetong.getHetongFile()) || "null".equals(hetong.getHetongFile())){
                hetong.setHetongFile(null);
        }
        if(hetongEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      hetong.set
            //  }
            hetongService.updateById(hetong);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        hetongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<HetongEntity> hetongList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            HetongEntity hetongEntity = new HetongEntity();
//                            hetongEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            hetongEntity.setKehuId(Integer.valueOf(data.get(0)));   //投保人 要改的
//                            hetongEntity.setHetongUuidNumber(data.get(0));                    //合同唯一编号 要改的
//                            hetongEntity.setHetongTypes(Integer.valueOf(data.get(0)));   //保险合同类型 要改的
//                            hetongEntity.setChangduanTypes(Integer.valueOf(data.get(0)));   //长短类型 要改的
//                            hetongEntity.setHetongFile(data.get(0));                    //合同文件 要改的
//                            hetongEntity.setBaofeiDouble(data.get(0));                    //保费 要改的
//                            hetongEntity.setBaoeDouble(data.get(0));                    //保额 要改的
//                            hetongEntity.setShouyirenName(data.get(0));                    //受益人姓名 要改的
//                            hetongEntity.setShouyirenPhone(data.get(0));                    //受益人手机号 要改的
//                            hetongEntity.setShouyirenIdNumber(data.get(0));                    //受益人身份证号 要改的
//                            hetongEntity.setShouyirenAddress(data.get(0));                    //受益人地址 要改的
//                            hetongEntity.setHetongContent("");//照片
//                            hetongEntity.setZhixingTime(new Date(data.get(0)));          //执行日期 要改的
//                            hetongEntity.setDaoqiTime(new Date(data.get(0)));          //到期日期 要改的
//                            hetongEntity.setInsertTime(date);//时间
//                            hetongEntity.setCreateTime(date);//时间
                            hetongList.add(hetongEntity);


                            //把要查询是否重复的字段放入map中
                                //合同唯一编号
                                if(seachFields.containsKey("hetongUuidNumber")){
                                    List<String> hetongUuidNumber = seachFields.get("hetongUuidNumber");
                                    hetongUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> hetongUuidNumber = new ArrayList<>();
                                    hetongUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("hetongUuidNumber",hetongUuidNumber);
                                }
                        }

                        //查询是否重复
                         //合同唯一编号
                        List<HetongEntity> hetongEntities_hetongUuidNumber = hetongService.selectList(new EntityWrapper<HetongEntity>().in("hetong_uuid_number", seachFields.get("hetongUuidNumber")));
                        if(hetongEntities_hetongUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(HetongEntity s:hetongEntities_hetongUuidNumber){
                                repeatFields.add(s.getHetongUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [合同唯一编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        hetongService.insertBatch(hetongList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = hetongService.queryPage(params);

        //字典表数据转换
        List<HetongView> list =(List<HetongView>)page.getList();
        for(HetongView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        HetongEntity hetong = hetongService.selectById(id);
            if(hetong !=null){


                //entity转view
                HetongView view = new HetongView();
                BeanUtils.copyProperties( hetong , view );//把实体数据重构到view中

                //级联表
                    KehuEntity kehu = kehuService.selectById(hetong.getKehuId());
                if(kehu != null){
                    BeanUtils.copyProperties( kehu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setKehuId(kehu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(hetong.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody HetongEntity hetong, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,hetong:{}",this.getClass().getName(),hetong.toString());
        Wrapper<HetongEntity> queryWrapper = new EntityWrapper<HetongEntity>()
            .eq("yonghu_id", hetong.getYonghuId())
            .eq("kehu_id", hetong.getKehuId())
            .eq("hetong_uuid_number", hetong.getHetongUuidNumber())
            .eq("hetong_types", hetong.getHetongTypes())
            .eq("changduan_types", hetong.getChangduanTypes())
            .eq("shouyiren_name", hetong.getShouyirenName())
            .eq("shouyiren_phone", hetong.getShouyirenPhone())
            .eq("shouyiren_id_number", hetong.getShouyirenIdNumber())
            .eq("shouyiren_address", hetong.getShouyirenAddress())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HetongEntity hetongEntity = hetongService.selectOne(queryWrapper);
        if(hetongEntity==null){
            hetong.setInsertTime(new Date());
            hetong.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      hetong.set
        //  }
        hetongService.insert(hetong);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
