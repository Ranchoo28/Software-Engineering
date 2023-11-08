package it.unical.ingsw;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {


    private UserService userService;
    @Mock
    private SecurityService securityService;
    @Mock
    private UserDao userDao;
    @Mock
    private User user;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userDao, securityService);
    }

        

    @Test
    public void userShouldGetNewPassword() throws Exception {
        String fakePassword = "Password1234";
        String hashedFakePassword = "Hashed Password1234";
        when(user.getPassword()).thenReturn(fakePassword);
        when(securityService.hash(fakePassword)).thenReturn(hashedFakePassword);

        User userReturn = userService.assignPassword(user);

        verify(securityService).hash(fakePassword);
        verify(user).setPassword(hashedFakePassword);
        verify(userDao).updateUser(user);

        assertEquals(user, userReturn);
    }

    @Test
    public void shouldNotProceedFurtherWhenUsersGetPasswordThrowsException() {
        when(user.getPassword()).thenThrow(RuntimeException.class);

        Exception ex = assertThrows(RuntimeException.class, () -> {
            userService.assignPassword(user);
        });

        assertNotNull(ex);
        verify(user, times(1)).getPassword();
        verifyNoMoreInteractions(user);
        verifyNoInteractions(userDao);
    }

    @Test
    public void shouldNotProceedFurtherWhenSecurityServiceThrowsException() throws Exception {
        
        when(securityService.hash(anyString())).thenThrow(Exception.class);
        
        Exception ex = assertThrows(Exception.class, () -> {
            securityService.hash(anyString());
        });

        assertNotNull(ex);
        verifyNoInteractions(user);
        verifyNoInteractions(userDao);
    }

    @Test
    public void shouldNotProceedFurtherWhenUsersSetPasswordThrowsException() throws Exception {
        // stiamo dando un comportamento ad un metodo che NON è void
        when(user.getPassword()).thenReturn("password");

        when(securityService.hash("password")).thenReturn("hashed password");
        
        // stiamo dando un comportamento ad un metodo che E' void
        doThrow(IllegalArgumentException.class).when(user).setPassword("hashed password");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            userService.assignPassword(user);
        });

        assertNotNull(ex);
        verify(user).getPassword();
        verify(securityService).hash(anyString());
        verify(user).setPassword(anyString());
        verifyNoInteractions(userDao);
    }

    @Test
    public void shouldNotUpdateUserDaoWhenExceptionIsThrownOnUpdateUser() throws Exception {

        // Lancia questa eccezione quando viene chiamato quel metodo con qualsiasi parametro
        doThrow(RuntimeException.class).when(userDao).updateUser(any());

        Exception ex = assertThrows(RuntimeException.class, () -> {
            userDao.updateUser(any());
        });

        assertNotNull(ex);
        verify(userDao, times(1)).updateUser(any());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenNullIsPassedAsParameter() {

        Exception ex = assertThrows(NullPointerException.class, () -> {
            userService.assignPassword(null);
        });

        assertTrue(ex instanceof NullPointerException);
        verifyNoInteractions(user);
        verifyNoInteractions(securityService);
        verifyNoInteractions(userDao);
    }
}
