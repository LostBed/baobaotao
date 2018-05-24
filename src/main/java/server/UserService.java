package server;


import dao.LoginLogDao;
import dao.UserDao;
import domin.LoginLog;
import domin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;

    public boolean hasMatchUser(String user,String password){
        int matchCount=userDao.getMatchCount(user,password);
        return matchCount>0;
    }

    public User findUserByUserName(String userName){
        return userDao.findUserByUsernName(userName);
    }

    public void loginSuccess(User user){
        user.setCredits(user.getCredits()+5);
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }


}
