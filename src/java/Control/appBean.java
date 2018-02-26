package Control;

import Utils.MyUtil;
//import javax.annotation.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

@Named(value = "appBean")
@ApplicationScoped
public class appBean {

    public appBean() {
    }
    public String getBaseUrl(){
    return MyUtil.baseurl();
    }
    
    public String getBasePath(){
    return MyUtil.basepath();
    }
    public String getBaseUsuario(){
    return MyUtil.baseUsuario();
    }
  
}
