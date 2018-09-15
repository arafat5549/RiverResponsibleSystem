package com.jqm.ssm.service;

import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.dao.ModuleDao;
import com.jqm.ssm.dao.PermissionDao;
import com.jqm.ssm.dao.UserDao;
import com.jqm.ssm.entity.Module;
import com.jqm.ssm.entity.Permission;
import com.jqm.ssm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 */
@Service
public class SystemService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    @Autowired
    private UserDao userDao;
    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private PermissionDao permissionDao;
    //@Autowired
    //private LogDao logDao;

    @Resource(name= "redisCache")
    private RedisCache redisCache;

    @Transactional(readOnly = false)
    public void updatePasswordById(Integer id, String loginName, String newPassword) {
        User user = new User();
        user.setId(id);
        user.setPassword(entryptPassword(newPassword));
        userDao.updateByPrimaryKeySelective(user);
        // 清除用户缓存
        //user.setLogname(loginName);
        //UserUtils.clearCache(user);
    }

    /**
     * 日记记录
     * @param user
     * @param url
     */
    public void log(User user,String url){
//        Log log = new Log();
//        log.setUrldetail(url);
//        log.setCreatetime(new Date());
//        log.setLogtypeid(1);//系统操作袭击
//        log.setContent("操作人:"+user.getLogname());
//        //log.setOptions();
//        logDao.insertSelective(log);
    }

    /**
     * 构建模块树
     * @param userId
     * @param visitedModule
     * @param visitedResource
     * @return
     */
    public String getModuleTree(Integer userId,String visitedModule,String visitedResource)
    {
        if(visitedModule == null) visitedModule = "";
        if(visitedResource == null) visitedResource = "";

        User user = userDao.selectByPrimaryKey(userId);
        if(user == null)	return "";

        StringBuilder sb = new StringBuilder();
        List<Module> moduleList;
        if(user.getIsAdmin())
        {
            moduleList = moduleDao.selectListByMap(null);
        } else {
            moduleList = moduleDao.getModuleListByUserId(userId);
        }
        if(moduleList==null || moduleList.size()==0)	return "";
        List<Permission> permissionList;
        if(user.getIsAdmin())
        {
            permissionList = permissionDao.selectListByMap(null);
        } else {
            List<String> moduleFlags = new ArrayList<String>();
            for(Module m:moduleList)
            {
                moduleFlags.add(m.getFlag());
            }
            permissionList = permissionDao.getPermissionListByModuleFlag(moduleFlags, userId);
        }

        //从前一页面传过来的ModuleFlag标记和visitedStructure标记
        //moduleFlag = "p";
        //visitedStructure = "s-1-1-1";

        String[] icons = "fa-gift、fa-cogs、fa-puzzle-piece、fa-sitemap、fa-table、fa-briefcase、fa-comments、fa-group、fa-globe、fa-th".split("、");
        int iconsFlag = 0;

        for(Module m:moduleList)
        {
            if(m.getFlag().equals(visitedModule))
            {
                sb.append("<li class=\"active\">");
            } else {
                sb.append("<li>");
            }
            sb.append("<a href=\"javascript:;\">");
            sb.append("<i class=\"fa " + icons[iconsFlag++] + "\"></i>");
            sb.append("<span class=\"title\">");
            sb.append(" " + m.getName());
            sb.append("</span>");
            sb.append("<span class=\"selected\"></span>");
            sb.append("<span class=\"arrow open\"></span>");
            sb.append("</a>");
            sb.append(buildResourceTree(m,permissionList,"s",visitedModule,visitedResource));
            sb.append("</li>");
        }

        return sb.toString();
    }
