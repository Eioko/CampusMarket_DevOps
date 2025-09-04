package code_sys.LayerControl;

import com.Aop.Pager;
import com.Aop.Protector;
import com.Po.Api.Result;
import com.Po.Dto.query.sons.UserQueryDto;
import com.Po.Dto.update.UserUpdateDTO;
import com.Po.Entity.User;
import com.Po.Vo.UserVO;
import code_sys.LayerService.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class LayerUserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<Object> login(@RequestBody com.Po.Dto.update.UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @Protector
    @GetMapping("/auth")
    public Result<UserVO> auth() {
        System.out.println("asdasd");
        return userService.auth();
    }

    @Protector
    @GetMapping("/getById/{id}")
    public Result<UserVO> getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody com.Po.Dto.update.UserRegisterDTO userRegisterDTO) {
        return userService.register(userRegisterDTO);
    }

    @Protector(role = "管理员")
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody com.Po.Dto.update.UserRegisterDTO userRegisterDTO) {
        return userService.insert(userRegisterDTO);
    }

    @Protector
    @PutMapping("/update")
    public Result<String> update(@RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.update(userUpdateDTO);
    }

    @Protector(role = "管理员")
    @PutMapping("/backUpdate")
    public Result<String> backUpdate(@RequestBody User user) {
        return userService.backUpdate(user);
    }

    @PutMapping("/updatePwd")
    public Result<String> updatePwd(@RequestBody com.Po.Dto.update.UserPwdUpdateDTO dto) {
        return userService.updatePwd(dto);
    }

    @Protector(role = "管理员")
    @PostMapping("/batchDelete")
    public Result<String> batchDelete(@RequestBody List<Integer> ids) {
        return userService.batchDelete(ids);
    }

    @Pager
    @Protector(role = "管理员")
    @PostMapping("/query")
    public Result<List<User>> query(@RequestBody UserQueryDto userQueryDto) {
        return userService.query(userQueryDto);
    }
}//