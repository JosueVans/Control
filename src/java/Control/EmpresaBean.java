package Control;

import Model.Empresa;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "empresaBean")
@RequestScoped
public class EmpresaBean {

    private Empresa selectedEmpresa;

    public EmpresaBean() {
    }

    public Empresa getSelectedEmpresa() {
        if (selectedEmpresa == null) {
            selectedEmpresa = new Empresa();
        }
        return selectedEmpresa;
    }

    public void setSelectedEmpresa(Empresa selectedEmpresa) {
        this.selectedEmpresa = selectedEmpresa;
    }
    
    
    

}