//    public Integer updateUserById(User user){
//        return userMapper.updateByPrimaryKeySelective(user);
//    }
    //###############################################################################################################//
    //                                       私有方法方法区域
    //###############################################################################################################//


    /**
     *
     * @param m：当前正在遍历的模块
     * @param resourceList：所有的资源列表
     * @param structure：要对其进行树形结构的资源，根是s
     * @param visitedModule：当前访问页面的Module
     * @param visitedResource：当前访问页面的Resource
     * @return
     */
    private String buildResourceTree(Module m,List<Permission> resourceList,String structure,String visitedModule,String visitedResource)
    {
        if(resourceList == null || resourceList.size()==0)
        {
            return "";
        }
        int parentLength = structure.split("-").length;
        //计算一级子资源
        List<Permission> sonList = new ArrayList<Permission>();
        for(Permission r:resourceList)
        {
            if(r.getStructure().split("-").length==parentLength+1
                    && r.getStructure().contains(structure)
                    && r.getModuleFlag().equals(m.getFlag()))
            {
                sonList.add(r);
            }
        }
        if(sonList.size()==0)
        {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<ul class=\"sub-menu\">");
        for(Permission r:sonList)
        {
            //递归，找子级的树形结构
            String s = buildResourceTree(m,resourceList,r.getStructure(),visitedModule,visitedResource);
            if(visitedResource.contains(r.getStructure()) && r.getModuleFlag().equals(visitedModule))
            {
                sb.append("<li class=\"active\">");
            } else {
                sb.append("<li>");
            }
            if(s.equals(""))
            {
                sb.append("<a href=\"")
                        .append(m.getUrl())
                        .append(r.getUrl())
                        .append("?visitedModule=")
                        .append(r.getModuleFlag())
                        .append("&visitedResource=")
                        .append(r.getStructure())
                        .append("\">")
                ;
                sb.append("<i class=\"fa fa-leaf \"></i>&nbsp;&nbsp;");
                sb.append(r.getName());
                sb.append("</a>");
            }
            else
            {
                sb.append("<a href=\"javascript:;\">");
                sb.append("<i class=\"fa fa-folder \"></i>&nbsp;&nbsp;");
                sb.append(r.getName());
                if(visitedResource.contains(r.getStructure()) && r.getModuleFlag().equals(visitedModule))
                {
                    sb.append("<span class=\"arrow open\"></span>");
                } else {
                    sb.append("<span class=\"arrow\"></span>");
                }
                sb.append("</a>");
            }
            sb.append(s);
            sb.append("</li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }
    //###############################################################################################################//
    //                                       静态方法方法区域
    //###############################################################################################################//

//    public static User getLoginUser(HttpSession session){
//        if(session!=null) return (User)session.getAttribute(Constants.KEY_LOGIN);
//        return null;
//    }


    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword) {
//        byte[] salt = Digests.generateSalt(SALT_SIZE);
//        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
//        return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
            return plainPassword;
    }
    /**
     * 验证密码
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        //byte[] salt = Encodes.decodeHex(password.substring(0,16));
        //byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        //return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
        return plainPassword.equals(password);
    }

    /**
     * 获取当前用户角色列表
     * @return

    public  List<Role> getRoleList(){
        @SuppressWarnings("unchecked")
        List<Role> roleList = (List<Role>)redisCache.getCache(Constants.CACHE_KEY_ROLE_LIST,Role.class);
        if (roleList == null){
            if (user.isAdmin()){
                roleList = roleDao.findAllList(new Role());
            }else{
                Role role = new Role();
                roleList = roleDao.findList(role);
            }
            redisCache.putCache(Constants.CACHE_KEY_ROLE_LIST, roleList);
        }
        return roleList;
    }*/

    /**
     * 获取当前用户授权菜单
     * @return

    public  List<Permission> getMenuList(){
        @SuppressWarnings("unchecked")
        List<Permission> menuList = (List<Permission>)redisCache.getCache(Constants.CACHE_KEY_PERMISSION_LIST,Permission.class);
        if (menuList == null){
            User user = getUser();
            if (user.isAdmin()){
                menuList = menuDao.findAllList(new Permission());
            }else{
                Permission m = new Permission();
                m.setUserId(user.getId());
                menuList = menuDao.findByUserId(m);
            }
            redisCache.putCache(Constants.CACHE_KEY_PERMISSION_LIST, menuList);
        }
        return menuList;
    }*/
}
