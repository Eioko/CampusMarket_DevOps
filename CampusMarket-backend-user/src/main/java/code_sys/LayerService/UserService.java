package code_sys.LayerService;

import com.Po.Api.Result;
import com.Po.Dto.query.sons.UserQueryDto;
import com.Po.Dto.update.UserLoginDTO;
import com.Po.Dto.update.UserPwdUpdateDTO;
import com.Po.Dto.update.UserRegisterDTO;
import com.Po.Dto.update.UserUpdateDTO;
import com.Po.Entity.User;
import com.Po.Vo.UserVO;

import java.util.List;

public interface UserService {
    Result<String> register(UserRegisterDTO userRegisterDTO);

    Result<Object> login(UserLoginDTO userLoginDTO);

    Result<UserVO> auth();

    Result<List<User>> query(UserQueryDto userQueryDto);

    Result<String> update(UserUpdateDTO userUpdateDTO);

    Result<String> batchDelete(List<Integer> ids);

    public Result<String> updatePwd(UserPwdUpdateDTO dto);

    Result<UserVO> getById(Integer id);

    Result<String> insert(UserRegisterDTO userRegisterDTO);

    Result<String> backUpdate(User user);

}
