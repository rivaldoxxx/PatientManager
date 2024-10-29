import com.example.patientmanager.entities.Patient;
import com.example.patientmanager.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patients")
public class PatientViewController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String showAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients";  // zwraca widok patients.html
    }

    @GetMapping("/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "add-patient";  // zwraca widok add-patient.html
    }

    @PostMapping
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/patients";  // przekierowuje po zapisaniu
    }
}
