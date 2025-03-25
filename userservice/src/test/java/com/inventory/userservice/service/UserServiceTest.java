package com.inventory.userservice.service;

import com.inventory.userservice.domain.entity.User;
import com.inventory.userservice.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.inventory.userservice.utils.UserFactory.testAdmin;
import static com.inventory.userservice.utils.UserFactory.testUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void testUserCreatedSuccessfully() throws Exception {
        //Given
        when(userRepository.save(testUser())).thenReturn(testUser());

        //When
        User response = userService.createUser(testUser());
        //Then
        assertNotNull(response);
        assertEquals(testUser().getId(), response.getId());
        assertEquals(testUser().getRole(), response.getRole());
    }

    @Test
    void testUserCreatedFailed() {
        //Given
        when(userRepository.findByEmail(testUser().getEmail())).thenReturn(Optional.of(testUser()));
        //When
        Exception thrown = assertThrows(Exception.class, () -> userService.createUser(testUser()));
        //Then
        assertNotNull(thrown);
        assertEquals("User already exists", thrown.getMessage());
        verify(userRepository).findByEmail(testUser().getEmail());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testAdminUpdatedSuccessfully() {
        //Given
        when(userRepository.findByEmail(testAdmin().getEmail())).thenReturn(Optional.of(testAdmin()));

        //When
        User response = userService.updateUser(testAdmin());
        //Then
        assertNotNull(response);
        assertEquals(testAdmin().getId(), response.getId());
    }

    @Test
    void testAdminUpdateFailed() {
        //Given
        when(userRepository.findByEmail(testAdmin().getEmail())).thenThrow(UsernameNotFoundException.class);
        //When
        UsernameNotFoundException thrown = assertThrows(UsernameNotFoundException.class, () -> userService.updateUser(testAdmin()));
        //Then
        assertNotNull(thrown);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testUserDeletedSuccessfully() {
        //Given
        when(userRepository.findById(testUser().getId())).thenReturn(Optional.of(testUser()));

        //When
        userService.deleteUser(testUser().getId());
        //Then
        verify(userRepository).findById(testUser().getId());
        verify(userRepository).delete(testUser());
    }

    @Test
    void testUserDeleteFailed() {
        //Given

        when(userRepository.findById(testUser().getId())).thenThrow(UsernameNotFoundException.class);

        //When
        UsernameNotFoundException thrown = assertThrows(UsernameNotFoundException.class, () -> userService.deleteUser(testUser().getId()));
        //Then
        assertNotNull(thrown);
        verify(userRepository).findById(testUser().getId());
        verify(userRepository, never()).delete(any(User.class));
    }

    @Test
    void testAdminFindByEmailSuccessfully() {
        //Given
        when(userRepository.findByEmail(testAdmin().getEmail())).thenReturn(Optional.of(testAdmin()));

        //When
        User response  = userService.findUserByEmail(testAdmin().getEmail());
        //Then
        assertNotNull(response);
        assertEquals(testAdmin().getId(), response.getId());
        assertEquals(testAdmin().getEmail(), response.getEmail());
    }

    @Test
    void testAdminFindByEmailFail() {
        //Given
        when(userRepository.findByEmail(testAdmin().getEmail())).thenThrow(UsernameNotFoundException.class);

        //When
        UsernameNotFoundException thrown = assertThrows(UsernameNotFoundException.class, () -> userService.findUserByEmail(testAdmin().getEmail()));
        //Then
        assertNotNull(thrown);
        verify(userRepository).findByEmail(testAdmin().getEmail());
    }

    @Test
    void testFindAllSuccessfully() {
        //Given
        when(userRepository.findAll()).thenReturn(List.of(testUser(),testAdmin()));

        //When
        List<User> response = userService.findAllUsers();

        //Then
        assertNotNull(response);
        assertEquals(testUser().getId(), response.getFirst().getId());
        assertEquals(2, response.size());
    }

}
