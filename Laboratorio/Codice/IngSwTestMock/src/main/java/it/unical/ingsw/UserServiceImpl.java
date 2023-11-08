package it.unical.ingsw;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private SecurityService securityService;

    public UserServiceImpl(UserDao userDao, SecurityService securityService) {
        this.userDao = userDao;
        this.securityService = securityService;
    }

    public User assignPassword(User user) throws Exception {
        String passwordMd5 = securityService.hash(user.getPassword());
        user.setPassword(passwordMd5);
        userDao.updateUser(user);
        return user;
    }

}
