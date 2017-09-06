package service;

import entity.Goods;
import entity.PageInfo;


public interface GoodsService {
	public PageInfo<Goods> getPageInfo(int pageIndex,int pageSize,String cid);
	public Goods get(String gid);
	public void deleteGoodsById(String gid);
	public void insertGoods(Goods goods);
}