package dao;

import entity.Goods;
import entity.PageInfo;

public interface GoodsDao {
	public PageInfo<Goods> pageInfo(int pageIndex,int pageSize,String cid);
	public int pageCount(String cid);
	public Goods getGoods(String gid);
	public void removeGoodsById(String gid);
	public void imgDownload(Goods goods);
	public void insertGood(Goods goods);
}
