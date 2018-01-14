package net.homecredit.comp.rest;

import net.homecredit.comp.domain.Priv2Role;
import net.homecredit.comp.domain.Role;
import net.homecredit.comp.security.PrivilegedSsoUser;
import net.homecredit.comp.service.IBossPriv2RoleService;
import net.homecredit.comp.service.IBossRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author vladut.stoica
 */
@RestController
public class BossPrivilegeController {

    private static final Logger logger = LoggerFactory.getLogger(BossPrivilegeController.class);

    @Autowired
    private IBossPriv2RoleService iBossPriv2RoleService;

    @Autowired
    private IBossRoleService iBossRoleService;

    @GetMapping(value = "/rights")
     public Hashtable<String,List<String>> getPrivileges(HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrivilegedSsoUser principal = authentication != null ? (PrivilegedSsoUser) authentication.getPrincipal() : null;

        List<Priv2Role> privileges;

        try {
            privileges = iBossPriv2RoleService.getPrivileges(principal.getLdapRoles());
        } catch (Exception e) {
            logger.debug("Error on getting privileges.", e);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return  null;
        }

        Hashtable<String,List<String>> privCodes = new Hashtable<>();
        Hashtable<String,List<String>> departments = new Hashtable<>();

        if (principal.getLdapRoles().isEmpty()) {
            logger.debug("Error on extracting roles.");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return null;
        }
        else {
            privCodes = getPrivCode(privileges);
        }

        departments = getDept(privCodes);

        return departments;
    }

    @GetMapping(value = {"/ldap_roles","/ldap_roles/{department}"})
    public Hashtable<String, List<String>> getLdapRoles (@PathVariable("department") Optional<String> department, HttpServletResponse response) {

        Hashtable<String,List<String>> codes = new Hashtable<>();
        List<Role> roleCodes;
        List<Role> roles;

        if ( !department.isPresent()) {
            try {
                roles = iBossRoleService.getAll();
            }catch (Exception e) {
                logger.debug("Error on getting roles.", e);
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                return null;
            }
            for (Role rol : roles) {
                if (!codes.containsKey(rol.getDepartment())) {
                    roleCodes = getRolCodes(rol.getDepartment(), response);
                    codes.put(rol.getDepartment(), getCodes(roleCodes));
                }
            }
        }
        else {
            roleCodes = getRolCodes(department.get(), response);
            codes.put(department.get(), getCodes(roleCodes));
        }

        return codes;
    }

    private Hashtable<String,List<String>> getPrivCode (List<Priv2Role> privileges) {

        Hashtable<String,List<String>> privCodes = new Hashtable<>();

        for (Priv2Role priv : privileges) {
            if (privCodes.get(priv.getPrivCode()) != null) {
                privCodes.get(priv.getPrivCode()).add(priv.getRoleCode());
            }
            else {
                List list = new ArrayList<String>();
                list.add(priv.getRoleCode());
                privCodes.put(priv.getPrivCode(), list);
            }
        }

        return privCodes;
    }

    private Hashtable<String,List<String>> getDept (Hashtable<String,List<String>> privCode) {
        Hashtable<String,List<String>> departments = new Hashtable<>();
        List<Role> code;

        for (String key : privCode.keySet()) {
            code = iBossRoleService.getDept(privCode.get(key));
            for (Role rol:code) {
                if (departments.get(key) != null) {
                    if (!departments.get(key).contains(rol.getDepartment()))
                        departments.get(key).add(rol.getDepartment());
                }
                else {
                    List list = new ArrayList<String>();
                    list.add(rol.getDepartment());
                    departments.put(key, list);
                }
            }
        }

        return departments;
    }

    private List<String> getCodes (List<Role> roles) {
        List codeList = new ArrayList<String>();

        for (Role role : roles) {
            codeList.add(role.getCode());
        }

        return codeList;
    }

    private List<Role> getRolCodes (String department, HttpServletResponse response) {
        List<Role> roleCodes;

        try {
            roleCodes = iBossRoleService.getCodes(department);
        } catch (Exception e) {
            logger.debug("Error on getting codes.", e);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }

        return roleCodes;
    }
}
