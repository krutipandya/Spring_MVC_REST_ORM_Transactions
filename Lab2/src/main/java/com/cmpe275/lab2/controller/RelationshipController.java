package com.cmpe275.lab2.controller;


import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cmpe275.lab2.model.Address;
import com.cmpe275.lab2.model.Frienship;
import com.cmpe275.lab2.model.Organization;
import com.cmpe275.lab2.model.Person;
import com.cmpe275.lab2.service.FriendshipService;
import com.cmpe275.lab2.service.OrganizationService;
import com.cmpe275.lab2.service.PersonService;


@Controller
public class RelationshipController {
	
	
	@Autowired
	private OrganizationService orgService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private FriendshipService friendService;
	
	/*Create Person API*/ 
	
	@RequestMapping(value="/person",method=RequestMethod.POST)
	public ResponseEntity<?> createPerson(Model model, String firstName, String lastName, String email, 
			String description, String city, String state, String zipcode, 
			String country, String street, Integer orgId){
		
		String result="";
		if(firstName!=null && !firstName.isEmpty() && lastName!=null && !lastName.isEmpty() && email!=null && !email.isEmpty()){
			try {
				System.out.println("inside create person");
				Address address = new Address();
				address.setStreet(street);
				address.setCity(city);
				address.setCountry(country);
				address.setZipcode(zipcode);
				address.setState(state);
				
				Person person = new Person();
				person.setDescription(description);
				person.setEmail(email);
				person.setFirstname(firstName);
				person.setLastname(lastName);
				person.setAddress(address);
				if(orgId!=null){
					Organization org = orgService.getOrganization(orgId);
					if(org!=null){
						person.setOrg(org != null ? org : null);
					}
				}
					
				personService.add(person);
				ObjectMapper mapper=new ObjectMapper();
				result=mapper.writeValueAsString(person);
				return new ResponseEntity<>(result, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>("Exception Occured", HttpStatus.BAD_REQUEST);
			}
		}
		else{
			result="Please enter required fields.";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/* - - - - Get Person API - - - - */ 
	
	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET, params = "format=xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody    public ResponseEntity<?> getPersonXml(Model model,@PathVariable("id") int id) 
	{
		String result="";
		try {
			
			System.out.println("inside get person");
			Person person = personService.getPerson(id);
			if(person!=null){
				
				List<Integer> friends = friendService.getAllFriends(person.getId());
				if(!(friends==null))
				{
					person.setFriendsDetails(friends);
				}
				ObjectMapper mapper=new ObjectMapper();
				result=mapper.writeValueAsString(person);
				return new ResponseEntity<>(result, HttpStatus.OK);
				
			}else{
				result="Person does not exist.";
				return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		} 
	}
	
	/*@RequestMapping(value="/person/{id}",method=RequestMethod.GET,  produces="text/html")
	public  String getPersonHTML(Model model,@PathVariable int id){
		String result="";
		try {
			
			System.out.println("inside get person");
			Person person = personService.getPerson(id);
			if(person!=null){
				
				List<Integer> friends = friendService.getAllFriends(person.getId());
				if(!(friends==null))
				{
					person.setFriendsDetails(friends);
				}
				  model.addAttribute("person",person);
			      model.addAttribute("fname",person.getFirstname());
			      model.addAttribute("lname", person.getLastname());
			      model.addAttribute("email", person.getEmail());
			      model.addAttribute("description", person.getDescription());
			      model.addAttribute("orgName", person.getOrg().getName());
			      model.addAttribute("street", person.getAddress().getStreet());
			      model.addAttribute("city", person.getAddress().getCity());
			      model.addAttribute("state", person.getAddress().getState());
			      model.addAttribute("zipcode", person.getAddress().getZipcode());
			      model.addAttribute("country", person.getAddress().getCountry());
			      return "person";
				
			}else{
				result="Person does not exist.";
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} 
	}*/
	
	/* - - - - Update Person API - - - - */
	
	@RequestMapping(value="/person/{id}",method=RequestMethod.POST)
	public ResponseEntity<?> updatePerson(Model model, @PathVariable Integer id, String firstName, String lastName, String email, 
			String description, String city, String state, String zipcode, 
			String country, String street, Integer orgId){
		
		String result="";
		if(email!=null && !email.isEmpty() && id!=null){
			try {
				System.out.println("inside update person");
				Person person = personService.getPerson(id);
				if(person!=null){
					
							if(orgId!=null){
								
								Organization org = orgService.getOrganization(orgId);
								person.setOrg(org != null ? org : null);
							}
							
							Address address = new Address();
							address.setStreet(street);
							address.setCity(city);
							address.setCountry(country);
							address.setZipcode(zipcode);
							address.setState(state);
							
							person.setDescription(description);
							person.setEmail(email);
							person.setFirstname(firstName);
							person.setLastname(lastName);
							person.setAddress(address);
							
							personService.edit(person);
							ObjectMapper mapper=new ObjectMapper();
							result=mapper.writeValueAsString(person);
							return new ResponseEntity<>(result, HttpStatus.OK);
				}else{
					result="Person does not exist.";
					return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>("Exception Occured", HttpStatus.BAD_REQUEST);
			} 
		}else{
			result="Please provide required fields.";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}
	
	/* - - - - Delete Person API - - - - */
	
	@RequestMapping(value="/person/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deletePerson(Model model,@PathVariable Integer id){
		String result="";
		if(id!=null){

			try {
				System.out.println("inside delete person");
				
				Person person = personService.getPerson(id);
				if(person!=null){
					
					List<Integer> friendsList = friendService.getAllFriends(person.getId());
					//System.out.println();
					if(friendsList!=null)
					{
						for(Integer friendId: friendsList){
							deleteFriend(model, person.getId(), friendId);
						}
						person.setFriendsDetails(new ArrayList<Integer>());
					}
					personService.delete(id);
					ObjectMapper mapper=new ObjectMapper();
					result=mapper.writeValueAsString(person);
					return new ResponseEntity<>(result, HttpStatus.OK);
				}
				else{
					result="Person does not exist.";
					return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>("Exception Occured", HttpStatus.BAD_REQUEST);
			}
			
		}else{
			result="Please provide required fields.";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		} 
	}
	
	
	/* - - - - Get Organization API - - - - */
	
	@RequestMapping(value="/org",method=RequestMethod.POST, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> createOrganization(Model model, String name, String email, 
			String description, String city, String state, String zipcode, 
			String country, String street){
		
		String result="";
		if(name!=null && !name.isEmpty()){
			
			try {
				
				Address address = new Address();
				address.setStreet(street);
				address.setCity(city);
				address.setCountry(country);
				address.setZipcode(zipcode);
				address.setState(state);
			
				Organization org = new Organization();
				org.setDescription(description);
				org.setEmail(email);
				org.setName(name);
				org.setAddress(address);
				
				orgService.add(org);
				ObjectMapper mapper=new ObjectMapper();
				result=mapper.writeValueAsString(org);
				return new ResponseEntity<>(result, HttpStatus.OK);
					
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>("Exception Occured", HttpStatus.BAD_REQUEST);
			} 
		}else{
			result="Please provide required fields.";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}
	
	/* - - - - Get Organization API - - - - */
	
	@RequestMapping(value="/org/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getOrganization(Model model,@PathVariable Integer id){
		String result="";
		try {
			System.out.println("inside get organization");
			if(id!=null){
				
				Organization org = orgService.getOrganization(id);
				
				if(org!=null){
					ObjectMapper mapper=new ObjectMapper();
					result=mapper.writeValueAsString(org);
					return new ResponseEntity<>(result, HttpStatus.OK);
				}else{
					result="Organization does not exist.";
					return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
			}
			else{
				result="Please enter required fields.";
				return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Exception Occured", HttpStatus.BAD_REQUEST);
		} 
	}
	
	
	/*Update Organization API*/
	
	@RequestMapping(value="/org/{id}",method=RequestMethod.POST)
	public ResponseEntity<?> updateOrganization(@PathVariable int id, Model model, String name, String email, 
			String description, String city, String state, String zipcode, 
			String country, String street){
		
		String result="";
		
		if(name!=null && !name.isEmpty()){
			try {
				Organization org = orgService.getOrganization(id);
				
				if(org!=null){

					Address address = new Address();
					address.setStreet(street);
					address.setCity(city);
					address.setCountry(country);
					address.setZipcode(zipcode);
					address.setState(state);

					org.setDescription(description);
					org.setEmail(email);
					org.setName(name);
					org.setAddress(address);
					
					orgService.edit(org);
					ObjectMapper mapper=new ObjectMapper();
					result=mapper.writeValueAsString(org);
					return new ResponseEntity<>(result, HttpStatus.OK);
				}else{
					result="Organization does not exist.";
					return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
					
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>("Exception Occured", HttpStatus.BAD_REQUEST);
			} 
		}else{
			result="Please provide required fields.";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}
	
	/* - - - - Delete Organization API - - - - */
	
	@RequestMapping(value="/org/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteOrganization(Model model,@PathVariable int id){
		String result="";
		try {
			System.out.println("inside delete org");
			Organization org = orgService.getOrganization(id);
			if(org != null){
				
				List<Person> listPersons =  personService.getPersonList(org);
				if(listPersons!=null){
					result="Can not delete this organization . Atleast one person belongs to this organization";
					return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
				}else{
					orgService.delete(id);
					ObjectMapper mapper=new ObjectMapper();
					result=mapper.writeValueAsString(org);
					return new ResponseEntity<>(result, HttpStatus.OK);
				}
			}else{
				result="Organization Does not exist.";
				return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Exception Occured", HttpStatus.BAD_REQUEST);
		} 
	}
	
	/* - - - - Create Friends API - - - - */
	
	@RequestMapping(value="/friends/{id1}/{id2}",method=RequestMethod.PUT)
	public ResponseEntity<?> createFriend(Model model,@PathVariable Integer id1,@PathVariable Integer id2){
		
		String result="";
		if(id1 != null && id2 != null && id1 != id2){
			try {
				Person p1 = personService.getPerson(id1);
				Person p2 = personService.getPerson(id2);
				if(p1!=null && p2 != null){
					Frienship f = friendService.search(id1, id2);
					if(f==null){
						f=new Frienship();
						f.setPerson1(p1);
						f.setPerson2(p2);
						friendService.add(f);
						ObjectMapper mapper=new ObjectMapper();
						result=mapper.writeValueAsString(f);
						return new ResponseEntity<>(result, HttpStatus.OK);
					}else{
						result="Friendship already exists.";
						return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
					}
				}else{
					result="Person(/s) does not exist.";
					return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>("Exception Occured", HttpStatus.BAD_REQUEST);
			} 
		}else{
			result="Please provide valid inputs.";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}
	
	/* - - - - Delete Friend API - - - - */
	
	@RequestMapping(value="/friends/{id1}/{id2}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteFriend(Model model,@PathVariable Integer id1,@PathVariable Integer id2){
		String result="";
		if(id1 != null && id2 != null && id1 != id2){
			try {
				
				Person p1 = personService.getPerson(id1);
				Person p2 = personService.getPerson(id2);
				
				if(p1!=null && p2 != null){
					
					Frienship f = friendService.search(id1, id2);
					if(f!=null){
						friendService.delete(f);
						ObjectMapper mapper=new ObjectMapper();
						result=mapper.writeValueAsString(f);
						return new ResponseEntity<>(result, HttpStatus.OK);
					}else{
						result="Friendship does not exist between these two persons";
						return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
					}
				}else{
					result="Friendship does not exist between these two persons";
					return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
				}
					
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>("Exception Occured", HttpStatus.BAD_REQUEST);
			} 
		}else{
			result="Please provide valid inputs.";
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
