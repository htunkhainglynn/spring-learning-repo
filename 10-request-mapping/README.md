# Read commits for previous changes.

### Why change to xml config?
>Use xml config, so can solve a problem : when the code is changed it needs to compile again, 
then the server could not reload automatically. 
Annotation based can't solve but xml base can.
Now if i change something in my java file and compile again, then it can reload the server automatically.


### About flashAttribute()
>There is 2 things you can do in redirecting a route. 
1) add attribute with addFlashAttribute()
2) add attribute with addAttribute()
both are used for adding attributes within request after directing a route.
1) addFlashAttribute() is handle by FlashManager and can pass even an object, we can extract in our view file easily(recommend)
2) addAttribute() can hold only String, so it is not ok to pass an Object. 
Because if we redirect a route it makes another request and addAttribute() has been added to url and it only accepts String. So it is obvious why there is an error.


### Bird eye view on how attributes are passed when there is an redirect
```
@PostMapping 
	public String save(
			@RequestParam String name,
			@RequestParam Level level,
			@RequestParam int duration,
			@RequestParam int fees,
			RedirectAttributes redirect) {
		var course = new Course(name, level, duration, fees);
		var id = service.create(course);
		redirect.addFlashAttribute("result", new Result(status.Success, "Successfully created!"));  // passing object
		// redirect.addAttribute("result", new Result(status.Success, "Successfully created!"));  // will prompt error, can't convert object to String
		return "redirect:/course/detail?id=%d".formatted(id); // redirect a url
	}
```
In this code, the attribute "successfully created!" is added to model as value and its key is "result". Then another request comes in, 

```
@GetMapping("detail") 
	public String findById(@RequestParam int id, ModelMap model) {
		var course = service.findById(id);
		model.put("course", course);
		return "course-details";
	}
```
in the model, the attribute "result" : "successfully created!" is already there. when ``model.put("course", course);`` this line runs the model becomes 


| Key      | Value                   |
| -------  | ----------------------- |
| result   | successfully created!   |
| course   | course object           |

