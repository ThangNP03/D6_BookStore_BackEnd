package ra.service;

import ra.model.user.RoleName;
import ra.model.user.Roles;

public interface IRoleService extends IGeneric<Roles, Long >{
    Roles findByRoleName (RoleName roleName );
}
