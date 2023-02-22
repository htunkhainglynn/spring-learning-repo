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


### Bird eye view on how attributes are passed when there is a redirect
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
In this code, the attribute result object (Strings are ok too) is added to model as value and its key is "result". Then another request comes in, 

```
@GetMapping("detail") 
public String findById(@RequestParam int id, ModelMap model) {
	var course = service.findById(id);
	model.put("course", course);
	return "course-details";
}
```
in the model, the attribute "result" : result object is already there. when ``model.put("course", course);`` this line runs the model becomes 


| Key      | Value                   |
| -------  | ----------------------- |
| result   | result object           |
| course   | course object           |


### @ModelAttribute
There is 3 ways to use @ModelAttribute

1)Extract request parameters and create an object.
Before @ModelAttribute

```
@PostMapping
public String save(
	@RequestParam String name,
	@RequestParam Level level,
	@RequestParam int duration,
	@RequestParam int fees,
	@ModelAttribute Course course,
	RedirectAttributes redirect) {
var course = new Course(name, level, duration, fees);
var id = service.create(course);
redirect.addFlashAttribute("result", new Result(status.Success, "Successfully created!"));
```

Using @ModelAttribute

```
@PostMapping 
public String save(
		@ModelAttribute Course course, 
		RedirectAttributes redirect) {
	var id = service.create(course);
	redirect.addFlashAttribute("result", new Result(status.Success, "Successfully created!"));
	return "redirect:/course/detail?id=%d".formatted(id);
}
```

2) Its function is similar to aop. It is used when you want to add attribute before every methods of controller is called. When a method is put @ModelAttribute, if a method that handles a route is called, there is attributes in model which are added by the method that has been put @ModelAttribute.

2 types of methods that can put @ModelAttribute

1) void method

```
@ModelAttribute
public void loadLevels(ModelMap model) {
	model.put("levels", Level.values());
}
```

2) with return type. It needs to add like this ``@ModelAttribute("levels")`` because in view file it needs to catch a variable, so this is how to set the variable name.

```
@ModelAttribute("levels")
public Level[] loadLevels(ModelMap model) {
	return Level.values();
}
```





