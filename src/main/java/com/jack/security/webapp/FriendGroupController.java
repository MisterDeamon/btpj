package com.jack.security.webapp;

import com.jack.security.pojo.Friend;
import com.jack.security.pojo.FriendGroup;
import com.jack.security.pojo.SecurityUser;
import com.jack.security.service.FriendGroupService;
import com.jack.security.service.SecurityUserService;
import com.jack.security.service.mybatis.FriendService;
import com.jack.security.shiro.ShiroDbRealm;
import com.jack.utils.Pager;
import com.jack.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wajiangk on 10/11/2016.
 */
@Controller
@RequestMapping(value="/management/security")
public class FriendGroupController extends BaseController {

    @Autowired
    private FriendGroupService friendGroupService;

    @Autowired
    private SecurityUserService userService;
    @Autowired
    private FriendService friendService;

    private static final String LIST = "management/security/friend/list";
    private static final String GROUPLIST = "management/security/friendGroup/list";
    private static final String CREATE = "management/security/friendGroup/create";
    private static final String MODIFY = "management/security/friendGroup/modify";
    private static final String FRIENDADD = "management/security/friend/create";

    @RequestMapping(value={"/friend","/friend/list"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model){
        String userId = ((ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal()).getId();
        model.addAttribute("friends",friendGroupService.findAllFriendsByGroup(userId));
        return LIST;

    }

    @RequestMapping(value={"/friendGroup","/friendGroup/list"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String groupList(Model model, HttpServletRequest request,FriendGroup searchfriendGroup){
        Pager<FriendGroup> pager = new Pager<FriendGroup>();
        pager.setPageSize(5);
        setPageInfo(request,pager);

        String userId = ((ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal()).getId();
        searchfriendGroup.setUserId(userId);
        List<FriendGroup> friendGroups = friendGroupService.findGroupPage(searchfriendGroup,pager);
        int total = friendGroupService.findGroupCount(searchfriendGroup,pager);

        pager.setTotalCount(total);
        pager.setResult(friendGroups);
        pager.setTotalCount(total);
        pager.setFirstandLastPn();
        model.addAttribute("page", pager);
        model.addAttribute("friendGroupList", pager.getResult());
        model.addAttribute("searchfriendGroup", searchfriendGroup);
        return GROUPLIST;
    }

    @RequestMapping(value = "/friendGroup/create", method = RequestMethod.GET)
    public String create() {
        return CREATE;
    }

    @RequestMapping(value = "/friendGroup/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String create(FriendGroup group) {

        Map<String, Object> map = new HashMap<String, Object>();
        group.setGroupName(StringUtils.encodeUTF8(group.getGroupName()));
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        group.setUserId(shiroUser.getId());
        friendGroupService.preCreate(group, shiroUser);
        friendGroupService.preUpdate(group, shiroUser);

        try {
            friendGroupService.save(group);
            map.put("msg", "群组添加成功");
            map.put("success", true);
        } catch (Exception e) {
            map.put("msg", "群组添加失败");
            e.printStackTrace();
        }

        return getJsonResult(map);
    }

    @RequestMapping(value = "/friendGroup/modify", method = RequestMethod.GET)
    public String modify(@RequestParam("id") String groupId, Model model) {
        FriendGroup tempGroup = new FriendGroup();
        tempGroup.setUserId(((ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal()).getId());
        tempGroup.setGroupId(groupId);
        model.addAttribute("group", friendGroupService.findGroup(tempGroup).get(0));
        return MODIFY;
    }

    @RequestMapping(value = "/friendGroup/modify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String modify(FriendGroup group) {

        group.setGroupName(StringUtils.encodeUTF8(group.getGroupName()));

        Map<String, Object> map = new HashMap<String, Object>();
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        friendGroupService.preUpdate(group, shiroUser);
        group.setUserId(shiroUser.getId());
        try {
            friendGroupService.update(group);
            map.put("msg", "群组修改成功");
            map.put("success", true);
        } catch (Exception e) {
            map.put("msg", "群组修改失败");
            e.printStackTrace();
        }

        return getJsonResult(map);
    }

    @RequestMapping(value = "/friend/addFriend", method = {RequestMethod.GET,RequestMethod.POST})
    public String addFriend(SecurityUser user,Model model,HttpServletRequest request){
        Pager<SecurityUser> pager = new Pager<SecurityUser>();
        pager.setPageSize(3);
        setPageInfo(request,pager);
        List<SecurityUser> userList=null;
        int total=0;
        if(StringUtils.isNotEmpty(user.getUserName())){
             userList = userService.findUserPage(user,pager);
             total = userService.findUserCount(user,pager);
        }
        FriendGroup tempGroup = new FriendGroup();
        tempGroup.setUserId(((ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal()).getId());
        List<FriendGroup> groups = friendGroupService.findGroup(tempGroup);
        pager.setResult(userList);
        pager.setTotalCount(total);
        pager.setFirstandLastPn();
        model.addAttribute("page", pager);
        model.addAttribute("groups", groups);
        model.addAttribute("userList", pager.getResult());
        model.addAttribute("searchUser", user);
        return FRIENDADD;
    }

    @RequestMapping(value = "/friend/saveFriend",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody String saveFriend(Friend friend){
        Map<String, Object> map = new HashMap<String, Object>();
        friend.setUserId(((ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal()).getId());
        try {
            friend.setFriendCommentName(StringUtils.encodeUTF8(friend.getFriendCommentName()));
            friendService.save(friend);
            map.put("success", true);
            map.put("msg", "好友添加成功");
        } catch (Exception e) {
            map.put("success", false);
            e.printStackTrace();
        }
        return getJsonResult(map);
    }

    @RequestMapping(value = "/friendGroup/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String delete(@RequestParam("ids") String[] groupIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        FriendGroup tempGroup = new FriendGroup();
        tempGroup.setUserId(((ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal()).getId());
        try {
            for (String id : groupIds) {
                tempGroup.setGroupId(id);
                friendGroupService.delete(tempGroup);
            }
            map.put("success", true);
            map.put("msg", "群组删除成功");
        } catch (Exception e) {
            map.put("success", false);
            e.printStackTrace();
        }
        return getJsonResult(map);
    }
}
