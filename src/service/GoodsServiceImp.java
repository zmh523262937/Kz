package service;

import java.util.List;

import dao.CatagoryDao;
import dao.CatagoryDaoImp;
import dao.GoodsDao;
import dao.GoodsDaoImp;
import entity.Catagory;
import entity.Goods;
import entity.PageInfo;

public class GoodsServiceImp implements GoodsService{
	GoodsDao dao=null;
	public   GoodsServiceImp() {
		dao = new GoodsDaoImp();
	}
	@Override
	public PageInfo<Goods> getPageInfo(int pageIndex,int pageSize,String cid) {
		// TODO Auto-generated method stub
		return dao.pageInfo(pageIndex, pageSize, cid);
	}
	@Override
	public Goods get(String gid) {
		// TODO Auto-generated method stub
		return dao.getGoods(gid);
	}
	@Override
	public void deleteGoodsById(String gid) {
		// TODO Auto-generated method stub
		dao.removeGoodsById(gid);
	}
	@Override
	public void insertGoods(Goods goods) {
		// TODO Auto-generated method stub
		dao.insertGood(goods);
	}

}
