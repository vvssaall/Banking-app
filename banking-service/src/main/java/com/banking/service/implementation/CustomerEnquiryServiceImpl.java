package com.banking.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banking.dao.entities.AccountStatus;
import com.banking.dao.entities.AccountType;
import com.banking.dao.entities.CustomerSavingEntity;
import com.banking.dao.repositories.AccountStatusRepository;
import com.banking.dao.repositories.AccountTypeRepository;
import com.banking.dao.repositories.CustomerAccountEnquiryRepository;
import com.banking.dtos.CustomerSavingEnquiryDto;
import com.banking.dtos.EmailDto;
import com.banking.email.service.EmailService;
import com.banking.service.exception.BankServiceException;
import com.banking.utils.AccountStatusEnum;
import com.banking.utils.Utils;

@Service
@Transactional
public class CustomerEnquiryServiceImpl implements CustomerEnquiryService {

	@Autowired
	private CustomerAccountEnquiryRepository customeraccountenquiryrepository;

	@Autowired
	private AccountTypeRepository accounttyperepository;

	@Autowired
	private AccountStatusRepository accountstatusrepository;

	@Autowired
	private EmailService emailservice;

	@Value("${bank.from.email:BankofMundrey&Tundrey}")
	private String fromEmail;

	@Override
	public CustomerSavingEnquiryDto save(CustomerSavingEnquiryDto customersavingenquirydto) {

		CustomerSavingEntity customersavingentity = new CustomerSavingEntity();
		BeanUtils.copyProperties(customersavingenquirydto, customersavingentity, new String[] { "accType", "status" });
		Optional<AccountType> accounttype = accounttyperepository.findByName(customersavingenquirydto.getAccType());
		if (accounttype.isPresent()) {
			customersavingentity.setAccType(accounttype.get());
		} else {
			throw new BankServiceException(
					"Hey this" + customersavingenquirydto.getAccType() + "Account type is not valid!");
		}
		AccountStatus accountStatus = new AccountStatus();
		accountStatus.setId(1);
		customersavingentity.setStatus(accountStatus);
		customersavingentity.setDoa(new Date());
		customersavingentity.setAppref("S-" + Utils.genRandomAlphaNum());
		CustomerSavingEntity customersavingEntity = customeraccountenquiryrepository.save(customersavingentity);

		customersavingenquirydto.setCseid(customersavingEntity.getCseId());

		System.out.println("Email sending .." + LocalDateTime.now());
		emailservice
				.sendEquiryEmail(new EmailDto(customersavingenquirydto.getName(), customersavingenquirydto.getEmail(),
						fromEmail, null, "Hello! your account enquiry is submitted successfully."));
		System.out.println("Email done .." + LocalDateTime.now());

		return customersavingenquirydto;

	}
	
	@Override
	public List<CustomerSavingEnquiryDto> findPendingEnquiry() {
		List<CustomerSavingEntity> customerSavingList = customeraccountenquiryrepository.findPendingEnquiries(AccountStatusEnum.PENDING.name());
		return convertEntityIntoDto(customerSavingList);
	}

	@Override
	public boolean emailNotExist(String email) {
		Optional<CustomerSavingEntity> customersaving = customeraccountenquiryrepository.findByEmail(email);
		if (customersaving.isPresent()) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public String updateEnquiryRegId(String cseId, String uuid) {
		CustomerSavingEntity customersaving = customeraccountenquiryrepository.findByAppref(cseId).get();
		customersaving.setUuid(uuid);

		return "done";
	}

	@Override
	public List<CustomerSavingEnquiryDto> findAll() {
		List<CustomerSavingEntity> customerSavingList = customeraccountenquiryrepository.findAll();
		return convertEntityIntoDto(customerSavingList);

	}

	@Override
	public CustomerSavingEnquiryDto findById(int cseId) {
		CustomerSavingEntity customersavingentity = customeraccountenquiryrepository.findById(cseId).get();
		CustomerSavingEnquiryDto customerdto = new CustomerSavingEnquiryDto();
		BeanUtils.copyProperties(customersavingentity, customerdto, new String[] { "accType", "status" });
		customerdto.setAccType(customersavingentity.getAccType().getName());
		customerdto.setStatus(customersavingentity.getStatus().getName());

		return customerdto;

	}

	@Override
	public CustomerSavingEnquiryDto changeEnquiryStatus(String cseId, String status) {
		CustomerSavingEntity customerSavingEntity = customeraccountenquiryrepository.findByAppref(cseId).get();
		AccountStatus accountStatus = accountstatusrepository.findByName(status).get(); // status = APPROVED
		customerSavingEntity.setStatus(accountStatus); // Updating account status
		CustomerSavingEnquiryDto customerSavingdto = new CustomerSavingEnquiryDto(); // Sending Back customer enquire
		BeanUtils.copyProperties(customerSavingEntity, customerSavingdto, new String[] { "accType", "status" });
		customerSavingdto.setAccType(customerSavingEntity.getAccType().getName());
		customerSavingdto.setStatus(customerSavingEntity.getStatus().getName());

		return customerSavingdto;

	}

	@Override
	public Optional<CustomerSavingEnquiryDto> findCustomerEnquiryByUuid(String uuid) {
		Optional<CustomerSavingEntity> customerSavingEntity = customeraccountenquiryrepository.findByUuid(uuid);
		if (customerSavingEntity.isPresent()) {
			CustomerSavingEnquiryDto customerSavingdto = new CustomerSavingEnquiryDto();
			BeanUtils.copyProperties(customerSavingEntity, customerSavingdto, new String[] { "accType", "status" });
			customerSavingdto.setAccType(customerSavingEntity.get().getAccType().getName());
			customerSavingdto.setStatus(customerSavingEntity.get().getStatus().getName());
			return Optional.of(customerSavingdto);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteById(int cseId) {
		customeraccountenquiryrepository.deleteById(cseId);
	}

	private List<CustomerSavingEnquiryDto> convertEntityIntoDto(List<CustomerSavingEntity> customersavingList) {
		List<CustomerSavingEnquiryDto> dtolist = new ArrayList<CustomerSavingEnquiryDto>();
		for (CustomerSavingEntity customerentity : customersavingList) {
			CustomerSavingEnquiryDto customersavingenquirydto = new CustomerSavingEnquiryDto();
			BeanUtils.copyProperties(customerentity, customersavingenquirydto);
			dtolist.add(customersavingenquirydto);
		}
		return dtolist;
	}

}
