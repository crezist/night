package com.xm.web.xm.controller;

import java.io.File;
		import java.io.IOException;
		import java.util.List;
		import java.util.Map;
		import java.util.UUID;
		
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.web.bind.annotation.PostMapping;
		import org.springframework.web.bind.annotation.RequestMapping;
		import org.springframework.web.bind.annotation.RequestParam;
		import org.springframework.web.bind.annotation.RestController;
		import org.springframework.web.multipart.MultipartFile;
		
		import com.xm.web.xm.pojo.GoodscollectionKey;
		import com.xm.web.xm.services.LiDongxuService;
		
		
@RestController
@RequestMapping(value="/Ajax_LidongxuController")
public class Ajax_LidongxuController {
			
		@Autowired
		LiDongxuService lidongxuService;	
		
		/**
		 * 查询商品名称是否重复
		 * @param gdname 商品名称
		 * @return
		 */
		@RequestMapping(value="selectgdname")
		public Object selectgdname(String gdname){
			return lidongxuService.selectgdname(gdname);
		}
		
		
		
		/**
		 * 添加商品全部信息
		 * @param gdname 商品名字
		 * @param stid 店铺编号
		 * @param gtkeywords 商品关键字
		 * @param gimgurl 商品图片路径
		 * @param price 商品价格
		 * @return
		 */
		@RequestMapping(value="insertgoodsinfo")
		public Object insertgoodsinfo(String gdname,Integer stid,String gtkeywords,String gimgurl,Double price){
			return lidongxuService.insertgoodsinfo(gdname, stid, gtkeywords, gimgurl, price);
		}
		
		
		
		/**
		 * 通过userid查询店铺编号
		 * @param userid 用户id
		 * @return
		 */
		@RequestMapping(value="selectstoreid")
		public Object selectstoreid(Integer userid){
			return lidongxuService.selectstoreid(userid);
		}
		
		/**
		 * 店铺更改商品信息
		 * @param gdid 商品编号
		 * @param gdname 商品名字
		 * @param gimgurl 商品图片路径
		 * @param price 商品价格
		 * @return
		 */
		@RequestMapping(value="updategdoosinfo")
		public Object updategdoosinfo(Integer gdid,String gdname,String gimgurl,Double price){
			return lidongxuService.updategoodsinfo(gdid, gdname, gimgurl, price);
		}
		
		
		/**
		 * 上传图片
		 * @param file1 文件
		 * @return
		 * @throws IOException 
		 */
		@PostMapping("filecontrollerupload_single")
		public Object  upload_single(@RequestParam(value="file1") MultipartFile file1 ) throws IOException {
			String old_fileName=file1.getOriginalFilename();
			StringBuffer fileName=new StringBuffer(UUID.randomUUID().toString());
		    fileName.append(old_fileName.substring(old_fileName.lastIndexOf(".")));
			System.out.println(fileName);
		    //创建放置在服务器本地的文件
		    File localFile = new File("d:"+File.separator+"web1"+File.separator+"tp"+File.separator+fileName);
		    //把传上来的文件写到本地文件
		    file1.transferTo(localFile);
		    return fileName;
		}
		
		
		/**
		 * 查看商品相关信息
		 * @param gdid 商品编号
		 * @return
		 */
		@RequestMapping(value="/getGoodsinfo")
		public Object getGoodsinfo(Integer gdid){
			return lidongxuService.getGoodsinfo(gdid);
		}
		
		
		/**
		 * 店铺删除商品
		 * @param gdid 商品编号
		 * @return
		 */
		@RequestMapping(value="/delectgoodsinfo")
		public boolean delectgoodsinfo(Integer gdid){
			return lidongxuService.delectgoodsinfo(gdid);
		}
		
		/**
		 * 查询所有收藏商品
		 * @param userid 用户id
		 * @return
		 */
		@RequestMapping(value="/selectAllcollection")
		public Object selectAllcollection(Integer userid){
			List<Map> cont=lidongxuService.selectAllcollection(userid);
			return cont;
		}
		
		
		
		/**
		 * 查询是否收藏该商品 
		 * @param gdid 商品编号
		 * @param userid 用户id
		 * @return
		 */
		@RequestMapping(value="/selectGoodscollection")
		public boolean selectGoodscollection(Integer gdid,Integer userid){
			return lidongxuService.selectGoodscollection(gdid, userid);
		}
		
			
		/**
		 *  添加收藏
		 * @param goodskey 添加收藏记录
		 * @return
		 */
		@RequestMapping(value="insertgoodskey")
		public boolean insertgoodskey(GoodscollectionKey goodskey){
			return lidongxuService.insertgoodscollectionMapper(goodskey);
		}
		
		/**
		 * 删除收藏
		 * @param gdid 商品编号
		 * @param userid 用户id
		 * @return
		 */
		@RequestMapping(value="delectgoodskey")
		public boolean delectgoodskey(Integer gdid,Integer userid){
			return lidongxuService.delectgoodscollec(gdid, userid);
		}
			
}
