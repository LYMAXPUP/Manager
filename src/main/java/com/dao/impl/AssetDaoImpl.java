package com.dao.impl;

import com.dao.IAssetDao;
import com.model.Asset;
import com.utils.DBManager;
import com.utils.FinanceData;
import com.utils.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssetDaoImpl implements IAssetDao {
    Connection connection = null;
    PreparedStatement pstm,pstm2,pstm3,pstm4=null;
    ResultSet rs,rs2,rs3,rs4 = null; // 查

    @Override
    public List<Asset> getAllAsset(int offset, int limit, String assetName) {
        List<Asset> assetlist = new ArrayList<>();
        connection = DBManager.getConnection();
        try {
            if(StringUtils.isEmpty(assetName)) {
                pstm = connection.prepareStatement("SELECT f.voucher voucher,f.time finance_time, f.state finance_state,p.name asset_name, p.department, p.model, p.price, \n" +
                        "p.buy_time, a.state asset_state, a.text, a.id,t.type\n" +
                        "from `assets` a\n" +
                        "INNER JOIN `type` t ON a.type_id = t.id\n" +
                        "INNER JOIN `finance` f ON a.finance_id = f.id\n" +
                        "INNER JOIN `products` p ON p.id = a.product_id\n" +
                        "GROUP BY id LIMIT ?,?");
                pstm.setInt(1, offset);
                pstm.setInt(2, limit);
                // 入账人
                pstm2 = connection.prepareStatement("SELECT u.user_id FROM finance f INNER JOIN assets a on f.id = a.finance_id INNER JOIN users u ON u.id = f.user_id LIMIT ?,?");
                pstm2.setInt(1, offset);
                pstm2.setInt(2, limit);
                // 保管人
                pstm3 = connection.prepareStatement("SELECT u.user_id FROM users u INNER JOIN assets a ON a.keeper_id = u.id LIMIT ?,?");
                pstm3.setInt(1, offset);
                pstm3.setInt(2, limit);
                // 使用人
                pstm4 = connection.prepareStatement("SELECT u.user_id FROM users u INNER JOIN assets a ON a.user_id = u.id LIMIT ?,?");
                pstm4.setInt(1, offset);
                pstm4.setInt(2, limit);

                rs = pstm.executeQuery();
                rs2 = pstm2.executeQuery();
                rs3 = pstm3.executeQuery();
                rs4 = pstm4.executeQuery();
                while (rs.next() && rs2.next() && rs3.next() && rs4.next()) {
                    Asset a = new Asset();
                    a.setVoucher(rs.getString(1));
                    a.setAccountTime(rs.getString(2));
                    a.setFinanceState(rs.getInt(3));
                    a.setAssetName(rs.getString(4));
                    a.setDepartment(rs.getString(5));
                    a.setModel(rs.getString(6));
                    a.setPrice(rs.getDouble(7));
                    a.setBuyTime(rs.getString(8));
                    a.setAssetState(rs.getInt(9));
                    a.setText(rs.getString(10));
                    a.setAssetId(rs.getInt(11));
                    a.setType(rs.getString(12));
                    a.setAccountName(rs2.getString(1));
                    a.setKeeperName(rs3.getString(1));
                    a.setUserName(rs4.getString(1));
                    assetlist.add(a);
                }
            }else{
                pstm = connection.prepareStatement("SELECT f.voucher voucher,f.time finance_time, f.state finance_state,p.name asset_name, p.department, p.model, p.price, \n" +
                        "p.buy_time, a.state asset_state, a.text,a.id, t.type\n" +
                        "from `assets` a\n" +
                        "INNER JOIN `type` t ON a.type_id = t.id\n" +
                        "INNER JOIN `finance` f ON a.finance_id = f.id\n" +
                        "INNER JOIN `products` p ON p.id = a.product_id\n" +
                        "WHERE p.name like ? GROUP BY id LIMIT ?,?");
                pstm.setString(1, "%"+assetName+"%");
                pstm.setInt(2, offset);
                pstm.setInt(3, limit);
                // 入账人
                pstm2 = connection.prepareStatement("SELECT u.user_id FROM finance f " +
                        "INNER JOIN assets a ON f.id = a.finance_id INNER JOIN users u ON u.id = f.user_id INNER JOIN products p ON p.id=a.product_id " +
                        "WHERE p.name LIKE ? LIMIT ?,?");
                pstm2.setString(1, "%"+assetName+"%");
                pstm2.setInt(2, offset);
                pstm2.setInt(3, limit);
                // 保管人
                pstm3 = connection.prepareStatement("SELECT u.user_id FROM users u INNER JOIN assets a ON a.keeper_id = u.id\n" +
                        "INNER JOIN products p ON p.id=a.product_id WHERE p.name LIKE ? LIMIT ?,?");
                pstm3.setString(1, "%"+assetName+"%");
                pstm3.setInt(2, offset);
                pstm3.setInt(3, limit);
                // 使用者
                pstm4 = connection.prepareStatement("SELECT u.user_id FROM users u INNER JOIN assets a ON a.user_id = u.id\n" +
                        "INNER JOIN products p ON p.id=a.product_id WHERE p.name LIKE ? LIMIT ?,?");
                pstm4.setString(1, "%"+assetName+"%");
                pstm4.setInt(2, offset);
                pstm4.setInt(3, limit);

                rs = pstm.executeQuery();
                rs2 = pstm2.executeQuery();
                rs3 = pstm3.executeQuery();
                rs4 = pstm4.executeQuery();
                while (rs.next() && rs2.next() && rs3.next() && rs4.next()) {
                    Asset a = new Asset();
                    a.setVoucher(rs.getString(1));
                    a.setAccountTime(rs.getString(2));
                    a.setFinanceState(rs.getInt(3));
                    a.setAssetName(rs.getString(4));
                    a.setDepartment(rs.getString(5));
                    a.setModel(rs.getString(6));
                    a.setPrice(rs.getDouble(7));
                    a.setBuyTime(rs.getString(8));
                    a.setAssetState(rs.getInt(9));
                    a.setText(rs.getString(10));
                    a.setAssetId(rs.getInt(11));
                    a.setType(rs.getString(12));
                    a.setAccountName(rs2.getString(1));
                    a.setKeeperName(rs3.getString(1));
                    a.setUserName(rs4.getString(1));
                    assetlist.add(a);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
            return assetlist;
    }

    @Override
    public Integer listCount(String assetName) {
        try {
            connection = DBManager.getConnection();
            if(StringUtils.isEmpty(assetName)) {
                pstm = connection.prepareStatement("select COUNT(*) from assets");
            } else{
                pstm = connection.prepareStatement("SELECT COUNT(*) FROM assets a INNER JOIN products p ON p.id = a.product_id WHERE p.name LIKE ?");
                pstm.setString(1,"%"+assetName+"%");
            }
            rs = pstm.executeQuery();
            while(rs.next()){
                return rs.getInt(1); // 返回Count
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Asset> getMyAsset(int offset, int limit, String userId, String assetName) {
        List<Asset> assetlist = new ArrayList<>();
        connection = DBManager.getConnection();
        try {
            if(StringUtils.isEmpty(assetName)) {
                pstm = connection.prepareStatement("SELECT f.voucher voucher,f.time finance_time, f.state finance_state,p.name asset_name, p.department, p.model, p.price, \n" +
                        "p.buy_time, a.state asset_state, a.text, a.id,t.type\n" +
                        "from `assets` a\n" +
                        "INNER JOIN `type` t ON a.type_id = t.id\n" +
                        "INNER JOIN `finance` f ON a.finance_id = f.id\n" +
                        "INNER JOIN `products` p ON p.id = a.product_id\n" +
                        "WHERE a.user_id=(SELECT id FROM users WHERE user_id=?)\n"+
                        "GROUP BY id LIMIT ?,?");
                pstm.setString(1, userId);
                pstm.setInt(2, offset);
                pstm.setInt(3, limit);
                // 入账人
                pstm2 = connection.prepareStatement("SELECT u.user_id FROM finance f INNER JOIN assets a on f.id = a.finance_id INNER JOIN users u ON u.id = f.user_id \n"+
                        "WHERE a.user_id=(SELECT id FROM users WHERE user_id=?) LIMIT ?,?");
                pstm2.setString(1, userId);
                pstm2.setInt(2, offset);
                pstm2.setInt(3, limit);
                // 保管人
                pstm3 = connection.prepareStatement("SELECT u.user_id FROM users u INNER JOIN assets a ON a.keeper_id = u.id\n"+
                        "WHERE a.user_id=(SELECT id FROM users WHERE user_id=?) LIMIT ?,?");
                pstm3.setString(1, userId);
                pstm3.setInt(2, offset);
                pstm3.setInt(3, limit);
                // 使用人
                pstm4 = connection.prepareStatement("SELECT u.user_id FROM users u INNER JOIN assets a ON a.user_id = u.id\n"+
                        "WHERE a.user_id=(SELECT id FROM users WHERE user_id=?) LIMIT ?,?");
                pstm4.setString(1, userId);
                pstm4.setInt(2, offset);
                pstm4.setInt(3, limit);

                rs = pstm.executeQuery();
                rs2 = pstm2.executeQuery();
                rs3 = pstm3.executeQuery();
                rs4 = pstm4.executeQuery();
                while (rs.next() && rs2.next() && rs3.next() && rs4.next()) {
                    Asset a = new Asset();
                    a.setVoucher(rs.getString(1));
                    a.setAccountTime(rs.getString(2));
                    a.setFinanceState(rs.getInt(3));
                    a.setAssetName(rs.getString(4));
                    a.setDepartment(rs.getString(5));
                    a.setModel(rs.getString(6));
                    a.setPrice(rs.getDouble(7));
                    a.setBuyTime(rs.getString(8));
                    a.setAssetState(rs.getInt(9));
                    a.setText(rs.getString(10));
                    a.setAssetId(rs.getInt(11));
                    a.setType(rs.getString(12));
                    a.setAccountName(rs2.getString(1));
                    a.setKeeperName(rs3.getString(1));
                    a.setUserName(rs4.getString(1));
                    assetlist.add(a);
                }
            }else{
                pstm = connection.prepareStatement("SELECT f.voucher voucher,f.time finance_time, f.state finance_state,p.name asset_name, p.department, p.model, p.price, \n" +
                        "p.buy_time, a.state asset_state, a.text,a.id, t.type\n" +
                        "from `assets` a\n" +
                        "INNER JOIN `type` t ON a.type_id = t.id\n" +
                        "INNER JOIN `finance` f ON a.finance_id = f.id\n" +
                        "INNER JOIN `products` p ON p.id = a.product_id\n" +
                        "WHERE p.name like ? AND a.user_id=(SELECT id FROM users WHERE user_id=?)\n" +
                        "GROUP BY id LIMIT ?,?");
                pstm.setString(1, "%"+assetName+"%");
                pstm.setString(2, userId);
                pstm.setInt(3, offset);
                pstm.setInt(4, limit);
                // 入账人
                pstm2 = connection.prepareStatement("SELECT u.user_id FROM finance f " +
                        "INNER JOIN assets a ON f.id = a.finance_id INNER JOIN users u ON u.id = f.user_id INNER JOIN products p ON p.id=a.product_id " +
                        "WHERE p.name LIKE ? AND a.user_id=(SELECT id FROM users WHERE user_id=?) LIMIT ?,?");
                pstm2.setString(1, "%"+assetName+"%");
                pstm2.setString(2, userId);
                pstm2.setInt(3, offset);
                pstm2.setInt(4, limit);
                // 保管人
                pstm3 = connection.prepareStatement("SELECT u.user_id FROM users u INNER JOIN assets a ON a.keeper_id = u.id\n" +
                        "INNER JOIN products p ON p.id=a.product_id WHERE p.name LIKE ? AND a.user_id=(SELECT id FROM users WHERE user_id=?) LIMIT ?,?");
                pstm3.setString(1, "%"+assetName+"%");
                pstm3.setString(2, userId);
                pstm3.setInt(3, offset);
                pstm3.setInt(4, limit);
                // 使用者
                pstm4 = connection.prepareStatement("SELECT u.user_id FROM users u INNER JOIN assets a ON a.user_id = u.id\n" +
                        "INNER JOIN products p ON p.id=a.product_id WHERE p.name LIKE ? AND a.user_id=(SELECT id FROM users WHERE user_id=?) LIMIT ?,?");
                pstm4.setString(1, "%"+assetName+"%");
                pstm4.setString(2, userId);
                pstm4.setInt(3, offset);
                pstm4.setInt(4, limit);

                rs = pstm.executeQuery();
                rs2 = pstm2.executeQuery();
                rs3 = pstm3.executeQuery();
                rs4 = pstm4.executeQuery();
                while (rs.next() && rs2.next() && rs3.next() && rs4.next()) {
                    Asset a = new Asset();
                    a.setVoucher(rs.getString(1));
                    a.setAccountTime(rs.getString(2));
                    a.setFinanceState(rs.getInt(3));
                    a.setAssetName(rs.getString(4));
                    a.setDepartment(rs.getString(5));
                    a.setModel(rs.getString(6));
                    a.setPrice(rs.getDouble(7));
                    a.setBuyTime(rs.getString(8));
                    a.setAssetState(rs.getInt(9));
                    a.setText(rs.getString(10));
                    a.setAssetId(rs.getInt(11));
                    a.setType(rs.getString(12));
                    a.setAccountName(rs2.getString(1));
                    a.setKeeperName(rs3.getString(1));
                    a.setUserName(rs4.getString(1));
                    assetlist.add(a);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return assetlist;
    }

    @Override
    public Integer myListCount(String userId, String assetName) {
        try {
            connection = DBManager.getConnection();
            if(StringUtils.isEmpty(assetName)) {
                pstm = connection.prepareStatement("select COUNT(*) from assets a WHERE a.user_id=(SELECT id FROM users WHERE user_id=?)");
                pstm.setString(1, userId);
            } else{
                pstm = connection.prepareStatement("SELECT COUNT(*) FROM assets a INNER JOIN products p ON p.id = a.product_id WHERE p.name LIKE ? AND a.user_id=(SELECT id FROM users WHERE user_id=?)");
                pstm.setString(1, userId);
                pstm.setString(2,"%"+assetName+"%");
            }
            rs = pstm.executeQuery();
            while(rs.next()){
                return rs.getInt(1); // 返回Count
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int findTypeId(Connection con, String type) {
        String sql = "SELECT id FROM type WHERE type=?;";
        rs = DBManager.executeQuery(con, sql, type);
        try {
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean updateAssets(Connection con, int assetId, String column, Object value){
        String sql = "UPDATE `assets` SET `"+column+"`= ? WHERE `id`=?;";
        Object[] param = new Object[]{value, assetId};
        return DBManager.executeUpdate(con, sql, param) > 0;
    }

    @Override
    public boolean updateProducts(Connection con, int assetId, String column, Object value){
        String sql = "UPDATE `products` SET `"+column+"`=? WHERE `id` = (SELECT `product_id` FROM `assets` WHERE `id`=?)";
        Object[] param = new Object[]{value, assetId};
        return DBManager.executeUpdate(con, sql, param) > 0;
    }

    @Override
    public boolean updateFinance(Connection con, int assetId, String column, Object value) {
        String sql = "UPDATE `finance` SET `"+column+"`=? WHERE `id` = (SELECT `finance_id` FROM `assets` WHERE `id`=?)";
        Object[] param = new Object[]{value, assetId};
        return DBManager.executeUpdate(con, sql, param) > 0;
    }

    @Override
    public boolean claimAsset(int assetId, String userId) {
        String sql = "UPDATE assets SET state = 1,keeper_id=(SELECT id FROM users WHERE user_id=?),\n" +
                "user_id=(SELECT id FROM users WHERE user_id=?) WHERE id=?";
        Object[] param = new Object[]{userId, userId,assetId};
        return DBManager.executeUpdate(null, sql, param) > 0;
    }

    public int getId(Connection con, String chart){
        String sql = "SELECT MAX(id) FROM "+chart;
        rs = DBManager.executeQuery(con, sql);
        try{
            while(rs.next()){
                return rs.getInt(1); // 返回Count
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 3
    @Override
    public boolean addAssets(Connection con, Asset asset) {
        System.out.println("3"+asset.getAssetName());
        int financeId = getId(con, "finance");
        int productId = getId(con, "products");
        String sql = "INSERT INTO assets VALUES (DEFAULT, ?, ?, (SELECT id FROM type WHERE type=?), DEFAULT, DEFAULT, DEFAULT, DEFAULT);";
        Object[] param = new Object[]{financeId, productId, asset.getType()};
        return DBManager.executeUpdate(con, sql, param) > 0;
    }

    // 1
    @Override
    public boolean addProducts(Connection con, Asset asset) {
        System.out.println("1"+asset.getAssetName());
        String sql = "INSERT INTO products VALUES (DEFAULT, ?,?,?,?,?);";
        Object[] param = new Object[]{asset.getAssetName(), asset.getDepartment(), asset.getModel(), asset.getPrice(), asset.getBuyTime()};
        return DBManager.executeUpdate(con, sql, param) > 0;
    }

    // 2
    @Override
    public boolean addFinance(Connection con, Asset asset) {
        System.out.println("2"+asset.getAssetName());
        String accountTime = FinanceData.accountTime();
        String voucher = FinanceData.voucher(getId(con, "products"), accountTime);
        String sql = "INSERT INTO finance VALUES (DEFAULT, (SELECT id FROM users WHERE user_id=?), ?, ?,DEFAULT);";
        Object[] param = new Object[]{asset.getAccountName(), voucher, accountTime};
        return DBManager.executeUpdate(con, sql, param) > 0;
    }
}
